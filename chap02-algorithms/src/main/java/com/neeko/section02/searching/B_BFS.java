package com.neeko.section02.searching;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 너비 우선 탐색(Breadth-First Search)
 * 선입 선출 구조의 queue를 활용한다.
 * 시작 노드에서 출발해서 시작 노드를 기준으로 가까운 노드를 먼저 방문하면서 탐색하는 알고리즘이다. */
public class B_BFS {

    /* 배추 밭에 필요한 배추 지렁이 갯수 구하는 문제
     * x, y 좌표를 활용한 탐색 문제를 해결할 수 있다.
     * */
    static int[][] map;
    // 배추 찾기를 할 때 방문 했는지 여부를 확인할 배열
    static boolean[][] visit;
    //지렁이 개수
    static int count;
    // 상하좌우 개념의 좌표 배열
    static int[] dirX = {0,0,-1,1};
    static int[] dirY = {-1,1,0,0};
    // x와 y좌표를 가지는 Node 클래스
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // 현재 좌표
    static int cx, cy;

    // 배추밭의 크기(가로, 세로)와 배추 개수
    static int M, N, K;

    // BFS로 문제 해결하기 위한 Queue
    static Queue<Node> q = new LinkedList<>();
    public static int solution(String input) throws IOException {

        BufferedReader br =new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 배추 밭
        map = new int[M][N];
        visit = new  boolean[M][N];

        /* 배추 밭에 배추 심기 */
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] =1;
        }

        // 출력 확인
        System.out.println("배추밭");
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        count = 0;

        /* 배추 찾기 */
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                /* 방문하지 않은 위치에 배추가 심어져있다면 지렁이를 심는다. */
                if(map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    /* 해당 위치에 붙어있는 배추밭을 방문 체크 (붙어있는 배추 묶음을 파악) */
                    bfs(i, j);
                }

            }
        }

        return count;
    }
    /* 지렁이가 심어질 때마다 한 번씩 동작하는 bfs 메소드 */
    static void bfs(int x, int y){
        visit[x][y] = true;
        q.offer(new Node(x, y));

        /* 연속적으로 상하좌우에 배추가 심어져있다면 해당 위치를 방문한 것으로 체크하고
        * queue를 통해 탐색 진행 */
        while(!q.isEmpty()){
            Node node = q.poll();

            /* 상하좌우 살펴보기 */
            for(int i = 0; i<4; i++){
                cx = node.x + dirX[i];
                cy = node.y + dirY[i];

                /* 지금 보는 방향이 좌표로서 존재하고, 방문한 적이 없고, 배추가 심어져 있다면 */
                if(rangeCheck() && !visit[cx][cy] && map[cx][cy] == 1){
                    q.offer(new Node(cx, cy));
                    visit[cx][cy] = true;
                }
            }
        }
    }
    static boolean rangeCheck(){
        return cx >= 0 && cy >= 0 && cx < M && cy < N;
    }

}