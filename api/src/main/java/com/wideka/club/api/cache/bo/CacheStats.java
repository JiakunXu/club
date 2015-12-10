package com.wideka.club.api.cache.bo;

import java.util.Date;

import com.wideka.club.framework.bo.SearchInfo;

/**
 * cache狀態對象.
 * 
 * @author xujiakun
 * 
 */
public class CacheStats extends SearchInfo {

	private static final long serialVersionUID = 2587109565600354500L;

	private Long id;

	/**
	 * key.
	 */
	private String address;

	private String hostAddress;

	private String hostName;

	private String port;

	/**
	 * Number of keys that have been deleted and found present.
	 */
	private String deleteHits;

	/**
	 * 表示系统存储缓存对象所使用的存储空间，单位为字节.
	 */
	private String bytes;

	/**
	 * 表示从memcached服务启动到当前时间，系统存储过的所有对象的数量，包括目前已经从缓存中删除的对象.
	 */
	private String totalItems;

	/**
	 * Total system time for this instance (seconds:microseconds).
	 */
	private String rusageSystem;

	private String touchMisses;

	private String cmdTouch;

	/**
	 * Number of denied connection attempts because memcached reached it’s
	 * configured connection limit (“-c” command line argument).
	 */
	private String listenDisabledNum;

	/**
	 * Number of failed authentication tries of clients.
	 */
	private String authErrors;

	/**
	 * 为了给新的数据项目释放空间，从缓存移除的缓存对象的数目。比如超过缓存大小时根据LRU算法移除的对象，以及过期的对象.
	 */
	private String evictions;

	/**
	 * memcached组件的版本.
	 */
	private String version;

	/**
	 * 服务器所在主机操作系统的指针大小，一般为32或64.
	 */
	private String pointerSize;

	/**
	 * memcached服务器所在主机当前系统的时间，单位是秒.
	 */
	private String time;

	/**
	 * Number of keys that have been incremented and found present.
	 */
	private String incrHits;

	/**
	 * 被请求的工作线程的总数量.
	 */
	private String threads;

	private String expiredUnfetched;

	/**
	 * memcached服务缓存允许使用的最大字节数.
	 */
	private String limitMaxbytes;

	private String hashIsExpanding;

	/**
	 * memcached服务器从网络读取的总的字节数.
	 */
	private String bytesRead;

	/**
	 * 表示当前系统打开的连接数.
	 */
	private String currConnections;

	/**
	 * 表示获取数据失败的次数.
	 */
	private String getMisses;

	/**
	 * Numer of times a write command to the cached used memory from another
	 * expired key. These are not storage operations deleting old items due to a
	 * full cache.
	 */
	private String reclaimed;

	/**
	 * memcached服务器发送到网络的总的字节数.
	 */
	private String bytesWritten;

	private String hashPowerLevel;

	/**
	 * 表示从memcached服务启动到当前时间，被服务器分配的连接结构的数量.
	 */
	private String connectionStructures;

	/**
	 * Number of keys that have been compared and swapped and found present.
	 */
	private String casHits;

	/**
	 * Number of items that have been delete and not found.
	 */
	private String deleteMisses;

	/**
	 * 表示从memcached服务启动到当前时间，系统打开过的连接的总数.
	 */
	private String totalConnections;

	/**
	 * Total user time for this instance (seconds:microseconds).
	 */
	private String rusageUser;

	/**
	 * The “flush_all” command clears the whole cache and shouldn’t be used
	 * during normal operation.
	 */
	private String cmdFlush;

	private String libevent;

	/**
	 * memcached服务从启动到当前所经过的时间，单位是秒.
	 */
	private String uptime;

	private String reservedFds;

	private String touchHits;

	/**
	 * The “cas” command is some kind of Memcached’s way to avoid locking. “cas”
	 * calls with bad identifier are counted in this stats key.
	 */
	private String casBadval;

	/**
	 * memcached服务进程的进程ID.
	 */
	private String pid;

	/**
	 * 表示获取数据成功的次数.
	 */
	private String getHits;

