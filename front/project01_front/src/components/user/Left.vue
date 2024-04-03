<script setup>

import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {onMounted, reactive, ref} from "vue";
import {allbuycnt, allbuysum, changeMoney, findBuy, money, sellProduct} from "@/api/user";
import {useStore} from "vuex";
import {CircleCloseFilled, Warning} from "@element-plus/icons-vue";

const visible = ref(false)
const visible1 = ref(false)
const visible2 = ref(false)
const store = useStore()
const search = ref('')
const left = ref(0)
const buysum = ref(0)
const buycnt = ref(0)
const form = reactive({addmoney: 0})
const form1 = reactive({reducemoney: 0})
const change = reactive({
  userid: '',
  balance: 0
})
const sellData = reactive(
  {
    userid: '',
    productId: '',
    productName: '',
    buyDate: '',
    left:'',
    money: '',
    yield: ''
  }
)
const filterTableData = ref([
  {
    productId: 'null',
    productName: 'null',
    yield: '0.000',
    money: '0.00',
    buyDate: 'null'
  }
])
onMounted(() => {
  money(store.getters.getId).then((res) => {
    console.log(res.data);
    left.value = res.data.balance;
  })
  allbuysum(store.getters.getId).then((res) => {
    console.log(res.data);
    buysum.value = res.data;
  })
  allbuycnt(store.getters.getId).then((res) => {
    console.log(res.data);
    buycnt.value = res.data;
  })
  findBuy(store.getters.getId).then((res) => {
    console.log(res.data);
    filterTableData.value = res.data;
  })
})
const onSubmit = () => {
  change.userid = store.getters.getId;
  change.balance = left.value + form.addmoney;
  changeMoney(change).then((res) => {
    console.log(res.data);
    ElMessage.success(res.msg);
    money(store.getters.getId).then((res) => {
      left.value = res.data.balance;
    })
  })
}
const onSubmit1 = () => {
  change.userid = store.getters.getId;
  change.balance = left.value - form1.reducemoney;
  changeMoney(change).then((res) => {
    console.log(res.data);
    ElMessage.success(res.msg);
    money(store.getters.getId).then((res) => {
      console.log(res.data);
      left.value = res.data.balance;
    })
  })
}
const onSubmit2 = () => {
  console.log(sellData);
  sellProduct(sellData).then((res)=>{
    console.log(res.data);
    ElMessage.success("卖出成功");
    money(store.getters.getId).then((res) => {
      console.log(res.data);
      left.value = res.data.balance;
    })
    allbuysum(store.getters.getId).then((res) => {
      console.log(res.data);
      buysum.value = res.data;
    })
    allbuycnt(store.getters.getId).then((res) => {
      console.log(res.data);
      buycnt.value = res.data;
    })
    findBuy(store.getters.getId).then((res) => {
      console.log(res.data);
      filterTableData.value = res.data;
    })
  })
  visible2.value=false;
}
const handleDelete = (data) => {
  console.log(data);
  sellData.userid = data.userid;
  sellData.productId = data.productId;
  sellData.buyDate = data.buyDate;
  sellData.money = data.money;
  sellData.yield = data.yield;
  money(store.getters.getId).then((res) => {
    console.log(res.data);
    sellData.left = res.data.balance;
  })
  money(store.getters.getId).then((res) => {
    sellData.left = res.data.balance;
  })
  visible2.value=true;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    已购买&余额
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">账户余额</div>
  <el-row :gutter="16" style="margin-bottom: 60px">
    <el-col :span=8>
      <div class="statistic-card">
        <el-statistic :value="left" precision="2">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              账户余额
              <el-tooltip
                  effect="dark"
                  content="您的账户现在剩余的未购买理财的可自由转入转出金额"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning/>
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
      </div>
    </el-col>
    <el-col :span=8>
      <div class="statistic-card">
        <el-statistic :value="buysum" precision="2">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              在理财产品中金额
              <el-tooltip
                  effect="dark"
                  content="在理财产品中正在获取收益，但是不可随意转出的部分"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning/>
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
      </div>
    </el-col>
    <el-col :span=8>
      <div class="statistic-card">
        <el-statistic :value="buycnt">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              已购买理财产品份数
              <el-tooltip
                  effect="dark"
                  content="您一共购买了多少份理财产品"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning/>
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
      </div>
    </el-col>
  </el-row>
  <el-button @click="visible = true" type="primary" round size="large" style="margin-right: 40px">
    充值
  </el-button>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">充值</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="form" label-width="120px">
      <el-form-item label="转入金额">
        <el-input-number v-model="form.addmoney" :precision="2" :step="0.1" :min="0"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>

  </el-drawer>
  <el-button @click="visible1 = true" type="primary" round size="large">
    转出
  </el-button>
  <el-drawer v-model="visible1" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">转出</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="form1" label-width="120px">
      <el-form-item label="转出金额">
        <el-input-number v-model="form1.reducemoney" :precision="2" :step="0.1" :min="0" :max="left"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit1">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">已购买的产品</div>
  <el-table
      :data="filterTableData.filter(data => !search||data.productName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%;margin-top: 40px">
    <el-table-column label="日期" prop="buyDate" width="200" sortable/>
    <el-table-column label="产品id" prop="productId" width="100" sortable/>
    <el-table-column label="产品名" prop="productName" width="300" sortable/>
    <el-table-column label="利率" prop="yield" width="150" sortable/>
    <el-table-column label="金额" prop="money" width="150" sortable/>
    <el-table-column align="right" fixed="right" min-width="220">
      <template #header>
        <el-input v-model="search" size="default" style="width: 200px;" placeholder="搜索产品名"/>
      </template>
      <template #default="scope">
        <el-button
            size="large"
            type="danger"
            @click="handleDelete(scope.row)"
        >卖出
        </el-button>

        <!--        @click留scope.row(这是表格数据)-->
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible2" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">卖出产品</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="sellData" ref="ruleFormRef" label-width="120px">
      <el-form-item label="用户编号">
        <el-input v-model="sellData.userid" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品id">
        <el-input v-model="sellData.productId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input v-model="sellData.yield" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="购买时间">
        <el-col :span="11">
          <el-date-picker
              v-model="sellData.buyDate"
              readonly="readonly"
              type="datetime"
              placeholder="Select date and time"
              format="YYYY-MM-DD HH:mm:ss"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="售出金额">
        <el-input-number v-model="sellData.money" :precision="2" :step="0.1" :min="0"  readonly="readonly"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit2">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>
:global(h2#card-usage ~ .example .example-showcase) {
  background-color: var(--el-fill-color) !important;
}

.el-statistic {
  --el-statistic-title-font-size: 20px;
  --el-statistic-content-font-size: 30px;
}

.statistic-card {
  height: 100%;
  padding: 20px;
  border-radius: 4px;
  background-color: var(--el-bg-color-overlay);
}

.statistic-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  font-size: 12px;
  color: var(--el-text-color-regular);
  margin-top: 16px;
}

.statistic-footer .footer-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistic-footer .footer-item span:last-child {
  display: inline-flex;
  align-items: center;
  margin-left: 4px;
}

.green {
  color: var(--el-color-success);
}

.red {
  color: var(--el-color-error);
}
</style>