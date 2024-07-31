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
 * @description 用户点击记录
 * @create 2024-07-30
 */
@Data
@Entity
@Table(name = "user_rating_page_action_record")
public class UserRatingPageActionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "access_id")
    private Integer accessId;

    @Column(name = "link_access")
    private Boolean linkAccess;

    @Column(name = "link_code")
    private String linkCode;

    @Column(name = "mail_access")
    private Boolean mailAccess;

    @Column(name = "action_type")
    private String actionType;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updated_time")
    private Date updatedTime;

}
