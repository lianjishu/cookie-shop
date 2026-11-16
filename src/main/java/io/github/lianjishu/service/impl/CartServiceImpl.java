package io.github.lianjishu.service.impl;

import io.github.lianjishu.dao.CartMapper;
import io.github.lianjishu.domain.Cart;
import io.github.lianjishu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    @Transactional
    public boolean addToCart(int userId, int goodsId, int amount) {
        Cart existing = cartMapper.selectByUserIdAndGoodsId(userId, goodsId);
        if (existing != null) {
            cartMapper.updateAmount(existing.getId(), existing.getAmount() + amount);
        } else {
            Cart cart = new Cart();
            cart.setUser_id(userId);
            cart.setGoods_id(goodsId);
            cart.setAmount(amount);
            cartMapper.addCart(cart);
        }
        return true;
    }

    @Override
    public int getCartCount(int userId) {
        return cartMapper.selectCountByUserId(userId);
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartMapper.selectByUserId(userId);
    }

    @Override
    @Transactional
    public boolean updateCartAmount(int cartId, int amount) {
        if (amount <= 0) {
            cartMapper.delete(cartId);
        } else {
            cartMapper.updateAmount(cartId, amount);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean removeFromCart(int cartId) {
        cartMapper.delete(cartId);
        return true;
    }

    @Override
    @Transactional
    public boolean clearCart(int userId) {
        cartMapper.clearByUserId(userId);
        return true;
    }
}
