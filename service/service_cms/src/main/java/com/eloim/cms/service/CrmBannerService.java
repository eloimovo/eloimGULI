package com.eloim.cms.service;


import com.eloim.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-23
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();

}
