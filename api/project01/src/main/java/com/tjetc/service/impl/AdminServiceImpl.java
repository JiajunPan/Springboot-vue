package com.tjetc.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.AdminMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.User;
import com.tjetc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectList();
    }

    @Override
    public Admin findById(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public JsonResult save(Admin admin) {
        List<Admin> admins = adminMapper.selectByAdmin(admin.getAdminName());
        if (admins.size() > 0) {
            return new JsonResult(1, "用户名重复，请换个用户名", null);
        }
        List<User> users = userMapper.selectByUsername(admin.getAdminName());
        if (users.size() > 0) {
            return new JsonResult(1, "用户名重复，请换个用户名", null);
        }
        adminMapper.insert(admin);
        return new JsonResult(0, "注册成功", admin.getAdminName() + admin.getPassword());
    }

    @Override
    public boolean deleteById(Long id) {
        int rows = adminMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public List<User> findAllUser() {
        return adminMapper.selectListUser();
    }

    @Override
    public User findByIdUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean deleteByIdUser(Long id) {
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public boolean updateUser(User user) {
        int rows = adminMapper.updateUser(user);
        return rows > 0;
    }

    @Override
    public boolean updateProtectQuestionUser(User user) {
        int rows = adminMapper.updateProtectQuestionUser(user);
        return rows > 0;
    }

    @Override
    public boolean updateStateCode(User user) {
        int rows = adminMapper.updateStateCode(user);
        return rows > 0;
    }

    @Override
    public User findInformationById(Long id) {
        List<User> users = userMapper.selectInformationById(id);
        return users.get(0);
    }

    @Override
    public boolean updateBalance(User user) {
        int rows = userMapper.updateBalance(user);
        return rows > 0;
    }

    @Override
    public User findBalanceById(Long id) {
        List<User> users = userMapper.selectBalanceById(id);
        return users.get(0);
    }

    @Override
    public List<Purchased> findBuyByUser(Long id) {
        return adminMapper.selectBuyByUser(id);
    }

    @Override
    public List<Purchased> findBuyByProduct(Long productid) {
        return adminMapper.selectBuyByProduct(productid);
    }

    @Override
    public List<Purchased> findBuyByDate(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return adminMapper.selectBuyByDate(dateTime1, dateTime2);
    }

    @Override
    public JsonResult login(String adminName, String password) {
        List<Admin> admins = adminMapper.selectByAdminAndPassword(adminName, password);
        if (admins.size() > 1) {
            return new JsonResult(1, "用户名重复，查询到多个用户，请联系管理员", null);
        }
        if (admins.size() <= 0) {
            return new JsonResult(1, "用户名或密码错误", null);
        }
        return new JsonResult(0, "", admins.get(0));
    }
}
