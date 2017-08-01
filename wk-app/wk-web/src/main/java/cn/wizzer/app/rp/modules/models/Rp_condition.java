package cn.wizzer.app.rp.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by ASUS on 2017/6/5.
 */
@Table("rp_condition")
public class Rp_condition extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    //
    @Column
    @Comment("红包标题")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String title;

    @Column
    @Comment("红包总金额")
    @ColDefine(type = ColType.INT)
    private int total_money;

    @Column
    @Comment("剩余奖金")
    @ColDefine(type = ColType.INT)
    private int left_money;

    @Column
    @Comment("红包数量")
    @ColDefine(type = ColType.INT)
    private int count_no;

    @Column
    @Comment("规则id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String ruler_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLeft_money() {
        return left_money;
    }

    public void setLeft_money(int left_money) {
        this.left_money = left_money;
    }

    public int getCount_no() {
        return count_no;
    }

    public void setCount_no(int count_no) {
        this.count_no = count_no;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuler_id() {
        return ruler_id;
    }

    public void setRuler_id(String ruler_id) {
        this.ruler_id = ruler_id;
    }
}
