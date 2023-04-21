<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="作业名称" prop="homeworkName">
        <el-input
            v-model="queryParams.homeworkName"
            placeholder="请输入作业名称"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="开始日期" prop="beginDate">
        <el-date-picker clearable
                        v-model="queryParams.beginDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="endDate">
        <el-date-picker clearable
                        v-model="queryParams.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['homework:homework:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['homework:homework:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['homework:homework:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['homework:homework:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="homeworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="作业名称" align="center" prop="homeworkName" width="360"/>
      <el-table-column label="开始日期" align="center" prop="beginDate" width="360">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="endDate" width="360">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['homework:homework:edit']"
          >修改
          </el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['homework:homework:remove']"
          >删除
          </el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-share"
              @click="handleAssign(scope.row)"
              v-hasPermi="['homework:homework:edit']"
          >发布
          </el-button>
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

    <!-- 添加或修改发布作业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业名称" prop="homeworkName">
          <el-input v-model="form.homeworkName" placeholder="请输入作业名称"/>
        </el-form-item>
        <el-form-item label="作业内容">
          <editor v-model="form.homeworkContent" :min-height="300"/>
        </el-form-item>
<!--        <el-form-item label="分配班级">
          <el-select v-model="form.deptId" placeholder="请选择班级">
            <el-option
                v-for="item in deptOptions"
                :key="item.deptId"
                :label="item.deptName"
                :value="item.deptId"
            ></el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="附件" prop="attachment">
          <file-upload v-model="form.attachment" placeholder="请输入附件" :fileType=fileType ></file-upload>
        </el-form-item>
        <el-form-item label="开始日期" prop="beginDate">
          <el-date-picker clearable
                          v-model="form.beginDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker clearable
                          v-model="form.endDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="assignOpen" width="400px" append-to-body>
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="班级">
          <el-select v-model="form.classId" placeholder="请选择班级">
            <el-option
                v-for="item in deptOptions"
                :key="item.deptId"
                :label="item.deptName"
                :value="item.deptId"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="assignSubmitForm">确 定</el-button>
        <el-button @click="assignCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addHomework, delHomework, getHomework, listHomework, updateHomework} from "@/api/homework/homework";
import {listLastNode} from "@/api/system/dept";
import {addAssignment} from "@/api/homework/myHomework";

export default {
  name: "Homework",
  data() {
    return {
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
      // 发布作业表格数据
      homeworkList: [],
      deptOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层
      assignOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        classId: null,
        homeworkName: null,
        homeworkContent: null,
        attachment: null,
        beginDate: null,
        endDate: null,
      },
      // 表单参数
      form: {},
      fileType: ["doc", "xls", "ppt", "txt", "pdf","docx","xlsx"],
      // 表单校验
      rules: {
        homeworkName: [
          {required: true, message: "作业名称不能为空", trigger: "blur"}
        ],
        homeworkContent: [
          {required: true, message: "作业内容不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询发布作业列表 */
    getList() {
      this.loading = true;
      listHomework(this.queryParams).then(response => {
        this.homeworkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    assignCancel() {
      this.assignOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        homeworkId: null,
        teacherId: null,
        deptId: [],
        homeworkName: null,
        homeworkContent: null,
        attachment: null,
        beginDate: null,
        endDate: null,
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
      this.ids = selection.map(item => item.homeworkId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      listLastNode().then(response => {
        this.deptOptions = response.data;
        this.open = true;
        this.title = "添加作业";
      });
    },
    /** 分配按钮操作 */
    handleAssign(row) {
      this.reset();
      const homeworkId = row.homeworkId || this.ids
      this.form.homeworkId = homeworkId
      listLastNode().then(response => {
        this.deptOptions = response.data;
        this.assignOpen = true;
        this.title = "发布作业";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const homeworkId = row.homeworkId || this.ids
      getHomework(homeworkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作业";
        console.log(this.form)
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.homeworkId != null) {
            updateHomework(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHomework(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    assignSubmitForm() {
      if (this.form.homeworkId != null) {
        addAssignment(this.form).then(response => {
          this.$modal.msgSuccess("发布作业成功");
          this.assignOpen = false;
        })
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const homeworkIds = row.homeworkId || this.ids;
      this.$modal.confirm('是否确认删除发布作业编号为"' + homeworkIds + '"的数据项？').then(function () {
        return delHomework(homeworkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homework/homework/export', {
        ...this.queryParams
      }, `homework_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
