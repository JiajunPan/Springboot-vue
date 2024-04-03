// request是一个axios对象(经过设置信息的)
import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/api/user/login',
        //post请求
        method: 'post',
        data
    })
}
export function logout() {
    return request({
        url: '/api/user/logout',
        //post请求
        method: 'post',
    })
}
export function register(data){
    return request({
        url: '/api/user/save',
        //post请求
        method: 'post',
        data
    })
}
export function findpassword(data){
    return request({
        url: '/api/user/forgetPassword',
        //post请求
        method: 'post',
        data
    })
}
export function changeInformation(data){
    return request({
        url: '/api/user/changeUserInformation',
        //post请求
        method: 'post',
        data
    })
}
export function changeMoney(data){
    return request({
        url: '/api/user/changeBalance',
        //post请求
        method: 'post',
        data
    })
}
export function saveInformation(data){
    return request({
        url: '/api/user/saveUserInformation',
        //post请求
        method: 'post',
        data
    })
}
export function allbuysum(id){
    return request({
        url: `/api/purchased/sumUser/${id}`,
        //post请求
        method: 'post',
    })
}
export function allbuycnt(id){
    return request({
        url: `/api/purchased/countUserBuy/${id}`,
        //post请求
        method: 'post',
    })
}
export function money(id){
    return request({
        url: `/api/user/balance/${id}`,
        //post请求
        method: 'post',
    })
}
export function update(data){
    return request({
        url: `/api/user/update`,
        method: 'post',
        data
    })
}
export function seeInformation(id){
    return request({
        url:`/api/user/userInformation/${id}`,
        //post请求
        method: 'post',

    })
}
export function allInformation(){
    return request({
        url:'/api/user/userInformation/all',
        //post请求
        method: 'post',

    })
}
export function allmoney(){
    return request({
        url:'/api/user/balance/all',
        //post请求
        method: 'post',

    })
}
export function allbuy(){
    return request({
        url:'/api/purchased/all',
        //post请求
        method: 'post',

    })
}
export function findBuy(id){
    return request({
        url:`/api/purchased/byUser/${id}`,
        //post请求
        method: 'post',

    })
}
export function computeIncome(id){
    return request({
        url:`/api/purchased/computeIncome/${id}`,
        //post请求
        method: 'post',

    })
}
export function expectIncome(id){
    return request({
        url:`/api/purchased/expectIncome/${id}`,
        //post请求
        method: 'post',

    })
}
export function sellProduct(data){
    return request({
        url:`/api/purchased/deleteByUser`,
        //post请求
        method: 'post',
        data
    })
}
export function buyProduct(data){
    return request({
        url:`/api/purchased/save`,
        //post请求
        method: 'post',
        data
    })
}








export function getUsers(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: '/api/user/logout',
        method: 'post',
        data
    })
}

//上传图片
export function uploadImage(data) {
    return request({
        //设置请求数据格式为multipart/form-data
        headers: {"Content-Type": "multipart/form-data;charset=UTF-8"},
        url: '/api/user/image/upload',
        method: 'post',
        data
    })
}

export function all(){
    return request({
        url: '/api/user/all',
        method: 'post',
    })
}
export function page (data){
    return request({
        url: '/api/user/page',
        method: 'post',
        data
    })
}