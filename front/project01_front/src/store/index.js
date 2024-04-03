import { createStore } from 'vuex'
import user from '@/store/user'
import createPersistedState from 'vuex-persistedstate'
import admin from "@/store/admin";
export default createStore({
  // 1.存储所有全局数据
  state: {
    count: 0
  },
  // 2.需要通过计算获取state里的内容获取数据
  // 只能读取不能修改
  getters: {
    // 定义函数，其他组件可以直接调用getCount函数获取state中的值，好处 防止直接修改state值
    getCount(state){
      return state.count
    }
  },
  // 3.定义对state各种操作，不能执行异步操作，简单直接赋值操作
  mutations: {
    // 定义函数，作用 修改state中变量的值
    addCount(state,num) {
      state.count=state.count+num
    },
    subCount(state,num){
      if(state.count>=num){
        state.count=state.count-num
      }else {
        state.count=0
      }
    }
  },
  // 4.定义对state各种操作，action无法直接修改state，需要在mutation里更新，
  // mutation不支持异步，所以需要在action里请求后端api
  actions: {
    myAddCount(context,num){
      // context.commit("addCount",num)
      // 模拟异步操作
      setTimeout(()=>{
        // 提交addCount
        context.commit("addCount",num)
      },2000)
    },
    mySubCount(context,num){
      context.commit("subCount",num)
    },
  },
  // 5.加载多个state文件，防止一个state文件过长
  modules: {
    user,admin
  },
  // 6.插件
  plugins:[
    createPersistedState({
      // 存储方式是sessionStorage
      storage:sessionStorage,
      paths:["count","user","admin"]
      // reducer(val){
      //   return{
      //     count:val.count,
      //    id:val.user.id
      //   }
      // }
    })
  ]
})
