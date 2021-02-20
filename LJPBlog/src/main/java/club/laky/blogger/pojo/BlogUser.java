package club.laky.blogger.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Table(name = "blog_user")
public class BlogUser implements Serializable {
	/**
	 * 用户id
	 */
	@Id
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 用户密码
	 */
	@Column(name = "user_password")
	private String userPassword;

	/**
	 * 用户别名
	 */
	@Column(name = "user_another_name")
	private String userAnotherName;

	/**
	 * 用户头像地址
	 */
	@Column(name = "user_head_sculpture")
	private String userHeadSculpture;

	/**
	 * 用户的等级
	 */
	@Column(name = "user_level")
	private Integer userLevel;

	/**
	 * 用户的经验值
	 */
	@Column(name = "user_experience")
	private Integer userExperience;

	/**
	 * 电话号码
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 用户邮箱
	 */
	@Column(name = "user_email")
	private String userEmail;

	/**
	 * 用户个人简介
	 */
	@Column(name = "user_abstract")
	private String userAbstract;

	/**
	 * 用户的创建时间
	 */
	@Column(name = "user_creat_date")
	private String userCreatDate;

	/**
	 * 用户状态 0：审核 1：正常 2 ：禁用
	 */
	@Column(name = "user_state")
	private Integer userState;

	/**
	 * 用户权限 1：博主 2：用户
	 */
	@Column(name = "user_power")
	private Integer userPower;

	/**
	 * 用户发表的文章
	 * */
	@Transient
	private List<BlogArticles> articles;

	/**
	 * 用户发表的评论
	 * */
	@Transient
	private List<BlogComment> comments;

	private static final long serialVersionUID = 1L;

	public List<BlogArticles> getArticles() {
		return articles;
	}

	public void setArticles(List<BlogArticles> articles) {
		this.articles = articles;
	}

	public List<BlogComment> getComments() {
		return comments;
	}

	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	/**
	 * 获取用户id
	 *
	 * @return user_id - 用户id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 *
	 * @param userId
	 *            用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户名
	 *
	 * @return user_name - 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 *
	 * @param userName
	 *            用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 获取用户密码
	 *
	 * @return user_password - 用户密码
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 设置用户密码
	 *
	 * @param userPassword
	 *            用户密码
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	/**
	 * 获取用户别名
	 *
	 * @return user_another_name - 用户别名
	 */
	public String getUserAnotherName() {
		return userAnotherName;
	}

	/**
	 * 设置用户别名
	 *
	 * @param userAnotherName
	 *            用户别名
	 */
	public void setUserAnotherName(String userAnotherName) {
		this.userAnotherName = userAnotherName == null ? null : userAnotherName.trim();
	}

	/**
	 * 获取用户头像地址
	 *
	 * @return user_head_sculpture - 用户头像地址
	 */
	public String getUserHeadSculpture() {
		return userHeadSculpture;
	}

	/**
	 * 设置用户头像地址
	 *
	 * @param userHeadSculpture
	 *            用户头像地址
	 */
	public void setUserHeadSculpture(String userHeadSculpture) {
		this.userHeadSculpture = userHeadSculpture == null ? null : userHeadSculpture.trim();
	}

	/**
	 * 获取用户的等级
	 *
	 * @return user_level - 用户的等级
	 */
	public Integer getUserLevel() {
		return userLevel;
	}

	/**
	 * 设置用户的等级
	 *
	 * @param userLevel
	 *            用户的等级
	 */
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * 获取用户的经验值
	 *
	 * @return user_experience - 用户的经验值
	 */
	public Integer getUserExperience() {
		return userExperience;
	}

	/**
	 * 设置用户的经验值
	 *
	 * @param userExperience
	 *            用户的经验值
	 */
	public void setUserExperience(Integer userExperience) {
		this.userExperience = userExperience;
	}

	/**
	 * 获取电话号码
	 *
	 * @return user_phone - 电话号码
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 设置电话号码
	 *
	 * @param userPhone
	 *            电话号码
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	/**
	 * 获取用户邮箱
	 *
	 * @return user_email - 用户邮箱
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 设置用户邮箱
	 *
	 * @param userEmail
	 *            用户邮箱
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail == null ? null : userEmail.trim();
	}

	/**
	 * 获取用户个人简介
	 *
	 * @return user_abstract - 用户个人简介
	 */
	public String getUserAbstract() {
		return userAbstract;
	}

	/**
	 * 设置用户个人简介
	 *
	 * @param userAbstract
	 *            用户个人简介
	 */
	public void setUserAbstract(String userAbstract) {
		this.userAbstract = userAbstract == null ? null : userAbstract.trim();
	}

	/**
	 * 获取用户的创建时间
	 *
	 * @return user_creat_date - 用户的创建时间
	 */
	public String getUserCreatDate() {
		return userCreatDate;
	}

	/**
	 * 设置用户的创建时间
	 *
	 * @param userCreatDate
	 *            用户的创建时间
	 */
	public void setUserCreatDate(String userCreatDate) {
		this.userCreatDate = userCreatDate == null ? null : userCreatDate.trim();
	}

	/**
	 * 获取用户状态 0：审核 1：正常 2 ：禁用
	 *
	 * @return user_state - 用户状态 0：审核 1：正常 2 ：禁用
	 */
	public Integer getUserState() {
		return userState;
	}

	/**
	 * 设置用户状态 0：审核 1：正常 2 ：禁用
	 *
	 * @param userState
	 *            用户状态 0：审核 1：正常 2 ：禁用
	 */
	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	/**
	 * 获取用户权限 1：博主 2：用户
	 *
	 * @return user_power - 用户权限 1：博主 2：用户
	 */
	public Integer getUserPower() {
		return userPower;
	}

	/**
	 * 设置用户权限 1：博主 2：用户
	 *
	 * @param userPower
	 *            用户权限 1：博主 2：用户
	 */
	public void setUserPower(Integer userPower) {
		this.userPower = userPower;
	}
}