package com.ruoyi.web.controller.homework;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.service.IHomeworkAssignmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 作业分配Controller
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@RestController
@RequestMapping("/homework/assignment")
public class HomeworkAssignmentController extends BaseController {
    @Resource
    private IHomeworkAssignmentService homeworkAssignmentService;
    /**
     * 新增作业分配
     */
    @PreAuthorize("@ss.hasAnyRoles('admin,teacher')")
    @Log(title = "作业分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeworkAssignment homeworkAssignment) {
        return toAjax(homeworkAssignmentService.insertHomeworkAssignment(homeworkAssignment));
    }
}
