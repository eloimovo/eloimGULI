package com.eloim.cms.controller;

import com.eloim.cms.entity.CrmBanner;
import com.eloim.cms.service.CrmBannerService;
import com.eloim.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/bannerFront")

public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有幻灯片
    @GetMapping("getAll")
    @Cacheable(value = "banner", key = "'selectIndexList'")
    public Result getAll(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return Result.ok().data("list",list);
    }
}
