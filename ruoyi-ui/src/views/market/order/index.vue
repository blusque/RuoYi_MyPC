<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单日期" prop="date">
        <el-date-picker clearable v-model="queryParams.date" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择订单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截至日期" prop="ddl">
        <el-date-picker clearable v-model="queryParams.ddl" type="date" value-format="yyyy-MM-dd" placeholder="请选择截至日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option v-for="dict in dict.type.market_order_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="送达地址" prop="address">
        <el-input v-model="queryParams.address" placeholder="请输入送达地址" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户编号" prop="customerId">
        <el-input v-model="queryParams.customerId" placeholder="请输入客户编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户姓名" prop="customerName">
        <el-input v-model="queryParams.customerName" placeholder="请输入客户姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="销售员编号" prop="staffId" label-width="100px">
        <el-input v-model="queryParams.staffId" placeholder="请输入销售员编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="销售员姓名" prop="staffName" label-width="100px">
        <el-input v-model="queryParams.staffName" placeholder="请输入销售员姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="产品编号" prop="materialId">
        <el-input v-model="queryParams.materialId" placeholder="请输入产品编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="产品名称" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入产品名称" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:market_order:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:market_order:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:market_order:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:market_order:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="id" />
      <el-table-column label="订单日期" align="center" prop="date" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止日期" align="center" prop="ddl" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ddl, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.market_order_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="客户编号" align="center" prop="customerId" />
      <el-table-column label="送货地址" align="center" prop="address" />
      <el-table-column label="销售员编号" align="center" prop="staffId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:market_order:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:market_order:remove']">删除</el-button>
          <br />
          <el-button size="mini" type="text" icon="el-icon-more" @click="handleDetail(scope.row)"
            v-hasPermi="['system:market_order:remove']">明细</el-button>
          <el-button size="mini" type="text" icon="el-icon-service" @click="handleCancel(scope.row)"
            v-hasPermi="['system:market_order:remove']">退货</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改销售订单对话框 -->
    <el-dialog :title="title" :visible.sync="open.main" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="截至日期" prop="ddl">
          <el-date-picker clearable v-model="form.ddl" type="date" value-format="yyyy-MM-dd" placeholder="请选择订单日期">
          </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="订单状态">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.market_order_status" :key="dict.value" :label="dict.value">{{ dict.label
            }}
            </el-radio>
          </el-radio-group>
        </el-form-item> -->
        <el-form-item v-if="isAdd" label="客户编号" prop="customerId">
          <!-- <el-input v-model="form.customerId" placeholder="请输入客户编号" /> -->
          <el-autocomplete v-model="form.customerId" :fetch-suggestions="querySearchId" placeholder="Please input"
            @select="handleSelect" />
        </el-form-item>
        <el-form-item label="送货地址" prop="address">
          <!-- <el-input v-model="form.address" type="textarea" placeholder="请输入内容" /> -->
          <el-autocomplete v-model="form.address" :fetch-suggestions="querySearchAddress" placeholder="Please input"
            @select="handleSelect" />
        </el-form-item>
        <el-form-item v-if="isAdd" label="销售员编号" prop="staffId" label-width="100px">
          <!-- <el-input v-model="form.staffId" placeholder="请输入销售员编号" /> -->
          <el-autocomplete v-model="form.staffId" :fetch-suggestions="querySearchStaffId" placeholder="Please input"
            @select="handleSelect" />
        </el-form-item>
        <br v-if="isAdd" />
        <el-divider v-if="isAdd" content-position="center">订单明细</el-divider>
        <div v-if="isAdd">
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddOrderDetail">添加</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteOrderDetail">删除
              </el-button>
            </el-col>
          </el-row>
          <el-table :data="marketOrderDetailList" :row-class-name="rowOrderDetailIndex"
            @selection-change="handleOrderDetailSelectionChange" ref="orderDetail" stripe>
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column label="序号" align="center" prop="index" width="50" />
            <el-table-column label="物料编号" align="center" prop="materialId" width="200" />
            <el-table-column label="数量" align="center" prop="number" width="150" />
          </el-table>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="open.addOrderDetail" v-bind="$attrs" v-on="$listeners" @open="onAddOrderDetailOpen"
      @close="onAddOrderDetailClose" :title="title" :show-close="false">
      <el-form ref="orderAddForm" :model="orderDetailData" :rules="orderDetailRules" size="medium" label-width="100px">
        <el-form-item label="物料编号" prop="materialId">
          <el-input v-model="orderDetailData.materialId" placeholder="请输入物料编号" clearable :style="{ width: '100%' }">
          </el-input>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input-number v-model="orderDetailData.number" placeholder="数量" clearable></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="handleAddOrderDetailCancel">取消</el-button>
        <el-button type="primary" @click="handleAddOrderDetailConfirm">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="open.orderDetail" v-bind="$attrs" v-on="$listeners" @open="onOrderDetailOpen"
      @close="onOrderDetailClose" :title="title">
      <h3 :style="{ textAlign: 'center' }">订单明细表</h3>
      <el-table :data="marketOrderDetailList" :row-class-name="rowOrderDetailIndex" ref="orderDetail" stripe>
        <el-table-column label="序号" align="center" prop="index" width="150" />
        <el-table-column label="物料编号" align="center" prop="materialId" width="250" />
        <el-table-column label="数量" align="center" prop="number" width="200" />
      </el-table>
    </el-dialog>

    <el-dialog :visible.sync="open.cancelDetail" v-bind="$attrs" v-on="$listeners" @open="onCancelDetailOpen"
      @close="onCancelDetailClose" :title="title">
      <h3 :style="{ textAlign: 'center' }">退货明细表</h3>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCancelDetail">添加</el-button>
        </el-col>
      </el-row>
      <el-table :data="marketCancelDetailList" :row-class-name="rowCancelDetailIndex" ref="orderDetail" stripe>
        <el-table-column label="序号" align="center" prop="index" width="50" />
        <el-table-column label="日期" align="center" prop="date" width="150" />
        <el-table-column label="物料编号" align="center" prop="materialId" width="250" />
        <el-table-column label="数量" align="center" prop="number" width="150" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCancelForm">确 定</el-button>
        <el-button @click="cancelCancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="open.addCancelDetail" v-bind="$attrs" v-on="$listeners" @open="onAddCancelDetailOpen"
      @close="onAddCancelDetailClose" :title="title" :show-close="false">
      <el-form ref="cancelAddForm" :model="cancelDetailData" :rules="cancelDetailRules" size="medium"
        label-width="100px">
        <el-form-item label="物料编号" prop="materialId">
          <el-input v-model="cancelDetailData.materialId" placeholder="请输入物料编号" clearable :style="{ width: '100%' }">
          </el-input>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input-number v-model="cancelDetailData.number" placeholder="数量" clearable></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="handleAddCancelDetailCancel">取消</el-button>
        <el-button type="primary" @click="handleAddCancelDetailConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrder,
  listOrderDetail,
  listCancelDetail,
  getOrder,
  getOrderList,
  delOrder,
  delOrderDetail,
  delCancelDetail,
  addOrder,
  addOrderDetail,
  addCancelDetail,
  updateOrder,
  updateOrderDetail,
  updateCancelDetail
} from "@/api/system/order";

