package com.tjetc.common;

import javax.servlet.http.HttpSession;

/**
 * 权限检查工具类，用于检查用户权限。
 */
public class CheckPrivilege {
    /**
     * 检查用户权限，确保用户具有管理员权限。
     *
     * @param session HttpSession对象
     */
    public static void checkPrivilege(HttpSession session){
        // 检查是否登录且具有管理员权限
        if (session == null || session.getAttribute("admin") == null) {
            new JsonResult(-1, "未登录或登录过期，请重新登录", null);
            return;
        }
        // 检查是否为普通用户，普通用户不允许操作
        if (session.getAttribute("user") != null) {
            new JsonResult(-1, "用户禁止操作", null);
        }
    }
}
