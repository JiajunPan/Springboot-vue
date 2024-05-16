package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.User;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList();
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    //插入新的user
    @Transactional
    @Override
    public JsonResult save(User user) {
        List<User> users = userMapper.selectByUsername(user.getUsername());
        if (users.size() > 0) {
            return new JsonResult(1, "用户名重复，请换个用户名", null);
        }
        userMapper.insert(user);
        user.setBalance(0);
        userMapper.insertBalance(user);
        user.setName("未填写");
        user.setSex("未填写");
        LocalDate localDate = LocalDate.parse("2000-01-01");
        LocalDate localDate1 = LocalDate.now();
        //计算时间差值
        Period period = Period.between(localDate, localDate1);
        //自动计算并写入年龄
        user.setAge(period.getYears());
        user.setBirth(localDate);
        user.setEmail("未填写");
        user.setAddress("未填写");
        user.setPhone("未填写");
        userMapper.insertInformationById(user);
        return new JsonResult(0, "注册成功", user.getUsername() + user.getPassword());
    }

    //删除user
    @Override
    public boolean deleteById(Long id) {
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean updateProtectQuestion(User user) {
        return userMapper.updateProtectQuestion(user) > 0;
    }

    @Override
    public PageInfo page(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectList();
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }


    @Override
    public JsonResult login(String username, String password) {
        List<User> users = userMapper.selectByUsernameAndPassword(username, password);
        if (users.size() == 0) {
            return new JsonResult(1, "用户名或密码错误", null);
        }
        if (users.size() > 1) {
            return new JsonResult(1, "用户名重复，查询到多个用户，请联系管理员", null);
        }
        if (users.get(0).getStatus() != 0) {
            if (users.get(0).getStatus() == 1) {
                return new JsonResult(0, "检测到异常登录，如非本人，请尽快更改账户密码及密保", users.get(0));
            } else {
                return new JsonResult(2, "您的账户因异常操作，已被冻结，请联系管理员", null);
            }
        }
        return new JsonResult(0, "", users.get(0));

    }

    @Override
    public JsonResult findUserByUsernameAndProtect(String username, String protectQuestion, String protectAnswer) {
        List<User> users = userMapper.selectByUsernameAndProtect(username, protectQuestion, protectAnswer);
        if (users.size() == 0) {
            return new JsonResult(1, "用户名或密保错误", null);
        }
        if (users.size() > 1) {
            throw new RuntimeException("用户名重复，查询到多个用户，请联系管理员");
        }
        return new JsonResult(0, "成功", users.get(0));
    }

    @Override
    public User findByName(User user) {
        System.out.println(userMapper.selectByUsername(user.getUsername()).get(0));
        return userMapper.selectByUsername(user.getUsername()).get(0);
    }

    @Override
    public boolean saveInformationById(User user, Long id) {
        User user1 = userMapper.selectById(id);
//        判断user是否存在，存在就更新，不存在。。。
        if (user1 == null) {
            return false;
        } else {
            user.setUserid(user1.getUserid());
        }
        userMapper.insertInformationById(user);
        return user.getUserid() > 0;
    }

    @Override
    public boolean deleteInformationById(Long id) {
        int rows = userMapper.deleteInformationById(id);
        return rows > 0;
    }

    @Override
    public boolean updateInformation(User user) {
        int rows = userMapper.updateInformation(user);
        return rows > 0;
    }

    @Override
    public User findInformationById(Long id) {
        List<User> users = userMapper.selectInformationById(id);
        return users.get(0);
    }

    @Override
    public List<User> findAllInformation() {
        return userMapper.selectallInformation();
    }

    @Override
    public List<User> findAllMoney() {
        return userMapper.selectallMoney();
    }

    @Override
    public boolean saveBalance(User user, Long id) {
        User user1 = userMapper.selectById(id);
//        判断user是否存在，存在就更新，不存在。。。
        if (user1 == null) {
            return false;
        } else {
            user.setUserid(user1.getUserid());
        }
        userMapper.insertBalance(user);
        return user.getUserid() > 0;
    }

    @Override
    public boolean deleteBalance(Long id) {
        int rows = userMapper.deleteBalance(id);
        return rows > 0;
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
}
