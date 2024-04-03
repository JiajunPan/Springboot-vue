<script setup>
import {useStore} from "vuex";
import {onMounted, ref} from "vue";
import {
  buyConditionCnt,
  buyConditionSum,
  maxProduct,
  maxUser,
  minProduct,
  sellConditionCnt,
  sellConditionSum
} from "@/api/admin";
import * as echarts from "echarts";

const store = useStore()
const sellSumx=[];
const sellSumy=[];

const sellSum=[];


const buySumx=[];
const buySumy=[];

const buySum=[];


const sellCntx=[];
const sellCnty=[];


const buyCntx=[];
const buyCnty=[];
const maxUserid=ref(0);
const maxUserSumMoney=ref(0);
const maxProductid=ref(0);
const maxProductSumMoney=ref(0);
const minProductid=ref(0);
const minProductSumMoney=ref(0);
onMounted(()=>{
  sellConditionSum().then((res)=>{
    for(var i=0;i<res.data.length;i++){
      sellSum.push({
        name: res.data[i].productName,
        value: res.data[i].sumMoney,
      });
      sellSumx.push(res.data[i].productName);
      sellSumy.push(res.data[i].sumMoney);
    }
    let myChart=echarts.init(document.getElementById('sellsump'))
    myChart.setOption({
          title: {
            text: '产品卖出占比',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            type:'scroll',
            bottom:10
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
              data: sellSum
            }
          ]
        });
    let myChart1 = echarts.init(document.getElementById('sellsum'))
    // 绘制图表
    myChart1.setOption({
      title: {
        text: '产品卖出金额',
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
          data:sellSumx,
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
          data: sellSumy
        }
      ]
    });
  })
  sellConditionCnt().then((res)=>{
    for(var i=0;i<res.data.length;i++){
      sellCntx.push(res.data[i].productName);
      sellCnty.push(res.data[i].cnt);
    }
    let myChart2 = echarts.init(document.getElementById('sellcnt'))
    // 绘制图表
    myChart2.setOption({
      title: {
        text: '产品卖出份数',
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
          data:sellCntx,
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
          data: sellCnty
        }
      ]
    });
  })
  buyConditionSum().then((res)=>{
    for(var i=0;i<res.data.length;i++){
      buySum.push({
        name: res.data[i].userid,
        value: res.data[i].sumMoney,
      });
      buySumx.push(res.data[i].userid);
      buySumy.push(res.data[i].sumMoney);
    }
    let myChart3=echarts.init(document.getElementById('buysump'))
    myChart3.setOption({
          title: {
            text: '用户购买金额占比',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            type:'scroll',
            bottom:10
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
              data: buySum
            }
          ]
        });
    let myChart4 = echarts.init(document.getElementById('buysum'))
    // 绘制图表
    myChart4.setOption({
      title: {
        text: '用户的购买金额',
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
          data:buySumx,
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
          data: buySumy
        }
      ]
    });
  })
  buyConditionCnt().then((res)=>{
    for(var i=0;i<res.data.length;i++){
      buyCntx.push(res.data[i].userid);
      buyCnty.push(res.data[i].cnt);
    }
    let myChart5 = echarts.init(document.getElementById('buycnt'))
    // 绘制图表
    myChart5.setOption({
      title: {
        text: '用户购买份数',
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
          data:buyCntx,
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
          data: buyCnty
        }
      ]
    });
  })
  maxUser().then((res)=>{
    maxUserid.value=res.data.userid;
    maxUserSumMoney.value=res.data.sumMoney;
  })
  maxProduct().then((res)=>{
maxProductid.value=res.data.productId;
maxProductSumMoney.value=res.data.sumMoney;
  })
  minProduct().then((res)=>{
minProductid.value=res.data.productId;
minProductSumMoney.value=res.data.sumMoney;
  })
})
</script>

<template>
  <div style="text-align:left;font-size: 50px">
    统计
  </div>
    <div style="font-size: 28px; margin-top: 80px;margin-bottom: 30px">你好,{{ store.getters.getAdminName }}</div>
    <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">你可以在这里查看统计数据</div>
    <table style="height: 1500px;width: 100%;text-align: center;
  align-items: center;
  justify-content: center;">
      <tr>
        <td><div id="sellsum" style="width: 600px;height: 400px"></div></td>
        <td><div id="sellsump" style="width: 600px;height: 400px"></div></td>
      </tr>
      <tr>
        <td><div id="buysum" style="width: 600px;height: 400px"></div></td>
        <td><div id="buysump" style="width: 600px;height: 400px"></div></td>
      </tr>
      <tr>
        <td><div id="sellcnt" style="width: 600px;height: 400px"></div></td>
        <td><div id="buycnt" style="width: 600px;height: 400px"></div></td>
      </tr>
    </table>
<table style="height: 500px;width: 100%;text-align: center;
  align-items: center;
  justify-content: center;">
  <tr>
    <td><div style="width: 400px;height: 400px">
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">买的最多的用户是</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">用户id：{{maxUserid}}</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">购买金额：{{maxUserSumMoney}}</div>
    </div></td>
    <td><div style="width: 400px;height: 400px">
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">卖的最多的产品是</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">产品id：{{maxProductid}}</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">购买金额：{{maxProductSumMoney}}</div>
    </div></td>
    <td><div style="width: 400px;height: 400px">
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">卖的的最少的产品是</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">产品id：{{minProductid}}</div>
      <div style="font-size: 28px; margin-top: 20px;margin-bottom: 30px">购买金额：{{minProductSumMoney}}</div>
    </div></td>
  </tr>
</table>
</template>

<style scoped>

</style>