package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.User;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
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
        List<User> users = userService.findAll();
        return new JsonResult(0, "", users);
    }

    //id查询接口
    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new JsonResult(0, "", user);
    }

    //新增
    @RequestMapping("save")
    public JsonResult save(User user) {
        if (user.getUsername() == null || "".equals(user.getUsername().trim())) {
            return new JsonResult(1, "用户名不能为空", null);
        } else if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return new JsonResult(1, "密码不能为空", null);
        } else if (user.getProtectQuestion() == null || "".equals(user.getProtectQuestion().trim())) {
            return new JsonResult(1, "密保问题不能为空", null);
        } else if (user.getProtectAnswer() == null || "".equals(user.getProtectAnswer().trim())) {
            return new JsonResult(1, "密保答案不能为空", null);
        } else return userService.save(user);
    }

    //删除
    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        boolean bl = userService.deleteById(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    //忘记密码
    @RequestMapping("forgetPassword")
    public JsonResult forgetPassword( String username, String protectQuestion, String protectAnswer) {
        if ((username == null || "".equals(username.trim())) || (protectQuestion == null || "".equals(protectQuestion.trim())) || (protectAnswer == null || "".equals(protectAnswer.trim()))) {
            return new JsonResult(1, "用户名与密保问题不能为空", null);
        }
        return userService.findUserByUsernameAndProtect(username, protectQuestion, protectAnswer);
    }

    //    重置密码
    @RequestMapping("resetPassword")
    public JsonResult resetPassword(String username, String password, String protectQuestion, String protectAnswer) {
        if ((username == null || "".equals(username.trim())) || (protectQuestion == null || "".equals(protectQuestion.trim())) || (protectAnswer == null || "".equals(protectAnswer.trim()))) {
            return new JsonResult(1, "用户名与密保问题不能为空", null);
        }
        if (userService.findUserByUsernameAndProtect(username, protectQuestion, protectAnswer).getStatus() == 0) {
            User user = new User();
            user.setUsername(username);
            user.setProtectQuestion(protectQuestion);
            user.setProtectAnswer(protectAnswer);
            user.setPassword(password);
            userService.update(user);
        }
        return new JsonResult(1, "用户名或者密保错误！", null);
    }

    @RequestMapping("update")
    public JsonResult update(User user,HttpSession session) {
        if ((user.getUsername() == null || "".equals(user.getUsername().trim())) || (user.getProtectQuestion() == null || "".equals(user.getProtectQuestion().trim())) || (user.getProtectAnswer() == null || "".equals(user.getProtectAnswer().trim()))||(user.getPassword() == null || "".equals(user.getPassword().trim()))) {
            return new JsonResult(1, "用户名,密保问题,密码不能为空", null);
        }
            userService.update(user);
        return new JsonResult(0, "成功！", null);
    }
    //重置密保
    @RequestMapping("change/{id}")
    public JsonResult change(@PathVariable("id") Long id, HttpSession session, String protectQuestion, String protectAnswer) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = new User();
        user.setUserid(id);
        user.setProtectQuestion(protectQuestion);
        user.setProtectAnswer(protectAnswer);
        boolean bl = userService.updateProtectQuestion(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "更改密保成功", null);
        } else {
            result = new JsonResult<>(1, "更改密保失败", null);
        }
        return result;
    }

    //登录类
    @RequestMapping("login")
    public JsonResult login(String username, String password, HttpSession session) {
        if ((username == null || "".equals(username.trim())) || (password == null || "".equals(password.trim()))) {
            return new JsonResult(1, "用户名与密码不能为空", null);
        }
//        查询用户
        JsonResult result = userService.login(username, password);
        if (result.getStatus() == 0) {
//      登录成功，往session对象存储登录的标记
//            springboot自动给我们传session
            User user = (User) result.getData();
//            在session对象中存储user对象，key为user字符串，value是user对象
            session.setAttribute("user", user);
        }
        return result;
    }
    @RequestMapping("logout")
    public JsonResult logout(HttpSession session) {
        session.invalidate();
        return new JsonResult<>(0, "登出成功",null);
    }
    //页处理类
    @RequestMapping("page")
    public JsonResult page(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize) {
        PageInfo pageInfo = userService.page(pageNum, pageSize);
        return new JsonResult<>(0, "", pageInfo);
    }

    //异常处理类
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public JsonResult handlerException(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }
    @ExceptionHandler({IndexOutOfBoundsException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }
    //    用户信息类维护
//    增
    @RequestMapping("saveUserInformation")
    public JsonResult saveUserInformation(Long userid,
                                          @RequestParam(value = "name", required = false, defaultValue = "未填写") String name,
                                          @RequestParam(value = "sex", required = false, defaultValue = "未填写") String sex,
                                          @RequestParam(value = "birth", required = false, defaultValue = "2000-01-01") String birth,
                                          @RequestParam(value = "email", required = false, defaultValue = "未填写") String email,
                                          @RequestParam(value = "address", required = false, defaultValue = "未填写") String address,
                                          @RequestParam(value = "phone", required = false, defaultValue = "未填写") String phone) {
        User user = new User();
        user.setName(name);
        user.setSex(sex);
//        需要对birth进行特殊化处理（int转LocalDate,分别输入)
        LocalDate localDate = LocalDate.parse(birth);
        LocalDate localDate1 = LocalDate.now();
//        计算时间差值
        Period period = Period.between(localDate, localDate1);
//        自动计算并写入年龄
        user.setAge(period.getYears());
        user.setBirth(localDate);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        boolean bl = userService.saveInformationById(user, userid);
        if (bl) {
            return new JsonResult(0, "保存成功", bl);
        } else {
            return new JsonResult(1, "保存失败", bl);
        }
    }

    //改
    @RequestMapping("changeUserInformation")
    public JsonResult changeUserInformation(Long userid, HttpSession session,
                                            @RequestParam(value = "name", required = false, defaultValue = "未填写") String name,
                                            @RequestParam(value = "sex", required = false, defaultValue = "未填写") String sex,
                                            @RequestParam(value = "age", required = false, defaultValue = "0") int age,
                                            @RequestParam(value = "birth", required = false, defaultValue = "2000-08-01") String birth,
                                            @RequestParam(value = "email", required = false, defaultValue = "未填写") String email,
                                            @RequestParam(value = "address", required = false, defaultValue = "未填写") String address,
                                            @RequestParam(value = "phone", required = false, defaultValue = "未填写") String phone) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
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
        boolean bl = userService.updateInformation(user);
        if (bl) {
            return new JsonResult(0, "更新成功", bl);
        } else {
            return new JsonResult(1, "更新失败", bl);
        }
    }

    //查
    @RequestMapping("userInformation/{id}")
    public JsonResult UserInformation(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = userService.findInformationById(id);
        return new JsonResult(0, "", user);
    }
    @RequestMapping("userInformation/all")
    public JsonResult allInformation(HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<User> users = userService.findAllInformation();
        return new JsonResult(0, "", users);
    }
    //删
    @RequestMapping("deleteInformation/{id}")
    public JsonResult deleteUserInformation(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        boolean bl = userService.deleteInformationById(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    //    余额处理类
    //    增
    @RequestMapping("saveBalance")
    public JsonResult saveUserInformation(Long id, HttpSession session, double balance) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = new User();
        user.setBalance(balance);
        boolean bl = userService.saveBalance(user, id);
        if (bl) {
            return new JsonResult(0, "保存成功", bl);
        } else {
            return new JsonResult(1, "保存失败", bl);
        }
    }

    //    删
    @RequestMapping("deleteBalance/{id}")
    public JsonResult deleteBalance(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        boolean bl = userService.deleteBalance(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    //    改
    @RequestMapping("changeBalance")
    public JsonResult changeBalance(Long userid, HttpSession session, double balance) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = new User();
        user.setUserid(userid);
        user.setBalance(balance);
        boolean bl = userService.updateBalance(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "更新成功", null);
        } else {
            result = new JsonResult<>(1, "更新失败", null);
        }
        return result;
    }

    //    查
    @RequestMapping("balance/all")
    public JsonResult findAllBalance(HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<User> users = userService.findAllMoney();
        return new JsonResult(0, "", users);
    }
    @RequestMapping("balance/{id}")
    public JsonResult findBalance(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = userService.findBalanceById(id);
        return new JsonResult(0, "", user);
    }

}
