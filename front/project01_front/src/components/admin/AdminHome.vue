<script setup>
import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {all, delAdmin, registerAdmin, resetpassword} from "@/api/admin";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const visible1 = ref(false)
const visible2 = ref(false)
const search = ref('')
const adminChange=reactive(
      {
        adminId: "",
        adminName: "",
        password: ""
      }
)
const newAdminData=reactive(
  {
    adminName: "",
    password: ""
  }
)
const adminData = ref([
  {
    adminName: "",
    password: ""
  }
])
onMounted(() => {
  all().then((res) => {
    console.log(res.data);
    adminData.value = res.data;
  })
})
const editprotect=(data)=>{
adminChange.adminId=data.adminId;
adminChange.adminName=data.adminName;
adminChange.password=data.password;
visible.value=true;
}
const deleteAccount=(data)=>{
  adminChange.adminId=data.adminId;
  adminChange.adminName=data.adminName;
  adminChange.password=data.password;
visible1.value=true;
}
const newAccount=()=>{
  newAdminData.adminName="";
      newAdminData.password= "";
  visible2.value=true;
}
// 改密码
const onSubmit=()=>{
resetpassword(adminChange).then((res)=>{
  console.log(res.data);
  ElMessage.success("成功");
  all().then((res) => {
    console.log(res.data);
    adminData.value = res.data;
  })
})
  visible.value=false;
}
// 删除
const onDelete=()=>{
delAdmin(adminChange.adminId).then((res)=>{
  console.log(res.data);
  ElMessage.success("成功");
  all().then((res) => {
    console.log(res.data);
    adminData.value = res.data;
  })
})
  visible1.value=false;
}
// 新建
const onSubmit1=()=>{
registerAdmin(newAdminData).then((res)=>{
  console.log(res.data);
  ElMessage.success("成功");
  all().then((res) => {
    console.log(res.data);
    adminData.value = res.data;
  })
})
  visible2.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    管理员管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理管理员账户</div>

  <el-table :data="adminData.filter(data => !search||data.adminName.toLowerCase().includes(search.toLowerCase()))"
            style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="adminId" label="管理员id" width="150" sortable/>
    <el-table-column prop="adminName" label="管理员用户名" width="200" sortable/>
    <el-table-column prop="password" label="密码" width="200" sortable/>
    <el-table-column align="right" fixed="right" min-width="320">
      <template #header>
        <el-input v-model="search" size="default" style="width: 200px; margin-right: 20px;" placeholder="搜索管理员用户名"/>
        <el-button size="default" @click="newAccount">
          新建
        </el-button>
      </template>
      <template #default="scope">
        <el-button size="default" @click="editprotect(scope.row)">
          改密码
        </el-button>
        <el-button size="default" type="danger" @click="deleteAccount(scope.row)">
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{close, titleId, titleClass}">
      <h4 :id="titleId" :class="titleClass">修改密码</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="adminChange" label-width="120px">
      <el-form-item label="管理员id">
        <el-input v-model="adminChange.adminId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="管理员用户名">
        <el-input v-model="adminChange.adminName" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="adminChange.password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible1" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">删除管理员</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="adminChange" ref="ruleFormRef" label-width="120px">
      <el-form-item label="管理员id">
        <el-input v-model="adminChange.adminId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="管理员用户名">
        <el-input v-model="adminChange.adminName" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="adminChange.password" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="确认删除?">此操作不可撤销！</el-form-item>
      <el-form-item>
        <el-button type="danger"  @click="onDelete" >确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible2" :show-close="false">
    <template #header="{ close, titleId, titleClass}">
      <h4 :id="titleId" :class="titleClass">新建</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="newAdminData" label-width="120px">
      <el-form-item label="管理员用户名">
        <el-input v-model="newAdminData.adminName" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="newAdminData.password" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit1">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>