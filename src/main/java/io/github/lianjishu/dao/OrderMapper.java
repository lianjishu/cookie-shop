package io.github.lianjishu.dao;

import io.github.lianjishu.domain.Order;
import io.github.lianjishu.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int insert(Order order);

    List<Order> selectByUserId(int userId);

    Order selectById(int id);

    List<OrderItem> selectItemsByOrderId(int orderId);

    int insertItem(OrderItem item);

    int updateStatus(@Param("orderId") int orderId, @Param("status") int status);

    List<Order> selectAllByStatus(@Param("status") int status, @Param("offset") int offset, @Param("limit") int limit);

    int getOrderCount(@Param("status") int status);

    List<OrderItem> selectItemsWithGoodsName(int orderId);

    int deleteOrder(int id);

    int deleteOrderItems(int orderId);
}
