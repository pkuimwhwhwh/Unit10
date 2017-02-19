package jp.practice.sales;

/**
 * 売上明細1件分のデータを保持するクラス
 */
public class Item {

    /** 商品id */
    private String id;
    /** 商品名 */
    private String name;
    /** 単価 */
    private int price;
    /** 個数 */
    private int quantity;
    /** 小計 */
    private int subtotal;

    /**
     * コンストラクターです。
     * @param id 商品id
     * @param name 商品名
     * @param price 単価
     * @param quantity 個数
     * @param subtotal 小計
     */
    public Item(String id, String name, int price, int quantity, int subtotal) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    /**
     * コンストラクターです。
     * @param id 商品id
     * @param name 商品名
     * @param price 単価
     * @param quantity 個数
     */
    public Item(String id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = price * quantity;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the subtotal
     */
    public int getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

}
//Copyright 2015 FUJITSU APPLICATIONS LIMITED
