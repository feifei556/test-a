package cn.wizzer.app.shop.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.shop.modules.models.Test_order_payment;
import cn.wizzer.app.shop.modules.services.TestOrderPaymentService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class TestOrderPaymentServiceImpl extends BaseServiceImpl<Test_order_payment> implements TestOrderPaymentService {
    public TestOrderPaymentServiceImpl(Dao dao) {
        super(dao);
    }
}
