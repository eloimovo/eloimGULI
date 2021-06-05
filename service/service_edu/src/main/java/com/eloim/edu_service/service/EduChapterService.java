package com.eloim.edu_service.service;

import com.eloim.edu_service.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eloim.edu_service.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterAndVideo(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String id);

}
