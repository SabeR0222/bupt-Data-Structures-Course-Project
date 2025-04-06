<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="Name">
      <el-input v-model="formInline.name" placeholder="name" clearable />
    </el-form-item>
    <el-form-item label="Type">
      <el-select v-model="formInline.type" placeholder="type" clearable>
        <el-option label="景区" value="1" />
        <el-option label="学校" value="0" />
      </el-select>
    </el-form-item>
    <el-form-item label="Keyword">
      <el-input v-model="formInline.keyword" placeholder="keyword" clearable />
    </el-form-item>
    <el-form-item label="Sort order">
      <el-select
        v-model="formInline.sortOrder"
        placeholder="sort order"
        clearable
      >
        <el-option label="popularity" value="1" />
        <el-option label="evaluation" value="0" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Query</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="locationData" stripe style="width: 100%">
    <el-table-column type="index" />
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column prop="type" label="Type" width="180">
      <template #default="scope">
        {{ getTypeText(scope.row.type) }}
      </template>
    </el-table-column>
    <el-table-column prop="keyword" label="Keyword" width="180" />
    <el-table-column prop="popularity" label="Popularity" width="180" />
    <el-table-column prop="evaluation" label="Evaluation" width="180" />
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button
          @click="onSelectLocation(scope.row.locationId)"
          :round="true"
        >
          Select
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();

const formInline = reactive({
  name: "",
  type: "",
  keyword: "",
  sortOrder: "",
});

const locationData = ref([]);

const onSubmit = () => {
  for (const key in formInline) {
    if (formInline[key] === "") {
      formInline[key] = null;
    }
  }
  axios
    .get("/api/location/query", {
      params: {
        name: formInline.name,
        type: formInline.type,
        keyword: formInline.keyword,
        sortOrder: formInline.sortOrder,
      },
    })
    .then((res) => {
      console.log(res.data.data);
      locationData.value = res.data.data;
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
};

onMounted(() => {
  for (const key in formInline) {
    if (formInline[key] === "") {
      formInline[key] = null;
    }
  }
  axios
    .get(`/api/location/recommend/${localStorage.getItem("vuems_userId")}`, formInline)
    .then((res) => {
      locationData.value = res.data.data;
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
});

async function onSelectLocation(locationId) {
  try {
    // 发送POST请求
    await axios.post("/api/locationBrowse/setCount", {
      locationId,
      userId: localStorage.getItem("vuems_userId"),
    });

    // 如果POST请求成功，再进行页面跳转
    router.push({
      path: `/location/${locationId}`,
    });
  } catch (error) {
    console.error("Error sending POST request:", error);
    // 处理错误，例如提示用户
  }
}

const getTypeText = (type) => {
  return type === 0 ? "学校" : "景区";
};
</script>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 180px;
}

.demo-form-inline .el-select {
  --el-select-width: 180px;
}
</style>
