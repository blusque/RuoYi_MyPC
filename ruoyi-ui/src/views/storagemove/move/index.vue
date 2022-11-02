<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="移库单编号" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入移库单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="移库日期" prop="date">
        <el-date-picker clearable
          v-model="queryParams.date"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择移库日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作员工编号" prop="staffId">
        <el-input
          v-model="queryParams.staffId"
          placeholder="请输入操作员工编号"
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
          v-hasPermi="['storagemove:move:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['storagemove:move:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storagemove:move:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="moveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="移库单编号" align="center" prop="id" />
      <el-table-column label="移库日期" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员工编号" align="center" prop="staffId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['storagemove:move:remove']"
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

    <!-- 添加或修改移动操作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="移库日期" prop="date">
          <el-date-picker clearable
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择移库日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作员工编号" prop="staffId">
          <el-input v-model="form.staffId" placeholder="请输入操作员工编号" />
        </el-form-item>
        <el-divider content-position="center">移动明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddStorageMoveDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteStorageMoveDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="storageMoveDetailList" :row-class-name="rowStorageMoveDetailIndex" @selection-change="handleStorageMoveDetailSelectionChange" ref="storageMoveDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料编号" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialId" placeholder="请输入物料编号" />
            </template>
          </el-table-column>
          <el-table-column label="移库数量" prop="number" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.number" placeholder="请输入移库数量" />
            </template>
          </el-table-column>
          <el-table-column label="原始库位" prop="from" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.from" placeholder="请输入原始库位" />
            </template>
          </el-table-column>
          <el-table-column label="目标库位" prop="to" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.to" placeholder="请输入目标库位" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMove, getMove, delMove, addMove, updateMove } from "@/api/storagemove/move";

export default {
  name: "Move",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedStorageMoveDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 移动操作表格数据
      moveList: [],
      // 移动明细表格数据
      storageMoveDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        date: null,
        staffId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        date: [
          { required: true, message: "移库日期不能为空", trigger: "blur" }
        ],
        staffId: [
          { required: true, message: "操作员工编号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询移动操作列表 */
    getList() {
      this.loading = true;
      listMove(this.queryParams).then(response => {
        this.moveList = response.rows;
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
        date: null,
        staffId: null
      };
      this.storageMoveDetailList = [];
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
      this.title = "添加移动操作";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMove(id).then(response => {
        this.form = response.data;
        this.storageMoveDetailList = response.data.storageMoveDetailList;
        this.open = true;
        this.title = "修改移动操作";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.storageMoveDetailList = this.storageMoveDetailList;
          if (this.form.id != null) {
            updateMove(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMove(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除移动操作编号为"' + ids + '"的数据项？').then(function() {
        return delMove(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 移动明细序号 */
    rowStorageMoveDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 移动明细添加按钮操作 */
    handleAddStorageMoveDetail() {
      let obj = {};
      obj.materialId = "";
      obj.number = "";
      obj.from = "";
      obj.to = "";
      this.storageMoveDetailList.push(obj);
    },
    /** 移动明细删除按钮操作 */
    handleDeleteStorageMoveDetail() {
      if (this.checkedStorageMoveDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的移动明细数据");
      } else {
        const storageMoveDetailList = this.storageMoveDetailList;
        const checkedStorageMoveDetail = this.checkedStorageMoveDetail;
        this.storageMoveDetailList = storageMoveDetailList.filter(function(item) {
          return checkedStorageMoveDetail.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleStorageMoveDetailSelectionChange(selection) {
      this.checkedStorageMoveDetail = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storagemove/move/export', {
        ...this.queryParams
      }, `move_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
