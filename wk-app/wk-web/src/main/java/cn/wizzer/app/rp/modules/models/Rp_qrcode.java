package cn.wizzer.app.rp.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 桂贵 on 2017/6/3.
 */
@Table("rp_qrcode")
public class Rp_qrcode extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("二维码号码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String rp_qrcode;

    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;



    public String getId() {
        return id;
    }

    public String getRp_qrcode() {
        return rp_qrcode;
    }

    public void setRp_qrcode(String rp_qrcode) {
        this.rp_qrcode = rp_qrcode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }


}
