<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    tooltip: {
      type: Object,
      default: () => {
        return {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        };
      }
    },
    legend: {
      type: Object,
      default: () => {
        return {
          left: 'center',
          bottom: '10',
          data: ['Industries', 'Technology', 'Forex', 'Gold', 'Forecasts']
        };
      }
    },
    series: {
      type: Array,
      default: () => {
        return [
          {
            name: 'MONTHLY SALE',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: [
              { value: 320, name: 'Industries' },
              { value: 240, name: 'Technology' },
              { value: 149, name: 'Forex' },
              { value: 100, name: 'Gold' },
              { value: 59, name: 'Forecasts' }
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      }
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: this.tooltip,
        legend: this.legend,
        series: this.series
      })
    },
    destroyChart() {
      if (!this.chart) {
        return
      }
      console.log("Destroying Chart!");
      this.chart.dispose()
      this.chart = null
    }
  }
}
</script>
