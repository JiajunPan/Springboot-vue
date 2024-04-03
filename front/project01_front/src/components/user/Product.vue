<script setup>
import {ElButton, ElMessage} from "element-plus";
import {computed, onMounted, reactive, ref} from 'vue'
import {all} from "@/api/product";
import {useStore} from "vuex";
import {buyProduct, changeMoney, money} from "@/api/user";
import {CircleCloseFilled} from "@element-plus/icons-vue";

const store = useStore();
const visible = ref();
const left = ref(0)
const tableData = ref([
  {
    productId: '',
    productName: '',
    introduce: '',
    yield: ''
  }
])
const buyData = reactive(
  {
    // userid: '',
    // productId: '',
    // productName: '',
    // buyDate: '',
    // left:0,
    // money: 0,
    // yield: 0
  })
const search = ref('')
onMounted(() => {
  money(store.getters.getId).then((res) => {
    console.log(res.data);
    left.value = res.data.balance;
  })
  all().then((res) => {
    console.log(res.data);
    tableData.value = res.data;
  })
})
const handleEdit = (data) => {
  buyData.yield = data.yield;
  money(store.getters.getId).then((res) => {
    console.log(res.data);
    buyData.left = res.data.balance;
  })
  const nowDate = new Date();
  const date = {
    year: nowDate.getFullYear(),
    month: nowDate.getMonth() + 1,
    date: nowDate.getDate(),
    hours: nowDate.getHours(),
    minutes: nowDate.getMinutes(),
    seconds: nowDate.getSeconds(),
  };
  const newmonth = date.month >= 10 ? date.month : "0" + date.month;
  const newday = date.date >=10 ? date.date : "0" + date.date;
  const newhours=date.hours>=10? date.hours:"0"+date.hours;
  const newminutes = date.minutes >= 10 ? date.minutes : "0" + date.minutes;
  const newseconds = date.seconds >= 10 ? date.seconds : "0" + date.seconds;
  buyData.userid = store.getters.getId;
  buyData.productId = data.productId;
  buyData.productName = data.productName;
  buyData.buyDate = date.year + "-" + newmonth + "-" + newday + " " + newhours + ":" + newminutes + ":" + newseconds;
  buyData.money=0;
  console.log("buyData........")
  console.log(buyData);
  visible.value = true;
}
const onSubmit = () => {
  console.log("onSubmit...............")
  console.log(buyData);
  buyProduct(buyData).then((res)=>{
    console.log(res.data);
    ElMessage.success(res.msg);
    money(store.getters.getId).then((res) => {
      console.log(res.data);
      left.value = res.data.balance;
    })
  })
visible.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px;margin-bottom: 50px">
    产品
  </div>
  <el-table :data="tableData.filter(data => !search||data.productName.toLowerCase().includes(search.toLowerCase()))"
            style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column fixed prop="productName" label="产品名" width="300" sortable/>
    <el-table-column prop="yield" label="利率" width="150" sortable/>
    <el-table-column prop="introduce" label="介绍" width="150" sortable/>
    <el-table-column prop="productId" label="产品编号" width="200" sortable/>
    <el-table-column align="right" fixed="right" min-width="220">
      <template #header>
        <el-input v-model="search" size="default" style="width: 200px;" placeholder="搜索产品名"/>
      </template>
      <template #default="scope">
        <el-button size="large" @click="handleEdit(scope.row)"
        >增持
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">增持产品</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="buyData" ref="ruleFormRef" label-width="120px">
      <el-form-item label="用户编号">
        <el-input v-model="buyData.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品id">
        <el-input v-model="buyData.productId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品名">
        <el-input v-model="buyData.productName" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input v-model="buyData.yield" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="购买时间">
        <el-col :span="11">
          <el-date-picker
              v-model="buyData.buyDate"
              readonly="readonly"
              type="datetime"
              placeholder="Select date and time"
              format="YYYY-MM-DD HH:mm:ss"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="购买金额">
        <el-input-number v-model="buyData.money" :precision="2" :step="0.1" :min="0"  :max="left"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>