package io.github.lianjishu.entity;

public class OrderItem {
    private int id;
    private float price;
    private int amount;
    private int goods_id;
    private int order_id;
    private Goods goods;
    private String goodsName;

    public OrderItem() {
    }

    public OrderItem(int id, float price, int amount, int goods_id, int order_id) {
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.goods_id = goods_id;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setName(String name) {
        this.goodsName = name;
    }

    public void setGoodsname(String goodsname) {
        this.goodsName = goodsname;
    }

    public float getSubtotal() {
        return price * amount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", price=" + price +
                ", amount=" + amount +
                ", goods_id=" + goods_id +
                ", order_id=" + order_id +
                '}';
    }
}