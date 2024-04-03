const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // 解决跨域 使用前端代理
    proxy: {
      // 请求 以/api开始的url 进行代理请求
      // 原始请求 http//localhost:8080/api/user/login===>http//localhost:8081/user/login
      '/api': {
        target: "http://localhost:8081",//代理请求baseurl
        changeOrigin: true,//允许跨域
        // 路径重写
        pathRewrite: {
          '^/api': ''//api重写成空字符串
        }
      },
      // 原始请求 http//localhost:8080/api/user/login===>http//localhost:8081/user/login
      '/image': {
        target: "http://localhost:8081",//代理请求baseurl
        changeOrigin: true,//允许跨域
        // pathRewrite: {
        //     '^/api': ''//api重写成空字符串
        // }
      }
    }
  }
})