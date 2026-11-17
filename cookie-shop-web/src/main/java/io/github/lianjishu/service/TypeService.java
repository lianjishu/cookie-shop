package io.github.lianjishu.service;

import io.github.lianjishu.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> GetAllType();

    Type select(int id);

    void insert(Type type);

    void update(Type type);

    boolean delete(int id);
}
