package com.tjetc.controller;


import com.tjetc.common.CheckPrivilege;
import com.tjetc.common.CreateDeleteJsonResult;
import com.tjetc.common.CreateUpdateJsonResult;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.User;
import com.tjetc.service.AdminService;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

/**
 * 管理员控制器，处理管理员相关的请求。
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    /**
     * 获取所有管理员列表。
     *
     * @param session HttpSession对象。
     * @return 所有管理员的JsonResult对象。
     */
    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<Admin> admins = adminService.findAll();
        return new JsonResult(0, "", admins);
    }

    /**
     * 根据管理员ID获取管理员信息。
     *
     * @param id 管理员ID。
     * @return 管理员信息的JsonResult对象。
     */
    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id) {
        Admin admin = adminService.findById(id);
        return new JsonResult(0, "", admin);
    }

    /**
     * 新增管理员。
     *
     * @param admin   管理员对象。
     * @param session HttpSession对象。
     * @return 新增操作的结果的JsonResult对象。
     */
    @RequestMapping("save")
    public JsonResult save(Admin admin, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        if (admin.getAdminName() == null || "".equals(admin.getAdminName().trim())) {
            return new JsonResult(1, "用户名不能为空", null);
        } else if (admin.getPassword() == null || "".equals(admin.getPassword().trim())) {
            return new JsonResult(1, "密码不能为空", null);
        } else return adminService.save(admin);
    }

    /**
     * 处理IndexOutOfBoundsException和NullPointerException异常。
     *
     * @param e 异常对象。
     * @return 处理异常后的JsonResult对象。
     */
    @ExceptionHandler({IndexOutOfBoundsException.class, NullPointerException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }

    /**
     * 删除管理员。
     *
     * @param id      管理员ID。
     * @param session HttpSession对象。
     * @return 删除操作的结果的JsonResult对象。
     */
    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = adminService.deleteById(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 重置管理员密码。
     *
     * @param admin   管理员对象。
     * @param session HttpSession对象。
     * @return 修改密码操作的结果的JsonResult对象。
     */
    @RequestMapping("resetPassword")
    public JsonResult resetPassword(Admin admin, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        if ((admin.getAdminName() == null || "".equals(admin.getAdminName().trim()))) {
            return new JsonResult(1, "用户名不能为空", null);
        }
        adminService.update(admin);
        return new JsonResult(0, "修改成功", null);
    }

    /**
     * 获取所有用户列表。
     *
     * @param session HttpSession对象。
     * @return 所有用户的JsonResult对象。
     */
    @RequestMapping("allUser")
    public JsonResult allUser(HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<User> users = adminService.findAllUser();
        return new JsonResult(0, "", users);
    }

    /**
     * 根据用户ID获取用户信息。
     *
     * @param id      用户ID。
     * @param session HttpSession对象。
     * @return 用户信息的JsonResult对象。
     */
    @RequestMapping("userById/{id}")
    public JsonResult userById(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        User user = adminService.findByIdUser(id);
        return new JsonResult(0, "", user);
    }

    /**
     * 删除用户。
     *
     * @param id      用户ID。
     * @param session HttpSession对象。
     * @return 删除操作的结果的JsonResult对象。
     */
    @RequestMapping("deleteUser/{id}")
    public JsonResult deleteUser(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = adminService.deleteByIdUser(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 修改用户保护状态。
     *
     * @param user    用户对象。
     * @param session HttpSession对象。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("changeUserProtect")
    public JsonResult changeUserProtect(User user, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = adminService.updateUser(user);
        JsonResult result;
        if (flag) {
            result = new JsonResult<>(0, "更改保护成功", null);
        } else {
            result = new JsonResult<>(1, "更改保护失败", null);
        }
        return result;
    }

    /**
     * 修改用户状态。
     *
     * @param user    用户对象。
     * @param session HttpSession对象。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("changeUserState")
    public JsonResult changeUserState(User user, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = adminService.updateStateCode(user);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 根据用户ID获取用户信息。
     *
     * @param id      用户ID。
     * @param session HttpSession对象。
     * @return 用户信息的JsonResult对象。
     */
    @RequestMapping("userInformation")
    public JsonResult UserInformation(Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        User user = adminService.findInformationById(id);
        return new JsonResult(0, "", user);
    }

    /**
     * 修改用户余额。
     *
     * @param user    用户对象。
     * @param session HttpSession对象。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("changeBalance")
    public JsonResult changeBalance(User user, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = adminService.updateBalance(user);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }

    /**
     * 修改用户信息。
     *
     * @param userid  用户ID。
     * @param session HttpSession对象。
     * @param name    用户姓名。
     * @param sex     用户性别。
     * @param age     用户年龄。
     * @param birth   用户出生日期。
     * @param email   用户邮箱。
     * @param address 用户地址。
     * @param phone   用户电话。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("changeUserInformation")
    public JsonResult changeUserInformation(Long userid, HttpSession session,
                                            @RequestParam(value = "name", required = false, defaultValue = "未填写") String name,
                                            @RequestParam(value = "sex", required = false, defaultValue = "未填写") String sex,
                                            @RequestParam(value = "age", required = false, defaultValue = "0") int age,
                                            @RequestParam(value = "birth", required = false, defaultValue = "2000-08-01") String birth,
                                            @RequestParam(value = "email", required = false, defaultValue = "未填写") String email,
                                            @RequestParam(value = "address", required = false, defaultValue = "未填写") String address,
                                            @RequestParam(value = "phone", required = false, defaultValue = "未填写") String phone) {
        CheckPrivilege.checkPrivilege(session);
        UserInformation(userid, session);
        User user = new User();
        user.setUserid(userid);
        user.setName(name);
        user.setSex(sex);
        //需要对birth进行特殊化处理（int转LocalDate,分别输入)
        LocalDate localDate = LocalDate.parse(birth);
        LocalDate localDate1 = LocalDate.now();
        //计算时间差值
        Period period = Period.between(localDate, localDate1);
        //自动计算并写入年龄
        user.setAge(period.getYears());
        user.setBirth(localDate);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        boolean flag = userService.updateInformation(user);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }

    /**
     * 根据用户ID获取用户余额。
     *
     * @param id      用户ID。
     * @param session HttpSession对象。
     * @return 用户余额的JsonResult对象。
     */
    @RequestMapping("balance/{id}")
    public JsonResult findBalance(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        User user = adminService.findBalanceById(id);
        return new JsonResult(0, "", user);
    }

    /**
     * 根据用户ID获取用户购买记录。
     *
     * @param id      用户ID。
     * @param session HttpSession对象。
     * @return 用户购买记录的JsonResult对象。
     */
    @RequestMapping("purchaseUser/{id}")
    public JsonResult purchaseUser(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<Purchased> purchaseds = adminService.findBuyByUser(id);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 根据产品ID获取产品购买记录。
     *
     * @param id      产品ID。
     * @param session HttpSession对象。
     * @return 产品购买记录的JsonResult对象。
     */
    @RequestMapping("purchaseProduct/{id}")
    public JsonResult purchaseProduct(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<Purchased> purchaseds = adminService.findBuyByProduct(id);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 根据购买日期范围获取购买记录。
     *
     * @param dateTime1 开始日期。
     * @param dateTime2 结束日期。
     * @param session   HttpSession对象。
     * @return 购买记录的JsonResult对象。
     */
    @RequestMapping("purchaseDate")
    public JsonResult purchaseDate(LocalDateTime dateTime1, LocalDateTime dateTime2, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<Purchased> purchaseds = adminService.findBuyByDate(dateTime1, dateTime2);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 管理员登录。
     *
     * @param adminName 管理员用户名。
     * @param password  管理员密码。
     * @param session   HttpSession对象。
     * @return 登录操作的结果的JsonResult对象。
     */
    @RequestMapping("login")
    public JsonResult login(String adminName, String password, HttpSession session) {
        if ((adminName == null || "".equals(adminName.trim())) || (password == null || "".equals(password.trim()))) {
            return new JsonResult(0, "用户名与密码不能为空", null);
        }
        //查询用户
        JsonResult result = adminService.login(adminName, password);
        if (result.getStatus() == 0) {
        //登录成功，往session对象存储登录的标记
            Admin admin = (Admin) result.getData();
        //在session对象中存储admin对象，key为admin字符串，value是admin对象
            session.setAttribute("admin", admin);
        }
        return result;
    }

    /**
     * 管理员登出。
     *
     * @param session HttpSession对象。
     * @return 登出操作的结果的JsonResult对象。
     */
    @RequestMapping("logout")
    public JsonResult logout(HttpSession session) {
        session.invalidate();
        return new JsonResult<>(0, "登出成功", null);
    }

    /**
     * 异常处理类，处理ArithmeticException异常。
     *
     * @param e 异常对象。
     * @return 处理异常后的JsonResult对象。
     */
    @ExceptionHandler({ArithmeticException.class})
    public JsonResult handlerException(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }


}
