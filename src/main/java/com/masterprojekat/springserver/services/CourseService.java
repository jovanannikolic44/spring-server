package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseStatus;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    private String uploadsPath = "C:\\Users\\Jovana\\Desktop\\Master\\Projects\\spring-server\\uploads\\";

    public List<Course> getAllCourses() {
        List<Course> users = new ArrayList<>();
        Streamable.of(courseRepository.findAll()).forEach(users::add);
        return users;
    }

    public List<Course> search(String searchText) {
        return courseRepository.searchCourseByNameAndInstrumentAndProfessorNameAndSurname(searchText);
    }

    public List<Course> getByPreferences(Set<String> preferences) {
        return courseRepository.findByInstrumentIn(preferences);
    }

    public List<Course> getFiveBestRatedCourses() {
        Pageable pageable = PageRequest.of(0,5);
        return courseRepository.findFiveCoursesByOrderByRatingDesc(pageable);
    }

    public List<Course> getFiveCheapestCourses() {
        Pageable pageable = PageRequest.of(0,5);
        return courseRepository.findFiveCoursesByOrderByPriceDesc(pageable);
    }

    public void saveRating(int courseId, float rating) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        float totalSumRatings = course.getTotalSumRatings();
        float numberOfRatings = course.getNumberOfRatings();
        totalSumRatings += rating;
        numberOfRatings++;
        float newRating = totalSumRatings / numberOfRatings;
        course.setRating(newRating);
        course.setTotalSumRatings(totalSumRatings);
        course.setNumberOfRatings(numberOfRatings);
        courseRepository.save(course);
    }

    public float getRating(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return course.getRating();
    }

    public List<Course> getAllProfessorsCourses(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow();
        return courseRepository.findByProfessorAndStatus(professor, CourseStatus.PRIHVACEN);
    }

    public void saveCourseWithImage(String name, float price, String professorUsername, String level,
                                    String instrument, String description, String content,
                                    int numberOfClasses, String imagePath) {

        User professor = userRepository.findById(professorUsername).orElseThrow();
        Course course = new Course();
        course.setName(name);
        course.setPrice(price);
        course.setProfessor(professor);
        course.setLevel(level);
        course.setInstrument(instrument);
        course.setDescription(description);
        course.setContent(content);
        course.setNumberOfClasses(numberOfClasses);
        course.setCourseImage(imagePath);
        course.setStatus(CourseStatus.ZAHTEV_POSLAT);
        courseRepository.save(course);
    }

    public Course updateCourse(Course newCourse) {
        Course course = courseRepository.findById(newCourse.getCourseId()).orElseThrow();
        course.setName(newCourse.getName());
        course.setPrice(newCourse.getPrice());
        course.setNumberOfClasses(newCourse.getNumberOfClasses());
        course.setDescription(newCourse.getDescription());
        course.setContent(newCourse.getContent());
        course.setLevel(newCourse.getLevel());
        courseRepository.save(course);
        return course;
    }
}
