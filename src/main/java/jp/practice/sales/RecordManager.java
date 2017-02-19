package jp.practice.sales;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品データ全体を保持するクラス
 */
public final class RecordManager {

    /** 商品データ */
    private static List<Item> list = new ArrayList<>();

    static {
        getList().add(new Item("A00101", "油性ボールペン", 60, 0, 0));
        getList().add(new Item("A00201", "極細ボールペン", 120, 0, 0));
        getList().add(new Item("A00301", "蛍光ペン６色セット", 420, 0, 0));
        getList().add(new Item("A00401", "シャープペンシル", 100, 0, 0));
        getList().add(new Item("A00501", "鉛筆H（１ダース）", 400, 0, 0));
        getList().add(new Item("B00101", "無線綴ノートA4", 100, 0, 0));
        getList().add(new Item("B00201", "リングノートA4", 120, 0, 0));
        getList().add(new Item("B00301", "領収書", 350, 0, 0));
        getList().add(new Item("C00101", "はさみ（青）", 128, 0, 0));
        getList().add(new Item("C00201", "ステープラー", 338, 0, 0));
        getList().add(new Item("C00301", "２穴パンチ", 128, 0, 0));
        getList().add(new Item("C00401", "ゼムクリップ", 98, 0, 0));
        getList().add(new Item("C00501", "消しゴム", 58, 0, 0));
    }
    /**
     * @return the list
     */
    public static List<Item> getList() {
        return list;
    }
    /**
     * set the list
     * @param list the list
     */
    public static void setList(List<Item> list) {
        RecordManager.list = list;
    }
    /**
     * 商品のIDで商品を検索する
     * @param id 商品のID
     * @return idで商品を見つけた場合、該当商品の値を返す
     */
    public static Item searchItem(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

}
// Copyright 2015 FUJITSU APPLICATIONS LIMITED
