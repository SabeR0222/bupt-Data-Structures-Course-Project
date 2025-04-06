<template>
  <el-tabs v-model="activeName" class="demo-tabs">
    <el-tab-pane label="Path planning" name="first">
      <PathPlanning />
    </el-tab-pane>
    <el-tab-pane label="Search spot" name="second">
      <SearchSpot />
    </el-tab-pane>
  </el-tabs>
  <el-divider />
  <Map />
  <el-divider />
  <WriteArticle :locationId="locationId"/>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import Map from "@/components/Map.vue";
import PathPlanning from "@/components/PathPlanning.vue";
import SearchSpot from "@/components/SearchSpot.vue";
import WriteArticle from "@/components/WriteArticle.vue";
import { useRoute } from 'vue-router';

const route = useRoute();
const locationId = route.params.locationId; // 获取路径参数
const activeName = ref("first");
</script>

<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>
