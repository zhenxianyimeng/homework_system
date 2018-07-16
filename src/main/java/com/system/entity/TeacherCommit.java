package com.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author zjb
 * @date 2018/7/14.
 */
@Entity
public class TeacherCommit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer total;

    private String type;

    private String grade;

    private String course;

    private String chapter;

    private String section;

    private String questionNum;

    private String questionTitle;

    private Integer maxScore;

    private String url;

    public TeacherCommit() {
    }

    public TeacherCommit(Integer total, String type, String grade, String course, String chapter, String section, String questionTitle, Integer maxScore, String url, String questionNum) {
        this.total = total;
        this.type = type;
        this.grade = grade;
        this.course = course;
        this.chapter = chapter;
        this.section = section;
        this.questionTitle = questionTitle;
        this.maxScore = maxScore;
        this.url = url;
        this.questionNum = questionNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    @Override
    public String toString() {
        return "TeacherCommit{" +
                "id=" + id +
                ", total=" + total +
                ", type='" + type + '\'' +
                ", grade='" + grade + '\'' +
                ", course='" + course + '\'' +
                ", chapter='" + chapter + '\'' +
                ", section='" + section + '\'' +
                ", questionNum='" + questionNum + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", maxScore=" + maxScore +
                ", url='" + url + '\'' +
                '}';
    }
}
