<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="移库单编号" prop="moveId">
        <el-input
          v-model="queryParams.moveId"
          placeholder="请输入移库单编号"
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
      <el-form-item label="原始库位" prop="from">
        <el-input
          v-model="queryParams.from"
          placeholder="请输入原始库位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标库位" prop="to">
        <el-input
          v-model="queryParams.to"
          placeholder="请输入目标库位"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storagemovedetail:movedetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="movedetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="移库单编号" align="center" prop="moveId" />
      <el-table-column label="物料编号" align="center" prop="materialId" />
      <el-table-column label="移库数量" align="center" prop="number" />
      <el-table-column label="原始库位" align="center" prop="from" />
      <el-table-column label="目标库位" align="center" prop="to" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改移动明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="移库单编号" prop="moveId">
          <el-input v-model="form.moveId" placeholder="请输入移库单编号" />
        </el-form-item>
        <el-form-item label="物料编号" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入物料编号" />
        </el-form-item>
        <el-form-item label="移库数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入移库数量" />
        </el-form-item>
        <el-form-item label="原始库位" prop="from">
          <el-input v-model="form.from" placeholder="请输入原始库位" />
        </el-form-item>
        <el-form-item label="目标库位" prop="to">
          <el-input v-model="form.to" placeholder="请输入目标库位" />
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
import { listMovedetail, getMovedetail, delMovedetail, addMovedetail, updateMovedetail } from "@/api/storagemovedetail/movedetail";

export default {
  name: "Movedetail",
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
      // 移动明细表格数据
      movedetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        moveId: null,
        materialId: null,
        from: null,
        to: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        moveId: [
          { required: true, message: "移库单编号不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "物料编号不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "移库数量不能为空", trigger: "blur" }
        ],
        from: [
          { required: true, message: "原始库位不能为空", trigger: "blur" }
        ],
        to: [
          { required: true, message: "目标库位不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询移动明细列表 */
    getList() {
      this.loading = true;
      listMovedetail(this.queryParams).then(response => {
        this.movedetailList = response.rows;
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
        moveId: null,
        materialId: null,
        number: null,
        from: null,
        to: null
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
      this.title = "添加移动明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMovedetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改移动明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMovedetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMovedetail(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除移动明细编号为"' + ids + '"的数据项？').then(function() {
        return delMovedetail(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storagemovedetail/movedetail/export', {
        ...this.queryParams
      }, `movedetail_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
