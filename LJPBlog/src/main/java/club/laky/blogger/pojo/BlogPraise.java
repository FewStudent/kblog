package club.laky.blogger.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "blog_praise")
public class BlogPraise implements Serializable {
    /**
     * 赞的id
     */
    @Id
    @Column(name = "praise_id")
    private Integer praiseId;

    /**
     * 相关文章id
     */
    @Column(name = "praise_articles_id")
    private Integer praiseArticlesId;

    /**
     * 赞的用户id
     */
    @Column(name = "praise_user_id")
    private Integer praiseUserId;

    /**
     * 赞的状态 0 取消 1 支持
     */
    @Column(name = "praise_flag")
    private Integer praiseFlag;

    /**
     * 点赞时间
     */
    @Column(name = "praise_date")
    private String praiseDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取赞的id
     *
     * @return praise_id - 赞的id
     */
    public Integer getPraiseId() {
        return praiseId;
    }

    /**
     * 设置赞的id
     *
     * @param praiseId 赞的id
     */
    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    /**
     * 获取相关文章
     *
     * @return praise_articles_id - 相关文章
     */
    public Integer getPraiseArticlesId() {
        return praiseArticlesId;
    }

    /**
     * 设置相关文章
     *
     * @param praiseArticlesId 相关文章
     */
    public void setPraiseArticlesId(Integer praiseArticlesId) {
        this.praiseArticlesId = praiseArticlesId;
    }

    /**
     * 获取赞的用户id
     *
     * @return praise_user_id - 赞的用户id
     */
    public Integer getPraiseUserId() {
        return praiseUserId;
    }

    /**
     * 设置赞的用户id
     *
     * @param praiseUserId 赞的用户id
     */
    public void setPraiseUserId(Integer praiseUserId) {
        this.praiseUserId = praiseUserId;
    }

    /**
     * 获取赞的状态 0 取消 1 支持
     *
     * @return praise_flag - 赞的状态 0 取消 1 支持
     */
    public Integer getPraiseFlag() {
        return praiseFlag;
    }

    /**
     * 设置赞的状态 0 取消 1 支持
     *
     * @param praiseFlag 赞的状态 0 取消 1 支持
     */
    public void setPraiseFlag(Integer praiseFlag) {
        this.praiseFlag = praiseFlag;
    }

    /**
     * 获取点赞时间
     *
     * @return praise_date - 点赞时间
     */
    public String getPraiseDate() {
        return praiseDate;
    }

    /**
     * 设置点赞时间
     *
     * @param praiseDate 点赞时间
     */
    public void setPraiseDate(String praiseDate) {
        this.praiseDate = praiseDate == null ? null : praiseDate.trim();
    }
}