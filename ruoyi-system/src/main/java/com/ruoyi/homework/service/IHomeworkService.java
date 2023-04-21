package com.ruoyi.homework.service;

import java.util.List;
import com.ruoyi.homework.domain.Homework;

/**
 * 发布作业Service接口
 * 
 * @author ruoyi
 * @date 2023-04-09
 */
public interface IHomeworkService 
{
    /**
     * 查询发布作业
     * 
     * @param homeworkId 发布作业主键
     * @return 发布作业
     */
    public Homework selectHomeworkByHomeworkId(Long homeworkId);

    /**
     * 查询发布作业列表
     * 
     * @param homework 发布作业
     * @return 发布作业集合
     */
    public List<Homework> selectHomeworkList(Homework homework);

    /**
     * 新增发布作业
     * 
     * @param homework 发布作业
     * @return 结果
     */
    public int insertHomework(Homework homework);

    /**
     * 修改发布作业
     * 
     * @param homework 发布作业
     * @return 结果
     */
    public int updateHomework(Homework homework);

    /**
     * 批量删除发布作业
     * 
     * @param homeworkIds 需要删除的发布作业主键集合
     * @return 结果
     */
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds);

    /**
     * 删除发布作业信息
     * 
     * @param homeworkId 发布作业主键
     * @return 结果
     */
    public int deleteHomeworkByHomeworkId(Long homeworkId);
}
