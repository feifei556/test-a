package cn.wizzer.app.visitor.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 */
@Table("visitor_log")
public class Visitor_log extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("用户访问标识")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String visitorId;

    @Column
    @Comment("用户访问路径")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String visitorPath;

    @Column
    @Comment("用户访问时间")
    @ColDefine(type = ColType.INT, width = 11)
    private int visiterTime;

    @Column
    @Comment("用户访问地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String visitorIP;

    @Column
    @Comment("用户访问来源")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String visiterRefer;

    @Column
    @Comment("用户访问系统")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String visitorOS;

    @Column
    @Comment("用户访问浏览器类型 ")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String visitorBrower;

    @Column
    @Comment("用户访问次数 ")
    @ColDefine(type = ColType.INT, width = 10)
    private int visitorCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorPath() {
        return visitorPath;
    }

    public void setVisitorPath(String visitorPath) {
        this.visitorPath = visitorPath;
    }

    public int getVisiterTime() {
        return visiterTime;
    }

    public void setVisiterTime(int visiterTime) {
        this.visiterTime = visiterTime;
    }

    public String getVisitorIP() {
        return visitorIP;
    }

    public void setVisitorIP(String visitorIP) {
        this.visitorIP = visitorIP;
    }

    public String getVisiterRefer() {
        return visiterRefer;
    }

    public void setVisiterRefer(String visiterRefer) {
        this.visiterRefer = visiterRefer;
    }

    public String getVisitorOS() {
        return visitorOS;
    }

    public void setVisitorOS(String visitorOS) {
        this.visitorOS = visitorOS;
    }

    public String getVisitorBrower() {
        return visitorBrower;
    }

    public void setVisitorBrower(String visitorBrower) {
        this.visitorBrower = visitorBrower;
    }

    public int getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(int visitorCount) {
        this.visitorCount = visitorCount;
    }
}
