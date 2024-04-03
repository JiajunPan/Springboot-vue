package com.tjetc.dao;

import com.tjetc.entity.Admin;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminMapper {
    List<Admin> selectList();

    void insert(Admin admin);

    int deleteById(Long adminId);

    List<Admin> selectByAdmin(@Param("adminName") String adminName);

    List<Admin> selectByAdminAndPassword(@Param("adminName") String adminName, @Param("password") String password);

    Admin selectById(Long adminId);

    int update(Admin admin);

    //User
    List<User> selectListUser();

    void insertUser(User user);

    int deleteByIdUser(Long id);

    List<User> selectByNameUser(@Param("username") String username);

    User selectByIdUser(Long id);
//更改所有保护
    int updateUser(User user);

    int updateProtectQuestionUser(User user);

    int updateStateCode(User user);

    //    information
    User selectInformationById(Long id);

    int updateBalance(User user);

    User selectBalanceById(Long id);

    //    process
    List<Purchased> selectBuyByUser(Long userid);

    List<Purchased> selectBuyByProduct(Long productid);

    List<Purchased> selectBuyByDate(LocalDateTime date1, LocalDateTime date2);
}