package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {
    //    全部
    List<Admin> findAll();

    //id查找
    Admin findById(Long id);

    //    注册
    JsonResult save(Admin admin);

    //    注销
    boolean deleteById(Long id);

    //    改
    int update(Admin admin);


    //用户
    //    全部用户
    List<User> findAllUser();

    //id查找
    User findByIdUser(Long id);

    //    注销
    boolean deleteByIdUser(Long id);

    //    改
    boolean updateUser(User user);

    boolean updateProtectQuestionUser(User user);

    boolean updateStateCode(User user);

    User findInformationById(Long id);

    boolean updateBalance(User user);

    User findBalanceById(Long id);

    List<Purchased> findBuyByUser(Long id);

    List<Purchased> findBuyByProduct(Long productid);

    List<Purchased> findBuyByDate(LocalDateTime dateTime1, LocalDateTime dateTime2);

    JsonResult login(String adminName, String password);
}
