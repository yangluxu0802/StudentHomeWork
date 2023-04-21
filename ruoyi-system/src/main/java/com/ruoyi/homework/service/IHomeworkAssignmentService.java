package com.ruoyi.homework.service;

import java.util.List;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.domain.vo.HomeworkAssignmentVo;

/**
 * 作业分配Service接口
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public interface IHomeworkAssignmentService 
{
    /**
     * 查询作业分配
     * 
     * @param assignmentId 作业分配主键
     * @return 作业分配
     */
    public HomeworkAssignmentVo selectHomeworkAssignmentByAssignmentId(Long assignmentId);

    /**
     * 查询作业分配列表
     * 
     * @param homeworkAssignment 作业分配
     * @return 作业分配集合
     */
    public List<HomeworkAssignmentVo> selectHomeworkAssignmentList(HomeworkAssignment homeworkAssignment);

    /**
     * 新增作业分配
     * 
     * @param homeworkAssignment 作业分配
     * @return 结果
     */
    public int insertHomeworkAssignment(HomeworkAssignment homeworkAssignment);

    /**
     * 修改作业分配
     *
     * @param homeworkAssignmentVo 作业分配
     * @return 结果
     */
    public int updateHomeworkAssignment(HomeworkAssignmentVo homeworkAssignmentVo);

    /**
     * 批量删除作业分配
     * 
     * @param assignmentIds 需要删除的作业分配主键集合
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentIds(Long[] assignmentIds);

    /**
     * 删除作业分配信息
     * 
     * @param assignmentId 作业分配主键
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentId(Long assignmentId);
}
