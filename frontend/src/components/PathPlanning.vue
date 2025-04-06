<script setup>
import { reactive, computed, ref } from "vue";
import emitter from "@/utils/emitter.ts";
import { useMapStore } from "@/store/map.js";
import { storeToRefs } from "pinia";

const mapStore = useMapStore();
const { srcId, destIdList } = storeToRefs(mapStore.state);

const vertices = computed(() => mapStore.vertexList);
console.log("vertices:", vertices);
const shortestPaths = ref([]);


const formInline = reactive({
  srcId: srcId !== undefined ? srcId : 0,
  destIdList: destIdList,
  strategy: "",
});

const vertexList = computed(() => {
  return mapStore.vertexList.filter((vertex) => vertex.type === 1);
});

const onSubmit = () => {
  // 验证是否都已选择或填写
  if (
    formInline.srcId === null || // 检查源ID是否已选
    !formInline.destIdList.length || // 确保目的地ID列表非空
    !formInline.strategy // 确认策略已选定
  ) {
    alert("Please select source, destination, and strategy");
  } else {
    // 所有条件满足，发送查询
    emitter.emit("path-planning", formInline);
    // shortestPaths.value = vertices.value.map((formInline.destIdList) => vertex.label);

  }
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
    <el-form-item label="Destinations">
      <el-select
        v-model="formInline.destIdList"
        multiple
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
    <el-form-item label="Strategy">
      <el-select v-model="formInline.strategy" placeholder="Select" clearable>
        <el-option label="Shortest distance" value="0" />
        <el-option label="Shortest time" value="1" />
        <el-option label="Shortest time(Available for cycling)" value="2" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Query</el-button>
    </el-form-item>
  </el-form>
  <div v-if="shortestPaths.length > 0">
    <p>最短路径为：</p>
    <ul>
      <li v-for="path in shortestPaths">
        {{ path }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
