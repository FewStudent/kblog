package club.laky.blogger.mapper;


import club.laky.blogger.pojo.BlogLabel;
import tk.mybatis.mapper.common.Mapper;

public interface BlogLabelMapper extends Mapper<BlogLabel> {
	
	public int updateQuickCount(String labelName);
}