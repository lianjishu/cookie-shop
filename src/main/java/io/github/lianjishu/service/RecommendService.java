package io.github.lianjishu.service;

import io.github.lianjishu.domain.Goods;

import java.util.List;

public interface RecommendService {

    List<Goods> getRecommendByType(int type);

    List<Goods> getAllRecommend();

    boolean addRecommend(int type, int goodsId);

    boolean deleteRecommend(int type, int goodsId);

    boolean isScroll(int goodsId);

    boolean isHot(int goodsId);

    boolean isNew(int goodsId);

    int getCountById(int type);
}
