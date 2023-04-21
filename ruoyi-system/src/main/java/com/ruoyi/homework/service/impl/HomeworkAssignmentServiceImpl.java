package com.ruoyi.homework.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.homework.domain.Homework;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.domain.vo.HomeworkAssignmentVo;
import com.ruoyi.homework.mapper.HomeworkAnswerMapper;
import com.ruoyi.homework.mapper.HomeworkAssignmentMapper;
import com.ruoyi.homework.mapper.HomeworkMapper;
import com.ruoyi.homework.service.IHomeworkAssignmentService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作业分配Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Service
public class HomeworkAssignmentServiceImpl implements IHomeworkAssignmentService {
    @Resource
    private HomeworkAssignmentMapper homeworkAssignmentMapper;
    @Resource
    private HomeworkMapper homeworkMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private HomeworkAnswerMapper homeworkAnswerMapper;

    /**
     * 查询作业分配
     *
     * @param assignmentId 作业分配主键
     * @return 作业分配
     */
    @Override
    public HomeworkAssignmentVo selectHomeworkAssignmentByAssignmentId(Long assignmentId) {
        return homeworkAssignmentMapper.selectHomeworkAssignmentByAssignmentId(assignmentId);
    }

    /**
     * 查询作业分配列表
     *
     * @param homeworkAssignment 作业分配
     * @return 作业分配
     */
    @Override
    public List<HomeworkAssignmentVo> selectHomeworkAssignmentList(HomeworkAssignment homeworkAssignment) {
        return homeworkAssignmentMapper.selectHomeworkAssignmentList(homeworkAssignment);
    }

    /**
     * 新增作业分配
     *
     * @param homeworkAssignment 作业分配
     * @return 结果
     */
    @Override
    public int insertHomeworkAssignment(HomeworkAssignment homeworkAssignment) {
        List<HomeworkAssignmentVo> homeworkAssignments = homeworkAssignmentMapper.selectHomeworkAssignmentList(homeworkAssignment);
        if (homeworkAssignments.size() != 0) {
            throw new RuntimeException("该作业已经分配给该班级");
        }
        List<SysUser> students = sysUserMapper.selectUserByDeptId(homeworkAssignment.getClassId());
        Homework homework = homeworkMapper.selectHomeworkByHomeworkId(homeworkAssignment.getHomeworkId());
        homeworkAssignment.setCreateTime(DateUtils.getNowDate());
        homeworkAssignment.setCreateBy(SecurityUtils.getUsername());
        homeworkAssignment.setTeacherId(homework.getTeacherId());
        for (SysUser student : students) {
            homeworkAssignment.setStudentId(student.getUserId());
            homeworkAssignmentMapper.insertHomeworkAssignment(homeworkAssignment);
        }
        return students.size();
    }

    /**
     * 修改作业分配
     *
     * @param homeworkAssignmentVo 作业分配
     * @return 结果
     */
    @Override
    public int updateHomeworkAssignment(HomeworkAssignmentVo homeworkAssignmentVo) {
        homeworkAssignmentVo.setHomeworkStatus("1");
        return homeworkAssignmentMapper.updateHomeworkAssignment(homeworkAssignmentVo);
    }

    /**
     * 批量删除作业分配
     *
     * @param assignmentIds 需要删除的作业分配主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkAssignmentByAssignmentIds(Long[] assignmentIds) {
        return homeworkAssignmentMapper.deleteHomeworkAssignmentByAssignmentIds(assignmentIds);
    }

    /**
     * 删除作业分配信息
     *
     * @param assignmentId 作业分配主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkAssignmentByAssignmentId(Long assignmentId) {
        return homeworkAssignmentMapper.deleteHomeworkAssignmentByAssignmentId(assignmentId);
    }
}
