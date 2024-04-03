<script setup>
import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {allmoney} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {changeMoney} from "@/api/admin";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const userData = ref([
  {
    userid:"",
    balance:""
  }
])
const userDataChange=reactive(
  {
    userid:"",
    balance:""
  }
)
const search=ref('')
onMounted(() => {
  allmoney().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
const handleEdit=(data)=>{
  userDataChange.userid=data.userid;
  userDataChange.balance=data.balance;
  visible.value=true;
}
const onSubmit=()=>{
changeMoney(userDataChange).then((res) => {
  console.log(res.data);
  ElMessage.success("成功");
  allmoney().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
  visible.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    用户余额管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理用户余额</div>

  <el-table :data="userData.filter(data => !search||data.userid.toString().toLowerCase().includes(search.toLowerCase()))" style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="userid" label="用户id" width="150" sortable/>
    <el-table-column prop="balance" label="余额"  width="200" sortable/>
    <el-table-column align="right" fixed="right" min-width="220">
      <template #header>
        <el-input v-model="search" size="default" style="width: 200px;" placeholder="搜索用户id"/>
      </template>
      <template #default="scope">
        <el-button size="large" @click="handleEdit(scope.row)"
        >修改
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改余额</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="userDataChange" label-width="120px">
      <el-form-item label="用户id">
        <el-input v-model="userDataChange.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="余额">
        <el-input-number v-model="userDataChange.balance" :precision="2" :step="0.01" :min="0"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>