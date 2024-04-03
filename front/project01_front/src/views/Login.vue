<script setup>
import {onMounted, reactive, ref} from 'vue'
import {useStore} from "vuex";
import {useRoute, useRouter} from "vue-router";
import {findpassword, login, logout, register} from '@/api/user'
import {ElMessage} from "element-plus";
import {loginadmin, logoutadmin} from "@/api/admin";

const dialogFormVisible = ref(false)
const router = useRouter()
const store = useStore()
const userForm = reactive({username: "", password: ""})
const userFormRegister = reactive({username: "", password: "", protectQuestion: "", protectAnswer: ""})
const form = reactive({username: "", protectQuestion: "", protectAnswer: ""})
const adminForm = reactive({adminName: "", password: ""})
// 校验规则
const rulesRegister = reactive({
  username: [{required: true, message: '用户名不能为空', trigger: 'blur'},
    {min: 3, message: '用户名长度至少3个字符', trigger: 'blur'},
  ],
  password: [{required: true, message: '密码不能为空', trigger: 'blur'},
    {min: 3,  message: '密码长度至少3个字符', trigger: 'blur'},
  ],
  protectQuestion: [{required: true, message: '问题不能为空', trigger: 'blur'},
  ],
  protectAnswer: [{required: true, message: '答案不能为空', trigger: 'blur'},
  ],
})
const rules = reactive({
  username: [{required: true, message: '用户名不能为空', trigger: 'blur'},
    {min: 3, message: '用户名长度至少3个字符', trigger: 'blur'},
  ],
  password: [{required: true, message: '密码不能为空', trigger: 'blur'},
    {min: 3,  message: '密码长度至少3个字符', trigger: 'blur'},
  ]
})
const rules1 = reactive({
  username: [{required: true, message: '用户名不能为空', trigger: 'blur'},
    {min: 3,  message: '用户名长度至少3个字符', trigger: 'blur'},
  ],
  protectQuestion: [{required: true, message: '问题不能为空', trigger: 'blur'},
  ],
  protectAnswer: [{required: true, message: '答案不能为空', trigger: 'blur'},
  ]
})
const adminrules = reactive({
  adminName: [{required: true, message: '账户名不能为空', trigger: 'blur'},
    {min: 3,  message: '用户名长度至少3个字符', trigger: 'blur'},
  ],
  password: [{required: true, message: '密码不能为空', trigger: 'blur'},
    {min: 3,  message: '密码长度至少3个字符', trigger: 'blur'},
  ]
})
const ruleFormRef = ref()
const ruleFormRefRegister = ref()
const ruleFormRefForget = ref()
const ruleFormRefAdmin = ref()
const submitForm = () => {
  logout();
  logoutadmin();
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      login(userForm).then((res) => {
        console.log(res)
        const user = res.data
        if (res.data.status===1){
          ElMessage.warning("登录成功,曾检测到异常登录，如非本人，请尽快更改账户密码及密保")
        }
        else{ElMessage.success("登录成功")}
        store.commit("saveUsernameAndId", user)
        //   跳转页面
        router.push({path: "/main"})
      })
    }
  })
}
const submitFormadmin = () => {
  logout();
  logoutadmin();
  ruleFormRefAdmin.value.validate((valid) => {
    if (valid) {
      loginadmin(adminForm).then((res) => {
        console.log(res)
        const admin = res.data
        ElMessage.success("登录成功")
        store.commit("saveAdminnameAndId", admin)
        //   跳转页面
        router.push({path: "/admin"})
      })
    }
  })
}
const submitFormRegister = () => {
  ruleFormRefRegister.value.validate((valid) => {
    if (valid) {
      register(userFormRegister).then((res) => {
        console.log(res)
        const user = res.data
        ElMessage.success("注册成功")
        //   跳转页面
        location.reload()
      })
    }
  })
}
const submitFormForget = () => {
  ruleFormRefForget.value.validate((valid) => {
    if (valid) {
      findpassword(form).then((res) => {
        console.log(res)
        const user = res.data
        ElMessage.success("成功,密码:" + user.password + ",请牢记")
        //   跳转页面
        dialogFormVisible.value = false;
      })
    }
  })
}
const loginpage = ref()
const registerpage = ref()
const adminpage = ref()
onMounted(() => {
  loginpage.value.style.display = 'inline';
  registerpage.value.style.display = 'none';
  adminpage.value.style.display = 'none';
})
const toregister = () => {
  loginpage.value.style.display = 'none';
  registerpage.value.style.display = 'inline';
  adminpage.value.style.display = 'none';
}
const tologin = () => {
  loginpage.value.style.display = 'inline';
  registerpage.value.style.display = 'none';
  adminpage.value.style.display = 'none';
}
const toadmin = () => {
  loginpage.value.style.display = 'none';
  registerpage.value.style.display = 'none';
  adminpage.value.style.display = 'inline';
}
</script>

