package com.eloim.staservice.service;

import com.eloim.staservice.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-06-02
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);

}
