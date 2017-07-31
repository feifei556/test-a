package cn.wizzer.app.shop.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.shop.modules.models.Test_order;
import cn.wizzer.app.shop.modules.services.TestOrderService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class TestOrderServiceImpl extends BaseServiceImpl<Test_order> implements TestOrderService {
    public TestOrderServiceImpl(Dao dao) {
        super(dao);
    }
}
