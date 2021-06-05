package com.eloim.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-30
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentPage(Page<EduComment> commentPage,String courseId);

}
