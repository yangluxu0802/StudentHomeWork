package com.ruoyi.web.controller.homework;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.homework.domain.Homework;
import com.ruoyi.homework.service.IHomeworkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 发布作业Controller
 *
 * @author ruoyi
 * @date 2023-04-09
 */
@RestController
@RequestMapping("/homework/homework")
public class HomeworkController extends BaseController {
    @Resource
    private IHomeworkService homeworkService;

    /**
     * 查询发布作业列表
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(Homework homework) {
        startPage();
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        return getDataTable(list);
    }

    /**
     * 导出发布作业列表
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:export')")
    @Log(title = "发布作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Homework homework) {
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        ExcelUtil<Homework> util = new ExcelUtil<Homework>(Homework.class);
        util.exportExcel(response, list, "发布作业数据");
    }

    /**
     * 获取发布作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:query')")
    @GetMapping(value = "/{homeworkId}")
    public AjaxResult getInfo(@PathVariable("homeworkId") Long homeworkId) {
        return success(homeworkService.selectHomeworkByHomeworkId(homeworkId));
    }

    /**
     * 新增发布作业
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:add')")
    @Log(title = "发布作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Homework homework) {
        return toAjax(homeworkService.insertHomework(homework));
    }

    /**
     * 修改发布作业
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:edit')")
    @Log(title = "发布作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Homework homework) {
        return toAjax(homeworkService.updateHomework(homework));
    }

    /**
     * 删除发布作业
     */
    @PreAuthorize("@ss.hasPermi('homework:homework:remove')")
    @Log(title = "发布作业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{homeworkIds}")
    public AjaxResult remove(@PathVariable Long[] homeworkIds) {
        return toAjax(homeworkService.deleteHomeworkByHomeworkIds(homeworkIds));
    }
}
