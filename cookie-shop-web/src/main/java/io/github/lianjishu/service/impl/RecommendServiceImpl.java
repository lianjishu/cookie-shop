package io.github.lianjishu.service.impl;

import io.github.lianjishu.mapper.RecommendMapper;
import io.github.lianjishu.entity.Goods;
import io.github.lianjishu.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<Goods> getRecommendByType(int type) {
        return recommendMapper.selectByType(type);
    }

    @Override
    public List<Goods> getAllRecommend() {
        return recommendMapper.selectAll();
    }

    @Override
    @Transactional
    public boolean addRecommend(int type, int goodsId) {
        if (recommendMapper.selectOne(goodsId, type) != null) {
            return false;
        }
        return recommendMapper.addRecommend(type, goodsId) > 0;
    }

    @Override
    @Transactional
    public boolean deleteRecommend(int type, int goodsId) {
        if (recommendMapper.selectOne(goodsId, type) == null) {
            return false;
        }
        return recommendMapper.deleteRecommend(type, goodsId) > 0;
    }

    @Override
    public boolean isScroll(int goodsId) {
        return recommendMapper.selectOne(goodsId, 1) != null;
    }

    @Override
    public boolean isHot(int goodsId) {
        return recommendMapper.selectOne(goodsId, 2) != null;
    }

    @Override
    public boolean isNew(int goodsId) {
        return recommendMapper.selectOne(goodsId, 3) != null;
    }

    @Override
    public int getCountById(int type) {
        return recommendMapper.getCountById(type);
    }
}
