
<template>
    <div class="app-container">
        <el-form label-width="120px">
            <!-- 讲师头像：TODO -->
            <el-form-item label="讲师头像">
                <!-- 头衔缩略图 -->
                <pan-thumb :image="teacher.avatar" />
                <!-- 文件上传按钮 -->
                <el-button
                        type="primary"
                        icon="el-icon-upload"
                        @click="imagecropperShow = true"
                        >更换头像
                </el-button>
                <!--
            v-show：是否显示上传组件
            :key：类似于id，如果一个页面多个图片上传控件，可以做区分
            :url：后台上传的url地址
            @close：关闭上传组件
            @crop-upload-success：上传成功后的回调 -->
                 <image-cropper
                   v-show="imagecropperShow"
                   :width="300"
                   :height="300"
                   :key="imagecropperKey"
                   :url="BASE_API + '/oss/upload'"
                   field="file"
                   @close="close"
                   @crop-upload-success="cropSuccess"
                   />
            </el-form-item>
          
            <el-form-item label="讲师名称">
                <el-input v-model="teacher.name" />
            </el-form-item>
            <el-form-item label="讲师排序">
                <el-input-number
                                 v-model="teacher.sort"
                                 controls-position="right"
                                 :min="0"
                                 />
            </el-form-item>
            <el-form-item label="讲师头衔">
                <el-select v-model="teacher.level" clearable placeholder="选择讲师头衔">
                    <!--
数据类型一定要和取出的json中的一致，否则没法回填
因此，这里value使用动态绑定的值，保证其数据类型是number
-->
                    <el-option :value="1" label="高级讲师" />
                    <el-option :value="2" label="首席讲师" />
                </el-select>


            </el-form-item>
            <el-form-item label="讲师资历">
                <el-input v-model="teacher.career" />
            </el-form-item>
            <el-form-item label="讲师简介">
                <el-input v-model="teacher.intro" :rows="10" type="textarea" />
            </el-form-item>
              <el-form-item>
                <el-button
                           :disabled="saveBtnDisabled"
                           type="primary"
                           @click="saveOrUpdate"
                           >保存</el-button
                    >
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    //引入对应的新增api方法
    import teacherApi from "@/api/teacher.js"
    import ImageCropper from '@/components/ImageCropper'
    import PanThumb from '@/components/PanThumb'

export default {
    components:{ImageCropper,PanThumb},
    data() {
        return {
            teacher: {
                name: "",
                sort: 0,
                level: 1,
                career: "",
                intro: "",
                avatar: "",
            },
            saveBtnDisabled: false,
            imagecropperShow: false,
            imagecropperKey:0,
            BASE_API:process.env.BASE_API,

        };
    },
    created() {//在页面渲染之前
    //判断路径中是否有id值
     this.init()
    },
    watch: {
            $route(to, from) {
                //路由变化方式，当路由发送变化，方法就执行
                console.log("watch $route");
                this.init()
            },
        },
    methods: {
        init() {
        //判断路径中是否有id值
        if (this.$route.params && this.$route.params.id) {
            //从路径中获取id值
            const id = this.$route.params.id;
            //调用根据id查询的方法
            this.getInfoById(id);
        }else{
            this.teacher = {};
        }
    },
        saveOrUpdate() {
           //判断修改还是新增操作
            //根据teacher对象是否有id值来判断
            if (!this.teacher.id) {
            //没有id值，做【新增操作】
            this.saveBtnDisabled = true;
            this.saveData();
            }else{
            //有id值，做【修改操作】
            this.updateTeacher()
        }
    },
         updateTeacher() {
             teacherApi.updateById(this.teacher).then((resp) => {
        //提示信息
             this.$message({
             message: "修改讲师记录成功",
             type: "success",
              });
        //回到讲师列表 路由跳转
             this.$router.push({ path: "/teacher/table" });
        });
    },
        // 保存
        saveData() {
            teacherApi.addTeacher(this.teacher).then((resp) => {//添加成功
                //提示信息
                this.$message({
                    message: "添加讲师记录成功",
                    type: "success",
                });
                //回到讲师列表 路由跳转
                this.$router.push({path: '/teacher/table'})
            });
        },
        getInfoById(id){
             teacherApi.getTeacherById(id)
              .then(resp =>{
              this.teacher = resp.data.teacher
          })
        },
        cropSuccess(data){
             this.imagecropperShow=false;
      //参数resp.data被封装到了方法参数data中了
      this.teacher.avatar = data.url
      this.imagecropperKey = this.imagecropperKey+1
        },
        close(){
            this.imagecropperShow=false;
      //上传组件初始化
      this.imagecropperKey = this.imagecropperKey+1
        },
    },
  
};
</script>