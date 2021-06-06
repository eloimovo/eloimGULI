<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-select v-model="searchObj.type" clearable placeholder="请选择">
          <el-option label="学员登录数统计" value="login_num" />
          <el-option label="学员注册数统计" value="register_num" />
          <el-option label="课程播放数统计" value="video_view_num" />
          <el-option label="每日课程数统计" value="course_num" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.begin"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()"
        >查询</el-button
      >
    </el-form>
    <div class="chart-container">
      <div id="chart" class="chart" style="height: 500px; width: 100%" />
    </div>
  </div>
</template>
<script>
import echarts from "echarts";
import staApi from '@/api/sta.js'
export default {
  data() {
    return {
      searchObj: {
        begin: "",
        end: "",
        type: "",
      },
      btnDisabled: false,
      chart: null,
      title: "",
      xData: [],
      yData: [],
    };
  },
  methods: {
    setChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = echarts.init(document.getElementById("chart"));
      // console.log(this.chart)
      // 指定图表的配置项和数据
      var option = {
        title: {
            text: this.title
        },
        // 
        xAxis: {
          type: "category",
          data:this.xData,//x轴数据
        },
        // 
        yAxis: {
          type: "value",
        },
        series: [
          {
            // 系列中的数据内容数组
            data:this.yData,//y轴数据
            // 折线图
            type: "line",//type 决定自己的图表类型
          },
        ],
      };
      this.chart.setOption(option);
    },
    showChart(){
        staApi.getShowData(this.searchObj).then(resp=>{
            //x轴 时间
            this.xData = resp.data.xlist
            //y轴 数据
            this.yData = resp.data.ylist

            //调用下面生成图表方法，改变值
            this.setChart();
        })
    }
  },
  created() {},
};
</script>

</script>
