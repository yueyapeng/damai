package com.damai.wine.service;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class DefaultSpringJUnitRunner extends SpringJUnit4ClassRunner {
    public DefaultSpringJUnitRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        System.setProperty("spring.boot.testMainclass", findSuperClass(clazz).getName());
    }

    public static Class<?> findSuperClass(Class<?> clazz) {
        Class<?> superCls = clazz.getSuperclass();
        return superCls == Object.class ? clazz : findSuperClass(superCls);
    }
}