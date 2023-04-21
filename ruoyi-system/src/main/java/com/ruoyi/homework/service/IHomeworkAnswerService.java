package com.ruoyi.homework.service;

import java.util.List;
import com.ruoyi.homework.domain.HomeworkAnswer;
import com.ruoyi.homework.domain.vo.HomeworkAnswerVo;

/**
 * 学生作业作答Service接口
 * 
 * @author ruoyi
 * @date 2023-04-17
 */
public interface IHomeworkAnswerService 
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
     * 批量删除学生作业作答
     * 
     * @param homeworkAnswerIds 需要删除的学生作业作答主键集合
     * @return 结果
     */
    public int deleteHomeworkAnswerByHomeworkAnswerIds(Long[] homeworkAnswerIds);

    /**
     * 删除学生作业作答信息
     * 
     * @param homeworkAnswerId 学生作业作答主键
     * @return 结果
     */
    public int deleteHomeworkAnswerByHomeworkAnswerId(Long homeworkAnswerId);
}
