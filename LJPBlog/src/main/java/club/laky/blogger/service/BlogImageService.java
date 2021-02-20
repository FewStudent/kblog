package club.laky.blogger.service;

import club.laky.blogger.pojo.BlogImage;

public interface BlogImageService {

	/**
	 * 添加一张图片的信息到数据库
	 * */
	public int insert(BlogImage blogImage);
	
	/**
	 * 根据图片的id获得一张图片的数据
	 * */
	public BlogImage queryImageById(Integer id);
	
	public BlogImage queryBlogImageByUrl(String name);
}
