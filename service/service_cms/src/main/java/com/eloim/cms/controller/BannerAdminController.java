package com.eloim.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.cms.entity.CrmBanner;
import com.eloim.cms.service.CrmBannerService;
import com.eloim.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/cms/bannerAdmin")

public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //条件分页查询banner
    @PostMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable Long page, @PathVariable Long limit){
        Page<CrmBanner> bannerPage = new Page<>(page,limit);

        crmBannerService.page(bannerPage,null);
        //获取数据
        List<CrmBanner> list = bannerPage.getRecords();
        //获取总记录数
        long total = bannerPage.getTotal();

        return Result.ok().data("rows",list).data("total",total);
    }

    //添加banner
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.save(crmBanner);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //修改banner
    @PostMapping("/updateBanner")
    public Result updateBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.updateById(crmBanner);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //根据id删除banner
    @DeleteMapping("/deleteBannerById/{id}")
    public Result deleteBannerById(@PathVariable String id){
        boolean flag = crmBannerService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //根据id查询banner
    @GetMapping("/getBannerById/{id}")
    public Result getBannerById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return Result.ok().data("item",crmBanner);
    }


}

