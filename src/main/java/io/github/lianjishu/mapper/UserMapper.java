package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    User selectByUsername(String username);

    User selectByEmail(String email);

    User selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    User selectByEmailPassword(@Param("email") String email, @Param("password") String password);

    User selectById(int id);

    int selectUserCount();

    List<User> selectUserList(@Param("offset") int offset, @Param("limit") int limit);

    int updateUserAddress(User user);

    int updatePwd(User user);

    int delete(int id);
}
