import request from '@/utils/request'

export default{
    getPageList(page, limit){
        return request({
            url:`/eduService/teacherFront/getTeacherFrontPageList/${page}/${limit}`,
            method: 'get'
        })
    },
     //根据ID查询讲师本身信息+课程信息
     getTeacherInfoByid(id){
        return request({
            url: `/eduService/teacherFront/getTeacherInfo/${id}`,
            method: `get`
        })
    },
}