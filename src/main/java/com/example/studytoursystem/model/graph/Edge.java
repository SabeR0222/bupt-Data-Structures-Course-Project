package com.example.studytoursystem.model.graph;

import lombok.Data;

@Data
public class Edge {
    private int destId;
    private int weight;
    private float crowding;
    private boolean isRideable;

    public int getTimeWeight() {
        return (int) (weight / crowding);
    }

    public int getRideableWeight() {
        if(!isRideable) {
            return (int)getTimeWeight();
        } else {
            return (int)(getTimeWeight() / 3);
        }
    }

    public int getWeightByStrategy(int stratgy) {
        return switch (stratgy) {
            case 0 -> weight;
            case 1 -> getTimeWeight();
            case 2 -> getRideableWeight();
            default -> 0;
        };
    }

    public Edge(int destId, int weight, float crowding, boolean isRideable) {
        this.destId = destId;
        this.weight = weight;
        this.crowding = crowding;
        this.isRideable = isRideable;
    }
}