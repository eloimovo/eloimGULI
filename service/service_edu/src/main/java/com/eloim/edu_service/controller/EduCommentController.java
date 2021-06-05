package com.eloim.edu_service.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.commonutils.JwtUtils;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.client.UcenterClient;
import com.eloim.edu_service.entity.EduComment;
import com.eloim.edu_service.service.EduCommentService;
import com.eloim.servicebase.entity.GuLiException;
import com.eloim.servicebase.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/eduService/comment")

public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    @Autowired
    private UcenterClient ucenterClient;

    @GetMapping("/getCommentPage/{page}/{limit}/{courseId}")
    public Result getCommentPage(@PathVariable long page,@PathVariable long limit,@PathVariable String courseId){
        Page<EduComment> commentPage = new Page<>(page,limit);
        Map<String,Object> map =eduCommentService.getCommentPage(commentPage,courseId);
        return Result.ok().data(map);
    }

    @PostMapping("/addComment")
    public Result addComment(@RequestBody EduComment eduComment, HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if (id.isEmpty()){
            throw new GuLiException(20001,"请先登录");
        }
        eduComment.setMemberId(id);
        UserVo user = ucenterClient.getUserById(id);
        eduComment.setAvatar(user.getAvatar());
        eduComment.setNickname(user.getNickname());
        boolean flag = eduCommentService.save(eduComment);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    public Result deleteComment(@PathVariable String id){
        boolean flag = eduCommentService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }

    }


}

