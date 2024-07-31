package com.finpoints.framework.ratetrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

/**
 * @author zhouyp
 * @program rateTrack
 * @description 用户评分页面访问行为
 * @create 2024-07-30
 */
@Data
@Entity
@Table(name = "user_rating_page_access")
public class UserRatingPageAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "access_num")
    private Integer accessNum;

    @Column(name = "link_code")
    private String linkCode;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updated_time")
    private Date updatedTime;

}
