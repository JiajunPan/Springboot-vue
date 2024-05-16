const user = {
    state: {
        userid: "",
        username: "",

    },
    getters: {
        getId(state) {
            console.log("getId:" + state.userid)
            return state.userid
        },
        getUsername(state) {
            return state.username
        }
    },
    mutations: {
        //     保存id
        saveId(state, userid) {
            state.userid = userid
        },
        saveUsernameAndId(state, user) {
            state.userid = user.userid
            state.username = user.username
        }

    },
    actions: {}
}
//导出这个模块，使得其他组件可以引入并使用这个模块来管理用户信息的状态
export default user