	/**
	 * 表示当前缓存中存放的所有缓存对象的数量。不包括目前已经从缓存中删除的对象.
	 */
	private String currItems;

	/**
	 * Number of items that have been compared and swapped and not found.
	 */
	private String casMisses;

	/**
	 * The Memcached server is currently accepting new connections.
	 */
	private String acceptingConns;

	private String evictedUnfetched;

	/**
	 * 累积获取数据的数量.
	 */
	private String cmdGet;

	/**
	 * 累积保存数据的数量.
	 */
	private String cmdSet;

	/**
	 * Number of authentication commands processed by the server – if you use
	 * authentication within your installation. The default is IP (routing)
	 * level security which speeds up the actual Memcached usage by removing the
	 * authentication requirement
	 */
	private String authCmds;

	/**
	 * Number of failed “incr” commands (see incr_hits).
	 */
	private String incrMisses;

	private String hashBytes;

	/**
	 * Number of items that have been decremented and not found.
	 */
	private String decrMisses;

	/**
	 * Number of keys that have been decremented and found present.
	 */
	private String decrHits;

	/**
	 * Memcached has a configurable maximum number of requests per event (-R
	 * command line argument), this counter shows the number of times any client
	 * hit this limit.
	 */
	private String connYields;

	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDeleteHits() {
		return deleteHits;
	}

	public void setDeleteHits(String deleteHits) {
		this.deleteHits = deleteHits;
	}

