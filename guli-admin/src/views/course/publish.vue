<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="3"
      process-status="wait"
      align-center
      style="margin-
      bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>
    <div class="ccInfo">
      <img :src=" coursePublishInfo.cover" />
      <div class="main">
        <h2>{{  coursePublishInfo.title }}</h2>
        <p class="gray">
          <span>共{{  coursePublishInfo.lessonNum }}课时</span>
        </p>
        <p>
          <span
            >所属分类：{{  coursePublishInfo.subjectLevelOne }} —
            {{  coursePublishInfo.subjectLevelTwo }}</span
          >
        </p>
        <p>课程讲师：{{  coursePublishInfo.teacherName }}</p>
        <h3 class="red">￥{{  coursePublishInfo.price }}</h3>
      </div>
    </div>

    <el-form label-width="auto">
      <el-form-item>
        <el-button @click="previous">返回修改</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish"
          >发布课程</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import courseApi from "@/api/course.js"
export default {
  
  data() {
    return {
      saveBtnDisabled: false,

      courseId:'',
      coursePublishInfo:{},
    };
  },
  methods: {
    getCoursePublishInfo(){
      courseApi.getCoursePublishInfo(this.courseId)
      .then(resp =>{
        this.coursePublishInfo=resp.data.result
      })
    },
    //跳转到上一步
    previous() {    
      this.$router.push({ path: "/course/chapter/"+this.courseId });
    },
    publish(){
       this.$confirm("你确定要发布此课程, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        courseApi.publishCourse(this.courseId).then((resp) => {
          //提示信息
          this.$message({
            message: "课程发布成功",
            type: "success",
          })
        })
      })
       //跳转课程列表页面
      this.$router.push({ path: "/course/list" });
  },
  noPublish(){
     this.$router.push({ path: "/course/list" });
  },
  },
  created(){
    if(this.$route.params && this.$route.params.id){
      this.courseId = this.$route.params.id
      //调用接口方法根据课程id查询课程信息
      this.getCoursePublishInfo()
     }
  },   
}
</script>
<style scoped>
.ccInfo {
  background: #f5f5f5;
  padding: 30px;
  overflow: hidden;
  border: 2px dashed #ddd;
  margin-bottom: 50px;
  position: relative;
}
.ccInfo img {
  background: #d6d6d6;
  width: 450px;
  height: 300px;
  display: block;
  float: left; 
  border: none;
}
.ccInfo .main {
  margin-left: 520px;
}
.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 2;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;
}
</style>