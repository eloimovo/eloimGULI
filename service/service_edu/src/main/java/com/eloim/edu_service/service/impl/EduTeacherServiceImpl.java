package com.eloim.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduTeacher;
import com.eloim.edu_service.entity.vo.TeacherQuery;
import com.eloim.edu_service.mapper.EduTeacherMapper;
import com.eloim.edu_service.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-15
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //取出值，判断他们是否有值
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为空，如果不为空，拼接条件
        //判断是否有传入教师名
        if (name!=null){
            //构建条件
            wrapper.like("name",name);//参数1：数据库字段名； 参数2：模糊查询的值
        }
        //判断是否传入教师头衔
        if (level!=null){
            //构造条件
            wrapper.eq("level",level);
        }
        if (begin!=null){
            //构造条件
            wrapper.ge("gmt_create",begin);//ge：大于等于
        }
        if (end!=null){
            //构造条件
            wrapper.le("gmt_modified",end);//le:小于等于
        }
        wrapper.orderByDesc("gmt_create");

        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //把分页数据封装到pageTeacher对象中
        baseMapper.selectPage(teacherPage,wrapper);
        //总记录数
        long total = teacherPage.getTotal();
        //当前页
        long current = teacherPage.getCurrent();
        //每页记录数
        long size = teacherPage.getSize();
        //查询到的对象
        List<EduTeacher> teacherList = teacherPage.getRecords();
        //总页数
        long pages = teacherPage.getPages();
        //是否有上一页
        boolean hasPrevious = teacherPage.hasPrevious();
        //是否有下一页
        boolean hasNext = teacherPage.hasNext();

        //将数据封装到map中返回
        map.put("total",total);
        map.put("current",current);
        map.put("size",size);
        map.put("list",teacherList);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);
        map.put("pages",pages);
        return map;

    }
}
