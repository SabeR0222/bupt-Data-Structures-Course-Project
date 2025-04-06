<script setup>
import { reactive, ref } from "vue";
import { ElDrawer, ElMessageBox } from "element-plus";
import axios from "axios";

const { locationId } = defineProps(["locationId"]);

const formLabelWidth = "60px";
let timer;

const dialog = ref(false);
const loading = ref(false);

const form = reactive({
  title: "",
  content: "",
  locationId: locationId,
  userId: localStorage.getItem("vuems_userId"),
});

const onClick = async () => {
  console.log("提交表单:", form);
  loading.value = true;
  try {
    const response = await axios.post("/api/article/add", form);
    console.log("服务器响应:", response.data);
    dialog.value = false;
    loading.value = false;
  } catch (error) {
    console.error("提交表单时发生错误:", error);
    loading.value = false;
  }
};
const handleClose = (done) => {
  if (loading.value) {
    return;
  }
  ElMessageBox.confirm("Do you want to submit?")
    .then(() => {
      loading.value = true;
      timer = setTimeout(() => {
        done();
        // 动画关闭需要一定的时间
        setTimeout(() => {
          loading.value = false;
        }, 200);
      }, 1000);
    })
    .catch(() => {
      // catch error
    });
};

const cancelForm = () => {
  loading.value = false;
  dialog.value = false;
  clearTimeout(timer);
};
</script>

<template>
  <el-button size="large" type="primary" @click="dialog = true"
    >Write an article</el-button
  >
  <el-drawer
    v-model="dialog"
    title="Write an article"
    :before-close="handleClose"
    direction="rtl"
    class="demo-drawer"
  >
    <div class="demo-drawer__content">
      <el-form :model="form">
        <el-form-item
          label="Title"
          :label-width="formLabelWidth"
          style="display: block"
        >
          <el-input v-model="form.title" autocomplete="off" />
        </el-form-item>
        <el-form-item
          label="Content"
          :label-width="formLabelWidth"
          style="display: block"
        >
          <el-input
            v-model="form.content"
            :autosize="{ minRows: 15, maxRows: 25 }"
            type="textarea"
            placeholder="Please input"
          />
        </el-form-item>
      </el-form>
      <div class="demo-drawer__footer">
        <el-button @click="cancelForm">Cancel</el-button>
        <el-button type="primary" :loading="loading" @click="onClick">
          {{ loading ? "Submitting ..." : "Submit" }}
        </el-button>
      </div>
    </div>
  </el-drawer>
</template>
