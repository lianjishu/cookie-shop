package io.github.lianjishu.dao;

import io.github.lianjishu.domain.Type;

import java.util.List;

public interface TypeMapper {

    List<Type> selectAll();

    Type selectById(int id);

    int insert(Type type);

    int update(Type type);

    int delete(int id);
}
