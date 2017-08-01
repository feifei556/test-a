package cn.wizzer.app.rp.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by worthsky on 2017/06/07.
 */
@Table("rp_ruler_qrcode")
public class Rp_ruler_qrcode extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("红包ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String redpacket_id;

    @Column
    @Comment("二维码ID")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String qrcode_id;

    @Column
    @Comment("是否有奖")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("规则ID")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String ruler_id;


    public Rp_ruler_qrcode() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedpacket_id() {
        return redpacket_id;
    }

    public void setRedpacket_id(String redpacket_id) {
        this.redpacket_id = redpacket_id;
    }

    public String getQrcode_id() {
        return qrcode_id;
    }

    public void setQrcode_id(String qrcode_id) {
        this.qrcode_id = qrcode_id;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getRuler_id() {
        return ruler_id;
    }

    public void setRuler_id(String ruler_id) {
        this.ruler_id = ruler_id;
    }

    public Rp_ruler_qrcode(String redpacket_id, String qrcode_id, boolean disabled, String ruler_id) {
        this.redpacket_id = redpacket_id;
        this.qrcode_id = qrcode_id;
        this.disabled = disabled;
        this.ruler_id = ruler_id;
    }
}