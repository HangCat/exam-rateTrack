package com.finpoints.framework.ratetrack.repository;

import com.finpoints.framework.ratetrack.model.UserRatingPageActionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
public interface UserAccessRecordRepository extends JpaRepository<UserRatingPageActionRecord, Long> {

    List<UserRatingPageActionRecord> findAllByAccessId(Integer accessId);

}
