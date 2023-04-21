package com.ruoyi.homework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 发布作业对象 homework
 *
 * @author ruoyi
 * @date 2023-04-09
 */
public class Homework extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long homeworkId;

    /**
     * 老师ID
     */
    @Excel(name = "老师ID")
    private Long teacherId;

    /**
     * 作业名称
     */
    @Excel(name = "作业名称")
    private String homeworkName;

    /**
     * 作业内容
     */
    @Excel(name = "作业内容")
    private String homeworkContent;

    /**
     * 附件
     */
    @Excel(name = "附件")
    private String attachment;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("homeworkId", getHomeworkId())
                .append("teacherId", getTeacherId())
                .append("homeworkName", getHomeworkName())
                .append("homeworkContent", getHomeworkContent())
                .append("attachment", getAttachment())
                .append("beginDate", getBeginDate())
                .append("endDate", getEndDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
