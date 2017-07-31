package cn.wizzer.app.web.modules.controllers.platform.shop;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.shop.modules.models.Test_cart;
import cn.wizzer.app.shop.modules.services.TestCartService;
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
@At("/platform/shop/cart")
public class TestCartController{
    private static final Log log = Logs.get();
    @Inject
    private TestCartService testCartService;

    @At("")
    @Ok("beetl:/platform/shop/cart/list.html")
    @RequiresPermissions("platform.shop.cart")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.shop.cart")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
		cnd.and("state","=","1");
    	return testCartService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/shop/cart/add.html")
    @RequiresPermissions("platform.shop.cart")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.cart.add")
    @SLog(tag = "Test_cart", msg = "${args[0].id}")
    public Object addDo(@Param("..")Test_cart testCart, HttpServletRequest req) {
		try {
			testCartService.insert(testCart);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/shop/cart/edit.html")
    @RequiresPermissions("platform.shop.cart")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", testCartService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.cart.edit")
    @SLog(tag = "Test_cart", msg = "${args[0].id}")
    public Object editDo(@Param("..")Test_cart testCart, HttpServletRequest req) {
		try {
            testCart.setOpBy(StringUtil.getUid());
			testCart.setOpAt((int) (System.currentTimeMillis() / 1000));
			testCartService.updateIgnoreNull(testCart);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.shop.cart.delete")
    @SLog(tag = "Test_cart", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				testCartService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				testCartService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/shop/cart/detail.html")
    @RequiresPermissions("platform.shop.cart")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", testCartService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
