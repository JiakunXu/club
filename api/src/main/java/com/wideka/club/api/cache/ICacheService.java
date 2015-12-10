package com.wideka.club.api.cache;

import java.util.Date;

import com.wideka.club.framework.exception.ServiceException;

/**
 * cacheService.
 * 
 * @author xujiakun
 * 
 * @param <K>
 * @param <V>
 */
public interface ICacheService<K, V> {

	/**
	 * 保存数据.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	V add(K key, V value) throws ServiceException;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws ServiceException
	 */
	V add(K key, V value, Date expiry) throws ServiceException;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 数据超时的秒数
	 * @return
	 * @throws ServiceException
	 */
	V add(K key, V value, int exp) throws ServiceException;

	/**
	 * 保存数据.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	V set(K key, V value) throws ServiceException;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws ServiceException
	 */
	V set(K key, V value, Date expiry) throws ServiceException;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 数据超时的秒数
	 * @return
	 * @throws ServiceException
	 */
	V set(K key, V value, int exp) throws ServiceException;

	/**
	 * 保存数据,前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	V replace(K key, V value) throws ServiceException;

	/**
	 * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws ServiceException
	 */
	V replace(K key, V value, Date expiry) throws ServiceException;

	/**
	 * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws ServiceException
	 */
	V replace(K key, V value, int exp) throws ServiceException;

	/**
	 * 获取缓存数据.
	 * 
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	V get(K key) throws ServiceException;

	/**
	 * 移出缓存数据.
	 * 
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	V remove(K key) throws ServiceException;

}
