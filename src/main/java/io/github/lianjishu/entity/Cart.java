package io.github.lianjishu.entity;

public class Cart {
    private int id;
    private int user_id;
    private int goods_id;
    private int amount;
    private Goods goods;

    public Cart() {
    }

    public Cart(int id, int user_id, int goods_id, int amount) {
        this.id = id;
        this.user_id = user_id;
        this.goods_id = goods_id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public float getSubtotal() {
        if (goods != null) {
            return goods.getPrice() * amount;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", goods_id=" + goods_id +
                ", amount=" + amount +
                '}';
    }
}