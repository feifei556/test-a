package cn.wizzer.app.web.modules.controllers.platform.shop;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.shop.modules.models.Test_order_goods;
import cn.wizzer.app.shop.modules.services.TestOrderGoodsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/shop/order/goods")
public class TestOrderGoodsController{
    private static final Log log = Logs.get();
    @Inject
    private TestOrderGoodsService testOrderGoodsService;

    @At("")
    @Ok("beetl:/platform/shop/order/goods/index.html")
    @RequiresPermissions("platform.shop.order.goods")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.goods")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        cnd.and("payState","=","0");

		return testOrderGoodsService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/shop/order/goods/add.html")
    @RequiresPermissions("platform.shop.order.goods")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.goods.add")
    @SLog(tag = "Test_order_goods", msg = "${args[0].id}")
    public Object addDo(@Param("..")Test_order_goods testOrderGoods, HttpServletRequest req) {
		try {
			testOrderGoodsService.insert(testOrderGoods);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/shop/order/goods/edit.html")
    @RequiresPermissions("platform.shop.order.goods")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", testOrderGoodsService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.goods.edit")
    @SLog(tag = "Test_order_goods", msg = "${args[0].id}")
    public Object editDo(@Param("..")Test_order_goods testOrderGoods, HttpServletRequest req) {
		try {
            testOrderGoods.setOpBy(StringUtil.getUid());
			testOrderGoods.setOpAt((int) (System.currentTimeMillis() / 1000));
			testOrderGoodsService.updateIgnoreNull(testOrderGoods);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.shop.order.goods.delete")
    @SLog(tag = "Test_order_goods", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				testOrderGoodsService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				testOrderGoodsService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/shop/order/goods/detail.html")
    @RequiresPermissions("platform.shop.order.goods")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", testOrderGoodsService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
