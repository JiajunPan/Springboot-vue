package com.tjetc.dao;

import com.tjetc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectList();
    List<User> selectallInformation();
    List<User> selectallMoney();

    void insert(User user);

    int deleteById(Long userid);

    List<User> selectByUsername(@Param("username") String username);

    List<User> selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User selectById(Long userid);

    List<User> selectByUsernameAndProtect(String username, String protectQuestion, String protectAnswer);

    int update(User user);

    int updateProtectQuestion(User user);


    void insertInformationById(User user);

    int deleteInformationById(Long userid);

    int updateInformation(User user);

    List<User> selectInformationById(@Param("userid") Long userid);

    void insertBalance(User user);

    int deleteBalance(Long userid);

    int updateBalance(User user);

    List<User> selectBalanceById(@Param("userid") Long userid);
}
