package cn.wizzer.app.rp.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.rp.modules.models.Rp_redpacket;
import cn.wizzer.app.rp.modules.services.RpRedpacketService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class RpRedpacketServiceImpl extends BaseServiceImpl<Rp_redpacket> implements RpRedpacketService {
    public RpRedpacketServiceImpl(Dao dao) {
        super(dao);
    }
}
