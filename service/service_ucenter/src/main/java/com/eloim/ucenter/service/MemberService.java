package com.eloim.ucenter.service;

import com.eloim.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eloim.ucenter.entity.vo.LoginVo;
import com.eloim.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-26
 */
public interface MemberService extends IService<Member> {




    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    Member getMemberByOpenId(String openid);

     Integer getCountRegister(String day) ;

}
