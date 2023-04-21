package com.ruoyi.homework.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.homework.domain.vo.HomeworkAnswerVo;
import com.ruoyi.homework.domain.vo.HomeworkAssignmentVo;
import com.ruoyi.homework.mapper.HomeworkAssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.homework.domain.HomeworkAssignment;
import com.ruoyi.homework.mapper.HomeworkAnswerMapper;
import com.ruoyi.homework.domain.HomeworkAnswer;
import com.ruoyi.homework.service.IHomeworkAnswerService;

import javax.annotation.Resource;

/**
 * 学生作业作答Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-17
 */
@Service
public class HomeworkAnswerServiceImpl implements IHomeworkAnswerService 
{
    @Resource
    private HomeworkAnswerMapper homeworkAnswerMapper;
    @Resource
    private HomeworkAssignmentMapper homeworkAssignmentMapper;

    /**
     * 查询学生作业作答
     * 
     * @param homeworkAnswerId 学生作业作答主键
     * @return 学生作业作答
     */
    @Override
    public HomeworkAnswerVo selectHomeworkAnswerByHomeworkAnswerId(Long homeworkAnswerId)
    {
        return homeworkAnswerMapper.selectHomeworkAnswerByHomeworkAnswerId(homeworkAnswerId);
    }

    /**
     * 查询学生作业作答列表
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 学生作业作答
     */
    @Override
    public List<HomeworkAnswerVo> selectHomeworkAnswerList(HomeworkAnswer homeworkAnswer)
    {
        return homeworkAnswerMapper.selectHomeworkAnswerList(homeworkAnswer);
    }

    /**
     * 新增学生作业作答
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHomeworkAnswer(HomeworkAnswer homeworkAnswer)
    {
        homeworkAnswer.setCreateTime(DateUtils.getNowDate());
        int rows = homeworkAnswerMapper.insertHomeworkAnswer(homeworkAnswer);
        insertHomeworkAssignment(homeworkAnswer);
        return rows;
    }

    /**
     * 修改学生作业作答
     * 
     * @param homeworkAnswer 学生作业作答
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHomeworkAnswer(HomeworkAnswer homeworkAnswer)
    {
        HomeworkAnswerVo homeworkAnswerVo = homeworkAnswerMapper.selectHomeworkAnswerByHomeworkAnswerId(homeworkAnswer.getHomeworkAnswerId());
        homeworkAnswer.setUpdateTime(DateUtils.getNowDate());
        HomeworkAssignmentVo homeworkAssignmentVo = homeworkAssignmentMapper.selectHomeworkAssignmentByAssignmentId(homeworkAnswerVo.getAssignmentId());
        homeworkAssignmentVo.setHomeworkStatus("2");
        HomeworkAssignment homeworkAssignment = new HomeworkAssignment();
        BeanUtils.copyProperties(homeworkAssignmentVo,homeworkAssignment);
        homeworkAssignmentMapper.updateHomeworkAssignment(homeworkAssignment);
        return homeworkAnswerMapper.updateHomeworkAnswer(homeworkAnswer);
    }

    /**
     * 批量删除学生作业作答
     * 
     * @param homeworkAnswerIds 需要删除的学生作业作答主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHomeworkAnswerByHomeworkAnswerIds(Long[] homeworkAnswerIds)
    {
        homeworkAnswerMapper.deleteHomeworkAssignmentByAssignmentIds(homeworkAnswerIds);
        return homeworkAnswerMapper.deleteHomeworkAnswerByHomeworkAnswerIds(homeworkAnswerIds);
    }

    /**
     * 删除学生作业作答信息
     * 
     * @param homeworkAnswerId 学生作业作答主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHomeworkAnswerByHomeworkAnswerId(Long homeworkAnswerId)
    {
        homeworkAnswerMapper.deleteHomeworkAssignmentByAssignmentId(homeworkAnswerId);
        return homeworkAnswerMapper.deleteHomeworkAnswerByHomeworkAnswerId(homeworkAnswerId);
    }

    /**
     * 新增作业分配信息
     * 
     * @param homeworkAnswer 学生作业作答对象
     */
    public void insertHomeworkAssignment(HomeworkAnswer homeworkAnswer)
    {
        List<HomeworkAssignment> homeworkAssignmentList = homeworkAnswer.getHomeworkAssignmentList();
        Long homeworkAnswerId = homeworkAnswer.getHomeworkAnswerId();
        if (StringUtils.isNotNull(homeworkAssignmentList))
        {
            List<HomeworkAssignment> list = new ArrayList<HomeworkAssignment>();
            for (HomeworkAssignment homeworkAssignment : homeworkAssignmentList)
            {
                homeworkAssignment.setAssignmentId(homeworkAnswerId);
                list.add(homeworkAssignment);
            }
            if (list.size() > 0)
            {
                homeworkAnswerMapper.batchHomeworkAssignment(list);
            }
        }
    }
}
