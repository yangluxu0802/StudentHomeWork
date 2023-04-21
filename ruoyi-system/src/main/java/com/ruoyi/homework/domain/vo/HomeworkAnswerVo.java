package com.ruoyi.homework.domain.vo;

import com.ruoyi.homework.domain.HomeworkAnswer;

public class HomeworkAnswerVo extends HomeworkAnswer {
    private String deptName;
    private String userName;
    private String homeworkStatus;
    private String homeworkName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getHomeworkStatus() {
        return homeworkStatus;
    }

    @Override
    public void setHomeworkStatus(String homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }


}
