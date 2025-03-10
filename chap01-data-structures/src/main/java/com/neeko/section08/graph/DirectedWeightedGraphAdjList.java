package com.neeko.section08.graph;


import java.util.ArrayList;
import java.util.List;

/* 인접 리스트 기반 유방향 가중치 그래프 예제
 * - 각 노드의 인접 노드와 간선 가중치를 관리
 */
public class DirectedWeightedGraphAdjList {
    private int vertices;                     // 현재 정점(노드)의 총 개수
    private List<List<Edge>> adjList;         // 각 정점의 인접 간선 목록
    private List<Boolean> active;             // 각 노드의 활성 상태 (true이면 해당 노드가 존재)

    /* 간선 클래스로 target 정점과 가중치 보유 */
    public static class Edge {
        int target;
        int weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + target + ", w:" + weight + ")";
        }
    }

    /* 초기 정점 수 만큼 인접 리스트와 active 리스트 초기화 */
    public DirectedWeightedGraphAdjList(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        active = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
            active.add(true);
        }
    }

    /* 새로운 노드를 동적으로 추가 */
    public int addNode() {
        adjList.add(new ArrayList<>());
        active.add(true);
        vertices++;
        return vertices - 1;
    }

    /* 유방향 간선 추가: u에서 v로 가는 간선에 가중치 추가
     * (u와 v가 모두 활성 상태여야 함)
     */
    public void addEdge(int u, int v, int weight) {
        if (u < vertices && v < vertices && active.get(u) && active.get(v)) {
            adjList.get(u).add(new Edge(v, weight));
        }
    }

    /* 유방향 간선 제거: u에서 v로 가는 간선을 제거 */
    public void removeEdge(int u, int v) {
        if (u < vertices && v < vertices) {
            adjList.get(u).removeIf(edge -> edge.target == v);
        }
    }

    /* 노드 x를 그래프에서 제거
     * 제거된 노드는 비활성화(active=false) 처리되고,
     * 다른 노드의 인접 목록에서 해당 노드와 연결된 간선이 제거됨
     */
    public void removeNode(int x) {
        if (x < vertices && active.get(x)) {
            active.set(x, false);
            // 다른 모든 노드의 인접 목록에서 x로 향하는 간선 제거
            for (int i = 0; i < vertices; i++) {
                if (active.get(i)) {
                    adjList.get(i).removeIf(edge -> edge.target == x);
                }
            }
            // 자신의 인접 리스트도 비움
            adjList.get(x).clear();
        }
    }

    /* 그래프의 상태를 출력
     * 각 활성 노드의 인접 간선 목록을 출력하며,
     * 비활성 노드는 'x'로 표시하여 제거된 노드를 구분함.
     */
    public void printGraph() {
        System.out.println("유방향 가중치 그래프 (인접 리스트):");
        for (int i = 0; i < vertices; i++) {
            if (active.get(i)) {
                System.out.print(i + ": ");
                for (Edge edge : adjList.get(i)) {
                    System.out.print(edge + " ");
                }
                System.out.println();
            } else {
                System.out.println(i + ": x");
            }
        }
    }

    public static void main(String[] args) {

        // 1. 초기 그래프 생성
        DirectedWeightedGraphAdjList graph = new DirectedWeightedGraphAdjList(4);

        // 그래프 구조:
        // 1 - 2
        // | \ |
        // 3 - 4
        graph.addEdge(0, 1, 5); // 1 -> 2 (5)
        graph.addEdge(0, 2, 3); // 1 -> 3 (3)
        graph.addEdge(0, 3, 1); // 1 -> 4 (1)
        graph.addEdge(1, 3, 2); // 2 -> 4 (2)
        graph.addEdge(2, 3, 2); // 3 -> 4 (2)
        System.out.println("초기 그래프:");
        graph.printGraph();
        System.out.println();

        // 2. addNode 테스트: 새로운 노드 추가
        // 그래프 구조:
        // 1 - 2 \
        // | \ |  5
        // 3 - 4 /
        int newNode = graph.addNode();
        System.out.println("새로운 노드 추가: " + newNode);
        graph.addEdge(1, newNode, 4);   // 2 -> 5 (4)
        graph.addEdge(3, newNode, 4);   // 4 -> 5 (4)
        System.out.println("노드 5 추가 후 그래프:");
        graph.printGraph();
        System.out.println();

        // 3. removeEdge 테스트: 노드 1에서 노드 4으로의 간선 제거
        // 그래프 구조:
        // 1 - 2 \
        // |   |  5
        // 3 - 4 /
        System.out.println("노드 1에서 노드 4 간선 제거:");
        graph.removeEdge(0, 3);
        graph.printGraph();
        System.out.println();

        // 4. removeNode 테스트: 노드 3 제거
        // 그래프 구조:
        // 1 - 2 \
        //     |  5
        // x   4 /
        System.out.println("노드 3 제거:");
        graph.removeNode(2);
        graph.printGraph();
        System.out.println();
    }
}