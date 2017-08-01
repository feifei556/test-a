package cn.wizzer.app.rp.modules.models;

import cn.wizzer.app.cms.modules.models.Cms_link_class;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by worthsky 2017/5/23-2017/6/1
 */
@Table("rp_redpacket")
public class Rp_redpacket extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("红包管理编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("红包标题")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String rp_title;

    @Column
    @Comment("红包总金额")
    @ColDefine(type = ColType.INT)
    private int total_money;

    @Column
    @Comment("中奖模式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String sweepstakes;

    @Column
    @Comment("每份奖额")
    @ColDefine(type = ColType.INT)
    private int each_money;

    @Column
    @Comment("红包数量")
    @ColDefine(type = ColType.INT)
    private int count_no;

    @Column
    @Comment("红包最小金额")
    @ColDefine(type = ColType.INT)
    private int min_prize_per;

    @Column
    @Comment("红包最大金额")
    @ColDefine(type = ColType.INT)
    private int max_prize_per;

    @Column
    @Comment("红包有效期")
    @ColDefine(type = ColType.INT)
    private int validity;

    @Column
    @Comment("红包提示")
    @ColDefine(type = ColType.TEXT)
    private String rp_notice;

    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRp_title() {
        return rp_title;
    }

    public void setRp_title(String rp_title) {
        this.rp_title = rp_title;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public String getSweepstakes() {
        return sweepstakes;
    }

    public void setSweepstakes(String sweepstakes) {
        this.sweepstakes = sweepstakes;
    }

    public int getEach_money() {
        return each_money;
    }

    public void setEach_money(int each_money) {
        this.each_money = each_money;
    }

    public int getCount_no() {
        return count_no;
    }

    public void setCount_no(int count_no) {
        this.count_no = count_no;
    }

    public int getMin_prize_per() {
        return min_prize_per;
    }

    public void setMin_prize_per(int min_prize_per) {
        this.min_prize_per = min_prize_per;
    }

    public int getMax_prize_per() {
        return max_prize_per;
    }

    public void setMax_prize_per(int max_prize_per) {
        this.max_prize_per = max_prize_per;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public String getRp_notice() {
        return rp_notice;
    }

    public void setRp_notice(String rp_notice) {
        this.rp_notice = rp_notice;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
