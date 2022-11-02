<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="出入库单编号" prop="outinId">
        <el-input
          v-model="queryParams.outinId"
          placeholder="请输入出入库单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料编号" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入物料编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库位编号" prop="storageId">
        <el-input
          v-model="queryParams.storageId"
          placeholder="请输入库位编号"
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
          v-hasPermi="['storageoutindetail:outindetail:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storageoutindetail:outindetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outindetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出入库单编号" align="center" prop="outinId" />
      <el-table-column label="物料编号" align="center" prop="materialId" />
      <el-table-column label="库位编号" align="center" prop="storageId" />
      <el-table-column label="出入库数量" align="center" prop="number" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改出入明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="出入库单编号" prop="outinId">
          <el-input v-model="form.outinId" placeholder="请输入出入库单编号" />
        </el-form-item>
        <el-form-item label="物料编号" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入物料编号" />
        </el-form-item>
        <el-form-item label="库位编号" prop="storageId">
          <el-input v-model="form.storageId" placeholder="请输入库位编号" />
        </el-form-item>
        <el-form-item label="出入库数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入出入库数量" />
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
import { listOutindetail, getOutindetail, delOutindetail, addOutindetail, updateOutindetail } from "@/api/storageoutindetail/outindetail";

export default {
  name: "Outindetail",
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
      // 出入明细表格数据
      outindetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        outinId: null,
        materialId: null,
        storageId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        outinId: [
          { required: true, message: "出入库单编号不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "物料编号不能为空", trigger: "blur" }
        ],
        storageId: [
          { required: true, message: "库位编号不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "出入库数量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询出入明细列表 */
    getList() {
      this.loading = true;
      listOutindetail(this.queryParams).then(response => {
        this.outindetailList = response.rows;
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
        id: null,
        outinId: null,
        materialId: null,
        storageId: null,
        number: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出入明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOutindetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出入明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOutindetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutindetail(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除出入明细编号为"' + ids + '"的数据项？').then(function() {
        return delOutindetail(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storageoutindetail/outindetail/export', {
        ...this.queryParams
      }, `outindetail_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