<template>
  <section>
    <div class="box">
      <div class="square" style="--i:0;"></div>
      <div class="square" style="--i:1;"></div>
      <div class="square" style="--i:2;"></div>
      <div class="square" style="--i:3;"></div>
      <div class="square" style="--i:4;"></div>
      <div class="container">
        <div class="formlogin" ref="loginpage">
          <div class="login">
            <h2>登录</h2>
            <br>
            <el-form ref="ruleFormRef" :model="userForm" :rules="rules" :label-position="'left'" label-width="70px">
              <!--  prop的值，表单数据的key值，rules定义的key值，三者保持一致，校验规则就生效-->
              <el-form-item label="用户名" prop="username">
                <el-input v-model="userForm.username"/>
              </el-form-item>
              <br>
              <el-form-item label="密码" prop="password">
                <el-input v-model="userForm.password" type="password"/>
              </el-form-item>
              <br>
              <br>
              <el-form-item style="justify-content: center">
                <el-button id="login" type="primary" @click="submitForm" round size="large" style="text-align: center">
                  登录
                </el-button>
              </el-form-item>
            </el-form>
            <button class="forget" @click="dialogFormVisible = true">忘记密码?</button>
            <button class="forget" style="position: absolute;top:327px;left: 200px " @click="toadmin">管理员</button>
          </div>
          <div class="loginIntro">
            <h3>欢迎登录</h3>
            <p>登录以查看业务情况，购买产品及进行业务</p>
            <p>还没有账户？</p>
            <p>加入我们！</p>
            <br>
            <button class="register" id="toregister" @click="toregister">注册</button>
          </div>
          <el-dialog
              v-model="dialogFormVisible"
              title="查看密码">
            <el-form :model="form" :rules="rules1" ref="ruleFormRefForget" :label-position="'left'" label-width="70px">
              <el-form-item label="用户名">
                <el-input v-model="form.username"/>
              </el-form-item>
              <br>
              <el-form-item label="密保问题">
                <el-input v-model="form.protectQuestion"/>
              </el-form-item>
              <br>
              <el-form-item label="密保答案">
                <el-input v-model="form.protectAnswer"/>
              </el-form-item>
              <br>
              <el-form-item style="justify-content: center">
                <el-button id="login" type="primary" @click="submitFormForget" round size="large"
                           style="text-align: center">查看密码
                </el-button>
              </el-form-item>
            </el-form>
            <br>
            <el-dialog
                v-model="innerVisible"
                width="30%"
                title="Inner Dialog"
                append-to-body
            />
            <template #footer>
      <span class="dialog-footer">
      </span>
            </template>
          </el-dialog>
        </div>
        <div class="formlogin" ref="registerpage">
          <div class="register">
            <h2>注册</h2>
            <br>
            <el-form ref="ruleFormRefRegister" :model="userFormRegister" :rules="rulesRegister" label-width="80px"
                     label-position="left">
              <!--  prop的值，表单数据的key值，rules定义的key值，三者保持一致，校验规则就生效-->
              <el-form-item label="用户名" prop="username">
                <el-input v-model="userFormRegister.username"/>
              </el-form-item>
              <br>
              <el-form-item label="密码" prop="password">
                <el-input v-model="userFormRegister.password" type="password"/>
              </el-form-item>
              <br>
              <el-form-item label="密保问题" prop="protectQuestion">
                <el-input v-model="userFormRegister.protectQuestion"/>
              </el-form-item>
              <br>
              <el-form-item label="答案" prop="protectAnswer">
                <el-input v-model="userFormRegister.protectAnswer"/>
              </el-form-item>
              <br>
              <el-form-item style="justify-content: center">
                <el-button id="login" type="primary" @click="submitFormRegister" round size="large"
                           style="text-align: center">注册
                </el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="registerIntro">
            <h3>欢迎注册</h3>
            <br>
            <p>注册以购买产品及开始业务</p>
            <p>已经有账户？</p>
            <p>现在登录！</p>
            <br>
            <button class="login" @click="tologin">登录</button>
          </div>
        </div>
        <div class="formlogin" ref="adminpage">
          <div class="login">
            <h2>管理员登录</h2>
            <br>
            <el-form ref="ruleFormRefAdmin" :model="adminForm" :rules="adminrules" :label-position="'left'"
                     label-width="70px">
              <!--  prop的值，表单数据的key值，rules定义的key值，三者保持一致，校验规则就生效-->
              <el-form-item label="用户名" prop="adminName">
                <el-input v-model="adminForm.adminName"/>
              </el-form-item>
              <br>
              <el-form-item label="密码" prop="password">
                <el-input v-model="adminForm.password" type="password"/>
              </el-form-item>
              <br>
              <br>
              <el-form-item style="justify-content: center">
                <el-button id="login" type="primary" @click="submitFormadmin" round size="large"
                           style="text-align: center">登录
                </el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="loginIntro">
            <h3>欢迎登录</h3>
            <p>登录以查看业务情况和管理用户</p>
            <p></p>
            <p></p>
            <br>
            <button id="tologin" @click="tologin">返回用户端</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

