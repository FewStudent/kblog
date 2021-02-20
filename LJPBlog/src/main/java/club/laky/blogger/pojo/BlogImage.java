package club.laky.blogger.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "blog_image")
public class BlogImage implements Serializable {
    /**
     * 图片的id
     */
    @Id
    @Column(name = "image_id")
    private Integer imageId;

    /**
     * 图片的路径
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 图片的作用类型 1 封面(包括轮播) 2 头像 3 轮播
     */
    @Column(name = "image_type")
    private Integer imageType;

    @Column(name = "image_uploader_id")
    private Integer imageUploaderId;

    @Column(name = "image_upload_time")
    private String imageUploadTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取图片的id
     *
     * @return image_id - 图片的id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * 设置图片的id
     *
     * @param imageId 图片的id
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取图片的路径
     *
     * @return image_url - 图片的路径
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片的路径
     *
     * @param imageUrl 图片的路径
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 获取图片的作用类型 1 封面(包括轮播) 2 头像 3 轮播
     *
     * @return image_type - 图片的作用类型 1 封面(包括轮播) 2 头像 3 轮播
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * 设置图片的作用类型 1 封面(包括轮播) 2 头像 3 轮播
     *
     * @param imageType 图片的作用类型 1 封面(包括轮播) 2 头像 3 轮播
     */
    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    /**
     * @return image_uploader_id
     */
    public Integer getImageUploaderId() {
        return imageUploaderId;
    }

    /**
     * @param imageUploaderId
     */
    public void setImageUploaderId(Integer imageUploaderId) {
        this.imageUploaderId = imageUploaderId;
    }

    /**
     * @return image_upload_time
     */
    public String getImageUploadTime() {
        return imageUploadTime;
    }

    /**
     * @param imageUploadTime
     */
    public void setImageUploadTime(String imageUploadTime) {
        this.imageUploadTime = imageUploadTime == null ? null : imageUploadTime.trim();
    }
}