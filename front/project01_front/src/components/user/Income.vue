<script setup>
import {onMounted, reactive, ref} from 'vue'
import {useStore} from "vuex";
import {computeIncome, expectIncome, findBuy, money} from "@/api/user";
import {Warning} from "@element-plus/icons-vue";
import * as echarts from "echarts";
const pieData =[]
const income=ref(0)
const expect=ref(0)
const store = useStore()
const left = ref(0)
var xset=[];
var yset=[];
onMounted(() => {
  findBuy(store.getters.getId).then((res) => {
    for(var i=0;i<res.data.length;i++){
      pieData.push({
        name: res.data[i].productName,
        value: res.data[i].money,
      });
      xset.push(res.data[i].productName);
      yset.push(res.data[i].money);
    }
    let myChart1=echarts.init(document.getElementById('myChart'))
    myChart1.setOption(
        {
          title: {
            text: '您的理财产品组成',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '10%',
            left: 'left'
          },
          series: [
            {
              name: '项目',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 15,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: pieData
            }
          ]
        }
    )
    let myChart = echarts.init(document.getElementById('myChart1'))
    // 绘制图表
    myChart.setOption({
      title: {
        text: '您的购买金额',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          data:xset,
          axisTick: {
            alignWithLabel: true
          }
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '项目',
          type: 'bar',
          barWidth: '60%',
          data: yset
        }
      ]
    });
    console.log("piedata.......")
    console.log(pieData);
    console.log(res.data);
  })

  console.log("x........")
  console.log(xset)
  console.log("y.........")
  console.log(yset)



  money(store.getters.getId).then((res) => {
    console.log(res.data);
    left.value = res.data.balance;
  })
  computeIncome(store.getters.getId).then((res) => {
    console.log(res.data);
    income.value = res.data.sumMoney;
  })
  expectIncome(store.getters.getId).then((res) => {
    console.log(res.data);
    expect.value = res.data.sumMoney;
  })
})
</script>
<template>
  <div style="text-align:left;font-size: 50px;margin-bottom: 50px">
    收益
  </div>
  <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">您的收益</div>
  <br>
  <el-row :gutter="16" style="margin-bottom: 60px">
    <el-col :span=8>
      <div class="statistic-card">
        <el-statistic :value="income" precision="2">
          <template #title>
            <div style="display: inline-flex; align-items: center;">
              已获得收益
              <el-tooltip
                  effect="dark"
                  content="您的账户现在拥有的理财产品获得的收益(可能由于产品性质不同转出有限制)"
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
        <el-statistic :value="expect" precision="2">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              预期收益
              <el-tooltip
                  effect="dark"
                  content="预计现有理财产品的年化收益"
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
  <div style="display: inline-block;width: 100%;height: 400px;margin-top: 70px;margin-bottom: 20px">
    <div id="myChart" style="display: inline-block;width: 500px;height: 400px"/>
    <div id="myChart1" style="display: inline-block;width: 500px;height: 400px"/>
  </div>

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