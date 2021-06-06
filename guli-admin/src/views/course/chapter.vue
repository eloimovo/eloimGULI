<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-
bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>
    <el-form label-width="120px">
       
<ul>
  <li>
      <el-button style="" type="text" @click=" openChapterDialog">添加章节</el-button>
  </li>
  <li v-for="chapter in chapterVideoList" :key="chapter.id">
   <p>
          {{ chapter.title }}
          <span>
            <el-button type="text" @click="openVideoDialog(chapter.id)">添加小节</el-button>
            <el-button style="" type="text" @click="editChapter(chapter.id)">编辑</el-button>
            <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
          </span>
        </p>
         <ul>
          <li v-for="video in chapter.children" :key="video.id">
             <p>
              {{ video.title }}
              <span class="acts">
                <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
              </span>
            </p>
          </li>
        </ul>
    </li>
</ul>


      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
          >下 一步</el-button
        >
      </el-form-item>
    </el-form>  
    <!-- 添加和修改章节表单 -->
<el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
    <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
            <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
            <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
    </div>
</el-dialog>
<!--添加小节表单-->
    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number
            v-model="video.sort"
            :min="0"
            controls-
            position="right"
          />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="0" >免费</el-radio>
            <el-radio :label="1" >收费</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
    <el-upload
:on-success="handleVodUploadSuccess"
:on-remove="handleVodRemove"
:before-remove="beforeVodRemove"
:on-exceed="handleUploadExceed"
:file-list="fileList"
:action="BASE_API + '/vodService/uploadVideo'"
:limit="1"
class="upload-demo"
>
    <el-button size="small" type="primary">上传视频</el-button>
<el-tooltip placement="right-end">
    <div slot="content">
        最大支持1G，<br />
        支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br />
        GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br />
        MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br />
        SWF、TS、VOB、WMV、WEBM 等视频格式上传
            </div>
<i class="el-icon-question" />
    </el-tooltip>
</el-upload>
</el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button
          :disabled="saveVideoBtnDisabled"
          type="primary"
          @click="saveOrUpdateVideo"
          >确 定</el-button
        >
      </div>
    </el-dialog>

  </div>
</template>


<script>
import chapterApi from "@/api/chapter.js"
import videoApi from "@/api/video.js"
export default {
  data() {
    return {
      BASE_API: process.env.BASE_API, // 接口API地址
      saveBtnDisabled: false,
      saveVideoBtnDisabled:false,
       courseId:'',
      chapterVideoList:[],
      fileList: [], //上传文件列表
      dialogChapterFormVisible: false,
      dialogVideoFormVisible:false,
      chapter:{
        title:"",
        sort:"",
        courseId:"",
        id:'',
      },
      video:{
        title:'',
        sort:'',
        isFree:'',
        courseId:'',
        chapterId:'',
        id:'',
        videoSourceId:'',
        videoOriginalName:'',
      }
     
    };
  
  },
  methods: {
      //上传成功执行方法
    handleVodUploadSuccess(response,file,fileList){
        this.video.videoSourceId = response.data.videoId;
        this.video.videoOriginalName=file.name
    },
    beforeVodRemove(file, fileList){
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
     handleVodRemove() {
      //调用接口里面的删除视频方法
      video.deleteAliyunVideoById(this.video.videoSourceId).then((resp) => {
        this.$message({
          type: "success",
          message: "删除成功!",
        });
        //把文件列表清空
        this.fileList = [];
        //清空视频id和视频名称
        this.video.videoSourceId =""
        this.video.video_original_name =""
      });
    },
    openVideoDialog(chapterId ){
      this.dialogVideoFormVisible = true,
      this.video={}
       //把文件列表清空
      this.fileList = [];
      this.video.chapterId=chapterId 
    },
    editChapter(chapterId){
      
      chapterApi.getChapterById(chapterId).then(resp =>{
      this.chapter=resp.data.chapter
      })
       this.dialogChapterFormVisible = true
    },
    editVideo(videoId){
      videoApi.getVideoById(videoId).then(resp =>{
        this.video=resp.data.video
      })
      this.dialogVideoFormVisible=true
    },
     removeChapter(chapterId) {
      this.$confirm("此操作将永久删除章节信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //点击确定，执行then方法
        chapterApi.deleteChapter(chapterId).then((resp) => {
          //删除成功
          //提示信息
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //刷新页面
          this.getChapterVideoById();
        });
      });
    },
    //删除小节
    removeVideo(videoId) {
      this.$confirm("此操作将永久删除小节信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //点击确定，执行then方法
        videoApi.deleteVideo(videoId).then((resp) => {
          //删除成功
          //提示信息
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //刷新页面
          this.getChapterVideoById();
        });
      });
    },
    saveOrUpdate() {
      if (this.chapter.id) {
        //修改章节
        this.updateChapter();
      } else {
        //新增章节
        this.saveChapter();
      }
    },
    saveOrUpdateVideo() {
      if (this.video.id) {
        //修改章节
        this.updateVideo();
      } else {
        //新增章节
        this.saveVideo();
      }
    },
    saveChapter(){
      this.chapter.courseId = this.courseId;
      chapterApi.addChapter(this.chapter).then(
        (resp) =>{
          this.dialogChapterFormVisible = false;
        //提示信息
        this.$message({
          message: "添加章节成功",
          type: "success",
        })
        //刷新页面
        this.getChapterVideoById();
        }
      )
    },
    saveVideo(){
      this.video.courseId = this.courseId;
      videoApi.addVideo(this.video).then(
        (resp) =>{
          this.dialogVideoFormVisible = false;
        //提示信息
        this.$message({
          message: "添加小节成功",
          type: "success",
        })
        //刷新页面
        this.getChapterVideoById();
        }
      )
    },
    //修改章节
    updateChapter() {
      //设置课程id到chapter对象中
      this.chapter.courseId = this.courseId;
      chapterApi.updateChapter(this.chapter).then((resp) => {
        //关闭弹框
        this.dialogChapterFormVisible = false;
        //提示信息
        this.$message({
          message: "修改章节成功",
          type: "success",
        });
        //刷新页面
        this.getChapterVideoById();
      });
    },
    updateVideo() {
      //设置课程id到chapter对象中
      this.video.courseId = this.courseId;
      videoApi.updateVideo(this.video).then((resp) => {
        //关闭弹框
        this.dialogVideoFormVisible = false;
        //提示信息
        this.$message({
          message: "修改章节成功",
          type: "success",
        });
        //刷新页面
        this.getChapterVideoById();
      });
    },
    openChapterDialog(){
      this.dialogChapterFormVisible = true,
      this.chapter={}
    },
    
    //跳转到上一步
    previous() {
      this.$router.push({ path: "/course/info/"+this.courseId });
    },
    next() {
      //跳转到第三步
      this.$router.push({ path: "/course/publish/"+this.courseId });
    },
    getChapterVideoById(){
      chapterApi.getChapterVideoById(this.courseId).then(resp=>{
        this.chapterVideoList=resp.data.list
      })
    },
  },

   created() {
    //获取路由里的id值
    if(this.$route.params && this.$route.params.id){
      this.courseId = this.$route.params.id
    }
    //根据课程id查询对应的课程章节和小结
    this.getChapterVideoById(this.courseId);
  },
};
</script>
