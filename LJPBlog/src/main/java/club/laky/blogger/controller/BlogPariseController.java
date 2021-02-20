package club.laky.blogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.laky.blogger.service.BlogPraiseService;
import club.laky.blogger.pojo.BlogPraise;

@Controller
@RequestMapping("/parise")
public class BlogPariseController {

	@Autowired
	BlogPraiseService service;
	
	/**	修改点赞情况
	 * @return	result	返回点赞情况:1、点亮 0、熄灭	
	 * @param	praise	赞的实体
	 * */
	@RequestMapping("toggle")
	@ResponseBody
	public int toggle(@RequestBody BlogPraise praise) {
		int result = 0;
		
		Integer userId = praise.getPraiseUserId();
		Integer articlesId = praise.getPraiseArticlesId();
		
		/*	根据用户的id和文章的id	查找到相关的赞	*/
		BlogPraise blogPraise = service.queryById(articlesId,userId);
		
		/*	如果没有赞	就添加一个到数据库	*/
		if(blogPraise == null) {
			praise.setPraiseFlag(1);
			result = service.insert(praise);
		}else {
			/*	如果有赞	根据赞的情况取反	*/
			if(blogPraise.getPraiseFlag() == 0) {
				blogPraise.setPraiseFlag(1);
				result = 1;
			}else {
				blogPraise.setPraiseFlag(0);
				result = 0;
			}
			/*	最后修改点赞的情况	*/
			service.update(blogPraise);
		}
		
		return result;
	}
	
}
