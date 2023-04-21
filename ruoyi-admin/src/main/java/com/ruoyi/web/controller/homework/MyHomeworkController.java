package com.ruoyi.web.controller.homework;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.domain.vo.HomeworkAnswerVo;
import com.ruoyi.homework.domain.vo.HomeworkAssignmentVo;
import com.ruoyi.homework.service.IHomeworkAnswerService;
import com.ruoyi.homework.service.IHomeworkAssignmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 我的作业Controller
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@RestController
@RequestMapping("/homework/my")
public class MyHomeworkController extends BaseController {
    @Resource
    private IHomeworkAssignmentService homeworkAssignmentService;
    @Resource
    private IHomeworkAnswerService homeworkAnswerService;

    /**
     * 查询我的作业列表
     */
    @PreAuthorize("@ss.hasPermi('homework:my:query')")
    @GetMapping("/list")
    public TableDataInfo list(HomeworkAssignment homeworkAssignment) {
        startPage();
        if (SecurityUtils.getUserId() != 1) {
            homeworkAssignment.setStudentId(SecurityUtils.getUserId());
        }
        List<HomeworkAssignmentVo> list = homeworkAssignmentService.selectHomeworkAssignmentList(homeworkAssignment);
        return getDataTable(list);
    }

    /**
     * 写作业
     */
    @PreAuthorize("@ss.hasPermi('homework:my:complete')")
    @Log(title = "写作业", businessType = BusinessType.UPDATE)
    @PutMapping("/complete")
    public AjaxResult complete(@RequestBody HomeworkAssignmentVo homeworkAssignmentVo) {
        HomeworkAnswerVo homeworkAnswerVo = new HomeworkAnswerVo();
        homeworkAnswerVo.setAssignmentId(homeworkAssignmentVo.getAssignmentId());
        homeworkAnswerVo.setHomeworkAnswerContent(homeworkAssignmentVo.getHomeworkAnswerContent());
        homeworkAnswerVo.setAnswerAttachment(homeworkAssignmentVo.getAnswerAttachment());
        homeworkAnswerVo.setSubmissionTime(new Date());
        homeworkAnswerService.insertHomeworkAnswer(homeworkAnswerVo);
        return toAjax(homeworkAssignmentService.updateHomeworkAssignment(homeworkAssignmentVo));
    }

    /**
     * 导出作业分配列表
     */
    @PreAuthorize("@ss.hasPermi('homework:assignment:export')")
    @Log(title = "作业分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeworkAssignment homeworkAssignment) {
        List<HomeworkAssignmentVo> list = homeworkAssignmentService.selectHomeworkAssignmentList(homeworkAssignment);
        ExcelUtil<HomeworkAssignmentVo> util = new ExcelUtil<>(HomeworkAssignmentVo.class);
        util.exportExcel(response, list, "作业分配数据");
    }

    /**
     * 获取我的作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('homework:my:query')")
    @GetMapping(value = "/{assignmentId}")
    public AjaxResult getInfo(@PathVariable("assignmentId") Long assignmentId) {
        return success(homeworkAssignmentService.selectHomeworkAssignmentByAssignmentId(assignmentId));
    }


    /**
     * 删除作业分配
     */
    @PreAuthorize("@ss.hasPermi('homework:assignment:remove')")
    @Log(title = "作业分配", businessType = BusinessType.DELETE)
    @DeleteMapping("/{assignmentIds}")
    public AjaxResult remove(@PathVariable Long[] assignmentIds) {
        return toAjax(homeworkAssignmentService.deleteHomeworkAssignmentByAssignmentIds(assignmentIds));
    }
}
