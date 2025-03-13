package com.neeko.section04.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class F_StairClimbing {
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n + 1];
        int[] dp = new int[n + 1];

        // 점수 입력
        for (int i = 1; i <= n; i++) {
            t[i] = Integer.parseInt(br.readLine());
        }


        if (n == 1) return t[1]; // 계단이 하나일 때
        if (n == 2) return t[1] + t[2]; // 계단이 두 개일 때

        dp[1] = t[1]; // 첫 번째 계단
        dp[2] = t[1] + t[2]; // 두 번째 계단까지의 최대 점수
        dp[3] = Math.max(t[1], t[2]) + t[3]; // 세 번째 계단까지의 최대 점수


        // DP 점화식 적용
        for (int i = 4; i <= n; i++) {
            // 가능 상황 1. 직전 계단에서 올라오는 상황
            // -> 연달아 밟지 않아야하므로 i-3 -> i-1 -> i
            // 가능 상황 2. 두 계단 전에서 올라오는 상황 -> i-2 전의 상황을 고려할 필요 X
            // 1번 : dp[i-3] + t[i-1] vs 2번 : dp[i-2] (현재 계단 점수)
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + t[i - 1]) + t[i]; // 3칸 연속으로 밟을 수 없다.
        }

        return dp[n]; // 마지막 계단까지의 최대 점수 반환
    }
}
