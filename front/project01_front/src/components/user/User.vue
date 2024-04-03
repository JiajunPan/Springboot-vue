<script setup>
import {ElButton, ElDrawer, ElMessage} from 'element-plus'
import {onMounted, reactive, ref} from 'vue'
import {useStore} from "vuex";
import {changeInformation, seeInformation, update} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";

const store = useStore()
const visible = ref(false)
const visible1 = ref(false)
const userinformationChange=reactive({
  userid: '',
  username: '',
  name: '',
  sex: '',
  birth: '',
  email: '',
  address: '',
  phone: ''
})
const userUpdate=reactive({
  username: '',
  password:'',
  protectQuestion:'',
  protectAnswer:''
})
const userinformation = reactive({
      userid: '',
      username: '',
      name: '',
      sex: '',
      age: '',
      birth: '',
      email: '',
      address: '',
      phone: ''
    }
)
const ruleFormRef = ref()
const rulesForm = reactive({
  name: [{required: true, message: '姓名不能为空', trigger: 'blur'},
    {min: 2, max: 10, message: '姓名长度2~10个字符', trigger: 'blur'},
  ],
  sex: [{required: true, message: '性别不能为空', trigger: 'blur'},
  ],
  date: [{required: true, message: '生日不能为空', trigger: 'blur'},
  ],
})
const onSubmit = () => {
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      changeInformation(userinformationChange).then((res) => {
        console.log(res.data);
        ElMessage.success(res.msg);
        seeInformation(store.getters.getId).then((res) => {
          console.log(res.data);
          userinformation.userid = res.data.userid;
          userinformation.username = store.getters.getUsername;
          userinformation.name = res.data.name;
          userinformation.sex = res.data.sex;
          userinformation.age = res.data.age;
          userinformation.birth = res.data.birth;
          userinformation.email = res.data.email;
          userinformation.address = res.data.address;
          userinformation.phone = res.data.phone;
        })
        visible.value=false;
      })
    }
  })
}
const onSubmit1=()=>{
  update(userUpdate).then((res)=>{
    console.log(res.data);
    ElMessage.success(res.msg);
    visible1.value=false;
  })
}
const changeUser=()=>{
  userinformationChange.userid=userinformation.userid;
  userinformationChange.username= userinformation.username;
  userinformationChange.name=userinformation.name;
  userinformationChange.sex=userinformation.sex;
  userinformationChange.birth=userinformation.birth;
  userinformationChange.email=userinformation.email;
  userinformationChange.address=userinformation.address;
  userinformationChange.phone= userinformation.phone;
  visible.value=true;
}
const updateUser=()=>{
  userUpdate.username=store.getters.getUsername;
  visible1.value=true;
}
onMounted(() => {
  seeInformation(store.getters.getId).then((res) => {
    console.log(res.data);
    userinformation.userid = res.data.userid;
    userinformation.username = store.getters.getUsername;
    userinformation.name = res.data.name;
    userinformation.sex = res.data.sex;
    userinformation.age = res.data.age;
    userinformation.birth = res.data.birth;
    userinformation.email = res.data.email;
    userinformation.address = res.data.address;
    userinformation.phone = res.data.phone;
  })
})

</script>

<template>
  <div style="text-align:left;font-size: 50px">
    个人中心
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">您的个人信息</div>
  <el-descriptions :column="3" border style="margin-bottom: 30px">
    <el-descriptions-item label="用户id" label-align="right" align="center" min-width="150px">
      {{ userinformation.userid }}
    </el-descriptions-item>
    <el-descriptions-item label="用户名" label-align="right" align="center" min-width="150px">
      {{ userinformation.username }}
    </el-descriptions-item>
    <el-descriptions-item label="姓名" label-align="right" align="center" min-width="150px">
      {{ userinformation.name }}
    </el-descriptions-item>
    <el-descriptions-item label="性别" label-align="right" align="center">
      {{ userinformation.sex }}
    </el-descriptions-item>
    <el-descriptions-item label="年龄" label-align="right" align="center">
      {{ userinformation.age }}
    </el-descriptions-item>
    <el-descriptions-item label="生日" label-align="right" align="center">
      {{ userinformation.birth }}
    </el-descriptions-item>
    <el-descriptions-item label="E-Mail" label-align="right" align="center">
      {{ userinformation.email }}
    </el-descriptions-item>
    <el-descriptions-item label="手机号" label-align="right" align="center">
      {{ userinformation.phone }}
    </el-descriptions-item>
    <el-descriptions-item label="地址" label-align="right" align="center">
      {{ userinformation.address }}
    </el-descriptions-item>
  </el-descriptions>
  <el-button @click="changeUser" type="primary" round size="large">
    修改个人信息
  </el-button>
  <el-button @click="updateUser" type="primary" round size="large">
    修改保护
  </el-button>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改个人信息</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="userinformationChange" ref="ruleFormRef" :rules="rulesForm" label-width="120px">
      <el-form-item label="姓名">
        <el-input v-model="userinformationChange.name"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="userinformationChange.sex"/>
      </el-form-item>
      <el-form-item label="生日">
        <el-col :span="11">
          <el-date-picker
              v-model="userinformationChange.birth"
              type="date"
              placeholder="选择一个日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="E-Mail">
        <el-input v-model="userinformationChange.email"/>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="userinformationChange.phone"/>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="userinformationChange.address"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible1" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改保护</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="userUpdate" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="userUpdate.username" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userUpdate.password"/>
      </el-form-item>
      <el-form-item label="密保问题">
        <el-input v-model="userUpdate.protectQuestion"/>
      </el-form-item>
      <el-form-item label="密保答案">
        <el-input v-model="userUpdate.protectAnswer"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit1">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <div style="font-size: 20px; margin-top: 80px;margin-bottom: 30px">
    有关账户余额和已购买的理财产品，请转至已购买&余额查看
  </div>
  <div style="font-size: 20px; margin-top: 30px;margin-bottom: 30px">
    如果想要注销本平台账户，为保证资金安全，请联系管理员
  </div>
</template>

<style scoped>

</style>