package com.eloim.edu_service.service;

import com.eloim.edu_service.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);

}
