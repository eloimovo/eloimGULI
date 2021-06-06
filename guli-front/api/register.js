import request from '@/utils/request'

export default{
    //根据手机号码发送短信
    getCode(mobile){
        return request({
            url: `/msm/send/${mobile}`,
            method: 'get'
            })
    },
    //用户注册
    register(registerVo){
        return request({
            url: `/ucenter/member/register`,
            method: 'post',
            data: registerVo
        })
    }

}