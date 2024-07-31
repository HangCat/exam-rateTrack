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
 * @description
 * @create 2024-07-30
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "has_sent")
    private Boolean hasSent;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updated_time")
    private Date updatedTime;

}
