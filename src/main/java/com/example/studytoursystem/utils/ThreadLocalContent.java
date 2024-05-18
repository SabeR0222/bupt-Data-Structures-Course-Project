package com.example.studytoursystem.utils;

import java.util.concurrent.atomic.AtomicReference;

public class ThreadLocalContent {
    private static class ThreadLocalVariable<T> extends java.lang.ThreadLocal<T> {
        @Override
        protected T initialValue() {
            return null;
        }
    }

    private static final java.lang.ThreadLocal<AtomicReference<Object>> THREAD_LOCAL_DATA = new ThreadLocalVariable<>();

    /**
     * 设置ThreadLocal中的数据
     *
     * @param data 数据对象
     */
    public static void setData(Object data) {
        THREAD_LOCAL_DATA.get().set(data);
    }

    /**
     * 获取ThreadLocal中的数据
     *
     * @return 数据对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getData() {
        return (T) THREAD_LOCAL_DATA.get().get();
    }

    /**
     * 清除ThreadLocal中的数据
     */
    public static void removeData() {
        THREAD_LOCAL_DATA.remove();
    }
}