body {
  overflow: hidden;
  /* 渐变背景 */
}

section {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: rgba(223, 241, 255, 0.99);
}


.box {
  position: relative;
}

.box .square {
  position: absolute;
  backdrop-filter: blur(5px);
  box-shadow: 0 25px 45px rgb(0, 0, 0, 0.1);
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  background: rgb(255, 255, 255, 0.1);
  border-radius: 10px;
  animation: animate 10s linear infinite;
  animation-delay: calc(-1s * var(--i));
}

/* 动画 */

@keyframes animate {
  0%, 100% {
    transform: translateY(-40px);
  }
  50% {
    transform: translate(40px);
  }
}

.box .square:nth-child(1) {
  top: -50px;
  right: -60px;
  width: 100px;
  height: 100px;
}

.box .square:nth-child(2) {
  top: 150px;
  left: -100px;
  width: 120px;
  height: 120px;

}

.box .square:nth-child(3) {
  bottom: 50px;
  right: -60px;
  width: 80px;
  height: 80px;

}

.box .square:nth-child(4) {
  bottom: -80px;
  left: 100px;
  width: 50px;
  height: 50px;
}

.box .square:nth-child(5) {
  top: -90px;
  left: 140px;
  width: 60px;
  height: 60px;
}

.container {
  position: relative;
  width: 800px;
  min-height: 500px;
  background: rgb(255, 255, 255, 0.1);
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  backdrop-filter: blur(5px);
  box-shadow: 0 25px 45px rgb(0, 0, 0, 0.1);
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
}

.formlogin {
  position: relative;
  height: 100%;
  width: 100%;
  padding: 40px;
}

.formlogin h2 {
  position: relative;
  width: 100%;
  color: #5b5454;
  font-size: 30px;
  font-weight: 100;
  letter-spacing: 1px;
  margin-bottom: 40px;
}


.formlogin .inputBox {
  width: 100%;
  margin-top: 20px;
}

.formlogin .inputBox input {
  width: 100%;
  background: rgb(255, 255, 255, 0.2);
  outline: none;
  padding: 10px 20px;
  border-radius: 35px;
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  font-size: 16px;
  letter-spacing: 1px;
  color: #000000;
  box-shadow: 0 5px 15px rgb(0, 0, 0, 0.05);
}

