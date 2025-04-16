package com.masterprojekat.springserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String username;

    private String name;
    private String surname;

    @Column(nullable = false)
    private String password;
    private String date;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String type;
    private String education;
    private String expertise;
    @Column(nullable = false)
    private String accountStatus;
    private String resetToken;
    @Column(nullable = false)
    private boolean firstLogIn;
    private String profilePicture;

    @OneToOne(mappedBy = "user")
    private Preferences preferences;

    @ManyToMany
    @JoinTable(
            name = "user_purchased_courses",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> purchasedCourses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_cart_courses",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> cartCourses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public boolean isFirstLogIn() {
        return firstLogIn;
    }

    public void setFirstLogIn(boolean firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public List<Course> getPurchasedCourses() {
        return purchasedCourses;
    }

    public void setPurchasedCourses(List<Course> purchasedCourses) {
        this.purchasedCourses = purchasedCourses;
    }

    public void addPurchasedCourse(Course course) {
        if (!purchasedCourses.contains(course)) {
            this.purchasedCourses.add(course);
        }
    }

    public List<Course> getCartCourses() {
        return cartCourses;
    }

    public void setCartCourses(List<Course> cartCourses) {
        this.cartCourses = cartCourses;
    }

    public void addCartCourse(Course course) {
        if (!cartCourses.contains(course)) {
            this.cartCourses.add(course);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                ", education='" + education + '\'' +
                ", expertise='" + expertise + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", firstLogIn=" + firstLogIn +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
