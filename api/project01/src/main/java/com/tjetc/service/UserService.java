package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.User;

import java.util.List;

public interface UserService {
    //    全部用户
    List<User> findAll();

    //id查找
    User findById(Long id);

    //    注册
    JsonResult save(User user);

    //    注销
    boolean deleteById(Long id);

    //    改
    int update(User user);

    boolean updateProtectQuestion(User user);

    boolean saveInformationById(User user, Long id);

    boolean deleteInformationById(Long id);

    boolean updateInformation(User user);

    User findInformationById(Long id);

    List<User> findAllInformation();
    List<User> findAllMoney();

    boolean saveBalance(User user, Long id);

    boolean deleteBalance(Long id);

    boolean updateBalance(User user);

    User findBalanceById(Long id);

    PageInfo page(int pageNum, int pageSize);

    //    登录
    JsonResult login(String username, String password);

    JsonResult findUserByUsernameAndProtect(String username, String protectQuestion, String protectAnswer);

    //新建信息

}
