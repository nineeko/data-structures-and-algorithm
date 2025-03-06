package com.neeko.section03.stack;


import java.util.Stack;

public class Practice2 {

    public int solution(String input) {

        Stack<Character> stack=new Stack<Character>();
        int count=0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)=='(') {
                stack.push('(');
            }else if(input.charAt(i)==')') {
                if(input.charAt(i-1)=='(') { // 레이저
                    stack.pop();
                    count+=stack.size();
                }else{
                    count++;                // 쇠막대기가 끝날 때 +1
                    stack.pop();
                }
            }
        }
        return count;
    }
}