package com.system.vo.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author zjb
 * @date 2018/7/16.
 */
public class QuestionVo implements Serializable {

    private static final long serialVersionUID = -1216696540385040315L;
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

    private List<String> urlList;

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

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
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

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public String toString() {
        return "QuestionVo{" +
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
                ", urlList=" + urlList +
                '}';
    }
}
