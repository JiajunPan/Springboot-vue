<script setup>
import { ElButton, ElDrawer, ElMessage, ElMessageBox } from 'element-plus';
import {onMounted, reactive, ref} from 'vue'
import {useStore} from "vuex";
import {changeInformation, seeInformation, update} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import axios from 'axios'; // 导入axios
import { useRouter } from 'vue-router';

const store = useStore()
const visible = ref(false)
const visible1 = ref(false)
const router = useRouter();

const userinformationChange=reactive({
  userid: '',
  username: '',
  name: '',
  sex: '',
  birth: '',
  email: '',
  address: '',
  phone: '',
  address: []
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
const provinces = [
  {
    value: '北京市',
    label: '北京市',
  },
  {
    value: '天津市',
    label: '天津市',
  },
  {
    value: '河北省',
    label: '河北省',
  },
  {
    value: '山西省',
    label: '山西省',
  },
  {
    value: '内蒙古自治区',
    label: '内蒙古自治区',
  },
  {
    value: '辽宁省',
    label: '辽宁省',
  },
  {
    value: '吉林省',
    label: '吉林省',
  },
  {
    value: '黑龙江省',
    label: '黑龙江省',
  },
  {
    value: '上海市',
    label: '上海市',
  },
  {
    value: '江苏省',
    label: '江苏省',
  },
  {
    value: '浙江省',
    label: '浙江省',
  },
  {
    value: '安徽省',
    label: '安徽省',
  },
  {
    value: '福建省',
    label: '福建省',
  },
  {
    value: '江西省',
    label: '江西省',
  },
  {
    value: '山东省',
    label: '山东省',
  },
  {
    value: '河南省',
    label: '河南省',
  },
  {
    value: '湖北省',
    label: '湖北省',
  },
  {
    value: '湖南省',
    label: '湖南省',
  },
  {
    value: '广东省',
    label: '广东省',
  },
  {
    value: '广西壮族自治区',
    label: '广西壮族自治区',
  },
  {
    value: '海南省',
    label: '海南省',
  },
  {
    value: '重庆市',
    label: '重庆市',
  },
  {
    value: '四川省',
    label: '四川省',
  },
  {
    value: '贵州省',
    label: '贵州省',
  },
  {
    value: '云南省',
    label: '云南省',
  },
  {
    value: '西藏自治区',
    label: '西藏自治区',
  },
  {
    value: '陕西省',
    label: '陕西省',
  },
  {
    value: '甘肃省',
    label: '甘肃省',
  },
  {
    value: '青海省',
    label: '青海省',
  },
  {
    value: '宁夏回族自治区',
    label: '宁夏回族自治区',
  },
  {
    value: '新疆维吾尔自治区',
    label: '新疆维吾尔自治区',
  },
  {
    value: '台湾省',
    label: '台湾省',
  },
  {
    value: '香港特别行政区',
    label: '香港特别行政区',
  },
  {
    value: '澳门特别行政区',
    label: '澳门特别行政区',
  },
];

const ruleFormRef = ref()
const rulesForm = reactive({
  name: [{ required: true, message: '姓名不能为空', trigger: 'blur' },
    { min: 2, max: 10, message: '姓名长度2~10个字符', trigger: 'blur' },
  ],
  sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
  birth: [{ required: true, message: '生日不能为空', trigger: 'blur' }],
  email: [
    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: ['blur', 'change'] }
  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: /^[1][3,4,5,7,8][0-9]{9}$/, message: '手机号格式不正确', trigger: ['blur', 'change'] }
  ],
});

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
const deleteAccount = (id) => {
  return new Promise((resolve, reject) => {
    axios.delete(`/api/user/delete/${id}`)
      .then((response) => {
        resolve(response.data); // 如果删除成功，传递成功的数据
      })
      .catch((error) => {
        reject(error); // 如果删除失败，传递失败的原因
      });
  });
};
const deleteUser = (id) => {
  ElMessageBox.confirm('确定要注销账号吗?（账户资金将一起注销，并且无法找回）', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteAccount(id)
      .then(() => {
        // 处理删除成功的逻辑
        ElMessage.success('账号注销成功');
        router.push({ path: "/" })
      })
      .catch(() => {
        // 处理删除失败的逻辑
        ElMessage.error('账号注销失败');
      });
  }).catch(() => {
    // 用户取消删除的操作
    ElMessage.info('已取消注销账号');
  });
};
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
    完善个人信息
  </el-button>
  <el-button @click="updateUser" type="primary" round size="large">
    修改保护
  </el-button>
  <el-button @click="deleteUser(userinformation.userid)" type="danger" round size="large">
    注销账号
  </el-button>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">完善个人信息</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="userinformationChange" :rules="rulesForm" ref="ruleFormRef" label-width="120px">
  <el-form-item label="姓名" prop="name">
    <el-input v-model="userinformationChange.name"/>
  </el-form-item>
  <el-form-item label="性别" prop="sex">
  <el-radio-group v-model="userinformationChange.sex">
    <el-radio label="男">男</el-radio>
    <el-radio label="女">女</el-radio>
  </el-radio-group>
</el-form-item>

  <el-form-item label="生日" prop="birth">
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
  <el-form-item label="E-Mail" prop="email">
    <el-input v-model="userinformationChange.email"/>
  </el-form-item>
  <el-form-item label="手机号" prop="phone">
    <el-input v-model="userinformationChange.phone"/>
  </el-form-item>
  <el-form-item label="地址" prop="address">
  <el-cascader
    v-model="userinformationChange.address"
    :options="provinces"
    placeholder="请选择省份"
    clearable
  ></el-cascader>
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