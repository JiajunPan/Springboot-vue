import request from '@/utils/request'

export function all() {
    return request({
        url: '/api/product/all',
        //post请求
        method: 'post',
    })
}
export function  update(data) {
    return request({
        url: '/api/product/change/',
        //post请求
        method: 'post',
        data
    })
}
export function  newproduct(data) {
    return request({
        url: '/api/product/save/',
        //post请求
        method: 'post',
        data
    })
}
export function  delproduct(id) {
    return request({
        url: `/api/product/delete/${id}`,
        //post请求
        method: 'post',
    })
}