<script>
export default {
  name: 'index',
  data() {
    return {
      imgList: [
        {id: 0, idView: require("@/assets/RotationImg/1.jpg")},
        {id: 1, idView: require("@/assets/RotationImg/2.jpg")},
        {id: 2, idView: require("@/assets/RotationImg/3.jpg")},
      ]
    };
  },
  methods: {}
}
</script>
<script setup>
import {useStore} from "vuex";

const store = useStore()
</script>
<template>
  <div style="text-align:left;font-size: 50px;margin-bottom: 50px">
    主页
  </div>
  <div style="font-size: 24px; margin-top: 80px;margin-bottom: 30px">
    欢迎，<br>
    用户编号：{{ store.getters.getId }}<br>
    用户名：{{ store.getters.getUsername }}
  </div>
  <div class="center" style="align-items: center;text-align: center;">
    <el-carousel trigger="click" height="400px" style="width: 1000px">
      <el-carousel-item v-for="item in imgList" :key="item.id">
        <img style="object-fit: scale-down;width: 100%;height: 100%" :src="item.idView" class="image" alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div class="center">
    <a href="http://www.licaizz.cn/licaitouzi/jijin/2019-02-21/28258.html">上市基金产品创新将更关注防风险</a>
  </div>
  <div class="center">
    <a href="http://www.licaizz.cn/licaitouzi/gupiao/2019-02-25/28274.html">市场定价意识增强 新股“无风险”收益降低</a>
  </div>
  <div class="center">
    <a href="http://news.10jqka.com.cn/20230831/c650210378.shtml">中国银行业协会秘书长刘峰：理财产品已成为实体经济融资的重要来源</a>
  </div>
  <div class="center">
    <a href="http://stock.10jqka.com.cn/usstock/20230831/c650212604.shtml">美股内部交易 |
      嘉信理财于8月30日披露1笔公司内部人交易情况</a>
  </div>
  <div class="center">
    <a href="https://finance.eastmoney.com/a/202308312832902929.html">邮储银行刘建军：手续费及佣金净收入增速较低受理财净值转型所致</a>
  </div>
  <div class="center">
    <a href="https://www.thepaper.cn/newsDetail_forward_24427664">法官说法|投资理财要理性 天上不会掉“馅饼”</a>
  </div>
  <div class="center">
    <a href="https://baijiahao.baidu.com/s?id=1775635020350667074">坚持以客户需求为中心 信银理财持续完善产品及服务</a>
  </div>
</template>

<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.center {
  width: 100%;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
}

a {
  text-align: center;
  font-size: 30px;
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>