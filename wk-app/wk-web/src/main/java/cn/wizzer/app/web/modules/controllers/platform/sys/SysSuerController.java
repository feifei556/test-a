package cn.wizzer.app.web.modules.controllers.platform.sys;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.sys.modules.models.Sys_suer;
import cn.wizzer.app.sys.modules.services.SysSuerService;
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
@At("/platform/sys/suer")
public class SysSuerController{
    private static final Log log = Logs.get();
    @Inject
    private SysSuerService sysSuerService;

    @At("")
    @Ok("beetl:/platform/sys/suer/index.html")
    @RequiresPermissions("platform.sys.suer")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.sys.suer")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return sysSuerService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/sys/suer/add.html")
    @RequiresPermissions("platform.sys.suer")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.suer.add")
    @SLog(tag = "Sys_suer", msg = "${args[0].id}")
    public Object addDo(@Param("..")Sys_suer sysSuer, HttpServletRequest req) {
		try {
			sysSuerService.insert(sysSuer);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/sys/suer/edit.html")
    @RequiresPermissions("platform.sys.suer")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", sysSuerService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.suer.edit")
    @SLog(tag = "Sys_suer", msg = "${args[0].id}")
    public Object editDo(@Param("..")Sys_suer sysSuer, HttpServletRequest req) {
		try {
            sysSuer.setOpBy(StringUtil.getUid());
			sysSuer.setOpAt((int) (System.currentTimeMillis() / 1000));
			sysSuerService.updateIgnoreNull(sysSuer);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.sys.suer.delete")
    @SLog(tag = "Sys_suer", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				sysSuerService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				sysSuerService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/sys/suer/detail.html")
    @RequiresPermissions("platform.sys.suer")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysSuerService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
