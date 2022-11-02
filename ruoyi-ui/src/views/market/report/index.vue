<template>
  <div class="app-container">
    <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="Month" name="first">
        <pie-chart v-if="activeName === 'first'" :height="height" :width="width" 
          :tooltips="tooltip" :series="series" :legend="legend" ref="pieChart1" />
      </el-tab-pane>
      <el-tab-pane label="Year" name="second">
        <pie-chart v-if="activeName === 'second'" height="500px" :height="height" :width="width" 
          :tooltips="tooltip" :series="series" :legend="legend" ref="pieChart2" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
  
<script>
import { ref } from 'vue'
import PieChart from '@/views/dashboard/PieChart.vue'
import {
  listOrderFromTo,
  searchOrderByMaterialName
} from '@/api/market/order.js'

export default {
  components: {
    PieChart
  },
  data() {
    return {
      activeName: "first",
      className: 'chart',
      width: '100%',
      height: '500px',
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        left: 'center',
        bottom: '10',
        data: ['Macbook Air M1', 'Macbook Pro M1, 14', 'Macbook Air M2', 'Macbook Pro M1, 16', 'Macbook Pro M2, 13']
      },
      series: [
        {
          name: 'MONTHLY SALE',
          type: 'pie',
          roseType: 'radius',
          radius: [20, 135],
          center: ['50%', '38%'],
          data: [
            { value: 320, name: 'Macbook Air M1' },
            { value: 240, name: 'Macbook Pro M1, 14' },
            { value: 149, name: 'Macbook Air M2' },
            { value: 100, name: 'Macbook Pro M1, 16' },
            { value: 59, name: 'Macbook Pro M2, 13' }
          ],
          animationEasing: 'cubicInOut',
          animationDuration: 2600
        }
      ]
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(this.$refs.pieChart1);
    }
  },
}
</script>

<style>
.demo-tabs>.el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>
  