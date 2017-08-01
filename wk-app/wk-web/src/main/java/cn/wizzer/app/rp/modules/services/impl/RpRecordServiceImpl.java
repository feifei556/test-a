package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_record;
import cn.wizzer.app.rp.modules.services.RpRecordService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpRecordServiceImpl extends BaseServiceImpl<Rp_record> implements RpRecordService {
    public RpRecordServiceImpl(Dao dao) {
        super(dao);
    }
}
