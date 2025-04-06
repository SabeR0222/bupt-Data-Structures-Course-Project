<script setup>
import { ref, onMounted, watch, nextTick, onUnmounted } from "vue";
import startImgSrc from "@/assets/iloveimg-resized/start.png";
import endImgSrc from "@/assets/iloveimg-resized/end.png";
import emitter from "@/utils/emitter";
import axios from "axios";
import { useMapStore } from "@/store/map";

let srcId = ref(0);
let destIdList = ref([]);
let edgeList = ref([]);
const mapStore = useMapStore();
const canvasRef = ref(null);

onMounted(() => {
  axios
    .get("/api/vertex")
    .then((response) => {
      mapStore.vertexList = response.data.data;
    })
    .catch((error) => {
      console.log(error);
    });

  emitter.on("path-planning", (value) => {
    axios.post("/api/path", value).then((response) => {
      edgeList = response.data.data;
      srcId.value = value.srcId;
      destIdList.value = value.destIdList;
      const canvas = canvasRef.value;
      const ctx = canvas.getContext("2d");
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      drawImage(
        mapStore.vertexList[srcId.value].x,
        mapStore.vertexList[srcId.value].y,
        startImgSrc
      );
      value.destIdList.forEach((id) => {
        drawImage(
          mapStore.vertexList[id].x,
          mapStore.vertexList[id].y,
          endImgSrc
        );
      });
      drawPath();
    });
  });
});

emitter.on("search-spot", (value) => {
  console.log("value:", value);
  axios.post("/api/spot", value).then((response) => {
    let spotList = response.data.data;
    console.log("spotList:", spotList);
    const canvas = canvasRef.value;
    const ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    spotList.forEach((spot) => {
      drawDotAndText(
        mapStore.vertexList[spot.vertexId].x,
        mapStore.vertexList[spot.vertexId].y,
        mapStore.vertexList[spot.vertexId].label
      );
    });
    let spotData = spotList.map((spot) => {
      return {
        label: mapStore.vertexList[spot.vertexId].label,
        distance: spot.distance,
        type: mapStore.vertexList[spot.vertexId].type,
      };
    });
    console.log("spotData:", spotData);
    emitter.emit("search-spot-result", spotData);
  });
});

onUnmounted(() => {
  emitter.off("path-planning");
  emitter.off("search-spot");
});

function drawImage(x, y, imgSrc) {
  const canvas = canvasRef.value;
  if (!canvas) return; // 确保canvasRef已被解析为DOM元素

  const ctx = canvas.getContext("2d");

  // 创建一个新的Image对象
  const img = new Image();
  img.onload = function () {
    ctx.drawImage(img, x - 12, y - 33, img.width, img.height); // 绘制图片，根据需要调整位置和大小
  };
  img.onerror = function () {
    console.error("Failed to load image");
  };
  img.src = imgSrc; // 设置图片源
}

function drawPath() {
  console.log("drawPath");
  const canvas = canvasRef.value;
  const ctx = canvas.getContext("2d");

  // 设置线条样式
  ctx.strokeStyle = "#000";
  ctx.lineWidth = 3;

  // 绘制连线
  for (let i = 0; i < edgeList.length - 1; i++) {
    const startPoint = {
      x: mapStore.vertexList[edgeList[i]].x,
      y: mapStore.vertexList[edgeList[i]].y,
    };
    const endPoint = {
      x: mapStore.vertexList[edgeList[i + 1]].x,
      y: mapStore.vertexList[edgeList[i + 1]].y,
    };

    ctx.beginPath();
    ctx.moveTo(startPoint.x, startPoint.y);
    ctx.lineTo(endPoint.x, endPoint.y);
    ctx.stroke();
  }
}

function drawDotAndText(centerX, centerY, text) {
  const canvas = canvasRef.value;
  const ctx = canvas.getContext("2d");

  var radius = 5; // 半径
  // 设置填充颜色
  ctx.fillStyle = "red";

  // 绘制圆形（点）
  ctx.beginPath();
  ctx.arc(centerX, centerY, radius, 0, Math.PI * 2, false);
  ctx.fill(); // 填充圆形

  // 设置字体样式
  ctx.font = "16px Arial";
  ctx.fillStyle = "black"; // 文本颜色
  // 计算文本位置，假设距圆心右侧10px，上方5px展示文本
  var textX = centerX + radius + 10;
  var textY = centerY - radius - 5;
  ctx.fillText(text, textX, textY); // 在指定位置绘制文本
}
</script>

<template>
  <div class="canvas-container">
    <canvas ref="canvasRef" width="782" height="1102"></canvas>
  </div>
</template>

<style scoped>
.canvas-container {
  width: 782px;
  height: 1102px;
  background-image: url("@/assets/xtc.jpg");
  background-size: contain;
  background-repeat: no-repeat;
}
</style>
