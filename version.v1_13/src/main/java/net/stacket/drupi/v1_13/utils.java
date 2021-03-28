package net.stacket.drupi.v1_13;

import java.lang.reflect.Field;

public class utils {

    public static Object getPrivateField(Object object, String field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField =  clazz.getSuperclass().getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        return result;
    }
}
