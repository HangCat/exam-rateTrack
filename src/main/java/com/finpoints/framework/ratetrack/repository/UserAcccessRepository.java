package com.finpoints.framework.ratetrack.repository;

import com.finpoints.framework.ratetrack.model.UserRatingPageAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
public interface UserAcccessRepository extends JpaRepository<UserRatingPageAccess, Long> {

    List<UserRatingPageAccess> findAllByUserId(Integer userId);

    UserRatingPageAccess findByLinkCode(String linkCode);

}
