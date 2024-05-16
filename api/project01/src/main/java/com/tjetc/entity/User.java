package com.tjetc.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 用户实体类，包含了用户的基本信息。
 */
@Data
public class User {
    /** 用户ID */
    private long userid;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 密保问题 */
    private String protectQuestion;
    /** 问题答案 */
    private String protectAnswer;
    /** 账户状态（是否冻结） */
    private int status;
    /** 用户姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 年龄 */
    private int age;
    /** 生日 */
    private LocalDate birth;
    /** 邮箱 */
    private String email;
    /** 地址 */
    private String address;
    /** 电话 */
    private String phone;
    /** 账户余额 */
    private double balance;
}
