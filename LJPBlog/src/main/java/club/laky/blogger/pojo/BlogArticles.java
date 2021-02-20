package club.laky.blogger.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Table(name = "blog_articles")
public class BlogArticles implements Serializable {
    @Id
    @Column(name = "articles_id")
    private Integer articlesId;

    /**
     * 文章标题
     */
    @Column(name = "articles_title")
    private String articlesTitle;

    /**
     * 文章标签名
     */
    @Column(name = "articles_type")
    private String articlesType;

    /**
     * 文章状态 0 审核 1 正常 2 屏蔽
     */
    @Column(name = "articles_state")
    private Integer articlesState;

    /**
     * 作者id
     */
    @Column(name = "articles_author_id")
    private Integer articlesAuthorId;

    /**
     * 能否评论 0 不能 1 能
     */
    @Column(name = "articles_commentable")
    private Integer articlesCommentable;

    /**
     * 置顶 0 不置顶 1 首页 2 分类
     */
    @Column(name = "articles_isstick")
    private Integer articlesIsstick;

    /**
     * 文章点赞数量
     */
    @Column(name = "articles_praise_count")
    private Integer articlesPraiseCount;

    /**
     * 文章评论数量
     */
    @Column(name = "articles_comment_count")
    private Integer articlesCommentCount;

    /**
     * 文章观看数量
     */
    @Column(name = "articles_view_count")
    private Integer articlesViewCount;

    /**
     * 文章配图的url
     */
    @Column(name = "articles_img_url")
    private String articlesImgUrl;

    /**
     * 文章的发布时间
     */
    @Column(name = "articles_create_date")
    private String articlesCreateDate;

    /**
     * 文章内容
     */
    @Column(name = "articles_content")
    private String articlesContent;
    
    @Transient
    private String authorName;
    
    /** 与文章相关联用户 */
    @Transient
    private BlogUser blogUser;
    
    /** 与文章相关的评论 */
    @Transient
    private List<BlogComment> blogComments;
    
    /** 文章贴上的标签     */
	@Transient
    private List<BlogLabel> blogLabels;
    
	private static final long serialVersionUID = 1L;

    
    public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
    
    public List<BlogComment> getBlogComments() {
		return blogComments;
	}

	public void setBlogComments(List<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}

	public List<BlogLabel> getBlogLabels() {
		return blogLabels;
	}

	public void setBlogLabels(List<BlogLabel> blogLabels) {
		this.blogLabels = blogLabels;
	}


    /**
     * @return articles_id
     */
    public Integer getArticlesId() {
        return articlesId;
    }

    /**
     * @param articlesId
     */
    public void setArticlesId(Integer articlesId) {
        this.articlesId = articlesId;
    }