	public String getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}

	public String getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public String getRusageSystem() {
		return rusageSystem;
	}

	public void setRusageSystem(String rusageSystem) {
		this.rusageSystem = rusageSystem;
	}

	public String getTouchMisses() {
		return touchMisses;
	}

	public void setTouchMisses(String touchMisses) {
		this.touchMisses = touchMisses;
	}

	public String getCmdTouch() {
		return cmdTouch;
	}

	public void setCmdTouch(String cmdTouch) {
		this.cmdTouch = cmdTouch;
	}

	public String getListenDisabledNum() {
		return listenDisabledNum;
	}

	public void setListenDisabledNum(String listenDisabledNum) {
		this.listenDisabledNum = listenDisabledNum;
	}

	public String getAuthErrors() {
		return authErrors;
	}

	public void setAuthErrors(String authErrors) {
		this.authErrors = authErrors;
	}

	public String getEvictions() {
		return evictions;
	}

	public void setEvictions(String evictions) {
		this.evictions = evictions;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPointerSize() {
		return pointerSize;
	}

	public void setPointerSize(String pointerSize) {
		this.pointerSize = pointerSize;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIncrHits() {
		return incrHits;
	}

	public void setIncrHits(String incrHits) {
		this.incrHits = incrHits;
	}

	public String getThreads() {
		return threads;
	}

	public void setThreads(String threads) {
		this.threads = threads;
	}

	public String getExpiredUnfetched() {
		return expiredUnfetched;
	}

	public void setExpiredUnfetched(String expiredUnfetched) {
		this.expiredUnfetched = expiredUnfetched;
	}

	public String getLimitMaxbytes() {
		return limitMaxbytes;
	}

	public void setLimitMaxbytes(String limitMaxbytes) {
		this.limitMaxbytes = limitMaxbytes;
	}

	public String getHashIsExpanding() {
		return hashIsExpanding;
	}

	public void setHashIsExpanding(String hashIsExpanding) {
		this.hashIsExpanding = hashIsExpanding;
	}

	public String getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(String bytesRead) {
		this.bytesRead = bytesRead;
	}

	public String getCurrConnections() {
		return currConnections;
	}

	public void setCurrConnections(String currConnections) {
		this.currConnections = currConnections;
	}

	public String getGetMisses() {
		return getMisses;
	}

	public void setGetMisses(String getMisses) {
		this.getMisses = getMisses;
	}

	public String getReclaimed() {
		return reclaimed;
	}

	public void setReclaimed(String reclaimed) {
		this.reclaimed = reclaimed;
	}

	public String getBytesWritten() {
		return bytesWritten;
	}

	public void setBytesWritten(String bytesWritten) {
		this.bytesWritten = bytesWritten;
	}

	public String getHashPowerLevel() {
		return hashPowerLevel;
	}

	public void setHashPowerLevel(String hashPowerLevel) {
		this.hashPowerLevel = hashPowerLevel;
	}

	public String getConnectionStructures() {
		return connectionStructures;
	}

	public void setConnectionStructures(String connectionStructures) {
		this.connectionStructures = connectionStructures;
	}

	public String getCasHits() {
		return casHits;
	}

	public void setCasHits(String casHits) {
		this.casHits = casHits;
	}

	public String getDeleteMisses() {
		return deleteMisses;
	}

	public void setDeleteMisses(String deleteMisses) {
		this.deleteMisses = deleteMisses;
	}

	public String getTotalConnections() {
		return totalConnections;
	}

	public void setTotalConnections(String totalConnections) {
		this.totalConnections = totalConnections;
	}

	public String getRusageUser() {
		return rusageUser;
	}

	public void setRusageUser(String rusageUser) {
		this.rusageUser = rusageUser;
	}

	public String getCmdFlush() {
		return cmdFlush;
	}

	public void setCmdFlush(String cmdFlush) {
		this.cmdFlush = cmdFlush;
	}

	public String getLibevent() {
		return libevent;
	}

	public void setLibevent(String libevent) {
		this.libevent = libevent;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getReservedFds() {
		return reservedFds;
	}

	public void setReservedFds(String reservedFds) {
		this.reservedFds = reservedFds;
	}

	public String getTouchHits() {
		return touchHits;
	}

	public void setTouchHits(String touchHits) {
		this.touchHits = touchHits;
	}

	public String getCasBadval() {
		return casBadval;
	}

	public void setCasBadval(String casBadval) {
		this.casBadval = casBadval;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getGetHits() {
		return getHits;
	}

	public void setGetHits(String getHits) {
		this.getHits = getHits;
	}

	public String getCurrItems() {
		return currItems;
	}

	public void setCurrItems(String currItems) {
		this.currItems = currItems;
	}

	public String getCasMisses() {
		return casMisses;
	}

	public void setCasMisses(String casMisses) {
		this.casMisses = casMisses;
	}

	public String getAcceptingConns() {
		return acceptingConns;
	}

	public void setAcceptingConns(String acceptingConns) {
		this.acceptingConns = acceptingConns;
	}

	public String getEvictedUnfetched() {
		return evictedUnfetched;
	}

	public void setEvictedUnfetched(String evictedUnfetched) {
		this.evictedUnfetched = evictedUnfetched;
	}

	public String getCmdGet() {
		return cmdGet;
	}

	public void setCmdGet(String cmdGet) {
		this.cmdGet = cmdGet;
	}

	public String getCmdSet() {
		return cmdSet;
	}

	public void setCmdSet(String cmdSet) {
		this.cmdSet = cmdSet;
	}

	public String getAuthCmds() {
		return authCmds;
	}

	public void setAuthCmds(String authCmds) {
		this.authCmds = authCmds;
	}

	public String getIncrMisses() {
		return incrMisses;
	}

	public void setIncrMisses(String incrMisses) {
		this.incrMisses = incrMisses;
	}

	public String getHashBytes() {
		return hashBytes;
	}

	public void setHashBytes(String hashBytes) {
		this.hashBytes = hashBytes;
	}

	public String getDecrMisses() {
		return decrMisses;
	}

	public void setDecrMisses(String decrMisses) {
		this.decrMisses = decrMisses;
	}

	public String getDecrHits() {
		return decrHits;
	}

	public void setDecrHits(String decrHits) {
		this.decrHits = decrHits;
	}

	public String getConnYields() {
		return connYields;
	}

	public void setConnYields(String connYields) {
		this.connYields = connYields;
	}

	public Date getCreateDate() {
		return createDate != null ? (Date) createDate.clone() : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			this.createDate = (Date) createDate.clone();
		}
	}

}
