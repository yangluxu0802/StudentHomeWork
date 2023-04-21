<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>-->

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assignmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="老师" align="center" prop="teacherName" />
      <el-table-column label="班级" align="center" prop="deptName" />
      <el-table-column label="作业标题" align="center" prop="homeworkName" />
      <el-table-column prop="status" label="作业状态" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.homework_status" :value="scope.row.homeworkStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="beginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作业分数" align="center" prop="score" />
      <el-table-column label="附件" align="center" prop="attachment" >
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-download"
              @click="handleDownload(scope.row)"
              v-show="scope.row.attachment"
          >下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleComplete(scope.row)"
            v-hasPermi="['homework:my:complete']"
            v-if="scope.row.homeworkStatus == 0 "
          >写作业</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homework:assignment:remove']"
            v-if="scope.row.homeworkStatus === 0 "
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

    <!-- 添加或修改作业分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业标题" prop="homeworkName">
          <el-input v-model="form.homeworkName" placeholder="请输入教师" :disabled="false"/>
        </el-form-item>
        <el-form-item label="作业内容" prop="homeworkContent">
          <editor v-model="form.homeworkContent" :min-height="300"/>
        </el-form-item>
        <el-form-item label="学生作答" prop="studentId">
          <editor v-model="form.homeworkAnswerContent" :min-height="300"/>
        </el-form-item>
        <el-form-item label="附件上传" prop="homeworkAnswerId">
          <file-upload v-model="form.answerAttachment" placeholder="请输入附件" :fileType=fileType></file-upload>
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
import {
  listAssignment,
  getAssignment,
  delAssignment,
  addAssignment,
  complete
} from "@/api/homework/myHomework";

export default {
  name: "Assignment",
  dicts: ['homework_status'],
  data() {
    return {
      fileType: ["doc", "xls", "ppt", "txt", "pdf","docx","xlsx"],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作业分配表格数据
      assignmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        homeworkId: null,
        classId: null,
        studentId: null,
        homeworkAnswerId: null,
        score: null,
        homeworkStatus: null,
        submissionTime: null,
        homeworkAnswerContent: null,
        answerAttachment: null,
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
      this.$download.resource(row.attachment)
    },
    /** 查询作业分配列表 */
    getList() {
      this.loading = true;
      listAssignment(this.queryParams).then(response => {
        this.assignmentList = response.rows;
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
        assignmentId: null,
        teacherId: null,
        homeworkId: null,
        classId: null,
        studentId: null,
        homeworkAnswerId: null,
        score: null,
        homeworkStatus: null,
        submissionTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
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
      this.ids = selection.map(item => item.assignmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加作业分配";
    },
    handleComplete(row) {
      this.reset();
      const assignmentId = row.assignmentId || this.ids
      getAssignment(assignmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "写作业";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.assignmentId != null) {
            complete(this.form).then(response => {
              this.$modal.msgSuccess("提交成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const assignmentIds = row.assignmentId || this.ids;
      this.$modal.confirm('是否确认删除作业分配编号为"' + assignmentIds + '"的数据项？').then(function() {
        return delAssignment(assignmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homework/assignment/export', {
        ...this.queryParams
      }, `assignment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
