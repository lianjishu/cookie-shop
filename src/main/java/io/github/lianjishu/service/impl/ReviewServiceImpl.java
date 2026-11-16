package io.github.lianjishu.service.impl;

import io.github.lianjishu.dao.ReviewMapper;
import io.github.lianjishu.domain.Review;
import io.github.lianjishu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    @Transactional
    public boolean addReview(Review review) {
        return reviewMapper.insert(review) > 0;
    }

    @Override
    public List<Review> getReviewsByGoodsId(int goodsId) {
        return reviewMapper.selectByGoodsId(goodsId);
    }

    @Override
    public double getAverageRating(int goodsId) {
        return reviewMapper.selectAverageRating(goodsId);
    }

    @Override
    public int getReviewCount(int goodsId) {
        return reviewMapper.selectCountByGoodsId(goodsId);
    }

    @Override
    public boolean hasReview(int orderId) {
        return reviewMapper.selectCountByOrderId(orderId) > 0;
    }
}
