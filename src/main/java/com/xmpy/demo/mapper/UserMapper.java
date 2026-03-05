package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 회원가입 - User 엔티티 전달
    int addUser(User user);

    Optional<User> getUserByUsername(String userName);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(int userId);
    // 여기까지가 일단 -------------------------- 회원가입 - 이제 토큰발급하러 가기

    void updateUserInfo(
            @Param("email") String email,
            @Param("password") String password,
            @Param("address") String address,
            @Param("userPhone") String userPhone
    );
}
