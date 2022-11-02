<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工艺名称" prop="craftName">
        <el-input
          v-model="queryParams.craftName"
          placeholder="请输入工艺名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父物料" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父物料"
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
          v-hasPermi="['system:craft:add']"
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
          v-hasPermi="['system:craft:edit']"
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
          v-hasPermi="['system:craft:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:craft:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="craftList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工艺编号" align="center" prop="id" />
      <el-table-column label="工艺名称" align="center" prop="craftName" />
      <el-table-column label="子物料" align="center" prop="materialId" />
      <el-table-column label="父物料" align="center" prop="parentId" />
      <el-table-column label="预计耗时(单件)" align="center" prop="duration" />
      <el-table-column label="车间编号" align="center" prop="factoryId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:craft:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:craft:remove']"
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

    <!-- 添加或修改工艺管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工艺名称" prop="craftName">
          <el-input v-model="form.craftName" placeholder="请输入工艺名称" />
        </el-form-item>
        <el-form-item label="子物料" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入子物料" />
        </el-form-item>
        <el-form-item label="父物料" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父物料" />
        </el-form-item>
        <el-form-item label="预计耗时(单件)" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入预计耗时(单件)" />
        </el-form-item>
        <el-form-item label="车间编号" prop="factoryId">
          <el-input v-model="form.factoryId" placeholder="请输入车间编号" />
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
import { listCraft, getCraft, delCraft, addCraft, updateCraft } from "@/api/system/craft";

export default {
  name: "Craft",
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
      // 工艺管理表格数据
      craftList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        craftName: null,
        parentId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        craftName: [
          { required: true, message: "工艺名称不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "子物料不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "父物料不能为空", trigger: "blur" }
        ],
        duration: [
          { required: true, message: "预计耗时(单件)不能为空", trigger: "blur" }
        ],
        factoryId: [
          { required: true, message: "车间编号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工艺管理列表 */
    getList() {
      this.loading = true;
      listCraft(this.queryParams).then(response => {
        this.craftList = response.rows;
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
        craftName: null,
        materialId: null,
        parentId: null,
        duration: null,
        factoryId: null
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
      this.title = "添加工艺管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCraft(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工艺管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCraft(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCraft(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工艺管理编号为"' + ids + '"的数据项？').then(function() {
        return delCraft(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/craft/export', {
        ...this.queryParams
      }, `craft_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
