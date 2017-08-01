package cn.wizzer.app.sys.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.sys.modules.models.Sys_suer;
import cn.wizzer.app.sys.modules.services.SysSuerService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class SysSuerServiceImpl extends BaseServiceImpl<Sys_suer> implements SysSuerService {
    public SysSuerServiceImpl(Dao dao) {
        super(dao);
    }
}
