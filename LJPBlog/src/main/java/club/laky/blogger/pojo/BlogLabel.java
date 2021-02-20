package club.laky.blogger.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "blog_label")
public class BlogLabel implements Serializable {
    @Id
    @Column(name = "label_id")
    private Integer labelId;

    @Column(name = "label_name")
    private String labelName;

    /**
     * 点击量
     */
    @Column(name = "label_pick_count")
    private Integer labelPickCount;

    /**
     * 0为禁用标签 1为正常
     */
    @Column(name = "label_state")
    private Integer labelState;

    private static final long serialVersionUID = 1L;

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
     * @return label_name
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    /**
     * 获取点击量
     *
     * @return label_pick_count - 点击量
     */
    public Integer getLabelPickCount() {
        return labelPickCount;
    }

    /**
     * 设置点击量
     *
     * @param labelPickCount 点击量
     */
    public void setLabelPickCount(Integer labelPickCount) {
        this.labelPickCount = labelPickCount;
    }

    /**
     * 获取0为禁用标签 1为正常
     *
     * @return label_state - 0为禁用标签 1为正常
     */
    public Integer getLabelState() {
        return labelState;
    }

    /**
     * 设置0为禁用标签 1为正常
     *
     * @param labelState 0为禁用标签 1为正常
     */
    public void setLabelState(Integer labelState) {
        this.labelState = labelState;
    }
}