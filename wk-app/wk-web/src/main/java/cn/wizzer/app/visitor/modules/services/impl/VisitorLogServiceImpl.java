package cn.wizzer.app.visitor.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.visitor.modules.models.Visitor_log;
import cn.wizzer.app.visitor.modules.services.VisitorLogService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class VisitorLogServiceImpl extends BaseServiceImpl<Visitor_log> implements VisitorLogService {
    public VisitorLogServiceImpl(Dao dao) {
        super(dao);
    }
}
