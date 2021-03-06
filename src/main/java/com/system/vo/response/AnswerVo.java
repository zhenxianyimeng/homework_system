package com.system.vo.response;

import java.util.List;

/**
 * @author zjb
 * @date 2018/7/16.
 */
public class AnswerVo {
    private Long id;

    private Long questionId;

    private String questionTitle;

//    private String url;

    private List<String> urlList;

    private List<String> checkUrlList;

    private Long studentId;

    private String studentName;

    private Double score;

    private String assessment;

    private Integer maxScore;

    private Integer status;

    public List<String> getCheckUrlList() {
        return checkUrlList;
    }

    public void setCheckUrlList(List<String> checkUrlList) {
        this.checkUrlList = checkUrlList;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "AnswerVo{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", urlList=" + urlList +
                ", checkUrlList=" + checkUrlList +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", score=" + score +
                ", assessment='" + assessment + '\'' +
                ", maxScore=" + maxScore +
                ", status=" + status +
                '}';
    }
}
