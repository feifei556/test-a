package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_product;
import cn.wizzer.app.rp.modules.services.RpProductService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpProductServiceImpl extends BaseServiceImpl<Rp_product> implements RpProductService {
    public RpProductServiceImpl(Dao dao) {
        super(dao);
    }
}
