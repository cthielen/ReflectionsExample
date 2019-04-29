package ReflectionsExample.loggers;

import ReflectionsExample.annotations.Logger;
import ReflectionsExample.entities.Course;

@Logger
public class CourseLogger implements EntityLogger {

    @Override
    public String serialize(Object o) {
        Course c = (Course)o;

        return "Course: " + c.getTitle();
    }

    @Override
    public boolean supports(Object o) {
        if(o instanceof Course) {
            return true;
        }
        return false;
    }
}
