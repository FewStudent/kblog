package club.laky.blogger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.laky.blogger.pojo.BlogLabel;
import club.laky.blogger.service.BlogLabelService;
import club.laky.blogger.util.CommonResponse;

@Controller
@RequestMapping("/label")
public class BlogLaebelController {

	@Autowired
	BlogLabelService service;

	@RequestMapping("insert")
	@ResponseBody
	public CommonResponse insert(@RequestBody BlogLabel label) {
		int result = service.insert(label);
		if (result == 0) {
			return CommonResponse.error("添加失败");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("update")
	@ResponseBody
	public CommonResponse update(@RequestBody BlogLabel label) {
		int result = service.update(label);
		if (result == 0) {
			return CommonResponse.error("没有找到可修改的数据");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("delete")
	@ResponseBody
	public CommonResponse delete(@RequestParam Integer id) {
		int result = service.delete(id);
		if (result == 0) {
			return CommonResponse.error("删除失败了");
		}
		return CommonResponse.isOk(result);
	}

	@RequestMapping("selectById")
	@ResponseBody
	public CommonResponse selectById(Integer id) {
		BlogLabel blogLabel = service.queryById(id);
		return CommonResponse.isOk(blogLabel);
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public CommonResponse selectAll() {
		return CommonResponse.isOk(service.queryAll().getData());
	}

	@RequestMapping("selectHot")
	@ResponseBody
	public CommonResponse selectByAid() {
		List<BlogLabel> lists = service.qeuryByPickCount();
		return CommonResponse.isOk(lists);
	}

}
