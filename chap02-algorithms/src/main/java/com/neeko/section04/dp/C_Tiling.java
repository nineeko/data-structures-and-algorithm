package com.neeko.section04.dp;

import java.util.Arrays;

public class C_Tiling {

    static final int INF = 9999;
    static final int MODULER= 1000000007;
    public static int solution(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0]=dp[1] =1;
        dp[2] =2;
        for(int i = 3; i<=n; i++){
            // 3 이전 또는 5 이전 인덱스의 봉지 개수 + 1
            // INF가 아닌 값으로 처리하기 위해 Math.min 으로 확인
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] = dp[i] % MODULER;
        }
        return dp[n];
    }
}
