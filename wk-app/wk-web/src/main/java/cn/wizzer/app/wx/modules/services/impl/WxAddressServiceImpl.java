package cn.wizzer.app.wx.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.wx.modules.models.Wx_address;
import cn.wizzer.app.wx.modules.services.WxAddressService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class WxAddressServiceImpl extends BaseServiceImpl<Wx_address> implements WxAddressService {
    public WxAddressServiceImpl(Dao dao) {
        super(dao);
    }
}
