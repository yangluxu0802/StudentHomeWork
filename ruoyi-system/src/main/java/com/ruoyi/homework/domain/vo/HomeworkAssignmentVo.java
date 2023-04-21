package com.ruoyi.homework.domain.vo;

import com.ruoyi.homework.domain.HomeworkAssignment;

import java.util.Date;

public class HomeworkAssignmentVo extends HomeworkAssignment {

    private String teacherName;
    private String homeworkName;
    private String deptName;
    private Date beginDate;
    private Date endDate;
    private String homeworkContent;
    private String attachment;
    private String homeworkAnswerContent;
    private String answerAttachment;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(String homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    private String score;
    private String homeworkStatus;
    private Date submissionTime;

    public String getHomeworkAnswerContent() {
        return homeworkAnswerContent;
    }

    public void setHomeworkAnswerContent(String homeworkAnswerContent) {
        this.homeworkAnswerContent = homeworkAnswerContent;
    }

    public String getAnswerAttachment() {
        return answerAttachment;
    }

    public void setAnswerAttachment(String answerAttachment) {
        this.answerAttachment = answerAttachment;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
