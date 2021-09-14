package com.dcm.thread.utils.common;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class BeanUtil {
    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);
    private static Map<String, BeanStructure> beanStructureCache = new HashMap();

    public BeanUtil() {
    }

    public static <X, Y> Y beanTransfer(X srcObject, Class<Y> targetClass) {
        Y returnValue = null;
        if (srcObject != null && targetClass != null) {
            try {
                returnValue = targetClass.newInstance();
                copyProperties(srcObject, returnValue);
            } catch (InstantiationException var4) {
                log.error("beanTransfer new instance error.", var4);
            } catch (IllegalAccessException var5) {
                log.error("beanTransfer illegal access error.", var5);
            }
        }

        return returnValue;
    }

    public static <T> T beanTransfer(Map<String, String> srcData, Class<T> targetClass) {
        T returnValue = null;
        if (!CollectionUtil.isNullOrEmpty(srcData) && targetClass != null) {
            try {
                returnValue = targetClass.newInstance();
                Iterator var3 = srcData.entrySet().iterator();

                while(var3.hasNext()) {
                    Entry<String, String> entry = (Entry)var3.next();
                    setPropertyValueWithCast(returnValue, (String)entry.getKey(), (String)entry.getValue());
                }
            } catch (InstantiationException var5) {
                log.error("beanTransfer new instance error.", var5);
            } catch (IllegalAccessException var6) {
                log.error("beanTransfer illegal access error.", var6);
            }
        }

        return returnValue;
    }

    public static <X, Y> List<Y> beanListTransfer(List<X> srcObjects, Class<Y> targetClass) {
        ArrayList returnValue = new ArrayList();

        try {
            Iterator var3 = srcObjects.iterator();

            while(var3.hasNext()) {
                X srcObject = var3.next();
                Y targetObject = targetClass.newInstance();
                copyProperties(srcObject, targetObject);
                returnValue.add(targetObject);
            }
        } catch (InstantiationException var6) {
            log.error("beanTransfer new instance error.", var6);
        } catch (IllegalAccessException var7) {
            log.error("beanTransfer illegal access error.", var7);
        }

        return returnValue;
    }

    public static void copyProperties(Object srcObject, Object targetObject) {
        if (srcObject != null && targetObject != null) {
            BeanStructure srcBeanStructure = getBeanStructure(srcObject.getClass());
            BeanStructure targetBeanStructure = getBeanStructure(targetObject.getClass());
            Iterator var4 = targetBeanStructure.getProperties().values().iterator();

            while(var4.hasNext()) {
                BeanProperty targetBeanProperty = (BeanProperty)var4.next();
                BeanProperty srcBeanProperty = (BeanProperty)srcBeanStructure.getProperties().get(targetBeanProperty.getName());
                if (srcBeanProperty != null && srcBeanProperty.getGetterMethod() != null && targetBeanProperty.getSetterMethod() != null && srcBeanProperty.getType().equals(targetBeanProperty.getType())) {
                    try {
                        targetBeanProperty.getSetterMethod().invoke(targetObject, srcBeanProperty.getGetterMethod().invoke(srcObject));
                    } catch (Exception var8) {
                        log.error("copyProperties error.", var8);
                    }
                }
            }

        } else {
            log.error("The coping source or target objects is null.");
        }
    }

    public static void copyMapToObject(Map<String, String> srcData, Object targetObject) {
        if (!CollectionUtil.isNullOrEmpty(srcData) && targetObject != null) {
            Iterator var2 = srcData.entrySet().iterator();

            while(var2.hasNext()) {
                Entry<String, String> entry = (Entry)var2.next();
                setPropertyValueWithCast(targetObject, (String)entry.getKey(), (String)entry.getValue());
            }
        } else {
            log.error("The coping src data or target objects is null.");
        }

    }

    public static void setPropertyValue(Object targetObject, String propertyName, Object value) {
        if (targetObject != null && !StringUtils.isNullOrEmpty(propertyName)) {
            BeanStructure targetBeanStructure = getBeanStructure(targetObject.getClass());
            BeanProperty targetBeanProperty = (BeanProperty)targetBeanStructure.getProperties().get(propertyName);
            if (targetBeanProperty.getSetterMethod() != null) {
                try {
                    targetBeanProperty.getSetterMethod().invoke(targetObject, value);
                } catch (Exception var6) {
                    log.error("setProperty error.", var6);
                }
            }
        } else {
            log.error("The target object or property is null.");
        }

    }

    public static void setPropertyValueWithCast(Object targetObject, String propertyName, String value) {
        if (targetObject != null && !StringUtils.isNullOrEmpty(propertyName)) {
            BeanStructure targetBeanStructure = getBeanStructure(targetObject.getClass());
            BeanProperty targetBeanProperty = (BeanProperty)targetBeanStructure.getProperties().get(propertyName);
            if (targetBeanProperty.getSetterMethod() != null) {
                Object castValue = StringUtils.castToBaseObject(value, targetBeanProperty.getType());
                targetBeanProperty.getType().cast(value);
                if (castValue != null) {
                    try {
                        targetBeanProperty.getSetterMethod().invoke(targetObject, castValue);
                    } catch (Exception var7) {
                        log.error("setProperty error.", var7);
                    }
                }
            }
        } else {
            log.error("The target object or property is null.");
        }

    }

    public static void setPropertyValues(Collection<? extends Object> targetObjects, String propertyName, Object value) {
        Iterator var3 = targetObjects.iterator();

        while(var3.hasNext()) {
            Object obj = var3.next();
            setPropertyValue(obj, propertyName, value);
        }

    }

    public static <T> T getPropertyValue(Object object, String propertyName, Class<T> clazz) {
        return getPropertyValue(object, propertyName);
    }

    public static <T> T getPropertyValue(Object object, String propertyName) {
        T returnValue = null;
        if (object != null && propertyName != null) {
            BeanStructure srcBeanStructure = getBeanStructure(object.getClass());
            BeanProperty beanProperty = (BeanProperty)srcBeanStructure.getProperties().get(propertyName);
            if (beanProperty != null) {
                Method getterMethod = beanProperty.getGetterMethod();
                if (getterMethod != null) {
                    try {
                        returnValue = getterMethod.invoke(object);
                    } catch (Exception var7) {
                        log.error("getPropertyValue error.", var7);
                    }
                }
            }
        } else {
            log.error("The object or property name is null.");
        }

        return returnValue;
    }

    public static BeanStructure getBeanStructure(Class clazz) {
        BeanStructure returnValue = (BeanStructure)beanStructureCache.get(clazz.getName());
        if (returnValue != null) {
            return returnValue;
        } else {
            returnValue = new BeanStructure();
            Map<String, Class> allProperties = getProperties(clazz);

            Entry entry;
            BeanProperty beanProperty;
            for(Iterator var3 = allProperties.entrySet().iterator(); var3.hasNext(); returnValue.getProperties().put(entry.getKey(), beanProperty)) {
                entry = (Entry)var3.next();
                Set<String> getterMethodNames = getGetterMethodNames((String)entry.getKey());
                Set<String> setterMethodNames = getSetterMethodNames((String)entry.getKey());
                beanProperty = new BeanProperty((String)entry.getKey(), (Class)entry.getValue());
                boolean hasSetter = false;
                Method setterMethod = null;
                Iterator var10 = setterMethodNames.iterator();

                String getterMethod;
                while(var10.hasNext()) {
                    getterMethod = (String)var10.next();
                    setterMethod = getMethod(clazz, getterMethod, (Class)entry.getValue());
                    hasSetter = hasSetter || setterMethod != null;
                    if (hasSetter) {
                        beanProperty.setSetterMethod(setterMethod);
                        break;
                    }
                }

                boolean hasGetter = false;
                getterMethod = null;
                Iterator var12 = getterMethodNames.iterator();

                while(var12.hasNext()) {
                    String getterMethodName = (String)var12.next();
                    Method getterMethod = getMethod(clazz, getterMethodName);
                    hasGetter = hasGetter || getterMethod != null && getterMethod.getReturnType().equals(entry.getValue());
                    if (hasGetter) {
                        beanProperty.setGetterMethod(getterMethod);
                        break;
                    }
                }
            }

            beanStructureCache.put(clazz.getName(), returnValue);
            return returnValue;
        }
    }

    public static Map<String, Class> getProperties(Class clazz) {
        Map<String, Class> returnValue = new HashMap();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] var3 = declaredFields;
        int var4 = declaredFields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            returnValue.put(field.getName(), field.getType());
        }

        if (!clazz.getSuperclass().equals(Object.class)) {
            returnValue.putAll(getProperties(clazz.getSuperclass()));
        }

        return returnValue;
    }

    public static <X, Y> Set<X> getPropertySetFromList(final Collection<Y> list, final String propertyName, Class<X> xClass) {
        return getPropertySetFromList(list, propertyName);
    }

    public static <X, Y> Set<X> getPropertySetFromList(final Collection<Y> list, final String propertyName) {
        LinkedHashSet returnValue = new LinkedHashSet();

        try {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Y item = var3.next();
                X attributeValue = getPropertyValue(item, propertyName);
                if (attributeValue != null) {
                    returnValue.add(attributeValue);
                }
            }
        } catch (Exception var6) {
            log.error("getPropertySetFromList error", var6);
        }

        return returnValue;
    }

    public static <X, Y> List<X> getPropertyListFromList(final Collection<Y> list, final String propertyName, Class<X> xClass) {
        return getPropertyListFromList(list, propertyName);
    }

    public static <X, Y> List<X> getPropertyListFromList(final Collection<Y> list, final String propertyName) {
        ArrayList returnValue = new ArrayList();

        try {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Y item = var3.next();
                X attributeValue = getPropertyValue(item, propertyName);
                if (attributeValue != null) {
                    returnValue.add(attributeValue);
                }
            }
        } catch (Exception var6) {
            log.error("getPropertyListFromList error", var6);
        }

        return returnValue;
    }

    public static <K, V> Map<K, V> listToMapTransfer(final Collection<V> list, final String mapKeyPropertyName) {
        Map<K, V> returnValue = new HashMap();
        if (!CollectionUtil.isNullOrEmpty(list) || StringUtils.isNullOrEmpty(mapKeyPropertyName)) {
            try {
                Iterator var3 = list.iterator();

                while(var3.hasNext()) {
                    V item = var3.next();
                    K propertyValue = getPropertyValue(item, mapKeyPropertyName);
                    if (propertyValue != null) {
                        returnValue.put(propertyValue, item);
                    }
                }
            } catch (Exception var6) {
                log.error("listToMapTransfer error", var6);
            }
        }

        return returnValue;
    }

    public static <V, F> List<V> filterObjectListByObjectValues(final Collection<V> srcObjects, final String srcPropertyName, final Collection<F> filterObjects, final String filterPropertyName) {
        List<V> returnValue = new ArrayList();
        if (!CollectionUtil.isNullOrEmpty(srcObjects) && !StringUtils.isNullOrEmpty(srcPropertyName) && !CollectionUtil.isNullOrEmpty(filterObjects) && !StringUtils.isNullOrEmpty(filterPropertyName)) {
            Set<?> filterValues = getPropertySetFromList(filterObjects, filterPropertyName);
            returnValue.addAll(filterObjectsByPropertyValues(srcObjects, srcPropertyName, filterValues));
        }

        return returnValue;
    }

    public static <V, F> List<V> filterObjectsByPropertyValues(final Collection<V> srcObjects, final String srcPropertyName, final Set<F> filterPropertyValues) {
        List<V> returnValue = new LinkedList();
        if (!CollectionUtil.isNullOrEmpty(srcObjects) && !CollectionUtil.isNullOrEmpty(filterPropertyValues) && !StringUtils.isNullOrEmpty(srcPropertyName)) {
            try {
                Iterator var4 = srcObjects.iterator();

                while(var4.hasNext()) {
                    V srcObject = var4.next();
                    F propertyValue = getPropertyValue(srcObject, srcPropertyName);
                    if (propertyValue != null && filterPropertyValues.contains(propertyValue)) {
                        returnValue.add(srcObject);
                    }
                }
            } catch (Exception var7) {
                log.error("filterObjectsByPropertyValues error", var7);
            }
        }

        return returnValue;
    }

    private static Method getMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (Exception var4) {
            return null;
        }
    }

    private static Set<String> getSetterMethodNames(String propertyName) {
        Set<String> returnValue = new HashSet();
        returnValue.add("set" + StringUtils.capitalize(propertyName));
        return returnValue;
    }

    private static Set<String> getGetterMethodNames(String propertyName) {
        Set<String> returnValue = new HashSet();
        returnValue.add("get" + StringUtils.capitalize(propertyName));
        returnValue.add("is" + StringUtils.capitalize(propertyName));
        returnValue.add("has" + StringUtils.capitalize(propertyName));
        return returnValue;
    }

    public static <T> T copyProperties(Object srcObject, Class<T> targertObject) {
        Object returnValue = null;

        try {
            returnValue = targertObject.newInstance();
            BeanUtils.copyProperties(srcObject, returnValue);
        } catch (Exception var4) {
        }

        return returnValue;
    }

    public static <T, Tc> List<T> copyPropertiesList(List<Tc> srcObjects, Class<T> targetObject) {
        List<T> returnValue = null;
        if (CollectionUtil.isNotEmpty(srcObjects)) {
            returnValue = new ArrayList();
            Iterator var3 = srcObjects.iterator();

            while(var3.hasNext()) {
                Tc srcObject = var3.next();
                returnValue.add(copyProperties(srcObject, targetObject));
            }
        }

        return returnValue;
    }

    public static <T, Tc> List<T> copyPropertiesListNotNull(List<Tc> srcObjects, Class<T> targetObject) {
        List<T> returnValue = new ArrayList();
        if (CollectionUtil.isNotEmpty(srcObjects)) {
            Iterator var3 = srcObjects.iterator();

            while(var3.hasNext()) {
                Tc srcObject = var3.next();
                returnValue.add(copyProperties(srcObject, targetObject));
            }
        }

        return returnValue;
    }

    public static boolean checkObjFieldIsNotNull(Object obj) {
        try {
            Field[] var1 = obj.getClass().getDeclaredFields();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Field f = var1[var3];
                f.setAccessible(true);
                if (f.get(obj) != null && !"serialVersionUID".equals(f.getName())) {
                    return true;
                }
            }
        } catch (IllegalAccessException var5) {
        }

        return false;
    }
}