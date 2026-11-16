package io.github.lianjishu.service;

import io.github.lianjishu.domain.Cart;
import io.github.lianjishu.domain.Order;
import io.github.lianjishu.domain.OrderItem;
import io.github.lianjishu.domain.Page;

import java.util.List;

public interface OrderService {

    int createOrder(Order order, List<Cart> cartItems);

    List<Order> getOrdersByUserId(int userId);

    Order getOrderDetail(int orderId);

    List<OrderItem> getOrderItems(int orderId);

    List<OrderItem> getOrderItemsWithGoodsName(int orderId);

    boolean updateOrderStatus(int orderId, int status);

    boolean updateStatus(int orderId, int status);

    Page selectPageByStatus(int status, int pageNumber);

    boolean deleteOrder(int id);

    int getOrderCount(int status);
}
