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

    /** 带上 user_id 的更新：既定位记录又校验归属，影响行数为 0 表示不存在或不属于该用户 */
    int updateAmountByUser(@Param("id") int id, @Param("userId") int userId, @Param("amount") int amount);

    /** 带上 user_id 的删除，语义同上 */
    int deleteByUser(@Param("id") int id, @Param("userId") int userId);

    int clearByUserId(int userId);
}
