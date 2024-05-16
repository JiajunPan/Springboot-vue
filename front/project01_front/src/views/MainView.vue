<!--主页面（推送页）-->
<script setup>
import {computed, ref} from 'vue'
import {logout} from "@/api/user";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "vuex";
var cnt = 2;
const isCollapse = ref(true)
const method1 = () => {
  if (cnt % 2 !== 0) {
    cnt++;
    isCollapse.value = true;
  } else {
    cnt++;
    isCollapse.value = false;
  }
}
const store = useStore()
const ExitClick=()=>{
logout().then((res)=>{
  ElMessage.success(res.msg);
  router.push({path: "/"})
})
}
</script>

<template>
  <div class="common-layout" style="width: 50px">
    <el-affix :offset="50">
      <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          :collapse="isCollapse"
          @open="handleOpen"
          @close="handleClose"
          style="height: 90vh;"
          router="true"
        >
        <el-menu-item v-model="isCollapse" @click="method1" style="margin-bottom: 40px;">
          <el-icon>
            <Expand/>
          </el-icon>
          <template #title>展开</template>
        </el-menu-item>
        <el-menu-item index="1" style="margin-bottom: 10px;" route="/main/home">
          <el-icon>
            <House/>
          </el-icon>
          <template #title>主页</template>
        </el-menu-item>
        <el-menu-item index="2" style="margin-bottom: 10px;" route="/main/product">
          <el-icon>
            <Goods/>
          </el-icon>
          <template #title>产品</template>
        </el-menu-item>
        <el-menu-item index="3" style="margin-bottom: 10px;" route="/main/income">
          <el-icon>
            <Coin/>
          </el-icon>
          <template #title>收益</template>
        </el-menu-item>
        <el-menu-item index="4" style="margin-bottom: 100px;" route="/main/left">
          <el-icon>
            <Wallet/>
          </el-icon>
          <template #title>已购买&余额</template>
        </el-menu-item>
        <el-menu-item index="5" style="bottom:1vh;top: 5vh;" route="/main/user">
          <el-icon>
            <User/>
          </el-icon>
          <template #title>个人中心</template>
        </el-menu-item>
        <el-menu-item index="6" style="bottom:1vh;top: 7vh" @click="ExitClick">
          <el-icon>
            <SwitchButton/>
          </el-icon>
          <template #title>退出</template>
        </el-menu-item>
      </el-menu>
    </el-affix>

  </div>
  <div style="position:absolute;width: 85%;height: 90%;left:100px;right: 0; top: 80px;">
    <router-view/>
  </div>

  <el-backtop :right="50" :bottom="50"/>
</template>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>