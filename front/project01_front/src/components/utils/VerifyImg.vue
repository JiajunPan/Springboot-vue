<script setup>
import {reactive,onMounted,ref} from 'vue';
//定义属性,在父组件中可以传递验证码的宽度和高度
const props = defineProps({
  width:String,
  height:String,
});
//获得模板中画布的引用
const verify = ref(null);
const state = reactive({
  pool:"ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",//字符串库
  width:props.width,
  height:props.height,
  imgCode:''//最终生成的验证码内容
});

//获得随机颜色
const randomColor = (min,max) =>{
  const r = randomNum(min,max);
  const g = randomNum(min,max);
  const b = randomNum(min,max);
  // console.log("rgb="+r+":"+g+":"+b);
  return 'rgb('+r+','+g+','+b+')';
}
//绘制验证码的方法
const draw=()=>{
  const ctx = verify.value.getContext('2d');
  ctx.fillStyle = randomColor(180,230);
  console.log(ctx.fillStyle)
  ctx.fillRect(0,0,state.width,state.height);
  let imgCode = '';//存储产生的随机字符
  for(let i=0;i<4;i++){
    const text = state.pool[randomNum(0,state.pool.length)];
    imgCode +=text;
    //设置字体大小的范围
    const fontSize = randomNum(25,40);
    //设置字体大小
    ctx.font=fontSize + 'px Simhei';
    ctx.textBaseLine = 'top';
    //设置字体颜色范围
    ctx.fillStyle = randomColor(50,150);
    //保存上面画布的设置
    ctx.save();
    //移动画布坐标的原点,以15为开始值,每次先后移动30个像素,绘制下一个字符
    ctx.translate(30*i+15,15);
    //旋转画布的原点,使文字产生旋转的效果
    const deg = randomNum(-45,45);
    ctx.rotate((deg*Math.PI)/180);
    //在画布上写上文字
    ctx.fillText(text,-10,15);
    //还原画布的设置,以便重新设置画布.
    ctx.restore();
  }
  // console.log(imgCode);
  //绘制干扰线
  for(let i=0;i<5;i++){
    ctx.beginPath();
    ctx.moveTo(randomNum(0,state.width),randomNum(0,state.height));
    ctx.lineTo(randomNum(0,state.width),randomNum(0,state.height));
    ctx.strokeStyle = randomColor(180,230);
    ctx.closePath();
    ctx.stroke();
  }
  //绘制干扰点
  for(let i=0;i<40;i++){
    ctx.beginPath();
    ctx.arc(randomNum(0,state.width),randomNum(0,state.height),1,0,2*Math.PI);
    ctx.closePath();
    ctx.fillStyle=randomColor(150,200);
    ctx.fill();
  }
  return imgCode;
}
//挂起的时候,绘图并获得验证码
onMounted(()=>{
  state.imgCode = draw();
});

//点击事件响应的方法
const handleDraw = ()=>{
  state.imgCode = draw();
}
//获得一个在规定范围内的随机函数
const randomNum = (min,max) =>{
  return parseInt(Math.random()*(max-min)+min);
}
</script>

<template>
  <div class="img-verify"></div>
  <canvas ref="verify" :width="width" :height="height" @click="handleDraw"></canvas>
</template>
<style type="text/css">
.img-verify canvas{
  cursor:pointer;
}
</style>