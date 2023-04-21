<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="提交时间" prop="submissionTime">
        <el-date-picker clearable
          v-model="queryParams.submissionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>-->

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['homework:answer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['homework:answer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['homework:answer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homework:answer:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="answerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="班级" align="center" prop="deptName" />
      <el-table-column label="学生" align="center" prop="userName" />
      <el-table-column label="作业标题" align="center" prop="homeworkName" />
      <el-table-column prop="status" label="作业状态" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.homework_status" :value="scope.row.homeworkStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="submissionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submissionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="附件" align="center" prop="answerAttachment" >
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-download"
              @click="handleDownload(scope.row)"
              v-show="scope.row.answerAttachment"
          >下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homework:answer:edit']"
            v-if="scope.row.homeworkStatus != 2 "
          >批阅</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homework:answer:remove']"
          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学生作业作答对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生作答" prop="homeworkAnswerContent">
          <editor v-model="form.homeworkAnswerContent" :min-height="300"/>
        </el-form-item>
        <el-form-item label="作业分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入作业分数" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAnswer, getAnswer, delAnswer, addAnswer, updateAnswer } from "@/api/homework/answer";

export default {
  name: "Answer",
  dicts: ['homework_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedHomeworkAssignment: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 学生作业作答表格数据
      answerList: [],
      // 作业分配表格数据
      homeworkAssignmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        homeworkAnswerContent: null,
        answerAttachment: null,
        score: null,
        homeworkStatus: null,
        submissionTime: null,
        assignmentId: null,
        homeworkAnswerId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 文件下载处理
    handleDownload(row) {
      this.$download.resource(row.answerAttachment)
    },
    /** 查询学生作业作答列表 */
    getList() {
      this.loading = true;
      listAnswer(this.queryParams).then(response => {
        this.answerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        homeworkAnswerId: null,
        assignmentId: null,
        homeworkAnswerContent: null,
        answerAttachment: null,
        score: null,
        homeworkStatus: null,
        submissionTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.homeworkAssignmentList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.homeworkAnswerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生作业作答";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const homeworkAnswerId = row.homeworkAnswerId || this.ids
      getAnswer(homeworkAnswerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "批阅";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.homeworkAssignmentList = this.homeworkAssignmentList;
          if (this.form.homeworkAnswerId != null) {
            updateAnswer(this.form).then(response => {
              this.$modal.msgSuccess("批阅成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnswer(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const homeworkAnswerIds = row.homeworkAnswerId || this.ids;
      this.$modal.confirm('是否确认删除学生作业作答编号为"' + homeworkAnswerIds + '"的数据项？').then(function() {
        return delAnswer(homeworkAnswerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 作业分配序号 */
    rowHomeworkAssignmentIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 作业分配添加按钮操作 */
    handleAddHomeworkAssignment() {
      let obj = {};
      obj.teacherId = "";
      obj.homeworkId = "";
      obj.classId = "";
      obj.studentId = "";
      obj.homeworkAnswerId = "";
      obj.score = "";
      obj.homeworkStatus = "";
      obj.submissionTime = "";
      obj.remark = "";
      this.homeworkAssignmentList.push(obj);
    },
    /** 作业分配删除按钮操作 */
    handleDeleteHomeworkAssignment() {
      if (this.checkedHomeworkAssignment.length == 0) {
        this.$modal.msgError("请先选择要删除的作业分配数据");
      } else {
        const homeworkAssignmentList = this.homeworkAssignmentList;
        const checkedHomeworkAssignment = this.checkedHomeworkAssignment;
        this.homeworkAssignmentList = homeworkAssignmentList.filter(function(item) {
          return checkedHomeworkAssignment.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleHomeworkAssignmentSelectionChange(selection) {
      this.checkedHomeworkAssignment = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homework/answer/export', {
        ...this.queryParams
      }, `answer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
