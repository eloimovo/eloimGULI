package com.eloim.ucenter.service.impl;



import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eloim.commonutils.JwtUtils;
import com.eloim.servicebase.entity.GuLiException;
import com.eloim.ucenter.entity.Member;
import com.eloim.ucenter.entity.vo.LoginVo;
import com.eloim.ucenter.entity.vo.RegisterVo;
import com.eloim.ucenter.mapper.MemberMapper;
import com.eloim.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-05-26
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


//    @Autowired
//    RedisTemplate redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        //获取手机号和密码
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        //判断输入的手机号和密码是否为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)){
            throw new GuLiException(20001,"手机号或密码为空");
        }

        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        Member member = baseMapper.selectOne(wrapper);
        if (member == null){
            throw new GuLiException(20001,"手机号不存在");
        }

        //判断密码是否正确
        // MD5加密是不可逆性的，不能解密，只能加密
        //将获取到的密码经过MD5加密与数据库比较
        if (!Md5Utils.getMD5(password.getBytes()).equals(member.getPassword())){
            throw new GuLiException(20001,"密码不正确");
        }

        //判断用户是否禁用
        if (member.getIsDisabled()){
            throw new GuLiException(20001,"用户被禁用");
        }

        //生成jwtToken
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取前端传来的数据
        String nickname = registerVo.getNickname(); //昵称
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String password = registerVo.getPassword(); //密码

        //非空判断
        if (StringUtils.isEmpty(nickname)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(mobile)
                ||StringUtils.isEmpty(password)){
            throw new GuLiException(20001,"传来的数据有空值，注册失败");
        }

        //判断验证码
        //获取redis验证码，根据手机号获取
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if (!code.equals(redisCode)){
//            throw new GuLiException(20001,"注册失败");
//        }
        //阿里云没审核通过，尴尬，就先直接定死一个验证码好了
        if (!code.equals("6666")){
            throw new GuLiException(20001,"验证码不正确，注册失败");
        }

        //手机号不能重复
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>=1){
            throw new GuLiException(20001,"手机号重复，注册失败");
        }

        //数据添加到数据库中
        Member member = new Member();
        member.setPassword(Md5Utils.getMD5(password.getBytes()));//密码加密
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://edu-eloim.oss-cn-hangzhou.aliyuncs.com/aaaaaaaaaa.jpg");
        baseMapper.insert(member);
    }

    @Override
    public Member getMemberByOpenId(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer getCountRegister(String day) {

        return baseMapper.getCountRegister(day);
    }
}
