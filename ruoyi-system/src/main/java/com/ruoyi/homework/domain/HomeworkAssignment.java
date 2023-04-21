package com.ruoyi.homework.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业分配对象 homework_assignment
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public class HomeworkAssignment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long assignmentId;

    /** 教师 */
    @Excel(name = "教师")
    private Long teacherId;

    /** 作业 */
    @Excel(name = "作业")
    private Long homeworkId;

    /** 班级 */
    @Excel(name = "班级")
    private Long classId;

    /** 学生 */
    @Excel(name = "学生")
    private Long studentId;

    /** 作答 */
    @Excel(name = "作答")
    private Long homeworkAnswerId;

    /** 作业分数 */
    @Excel(name = "作业分数")
    private String score;

    /** 作业状态，0表示未提交，1表示已提交，2表示已批改 */
    @Excel(name = "作业状态，0表示未提交，1表示已提交，2表示已批改")
    private String homeworkStatus;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submissionTime;

    public void setAssignmentId(Long assignmentId) 
    {
        this.assignmentId = assignmentId;
    }

    public Long getAssignmentId() 
    {
        return assignmentId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setHomeworkAnswerId(Long homeworkAnswerId) 
    {
        this.homeworkAnswerId = homeworkAnswerId;
    }

    public Long getHomeworkAnswerId() 
    {
        return homeworkAnswerId;
    }
    public void setScore(String score) 
    {
        this.score = score;
    }

    public String getScore() 
    {
        return score;
    }
    public void setHomeworkStatus(String homeworkStatus) 
    {
        this.homeworkStatus = homeworkStatus;
    }

    public String getHomeworkStatus() 
    {
        return homeworkStatus;
    }
    public void setSubmissionTime(Date submissionTime) 
    {
        this.submissionTime = submissionTime;
    }

    public Date getSubmissionTime() 
    {
        return submissionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("assignmentId", getAssignmentId())
            .append("teacherId", getTeacherId())
            .append("homeworkId", getHomeworkId())
            .append("classId", getClassId())
            .append("studentId", getStudentId())
            .append("homeworkAnswerId", getHomeworkAnswerId())
            .append("score", getScore())
            .append("homeworkStatus", getHomeworkStatus())
            .append("submissionTime", getSubmissionTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
