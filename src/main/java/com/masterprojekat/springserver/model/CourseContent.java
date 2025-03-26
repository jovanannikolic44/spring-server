package com.masterprojekat.springserver.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CourseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;
    private String sectionTitle;

    @ElementCollection
    @CollectionTable(name = "course_content_data", joinColumns = @JoinColumn(name = "content_id"))
    @Column(name = "section_data")
    private List<String> sectionData;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public List<String> getSectionData() {
        return sectionData;
    }

    public void setSectionData(List<String> sectionData) {
        this.sectionData = sectionData;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
