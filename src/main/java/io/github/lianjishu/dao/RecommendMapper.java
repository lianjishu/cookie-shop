package io.github.lianjishu.dao;

import io.github.lianjishu.domain.Goods;
import io.github.lianjishu.domain.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper {

    List<Goods> selectByType(int type);

    List<Goods> selectAll();

    Recommend selectOne(@Param("goodsId") int goodsId, @Param("type") int type);

    int addRecommend(@Param("type") int type, @Param("goodsId") int goodsId);

    int deleteRecommend(@Param("type") int type, @Param("goodsId") int goodsId);

    int getCountById(int typeId);
}
