package com.system.service;

import com.system.repository.*;
import com.system.vo.request.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjb
 * @date 2018/7/14.
 */
@Service
public class SelectService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    SectionRepository sectionRepository;

    public SelectVo getSelectOption(){
        SelectVo selectVo = new SelectVo();

        return selectVo;
    }
}
