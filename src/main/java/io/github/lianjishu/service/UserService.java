package io.github.lianjishu.service;

import io.github.lianjishu.entity.User;
import io.github.lianjishu.entity.Page;

public interface UserService {

    boolean register(User user);

    User login(String ue, String password);

    User loginByUsername(String username);

    User selectById(int id);

    void updateUserAddress(User user);

    void updatePwd(User user);

    Page getUserPage(int pageNumber);

    boolean delete(int id);

    int selectCount();
}
