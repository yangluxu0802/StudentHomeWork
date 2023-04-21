package com.ruoyi.web.controller.homework;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.homework.domain.HomeworkAnswer;
import com.ruoyi.homework.domain.vo.HomeworkAnswerVo;
import com.ruoyi.homework.service.IHomeworkAnswerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学生作业作答Controller
 *
 * @author ruoyi
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/homework/answer")
public class HomeworkAnswerController extends BaseController {
    @Resource
    private IHomeworkAnswerService homeworkAnswerService;

    /**
     * 查询学生作业作答列表
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeworkAnswer homeworkAnswer) {
        startPage();
        List<HomeworkAnswerVo> list = homeworkAnswerService.selectHomeworkAnswerList(homeworkAnswer);
        return getDataTable(list);
    }

    /**
     * 导出学生作业作答列表
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:export')")
    @Log(title = "学生作业作答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeworkAnswer homeworkAnswer) {
        List<HomeworkAnswerVo> list = homeworkAnswerService.selectHomeworkAnswerList(homeworkAnswer);
        ExcelUtil<HomeworkAnswerVo> util = new ExcelUtil<HomeworkAnswerVo>(HomeworkAnswerVo.class);
        util.exportExcel(response, list, "学生作业作答数据");
    }

    /**
     * 获取学生作业作答详细信息
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:query')")
    @GetMapping(value = "/{homeworkAnswerId}")
    public AjaxResult getInfo(@PathVariable("homeworkAnswerId") Long homeworkAnswerId) {
        return success(homeworkAnswerService.selectHomeworkAnswerByHomeworkAnswerId(homeworkAnswerId));
    }

    /**
     * 新增学生作业作答
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:add')")
    @Log(title = "学生作业作答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeworkAnswer homeworkAnswer) {
        return toAjax(homeworkAnswerService.insertHomeworkAnswer(homeworkAnswer));
    }

    /**
     * 修改学生作业作答
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:edit')")
    @Log(title = "学生作业作答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeworkAnswer homeworkAnswer) {
        return toAjax(homeworkAnswerService.updateHomeworkAnswer(homeworkAnswer));
    }

    /**
     * 删除学生作业作答
     */
    @PreAuthorize("@ss.hasPermi('homework:answer:remove')")
    @Log(title = "学生作业作答", businessType = BusinessType.DELETE)
    @DeleteMapping("/{homeworkAnswerIds}")
    public AjaxResult remove(@PathVariable Long[] homeworkAnswerIds) {
        return toAjax(homeworkAnswerService.deleteHomeworkAnswerByHomeworkAnswerIds(homeworkAnswerIds));
    }
}
