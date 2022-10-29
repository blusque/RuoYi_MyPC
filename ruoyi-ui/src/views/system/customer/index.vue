<template>
  <div class="app-container">
    <el-form :model="queryCustomerParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
      label-width="68px">
      <el-form-item label="客户姓名" prop="name">
        <el-input v-model="queryCustomerParams.name" placeholder="请输入客户姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户电话" prop="tel">
        <el-input v-model="queryCustomerParams.tel" placeholder="请输入客户电话" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户邮件" prop="email">
        <el-input v-model="queryCustomerParams.email" placeholder="请输入客户邮件" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户评级" prop="customerLevel">
        <el-select v-model="queryCustomerParams.customerLevel" placeholder="请选择客户评级" clearable>
          <el-option v-for="dict in dict.type.customer_level" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="客户标签" prop="customerTags">
        <el-select v-model="queryCustomerParams.customerTags" placeholder="请选择客户标签" clearable>
          <el-option v-for="dict in dict.type.customer_tag" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:customer:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:customer:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:customer:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:customer:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户编号" align="center" prop="id" />
      <el-table-column label="客户姓名" align="center" prop="name" />
      <el-table-column label="客户电话" align="center" prop="tel" />
      <el-table-column label="客户邮件" align="center" prop="email" />
      <el-table-column label="客户地址" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="showAddress(scope.row)">显示地址</el-button>
        </template>
      </el-table-column>
      <el-table-column label="客户评级" align="center" prop="customerLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_level" :value="scope.row.customerLevel" />
        </template>
      </el-table-column>
      <el-table-column label="客户标签" align="center" prop="customerTags">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_tag" :value="scope.row.customerTags" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:customer:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:customer:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryCustomerParams.pageNum"
      :limit.sync="queryCustomerParams.pageSize" @pagination="getList" />

    <!-- 添加或修改客户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open.main" width="500px" @open="onCustomerChange" append-to-body>
      <el-form ref="form" :model="form" :rules="mainRules" label-width="80px">
        <el-form-item label="客户姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入客户电话" />
        </el-form-item>
        <el-form-item label="客户邮件" prop="email">
          <el-input v-model="form.email" placeholder="请输入客户邮件" />
        </el-form-item>
        <el-form-item label="客户评级">
          <el-radio-group v-model="form.customerLevel">
            <el-radio v-for="dict in dict.type.customer_level" :key="dict.label" :label="dict.label">{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="客户标签">
          <el-radio-group v-model="form.customerTags">
            <el-radio v-for="dict in dict.type.customer_tag" :key="dict.value" :label="dict.value">{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">客户地址信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCustomerAddress">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCustomerAddress">删除
            </el-button>
          </el-col>
        </el-row>
        <el-table :data="customerAddressList" :row-class-name="rowCustomerAddressIndex"
          @selection-change="handleCustomerAddressSelectionChange" ref="customerAddress" stripe>
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="地址" align="center" prop="customerAddress" width="350" />
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog v-bind="$attr" v-on="$listeners" @open="onAddressAddOpen" @close="handleAddressAddCancel" title="添加客户地址"
      :visible.sync="open.address" width="500px" append-to-body :show-close="false">
      <el-form ref="elForm" :model="addressForm" :rules="addressRules" size="medium" label-width="100px">
        <el-form-item label="客户地址" prop="address">
          <el-input v-model="addressForm.address" type="textarea" placeholder="请输入客户地址" :maxlength="255" show-word-limit
            :autosize="{ minRows: 4, maxRows: 4 }" :style="{ width: '100%' }"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="handleAddressAddCancel">取消</el-button>
        <el-button type="primary" @click="handleAddressAddConfirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog v-bind="$attr" v-on="$listeners" title="显示客户地址"
      :visible.sync="open.showAddress" width="500px" append-to-body :show-close="false">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCustomerAddress">添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCustomerAddress">删除
          </el-button>
        </el-col>
      </el-row>
      <el-table :data="customerAddressList" :row-class-name="rowCustomerAddressIndex"
        @selection-change="handleCustomerAddressSelectionChange" ref="customerAddress" stripe>
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column label="序号" align="center" prop="index" width="50" />
        <el-table-column label="地址" align="center" prop="customerAddress" width="350" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAddress">确 定</el-button>
        <el-button @click="cancelAddress">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="js">
import {
  listCustomer,
  listCustomerAddress,
  getCustomer,
  getCustomerAddress,
  addCustomer,
  addCustomerAddress,
  delCustomer,
  delCustomerList,
  delCustomerAddress,
  updateCustomer,
  updateCustomerAddress
} from "@/api/system/customer";

const levels = ['nice', 'common', 'humble'];

export default {
  name: "Customer",
  dicts: ['customer_tag', 'customer_level'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCustomerAddress: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户管理表格数据
      customerList: [],
      // 客户地址表格数据
      customerAddressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: {
        main: false,
        address: false,
        showAddress: false
      },
      parentDialog: 'main',
      // 查询参数
      queryCustomerParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        tel: null,
        email: null,
        customerLevel: null,
        customerTags: null
      },
      queryAddressParams: {
        pageNum: 1,
        pageSize: 3,
        customer_id: null,
        customer_address: null
      },
      // 表单参数
      form: {},
      // 表单校验
      mainRules: {
        name: [{
          required: true,
          message: "客户姓名不能为空",
          trigger: "blur"
        }],
        customerLevel: [{
          required: true,
          message: "客户评级不能为空",
          trigger: "blur"
        }],
        // customerAddressList: [{
        //   required: true,
        //   message: "客户地址不能为空",
        //   tigger: "blur"
        // }]
      },
      addressForm: {
        address: undefined,
      },
      addressRules: {
        address: [{
          required: true,
          message: '请输入客户地址',
          trigger: 'blur'
        }],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户管理列表 */
    getList() {
      this.loading = true;
      listCustomer(this.queryCustomerParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 显示地址按钮
    showAddress(row) {
      this.loading = true;
      this.open.showAddress = true;
      this.parentDialog = 'address';
      let id = row.id;
      getCustomerAddress(id).then(response => {
        console.log(response);
        this.customerAddressList = response.data;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open.main = false;
      this.reset();
    },
    cancelAddress() {
      this.open.showAddress = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        tel: null,
        email: null,
        customerLevel: "nice",
        customerTags: "0"
      };
      this.customerAddressList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryCustomerParams.pageNum = 1;
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.parentDialog = 'main';
      this.open.main = true;
      this.title = "添加客户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.parentDialog = 'main';
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        console.log(response);
        this.form = response.data;
        if (response.data.customerLevel >= 0.7) {
          this.form.customerLevel = 'nice';
        } else if (response.data.customerLevel >= 0.5) {
          this.form.customerLevel = 'common';
        } else {
          this.form.customerLevel = 'humble';
        }
        this.open.main = true;
        this.title = "修改客户管理";
      });
      getCustomerAddress(id).then(response => {
        console.log(response);
        this.customerAddressList = response.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (typeof this.form.customerLevel != 'string') {
            this.form.customerLevel = this.form.customerLevel;
            console.log(typeof this.form.customerLevel);
          } else if (this.form.customerLevel === levels[0]) {
            this.form.customerLevel = 0.8;
          } else if (this.form.customerLevel === levels[1]) {
            this.form.customerLevel = 0.5;
          } else if (this.form.customerLevel === levels[2]) {
            this.form.customerLevel = 0.3;
            console.log(this.form);
          }
          this.form.customerAddressList = this.customerAddressList;
          console.log(this.form);
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open.main = false;
              this.getList();
            });
          } else {
            addCustomer(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open.main = false;
              this.getList();
            });
          }
        }
      });
    },
    submitAddress() {
      console.log(this.customerAddressList);
      for (var i = 0; i < this.customerAddressList.length; ++i) {
        console.log(this.customerAddressList[i]);
        this.customerAddressList[i].customerId = this.customerAddressList[0].customerId;
      }
      updateCustomerAddress(this.customerAddressList).then(response => {
        console.log(response);
        this.open.showAddress = false;
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除客户管理编号为"' + ids + '"的数据项？').then(function () {
        return delCustomer(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    onAddressAddOpen() {
      if (this.parentDialog === 'main') {
        this.open.main = false;
      } else if (this.parentDialog === 'address') {
        this.open.showAddress = false;
      }
    },
    onAddressAddClose(parentShow) {
      this.open.address = false;
      if (this.parentDialog === 'main') {
        this.open.main = true;
      } else if (this.parentDialog === 'address') {
        this.open.showAddress = true;
      }
    },
    handleAddressAddCancel() {
      this.onAddressAddClose();
      this.$refs['elForm'].resetFields();
    },
    handleAddressAddConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          console.log(valid);
          console.log(this.addressForm);
          let obj = {
            customerId: null,
            customerAddress: this.addressForm.address,
          };
          this.customerAddressList.push(obj);
          console.log(this.customerAddressList);
          this.onAddressAddClose();
          this.$refs['elForm'].resetFields();
          return;
        };
        console.log(valid);
        return;
      })
    },
    /** 客户地址序号 */
    rowCustomerAddressIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 客户地址添加按钮操作 */
    handleAddCustomerAddress() {
      this.open.address = true;
    },
    /** 客户地址删除按钮操作 */
    handleDeleteCustomerAddress() {
      if (this.checkedCustomerAddress.length == 0) {
        this.$modal.msgError("请先选择要删除的客户地址数据");
      } else {
        const customerAddressList = this.customerAddressList;
        const checkedCustomerAddress = this.checkedCustomerAddress;
        this.customerAddressList = customerAddressList.filter(function (item) {
          return checkedCustomerAddress.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCustomerAddressSelectionChange(selection) {
      this.checkedCustomerAddress = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/customer/export', {
        ...this.queryCustomerParams
      }, `customer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
