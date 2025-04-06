<script setup>
import { reactive, ref, onMounted } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const locationForm = reactive({
  location: "",
});

const titleForm = reactive({
  title: "",
});

const articleData = ref([]);

const submitLocation = () => {
  axios
    .get("/api/article/getArticleByLocation", {
      params: {
        location: locationForm.location,
      },
    })
    .then((res) => {
      articleData.value = res.data.data;

      articleData.value.forEach((item) => {
        item.evaluation = Number(item.evaluation.toFixed(1));
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
};

const submitTitle = () => {
  axios
    .get("/api/article/getArticleByTitle", {
      params: { title: titleForm.title },
    })
    .then((res) => {
      articleData.value = res.data.data;

      articleData.value.forEach((item) => {
        item.evaluation = Number(item.evaluation.toFixed(1));
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
};

onMounted(() => {
  axios
    .get(
      `/api/article/getRecommendArticle/${localStorage.getItem("vuems_userId")}`
    )
    .then((res) => {
      articleData.value = res.data.data;
      articleData.value.forEach((item) => {
        item.evaluation = Number(item.evaluation.toFixed(1));
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
});

const handleDelete = (row) => {
  ElMessageBox.confirm(`正在删除文章：${row.title}，确认删除？`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    // 发送delete请求
    axios
      .post(`/api/article/delete/${row.articleId}/${localStorage.getItem("vuems_userId")}`)
      .then((res) => {
        ElMessage.success("删除成功");
        articleData.value = articleData.value.filter(
          (item) => item.articleId !== row.articleId
        );
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  });
};

const handleBrowse = (articleId) => {
  const jsonDataStr = JSON.stringify(
    articleData.value.find((item) => item.articleId === articleId)
  );
  router.push({
    path: `/article/${articleId}`,
    query: {
      data: jsonDataStr,
    },
  });
};
</script>
<template>
  <el-form :inline="true" :model="locationForm" class="demo-form-inline">
    <el-form-item label="Search by location name">
      <el-input
        v-model="locationForm.location"
        placeholder="location"
        clearable
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitLocation">Query</el-button>
    </el-form-item>
  </el-form>
  <el-form :inline="true" :model="titleForm" class="demo-form-inline">
    <el-form-item label="Search by Title">
      <el-input v-model="titleForm.title" placeholder="title" clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitTitle">Query</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="articleData" stripe style="width: 100%">
    <el-table-column type="index" />
    <el-table-column prop="title" label="Title" width="180" />
    <el-table-column prop="locationName" label="Location" width="180" />
    <el-table-column prop="popularity" label="Popularity" width="180" />
    <el-table-column prop="evaluation" label="Evaluation" width="180" />
    <el-table-column prop="username" label="Author" width="180" />
    <el-table-column fixed="right" label="操作" width="200" align="center">
      <template #default="scope">
        <el-button
          type="primary"
          text
          bg
          size="small"
          @click="handleBrowse(scope.row.articleId)"
          >Browse</el-button
        >
        <el-button
          type="danger"
          text
          bg
          size="small"
          @click="handleDelete(scope.row)"
          >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<style>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
