package com.eloim.order.client;

import com.eloim.servicebase.entity.UserVo;
import org.springframework.stereotype.Component;


@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UserVo getUserById(String id) {
        return new UserVo();
    }
}
