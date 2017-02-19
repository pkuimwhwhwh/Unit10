package jp.practice.sales;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * コントローラーと通信するフォームクラスです。
 * @author 王　航
 *
 */
public class SalesForm {
    /** 商品IDの最短サイズ */
    private static final int MINSIZE = 5;
    /** 商品id */
    // @NotEmpty(message="商品を選択してください",groups = {First.class})
    // @NotNull(message="商品を選択してください",groups = {First.class})
    @Size(min = MINSIZE, message = "明細行を選択してください", groups = { First.class })
    private String id;
    /** 商品名 */
    private String name;
    /** 単価 */
    private int price;
    /** 個数 */
    @NotNull(message = "点数には1 以上の数字を入力してください。", groups = { First.class })
//    @DecimalMin(value = "1", message = "点数には1 以上の数字を入力してください。", groups = { First.class })
    @Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message = "点数には1 以上の数字を入力してください。", groups = { First.class })
    private String quantity;
    /** 小計 */
    private int subtotal;
    /** 選択した商品のリスト **/
    private LinkedHashMap<String, Item> itemMap;
    /** 削除しようとする商品のIDリスト **/
    @NotNull(message = "明細行を選択してください", groups = { Second.class })
    private String idToBeDel;
    /**
     * コンストラクタです
     */
    public SalesForm() {
        this.id = "A00101";
        this.quantity = "1";
        this.itemMap = new LinkedHashMap<>();
        this.idToBeDel = "";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
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
     * @param name
     *            the name to set
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
     * @param price
     *            the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }
    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    /**
     * @return the subtotal
     */
    public int getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal
     *            the subtotal to set
     */
    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the idToBeDel
     */
    public String getIdToBeDel() {
        return idToBeDel;
    }

    /**
     * @param idToBeDel
     *            the idToBeDel to set
     */
    public void setIdToBeDel(String idToBeDel) {
        this.idToBeDel = idToBeDel;
    }

    /**
     * @return the itemMap
     */
    public LinkedHashMap<String, Item> getItemMap() {
        return itemMap;
    }

    /**
     * @param itemMap
     *            the itemMap to set
     */
    public void setItemMap(LinkedHashMap<String, Item> itemMap) {
        this.itemMap = itemMap;
    }
    /**
     * 選択した明細を追加するメソッドです。
     * @param id 選択した明細における商品のID
     * @param quantity 選択した明細における商品の数量
     */
    public void addItem(String id, String quantity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-ss");
        java.util.Date date = new java.util.Date();
        String str = sdf.format(date);
        this.itemMap.put(str, new Item(id, RecordManager.searchItem(id).getName(),
                RecordManager.searchItem(id).getPrice(), Integer.parseInt(quantity)));
    }

}
