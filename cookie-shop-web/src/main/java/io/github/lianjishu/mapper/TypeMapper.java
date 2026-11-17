package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    List<Type> selectAll();

    Type selectById(int id);

    int insert(Type type);

    int update(Type type);

    int delete(int id);
}
