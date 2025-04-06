<script setup>
import axios from "axios";
import { onMounted, ref, reactive, watch, computed } from "vue";
import { useRoute } from "vue-router";
import { ElMessage } from "element-plus";

let score = ref(0);
const colors = ref(["#99A9BF", "#F7BA2A", "#FF9900"]);
const route = useRoute();
const wordIndexes = ref([]);
const articleId = route.params.articleId;
const jsonDataStr = route.query.data;
const ArticleDetail = ref({
  articleId: articleId,
  title: "",
  locationName: "",
  popularity: 0,
  evaluation: 0,
  content: "",
  username: "",
});

ArticleDetail.value = {
  ...ArticleDetail.value, // 保留现有属性
  ...JSON.parse(jsonDataStr), // 添加或覆盖有新数据的属性
};

onMounted(() => {
  axios
    .get(`/api/article/getContent/${articleId}`)
    .then((res) => {
      ArticleDetail.value.content = res.data.data;
      console.log("收到ArticleDetail:", ArticleDetail.value);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
  axios.post(`/api/article/updatePopularity/${articleId}`).then((response) => {
    console.log("浏览量加1");
  });
});

const dataForSubmitScore = reactive({
  userId: localStorage.getItem("vuems_userId"),
  articleId: articleId,
  score: score.value,
});

const dataForSubmitWord = reactive({
  text: ArticleDetail.value.content,
  word: "",
});

const submitScore = () => {
  axios
    .post("/api/articleScore/setScore", dataForSubmitScore)
    .then((response) => {
      console.log("评分提交成功，服务器响应：", response.data);
      ElMessage({
        message: "感谢您的评分！",
        type: "success",
      });
    })
    .catch((error) => {
      console.error("评分提交失败：", error);
      ElMessage({
        message: "评分提交时发生错误，请稍后重试。",
        type: "error",
      });
    });
};

const submitWord = () => {
  dataForSubmitWord.text = ArticleDetail.value.content;
  axios
    .post("/api/article/search", dataForSubmitWord)
    .then(function (response) { 
      wordIndexes.value = response.data.data;
      ElMessage({
        message: "查询成功！",
        type: "success",
      });
    })
    .catch(function (error) {
      console.error("请求出错：", error);
    });
};

watch(score, (newScore, oldScore) => {
  dataForSubmitScore.score = newScore * 2;
});

const formattedWordIndexes = computed(() => {
  return wordIndexes.value.map((entry) => Object.keys(entry)[0]).join(", ");
});
</script>

<template>
  <el-form :inline="true" :model="dataForSubmitWord" class="demo-form-inline">
    <el-form-item label="Word">
      <el-input v-model="dataForSubmitWord.word" placeholder="word" clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitWord">Query</el-button>
    </el-form-item>
  </el-form>
  <h3>该单词在文章中出现的位置：{{ formattedWordIndexes }}</h3>
  <el-card style="max-width: 100%">
    <template #header>
      <div class="card-header">
        <h2>{{ ArticleDetail.title }}</h2>
      </div>
    </template>
    <p>{{ ArticleDetail.content }}</p>
    <template #footer>
      <el-descriptions>
        <el-descriptions-item label="Location">{{
          ArticleDetail.locationName
        }}</el-descriptions-item>
        <el-descriptions-item label="Popularity">{{
          ArticleDetail.popularity
        }}</el-descriptions-item>
        <el-descriptions-item label="Evaluation">{{
          ArticleDetail.evaluation
        }}</el-descriptions-item>
        <el-descriptions-item label="Author">{{
          ArticleDetail.username
        }}</el-descriptions-item>
      </el-descriptions>
    </template>
  </el-card>
  <div class="diary-rating-container">
    <div class="rating-text">You can rate this diary</div>
    <el-rate v-model="score" allow-half :colors="colors" size="large" />
    <el-button class="submit-button" @click="submitScore">Submit</el-button>
  </div>
</template>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.diary-rating-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.rating-text {
  margin-bottom: 10px; /* 在评分说明和评分组件之间添加一点间距 */
}

.submit-button {
  margin-top: 10px; /* 在评分组件和按钮之间添加间距 */
  width: 10%; /* 使按钮宽度适应容器，可以根据需要调整 */
}

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
