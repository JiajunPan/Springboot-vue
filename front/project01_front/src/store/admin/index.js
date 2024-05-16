const admin = {
    state: {
        adminId: "",
        adminName: "",
    },
    getters: {
        getId(state) {
            console.log("getId:" + state.adminId)
            return state.adminId
        },
        getAdminName(state) {
            return state.adminName
        }
    },
    mutations: {
        //     保存id
        saveId(state, adminId) {
            state.adminId = adminId
        },
        saveAdminnameAndId(state, admin) {
            state.adminid = admin.adminid
            state.adminName = admin.adminName
        }

    },
    actions: {}
}
//导出这个模块，使得其他组件可以引入并使用这个模块来管理管理员信息的状态
export default admin