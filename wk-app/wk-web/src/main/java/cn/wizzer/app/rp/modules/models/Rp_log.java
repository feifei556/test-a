package cn.wizzer.app.rp.modules.models;

import cn.wizzer.app.wx.modules.models.Wx_config;
import cn.wizzer.app.wx.modules.models.Wx_user;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by  on 2017/5/23.
 */

@Table("rp_log")
public class Rp_log extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    //
    @Column
    @Comment("微信号码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String openid;

    @Column
    @Comment("微信昵称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String nickname;

    @One(field = "nickname")
    private Wx_user  wx_user;


    @Column
    @Comment("是否中奖")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean disable;

    @Column
    @Comment("二维码")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String qrcodeId;

    @Column
    @Comment("规则编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String rulerId;

    @Column
    @Comment("产品名称")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String product_name;

    @Column
    @Comment("红包标题")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String rp_title;


    @Column
    @Comment("扫码时间")
    @ColDefine(type = ColType.INT)
    protected Integer scanTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRp_title() {
        return rp_title;
    }

    public void setRp_title(String rp_title) {
        this.rp_title = rp_title;
    }

    public Integer getScanTime() {
        return scanTime;
    }

    public void setScanTime(Integer scanTime) {
        this.scanTime = scanTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Wx_user getWx_user() {
        return wx_user;
    }

    public void setWx_user(Wx_user wx_user) {
        this.wx_user = wx_user;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRulerId() {
        return rulerId;
    }

    public void setRulerId(String rulerId) {
        this.rulerId = rulerId;
    }
}
