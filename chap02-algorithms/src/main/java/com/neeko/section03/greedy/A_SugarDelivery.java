package com.neeko.section03.greedy;

/* 설탕 배달 문제
* 매 단계마다 즉각적으로 최적이라고 생각되는 선택을 반복해서
* 전체 문제의 해답(최소 봉지의 수)를 구하는 문항*/
public class A_SugarDelivery {

    public static int solution(int n){
        int count = 0;
        while(true){
            /* 5키로 봉지로 모두 가져갈 수 있다면? */
            if(n%5==0){
                return n/5 +count;
            }else if(n<0){ // 3 or 5조합으로 해결 안되는 상황
                return -1;
            }else if(n == 0){ // 3키로 봉지로만 해결
                return count;
            }
            /* 5키로로 나누어 떨어지지 않는다면 3키로 봉지 사용해보기 */
            n=n-3;
            count++;

        }

    }
}
