import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../components/admin/AdminHome.vue'

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    },
    {
        path: '/main',
        name: 'main',
        component: () => import(/* webpackChunkName: "about" */ '../views/MainView.vue'),
        redirect: '/main/home',
        children: [
            {
                path: '/main/home',
                name: 'home',
                component: () => import(/* webpackChunkName: "about" */ '../components/user/Home.vue')
            },
            {
                path: '/main/product',
                name: 'productview',
                component: () => import(/* webpackChunkName: "about" */ '../components/user/Product.vue')
            },
            {
                path: '/main/income',
                name: 'incomeview',
                component: () => import(/* webpackChunkName: "about" */ '../components/user/Income.vue')
            },
            {
                path: '/main/left',
                name: 'leftview',
                component: () => import(/* webpackChunkName: "about" */ '../components/user/Left.vue')
            },
            {
                path: '/main/user',
                name: 'userview',
                component: () => import(/* webpackChunkName: "about" */ '../components/user/User.vue')
            },
        ]
    },
    {
        path: '/admin',
        name: 'admin',
        component: () => import(/* webpackChunkName: "about" */ '../views/AdminView.vue'),
        redirect: '/admin/home',
        children: [
            {
                path: '/admin/home',
                name: 'adminhome',
                component: () => import(/* webpackChunkName: "about" */ '../components/admin/AdminHome.vue')
            },
            {
                path: '/admin/user',
                name: 'useraccount',
                redirect: '/admin/user/account',
                children: [
                    {
                        path: '/admin/user/account',
                        name: 'useraccount',
                        component: () => import(/* webpackChunkName: "about" */ '../components/admin/UserAccount.vue')
                    },
                    {
                        path: '/admin/user/information',
                        name: 'userinformation',
                        component: () => import(/* webpackChunkName: "about" */ '../components/admin/UserInformation.vue')
                    },
                    {
                        path: '/admin/user/money',
                        name: 'usermoney',
                        component: () => import(/* webpackChunkName: "about" */ '../components/admin/UserMoney.vue')
                    },
                ]
            },
            {
                path: '/admin/buy',
                name: 'buyset',
                component: () => import(/* webpackChunkName: "about" */ '../components/admin/BuySetting.vue')
            },
            {
                path: '/admin/statistic',
                name: 'statistic',
                component: () => import(/* webpackChunkName: "about" */ '../components/admin/Statistic.vue')
            },
            {
                path: '/admin/product',
                name: 'productset',
                component: () => import(/* webpackChunkName: "about" */ '../components/admin/ProductSetting.vue')
            },
        ]
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
