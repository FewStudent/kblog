package club.laky.blogger.util;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * 视图模型构造工具
 * */
public class ModelCreater {

    /**
     * 仅仅做跳转
     */
    public static ModelAndView createModelAndView(HttpServletRequest request, String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("_path", request.getServletContext().getContextPath());
        modelAndView.setViewName(url);
        return modelAndView;
    }

    /**
     * 携带很多数据
     *
     * @param maps 携带的数据映射
     */
    public static ModelAndView createModelAndView(HttpServletRequest request, Map<String, Object> maps, String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("_path", request.getServletContext().getContextPath());

        Set<String> keys = maps.keySet();
        for (String key : keys) {
            modelAndView.addObject(key, maps.get(key));
        }
        modelAndView.setViewName(url);
        return modelAndView;
    }

    /**
     * 携带一个对象
     *
     * @param obj  对象
     * @param name 对象的映射名
     * @param url  前往地址
     */
    public static ModelAndView createModelAndView(HttpServletRequest request, String url, String name, Object obj) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("_path", request.getServletContext().getContextPath());
        modelAndView.addObject(name, obj);
        modelAndView.setViewName(url);
        return modelAndView;
    }
}