package com.eloim.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduComment;
import com.eloim.edu_service.mapper.EduCommentMapper;
import com.eloim.edu_service.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-30
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> getCommentPage(Page<EduComment> commentPage,String courseId) {
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(commentPage, wrapper);

        long total = commentPage.getTotal();//总记录数
        List<EduComment> commentList = commentPage.getRecords();//数据集合
        long size = commentPage.getSize();//每页记录数
        long current = commentPage.getCurrent();//当前页
        long pages = commentPage.getPages();//总页数
        boolean hasPrevious = commentPage.hasPrevious();//是否有上一页
        boolean hasNext = commentPage.hasNext();//是否有下一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", commentList);
        map.put("size", size);
        map.put("current", current);
        map.put("pages", pages);
        map.put("hasPrevious", hasPrevious);
        map.put("hasNext", hasNext);

        return map;
    }
}