import {
  listCustomerIdByNum,
  listCustomerAddressByStr
} from "@/api/system/customer"

import {
  listUser
} from "@/api/system/user"

export default {
  name: "Order",
  dicts: ['market_order_status'],
  data() {
    const cancelNumCheck = (rule, value, cb) => {
      if (!value) {
        cb(new Error('请输入产品数量'));
        return;
      }
      if (Number(value) > Number(this.tempMarketOrder.number)) {
        console.log(`最大数目为${this.tempMarketOrder.number}`);
        cb(new Error(`最大数目为${this.tempMarketOrder.number}`));
      } else {
        cb();
      }
    }
    const cancelIdCheck = (rule, value, cb) => {
      if (!value) {
        cb(new Error('请输入产品编号'));
        return;
      }
      let obj = {
        orderId: this.nowId,
        materialId: this.cancelDetailData.materialId
      };
      console.log(obj);
      listOrderDetail(obj).then(response => {
        console.log(response);
        if (response.rows.length == 0) {
          cb(new Error("请输入此订单未涉及该产品"))
        } else {
          this.tempMarketOrder = response.rows[0];
          cb();
        }
      })
    }
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 选中客户
      customerId: [],
      // 检查时订单列表
      tempMarketOrder: [],
      // // 客户编号列表
      // customerIds: [],
      // // 销售员编号列表
      // staffIds: [],
      // // 物料编号列表
      // materialIds: [],
      // TODO： 完成后端查询
      // 当前编号
      nowId: null,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 销售订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: {
        main: false,
        orderDetail: false,
        cancelDetail: false,
        addOrderDetail: false,
        addCancelDetail: false
      },
      isValid: {
        customerId: true,
        materialId: true,
        staffId: true
      },
      // 父级窗口
      parentDialog: 'main',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        date: null,
        ddl: null,
        status: null,
        customerId: null,
        customerName: null,
        address: null,
        staffId: null,
        staffName: null,
        materialId: null,
        materialName: null
      },
      // 是否是增加
      isAdd: true,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ddl: [
          { required: true, message: "截至日期不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "订单状态不能为空", trigger: "blur" }
        ],
        customerId: [
          { required: true, message: "客户编号不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "送货地址不能为空", trigger: "blur" }
        ],
        staffId: [
          { required: true, message: "销售员编号不能为空", trigger: "blur" }
        ]
      },
      // 订单明细列表
      marketOrderDetailList: [],
      // 选中的订单明细
      checkedOrderDetial: [],
      // 订单明细数据
      orderDetailData: {
        orderId: undefined,
        materialId: undefined,
        number: undefined,
      },
      // 订单明细表单校验
      orderDetailRules: {
        materialId: [{
          required: true,
          message: '请输入物料编号',
          trigger: 'blur'
        }],
        number: [{
          required: true,
          message: '数量',
          trigger: 'blur'
        }],
      },
      // 退货明细列表
      marketCancelDetailList: [],

      cancelDetailData: {
        orderId: undefined,
        date: undefined,
        materialId: undefined,
        number: undefined
      },
      cancelDetailRules: {
        materialId: [{
          required: true,
          trigger: 'blur',
          validator: cancelIdCheck
        }],
        number: [{
          required: true,
          trigger: 'blur',
          validator: cancelNumCheck
        }],
      },
      customerInfo: {
        ids: [],
        addressInfo: []
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    openMain() {
      this.open.main = true;
      this.parentDialog = 'main';
    },
    openOrderDetail() {
      this.open.orderDetail = true;
      this.parentDialog = 'orderDetail';
    },
    openCancelDetail() {
      this.open.cancelDetail = true;
      this.parentDialog = 'cancelDetail';
    },
    openParent() {
      if (this.parentDialog === 'main') {
        this.open.main = true;
      } else if (this.parentDialog === 'orderDetail') {
        this.open.orderDetail = true;
      } else if (this.parentDialog === 'cancelDetail') {
        this.open.cancelDetail = true;
      }
    },
    closeParent() {
      if (this.parentDialog === 'main') {
        this.open.main = false;
      } else if (this.parentDialog === 'orderDetail') {
        this.open.orderDetail = false;
      } else if (this.parentDialog === 'cancelDetail') {
        this.open.cancelDetail = false;
      }
    },
    /** 查询销售订单列表 */
    getList() {
      this.loading = true;
      let marketOrderDetailList = [];
      let marketCancelDetailList = [];
      if (this.queryParams.materialId != null) {
        let obj = {
          materialId: this.queryParams.materialId
        };
        marketOrderDetailList.push(obj);
      }
      let queryParams = {
        date: this.queryParams.date,
        ddl: this.queryParams.ddl,
        status: this.queryParams.status,
        customerId: this.queryParams.customerId,
        address: this.queryParams.address,
        staffId: this.queryParams.staffId,
        marketOrderDetailList: marketOrderDetailList,
        marketCancelDetailList: marketCancelDetailList
      };
      console.log(queryParams);
      listOrder(queryParams).then(response => {
        console.log(response.data);
        this.orderList = response.data;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open.main = false;
      this.reset();
    },
    cancelCancel() {
      // TODO: cancel cancelDetail insert
      this.open.cancelDetail = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        date: null,
        status: "0",
        customerId: null,
        address: null,
        staffId: null
      };
      this.marketOrderDetailList = [];
      this.marketCancelDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    querySearchId(queryString, cb) {
      let query = {
        id: queryString ? Number(queryString) : null
      };
      console.log(query);
      let results = [];
      listCustomerIdByNum(query).then(response => {
        console.log(response);
        response.rows.forEach(item => {
          results.push({ value: String(item) });
        });
        if (results.length == 0) {
          this.isValid.customerId = false;
        }
        console.log(results);
        // TODO: return result;
        cb(results);
      });
    },
    querySearchAddress(queryString, cb) {
      let query = {
        customerId: this.form.customerId,
        customerAddress: this.form.address
      };
      let results = [];
      listCustomerAddressByStr(query).then(response => {
        response.rows.forEach(item => {
          results.push({ value: item });
        });
        console.log(results);
        // TODO: return result;
        cb(results);
      });
    },
    querySearchStaffId(queryString, cb) {
      let query = {
        deptId: 103
      }
      let results = [];
      listUser(query).then(response => {
        response.rows.forEach(item => {
          results.push({ value: String(item.userId) });
        });
      });
      results = queryString ? results.filter(this.createFilter(queryString)) : results;
      cb(results);
    },
    createFilter(queryString) {
      return (item) => {
        return item.value.match(queryString);
      };
    },
    handleSelect() {

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
      this.isAdd = true;
      this.openMain();
      this.title = "添加销售订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isAdd = false;
      const ids = row.id || this.ids
      console.log(ids);
      getOrder(ids).then(response => {
        this.form = response.data;
        this.openMain();
        this.title = "修改销售订单";
      });
    },
    getFormatedDate(date) {
      console.log(date);
      var y = date.getFullYear();
      var mon = date.getMonth() + 1;
      console.log(mon);
      mon = mon < 10 ? ('0' + mon) : mon;
      var d = date.getDate();
      d = d < 10 ? ('0' + d) : d;
      var h = date.getHours();
      h = h < 10 ? '0' + h : h;
      var m = date.getMinutes();
      m = m < 10 ? '0' + m : m;
      var s = date.getSeconds();
      s = s < 10 ? '0' + s : s;
      return y + '-' + mon + '-' + d + ' ' + h + ':' + m + ':' + s;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open.main = false;
              this.getList();
            });
          } else {
            this.form.date = this.getFormatedDate(new Date());
            console.log(this.form.date);
            this.form.status = 1;
            this.form.marketOrderDetailList = this.marketOrderDetailList;
            addOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open.main = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除销售订单编号为"' + ids + '"的数据项？').then(function () {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    handleDetail(row) {
      this.loading = true;
      let queryParams = {
        orderId: row.id
      };
      console.log(queryParams);
      listOrderDetail(queryParams).then(response => {
        this.marketOrderDetailList = response.rows;
        console.log(this.marketOrderDetailList);
        this.total = response.total;
        this.loading = false;
      });
      this.title = "查看订单明细";
      this.openOrderDetail();
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },
    rowOrderDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    handleOrderDetailSelectionChange(selection) {
      this.checkedOrderDetail = selection.map(item => item.index)
    },
    handleDeleteOrderDetail() {
      if (this.checkedOrderDetial.length == 0) {
        this.$modal.msgError("请先选择要删除的客户地址数据");
      } else {
        const marketOrderDetailList = this.marketOrderDetailList;
        const checkedOrderDetail = this.checkedOrderDetail;
        this.marketOrderDetailList = marketOrderDetailList.filter(function (item) {
          return checkedOrderDetail.indexOf(item.index) == -1
        });
      }
    },

    onAddCancelDetailOpen() {
      this.closeParent();
    },
    onAddCancelDetailClose() {
      this.openParent();
      this.$refs['cancelAddForm'].resetFields();
    },
    onOrderDetailOpen() {

    },
    onOrderDetailClose() {
      this.open.orderDetail = false;
    },
    handleAddOrderDetail() {
      console.log(this.open.addOrderDetail);
      this.open.addOrderDetail = true;
    },
    onAddOrderDetailOpen() {
      this.closeParent();
    },
    onAddOrderDetailClose() {
      this.openParent();
      this.$refs['orderAddForm'].resetFields();
    },
    handleAddOrderDetailCancel() {
      this.onAddOrderDetailClose();
      console.log(this.open.addOrderDetail);
      this.open.addOrderDetail = false;
    },
    handleAddOrderDetailConfirm() {
      this.$refs['orderAddForm'].validate(valid => {
        if (!valid) return
        let obj = {
          materialId: this.orderDetailData.materialId,
          number: this.orderDetailData.number
        };
        this.marketOrderDetailList.push(obj);
        console.log(this.marketOrderDetailList);
        this.onOrderDetailClose();
        this.open.addOrderDetail = false;
      });
    },

    rowCancelDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    handleCancel(row) {
      this.loading = true;
      this.nowId = row.id;
      let queryParams = {
        orderId: row.id
      };
      console.log(queryParams);
      listCancelDetail(queryParams).then(response => {
        this.marketCancelDetailList = response.rows;
        console.log(this.marketCancelDetailList);
        this.total = response.total;
        this.loading = false;
      });
      this.title = "查看退货明细";
      this.openCancelDetail();
    },
    onCancelDetailOpen() {

    },
    onCancelDetailClose() {
      this.open.cancelDetail = false;
    },
    handleAddCancelDetailCancel() {
      this.onAddCancelDetailClose();
      console.log(this.open.addCancelDetail);
      this.open.addCancelDetail = false;
    },
    handleAddCancelDetailConfirm() {
      this.$refs['cancelAddForm'].validate(valid => {
        if (!valid) return
        let obj = {
          orderId: this.nowId,
          date: this.getFormatedDate(new Date()),
          materialId: this.cancelDetailData.materialId,
          number: this.cancelDetailData.number
        };
        this.marketCancelDetailList.push(obj);
        console.log(this.marketCancelDetailList);
        this.onCancelDetailClose();
        this.open.addCancelDetail = false;
      });
    },
    handleAddCancelDetail() {
      // TODO: add cancelDetail
      console.log(this.open.addCancelDetail)
      this.open.addCancelDetail = true;
    },
    submitCancelForm() {
      // TODO: submit cancelDetail submit
      let obj = {
        id: this.nowId
      }
      listOrder(obj).then(response => {
        obj = response.data[0];
        if (obj.status === '0') {
          obj.status = '3';
        } else {
          obj.status = '4';
        }
        console.log(obj);
        updateOrder(obj).then(response => {
          console.log(response);
        })
      })
      updateCancelDetail(this.marketCancelDetailList).then(response => {
        this.$modal.msgSuccess("新增成功");
        this.open.cancelDetail = false;
        this.getList();
      })
    },

    getCustomerInfo() {
      let customerList = [];
      listCustomer()
    }
  }
};
</script>
