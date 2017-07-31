package cn.wizzer.app.shop.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.shop.modules.models.Test_goods;
import cn.wizzer.app.shop.modules.services.TestGoodsService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class TestGoodsServiceImpl extends BaseServiceImpl<Test_goods> implements TestGoodsService {
    public TestGoodsServiceImpl(Dao dao) {
        super(dao);
    }
}
