package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseProgress;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.model.UserAccountStatus;
import com.masterprojekat.springserver.repository.CourseProgressRepository;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseProgressRepository courseProgressRepository;


    public User save(User user) {
        user.setAccountStatus(UserAccountStatus.NIJE_AKTIVAN);
        return userRepository.save(user);
    }

    public User updateInfo(User newUser) {
        User user = userRepository.findById(newUser.getUsername()).orElseThrow(() -> new RuntimeException("Ne postoji korisnik sa korisnickim imenom " + newUser.getUsername() + "!"));
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setDate(newUser.getDate());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());
        if("Profesor".equals(newUser.getType())) {
            user.setEducation(newUser.getEducation());
            user.setExpertise(newUser.getExpertise());
        }
        userRepository.save(user);
        return user;
    }

    public User updatePassword(String username, String newPassword) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("Ne postoji korisnik sa korisnickim imenom " + username + "!"));
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Streamable.of(userRepository.findAll()).forEach(users::add);
        return users;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User getByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public boolean checkIfEmailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public boolean checkIfPhoneNumberExists(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.isPresent();
    }

    public void updateProfilePicture(String username, String imagePath) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("Ne postoji korisnik sa korisnickim imenom " + username + "!"));
        user.setProfilePicture(imagePath);
        userRepository.save(user);
    }

    public void purchaseCourse(String username, int courseId) {
        User user = userRepository.findById(username).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (user.getPurchasedCourses().contains(course)) {
            throw new IllegalStateException("Kurs je vec kupljen!");
        }

        user.addPurchasedCourse(course);
        userRepository.save(user);

        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setUser(user);
        courseProgress.setCourse(course);
        courseProgress.setProgress(0);

        courseProgressRepository.save(courseProgress);
    }

    public List<Course> getPurchasedCourses(String username) {
        User user = userRepository.findById(username).orElseThrow();
        return user.getPurchasedCourses();
    }

    public void addCourseToCart(String username, int courseId) {
        User user = userRepository.findById(username).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        user.addCartCourse(course);
        userRepository.save(user);
    }

    public List<Course> getCoursesFromCart(String username) {
        User user = userRepository.findById(username).orElseThrow();
        return user.getCartCourses();
    }

    public void removeCoursesFromCart(String username, List<Integer> idsToBeRemoved) {
        User user = userRepository.findById(username).orElseThrow();
        List<Course> coursesToRemove = courseRepository.findAllById(idsToBeRemoved);
        user.getCartCourses().removeAll(coursesToRemove);
        userRepository.save(user);
    }

    public boolean isCoursePurchased(String username, int courseId) {
        User user = userRepository.findById(username).orElseThrow();
        for(Course course : user.getPurchasedCourses()) {
            if(course.getCourseId() == courseId)
                return true;
        }
        return false;
    }
}
