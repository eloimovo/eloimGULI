import request from '@/utils/request'

export default  {
    getList(){
    return request({
    url: '/eduService/subject/getAll',
    method: 'get',
     })
    },
}
