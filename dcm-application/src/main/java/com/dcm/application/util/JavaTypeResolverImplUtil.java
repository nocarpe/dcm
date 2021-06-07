package com.dcm.application.util;


import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;


public class JavaTypeResolverImplUtil extends JavaTypeResolverDefaultImpl {

    public JavaTypeResolverImplUtil() {
        super();
        super.typeMap.put(-6,
            new JdbcTypeInformation("TINYINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
