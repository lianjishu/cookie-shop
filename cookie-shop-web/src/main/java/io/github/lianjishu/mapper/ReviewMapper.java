package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    int insert(Review review);

    List<Review> selectByGoodsId(int goodsId);

    double selectAverageRating(int goodsId);

    int selectCountByGoodsId(int goodsId);

    int selectCountByOrderId(int orderId);
}
