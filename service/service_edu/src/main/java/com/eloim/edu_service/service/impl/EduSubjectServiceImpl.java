package com.eloim.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eloim.edu_service.entity.EduSubject;
import com.eloim.edu_service.entity.excel.SubjectData;
import com.eloim.edu_service.entity.subject.OneSub;
import com.eloim.edu_service.entity.subject.TwoSub;
import com.eloim.edu_service.listener.SubjectListener;
import com.eloim.edu_service.mapper.EduSubjectMapper;
import com.eloim.edu_service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class,new SubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSub> getAllSubjects() {
        //查询一级分类
        QueryWrapper<EduSubject> Wrapper1 = new QueryWrapper<>();
        Wrapper1.eq("parent_id","0");
        List<EduSubject> oneList = this.list(Wrapper1);
        //查询二级分类
        QueryWrapper<EduSubject> Wrapper2 = new QueryWrapper<>();
        Wrapper1.ne("parent_id","0");
        List<EduSubject> twoList = this.list(Wrapper2);
        //封装
        List<OneSub> list=new ArrayList<>();
        for (int i = 0; i < oneList.size(); i++) {
            EduSubject eduSubject1 = oneList.get(i);
            OneSub oneSub = new OneSub();
            BeanUtils.copyProperties(eduSubject1,oneSub);
            List<TwoSub> children=new ArrayList<>();
            for (int j = 0; j < twoList.size(); j++) {
                EduSubject eduSubject2 = twoList.get(j);
                if(eduSubject2.getParentId().equals( eduSubject1.getId())){
                    TwoSub twoSub = new TwoSub();
                    BeanUtils.copyProperties(eduSubject2,twoSub);
                    children.add(twoSub);
                }
            }
            oneSub.setChildren(children);
            list.add(oneSub);
        }

        return list;
    }
}
