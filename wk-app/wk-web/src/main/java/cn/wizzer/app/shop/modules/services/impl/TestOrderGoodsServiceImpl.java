package cn.wizzer.app.shop.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.shop.modules.models.Test_order_goods;
import cn.wizzer.app.shop.modules.services.TestOrderGoodsService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class TestOrderGoodsServiceImpl extends BaseServiceImpl<Test_order_goods> implements TestOrderGoodsService {
    public TestOrderGoodsServiceImpl(Dao dao) {
        super(dao);
    }
}
