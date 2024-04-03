<script setup>
import {ElButton, ElDrawer, ElMessage} from "element-plus";
import {onMounted, reactive, ref} from 'vue'
import {allProduct} from "@/api/admin";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import {delproduct, newproduct, update} from "@/api/product";
import {useStore} from "vuex";

const store = useStore()
const visible = ref(false)
const visible1 = ref(false)
const visible2 = ref(false)
const search = ref('')
const userData = ref([
  {
    productId: "",
    productName: "",
    introduce: "",
    yield: ""
  }]
)
const productData = reactive(
  {
    productName: "",
    introduce: "",
    yield: 0
  }
)
const editData=reactive(
    {
      productId: "",
      productName: "",
      introduce: "",
      yield: ""
    }
)
const deleteData=reactive(
  {
    productId: "",
    productName: "",
    yield: ""
  }
)
onMounted(() => {
  allProduct().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
const handleEdit = (data) => {
editData.productId=data.productId;
editData.productName=data.productName;
editData.introduce=data.introduce;
editData.yield=data.yield;
  visible.value = true;
}
const handleDelete = (data) => {
deleteData.productId=data.productId;
deleteData.productName=data.productName;
deleteData.yield=data.yield;
  visible1.value = true;
}
const newProduct = () => {
  productData.productName='';
  productData.introduce='';
  productData.yield=0;
  visible2.value = true;
}
const onSubmit=()=>{
  update(editData).then((res)=>{
    console.log(res.data);
    ElMessage.success("成功");
  // 插入重新取值函数（onMounted）
    allProduct().then((res) => {
      console.log(res.data);
      userData.value = res.data;
    })
  })
  visible.value=false;
}
const onSubmit1=()=>{
  delproduct(deleteData.productId).then((res)=>{
    console.log(res.data);
    ElMessage.success("成功");
    // 插入重新取值函数（onMounted）
    allProduct().then((res) => {
      console.log(res.data);
      userData.value = res.data;
    })
  })
  visible1.value=false;
}
const onSubmit2=()=>{
  console.log(productData);
newproduct(productData).then((res)=>{
  console.log(res.data);
  ElMessage.success("成功");
  // 插入重新取值函数（onMounted）
  allProduct().then((res) => {
    console.log(res.data);
    userData.value = res.data;
  })
})
  visible2.value=false;
}
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    产品信息管理
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>

  <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里管理产品信息</div>

  <el-table :data="userData.filter(data => !search||data.productName.toLowerCase().includes(search.toLowerCase()))"
            style="width: 1500px;margin-top: 40px;font-size:medium" :row-style="{height: '80px'}">
    <el-table-column prop="productId" label="产品id" width="100" sortable/>
    <el-table-column prop="productName" label="产品名" width="300" sortable/>
    <el-table-column prop="yield" label="利率" width="150" sortable/>
    <el-table-column prop="introduce" label="介绍" width="200" sortable/>
    <el-table-column align="right" fixed="right" min-width="320">
      <template #header>
        <el-input v-model="search" size="default" style="width: 200px; margin-right: 20px" placeholder="搜索产品名"/>
        <el-button size="default" @click="newProduct">
          新建
        </el-button>
      </template>
      <template #default="scope">
        <el-button size="large" @click="handleEdit(scope.row)"
        >修改
        </el-button
        >
        <el-button size="large" type="danger" @click="handleDelete(scope.row)"
        >删除
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改产品</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="editData" label-width="120px">
      <el-form-item label="产品id">
        <el-input v-model="editData.productId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品名">
        <el-input v-model="editData.productName"/>
      </el-form-item>
      <el-form-item label="介绍">
        <el-input v-model="editData.introduce"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input-number v-model="editData.yield"  :step="0.00001" :precision="5" :min="0" :max="0.4"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible1" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">删除产品</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="deleteData" label-width="120px">
      <el-form-item label="产品id">
        <el-input v-model="deleteData.productId" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="产品名">
        <el-input v-model="deleteData.productName" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input-number v-model="deleteData.yield"  :step="0.00001" :precision="5" :min="0" :max="0.4" readonly="readonly"/>
      </el-form-item>
      <el-form-item label="确认删除？">
        此操作不可逆！
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="onSubmit1">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  <el-drawer v-model="visible2" :show-close="false">
    <template #header="{ close, titleId, titleClass}">
      <h4 :id="titleId" :class="titleClass">新增产品</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left">
          <CircleCloseFilled/>
        </el-icon>
        关闭
      </el-button>
    </template>
    <el-form :model="productData" label-width="120px">
      <el-form-item label="产品名">
        <el-input v-model="productData.productName" placeholder="请输入产品名"/>
      </el-form-item>
      <el-form-item label="介绍">
        <el-input v-model="productData.introduce" placeholder="请输入产品简介"/>
      </el-form-item>
      <el-form-item label="利率">
        <el-input-number v-model="productData.yield" :step="0.00001" :precision="5" :min="0" :max="0.4"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit2">确认</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>

</style>