package com.eloim.order.controller;


import com.eloim.commonutils.Result;
import com.eloim.order.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/order/pay-log")

public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    //根据订单号，生成微信支付二维码的接口
    @GetMapping("/createWxQRcode/{orderNo}")
    public Result createWxQRcode(@PathVariable String orderNo){
        //返回信息，包含二维码地址、其他信息
        Map<String, Object> map = payLogService.createWxQrcode(orderNo);
        return Result.ok().data(map);
    }

    //根据订单号查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){

        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if (map==null){
            return Result.error().message("支付出错了");
        }
        //如果返回的map不为空，通过map获取订单的状态
        if (map.get("trade_state").equals("SUCCESS")){ //支付成功
            //添加记录到支付表里，并更新订单表的状态
            payLogService.updateOrderStatus(map);
            return Result.ok().message("支付成功");
        }

        return Result.ok().message("支付中").code(25000);
    }
}

