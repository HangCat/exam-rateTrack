package com.finpoints.framework.ratetrack.repository;

import com.finpoints.framework.ratetrack.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    /**
     * 分页查询未发送过邮件的
     */
    Page<UserInfo> findAllByHasSentFalse(Pageable pageable);

    /**
     * 计算未发送过消息的总用户数
     *
     * @return 用户数
     */
    int countAllByHasSentFalse();

    /**
     * 计算已经发送过消息的总用户数
     *
     * @return 用户数
     */
    int countAllByHasSentTrue();

    /**
     * 分页查询未发送过邮件的
     */
    Page<UserInfo> findAllByHasSentTrue(Pageable pageable);

}
