package com.system.vo.request;

import java.io.Serializable;

/**
 * @author zjb
 * @date 2018/7/15.
 */
public class SelectRequest implements Serializable {
    private static final long serialVersionUID = -2225420864532997925L;
    private String typeValue;

    private String courseValue;

    private String gradeValue;

    private String chapterValue;

    private String sectionValue;

    private Integer maxScore;

    private String questionNum;

    private String questionTitle;

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getCourseValue() {
        return courseValue;
    }

    public void setCourseValue(String courseValue) {
        this.courseValue = courseValue;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getChapterValue() {
        return chapterValue;
    }

    public void setChapterValue(String chapterValue) {
        this.chapterValue = chapterValue;
    }

    public String getSectionValue() {
        return sectionValue;
    }

    public void setSectionValue(String sectionValue) {
        this.sectionValue = sectionValue;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
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

    @Override
    public String toString() {
        return "SelectRequest{" +
                "typeValue=" + typeValue +
                ", courseValue=" + courseValue +
                ", gradeValue=" + gradeValue +
                ", chapterValue=" + chapterValue +
                ", sectionValue=" + sectionValue +
                ", maxScore=" + maxScore +
                ", questionNum='" + questionNum + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                '}';
    }
}
