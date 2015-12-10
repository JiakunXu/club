package com.wideka.club.framework.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.cache.service.impl.MemcachedCacheServiceImpl;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.PropertiesUtil;

/**
 * 统一cache管理session.
 * 
 * @author xujiakun
 * 
 */
public class SessionFilter implements Filter {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SessionFilter.class);

	private MemcachedCacheServiceImpl memcachedCacheService;

	public void destroy() {
		this.memcachedCacheService = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
		ServletException {
		if (req instanceof HttpServletRequest) {
			doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}

	@SuppressWarnings("unchecked")
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		String requestSessionId = null;
		HttpSession session = null;
		Map<String, Object> map = null;

		try {
			requestSessionId = request.getRequestedSessionId();

			if (StringUtils.isNotEmpty(requestSessionId)) {
				map = (Map<String, Object>) this.memcachedCacheService.get(requestSessionId);

				if (map != null && map.size() != 0) {
					session = request.getSession();
					if (session != null) {
						for (Map.Entry<String, Object> m : map.entrySet()) {
							session.setAttribute(m.getKey(), m.getValue());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		chain.doFilter(request, response);

		try {
			session = request.getSession(false);

			if (session != null) {
				String sessionId = session.getId();

				if (StringUtils.isNotEmpty(sessionId)) {
					@SuppressWarnings("rawtypes")
					Enumeration attributeNames = session.getAttributeNames();
					map = new HashMap<String, Object>();

					while (attributeNames.hasMoreElements()) {
						String attributeName = (String) attributeNames.nextElement();
						map.put(attributeName, session.getAttribute(attributeName));
					}

					this.memcachedCacheService
						.set(sessionId, map, IMemcachedCacheService.CACHE_KEY_SESSION_DEFAULT_EXP);

					if (!sessionId.equals(requestSessionId) && StringUtils.isNotEmpty(requestSessionId)) {
						this.memcachedCacheService.set(requestSessionId, map,
							IMemcachedCacheService.CACHE_KEY_SESSION_EXP);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		map = null;
		session = null;
	}

	public void init(FilterConfig config) throws ServletException {
		try {
			Properties proobj = PropertiesUtil.loadProperties("env.properties");

			MemcachedClientBuilder builder =
				new XMemcachedClientBuilder(AddrUtil.getAddresses((String) proobj.get("xmemcached.memcached.servers")));

			builder.setConnectionPoolSize(2);
			builder.setCommandFactory(new BinaryCommandFactory());
			builder.setSessionLocator(new KetamaMemcachedSessionLocator());
			builder.setTranscoder(new SerializingTranscoder());

			MemcachedClient client = builder.build();

			this.memcachedCacheService = new MemcachedCacheServiceImpl();
			this.memcachedCacheService.setMemcachedClient(client);

		} catch (IOException e) {
			logger.error(e);
		}
	}

}
