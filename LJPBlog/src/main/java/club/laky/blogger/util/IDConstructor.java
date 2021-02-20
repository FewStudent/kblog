package club.laky.blogger.util;

import java.util.UUID;

public class IDConstructor {

	/** 创建用户id */
	public static String createUserId() {
		/* BU代表BlogUser  */
		StringBuffer buffer = new StringBuffer("BU");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	/** 创建文章id */
	public static String creatArticlesId() {
		/* BA代表BlogArticles */
		StringBuffer buffer = new StringBuffer("BA");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	/** 创建评论id */
	public static String creatCommentId() {
		/* BC代表BlogComment */
		StringBuffer buffer = new StringBuffer("BC");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	/** 创建标签id */
	public static String creatLabelId() {
		/* BL代表BlogLabel */
		StringBuffer buffer = new StringBuffer("BL");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	public static String creatALabelId() {
		/* BL代表BlogLabel */
		StringBuffer buffer = new StringBuffer("BAL");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	/** 创建赞id */
	public static String creatPraiseId() {
		/* BP代表BlogPraise */
		StringBuffer buffer = new StringBuffer("BP");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
	
	/** 创建图片id */
	public static String creatImageId() {
		/* BL代表BlogImage */
		StringBuffer buffer = new StringBuffer("BI");
		buffer.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
		return buffer.toString();
	}
}
