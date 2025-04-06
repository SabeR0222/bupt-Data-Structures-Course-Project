import { defineStore } from "pinia";

export const useMapStore = defineStore("map", {
  state() {
    return {
      vertexList: [],
    };
  },
});
