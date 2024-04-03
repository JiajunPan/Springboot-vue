// request是一个axios对象(经过设置信息的)
import request from '@/utils/request'

export function loginadmin(data) {
    return request({
        url: '/api/admin/login',
        //post请求
        method: 'post',
        data
    })
}
export function logoutadmin() {
    return request({
        url: '/api/user/logout',
        //post请求
        method: 'post',
    })
}
export function all(){
    return request({
        url: '/api/admin/all',
        method: 'post',
    })
}
export function allProduct(){
    return request({
        url: '/api/product/all',
        method: 'post',
    })
}
export function delUser(id){
    return request({
        url: `/api/admin/deleteUser/${id}`,
        method: 'post',
    })
}
export function sellBuy(data){
    return request({
        url:`/api/purchased/deleteByUser`,
        //post请求
        method: 'post',
        data
    })
}
export function findMoney(id){
    return request({
        url: `/api/admin/balance/${id}`,
        method: 'post',
    })
}
export function resetpassword(data){
    return request({
        url: `/api/admin/resetPassword`,
        method: 'post',
        data
    })
}

export function delAdmin(id){
    return request({
        url: `/api/admin/delete/${id}`,
        method: 'post',
    })
}
export function changeMoney(data){
    return request({
        url: '/api/admin/changeBalance',
        //post请求
        method: 'post',
        data
    })
}
export function changeUserInformation(data){
    return request({
        url: '/api/admin/changeUserInformation',
        //post请求
        method: 'post',
        data
    })
}
export function registerAdmin(data){
    return request({
        url: '/api/admin/save',
        //post请求
        method: 'post',
        data
    })
}
export function setProtectUser(data){
    return request({
        url: '/api/admin/changeUserProtect',
        //post请求
        method: 'post',
        data
    })
}
export function setStatus(data){
    return request({
        url: '/api/admin/changeUserState',
        //post请求
        method: 'post',
        data
    })
}
export function sellConditionSum(){
    return request({
        url: '/api/purchased/sumProductAll',
        //post请求
        method: 'post',

    })
}
export function sellConditionCnt(){
    return request({
        url: '/api/purchased/countProduct',
        //post请求
        method: 'post',

    })
}
export function buyConditionSum(){
    return request({
        url: '/api/purchased/sumUserAll',
        //post请求
        method: 'post',

    })
}
export function buyConditionCnt(){
    return request({
        url: '/api/purchased/countUser',
        //post请求
        method: 'post',

    })
}
export function maxUser(){
    return request({
        url: '/api/purchased/maxUser',
        //post请求
        method: 'post',

    })
}
export function maxProduct(){
    return request({
        url: '/api/purchased/maxProduct',
        //post请求
        method: 'post',

    })
}
export function minProduct(){
    return request({
        url: '/api/purchased/minProduct',
        //post请求
        method: 'post',

    })
}