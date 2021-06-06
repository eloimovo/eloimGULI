import request from '@/utils/request'

export default  {
    getVideoById(videoId){
    return request({
    url: `/eduService/video/getVideoById/${videoId}`,
    method: 'get',
     })
    },
    addVideo(video){
        return request({
        url: `/eduService/video/addVideo`,
        method: 'post',
        data:video
         })
        },
    deleteVideo(videoId){
        return request({
            url: `/eduService/video/deleteVideoById/${videoId}`,
            method: `delete`,
        })
    },
    updateVideo(video){
        return request({
        url: `/eduService/video/updateVideo`,
        method: 'post',
        data:video
         })
        },
         //根据视频id删除阿里云视频
    deleteAliyunVideoById(VideoId){
        return request({
            url: "/vodService/removeAliyunVideoById/"+VideoId,
            method: 'delete',
        })
    }
}