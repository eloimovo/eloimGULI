package com.eloim.edu_service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduCourse;
import com.eloim.edu_service.entity.EduCourseDescription;
import com.eloim.edu_service.entity.EduSubject;
import com.eloim.edu_service.entity.vo.*;
import com.eloim.edu_service.mapper.EduCourseMapper;
import com.eloim.edu_service.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eloim.servicebase.entity.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;




    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result<=0){//表示添加失败
            throw new GuLiException(20001,"添加课程信息失败");
        }
        String id = eduCourse.getId();
        //想课程简介表里面添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);
        return id;
    }

    @Override
    public CourseInfo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(eduCourse,courseInfo);

        //查询简介表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(courseDescription.getDescription());

        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
            throw new GuLiException(20001,"修改课程信息失败");
        }

        //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(courseInfo.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublish(String courseId) {

        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void pageQuery(Page<EduCourse> page, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //取出值，判断他们是否有值
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String status =courseQuery.getStatus();



        //判断条件值是否为空，如果不为空，拼接条件

        if (title!=null){
            //构建条件
            wrapper.like("title",title);//参数1：数据库字段名； 参数2：模糊查询的值
        }

        if (status!=null){
            //构造条件
            wrapper.eq("status",status);
        }
        if (teacherId!=null){
            //构造条件
            wrapper.eq("teacher_id",teacherId);
        }


        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(page, wrapper);
    }

    @Override

    public boolean removeCourse(String id) {
        eduVideoService.removeByCourseId(id);
        eduChapterService.removeByCourseId(id);
        eduCourseDescriptionService.removeById(id);
        boolean b = this.removeById(id);
        return b;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {

        String subjectId = null;
        String subjectParentId = null;
        String gmtCreateSort = null;
        String buyCountSort = null;
        String priceSort = null;

        if (!StringUtils.isEmpty(courseFrontVo)){
            subjectId = courseFrontVo.getSubjectId();
            subjectParentId = courseFrontVo.getSubjectParentId();
            gmtCreateSort = courseFrontVo.getGmtCreateSort();
            buyCountSort = courseFrontVo.getBuyCountSort();
            priceSort = courseFrontVo.getPriceSort();
        }


        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接条件
        if (!StringUtils.isEmpty(subjectParentId)){//一级分类
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)){//二级分类
            wrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(buyCountSort)){//关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)){//价格
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){//最新，创建时间
            wrapper.orderByDesc("gmt_create");
        }


        baseMapper.selectPage(coursePage, wrapper);

        long total = coursePage.getTotal();//总记录数
        List<EduCourse> courseList = coursePage.getRecords();//数据集合
        long size = coursePage.getSize();//每页记录数
        long current = coursePage.getCurrent();//当前页
        long pages = coursePage.getPages();//总页数
        boolean hasPrevious = coursePage.hasPrevious();//是否有上一页
        boolean hasNext = coursePage.hasNext();//是否有下一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",courseList);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
        return baseMapper.getBaseCourseInfo(id);
    }


}

