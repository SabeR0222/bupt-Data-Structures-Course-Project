<script setup>
import { reactive, computed } from "vue";
import emitter from "@/utils/emitter.ts";
import { useMapStore } from "@/store/map.js";
import { storeToRefs } from "pinia";
import { ref, onUnmounted } from "vue";

let tableData = ref([]);

emitter.on("search-spot-result", (value) => {
  tableData.value = value;
  // 将type映射为文字
  const typeMapping = {
    2: "食堂",
    3: "自习室",
    4: "体育场馆",
    5: "快递站",
    6: "外卖柜",
    7: "洗衣房",
    8: "商店",
    9: "学生活动中心",
    10: "实验室",
    11: "其他",
  };

  const processedData = tableData.value.map((item) => ({
    ...item,
    type: typeMapping[item.type], // 使用映射表转换type
  }));

  // 现在，processedData中的每个对象的type已经是文字了，可以直接用于表格展示
  tableData.value = processedData;
});

onUnmounted(() => {
  emitter.off("search-spot-result", () => {});
});

const mapStore = useMapStore();
const { srcId } = storeToRefs(mapStore.state);

const formInline = reactive({
  srcId: srcId !== undefined ? srcId : 0,
  type: "",
  x: 0,
  y: 0,
  distance: 300,
});

const vertexList = computed(() => {
  return mapStore.vertexList.filter((vertex) => vertex.type === 1);
});

const onSubmit = () => {
  // 所有条件满足，发送查询
  formInline.x = mapStore.vertexList[formInline.srcId].x;
  formInline.y = mapStore.vertexList[formInline.srcId].y;
  emitter.emit("search-spot", formInline);
};
</script>

<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="Source">
      <el-select
        v-model="formInline.srcId"
        placeholder="Select"
        style="width: 240px"
      >
        <el-option
          v-for="item in vertexList"
          :key="item.vertexId"
          :label="item.label"
          :value="item.vertexId"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="Type">
      <el-select v-model="formInline.type" placeholder="Select" clearable>
        <el-option label="食堂" value="2" />
        <el-option label="自习室" value="3" />
        <el-option label="体育场馆" value="4" />
        <el-option label="快递站" value="5" />
        <el-option label="外卖柜" value="6" />
        <el-option label="洗衣房" value="7" />
        <el-option label="商店" value="8" />
        <el-option label="学生活动中心" value="9" />
        <el-option label="实验室" value="10" />
        <el-option label="其他" value="11" />
      </el-select>
    </el-form-item>
    <el-form-item label="Distance">
      <el-input-number v-model="formInline.distance" :min="1" :max="1000" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Query</el-button>
    </el-form-item>
  </el-form>
  <el-table
    :data="tableData"
    :default-sort="{ prop: 'distance', order: 'ascending' }"
    stripe
    style="width: 100%"
  >
    <el-table-column type="index" />
    <el-table-column prop="label" label="Name" width="180" />
    <el-table-column prop="distance" label="Distance" width="180" />
    <el-table-column prop="type" label="Type" width="180" />
  </el-table>
</template>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
