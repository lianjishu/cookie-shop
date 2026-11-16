package io.github.lianjishu.service;

import io.github.lianjishu.domain.Cart;

import java.util.List;

public interface CartService {

    boolean addToCart(int userId, int goodsId, int amount);

    int getCartCount(int userId);

    List<Cart> getCartByUserId(int userId);

    boolean updateCartAmount(int cartId, int amount);

    boolean removeFromCart(int cartId);

    boolean clearCart(int userId);
}
