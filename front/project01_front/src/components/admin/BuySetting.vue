<script setup>
import {ElButton, ElDrawer} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {allbuy, allInformation} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {findMoney, sellBuy} from "@/api/admin";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const search=ref('')
const userDataChange=reactive(
  {
    userid: "",
    productName: "",
    productId: "",
    buyDate: "",
    money: "",
    left:"",
    yield: "",
  }
)
const userData = ref([
  {
    userid: "",
    productName: "",
    productId: "",
    buyDate: "",
    money: "",
    yield: "",
  }
])
onMounted(() => {
 allbuy().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
const handleDelete=(data)=>{
userDataChange.userid=data.userid;
userDataChange.productName=data.productName;
userDataChange.productId=data.productId;
userDataChange.buyDate=data.buyDate;
findMoney(data.userid).then((res) => {
    console.log(res.data);
  userDataChange.left = res.data.balance;
  })
userDataChange.money=data.money;
userDataChange.yield=data.yield;
  visible.value=true;
  console.log(userDataChange)
}
const onSubmit=()=>{
sellBuy(userDataChange).then((res)=>{
  allbuy().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
  visible.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    购买信息管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理用户购买信息</div>

  <el-table :data="userData.filter(data => !search||data.productName.toLowerCase().includes(search.toLowerCase()))" style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="userid" label="用户id" width="95" sortable/>
    <el-table-column prop="productName" label="产品名" width="200" sortable/>
    <el-table-column prop="productId" label="产品id" width="95" sortable/>
    <el-table-column prop="buyDate" label="购买日期" width="200" sortable/>
    <el-table-column prop="money" label="购买金额" width="120" sortable/>
    <el-table-column prop="yield" label="利率" width="200" sortable/>
    <el-table-column align="right" fixed="right" min-width="220">
      <template #header>
        <el-input v-model="search" size="default"  style="width: 200px;" placeholder="搜索产品名"/>
      </template>
      <template #default="scope">
        <el-button size="default" type="danger" @click="handleDelete(scope.row)"
        >删除
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">删除购买信息（代卖出）</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="userDataChange" ref="ruleFormRef" label-width="120px">
      <el-form-item label="用户编号">
        <el-input v-model="userDataChange.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品id">
        <el-input v-model="userDataChange.productId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input v-model="userDataChange.yield" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="购买时间">
        <el-col :span="11">
          <el-date-picker
              v-model="userDataChange.buyDate"
              readonly="readonly"
              type="datetime"
              placeholder="Select date and time"
              format="YYYY-MM-DD HH:mm:ss"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="售出金额">
        <el-input-number v-model="userDataChange.money" :precision="2" :step="0.1" :min="0"  readonly="readonly"/>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="onSubmit">确认删除</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>