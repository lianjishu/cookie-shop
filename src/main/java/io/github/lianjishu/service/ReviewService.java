package io.github.lianjishu.service;

import io.github.lianjishu.entity.Review;

import java.util.List;

public interface ReviewService {

    boolean addReview(Review review);

    List<Review> getReviewsByGoodsId(int goodsId);

    double getAverageRating(int goodsId);

    int getReviewCount(int goodsId);

    boolean hasReview(int orderId);
}
