<script setup>
import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {all, allmoney} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {delUser, setProtectUser, setStatus} from "@/api/admin";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const visible1 = ref(false)
const visible2 = ref(false)
const search = ref('')
const stateData = reactive(
  {
    userid: "",
    username: "",
    status: ""
  }
)
const protectData = reactive(
  {
    userid: "",
    username: "",
    password: "",
    protectQuestion: "",
    protectAnswer: "",
  }
)
const deleteData = reactive(
  {
    userid: "",
    username: "",
    status: ""
  }
)
const userData = ref([
  {
    userid: "",
    username: "",
    password: "",
    protectQuestion: "",
    protectAnswer: "",
    status: ""
  }
])
onMounted(() => {
  all().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
const editprotect = (data) => {
  protectData.userid = data.userid;
  protectData.username = data.username;
  protectData.password = data.password;
  protectData.protectQuestion = data.protectQuestion;
  protectData.protectAnswer = data.protectAnswer;
  visible.value = true;
}
const editstate = (data) => {
  stateData.userid = data.userid;
  stateData.username = data.username;
  stateData.status = data.status;
  visible1.value = true;
}
const deleteAccount = (data) => {
  deleteData.userid = data.userid;
  deleteData.username = data.username;
  deleteData.status = data.status;
  visible2.value = true;
}
const onSubmit=()=>{
  setProtectUser(protectData).then((res) => {
    console.log(res.data);
    ElMessage.success("成功");
    all().then((res) => {
      console.log(res.data);
      userData.value = res.data;
    })
  })
  visible.value =false;
}
const onSubmit1=()=>{
  setStatus(stateData).then((res) => {
    console.log(res.data);
    ElMessage.success("成功");
    all().then((res) => {
      console.log(res.data);
      userData.value = res.data;
    })
  })
  visible1.value =false;
}
const onSubmit2=()=>{
delUser(deleteData.userid).then((res) => {
  console.log(res.data);
  ElMessage.success("成功");
  all().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
  visible2.value =false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    用户账户管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理用户账户</div>

  <el-table :data="userData.filter(data => !search||data.username.toLowerCase().includes(search.toLowerCase()))"
            style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="userid" label="用户id" width="95" sortable/>
    <el-table-column prop="username" label="用户名" width="150" sortable/>
    <el-table-column prop="password" label="密码" width="200"/>
    <el-table-column prop="protectQuestion" label="密保问题" width="200" sortable/>
    <el-table-column prop="protectAnswer" label="密保答案" width="200" sortable/>
    <el-table-column prop="status" label="状态码（0正常1提示2禁用）" width="260" sortable/>
    <el-table-column align="right" fixed="right" min-width="260">
      <template #header>
        <el-input v-model="search" size="default" style="width: 240px;" placeholder="搜索用户名"/>
      </template>
      <template #default="scope">
        <el-button size="default" @click="editprotect(scope.row)"
        >改保护
        </el-button
        >
        <el-button size="default" @click="editstate(scope.row)"
        >改状态
        </el-button
        >
        <el-button size="default" type="danger" @click="deleteAccount(scope.row)"
        >删除
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改账户保护</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="protectData" label-width="120px">
      <el-form-item label="用户id">
        <el-input v-model="protectData.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="protectData.username" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="protectData.password"/>
      </el-form-item>
      <el-form-item label="密保问题">
        <el-input v-model="protectData.protectQuestion"/>
      </el-form-item>
      <el-form-item label="密保答案">
        <el-input v-model="protectData.protectAnswer"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible1" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改账户状态</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="stateData" label-width="120px">
      <el-form-item label="用户id">
        <el-input v-model="stateData.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="stateData.username" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="状态码（0正常1提示2禁用）">
        <el-input-number v-model="stateData.status"  :step="1" :min="0" :max="2"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit1">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible2" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">删除用户</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="deleteData" label-width="120px">
      <el-form-item label="用户id">
        <el-input v-model="deleteData.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="deleteData.username" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="状态码">
        <el-input v-model="deleteData.status" readonly="readonly"/>
      </el-form-item>
      <br>
      <br>
      <el-form-item label="确认删除？">此操作不可逆！
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="onSubmit2">确认删除</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>