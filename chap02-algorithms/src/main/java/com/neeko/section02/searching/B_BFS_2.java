package com.neeko.section02.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/* 너비 우선 탐색(Breadth-First Search)
 *   선입선출 구조의 queue를 활용한다.
 *   시작 노드에서 출발해 시작 노드를 기준으로 가까운 노드를 먼저 방문하면서 탐색하는 알고리즘이다.
 * */
public class B_BFS_2 {

    /* 배추 밭에 필요한 배추 지렁이 개수 구하기
     * x, y좌표를 활용한 탐색 문제를 해결할 수 있다.*/

    /* BFS로 문제 해결을 하기 위한 Queue */
    static Queue<Node> q = new LinkedList<>();

    /* 상하좌우 개념의 좌표 배열들 */
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};

    /* 배추밭 */
    static int map[][];

    /* 방문한 배추 좌표 배열 */
    static boolean visit[][];

    /* 현재 좌표 */
    static int cx, cy;

    /* 배추밭의 크기(너비/높이), 심어진 배추의 수 */
    static int M, N, K;

    /* 필요한 배추흰지렁이의 수 */
    static int count;

    /* x와 y좌표를 가지는 static 내부 클래스 */
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        /* 배추밭에 배추 심기 */
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 좌표와 인덱스 개념이 반대이니 조심!
            map[y][x] = 1;
        }

        // 출력 확인
        System.out.println("배추밭");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        count = 0;

        /* 배추 찾기 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                /* 방문 안한 위치에 배추가 심어져 있다면 지렁이를 심자 */
                if(map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    /* 해당 위치 및 붙어있는 배추밭을 방문체크(붙어있는 배추 묶음 파악) */
                    // bfs의 인자는 x좌표, y좌표 순으로 받을 것이라 i와 j를 확인하고 넘겨주자
                    bfs(j, i);
                }
            }
        }

        return count;
    }

    static boolean range_check() {
        return cx >= 0 && cx < M && cy >= 0 && cy < N;
    }

    /* 지렁이가 심어지면 한 번씩 동작 */
    static void bfs(int x, int y) {
        q.offer(new Node(x, y));
        visit[y][x] = true;

        /* 연속적으로 상하 좌우에 배추가 심어져 있다면 해당 위치를 방문한 것으로 체크하고 que를 통해 탐색하는 작업을 반복한다. */
        while(!q.isEmpty()) {
            Node node = q.poll();

            /* 상하좌우 살펴보기 */
            for(int i = 0; i < 4; i++) {
                cx = node.x + dirX[i];
                cy = node.y + dirY[i];

                /* 지금 보는 방향이 좌표로써 존재하면서 방문한 적이 없고 배추가 심어져 있다면 */
                if(range_check() && !visit[cy][cx] && map[cy][cx] == 1) {
                    q.offer(new Node(cx, cy));
                    visit[cy][cx] = true;
                }
            }
        }
    }
}