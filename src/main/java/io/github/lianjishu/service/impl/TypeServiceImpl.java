package io.github.lianjishu.service.impl;

import io.github.lianjishu.mapper.TypeMapper;
import io.github.lianjishu.entity.Type;
import io.github.lianjishu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> GetAllType() {
        return typeMapper.selectAll();
    }

    @Override
    public Type select(int id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void insert(Type type) {
        typeMapper.insert(type);
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public boolean delete(int id) {
        return typeMapper.delete(id) > 0;
    }
}
