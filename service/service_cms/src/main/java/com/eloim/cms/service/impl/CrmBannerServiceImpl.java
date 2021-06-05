package com.eloim.cms.service.impl;

import com.eloim.cms.entity.CrmBanner;

import com.eloim.cms.mapper.CrmBannerMapper;
import com.eloim.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-23
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
