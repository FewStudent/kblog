package club.laky.blogger.service;

import java.util.List;

import club.laky.blogger.pojo.BlogLabel;
import club.laky.blogger.util.PageHelper;

public interface BlogLabelService {
	
	/** 添加一个标签 */
	public int insert(BlogLabel blogLabel);
	
	/** 删除一个标签 */
	public int delete(Integer id);
	
	/** 修改一个标签 */
	public int update(BlogLabel blogLabel);
	
	/** 标签加一 */
	public int updateQuickCount(String labelName);
	
	/** 根据Id获得一个标签 */
	public BlogLabel queryById(Integer id);
	
	/** 根据标签名获得一个标签 */
	public BlogLabel queryByName(String name);
	
	/** 获得所有标签 */
	public PageHelper<BlogLabel> queryAll();
	
	/** 获得点击量最多的标签 */
	public List<BlogLabel> qeuryByPickCount();
	
	/** 后台模糊查询 */
	public PageHelper<BlogLabel> queryByLikeName(Integer state,String likeName,Integer page,Integer pagelimit);
}
