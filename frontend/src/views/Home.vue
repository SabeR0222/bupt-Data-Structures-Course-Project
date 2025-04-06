<script setup>
import Menu from "@/components/Menu.vue";
import Dashboard from "@/components/Dashboard.vue";
import vHeader from "@/components/Header.vue";
import Article from "./Article.vue";
import router from "@/router";
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <v-header />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Menu />
        </el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped></style>
