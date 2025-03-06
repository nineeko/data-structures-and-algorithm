package com.neeko.section03.stack;

import java.util.Stack;

public class Practice1 {

    public String solution (String input){

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }
        if(stack.isEmpty()){
            return "YES";
        }else
            return "NO";

    }
}
