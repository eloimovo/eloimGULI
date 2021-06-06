import request from '@/utils/request'
export default {
    getCommentPage(page, limit, courseId) {
        return request({
            url: `/eduService/comment/getCommentPage/${page}/${limit}/${courseId}`,
            method: 'get',

        })
    },
    addComment(comment) {
        return request({
            url: `/eduService/comment/addComment`,
            method: 'post',
            data: comment
        })
    }
}