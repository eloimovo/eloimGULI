package com.eloim.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.client.VodClient;
import com.eloim.edu_service.entity.EduVideo;
import com.eloim.edu_service.mapper.EduVideoMapper;
import com.eloim.edu_service.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eloim.servicebase.entity.GuLiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);
        List<String> list=new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            if(!eduVideo.getVideoSourceId().isEmpty()){
                list.add(eduVideo.getVideoSourceId());
            }
        }
        //根据多个视频id，删除多个视频
        if (list.size()>0){
            Result result = vodClient.removeBatch(list);
            if(result.getCode()==20001){
                throw new GuLiException(20001,"Hystrix:调用服务失败。。。");
            }
        }

        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", id);
        baseMapper.delete(queryWrapper2);
    }
}
