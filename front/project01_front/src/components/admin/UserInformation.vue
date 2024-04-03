<script setup>
import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {allInformation, allmoney} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {changeUserInformation} from "@/api/admin";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const search=ref('')
const userDataChange=reactive(
  {
    userid: "",
    name: "",
    sex: "",
    birth: "",
    email: "",
    address: "",
    phone: ""
  }
)
const userData = ref([
  {
    userid: "",
    name: "",
    sex: "",
    age: "",
    birth: "",
    email: "",
    address: "",
    phone: ""
  }
])
onMounted(() => {
  allInformation().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
const handleEdit=(data)=>{
userDataChange.userid=data.userid;
userDataChange.name=data.name;
userDataChange.sex=data.sex;
userDataChange.birth=data.birth;
userDataChange.email=data.email;
userDataChange.address=data.address;
userDataChange.phone=data.phone;
  visible.value=true;
}
const onSubmit=()=>{
  changeUserInformation(userDataChange).then((res) => {
    console.log(res.data);
    ElMessage.success("成功");
    allInformation().then((res) => {
      console.log(res.data);
      userData.value = res.data;
    })
  })
  visible.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    用户信息管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理用户个人信息</div>

  <el-table :data="userData.filter(data => !search||data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="userid" label="用户id" width="75" sortable/>
    <el-table-column prop="name" label="姓名" width="120" sortable/>
    <el-table-column prop="sex" label="性别" width="60" sortable/>
    <el-table-column prop="age" label="年龄" width="60" sortable/>
    <el-table-column prop="birth" label="生日" width="120" sortable/>
    <el-table-column prop="email" label="邮箱" width="200" sortable/>
    <el-table-column prop="address" label="地址" width="200" sortable/>
    <el-table-column prop="phone" label="电话" width="150" sortable/>
    <el-table-column align="right" fixed="right" min-width="180">
      <template #header>
        <el-input v-model="search" size="default" style="width: 150px;" placeholder="搜索姓名"/>
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
      <h4 :id="titleId" :class="titleClass">修改用户个人信息</h4>
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
      <el-form-item label="姓名">
        <el-input v-model="userDataChange.name"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="userDataChange.sex"/>
      </el-form-item>
      <el-form-item label="生日">
        <el-col :span="11">
          <el-date-picker
              v-model="userDataChange.birth"
              type="date"
              placeholder="选择一个日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="E-Mail">
        <el-input v-model="userDataChange.email"/>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="userDataChange.phone"/>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="userDataChange.address"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>