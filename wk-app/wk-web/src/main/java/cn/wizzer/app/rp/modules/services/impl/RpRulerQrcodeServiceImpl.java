package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_ruler_qrcode;
import cn.wizzer.app.rp.modules.services.RpRulerQrcodeService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpRulerQrcodeServiceImpl extends BaseServiceImpl<Rp_ruler_qrcode> implements RpRulerQrcodeService {
    public RpRulerQrcodeServiceImpl(Dao dao) {
        super(dao);
    }
}
