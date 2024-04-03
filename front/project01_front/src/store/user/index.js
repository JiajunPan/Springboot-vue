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
export default user