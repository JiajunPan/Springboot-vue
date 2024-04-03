package com.tjetc.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long userid;
    private String username;
    private String password;
    private String protectQuestion;
    private String protectAnswer;
    private int status;
    private String name;
    private String sex;
    private int age;
    private LocalDate birth;
    private String email;
    private String address;
    private String phone;
    private double balance;
//    14个属性，分别是用户id，用户名，密码，密保问题，问题答案，账户状态（是否冻结），
//    用户姓名，性别，年龄，生日，邮箱，地址，电话，账户余额
}