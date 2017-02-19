package jp.practice.sales;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * クライアントからのリクエストを処理するコントローラークラスです。
 * @author 王　航
 *
 *
 */
@Controller
@RequestMapping(value = "/system")
@SessionAttributes(types = { SalesForm.class })
public class SalesSystemController {
    /** 初期画面のURLを表す変数です。*/
    public static final String INIT = "init";
    /** 明細追加画面のURLを表す変数です。*/
    public static final String ADD = "add";
    /** 確定画面のURLを表す変数です。*/
    public static final String FIX = "fix";
    /** 登録済みの時のメッセージを表す変数です。*/
    public static final String FIXMESSAGE = "以下のように売上登録しました";
    /** 明細を追加済みの時のメッセージを表す変数です。*/
    public static final String ADDMESSAGE = "明細に追加しました";
    /** 追加した商品の総額を表す変数です。*/
    private static int sum;
    /**
     * 初期処理を行うメソッド。RecordManagerから商品一覧を設定する。
     * @param model モデル
     */
    private static void init(Model model) {
        List<Item> itemList = RecordManager.getList();
        model.addAttribute("itemList", itemList);
    }

    /**
     * 初期画面を生成するメソッドです。
     * @param form フォームクラス
     * @param model モデル
     * @return 初期画面
     */
    @RequestMapping(value = "/start")
    public String home(SalesForm form, Model model) {
        form.setId("A00101");
        form.setQuantity("1");
//        List<Item> itemList = RecordManager.getList();
//        model.addAttribute("itemList", itemList);
        init(model);
        return INIT;
    }
    /**
     * 明細追加処理を行うメソッドです。
     * @param form フォームクラス
     * @param result Bindingの結果を表す変数
     * @param model モデル
     * @return 追加画面
     */
    @RequestMapping(params = "add")
    public String add(@Validated({ First.class })  SalesForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors("quantity") || result.hasFieldErrors("id")) {
            // 初期画面と同じように商品一覧を表示する
              init(model);
//            List<Item> itemList = RecordManager.getList();
//            model.addAttribute("itemList", itemList);
            model.addAttribute("selectedItems", form.getItemMap());
            if (form.getItemMap().isEmpty()) {
                return INIT;
            }
            // 商品の合計値を計算する。
            setSum(0);
            for (String key : form.getItemMap().keySet()) {
                int subtotalperitem = form.getItemMap().get(key).getSubtotal();
                setSum(getSum() + subtotalperitem);
//              sum=sum+subtotalperitem;
             }
            model.addAttribute("total", changeSumFormat(sum));
            return ADD;
        }
        // 選択された商品を追加する
        form.addItem(form.getId(), form.getQuantity());
        // 選択された商品を追加したあとにIDと数量を初期化する
        form.setId("A00101");
        form.setQuantity("1");
        model.addAttribute("selectedItems", form.getItemMap());
        // 初期画面と同じように商品一覧を表示する
//        List<Item> itemList = RecordManager.getList();
//        model.addAttribute("itemList", itemList);
        init(model);
        // 選択された商品のID一覧を作成する
        List<String> idList = new ArrayList<String>();
        model.addAttribute("idList", idList);
        form.setIdToBeDel(null);

        // 商品の合計値を計算する。
        setSum(0);
        for (String key : form.getItemMap().keySet()) {
            int subtotalperitem = form.getItemMap().get(key).getSubtotal();
//          sum=sum+subtotalperitem;
            setSum(getSum() + subtotalperitem);
        }
        model.addAttribute("total", changeSumFormat(sum));
        model.addAttribute("addmessage", ADDMESSAGE);
        return ADD;
    }
    /**
     * 明細削除処理を行うメソッドです。
     * @param form フォームクラス
     * @param result Bindingの結果を表す変数
     * @param model モデル
     * @return 明細が1個以上残る場合は追加画面を、0の場合は初期画面を返す。
     */
    @RequestMapping(params = "delete")
    public String remove(@Validated({ Second.class }) SalesForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors("idToBeDel")) {
            // 初期画面と同じように商品一覧を表示する
//            List<Item> itemList = RecordManager.getList();
//            model.addAttribute("itemList", itemList);
            init(model);
            model.addAttribute("selectedItems", form.getItemMap());
            // 商品の合計値を計算する。
            setSum(0);
            for (String key : form.getItemMap().keySet()) {
                int subtotalperitem = form.getItemMap().get(key).getSubtotal();
                setSum(getSum() + subtotalperitem);
//              sum=sum+subtotalperitem;
                }
            model.addAttribute("total", changeSumFormat(sum));

            return ADD;
        }
        form.getItemMap().remove(form.getIdToBeDel());
        form.setIdToBeDel(null);
        //明細をすべて削除した場合初期画面に戻る
        if (form.getItemMap().isEmpty()) {
//            List<Item> itemList = RecordManager.getList();
//            model.addAttribute("itemList", itemList);
            init(model);
            return INIT;
        }
        // 商品の合計値を計算する。
        setSum(0);
        for (String key : form.getItemMap().keySet()) {
            int subtotalperitem = form.getItemMap().get(key).getSubtotal();
            setSum(getSum() + subtotalperitem);
//          sum=sum+subtotalperitem;
        }
        model.addAttribute("total", changeSumFormat(sum));
        model.addAttribute("selectedItems", form.getItemMap());
        // 初期画面と同じように商品一覧を表示する
//        List<Item> itemList = RecordManager.getList();
//        model.addAttribute("itemList", itemList);
        init(model);
        // 選択された商品のID一覧を作成する
        // List<String> idList=new ArrayList<String>();
        // for(int i=0;i<form.getItemMap().size();i++){
        // idList.add(form.getItemMap().get(i).getId());
        // }
        // model.addAttribute("idList", idList);

        return ADD;
    }
    /**
     * 明細確定処理を行うメソッドです。
     * @param form フォームクラス
     * @param model モデル
     * @return 売上登録画面
     */
    @RequestMapping(params = "fix")
    public String firm(SalesForm form, Model model) {
        model.addAttribute("salesID", Calendar.getInstance().getTimeInMillis());
        model.addAttribute("fixmessage", FIXMESSAGE);
        model.addAttribute("selectedItems", form.getItemMap());
        // 商品の合計値を計算する。
        setSum(0);
        for (String key : form.getItemMap().keySet()) {
            int subtotalperitem = form.getItemMap().get(key).getSubtotal();
            setSum(getSum() + subtotalperitem);
//          sum=sum+subtotalperitem;
        }
        model.addAttribute("total", changeSumFormat(sum));
        return FIX;
    }
    /**
     * 終了処理を行うメソッドです。
     * @param form フォームクラス
     * @param model モデル
     * @param sessionStatus セッション情報。
     * @return 初期画面
     */
    @RequestMapping(params = "toInit")
    public String end(SalesForm form, Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
//        List<Item> itemList = RecordManager.getList();
//        model.addAttribute("itemList", itemList);
        init(model);
        return INIT;
    }

    /**
     * 追加した商品の総額をゲットする
     * @return the sum
     */
    public static int getSum() {
        return sum;
    }

    /**
     * 追加した商品の総額を設定する
     * @param sum the sum to set
     */
    public static void setSum(int sum) {
        SalesSystemController.sum = sum;
    }
    /**
     * 総額の中にカンマを追加するメソッド
     * @param sum 商品総額
     * @return カンマ追加した後の結果を返す
     */
    public static String changeSumFormat(int sum) {
        String result = null;
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(",000");
        result = df.format(sum);
        return result;
    }
}
