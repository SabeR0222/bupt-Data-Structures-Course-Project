import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/Register.vue"),
  },
  {
    path: "/",
    name: "home",
    component: () => import("../views/Home.vue"),
    children: [
      {
        path: "/location",
        name: "location",
        component: () => import("../views/Location.vue"),
      },
      {
        path: "/article",
        name: "article",
        component: () => import("../views/Article.vue"),
      },
      {
        path: "/location/:locationId",
        name: "locationDetail",
        component: () => import("../views/LocationDetail.vue"),
      },
      {
        path: "/article/:articleId",
        name: "articledetail",
        component: () => import("../views/ArticleDetail.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
