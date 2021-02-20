package club.laky.blogger.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "blog_manager")
public class BlogManager implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    @Column(name = "account")
    private String account;

    /**
     * 密码 加密过后的
     */
    @Column(name = "password")
    private String password;

    /**
     * 管理员昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 0 冻结 1 正常
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 权限：1 超级管理员 2 管理员
     */
    @Column(name = "power")
    private Integer power;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 获取密码 加密过后的
     *
     * @return password - 密码 加密过后的
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码 加密过后的
     *
     * @param password 密码 加密过后的
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取管理员昵称
     *
     * @return nickname - 管理员昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置管理员昵称
     *
     * @param nickname 管理员昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取0 冻结 1 正常
     *
     * @return state - 0 冻结 1 正常
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0 冻结 1 正常
     *
     * @param state 0 冻结 1 正常
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取权限：1 超级管理员 2 管理员
     *
     * @return power - 权限：1 超级管理员 2 管理员
     */
    public Integer getPower() {
        return power;
    }

    /**
     * 设置权限：1 超级管理员 2 管理员
     *
     * @param power 权限：1 超级管理员 2 管理员
     */
    public void setPower(Integer power) {
        this.power = power;
    }
}