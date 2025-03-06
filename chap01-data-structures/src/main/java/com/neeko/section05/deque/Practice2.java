package com.neeko.section05.deque;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

public class Practice2 {

    public String solution(String command, Integer[] array) {
        Deque<Integer> deque = new ArrayDeque<>(Arrays.asList(array));
        boolean isReversed = false;

        for (char cmd : command.toCharArray()) {
            if (cmd == 'R') {
                isReversed = !isReversed;
            } else if (cmd == 'D') {
                if (deque.isEmpty()) return "error";
                if (isReversed) {
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
            }
        }


        StringBuilder sb = new StringBuilder("[");
        while (!deque.isEmpty()) {
            sb.append(isReversed ? deque.removeLast() : deque.removeFirst());
            if (!deque.isEmpty()) sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }
}
