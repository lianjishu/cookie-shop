package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.Goods;
import io.github.lianjishu.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendMapper {

    List<Goods> selectByType(int type);

    List<Goods> selectAll();

    Recommend selectOne(@Param("goodsId") int goodsId, @Param("type") int type);

    int addRecommend(@Param("type") int type, @Param("goodsId") int goodsId);

    int deleteRecommend(@Param("type") int type, @Param("goodsId") int goodsId);

    int getCountById(int typeId);
}
