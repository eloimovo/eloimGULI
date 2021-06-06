import request from '@/utils/request'

export default{

    getTeacherList(current,limit,teacherQuery) {
        return request({
            url: `/eduService/teacher/pageTeacherCondition/${current}/${limit}`,
            method:'post',
            data:teacherQuery,
        })
    },
    removeById(id){
        return request({
            url: `/eduService/teacher/deleteTeacherById/${id}`,
            method: 'delete',
        })
    },
    addTeacher(teacher){
        return request({
            url:`/eduService/teacher/addTeacher`,
            method: 'post',
            data: teacher,
        })
    },
    getTeacherById(id){
        return request({
            url:`/eduService/teacher/getById/${id}`,
            method: 'get'
        })
    },
    updateById(teacher){
        return request({
            url: `/eduService/teacher/updateById`,
            method: `post`,
            data: teacher
        })
    },
    getAllTeacher(){
        return request({
            url: `/eduService/teacher/findAll`,
            method: `get`,

        })
    },
}