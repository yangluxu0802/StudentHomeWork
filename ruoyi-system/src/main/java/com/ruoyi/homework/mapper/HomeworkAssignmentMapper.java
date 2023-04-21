package com.ruoyi.homework.mapper;

import java.util.List;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.domain.vo.HomeworkAssignmentVo;

/**
 * 作业分配Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public interface HomeworkAssignmentMapper 
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
     * @param homeworkAssignment 作业分配
     * @return 结果
     */
    public int updateHomeworkAssignment(HomeworkAssignment homeworkAssignment);

    /**
     * 删除作业分配
     * 
     * @param assignmentId 作业分配主键
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentId(Long assignmentId);

    /**
     * 批量删除作业分配
     * 
     * @param assignmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentIds(Long[] assignmentIds);
}