.formlogin .inputBox input::placeholder {
  color: #000000;
}

#login {
  background: #fff;
  color: #666;
  width: 100px;
  margin-bottom: 20px;
  font-weight: 100;
}


#login:hover {
  background-color: #62a4ad;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.login {
  width: 50%;
  left: 0;
  top: 0;
  padding-right: 20px;
  padding-left: 20px;
  align-items: center;
}

.checkBox {
  width: 50%;
  padding-top: 20px;
}

.loginIntro {
  position: absolute;
  right: 0;
  top: 0;
  width: 50%;
  padding-top: 45px;
  padding-left: 20px;
  padding-right: 20px;
  z-index: 2;
  transition: 0.5s ease-in-out;
}

.loginIntro h3 {
  position: relative;
  width: 100%;
  color: #2c8ae8;
  font-size: 30px;
  font-weight: 100;
  letter-spacing: 1px;
  margin-bottom: 40px;
}

.loginIntro button {
  outline: none;
  padding: 10px 20px;
  border-radius: 35px;
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  box-shadow: 0 5px 15px rgb(0, 0, 0, 0.05);
  font-size: 16px;
  letter-spacing: 1px;
  background: #fff;
  color: #666;
  width: 100px;
  cursor: pointer;
  margin-bottom: 20px;
  font-weight: 100;
}

.forget {
  outline: none;
  padding: 7px 14px;
  border-radius: 35px;
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  box-shadow: 0 5px 15px rgb(0, 0, 0, 0.05);
  font-size: 14px;
  letter-spacing: 1px;
  background: #fff;
  color: #666;
  width: 100px;
  cursor: pointer;
  font-weight: 100;
  margin-left: 70px;
}

.forget:hover {
  background-color: #ff5900;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.loginIntro button:hover {
  background-color: #2aabd2;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.loginIntro p {
  padding-bottom: 10px;
  color: #5b5454;
  font-size: 20px;
  font-weight: 100;
  letter-spacing: 1px;
}

.register {
  width: 50%;
  left: 0;
  top: 0;
  padding-right: 20px;
  padding-left: 20px;
  align-items: center;
}

.registerIntro {
  position: absolute;
  right: 0;
  top: 0;
  width: 50%;
  padding-top: 45px;
  padding-left: 40px;
  padding-right: 20px;
  z-index: 2;
  transition: 0.5s ease-in-out;
}

.registerIntro h3 {
  position: relative;
  width: 100%;
  color: #2c8ae8;
  font-size: 30px;
  font-weight: 100;
  letter-spacing: 1px;
  margin-bottom: 40px;
}

.registerIntro button {
  outline: none;
  padding: 10px 20px;
  border-radius: 35px;
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  box-shadow: 0 5px 15px rgb(0, 0, 0, 0.05);
  font-size: 16px;
  letter-spacing: 1px;
  background: #fff;
  color: #666;
  width: 100px;
  cursor: pointer;
  margin-bottom: 20px;
  font-weight: 100;
}

.registerIntro button:hover {
  background-color: #2aabd2;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.registerIntro p {
  padding-bottom: 10px;
  color: #5b5454;
  font-size: 20px;
  font-weight: 100;
  letter-spacing: 1px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

#tologin {
  outline: none;
  padding: 10px 20px;
  border-radius: 35px;
  border: 1px solid rgb(255, 255, 255, 0.5);
  border-right: 1px solid rgb(255, 255, 255, 0.2);
  border-bottom: 1px solid rgb(255, 255, 255, 0.2);
  box-shadow: 0 5px 15px rgb(0, 0, 0, 0.05);
  font-size: 16px;
  letter-spacing: 1px;
  background: #fff;
  color: #666;
  width: 200px;
  cursor: pointer;
  margin-bottom: 20px;
  font-weight: 100;
}

#tologin:hover {
  background-color: #2aabd2;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}
</style>