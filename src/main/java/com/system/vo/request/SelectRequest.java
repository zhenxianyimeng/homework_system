package com.system.vo.request;

import java.io.Serializable;

/**
 * @author zjb
 * @date 2018/7/15.
 */
public class SelectRequest implements Serializable {
    private static final long serialVersionUID = -2225420864532997925L;
    private Integer typeValue;

    private Integer courseValue;

    private Integer gradeValue;

    private Integer chapterValue;

    private Integer sectionValue;

    public Integer getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Integer typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getCourseValue() {
        return courseValue;
    }

    public void setCourseValue(Integer courseValue) {
        this.courseValue = courseValue;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Integer getChapterValue() {
        return chapterValue;
    }

    public void setChapterValue(Integer chapterValue) {
        this.chapterValue = chapterValue;
    }

    public Integer getSectionValue() {
        return sectionValue;
    }

    public void setSectionValue(Integer sectionValue) {
        this.sectionValue = sectionValue;
    }

    @Override
    public String toString() {
        return "SelectRequest{" +
                "typeValue=" + typeValue +
                ", courseValue=" + courseValue +
                ", gradeValue=" + gradeValue +
                ", chapterValue=" + chapterValue +
                ", sectionValue=" + sectionValue +
                '}';
    }
}
