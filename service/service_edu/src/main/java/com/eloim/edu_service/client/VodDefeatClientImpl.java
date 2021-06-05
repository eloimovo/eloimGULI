package com.eloim.edu_service.client;

import com.eloim.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VodDefeatClientImpl implements VodClient {
    @Override
    public Result removeAliyunVideoById(String id) {
        return Result.error().message("调用服务失败");
    }

    @Override
    public Result removeBatch(List<String> videoIdList) {
        return Result.error().message("调用服务失败");
    }
}
