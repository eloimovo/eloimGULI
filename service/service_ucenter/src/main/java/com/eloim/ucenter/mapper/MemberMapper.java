package com.eloim.ucenter.mapper;



import com.eloim.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author eloim
 * @since 2021-05-26
 */

public interface MemberMapper extends BaseMapper<Member> {

    Integer getCountRegister(String day);

}
