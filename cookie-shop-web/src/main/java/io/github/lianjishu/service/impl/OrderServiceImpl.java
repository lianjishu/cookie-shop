package io.github.lianjishu.service.impl;

import io.github.lianjishu.mapper.OrderMapper;
import io.github.lianjishu.entity.Cart;
import io.github.lianjishu.entity.Order;
import io.github.lianjishu.entity.OrderItem;
import io.github.lianjishu.entity.Page;
import io.github.lianjishu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public int createOrder(Order order, List<Cart> cartItems) {
        orderMapper.insert(order);
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setPrice(cart.getGoods().getPrice());
            item.setAmount(cart.getAmount());
            item.setGoods_id(cart.getGoods_id());
            item.setOrder_id(order.getId());
            orderMapper.insertItem(item);
        }
        return order.getId();
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Override
    public Order getOrderDetail(int orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId) {
        return orderMapper.selectItemsByOrderId(orderId);
    }

    @Override
    public List<OrderItem> getOrderItemsWithGoodsName(int orderId) {
        return orderMapper.selectItemsWithGoodsName(orderId);
    }

    @Override
    public boolean updateOrderStatus(int orderId, int status) {
        orderMapper.updateStatus(orderId, status);
        return true;
    }

    @Override
    public boolean updateStatus(int orderId, int status) {
        if (status < 1 || status > 4) {
            return false;
        }
        orderMapper.updateStatus(orderId, status);
        return true;
    }

    @Override
    public Page selectPageByStatus(int status, int pageNumber) {
        Page page = new Page();
        int pageSize = 8;
        page.setPageNumber(pageNumber);
        int totalCount = orderMapper.getOrderCount(status);
        page.SetPageSizeAndTotalCount(pageSize, totalCount);
        if (page.getPageNumber() > page.getTotalPage() && page.getTotalPage() > 0) {
            page.setPageNumber(page.getTotalPage());
        }
        int offset = (page.getPageNumber() - 1) * pageSize;
        List<Order> list = orderMapper.selectAllByStatus(status, offset, pageSize);
        page.setList(list);
        return page;
    }

    @Override
    @Transactional
    public boolean deleteOrder(int id) {
        orderMapper.deleteOrderItems(id);
        orderMapper.deleteOrder(id);
        return true;
    }

    @Override
    public int getOrderCount(int status) {
        return orderMapper.getOrderCount(status);
    }
}
