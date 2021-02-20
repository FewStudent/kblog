package club.laky.blogger.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "blog_articles_label")
public class BlogArticlesLabel implements Serializable {
	@Id
	@Column(name = "blog_l_a_id")
	private Integer blogLAId;

	@Column(name = "label_id")
	private Integer labelId;

	@Column(name = "articles_id")
	private Integer articlesId;

	@Column(name = "bal_state")
	private Integer balState;

	/** 对应的文章 */
	@Transient
	private String articlesTitle;

	/** 对应的标签 */
	@Transient
	private String labelName;

	private static final long serialVersionUID = 1L;

	public String getArticlesTitle() {
		return articlesTitle;
	}

	public void setArticlesTitle(String articlesTitle) {
		this.articlesTitle = articlesTitle;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return blog_l_a_id
	 */
	public Integer getBlogLAId() {
		return blogLAId;
	}

	/**
	 * @param blogLAId
	 */
	public void setBlogLAId(Integer blogLAId) {
		this.blogLAId = blogLAId;
	}

	/**
	 * @return label_id
	 */
	public Integer getLabelId() {
		return labelId;
	}

	/**
	 * @param labelId
	 */
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
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
	 * @return bal_state
	 */
	public Integer getBalState() {
		return balState;
	}

	/**
	 * @param balState
	 */
	public void setBalState(Integer balState) {
		this.balState = balState;
	}
}