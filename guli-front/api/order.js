import request from '@/utils/request'

export default{
    
    createOrder(courseId){
        return request({
            url: `/order/creatOrder/${courseId}`,
            method: 'post'
            })
    },
   
    getOrderInfo(id){
        return request({
            url: `/order/getOrderInfoById/${id}`,
            method: 'get',
            
        })
    },
    //根据订单号，生产二维码
    createWxQRcode(orderNo){
        return request({
            url: `/order/pay-log//createWxQRcode/${orderNo}`,
            method: 'get'
        })
    },
    //根据订单号，查询订单支付状态
    getPayStatus(orderNo){
        return request({
            url: `/order/pay-log//queryPayStatus/${orderNo}`,
            method: 'get'
        })
    },

}