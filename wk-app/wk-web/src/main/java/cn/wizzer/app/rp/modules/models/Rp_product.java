package cn.wizzer.app.rp.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/*
 * Created by 尹明伟 on 2017/5/22.
 */

@Table("Rp_product")
public class Rp_product  extends BaseModel implements Serializable{
  public static final long serialVersionUID = 1L;
  @Column
  @Name
  @Comment("产品id")
  @ColDefine(type = ColType.VARCHAR, width = 32)
  @Prev(els = {@EL("uuid()")})
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column
    @Comment("产品名称")

    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String product_name;

    @Column
    @Comment("产品介绍")
    @ColDefine(type = ColType.VARCHAR, width= 255)
    private String product_introdu;

    public String getProduct_name() {return product_name;}

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_introdu() {
        return product_introdu;
    }

    public void setProduct_introdu(String product_introdu) {
        this.product_introdu = product_introdu;
    }


}
