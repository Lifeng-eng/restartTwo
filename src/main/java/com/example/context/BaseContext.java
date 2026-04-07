package com.example.context;

public class BaseContext {

    // 创建一个 ThreadLocal 变量，用于存储当前用户的 ID
    private static final ThreadLocal<Long> THREAD_LOCAL_ID = new ThreadLocal<>();
    
    // 如果需要存储用户名，可以再定义一个
    private static final ThreadLocal<String> THREAD_LOCAL_USERNAME = new ThreadLocal<>();

    /**
     * 设置当前用户 ID
     */
    public static void setCurrentId(Long id) {
        THREAD_LOCAL_ID.set(id);
    }

    /**
     * 获取当前用户 ID
     */
    public static Long getCurrentId() {
        return THREAD_LOCAL_ID.get();
    }

    /**
     * 设置当前用户名
     */
    public static void setCurrentUsername(String username) {
        THREAD_LOCAL_USERNAME.set(username);
    }

    /**
     * 获取当前用户名
     */
    public static String getCurrentUsername() {
        return THREAD_LOCAL_USERNAME.get();
    }

    /**
     * 移除当前线程的数据 (必须在请求结束后调用)
     */
    public static void removeCurrentId() {
        THREAD_LOCAL_ID.remove();
        THREAD_LOCAL_USERNAME.remove();
    }
}