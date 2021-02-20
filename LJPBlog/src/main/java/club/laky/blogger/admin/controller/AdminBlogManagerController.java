package club.laky.blogger.admin.controller;

import club.laky.blogger.pojo.BlogManager;
import club.laky.blogger.service.BlogManagerService;
import club.laky.blogger.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/manager")
public class AdminBlogManagerController {
    @Autowired
    BlogManagerService service;

    /**
     * 前往管路员管理页面的请求
     */
    @RequestMapping("manage")
    public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView andView = new ModelAndView();
        andView.addObject("_path", request.getServletContext().getContextPath());
        andView.addObject("user", request.getSession().getAttribute("user"));
        andView.setViewName("/blog/admin/managers");
        return andView;
    }

    /**
     * layui的表格数据
     *
     * @param page  页数
     * @param limit 单页数量
     * @param power 管路员权限
     * @param state 管路员状态
     * @return 返回符合layui表格格式的数据
     */
    @ResponseBody
    @RequestMapping(value = "/page", produces = "text/html;charset=UTF-8")
    public String page(int page, int limit, Integer power, Integer state, String content) {
        PageHelper<BlogManager> pageHelper;
        pageHelper = service.queryByFiled(content, power, state, page, limit);
        /* 将数据转换成符合layui表格的数据 */
        TableJson json = new TableJson(0, "获取数据成功", pageHelper.getTotolCount(), pageHelper.getData());
        return JsonUtils.objectToJson(json);
    }


    /**
     * 冻结管路员的请求
     *
     * @param id 授权管路员的id
     * @return 返回数据库修改条数
     */
    @ResponseBody
    @RequestMapping("freeze")
    public Integer freeze(@RequestParam Integer id) {
        BlogManager blogManager = new BlogManager();
        blogManager.setId(id);
        blogManager.setState(0);
        return service.updateById(blogManager);
    }

    /**
     * 管路员解冻的请求
     *
     * @param id 管路员的id
     * @return 返回数据库修改条数
     */
    @ResponseBody
    @RequestMapping("thaw")
    public Integer thaw(@RequestParam Integer id) {
        BlogManager blogManager = new BlogManager();
        blogManager.setId(id);
        blogManager.setState(1);
        return service.updateById(blogManager);
    }

    /**
     * 授权管路员的请求
     *
     * @param id 授权管路员的id
     * @return 返回数据库修改条数
     */
    @RequestMapping("allow")
    @ResponseBody
    public Integer allow(@RequestParam Integer id) {
        BlogManager blogManager = new BlogManager();
        blogManager.setId(id);
        blogManager.setPower(2);
        return service.updateById(blogManager);
    }

    /**
     * 收回管路员权利的请求
     *
     * @param id 收回管路员权利的请求
     * @return 返回数据库修改条数
     */
    @ResponseBody
    @RequestMapping("relieve")
    public Integer relieve(@RequestParam Integer id) {
        BlogManager blogManager = new BlogManager();
        blogManager.setId(id);
        blogManager.setPower(3);
        return service.updateById(blogManager);
    }

    /**
     * 跳转到登陆页面
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        return ModelCreater.createModelAndView(request, "/blog/admin/login");
    }

    /**
     * 管理员的登陆验证请求
     */
    @ResponseBody
    @RequestMapping("verify")
    public CommonResponse verify(HttpServletRequest request, @RequestBody BlogManager manager) {
        CommonResponse commonResponse = service.verify(manager);
        if (commonResponse.getStatus()) {
            request.getSession().setAttribute("admin", commonResponse.getData());
        }
        return commonResponse;
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping("toInsert")
    public ModelAndView toInsert(HttpServletRequest request) {
        return ModelCreater.createModelAndView(request, "/blog/admin_update/admin_add");
    }

    /**
     * 添加管理员
     */
    @ResponseBody
    @RequestMapping("insert")
    public CommonResponse insert(@RequestBody BlogManager manager) {
        int result = service.insert(manager);
        if (result != 1) {
            return CommonResponse.error("账号已存在");
        }
        return CommonResponse.isOk("添加成功");
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, Integer id) {
        return ModelCreater.createModelAndView(request, "/blog/admin_update/admin_update",
                "item", service.queryOneById(id));
    }

    /**
     * 管理员的修改请求：实际上只修改密码
     * */
    @ResponseBody
    @RequestMapping("updateManager")
    public CommonResponse updateManager(@RequestBody BlogManager manager) {
        int result = service.updateById(manager);
        if(result != 1){
            return CommonResponse.error("修改失败");
        }
        return CommonResponse.isOk("修改成功");
    }
}
