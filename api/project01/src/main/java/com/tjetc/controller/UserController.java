package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.CheckPrivilege;
import com.tjetc.common.CreateDeleteJsonResult;
import com.tjetc.common.CreateUpdateJsonResult;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.User;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * 用于管理用户相关操作的Controller类。
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取所有用户的列表。
     *
     * @param session HttpSession对象
     * @return 包含用户列表或错误消息的JsonResult
     */
    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<User> users = userService.findAll();
        return new JsonResult(0, "", users);
    }

    /**
     * 根据用户ID检索用户。
     *
     * @param id 要检索的用户的ID
     * @return 包含用户对象或错误消息的JsonResult
     */
    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id) {
        // 根据用户ID查询用户信息
        User user = userService.findById(id);
        return new JsonResult(0, "", user);
    }

    /**
     * 保存用户信息。
     *
     * @param user 包含用户信息的User对象
     * @return 如果保存成功，则返回包含成功信息的JsonResult；如果用户名、密码、密保问题或密保答案为空，则返回错误信息的JsonResult。
     */
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

    /**
     * 根据用户ID删除用户信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象
     * @return 如果删除成功，则返回成功信息的JsonResult；如果未登录或登录过期，则返回未登录或登录过期信息的JsonResult；如果删除失败，则返回失败信息的JsonResult。
     */
    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            // 如果未登录或登录过期，则返回未登录或登录过期信息的JsonResult
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        // 调用userService的deleteById方法删除用户信息
        boolean flag = userService.deleteById(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);
    }

    /**
     * 处理用户忘记密码的情况，根据用户名、密保问题和密保答案进行验证。
     *
     * @param username        用户名
     * @param protectQuestion 密保问题
     * @param protectAnswer   密保答案
     * @return 如果用户名、密保问题和密保答案都不为空，则调用userService的findUserByUsernameAndProtect方法验证用户信息并返回结果；
     * 如果用户名、密保问题或密保答案为空，则返回用户名与密保问题不能为空的JsonResult。
     */
    @RequestMapping("forgetPassword")
    public JsonResult forgetPassword(String username, String protectQuestion, String protectAnswer) {
        if ((username == null || "".equals(username.trim()))
                || (protectQuestion == null || "".equals(protectQuestion.trim()))
                || (protectAnswer == null || "".equals(protectAnswer.trim()))) {
            return new JsonResult(1, "用户名与密保问题不能为空", null);
        }
        return userService.findUserByUsernameAndProtect(username, protectQuestion, protectAnswer);
    }


    /**
     * 重置用户密码，需要提供用户名、新密码、密保问题和密保答案。
     *
     * @param username        用户名
     * @param password        新密码
     * @param protectQuestion 密保问题
     * @param protectAnswer   密保答案
     * @return 如果用户名、密保问题和密保答案都不为空，则验证用户信息并重置密码；如果用户名、密保问题或密保答案为空，则返回用户名与密保问题不能为空的JsonResult；
     * 如果用户名或密保问题答案错误，则返回用户名或者密保错误的JsonResult。
     */
    @RequestMapping("resetPassword")
    public JsonResult resetPassword(String username, String password, String protectQuestion, String protectAnswer) {
        // 检查用户名、密保问题和密保答案是否为空
        if ((username == null || "".equals(username.trim()))
                || (protectQuestion == null || "".equals(protectQuestion.trim()))
                || (protectAnswer == null || "".equals(protectAnswer.trim()))) {
            return new JsonResult(1, "用户名与密保问题不能为空", null);
        }
        // 验证用户信息并重置密码
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

    /**
     * 更新用户信息，需要提供用户对象和 HttpSession 对象。
     *
     * @param user    用户对象，包含用户名、密保问题、密保答案和密码等信息
     * @param session HttpSession 对象，用于验证用户是否登录
     * @return 如果用户名、密保问题、密保答案或密码为空，则返回用户名、密保问题、密码不能为空的JsonResult；
     * 否则更新用户信息并返回成功的JsonResult。
     */
    @RequestMapping("update")
    public JsonResult update(User user, HttpSession session) {
        // 检查用户是否登录
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        // 检查用户名、密保问题、密保答案和密码是否为空
        if ((user.getUsername() == null || "".equals(user.getUsername().trim())) ||
                (user.getProtectQuestion() == null || "".equals(user.getProtectQuestion().trim())) ||
                (user.getProtectAnswer() == null || "".equals(user.getProtectAnswer().trim())) ||
                (user.getPassword() == null || "".equals(user.getPassword().trim()))) {
            return new JsonResult(1, "用户名,密保问题,密码不能为空", null);
        }
        if (user.getPassword().length() < 3) {
            return new JsonResult(1, "新密码长度应大于3", null);
        }
        //拿到当前用户
        User user1 = userService.findByName(user);
        //密保问题不正确
        if (!user.getProtectQuestion().equals(user1.getProtectQuestion())){
            return new JsonResult(1, "密保问题错误", null);
        }
        //密保问题正确，检查是否相同
        if (!user.getProtectAnswer().equals(user1.getProtectAnswer())){
            return new JsonResult(1, "回答错误", null);
        }
        // 更新用户信息
        userService.update(user);
        return new JsonResult(0, "成功！", null);
    }


    /**
     * 更改用户的密保问题和密保答案。
     *
     * @param id              用户ID
     * @param session         HttpSession 对象，用于验证用户是否登录
     * @param protectQuestion 新的密保问题
     * @param protectAnswer   新的密保答案
     * @return 如果用户未登录，则返回未登录或登录过期的JsonResult；
     * 如果更改成功，则返回更改密保成功的JsonResult；否则返回更改密保失败的JsonResult。
     */
    @RequestMapping("change/{id}")
    public JsonResult change(@PathVariable("id") Long id, HttpSession session, String protectQuestion, String protectAnswer) {
        // 检查用户是否登录
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        // 创建用户对象并设置新的密保问题和答案
        User user = new User();
        user.setUserid(id);
        user.setProtectQuestion(protectQuestion);
        user.setProtectAnswer(protectAnswer);
        // 更新用户的密保问题和答案
        boolean flag = userService.updateProtectQuestion(user);
        // 根据更新结果返回对应的JsonResult
        JsonResult result;
        if (flag) {
            result = new JsonResult<>(0, "更改密保成功", null);
        } else {
            result = new JsonResult<>(1, "更改密保失败", null);
        }
        return result;
    }


    /**
     * 用户登录接口。
     *
     * @param username 用户名
     * @param password 密码
     * @param session  HttpSession 对象，用于存储用户登录状态
     * @return 如果用户名或密码为空，则返回用户名与密码不能为空的JsonResult；
     * 如果登录成功，则返回登录成功的JsonResult，并将用户信息存储在session中；
     * 否则返回登录失败的JsonResult。
     */
    @RequestMapping("login")
    public JsonResult login(String username, String password, HttpSession session) {
        // 检查用户名和密码是否为空
        if ((username == null || "".equals(username.trim())) || (password == null || "".equals(password.trim()))) {
            return new JsonResult(1, "用户名与密码不能为空", null);
        }
        // 查询用户信息
        JsonResult result = userService.login(username, password);
        // 如果登录成功，将用户信息存储在session中
        if (result.getStatus() == 0) {
            User user = (User) result.getData();
            session.setAttribute("user", user);
        }
        return result;
    }


    /**
     * 用户登出接口。
     *
     * @param session HttpSession 对象，用于清除用户登录状态
     * @return 返回登出成功的JsonResult，并清除session中的用户信息。
     */
    @RequestMapping("logout")
    public JsonResult logout(HttpSession session) {
        // 清除session中的用户信息
        session.invalidate();
        return new JsonResult<>(0, "登出成功", null);
    }


    /**
     * 分页查询用户列表接口。
     *
     * @param pageNum  页码，默认为1
     * @param pageSize 每页大小，默认为2
     * @return 返回分页后的用户信息列表的JsonResult。
     */
    @RequestMapping("page")
    public JsonResult page(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize) {
        // 调用userService的分页方法
        PageInfo pageInfo = userService.page(pageNum, pageSize);
        return new JsonResult<>(0, "", pageInfo);
    }


    /**
     * 异常处理类，处理ArithmeticException和NullPointerException异常。
     *
     * @param e 异常对象
     * @return 返回包含异常信息的JsonResult
     */
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public JsonResult handlerException(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }

    /**
     * 异常处理类，处理IndexOutOfBoundsException异常。
     *
     * @param e 异常对象
     * @return 返回包含异常信息的JsonResult
     */
    @ExceptionHandler({IndexOutOfBoundsException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }


    /**
     * 新增用户信息。
     *
     * @param userid  用户ID
     * @param name    用户姓名
     * @param sex     用户性别
     * @param birth   用户生日
     * @param email   用户邮箱
     * @param address 用户地址
     * @param phone   用户电话
     * @return 返回保存结果的JsonResult
     */
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
        // 需要对birth进行特殊化处理（int转LocalDate,分别输入)
        LocalDate localDate = LocalDate.parse(birth);
        LocalDate localDate1 = LocalDate.now();
        // 计算时间差值
        Period period = Period.between(localDate, localDate1);
        // 自动计算并写入年龄
        user.setAge(period.getYears());
        user.setBirth(localDate);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        boolean flag = userService.saveInformationById(user, userid);
        if (flag) {
            return new JsonResult(0, "保存成功", true);
        } else {
            return new JsonResult(1, "保存失败", false);
        }
    }


    /**
     * 修改用户信息。
     *
     * @param userid  用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @param name    用户姓名
     * @param sex     用户性别
     * @param age     用户年龄
     * @param birth   用户生日
     * @param email   用户邮箱
     * @param address 用户地址
     * @param phone   用户电话
     * @return 返回更新结果的JsonResult
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
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        UserInformation(userid, session);
        User user = new User();
        user.setUserid(userid);
        user.setName(name);
        user.setSex(sex);
        // 需要对birth进行特殊化处理（int转LocalDate,分别输入)
        LocalDate localDate = LocalDate.parse(birth);
        LocalDate localDate1 = LocalDate.now();
        // 计算时间差值
        Period period = Period.between(localDate, localDate1);
        // 自动计算并写入年龄
        user.setAge(period.getYears());
        user.setBirth(localDate);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        boolean flag = userService.updateInformation(user);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }


    /**
     * 根据用户ID查询用户信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回查询结果的JsonResult
     */
    @RequestMapping("userInformation/{id}")
    public JsonResult UserInformation(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = userService.findInformationById(id);
        return new JsonResult(0, "", user);
    }


    /**
     * 查询所有用户信息。
     *
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回查询结果的JsonResult
     */
    @RequestMapping("userInformation/all")
    public JsonResult allInformation(HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<User> users = userService.findAllInformation();
        return new JsonResult(0, "", users);
    }

    /**
     * 根据用户ID删除用户信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回删除结果的JsonResult
     */
    @RequestMapping("deleteInformation/{id}")
    public JsonResult deleteUserInformation(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        boolean flag = userService.deleteInformationById(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }


    /**
     * 保存用户余额信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @param balance 用户余额
     * @return 返回保存结果的JsonResult
     */
    @RequestMapping("saveBalance")
    public JsonResult saveUserInformation(Long id, HttpSession session, double balance) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = new User();
        user.setBalance(balance);
        boolean flag = userService.saveBalance(user, id);
        if (flag) {
            return new JsonResult(0, "保存成功", true);
        } else {
            return new JsonResult(1, "保存失败", false);
        }
    }

    /**
     * 根据用户ID删除用户余额信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回删除结果的JsonResult
     */
    @RequestMapping("deleteBalance/{id}")
    public JsonResult deleteBalance(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        boolean flag = userService.deleteBalance(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 更新用户余额信息。
     *
     * @param userid  用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @param balance 用户余额
     * @return 返回更新结果的JsonResult
     */
    @RequestMapping("changeBalance")
    public JsonResult changeBalance(Long userid, HttpSession session, double balance) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = new User();
        user.setUserid(userid);
        user.setBalance(balance);
        boolean flag = userService.updateBalance(user);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }

    /**
     * 查询所有用户余额信息。
     *
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回查询结果的JsonResult
     */
    @RequestMapping("balance/all")
    public JsonResult findAllBalance(HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<User> users = userService.findAllMoney();
        return new JsonResult(0, "", users);
    }

    /**
     * 根据用户ID查询用户余额信息。
     *
     * @param id      用户ID
     * @param session HttpSession对象，用于验证用户登录状态
     * @return 返回查询结果的JsonResult
     */
    @RequestMapping("balance/{id}")
    public JsonResult findBalance(@PathVariable("id") Long id, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        User user = userService.findBalanceById(id);
        return new JsonResult(0, "", user);
    }


}