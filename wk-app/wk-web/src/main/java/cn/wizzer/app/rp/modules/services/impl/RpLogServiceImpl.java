package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_log;
import cn.wizzer.app.rp.modules.services.RpLogService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpLogServiceImpl extends BaseServiceImpl<Rp_log> implements RpLogService {
    public RpLogServiceImpl(Dao dao) {
        super(dao);
    }
}
