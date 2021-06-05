package com.eloim.order.service;

import com.eloim.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-31
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, Object> createWxQrcode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);

}
