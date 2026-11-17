package io.github.lianjishu.mapper;

import io.github.lianjishu.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    int addCart(Cart cart);

    List<Cart> selectByUserId(int userId);

    int selectCountByUserId(int userId);

    Cart selectByUserIdAndGoodsId(@Param("userId") int userId, @Param("goodsId") int goodsId);

    int updateAmount(@Param("id") int id, @Param("amount") int amount);

    int delete(int id);

    int clearByUserId(int userId);
}
