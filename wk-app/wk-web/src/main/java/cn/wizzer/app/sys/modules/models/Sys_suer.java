package cn.wizzer.app.sys.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/19.
 */

@Table("sys_suer")
public class Sys_suer extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("用户名")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String userName;

    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String passWord;
    @Column
    @Comment("邮箱地址")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String email;
    @Column
    @Comment("电话")
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String telephone;
    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.VARCHAR)
    private String disable;
    @Column
    @Comment("性别")
    @ColDefine(type = ColType.VARCHAR)
    private String sex;
    @Column
    @Comment("账户")
    @ColDefine(type = ColType.INT,width=11)
    private Integer account;
    @Column
    @Comment("地址")
    @ColDefine(type = ColType.VARCHAR,width=255)
    private String address;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }
}
