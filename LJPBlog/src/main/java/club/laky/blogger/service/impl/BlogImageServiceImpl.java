package club.laky.blogger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogImageMapper;
import club.laky.blogger.service.BlogImageService;
import club.laky.blogger.pojo.BlogImage;
import club.laky.blogger.util.IDConstructor;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogImageServiceImpl implements BlogImageService {
	
	@Autowired
	BlogImageMapper mapper;

	/**	添加一张图片的信息到数据库
	 * @return	返回数据库变化的条数
	 * @param	blogImage	图片的实体
	 * */
	@Override
	public int insert(BlogImage blogImage) {
		return mapper.insertSelective(blogImage);
	}

	/**	根据id获得图片的信息
	 * @return	返回图片的信息
	 * @param	id		图片的id
	 * */
	@Override
	public BlogImage queryImageById(Integer id) {
		Example example = new Example(BlogImage.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("imageId",id);
		return mapper.selectOneByExample(example);
	}

	@Override
	public BlogImage queryBlogImageByUrl(String name) {
		Example example = new Example(BlogImage.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("imageUrl",name);
		return mapper.selectOneByExample(example);
	}

}
