package cn.wizzer.app.web.modules.controllers.platform.shop;

import cn.wizzer.app.shop.modules.models.Test_cart;
import cn.wizzer.app.shop.modules.models.Test_order_goods;
import cn.wizzer.app.shop.modules.services.TestCartService;
import cn.wizzer.app.shop.modules.services.TestOrderGoodsService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.shop.modules.models.Test_goods;
import cn.wizzer.app.shop.modules.services.TestGoodsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
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
@At("/platform/shop/goods")
public class TestGoodsController{
    private static final Log log = Logs.get();
    @Inject
    private TestGoodsService testGoodsService;
    @Inject
    private TestOrderGoodsService testOrderGoodsService;
    @Inject
    private TestCartService testCartService;

    @At("")
    @Ok("beetl:/platform/shop/goods/index.html")
    @RequiresPermissions("platform.shop.goods")
    public void index() {
    }

    @At("/list")
    @Ok("beetl:/platform/shop/goods/list.html")
    @RequiresPermissions("platform.shop.goods")
    public void list() {
    }


    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.shop.goods")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return testGoodsService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/shop/goods/add.html")
    @RequiresPermissions("platform.shop.goods")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.goods.add")
    @SLog(tag = "Test_goods", msg = "${args[0].id}")
    public Object addDo(@Param("..")Test_goods testGoods, HttpServletRequest req) {
		try {

            testGoodsService.insert(testGoods);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/shop/goods/edit.html")
    @RequiresPermissions("platform.shop.goods")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", testGoodsService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.goods.edit")
    @SLog(tag = "Test_goods", msg = "${args[0].id}")
    public Object editDo(@Param("..")Test_goods testGoods, HttpServletRequest req) {
		try {
            testGoods.setOpBy(StringUtil.getUid());
			testGoods.setOpAt((int) (System.currentTimeMillis() / 1000));
			testGoodsService.updateIgnoreNull(testGoods);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.shop.goods.delete")
    @SLog(tag = "Test_goods", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				testGoodsService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				testGoodsService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/shop/goods/detail.html")
    @RequiresPermissions("platform.shop.goods")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", testGoodsService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }
    @At("/addCart/?")
    @Ok("beetl:/platform/shop/cart/list.html")
    @RequiresPermissions("platform.shop.goods")
    public void addCart(String id, HttpServletRequest req) {
        if(id!=null){
            Test_cart test_cart=testCartService.fetch(Cnd.where("goodId","=",id).and("state","=","1"));
        if(test_cart!=null){
            return  ;
        }else{
            Test_cart testcart=new Test_cart();
            testcart.setGoodId(id);
            testcart.setState("1");
            testCartService.insert(testcart);
        }
        }
    }

}
