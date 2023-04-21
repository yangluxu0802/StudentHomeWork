package com.ruoyi.homework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 学生作业作答对象 homework_answer
 *
 * @author ruoyi
 * @date 2023-04-17
 */
public class HomeworkAnswer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long homeworkAnswerId;

    /**
     * 分配的作业ID
     */
    @Excel(name = "分配的作业ID")
    private Long assignmentId;

    /**
     * 学生作答
     */
    @Excel(name = "学生作答")
    private String homeworkAnswerContent;

    /**
     * 附件
     */
    @Excel(name = "附件")
    private String answerAttachment;

    /**
     * 作业分数
     */
    @Excel(name = "作业分数")
    private String score;

    /**
     * 作业状态，0表示未提交，1表示已提交，2表示已批改
     */
    @Excel(name = "作业状态，0表示未提交，1表示已提交，2表示已批改")
    private String homeworkStatus;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submissionTime;

    /**
     * 作业分配信息
     */
    private List<HomeworkAssignment> homeworkAssignmentList;

    public void setHomeworkAnswerId(Long homeworkAnswerId) {
        this.homeworkAnswerId = homeworkAnswerId;
    }

    public Long getHomeworkAnswerId() {
        return homeworkAnswerId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setHomeworkAnswerContent(String homeworkAnswerContent) {
        this.homeworkAnswerContent = homeworkAnswerContent;
    }

    public String getHomeworkAnswerContent() {
        return homeworkAnswerContent;
    }

    public void setAnswerAttachment(String answerAttachment) {
        this.answerAttachment = answerAttachment;
    }

    public String getAnswerAttachment() {
        return answerAttachment;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setHomeworkStatus(String homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public String getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public List<HomeworkAssignment> getHomeworkAssignmentList() {
        return homeworkAssignmentList;
    }

    public void setHomeworkAssignmentList(List<HomeworkAssignment> homeworkAssignmentList) {
        this.homeworkAssignmentList = homeworkAssignmentList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("homeworkAnswerId", getHomeworkAnswerId())
                .append("assignmentId", getAssignmentId())
                .append("homeworkAnswerContent", getHomeworkAnswerContent())
                .append("answerAttachment", getAnswerAttachment())
                .append("score", getScore())
                .append("homeworkStatus", getHomeworkStatus())
                .append("submissionTime", getSubmissionTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("homeworkAssignmentList", getHomeworkAssignmentList())
                .toString();
    }
}
