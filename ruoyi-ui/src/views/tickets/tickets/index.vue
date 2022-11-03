<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工作中心代码" prop="wcennunm">
        <el-input
          v-model="queryParams.wcennunm"
          placeholder="请输入工作中心代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求日期" prop="dateOfDemand">
        <el-date-picker clearable
          v-model="queryParams.dateOfDemand"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择需求日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="派工日期" prop="dd">
        <el-date-picker clearable
          v-model="queryParams.dd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择派工日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="执行状况" prop="ec">
        <el-input
          v-model="queryParams.ec"
          placeholder="请输入执行状况"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['tickets:tickets:add']"
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
          v-hasPermi="['tickets:tickets:edit']"
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
          v-hasPermi="['tickets:tickets:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tickets:tickets:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ticketsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="派工单号" align="center" prop="mcnum" />
      <el-table-column label="工作中心代码" align="center" prop="wcennunm" />
      <el-table-column label="需求日期" align="center" prop="dateOfDemand" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateOfDemand, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="派工日期" align="center" prop="dd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行状况" align="center" prop="ec" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tickets:tickets:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tickets:tickets:remove']"
          >删除</el-button>
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

    <!-- 添加或修改生产工票对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工作中心代码" prop="wcennunm">
          <el-input v-model="form.wcennunm" placeholder="请输入工作中心代码" />
        </el-form-item>
        <el-form-item label="需求日期" prop="dateOfDemand">
          <el-date-picker clearable
            v-model="form.dateOfDemand"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择需求日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="派工日期" prop="dd">
          <el-date-picker clearable
            v-model="form.dd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择派工日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="执行状况" prop="ec">
          <el-input v-model="form.ec" placeholder="请输入执行状况" />
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
import { listTickets, getTickets, delTickets, addTickets, updateTickets } from "@/api/tickets/tickets";

export default {
  name: "Tickets",
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
      // 生产工票表格数据
      ticketsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wcennunm: null,
        dateOfDemand: null,
        dd: null,
        ec: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wcennunm: [
          { required: true, message: "工作中心代码不能为空", trigger: "blur" }
        ],
        dateOfDemand: [
          { required: true, message: "需求日期不能为空", trigger: "blur" }
        ],
        dd: [
          { required: true, message: "派工日期不能为空", trigger: "blur" }
        ],
        ec: [
          { required: true, message: "执行状况不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产工票列表 */
    getList() {
      this.loading = true;
      listTickets(this.queryParams).then(response => {
        this.ticketsList = response.rows;
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
        mcnum: null,
        wcennunm: null,
        dateOfDemand: null,
        dd: null,
        ec: null
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
      this.ids = selection.map(item => item.mcnum)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工票";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mcnum = row.mcnum || this.ids
      getTickets(mcnum).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产工票";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mcnum != null) {
            updateTickets(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTickets(this.form).then(response => {
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
      const mcnums = row.mcnum || this.ids;
      this.$modal.confirm('是否确认删除生产工票编号为"' + mcnums + '"的数据项？').then(function() {
        return delTickets(mcnums);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tickets/tickets/export', {
        ...this.queryParams
      }, `tickets_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
