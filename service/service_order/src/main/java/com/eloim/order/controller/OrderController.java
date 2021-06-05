package com.eloim.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eloim.commonutils.JwtUtils;
import com.eloim.commonutils.Result;
import com.eloim.order.entity.Order;
import com.eloim.order.service.OrderService;
import com.eloim.servicebase.entity.GuLiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/order")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/creatOrder/{courseId}")
    public Result creatOrder(@PathVariable String courseId, HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        if (userId.isEmpty()){
            throw new GuLiException(20001,"请先登录");
        }
        String orderNo=orderService.creatOrder(courseId,userId);
        return Result.ok().data("orderNo",orderNo);
    }
    //根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public Result getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return Result.ok().data("item",order);
    }

    //根据【用户id、课程id】查询订单表中的状态
    @GetMapping("/isBuyCourse/{memberId}/{courseId}")
    public Boolean isBuyCourse(@PathVariable String memberId,@PathVariable String courseId)	  {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 【1】代表已支付
        int result = orderService.count(wrapper);

        if (result>0){//已支付
            return true;
        }else {
            return false;
        }
    }

}

