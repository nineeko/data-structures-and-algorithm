package com.neeko.section07.heap;

import java.util.PriorityQueue;

public class Practice1 {
    public String solution(Integer[] array) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
            if (Math.abs(a) == Math.abs(b)) return a - b;
            return Math.abs(a) - Math.abs(b);
        });

        StringBuilder sb = new StringBuilder();

        for (int num : array) {
            if (num == 0) {
                if (minHeap.isEmpty()) {
                    sb.append("0 ");
                } else {
                    sb.append(minHeap.poll()).append(" ");
                }
            } else {
                minHeap.offer(num);
            }
        }

        return sb.toString().trim(); // 마지막 공백 제거
    }
}


