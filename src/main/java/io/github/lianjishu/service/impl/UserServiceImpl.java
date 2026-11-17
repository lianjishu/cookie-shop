package io.github.lianjishu.service.impl;

import io.github.lianjishu.mapper.UserMapper;
import io.github.lianjishu.entity.Page;
import io.github.lianjishu.entity.User;
import io.github.lianjishu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public boolean register(User user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return false;
        }
        if (userMapper.selectByEmail(user.getEmail()) != null) {
            return false;
        }
        userMapper.addUser(user);
        return true;
    }

    @Override
    public User login(String ue, String password) {
        User user = userMapper.selectByUsernamePassword(ue, password);
        if (user != null) {
            return user;
        }
        return userMapper.selectByEmailPassword(ue, password);
    }

    @Override
    public User loginByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updateUserAddress(User user) {
        userMapper.updateUserAddress(user);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }

    @Override
    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = userMapper.selectUserCount();
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List<User> list = userMapper.selectUserList((pageNumber - 1) * pageSize, pageSize);
        p.setList(list);
        return p;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public int selectCount() {
        return userMapper.selectUserCount();
    }
}
