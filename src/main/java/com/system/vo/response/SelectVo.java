package com.system.vo.response;

import com.system.entity.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author zjb
 * @date 2018/7/14.
 */
public class SelectVo implements Serializable {
    private static final long serialVersionUID = -5042695279929471314L;
    private List<Type> typeList;

    private List<Grade> gradeList;

    private List<Course> courseList;

    private List<Chapter> chapterList;

    private List<Section> sectionList;

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public String toString() {
        return "SelectVo{" +
                "typeList=" + typeList +
                ", gradeList=" + gradeList +
                ", courseList=" + courseList +
                ", chapterList=" + chapterList +
                ", sectionList=" + sectionList +
                '}';
    }
}
