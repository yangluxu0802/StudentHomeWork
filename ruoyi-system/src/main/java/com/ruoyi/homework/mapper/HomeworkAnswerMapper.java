package com.ruoyi.homework.mapper;

import java.util.List;
import com.ruoyi.homework.domain.HomeworkAnswer;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.domain.vo.HomeworkAnswerVo;

/**
 * 学生作业作答Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-17
 */
public interface HomeworkAnswerMapper 
{
    /**
     * 查询学生作业作答
     * 
     * @param homeworkAnswerId 学生作业作答主键
     * @return 学生作业作答
     */
    public HomeworkAnswerVo selectHomeworkAnswerByHomeworkAnswerId(Long homeworkAnswerId);

    /**
     * 查询学生作业作答列表
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 学生作业作答集合
     */
    public List<HomeworkAnswerVo> selectHomeworkAnswerList(HomeworkAnswer homeworkAnswer);

    /**
     * 新增学生作业作答
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 结果
     */
    public int insertHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 修改学生作业作答
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 结果
     */
    public int updateHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 删除学生作业作答
     * 
     * @param homeworkAnswerId 学生作业作答主键
     * @return 结果
     */
    public int deleteHomeworkAnswerByHomeworkAnswerId(Long homeworkAnswerId);

    /**
     * 批量删除学生作业作答
     * 
     * @param homeworkAnswerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeworkAnswerByHomeworkAnswerIds(Long[] homeworkAnswerIds);

    /**
     * 批量删除作业分配
     * 
     * @param homeworkAnswerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentIds(Long[] homeworkAnswerIds);
    
    /**
     * 批量新增作业分配
     * 
     * @param homeworkAssignmentList 作业分配列表
     * @return 结果
     */
    public int batchHomeworkAssignment(List<HomeworkAssignment> homeworkAssignmentList);
    

    /**
     * 通过学生作业作答主键删除作业分配信息
     * 
     * @param homeworkAnswerId 学生作业作答ID
     * @return 结果
     */
    public int deleteHomeworkAssignmentByAssignmentId(Long homeworkAnswerId);
}
