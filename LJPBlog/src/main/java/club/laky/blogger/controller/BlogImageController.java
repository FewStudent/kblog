package club.laky.blogger.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import club.laky.blogger.service.BlogImageService;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.TableJson;
import club.laky.blogger.pojo.BlogImage;
import club.laky.blogger.pojo.BlogUser;

@Controller
@RequestMapping("/image")
public class BlogImageController {

	@Autowired
	BlogImageService service;


	/**
	 * 保存图片信息到数据库
	 */
	@ResponseBody
	@RequestMapping("upload")
	public String upload(HttpServletRequest request, @RequestParam MultipartFile files[]) throws IOException {
		String projectName = request.getContextPath();
		BlogImage picture = new BlogImage();
		String pictureUrl;
		MultipartFile file = files[0];
		if (!file.isEmpty()) {
			//文件原名
			String originalFileName = file.getOriginalFilename();
			//文件后缀名
			String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."));
			/* 文件名 */
			String fileName = UUID.randomUUID() + suffixName;
			/* 文件路径 */
			String path = null;
			/* 文件类型 */
			String type = null;
			/* 获取文件的类型 */
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					/* 获取项目在容器中实际发布运行的根路径 */
					String realPath = request.getSession().getServletContext().getRealPath("/");
					/* 设置存放图片文件的路径 */
					path = realPath + fileName;
					/* 把图片保存到指定位置 */
					file.transferTo(new File(path));
					/* 图片保存路径 */
					pictureUrl = projectName + "/" +  fileName;
					picture.setImageUrl(pictureUrl);
					picture.setImageUploadTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(Calendar.getInstance().getTime()).toString());
					BlogUser blogUser = (BlogUser) request.getSession().getAttribute("user");
					if (blogUser != null) {
						picture.setImageUploaderId(blogUser.getUserId());
					}
					// 如果没有重复的图片,则记录进数据库
					if (service.queryBlogImageByUrl(pictureUrl) == null) {
						service.insert(picture);
					}
				} else {
					return "1";
				}
			} else {
				return "2";
			}
		} else {
			return "3";
		}
		return pictureUrl;
	}
	
	@ResponseBody
	@RequestMapping("layuiUpload")
	public String layuiUpload(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
		TableJson json = new TableJson();
		String projectName = request.getContextPath();
		if(!file.isEmpty()) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/");
				/* 设置存放图片文件的路径 */
				//文件原名
				String originalFileName = file.getOriginalFilename();
				//文件后缀名
				String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."));
				/* 文件名 */
				String fileName = UUID.randomUUID() + suffixName;
				//文件保存路径
				String path = realPath + fileName;
				/* 把图片保存到指定位置 */
				file.transferTo(new File(path));
				
				String pictureUrl = projectName +  "/" +  fileName;
				
				Map<String, String> map = new HashMap<>();
				map.put("src", pictureUrl);
				json.setCode(0);
				json.setMsg("成功了");
				json.setCount(1);
				json.setData(map);
				return JsonUtils.objectToJson(json);
			} catch (Exception e) {
				return JsonUtils.objectToJson(new TableJson(0,"上传失败",0,null));
			}
		}else {
			return JsonUtils.objectToJson(new TableJson(0,"上传失败",0,null));
		}
	}
}
