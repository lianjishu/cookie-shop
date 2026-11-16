package io.github.lianjishu.dao;

import io.github.lianjishu.domain.Review;

import java.util.List;

public interface ReviewMapper {

    int insert(Review review);

    List<Review> selectByGoodsId(int goodsId);

    double selectAverageRating(int goodsId);

    int selectCountByGoodsId(int goodsId);

    int selectCountByOrderId(int orderId);
}
