package com.neeko.section05.deque;

import java.util.ArrayDeque;
import java.util.Deque;


public class Practice1 {

    static class Balloon {
        int order;
        int noteValue;

        Balloon(int order, int noteValue) {
            this.order = order;
            this.noteValue = noteValue;
        }
    }

    public String solution(Integer[] notes) {

        // 풍선을 Deque에 삽입
        Deque<Balloon> balloons = new ArrayDeque<>();
        for (int i = 0; i < notes.length; i++) {
            balloons.addLast(new Balloon(i + 1, notes[i]));
        }

        StringBuilder sb = new StringBuilder();

        Balloon current = balloons.pollFirst(); // 첫 번째 풍선 터뜨리기

        while (!balloons.isEmpty()) {
            sb.append(current.order).append(" ");

            int move = current.noteValue; // 풍선에 적힌 값

            if (move > 0) {
                // 오른쪽 이동
                for (int i = 1; i < move; i++) {
                    balloons.addLast(balloons.pollFirst());
                }
                current = balloons.pollFirst(); // 이동 후 풍선 터뜨리기
            } else {
                // 왼쪽 이동 (음수일 때는 그대로 move만큼 이동)
                for (int i = 0; i < Math.abs(move); i++) {
                    balloons.addFirst(balloons.pollLast());
                }
                current = balloons.pollFirst(); // 이동 후 풍선 터뜨리기
            }
        }
        sb.append(current.order); // 마지막 풍선 추가

        return sb.toString();

    }
}

