package ReflectionsExample.loggers;

public interface EntityLogger {
    // Log the message
    String serialize(Object o);

    // Check if class 'c' is supported
    boolean supports(Object o);
}
