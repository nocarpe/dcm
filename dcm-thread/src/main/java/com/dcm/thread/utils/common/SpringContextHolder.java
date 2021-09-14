package com.dcm.thread.utils.common;

public class SpringContextHolder {
    private static ApplicationContext applicationContext;
    private static boolean isMocked;

    public static synchronized void setApplicationContextForTest(ApplicationContext mockApplicationContext) {
        applicationContext = mockApplicationContext;
        isMocked = true;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        return beanMaps != null && !beanMaps.isEmpty() ? beanMaps.values().iterator().next() : null;
    }

    /** @deprecated */
    @Deprecated
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static <T> T getBeanByName(String name, Class<T> clazz) {
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        return beanMaps.get(name);
    }

    public SpringContextHolder(ApplicationContext arg0) {
        applicationContext = arg0;
    }

    public static boolean isMocked() {
        return isMocked;
    }
}