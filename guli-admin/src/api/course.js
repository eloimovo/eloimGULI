import request from '@/utils/request' //引入已经封装好的axios 和 拦截器

export default{
    //添加课程信息功能
    addCourse(courseInfo){
        return request({
            url:"/eduService/course/addCourse",
            method: 'post',
            data: courseInfo,
        })
    },
    updateCourse(courseInfo){
        return request({
            url:"/eduService/course/updateCourseInfo",
            method: 'post',
            data: courseInfo,
        })
    },
    getCourse(courseId){
        return request({
        url: `/eduService/course/getCourseInfo/${courseId}`,
        method: 'get',
         })
    },
    getCoursePublishInfo(courseId){
        return request({
        url: `/eduService/course/getCoursePublishInfo/${courseId}`,
        method: 'get',
         })
    },
    publishCourse(courseId){
        return request({
        url: `/eduService/course/publishCourse/${courseId}`,
        method: 'get',
         })
    },
    
    getCoureseList(current,limit,courseQuery){
        return request({
            url: `/eduService/course/pageCourseCondition/${current}/${limit}`,
            method:'post',
            data:courseQuery,
        })
    },
    removeById(id){
        return request({
            url: `/eduService/course/deleteCourseById/${id}`,
            method: 'delete',
        })
    },
}