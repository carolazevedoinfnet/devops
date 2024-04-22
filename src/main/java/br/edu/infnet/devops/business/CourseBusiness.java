package br.edu.infnet.devops.business;

import br.edu.infnet.devops.service.CourseService;

import java.util.ArrayList;
import java.util.List;


// CourseBusiness = SUT - System (Method) Under Test
public class CourseBusiness {

    // CourseService is a Dependency
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retriveCoursesRelatedToDevOps(String student) {

        var filteredCourses = new ArrayList<String>();
        if("Foo Bar".equals(student)) return filteredCourses;
        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (course.contains("DevOps")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToDevOps(String student) {

        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (!course.contains("DevOps")) {
                service.deleteCourse(course);
            }
        }
    }

}