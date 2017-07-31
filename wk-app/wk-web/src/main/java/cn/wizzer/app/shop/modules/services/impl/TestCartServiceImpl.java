package cn.wizzer.app.shop.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.shop.modules.models.Test_cart;
import cn.wizzer.app.shop.modules.services.TestCartService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class TestCartServiceImpl extends BaseServiceImpl<Test_cart> implements TestCartService {
    public TestCartServiceImpl(Dao dao) {
        super(dao);
    }
}
