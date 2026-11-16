package io.github.lianjishu.service;

import io.github.lianjishu.domain.Goods;
import io.github.lianjishu.domain.Page;
import io.github.lianjishu.domain.Type;

import java.util.List;

public interface GoodsService {

    List<Goods> getAllGoods();

    List<Goods> getGoodsByTypeId(int typeId);

    Page getGoodsByPage(int pageNum, int pageSize);

    Page getGoodsByTypeIdByPage(int typeId, int pageNum, int pageSize);

    Goods getGoodsDetail(int id);

    List<Goods> searchGoods(String keyword);

    List<Type> getAllTypes();

    Type getTypeById(int id);

    void insert(Goods goods);

    void update(Goods goods);

    void delete(int id);

    Goods getGoodsById(int id);

    Page getGoodsRecommendPage(int type, int pageNum);

    void addRecommend(int id, int type);

    void removeRecommend(int id, int type);

    int selectCount();
}
