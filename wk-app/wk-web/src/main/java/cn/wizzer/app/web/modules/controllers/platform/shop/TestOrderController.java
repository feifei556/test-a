package cn.wizzer.app.web.modules.controllers.platform.shop;

import cn.wizzer.app.shop.modules.models.Test_cart;
import cn.wizzer.app.shop.modules.models.Test_goods;
import cn.wizzer.app.shop.modules.models.Test_order_goods;
import cn.wizzer.app.shop.modules.services.TestCartService;
import cn.wizzer.app.shop.modules.services.TestGoodsService;
import cn.wizzer.app.shop.modules.services.TestOrderGoodsService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.shop.modules.models.Test_order;
import cn.wizzer.app.shop.modules.services.TestOrderService;
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
@At("/platform/shop/order")
public class TestOrderController{
    private static final Log log = Logs.get();
    @Inject
    private TestOrderService testOrderService;

    @Inject
    private TestGoodsService testGoodsService;

    @Inject
    private TestOrderGoodsService testOrderGoodsService;

    @Inject
    private TestCartService testCartService;


    @At("")
    @Ok("beetl:/platform/shop/order/index.html")
    @RequiresPermissions("platform.shop.order")
    public void index() {
    }
    @At("/addOrder")
    @Ok("beetl:/platform/shop/order/index.html")
    @RequiresPermissions("platform.shop.order")
    public void addOrder(HttpServletRequest req) {
        Test_order test_order=new Test_order();
        test_order.setOrderState("1");
        test_order.setPayState("0");
        testOrderService.insert(test_order);
        String id=test_order.getId();
        int payMoney=0;
        String[] arry=req.getParameterValues("goods");
        for(int i=0;i< arry.length;i++){
            int m=Integer.parseInt(req.getParameter(arry[i]));
            Test_order_goods test_order_goods=new Test_order_goods();
            test_order_goods.setOrderId(id);
            test_order_goods.setGoodNum(m);
            test_order_goods.setGoodId(arry[i]);
      Test_cart test_cart=testCartService.fetch(Cnd.where("goodId","=",arry[i]));
            test_cart.setState("0");
            testCartService.update(test_cart);
            Test_goods test_goods=testGoodsService.fetch(Cnd.where("id","=",arry[i]));
            test_order_goods.setGoodName(test_goods.getGoodName());
            test_order_goods.setRetailPrice(test_goods.getRetailPrice());
            testOrderGoodsService.insert(test_order_goods);
            payMoney+=m*test_goods.getRetailPrice();
            if(m<=test_goods.getGoodStore()){
                test_goods.setGoodStore(test_goods.getGoodStore()-m);
                test_goods.setGoodFrozenStore(m);

            }else{
                test_goods.setGoodStore(test_goods.getGoodStore());
                test_goods.setGoodFrozenStore(test_goods.getGoodStore());
            }

            testGoodsService.update(test_goods);

        }
        test_order.setPayMoney(payMoney);
        testOrderService.update(test_order);


    }


    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.shop.order")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return testOrderService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/shop/order/add.html")
    @RequiresPermissions("platform.shop.order")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.add")
    @SLog(tag = "Test_order", msg = "${args[0].id}")
    public Object addDo(@Param("..")Test_order testOrder, HttpServletRequest req) {
		try {
			testOrderService.insert(testOrder);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/shop/order/edit.html")
    @RequiresPermissions("platform.shop.order")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", testOrderService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.edit")
    @SLog(tag = "Test_order", msg = "${args[0].id}")
    public Object editDo(@Param("..")Test_order testOrder, HttpServletRequest req) {
		try {
            testOrder.setOpBy(StringUtil.getUid());
			testOrder.setOpAt((int) (System.currentTimeMillis() / 1000));
			testOrderService.updateIgnoreNull(testOrder);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.shop.order.delete")
    @SLog(tag = "Test_order", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				testOrderService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				testOrderService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/shop/order/detail.html")
    @RequiresPermissions("platform.shop.order")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", testOrderService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
