<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="采购时间" prop="mrpPurchaseDate">
        <el-date-picker clearable
          v-model="queryParams.mrpPurchaseDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择采购时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="采购产品名称编号" prop="mrpPurchaseNameid">
        <el-input
          v-model="queryParams.mrpPurchaseNameid"
          placeholder="请输入采购产品名称编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购产品数量" prop="mrpPurchaseNumber">
        <el-input
          v-model="queryParams.mrpPurchaseNumber"
          placeholder="请输入采购产品数量"
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
          v-hasPermi="['purchase:purchase:add']"
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
          v-hasPermi="['purchase:purchase:edit']"
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
          v-hasPermi="['purchase:purchase:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:purchase:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购编号" align="center" prop="mrpPurchaseId" />
      <el-table-column label="采购时间" align="center" prop="mrpPurchaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.mrpPurchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购产品名称编号" align="center" prop="mrpPurchaseNameid" />
      <el-table-column label="采购产品数量" align="center" prop="mrpPurchaseNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:purchase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:purchase:remove']"
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

    <!-- 添加或修改采购对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="采购时间" prop="mrpPurchaseDate">
          <el-date-picker clearable
            v-model="form.mrpPurchaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择采购时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="采购产品名称编号" prop="mrpPurchaseNameid">
          <el-input v-model="form.mrpPurchaseNameid" placeholder="请输入采购产品名称编号" />
        </el-form-item>
        <el-form-item label="采购产品数量" prop="mrpPurchaseNumber">
          <el-input v-model="form.mrpPurchaseNumber" placeholder="请输入采购产品数量" />
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
import { listPurchase, getPurchase, delPurchase, addPurchase, updatePurchase } from "@/api/purchase/purchase";

export default {
  name: "Purchase",
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
      // 采购表格数据
      purchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mrpPurchaseDate: null,
        mrpPurchaseNameid: null,
        mrpPurchaseNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mrpPurchaseDate: [
          { required: true, message: "采购时间不能为空", trigger: "blur" }
        ],
        mrpPurchaseNameid: [
          { required: true, message: "采购产品名称编号不能为空", trigger: "blur" }
        ],
        mrpPurchaseNumber: [
          { required: true, message: "采购产品数量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购列表 */
    getList() {
      this.loading = true;
      listPurchase(this.queryParams).then(response => {
        this.purchaseList = response.rows;
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
        mrpPurchaseId: null,
        mrpPurchaseDate: null,
        mrpPurchaseNameid: null,
        mrpPurchaseNumber: null
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
      this.ids = selection.map(item => item.mrpPurchaseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mrpPurchaseId = row.mrpPurchaseId || this.ids
      getPurchase(mrpPurchaseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mrpPurchaseId != null) {
            updatePurchase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchase(this.form).then(response => {
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
      const mrpPurchaseIds = row.mrpPurchaseId || this.ids;
      this.$modal.confirm('是否确认删除采购编号为"' + mrpPurchaseIds + '"的数据项？').then(function() {
        return delPurchase(mrpPurchaseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/purchase/export', {
        ...this.queryParams
      }, `purchase_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
