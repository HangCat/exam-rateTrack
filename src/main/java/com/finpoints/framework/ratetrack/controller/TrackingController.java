package com.finpoints.framework.ratetrack.controller;

import com.finpoints.framework.ratetrack.model.UserRatingPageAccess;
import com.finpoints.framework.ratetrack.model.UserRatingPageActionRecord;
import com.finpoints.framework.ratetrack.repository.UserAcccessRepository;
import com.finpoints.framework.ratetrack.repository.UserAccessRecordRepository;
import com.finpoints.framework.ratetrack.repository.UserInfoRepository;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
@RestController
@RequestMapping("/api")
public class TrackingController {

    private final UserAcccessRepository userAcccessRepository;

    private final UserAccessRecordRepository userAccessRecordRepository;

    private final UserInfoRepository userInfoRepository;

    public TrackingController(UserAcccessRepository userAcccessRepository,
                              UserAccessRecordRepository userAccessRecordRepository,
                              UserInfoRepository userInfoRepository) {
        this.userAcccessRepository = userAcccessRepository;
        this.userAccessRecordRepository = userAccessRecordRepository;
        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/track-visit")
    public ResponseEntity<Map<String, String>> trackVisit(@RequestBody TrackingRequest request) {
        // 记录访问平台的信息
        System.out.println("/track-visit" + request);
        UserRatingPageAccess access = userAcccessRepository.findByLinkCode(request.getUniqueId());
        if (access == null) {
            return ResponseEntity.ok(DefaultResp.of("message", "link code not found").response);
        }
        Integer accessNum = access.getAccessNum();
        access.setAccessNum(accessNum + 1);
        userAcccessRepository.save(access);
        DefaultResp resp = DefaultResp.of("userId", access.getUserId() + "")
                .add("uniqueId", request.getUniqueId())
                .add("accessNum", access.getAccessNum() + "");
        return ResponseEntity.ok(resp.response);
    }

    @PostMapping("/button-action")
    public ResponseEntity<Map<String, String>> buttonAction(@RequestBody ActionRequest request) {
        System.out.println("/button-action" + request);
        // 记录按钮点击情况
        // 先查询access表数据
        UserRatingPageAccess access = userAcccessRepository.findByLinkCode(request.getUniqueId());
        if (access == null) {
            return ResponseEntity.ok(DefaultResp.of("message", "link code not found").response);
        }
        // 记录相关操作
        UserRatingPageActionRecord record = new UserRatingPageActionRecord();
        record.setAccessId(access.getId());
        record.setLinkAccess(false);
        record.setMailAccess(false);
        if (StringUtils.endsWithIgnoreCase(request.getAccessSource(), "webpage")) {
            record.setLinkAccess(true);
        } else {
            record.setMailAccess(true);
        }
        record.setLinkCode(access.getLinkCode());
        record.setActionType(record.getActionType());

        userAccessRecordRepository.save(record);

        // 执行相应的操作
        return ResponseEntity.ok(DefaultResp.of("message", "执行操作：" + request.getAction()).response);
    }

    private static class DefaultResp {
        Map<String, String> response;

        static DefaultResp of(String key, String value) {
            DefaultResp resp = new DefaultResp();
            resp.response = new HashMap<>();
            resp.response.put(key, value);
            return resp;
        }

        DefaultResp add(String key, String value) {
            response.put(key, value);
            return this;
        }
    }


    @Data
    @ToString
    static class TrackingRequest {
        private String accessSource;
        private String uniqueId;
    }

    @Data
    @ToString
    static class ActionRequest {
        private String accessSource;
        private String uniqueId;
        private String action;
    }
}
