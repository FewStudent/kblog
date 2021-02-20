package club.laky.blogger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogArticlesLabel;
import club.laky.blogger.service.BlogArticlesLabelService;
import club.laky.blogger.util.CommonResponse;

@Controller
@RequestMapping("/balabel")
public class BlogArticlesLabelController {

	@Autowired
	BlogArticlesLabelService service;

	@RequestMapping("insert")
	@ResponseBody
	public CommonResponse insert(@RequestParam Integer labelId,@RequestParam Integer articlesId) {
		if (service.queryByAIdAndLId(articlesId, labelId) != null) {
			return CommonResponse.error("请不要重复添加标签");
		}
		BlogArticlesLabel articlesLabel = new BlogArticlesLabel();
		articlesLabel.setArticlesId(articlesId);
		articlesLabel.setLabelId(labelId);
		int result = service.insertTx(articlesLabel);
		if(result == 0) {
			return CommonResponse.error("添加失败了");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("delete")
	@ResponseBody
	public CommonResponse delete(@RequestParam Integer id) {
		int result = service.deleteTx(id);
		if(result == 0) {
			return CommonResponse.error("删除失败");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("update")
	@ResponseBody
	public CommonResponse update(@RequestBody BlogArticlesLabel blogArticlesLabel) {
		int result = service.updateTx(blogArticlesLabel.getBalState(),blogArticlesLabel.getBlogLAId());
		if(result == 0) {
			return CommonResponse.error("修改失败");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("selectById")
	@ResponseBody
	public CommonResponse selectByArticelsId(Integer id) {
		List<BlogArticlesLabel> articlesLabels = service.queryByArticlesIdTx(id);
		if(articlesLabels == null || articlesLabels.size() == 0) {
			return CommonResponse.error("没有数据");
		}
		return CommonResponse.isOk(articlesLabels);
	}

	@RequestMapping("toInsert")
	public ModelAndView toInsert(HttpServletRequest request,HttpServletResponse response,String articlesId) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path", request.getServletContext().getContextPath());
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.addObject("articlesId",articlesId);
		andView.setViewName("/blog/add_articles_label");
		return andView;
	}

}
