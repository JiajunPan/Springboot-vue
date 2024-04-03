package com.tjetc.controller;


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

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    //登陆信息类维护
    //所有列表
    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<Admin> admins = adminService.findAll();
        return new JsonResult(0, "", admins);
    }

    //id查询接口
    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id) {
        Admin admin = adminService.findById(id);
        return new JsonResult(0, "", admin);
    }

    //新增
    @RequestMapping("save")
    public JsonResult save(Admin admin ,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        if (admin.getAdminName() == null || "".equals(admin.getAdminName().trim())) {
            return new JsonResult(1, "用户名不能为空", null);
        } else if (admin.getPassword() == null || "".equals(admin.getPassword().trim())) {
            return new JsonResult(1, "密码不能为空", null);
        } else return adminService.save(admin);
    }
    @ExceptionHandler({IndexOutOfBoundsException.class,NullPointerException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }
    //删除
    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = adminService.deleteById(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    @RequestMapping("resetPassword")
    public JsonResult resetPassword(Admin admin ,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        if ((admin.getAdminName() == null || "".equals(admin.getAdminName().trim()))) {
            return new JsonResult(1, "用户名不能为空", null);
        }
        adminService.update(admin);
        return new JsonResult(0, "修改成功", null);
    }

    @RequestMapping("allUser")
    public JsonResult allUser(HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<User> users = adminService.findAllUser();
        return new JsonResult(0, "", users);
    }

    @RequestMapping("userById/{id}")
    public JsonResult userById(@PathVariable("id") Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        User user = adminService.findByIdUser(id);
        return new JsonResult(0, "", user);
    }

    @RequestMapping("deleteUser/{id}")
    public JsonResult deleteUser(@PathVariable("id") Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = adminService.deleteByIdUser(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }
//    改保护
    @RequestMapping("changeUserProtect")
    public JsonResult changeUserProtect(User user,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = adminService.updateUser(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "更改保护成功", null);
        } else {
            result = new JsonResult<>(1, "更改保护失败", null);
        }
        return result;
    }

    @RequestMapping("changeUserState")
    public JsonResult changeUserState(User user,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = adminService.updateStateCode(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "更改状态成功", null);
        } else {
            result = new JsonResult<>(1, "更改状态失败", null);
        }
        return result;
    }

    @RequestMapping("userInformation")
    public JsonResult UserInformation(Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        User user = adminService.findInformationById(id);
        return new JsonResult(0, "", user);
    }

    @RequestMapping("changeBalance")
    public JsonResult changeBalance(User user,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = adminService.updateBalance(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "更新成功", null);
        } else {
            result = new JsonResult<>(1, "更新失败", null);
        }
        return result;
    }
    @RequestMapping("changeUserInformation")
    public JsonResult changeUserInformation(Long userid,HttpSession session,
                                            @RequestParam(value = "name", required = false, defaultValue = "未填写") String name,
                                            @RequestParam(value = "sex", required = false, defaultValue = "未填写") String sex,
                                            @RequestParam(value = "age", required = false, defaultValue = "0") int age,
                                            @RequestParam(value = "birth", required = false, defaultValue = "2000-08-01") String birth,
                                            @RequestParam(value = "email", required = false, defaultValue = "未填写") String email,
                                            @RequestParam(value = "address", required = false, defaultValue = "未填写") String address,
                                            @RequestParam(value = "phone", required = false, defaultValue = "未填写") String phone) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        UserInformation(userid,session);
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
        boolean bl = userService.updateInformation(user);
        if (bl) {
            return new JsonResult(0, "更新成功", bl);
        } else {
            return new JsonResult(1, "更新失败", bl);
        }
    }
    @RequestMapping("balance/{id}")
    public JsonResult findBalance(@PathVariable("id") Long id ,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        User user = adminService.findBalanceById(id);
        return new JsonResult(0, "", user);
    }

    @RequestMapping("purchaseUser/{id}")
    public JsonResult purchaseUser(@PathVariable("id") Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<Purchased> purchaseds = adminService.findBuyByUser(id);
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("purchaseProduct/{id}")
    public JsonResult purchaseProduct(@PathVariable("id") Long id ,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<Purchased> purchaseds = adminService.findBuyByProduct(id);
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("purchaseDate")
    public JsonResult purchaseDate(LocalDateTime dateTime1, LocalDateTime dateTime2,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<Purchased> purchaseds = adminService.findBuyByDate(dateTime1, dateTime2);
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("login")
    public JsonResult login(String adminName, String password, HttpSession session) {
        if ((adminName == null || "".equals(adminName.trim())) || (password == null || "".equals(password.trim()))) {
            return new JsonResult(0, "用户名与密码不能为空", null);
        }
//        查询用户
        JsonResult result = adminService.login(adminName, password);
        if (result.getStatus() == 0) {
//      登录成功，往session对象存储登录的标记
//            springboot自动给我们传session
            Admin admin = (Admin) result.getData();
//            在session对象中存储admin对象，key为admin字符串，value是admin对象
            session.setAttribute("admin", admin);
        }
        return result;
    }
    @RequestMapping("logout")
    public JsonResult logout(HttpSession session) {
        session.invalidate();
        return new JsonResult<>(0, "登出成功",null);
    }
    //异常处理类
    @ExceptionHandler({ArithmeticException.class})
    public JsonResult handlerException(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }
}
