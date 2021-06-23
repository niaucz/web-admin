package com.example.webadmin.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 当前线程
 * </p>
 *
 * @author yh.zhao
 * @since 2020-05-26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalMap {

    /**
     * 静态化当前线程
     */
    private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    /**
     * 为当前线程添加内容 key，value
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    /**
     * 根据key值删除当前线程内容
     *
     * @param key
     * @return
     */
    public static Object remove(String key) {
        return getContextMap().remove(key);
    }

    /**
     * 根据key 获取当前线程内的内容
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return getContextMap().get(key);
    }

    /**
     * 取得当前线程
     *
     * @return
     */
    private static Map<String, Object> getContextMap() {
        return THREAD_CONTEXT.get();
    }

    /**
     * 清空线程内容，慎用
     */
    public static void remove() {
        getContextMap().clear();
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(8) {

                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }
}