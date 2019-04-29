package ReflectionsExample.services;

import ReflectionsExample.loggers.EntityLogger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class EntityLoggerService {
    // Log the object using a compatible logger
    public static void log(Object object) {
        EntityLogger logger = scanForCompatibleLogger(object);

        if(logger == null) {
            // Unable to find a proper logger ...
            return;
        }

        String serializedObject = logger.serialize(object);

        System.out.println(serializedObject);
    }

    /**
     * Find compatible loggers for the given object by scanning for classes annotated
     * with @EntityLogger, then calling the 'supports' method on them passing in object.
     * If 'supports' returns true, we've found our logger.
     *
     * @param object - Any object
     * @return - A compatible class implementing EventLogger, else null.
     */
    private static EntityLogger scanForCompatibleLogger(Object object) {
        Reflections reflections = new Reflections("ReflectionsExample.loggers");

        // Get all classes annotated with @EntityLogger
        Set<Class<?>> singletons =
            reflections.getTypesAnnotatedWith(ReflectionsExample.annotations.Logger.class);

        // Loop over classes, seeing which one supports the given object
        for(Class<?> singleton : singletons) {
            try {
                // Find a method called 'supports' ...
                Method supportsMethod = singleton.getMethod("supports", Object.class);

                // Invoke it ...
                Object result = supportsMethod.invoke(singleton.newInstance(), object);

                // If it returns true ...
                if((boolean)result == true) {
                    return (EntityLogger)singleton.newInstance();
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                // Not the right one apparently ...
            }
        }

        return null;
    }
}
