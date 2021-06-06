import request from '@/utils/request'

export default{
    //前台多条件分页查询
    getConditionPage(page,limit,searchObj){
        return request({
            url: `/eduService/courseFront/getCourseFrontList/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    },
    //查询所有分类（一级分类、二级分类）的方法
    getAllSubject(){
        return request({
            url: `/eduService/subject/getAll`,
            method: 'get'
        })
    },
    getCourseFrontInfo(courseId){
        return request({
            url: `/eduService/courseFront/getCourseFrontInfo/${courseId}`,
            method: 'get'
        })
    }
}