package com.neeko.section02.searching;

import java.util.HashMap;
import java.util.Map;

public class G_TreeTraversal {
    static class Node {
        char left;
        char right;

        public Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    // 트리를 저장하는 Map
    static Map<Character, Node> tree = new HashMap<>();

    // 전위 순회 (Pre-order)
    static StringBuilder preOrder(char node){
        StringBuilder result = new StringBuilder();
        if (node == '.') return result;
        result.append(node);  // 현재 노드 출력
        result.append(preOrder(tree.get(node).left));  // 왼쪽 자식 순회
        result.append(preOrder(tree.get(node).right));  // 오른쪽 자식 순회
        return result;
    }


    // 중위 순회 (In-order)
    static StringBuilder inOrder(char node){
        StringBuilder result = new StringBuilder();
        if (node == '.') return result;
        result.append(inOrder(tree.get(node).left));  // 왼쪽 자식 순회
        result.append(node);  // 현재 노드 출력
        result.append(inOrder(tree.get(node).right));  // 오른쪽 자식 순회
        return result;
    }

    // 후위 순회 (Post-order)
    static StringBuilder postOrder(char node){
        StringBuilder result = new StringBuilder();
        if (node == '.') return result;
        result.append(postOrder(tree.get(node).left)); // 왼쪽 자식 순회
        result.append(postOrder(tree.get(node).right)); // 오른쪽 자식 순회
        result.append(node);  // 현재 노드 출력
        return result;
    }

    public static String solution(String input) {
        StringBuilder result = new StringBuilder();
        String[] lines = input.split("\n");

        // 첫 번째 줄은 노드의 개수, 그 후에 트리 정보를 받는다
        int n = Integer.parseInt(lines[0]);

        // 트리 구성
        for (int i = 1; i <= n; i++) {
            String[] parts = lines[i].split(" ");
            char node = parts[0].charAt(0);
            char left = parts[1].charAt(0);
            char right = parts[2].charAt(0);
            tree.put(node, new Node(left, right));
        }

        // 전위, 중위, 후위 순회 결과 생성
        result.append(preOrder('A')).append("\n");
        result.append(inOrder('A')).append("\n");
        result.append(postOrder('A')).append("\n");

        return result.toString().trim();  // 마지막에 불필요한 공백 제거
    }
}
