package io.github.lianjishu.dao;

import io.github.lianjishu.domain.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int addCart(Cart cart);

    List<Cart> selectByUserId(int userId);

    int selectCountByUserId(int userId);

    Cart selectByUserIdAndGoodsId(@Param("userId") int userId, @Param("goodsId") int goodsId);

    int updateAmount(@Param("id") int id, @Param("amount") int amount);

    int delete(int id);

    int clearByUserId(int userId);
}
