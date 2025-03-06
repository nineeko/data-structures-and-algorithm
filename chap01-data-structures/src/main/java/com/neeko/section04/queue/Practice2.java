package com.neeko.section04.queue;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Practice2 {

    public int solution(int w, int l, Integer[] truckWeight) {
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> trucks = new LinkedList<>(Arrays.asList(truckWeight));

        int time = 0;
        int totalWeightOnBridge = 0; // 다리 위 트럭들의 총 무게


        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        while (!trucks.isEmpty() || totalWeightOnBridge > 0) {
            time++; // 1초 경과

            // 다리 맨 앞의 트럭이 빠져나옴
            totalWeightOnBridge -= bridge.poll();


            if (!trucks.isEmpty()) {
                if (totalWeightOnBridge + trucks.peek() <= l) {
                    int newTruck = trucks.poll(); // 다리에 올라가는 트럭
                    bridge.offer(newTruck); // 다리에 추가
                    totalWeightOnBridge += newTruck;
                } else {
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}
