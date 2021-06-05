package com.eloim.order.service;

import com.eloim.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-31
 */
public interface OrderService extends IService<Order> {

    String creatOrder(String courseId, String userId);

}
