package club.laky.blogger.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "blog_comment")
public class BlogComment implements Serializable {
	@Id
	@Column(name = "comment_id")
	private Integer commentId;

	/**
	 * 评论者id
	 */
	@Column(name = "comment_observer_id")
	private Integer commentObserverId;

	/**
	 * 评论时间
	 */
	@Column(name = "comment_time")
	private String commentTime;

	/**
	 * 被评论博客id
	 */
	@Column(name = "comment_blog_id")
	private Integer commentBlogId;


	/**
	 * 评论内容
	 */
	@Column(name = "comment_content")
	private String commentContent;

	/**
	 * 0为屏蔽 1为显示
	 */
	@Column(name = "comment_state")
	private Integer commentState;

	@Transient
	private BlogUser commenter;

	@Transient
	private BlogArticles articles;

	@Transient
	private String observerName;
	
	@Transient
	private String articlesTitle;

	private static final long serialVersionUID = 1L;

	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public String getArticlesTitle() {
		return articlesTitle;
	}

	public void setArticlesTitle(String articlesTitle) {
		this.articlesTitle = articlesTitle;
	}

	public BlogUser getCommenter() {
		return commenter;
	}

	public void setCommenter(BlogUser commenter) {
		this.commenter = commenter;
	}

	public BlogArticles getArticles() {
		return articles;
	}

	public void setArticles(BlogArticles articles) {
		this.articles = articles;
	}

	public BlogUser getBlogUser() {
		return commenter;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.commenter = blogUser;
	}

	/**
	 * @return comment_id
	 */
	public Integer getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	/**
	 * 获取评论者id
	 *
	 * @return comment_observer_id - 评论者id
	 */
	public Integer getCommentObserverId() {
		return commentObserverId;
	}

	/**
	 * 设置评论者id
	 *
	 * @param commentObserverId
	 *            评论者id
	 */
	public void setCommentObserverId(Integer commentObserverId) {
		this.commentObserverId = commentObserverId ;
	}

	/**
	 * 获取评论时间
	 *
	 * @return comment_time - 评论时间
	 */
	public String getCommentTime() {
		return commentTime;
	}

	/**
	 * 设置评论时间
	 *
	 * @param commentTime
	 *            评论时间
	 */
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime == null ? null : commentTime.trim();
	}

	/**
	 * 获取被评论博客id
	 *
	 * @return comment_blog_id - 被评论博客id
	 */
	public Integer getCommentBlogId() {
		return commentBlogId;
	}

	/**
	 * 设置被评论博客id
	 *
	 * @param commentBlogId
	 *            被评论博客id
	 */
	public void setCommentBlogId(Integer commentBlogId) {
		this.commentBlogId = commentBlogId;
	}


	/**
	 * 获取评论内容
	 *
	 * @return comment_content - 评论内容
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * 设置评论内容
	 *
	 * @param commentContent
	 *            评论内容
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent == null ? null : commentContent.trim();
	}

	/**
	 * 获取0为屏蔽 1为显示
	 *
	 * @return comment_state - 0为屏蔽 1为显示
	 */
	public Integer getCommentState() {
		return commentState;
	}

	/**
	 * 设置0为屏蔽 1为显示
	 *
	 * @param commentState
	 *            0为屏蔽 1为显示
	 */
	public void setCommentState(Integer commentState) {
		this.commentState = commentState;
	}
}