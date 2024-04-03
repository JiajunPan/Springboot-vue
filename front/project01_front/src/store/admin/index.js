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
export default admin