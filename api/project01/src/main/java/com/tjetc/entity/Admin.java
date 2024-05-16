package com.tjetc.entity;

import lombok.Data;

/**
 * 管理员实体类，包含管理员的基本信息。
 */
@Data
public class Admin {
    /** 管理员ID */
    private long adminId;
    /** 管理员用户名 */
    private String adminName;
    /** 管理员密码 */
    private String password;
}
