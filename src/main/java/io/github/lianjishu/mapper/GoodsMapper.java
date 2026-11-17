package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> selectAll();

    List<Goods> selectByTypeId(int typeId);

    List<Goods> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    List<Goods> selectByTypeIdByPage(@Param("typeId") int typeId, @Param("offset") int offset, @Param("limit") int limit);

    Goods selectById(int id);

    List<Goods> search(@Param("keyword") String keyword);

    int selectCount();

    int selectCountByTypeId(int typeId);

    int insert(Goods goods);

    int update(Goods goods);

    int delete(int id);

    List<Goods> selectGoodsByRecommend(@Param("type") int type, @Param("offset") int offset, @Param("limit") int limit);

    int getRecommendCount(@Param("type") int type);
}
