package com.finpoints.framework.ratetrack.controller;

import com.finpoints.framework.ratetrack.model.UserRatingPageActionRecord;
import com.finpoints.framework.ratetrack.model.UserRatingPageAccess;
import com.finpoints.framework.ratetrack.repository.UserAccessRecordRepository;
import com.finpoints.framework.ratetrack.repository.UserAcccessRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
@Controller
public class WebpageController {


    private final UserAcccessRepository userAcccessRepository;

    private final UserAccessRecordRepository userAccessRecordRepository;

    public WebpageController(UserAcccessRepository userAcccessRepository, UserAccessRecordRepository userAccessRecordRepository) {
        this.userAcccessRepository = userAcccessRepository;
        this.userAccessRecordRepository = userAccessRecordRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<UserRatingPageAccess> accessInfos = userAcccessRepository.findAll();
        model.addAttribute("accessInfos", accessInfos);
        System.out.println(accessInfos);
        return "dashboard";
    }

    @GetMapping("/access-records/{accessId}")
    @ResponseBody
    public List<UserRatingPageActionRecord> getAccessSubRecords(
            @PathVariable Integer accessId) {
        List<UserRatingPageActionRecord> all = userAccessRecordRepository.findAll();
        System.out.println(all);
        return userAccessRecordRepository.findAllByAccessId(accessId);
    }

    @GetMapping("/email-page/{uniqueId}")
    public String getEmailPage(@PathVariable("uniqueId") String uniqueId,
                               Model model) {
        // 可以在这里添加需要传递给模板的模型数据
        model.addAttribute("uniqueId", uniqueId);
        model.addAttribute("accessSource", "webpage");
        return "main_page_template"; // 返回的字符串应与模板文件的名称匹配，不包含后缀
    }

}
