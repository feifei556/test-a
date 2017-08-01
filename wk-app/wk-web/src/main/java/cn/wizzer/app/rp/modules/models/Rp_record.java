package cn.wizzer.app.rp.modules.models;

import cn.wizzer.app.wx.modules.models.Wx_user;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by  on 2017/5/19.
 */
@Table("rp_record")
public class Rp_record  extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("微信昵称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String nickname;

    @One(field = "nickname")
    private Wx_user  wx_user;


    //
    @Column
    @Comment("二维码")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String qrcode_id;


    @Column
    @Comment("中奖金额")
    @ColDefine(type = ColType.INT,width=11)
    private int prize;

    @Column
    @Comment("红包标题")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String rp_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Wx_user getWx_user() {
        return wx_user;
    }

    public void setWx_user(Wx_user wx_user) {
        this.wx_user = wx_user;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQrcode_id() {
        return qrcode_id;
    }

    public void setQrcode_id(String qrcode_id) {
        this.qrcode_id = qrcode_id;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getRp_title() {
        return rp_title;
    }

    public void setRp_title(String rp_title) {
        this.rp_title = rp_title;
    }
}


