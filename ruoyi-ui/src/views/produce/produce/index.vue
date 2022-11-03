<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产日期" prop="mrpProduceDate">
        <el-date-picker clearable
          v-model="queryParams.mrpProduceDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择生产日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生产产品名称编号" prop="mrpProduceNameid">
        <el-input
          v-model="queryParams.mrpProduceNameid"
          placeholder="请输入生产产品名称编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产数量" prop="mrpProduceNumber">
        <el-input
          v-model="queryParams.mrpProduceNumber"
          placeholder="请输入生产数量"
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
          v-hasPermi="['produce:produce:add']"
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
          v-hasPermi="['produce:produce:edit']"
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
          v-hasPermi="['produce:produce:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['produce:produce:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="produceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生产编号" align="center" prop="mrpProduceId" />
      <el-table-column label="生产日期" align="center" prop="mrpProduceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.mrpProduceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产产品名称编号" align="center" prop="mrpProduceNameid" />
      <el-table-column label="生产数量" align="center" prop="mrpProduceNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['produce:produce:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['produce:produce:remove']"
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

    <!-- 添加或修改生产对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生产日期" prop="mrpProduceDate">
          <el-date-picker clearable
            v-model="form.mrpProduceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生产日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产产品名称编号" prop="mrpProduceNameid">
          <el-input v-model="form.mrpProduceNameid" placeholder="请输入生产产品名称编号" />
        </el-form-item>
        <el-form-item label="生产数量" prop="mrpProduceNumber">
          <el-input v-model="form.mrpProduceNumber" placeholder="请输入生产数量" />
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
import { listProduce, getProduce, delProduce, addProduce, updateProduce } from "@/api/produce/produce";

export default {
  name: "Produce",
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
      // 生产表格数据
      produceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mrpProduceDate: null,
        mrpProduceNameid: null,
        mrpProduceNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mrpProduceDate: [
          { required: true, message: "生产日期不能为空", trigger: "blur" }
        ],
        mrpProduceNameid: [
          { required: true, message: "生产产品名称编号不能为空", trigger: "blur" }
        ],
        mrpProduceNumber: [
          { required: true, message: "生产数量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产列表 */
    getList() {
      this.loading = true;
      listProduce(this.queryParams).then(response => {
        this.produceList = response.rows;
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
        mrpProduceId: null,
        mrpProduceDate: null,
        mrpProduceNameid: null,
        mrpProduceNumber: null
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
      this.ids = selection.map(item => item.mrpProduceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mrpProduceId = row.mrpProduceId || this.ids
      getProduce(mrpProduceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mrpProduceId != null) {
            updateProduce(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduce(this.form).then(response => {
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
      const mrpProduceIds = row.mrpProduceId || this.ids;
      this.$modal.confirm('是否确认删除生产编号为"' + mrpProduceIds + '"的数据项？').then(function() {
        return delProduce(mrpProduceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('produce/produce/export', {
        ...this.queryParams
      }, `produce_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
