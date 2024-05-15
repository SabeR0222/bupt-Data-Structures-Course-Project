package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class EdgeNode {
    int destId;
    int weight;
    float crowding;
    boolean isRideable;

    // 计算真实权重: 原始权重除以拥挤度
    public int getTimeWeight() {
        return (int)(weight / crowding);
    }

    public int getRideableWeight() {
        if (!isRideable) {
            return (int)getTimeWeight();
        } else {
            return (int)(getTimeWeight() / 3);
        }
    }

    public int getWeightByStrategy(int strategy) {
        switch (strategy) {
            case 0:
                return weight;
            case 1:
                return getTimeWeight();
            case 2:
                return getRideableWeight();
            default:
                return 0;
        }
    }

    public EdgeNode(int destId, int weight, float crowding, boolean isRideable) {
        this.destId = destId;
        this.weight = weight;
        this.crowding = crowding;
        this.isRideable = isRideable;
    }
}