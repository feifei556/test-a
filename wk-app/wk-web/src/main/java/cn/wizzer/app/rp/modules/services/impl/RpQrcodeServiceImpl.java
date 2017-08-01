package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_qrcode;
import cn.wizzer.app.rp.modules.services.RpQrcodeService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpQrcodeServiceImpl extends BaseServiceImpl<Rp_qrcode> implements RpQrcodeService {
    public RpQrcodeServiceImpl(Dao dao) {
        super(dao);
    }
}
