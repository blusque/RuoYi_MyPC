<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="出入库单编号" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入出入库单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出/入库" prop="outorin">
          <el-radio-group v-model="queryParams.outorin" size="medium" clearable
                          @keyup.enter.native="handleQuery" >
            <el-radio v-for="(item, index) in field101Options" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label}}</el-radio>
          </el-radio-group>
      </el-form-item>
      <el-form-item label="库单类型" prop="kdtype">
        <el-select v-model="queryParams.kdtype" placeholder="请选择库单类型" clearable :style="{width: '100%'}"
                   @keyup.enter.native="handleQuery">
          <el-option v-for="(item, index) in field102Options" :key="index" :label="item.label"
                     :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="出入库日期" prop="date">
        <el-date-picker clearable
          v-model="queryParams.date"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出入库日期">
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
          v-hasPermi="['storageoutin:outin:add']"
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
          v-hasPermi="['storageoutin:outin:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storageoutin:outin:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outinList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出入库单编号" align="center" prop="id" />
      <el-table-column label="出/入库" align="center" prop="outorin" />
      <el-table-column label="库单类型" align="center" prop="kdtype" />
      <el-table-column label="出入库日期" align="center" prop="date" width="180">
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
            v-hasPermi="['storageoutin:outin:remove']"
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

    <!-- 添加或修改出入操作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="出/入库" prop="outorin">
          <el-radio-group v-model="form.outorin" size="medium">
            <el-radio v-for="(item, index) in field101Options" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="库单类型" prop="kytype">
          <el-select v-model="form.kdtype" placeholder="请选择库单类型" clearable :style="{width: '100%'}">
            <el-option v-for="(item, index) in field102Options" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出入库日期" prop="date">
          <el-date-picker clearable
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出入库日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作员工编号" prop="staffId">
          <el-input v-model="form.staffId" placeholder="请输入操作员工编号" />
        </el-form-item>
        <el-divider content-position="center">出入明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddStorageOutinDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteStorageOutinDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="storageOutinDetailList" :row-class-name="rowStorageOutinDetailIndex" @selection-change="handleStorageOutinDetailSelectionChange" ref="storageOutinDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料编号" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialId" placeholder="请输入物料编号" />
            </template>
          </el-table-column>
          <el-table-column label="库位编号" prop="storageId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.storageId" placeholder="请输入库位编号" />
            </template>
          </el-table-column>
          <el-table-column label="出入库数量" prop="number" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.number" placeholder="请输入出入库数量" />
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
import { listOutin, getOutin, delOutin, addOutin, updateOutin } from "@/api/storageoutin/outin";

export default {
  name: "Outin",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedStorageOutinDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 出入操作表格数据
      outinList: [],
      // 出入明细表格数据
      storageOutinDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        outorin: null,
        kdtype: null,
        date: null,
        staffId: null
      },
      // 表单参数
      form: {
        outorin: undefined,
        kdtype: undefined,
      },
      // 表单校验
      rules: {
        outorin: [{
          required: true,
          message: '出/入库必须选择',
          trigger: 'change'
        }],
        kdtype: [{
          required: true,
          message: '库单类型必须选择',
          trigger: 'change'
        }],
        date: [
          { required: true, message: "出入库日期不能为空", trigger: "blur" }
        ],
        staffId: [
          { required: true, message: "操作员工编号不能为空", trigger: "blur" }
        ]
      },
      field101Options: [{
        "label": "入库",
        "value": '入库'
      }, {
        "label": "出库",
        "value": '出库'
      }],
      field102Options: [{
        "label": "PO",
        "value": 'PO'
      }, {
        "label": "CO",
        "value": 'CO'
      }, {
        "label": "MO",
        "value": 'MO'
      }],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询出入操作列表 */
    getList() {
      this.loading = true;
      listOutin(this.queryParams).then(response => {
        this.outinList = response.rows;
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
        outorin: null,
        kdtype: null,
        date: null,
        staffId: null
      };
      this.storageOutinDetailList = [];
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
      this.title = "添加出入操作";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOutin(id).then(response => {
        this.form = response.data;
        this.storageOutinDetailList = response.data.storageOutinDetailList;
        this.open = true;
        this.title = "修改出入操作";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.storageOutinDetailList = this.storageOutinDetailList;
          if (this.form.id != null) {
            updateOutin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutin(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除出入操作编号为"' + ids + '"的数据项？').then(function() {
        return delOutin(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 出入明细序号 */
    rowStorageOutinDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 出入明细添加按钮操作 */
    handleAddStorageOutinDetail() {
      let obj = {};
      obj.materialId = "";
      obj.storageId = "";
      obj.number = "";
      this.storageOutinDetailList.push(obj);
    },
    /** 出入明细删除按钮操作 */
    handleDeleteStorageOutinDetail() {
      if (this.checkedStorageOutinDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的出入明细数据");
      } else {
        const storageOutinDetailList = this.storageOutinDetailList;
        const checkedStorageOutinDetail = this.checkedStorageOutinDetail;
        this.storageOutinDetailList = storageOutinDetailList.filter(function(item) {
          return checkedStorageOutinDetail.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleStorageOutinDetailSelectionChange(selection) {
      this.checkedStorageOutinDetail = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storageoutin/outin/export', {
        ...this.queryParams
      }, `outin_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
