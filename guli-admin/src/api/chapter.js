import request from '@/utils/request'

export default  {
    getChapterById(courseId){
    return request({
    url: `/eduService/chapter/getChapterById/${courseId}`,
    method: 'get',
     })
    },
    getChapterVideoById(courseId){
        return request({
            url: `/eduService/chapter/getChapterVideoById/${courseId}`,
            method: 'get',
             })
    },
    addChapter(chapter){
        return request({
        url: `/eduService/chapter/addChapter`,
        method: 'post',
        data:chapter
         })
        },
    deleteChapter(chapterId){
        return request({
            url: `/eduService/chapter/deleteChapterById/${chapterId}`,
            method: `delete`,
        })
    },
    updateChapter(chapter){
        return request({
        url: `/eduService/chapter/updateChapter`,
        method: 'post',
        data:chapter
         })
    },
    
}
