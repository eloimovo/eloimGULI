package com.eloim.edu_service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eloim.edu_service.entity.EduSubject;
import com.eloim.edu_service.entity.excel.SubjectData;
import com.eloim.edu_service.service.EduSubjectService;
import com.eloim.servicebase.entity.GuLiException;

public class SubjectListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener態交给spring进行ioc管理，需要自己手动new，不能注入其他对象
    //不能实现数据库操作

    public EduSubjectService eduSubjectService;

    //有参，传递subjectService用于操作数据库
    public SubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //无参
    public SubjectListener() {
    }

    //读取excel内容，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //表示excel中没有数据，就不需要读取了
        if (subjectData==null){
            throw new GuLiException(20001,"文件内容位空");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断是否有一级分类是否重复
        EduSubject OneSubject = this.existOneSubject( subjectData.getOneSubjectName());
        if (OneSubject == null){ //没有相同的一级分类，进行添加
            OneSubject = new EduSubject();
            OneSubject.setParentId("0"); //设置一级分类id值，0代表为一级分类
            OneSubject.setTitle(subjectData.getOneSubjectName());//设置一级分类名
            eduSubjectService.save(OneSubject);//给数据库添加一级分类
        }

        //获取一级分类的id值
        String parent_id = OneSubject.getId();
        //判断是否有耳机分类是否重复
        EduSubject TwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), parent_id);
        if (TwoSubject==null){//没有相同的二级分类，进行添加
            TwoSubject = new EduSubject();
            TwoSubject.setParentId(parent_id); //设置二级分类id值
            TwoSubject.setTitle(subjectData.getTwoSubjectName());//设置二级分类名
            eduSubjectService.save(TwoSubject);//给数据库添加二级分类
        }

    }


    //判断一级分类不能重复添加
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(String name,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id",parentId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}