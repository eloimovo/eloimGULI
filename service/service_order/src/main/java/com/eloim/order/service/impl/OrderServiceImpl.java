package com.eloim.order.service.impl;

import com.eloim.order.client.CourseClient;
import com.eloim.order.client.UcenterClient;
import com.eloim.order.entity.Order;
import com.eloim.order.mapper.OrderMapper;
import com.eloim.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eloim.order.util.OrderNoUtil;
import com.eloim.servicebase.entity.CourseVo;
import com.eloim.servicebase.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String creatOrder(String courseId, String userId) {
        CourseVo course = courseClient.getCourseVoById(courseId);
        UserVo user = ucenterClient.getUserById(userId);
        Order order = new Order();
        order.setMobile(user.getMobile());
        order.setNickname(user.getNickname());
        order.setMemberId(userId);
        order.setCourseCover(course.getCover());
        order.setCourseId(courseId);
        order.setCourseTitle(course.getTitle());
        order.setTeacherName(course.getTeacherName());
        order.setTotalFee(course.getPrice());
        order.setStatus(0);//支付状态：（ 0：已支付，1：未支付 ）
        order.setPayType(1);//支付类型： 1：微信 ， 2：支付宝
        String no = OrderNoUtil.getOrderNo();
        order.setOrderNo(no); //订单号
        //保存订单
        baseMapper.insert(order);
        return no;
    }
}
