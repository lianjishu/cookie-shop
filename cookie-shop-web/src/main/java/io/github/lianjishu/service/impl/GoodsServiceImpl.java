package io.github.lianjishu.service.impl;

import io.github.lianjishu.mapper.GoodsMapper;
import io.github.lianjishu.mapper.RecommendMapper;
import io.github.lianjishu.mapper.TypeMapper;
import io.github.lianjishu.entity.Goods;
import io.github.lianjishu.entity.Page;
import io.github.lianjishu.entity.Type;
import io.github.lianjishu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.selectAll();
    }

    @Override
    public List<Goods> getGoodsByTypeId(int typeId) {
        return goodsMapper.selectByTypeId(typeId);
    }

    @Override
    public Page getGoodsByPage(int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        int totalCount = goodsMapper.selectCount();
        page.setTotalCount(totalCount);
        int offset = (pageNum - 1) * pageSize;
        List<Goods> list = goodsMapper.selectByPage(offset, pageSize);
        page.setList(list);
        return page;
    }

    @Override
    public Page getGoodsByTypeIdByPage(int typeId, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        int totalCount = goodsMapper.selectCountByTypeId(typeId);
        page.setTotalCount(totalCount);
        int offset = (pageNum - 1) * pageSize;
        List<Goods> list = goodsMapper.selectByTypeIdByPage(typeId, offset, pageSize);
        page.setList(list);
        return page;
    }

    @Override
    public Goods getGoodsDetail(int id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<Goods> searchGoods(String keyword) {
        return goodsMapper.search(keyword);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.selectAll();
    }

    @Override
    public Type getTypeById(int id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void insert(Goods goods) {
        goodsMapper.insert(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.update(goods);
    }

    @Override
    public void delete(int id) {
        goodsMapper.delete(id);
    }

    @Override
    public Goods getGoodsById(int id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public Page getGoodsRecommendPage(int type, int pageNum) {
        Page p = new Page();
        p.setPageNumber(pageNum);
        int pageSize = 8;
        int totalCount = goodsMapper.getRecommendCount(type);
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List<Goods> list = goodsMapper.selectGoodsByRecommend(type, (pageNum - 1) * pageSize, pageSize);
        for (Goods g : list) {
            g.setScroll(recommendMapper.selectOne(g.getId(), 1) != null);
            g.setHot(recommendMapper.selectOne(g.getId(), 2) != null);
            g.setNew(recommendMapper.selectOne(g.getId(), 3) != null);
        }
        p.setList(list);
        return p;
    }

    @Override
    public void addRecommend(int id, int type) {
        recommendMapper.addRecommend(type, id);
    }

    @Override
    public void removeRecommend(int id, int type) {
        recommendMapper.deleteRecommend(type, id);
    }

    @Override
    public int selectCount() {
        return goodsMapper.selectCount();
    }
}
