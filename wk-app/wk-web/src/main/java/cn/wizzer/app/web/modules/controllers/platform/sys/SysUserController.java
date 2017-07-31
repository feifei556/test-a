package cn.wizzer.app.web.modules.controllers.platform.sys;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
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
@At("/platform/sys/user")
public class SysUserController{
    private static final Log log = Logs.get();
    @Inject
    private SysUserService sysUserService;

    @At("")
    @Ok("beetl:/platform/sys/user/index.html")
    @RequiresPermissions("platform.sys.user")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.sys.user")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return sysUserService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/sys/user/add.html")
    @RequiresPermissions("platform.sys.user")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.user.add")
    @SLog(tag = "Sys_user", msg = "${args[0].id}")
    public Object addDo(@Param("..")Sys_user sysUser, HttpServletRequest req) {
		try {
			sysUserService.insert(sysUser);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/sys/user/edit.html")
    @RequiresPermissions("platform.sys.user")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", sysUserService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.user.edit")
    @SLog(tag = "Sys_user", msg = "${args[0].id}")
    public Object editDo(@Param("..")Sys_user sysUser, HttpServletRequest req) {
		try {
            sysUser.setOpBy(StringUtil.getUid());
			sysUser.setOpAt((int) (System.currentTimeMillis() / 1000));
			sysUserService.updateIgnoreNull(sysUser);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.sys.user.delete")
    @SLog(tag = "Sys_user", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				sysUserService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				sysUserService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/sys/user/detail.html")
    @RequiresPermissions("platform.sys.user")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysUserService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
