package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_ruler;
import cn.wizzer.app.rp.modules.services.RpRulerService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpRulerServiceImpl extends BaseServiceImpl<Rp_ruler> implements RpRulerService {
    public RpRulerServiceImpl(Dao dao) {
        super(dao);
    }
}
