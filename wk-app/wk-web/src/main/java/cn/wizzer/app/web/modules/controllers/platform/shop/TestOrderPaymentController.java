package cn.wizzer.app.web.modules.controllers.platform.shop;

import cn.wizzer.app.shop.modules.models.Test_order;
import cn.wizzer.app.shop.modules.models.Test_order_goods;
import cn.wizzer.app.shop.modules.services.TestOrderService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.shop.modules.models.Test_order_payment;
import cn.wizzer.app.shop.modules.services.TestOrderPaymentService;
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
@At("/platform/shop/order/payment")
public class TestOrderPaymentController{
    private static final Log log = Logs.get();
    @Inject
    private TestOrderPaymentService testOrderPaymentService;

    @Inject
    private SysUserService sysUserService;

    @Inject
    private TestOrderService testOrderService;

    @At("")
    @Ok("beetl:/platform/shop/order/payment/index.html")
    @RequiresPermissions("platform.shop.order.payment")
    public void index() {
    }


    @At("/pay/?")
    @Ok("beetl:/platform/shop/order/payment/payList.html")
    @RequiresPermissions("platform.shop.order.payment")
    public void pay(String orderId,HttpServletRequest req) {
        Test_order test_order = testOrderService.fetch(Cnd.where("id", "=", orderId));
      Sys_user sys_user=(Sys_user)req.getSession().getAttribute("user");
      if(sys_user!=null){
          req.setAttribute("user", sys_user);
      }
        req.setAttribute("order", test_order);

    }

    @At("/payMoney")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.payment")
    public Object pay(HttpServletRequest req) {
        String  orderId=Strings.sBlank(req.getParameter("orderId"));
        String  payType=Strings.sBlank(req.getParameter("payMethod"));

        String  payPass=Strings.sBlank(req.getParameter("payPass"));
        int pay=Integer.parseInt(req.getParameter("pay"));
     Sys_user user=sysUserService.fetch(Cnd.where("id","=",orderId));
     int count=0;
     if(user!=null){
         if(user.getPayPassword().equals(payPass)){
             if(user.getMoney()>pay){
              user.setMoney(user.getMoney()-pay);
                 sysUserService.update(user);
                 Test_order_payment test_order_payment=new Test_order_payment();
                 test_order_payment.setOrderId(orderId);
                 test_order_payment.setPay(pay);
                 test_order_payment.setPayMethod(payType);
                int time =(int)(System.currentTimeMillis()/1000);
                 test_order_payment.setPayTime(time);
                 testOrderPaymentService.insert(test_order_payment);
           Test_order test_order=testOrderService.fetch(Cnd.where("id","=",orderId));
           test_order.setPayState("1");
                 testOrderService.update(test_order);
                 return Result.success("支付成功");
             }else{
                 return Result.error("余额不足,无法支付");
             }
         }else{
            return Result.error("密码错误");
         }
     }
    return  "";

    }





    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.payment")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return testOrderPaymentService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/shop/order/payment/add.html")
    @RequiresPermissions("platform.shop.order.payment")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.payment.add")
    @SLog(tag = "Test_order_payment", msg = "${args[0].id}")
    public Object addDo(@Param("..")Test_order_payment testOrderPayment, HttpServletRequest req) {
		try {
			testOrderPaymentService.insert(testOrderPayment);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/shop/order/payment/edit.html")
    @RequiresPermissions("platform.shop.order.payment")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", testOrderPaymentService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.shop.order.payment.edit")
    @SLog(tag = "Test_order_payment", msg = "${args[0].id}")
    public Object editDo(@Param("..")Test_order_payment testOrderPayment, HttpServletRequest req) {
		try {
            testOrderPayment.setOpBy(StringUtil.getUid());
			testOrderPayment.setOpAt((int) (System.currentTimeMillis() / 1000));
			testOrderPaymentService.updateIgnoreNull(testOrderPayment);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.shop.order.payment.delete")
    @SLog(tag = "Test_order_payment", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				testOrderPaymentService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				testOrderPaymentService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/shop/order/payment/detail.html")
    @RequiresPermissions("platform.shop.order.payment")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", testOrderPaymentService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
