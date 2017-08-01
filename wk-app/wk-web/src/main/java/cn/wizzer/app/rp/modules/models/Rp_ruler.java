package cn.wizzer.app.rp.modules.models;

import cn.wizzer.app.wx.modules.models.Wx_user;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by Yehaiwen on 2017/6/6.16:11
 */
@Table("rp_ruler")
public class Rp_ruler extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("规则编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;


    @Column
    @Comment("红包编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String redpacket_id;

    @Column
    @Comment("中奖概率")
    @ColDefine(type = ColType.FLOAT)
    private float winning_probability;

    @Column
    @Comment("开始时间")
    @ColDefine(type = ColType.INT)
    private int start_time;

    @Column
    @Comment("结束时间")
    @ColDefine(type = ColType.INT)
    private int close_time;

    @Column
    @Comment("成功页面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String success_page;

    @Column
    @Comment("失败页面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String fail_page;

    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("产品编号")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String product_id;

    @Column
    @Comment("介绍")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String introduce;

    @Column
    @Comment("二维码编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String qrcode_id;

    public Rp_ruler() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getWinning_probability() {
        return winning_probability;
    }

    public void setWinning_probability(float winning_probability) {
        this.winning_probability = winning_probability;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getClose_time() {
        return close_time;
    }

    public void setClose_time(int close_time) {
        this.close_time = close_time;
    }

    public String getSuccess_page() {
        return success_page;
    }

    public void setSuccess_page(String success_page) {
        this.success_page = success_page;
    }

    public String getFail_page() {
        return fail_page;
    }

    public void setFail_page(String fail_page) {
        this.fail_page = fail_page;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getQrcode_id() {
        return qrcode_id;
    }

    public void setQrcode_id(String qrcode_id) {
        this.qrcode_id = qrcode_id;
    }

    public String getRedpacket_id() {
        return redpacket_id;
    }

    public void setRedpacket_id(String redpacket_id) {
        this.redpacket_id = redpacket_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    }