    /**
     * 获取文章标题
     *
     * @return articles_title - 文章标题
     */
    public String getArticlesTitle() {
        return articlesTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articlesTitle 文章标题
     */
    public void setArticlesTitle(String articlesTitle) {
        this.articlesTitle = articlesTitle == null ? null : articlesTitle.trim();
    }

    /**
     * 获取文章标签名
     *
     * @return articles_type - 文章标签名
     */
    public String getArticlesType() {
        return articlesType;
    }

    /**
     * 设置文章标签名
     *
     * @param articlesType 文章标签名
     */
    public void setArticlesType(String articlesType) {
        this.articlesType = articlesType == null ? null : articlesType.trim();
    }

    /**
     * 获取文章状态 0 审核 1 正常 2 屏蔽
     *
     * @return articles_state - 文章状态 0 审核 1 正常 2 屏蔽
     */
    public Integer getArticlesState() {
        return articlesState;
    }

    /**
     * 设置文章状态 0 审核 1 正常 2 屏蔽
     *
     * @param articlesState 文章状态 0 审核 1 正常 2 屏蔽
     */
    public void setArticlesState(Integer articlesState) {
        this.articlesState = articlesState;
    }

    /**
     * 获取作者id
     *
     * @return articles_author_id - 作者id
     */
    public Integer getArticlesAuthorId() {
        return articlesAuthorId;
    }

    /**
     * 设置作者id
     *
     * @param articlesAuthorId 作者id
     */
    public void setArticlesAuthorId(Integer articlesAuthorId) {
        this.articlesAuthorId = articlesAuthorId;
    }

    /**
     * 获取能否评论 0 不能 1 能
     *
     * @return articles_commentable - 能否评论 0 不能 1 能
     */
    public Integer getArticlesCommentable() {
        return articlesCommentable;
    }

    /**
     * 设置能否评论 0 不能 1 能
     *
     * @param articlesCommentable 能否评论 0 不能 1 能
     */
    public void setArticlesCommentable(Integer articlesCommentable) {
        this.articlesCommentable = articlesCommentable;
    }

    /**
     * 获取置顶 0 不置顶 1 首页 2 分类
     *
     * @return articles_isstick - 置顶 0 不置顶 1 首页 2 分类
     */
    public Integer getArticlesIsstick() {
        return articlesIsstick;
    }

    /**
     * 设置置顶 0 不置顶 1 首页 2 分类
     *
     * @param articlesIsstick 置顶 0 不置顶 1 首页 2 分类
     */
    public void setArticlesIsstick(Integer articlesIsstick) {
        this.articlesIsstick = articlesIsstick;
    }

    /**
     * 获取文章点赞数量
     *
     * @return articles_praise_count - 文章点赞数量
     */
    public Integer getArticlesPraiseCount() {
        return articlesPraiseCount;
    }

    /**
     * 设置文章点赞数量
     *
     * @param articlesPraiseCount 文章点赞数量
     */
    public void setArticlesPraiseCount(Integer articlesPraiseCount) {
        this.articlesPraiseCount = articlesPraiseCount;
    }

    /**
     * 获取文章评论数量
     *
     * @return articles_comment_count - 文章评论数量
     */
    public Integer getArticlesCommentCount() {
        return articlesCommentCount;
    }

    /**
     * 设置文章评论数量
     *
     * @param articlesCommentCount 文章评论数量
     */
    public void setArticlesCommentCount(Integer articlesCommentCount) {
        this.articlesCommentCount = articlesCommentCount;
    }

    /**
     * 获取文章观看数量
     *
     * @return articles_view_count - 文章观看数量
     */
    public Integer getArticlesViewCount() {
        return articlesViewCount;
    }

    /**
     * 设置文章观看数量
     *
     * @param articlesViewCount 文章观看数量
     */
    public void setArticlesViewCount(Integer articlesViewCount) {
        this.articlesViewCount = articlesViewCount;
    }

    /**
     * 获取文章配图的url
     *
     * @return articles_img_url - 文章配图的url
     */
    public String getArticlesImgUrl() {
        return articlesImgUrl;
    }

    /**
     * 设置文章配图的url
     *
     * @param articlesImgUrl 文章配图的url
     */
    public void setArticlesImgUrl(String articlesImgUrl) {
        this.articlesImgUrl = articlesImgUrl == null ? null : articlesImgUrl.trim();
    }

    /**
     * 获取文章的发布时间
     *
     * @return articles_create_date - 文章的发布时间
     */
    public String getArticlesCreateDate() {
        return articlesCreateDate;
    }

    /**
     * 设置文章的发布时间
     *
     * @param articlesCreateDate 文章的发布时间
     */
    public void setArticlesCreateDate(String articlesCreateDate) {
        this.articlesCreateDate = articlesCreateDate == null ? null : articlesCreateDate.trim();
    }

    /**
     * 获取文章内容
     *
     * @return articles_content - 文章内容
     */
    public String getArticlesContent() {
        return articlesContent;
    }

    /**
     * 设置文章内容
     *
     * @param articlesContent 文章内容
     */
    public void setArticlesContent(String articlesContent) {
        this.articlesContent = articlesContent == null ? null : articlesContent.trim();
    }

	public BlogUser getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}
    
}