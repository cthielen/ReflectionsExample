package ReflectionsExample;

import ReflectionsExample.entities.Course;
import ReflectionsExample.entities.FundItem;
import ReflectionsExample.services.EntityLoggerService;

public class ReflectionsExample {
    public static void main(String [] args) {
        // We have a FundItem
        FundItem item = new FundItem();
        item.setDescription("Initial allocation");
        item.setAmount(100.5f);

        // Log it
        EntityLoggerService.log(item);

        // We have a Course
        Course course = new Course();
        course.setTitle("Intro to Java Reflections");

        // Log it
        EntityLoggerService.log(course);
    }
}
