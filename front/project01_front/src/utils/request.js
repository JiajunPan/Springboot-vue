import axios from 'axios'
import {ElMessageBox, ElMessage} from 'element-plus'
import qs from 'qs'

// axios创建实例对象
const service = axios.create({
    // 设置baseRUL
    //baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    // 允许axios请求带上cookie
    withCredentials: true, // send cookies when cross-domain requests
    //设置header的Content-Type类型
    headers: {"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"},
    // 主要用于序列化params（params 参数用在get请求上）
    paramsSerializer: params => {
        if (params) {
            //qs.stringify 的{arrayFormat: 'repeat'} 可以对数组转换格式处理成 ids=1&ids=2
            return qs.stringify(params, {arrayFormat: 'repeat'});
        }
    },
    /* 设置请求超时时间*/
    timeout: 30000, // request timeout
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    // 只能用在 'PUT', 'POST' 和 'PATCH' 这几个请求方法
    transformRequest: (data, headers) => {
        if (headers['Content-Type']) {
            if ((headers['Content-Type']).indexOf('multipart/form-data') > -1) { // 上传文件处理
                return data
            } else if ((headers['Content-Type']).indexOf('application/json') > -1) { // 请求json数据格式处理
                return JSON.stringify(data)
            } else { // 其他都是按照 x-www-form-urlencoded数据格式处理
                //qs.stringify 的{arrayFormat: 'repeat'} 可以对数组转换格式处理成 ids=1&ids=2
                return qs.stringify(data, {arrayFormat: 'repeat'})
            }
        } else { // headers['Content-Type'] 不存在时，按照 x-www-form-urlencoded 数据格式处理
            //qs.stringify 的{arrayFormat: 'repeat'} 可以对数组转换格式处理成 ids=1&ids=2
            return qs.stringify(data, {arrayFormat: 'repeat'})
        }
    }
})

// response 拦截器
service.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        const res = response.data

        // if the custom code is not 20000, it is judged as an error.
        if (res.status > 0) {
            console.log(res.msg)
            ElMessage.error(res.msg || "系统出错");
            return new Promise(() => {
            });//此时的promise链停下来了
        } else if (res.status === -1) { // 未登录或者登录过期
            // to re-login
            ElMessageBox.alert('登录已经过期，请重新登录', '登录过期', {
                confirmButtonText: '重新登录',
                callback: () => {
                    //跳转登录页面
                    window.location.href = "/";
                },
            })
            //此时的promise链停下来了
            return new Promise(() => {
            })
        } else {
            //成功 正确数据
            return res
        }
    },
    error => {
        console.log('err' + error) // for debug
        ElMessage.error(error.msg)
        //此时的promise链停下来了
        return new Promise(() => {
        });
    }
)

export default service
