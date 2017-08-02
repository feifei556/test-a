package cn.wizzer.app.web.modules.controllers.platform.wx;

import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.wx.modules.services.WxAddressService;
import cn.wizzer.app.wx.modules.services.WxConfigService;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.weixin.at.impl.MemoryJsapiTicketStore;
import org.nutz.weixin.spi.WxApi2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@IocBean
@At("/platform/wx/add")
public class WxAddressController{
    private static final Log log = Logs.get();
    @Inject
    private WxAddressService wxAddressService;
    @Inject
    private WxConfigService wxConfigService;



//    @At("")
//    @Ok("beetl:/platform/wx/address/index.html")
//    @RequiresPermissions("platform.wx.address")
//    public void index() {
//    }
//
//    @At("/data")
//    @Ok("json")
//    @RequiresPermissions("platform.wx.address")
//    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
//		Cnd cnd = Cnd.NEW();
//    	return wxAddressService.data(length, start, draw, order, columns, cnd, null);
//    }
//
//    @At("/add")
//    @Ok("beetl:/platform/wx/address/add.html")
//    @RequiresPermissions("platform.wx.address")
//    public void add() {
//
//    }
//
//    @At("/addDo")
//    @Ok("json")
//    @RequiresPermissions("platform.wx.address.add")
//    @SLog(tag = "Wx_address", msg = "${args[0].id}")
//    public Object addDo(@Param("..")Wx_address wxAddress, HttpServletRequest req) {
//		try {
//			wxAddressService.insert(wxAddress);
//			return Result.success("system.success");
//		} catch (Exception e) {
//			return Result.error("system.error");
//		}
//    }
//
//    @At("/edit/?")
//    @Ok("beetl:/platform/wx/address/edit.html")
//    @RequiresPermissions("platform.wx.address")
//    public void edit(String id,HttpServletRequest req) {
//		req.setAttribute("obj", wxAddressService.fetch(id));
//    }
//
//    @At("/editDo")
//    @Ok("json")
//    @RequiresPermissions("platform.wx.address.edit")
//    @SLog(tag = "Wx_address", msg = "${args[0].id}")
//    public Object editDo(@Param("..")Wx_address wxAddress, HttpServletRequest req) {
//		try {
//            wxAddress.setOpBy(StringUtil.getUid());
//			wxAddress.setOpAt((int) (System.currentTimeMillis() / 1000));
//			wxAddressService.updateIgnoreNull(wxAddress);
//			return Result.success("system.success");
//		} catch (Exception e) {
//			return Result.error("system.error");
//		}
//    }
//
//    @At({"/delete/?", "/delete"})
//    @Ok("json")
//    @RequiresPermissions("platform.wx.address.delete")
//    @SLog(tag = "Wx_address", msg = "${req.getAttribute('id')}")
//    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
//		try {
//			if(ids!=null&&ids.length>0){
//				wxAddressService.delete(ids);
//    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
//			}else{
//				wxAddressService.delete(id);
//    			req.setAttribute("id", id);
//			}
//            return Result.success("system.success");
//        } catch (Exception e) {
//            return Result.error("system.error");
//        }
//    }
//
//    @At("/detail/?")
//    @Ok("beetl:/platform/wx/address/detail.html")
//    @RequiresPermissions("platform.wx.address")
//	public void detail(String id, HttpServletRequest req) {
//		if (!Strings.isBlank(id)) {
//            req.setAttribute("obj", wxAddressService.fetch(id));
//		}else{
//            req.setAttribute("obj", null);
//        }
//    }


    @At("/index/?")
    @Ok("beetl:/public/add/index.html")
    public void index(String wxid,HttpServletRequest req, HttpSession session) {
        WxApi2 wxApi2 = wxConfigService.getWxApi2(wxid);
        if (Lang.isEmpty(Globals.memoryJsapiTicketStore.get(wxid))) {
            Globals.memoryJsapiTicketStore.put(wxid, new MemoryJsapiTicketStore());
        }
        MemoryJsapiTicketStore memoryJsapiTicketStore = Globals.memoryJsapiTicketStore.get(wxid);
        wxApi2.setJsapiTicketStore(memoryJsapiTicketStore);
        String url = "http://" + Globals.AppDomain + Globals.AppBase + "/public/wx/add/index/"+wxid;
        NutMap jsConfig = wxApi2.genJsSDKConfig(url, "getLocation");

        req.setAttribute("list", wxAddressService.query(Cnd.orderBy().asc("opAt")));
        req.setAttribute("jsConfig", Json.toJson(jsConfig));
    }
}
