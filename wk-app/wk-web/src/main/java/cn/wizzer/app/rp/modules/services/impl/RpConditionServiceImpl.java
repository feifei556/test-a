package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_condition;
import cn.wizzer.app.rp.modules.services.RpConditionService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpConditionServiceImpl extends BaseServiceImpl<Rp_condition> implements RpConditionService {
    public RpConditionServiceImpl(Dao dao) {
        super(dao);
    }
}
