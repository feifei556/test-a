package cn.wizzer.app.shop.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/31.
 */
@Table("test_goods")
public class Test_goods extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("商品编码")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("商品名称")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String goodName;

    @Column
    @Comment("商品库存")
    @ColDefine(type = ColType.INT)
    private int goodStore;

    @Column
    @Comment("商品冻结库存")
    @ColDefine(type = ColType.INT)
    private int goodFrozenStore;

    @Column
    @Comment("市场价")
    @ColDefine(type = ColType.INT)
    private int marketPrice;

    @Column
    @Comment("销售价")
    @ColDefine(type = ColType.INT)
    private int retailPrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodStore() {
        return goodStore;
    }

    public void setGoodStore(int goodStore) {
        this.goodStore = goodStore;
    }

    public int getGoodFrozenStore() {
        return goodFrozenStore;
    }

    public void setGoodFrozenStore(int goodFrozenStore) {
        this.goodFrozenStore = goodFrozenStore;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}
