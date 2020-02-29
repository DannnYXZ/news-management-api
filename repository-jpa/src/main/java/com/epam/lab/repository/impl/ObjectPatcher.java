package com.epam.lab.repository.impl;

import java.lang.reflect.Field;

public class ObjectPatcher {

    public static void patch(Object target, Object source) throws RuntimeException {
        try {
            for (Field f : source.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                f.set(target, f.get(source) != null ? f.get(source) : f.get(target));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Object patching failure.");
        }
    }
}
