package dfs.leetc679_h;

import java.util.*;

public class Solution {
    private Stack<Character> expression = new Stack<>();
    private boolean is24=false;
    public boolean judgePoint24(int[] cards) {
        char[] numbers = new char[cards.length];
        char[] operator = new char[]{'+','-','*','/'};
        for(int i=0;i<cards.length;i++){
            numbers[i]=(char)('0'+ cards[i]);
        }
        for(int i=0;i<numbers.length;i++){
            DFS(numbers,operator);
        }
        return is24;
    }
    
    private void DFS(char[] numbers, char[] operator){
        if(is24){
            return ;
        }
        if(numbers.length==0){
            isEquals24();
            return;
        }

        if(!expression.isEmpty() && expression.peek()>'0'&& expression.peek()<='9'){
            for(int i=0;i<operator.length;i++){
                expression.push(operator[i]);
                DFS(numbers, operator);
                expression.pop();
            }
        } else{
            for(int i=0;i<numbers.length;i++){
                expression.push(numbers[i]);
                DFS(copyWithout(i,numbers),operator);
                expression.pop();
            }
        }
    }

    private char[] copyWithout(int index,char[] originalArray){
        char[] newArray = new char[originalArray.length-1];
        for(int i=0;i<originalArray.length;i++){
            if(i!=index){
                if(i>index){
                    newArray[i-1]=originalArray[i]; 
                } else{
                    newArray[i]=originalArray[i];
                }
            }
        }

        return newArray;
    }

    private void isEquals24(){
        
        StringBuilder exp = new StringBuilder();
        expression.stream().forEach(c->exp.append(c));
        //if it has one parentheses (1+2)+(3+4)
        doCalculate(new StringBuilder(exp).insert(0, '(').insert(4, ')').toString());
        doCalculate(new StringBuilder(exp).insert(0, '(').insert(6, ')').toString());
        doCalculate(new StringBuilder(exp).insert(2, '(').insert(6, ')').toString());
        doCalculate(new StringBuilder(exp).insert(2, '(').insert(8, ')').toString());
        doCalculate(new StringBuilder(exp).insert(4, '(').insert(8, ')').toString());
        //if it has two parentheses 
        doCalculate(new StringBuilder(exp).insert(0, '(').insert(4, ')').insert(6, '(').insert(10, ')').toString());
        //no parentheses
        doCalculate(exp.toString());
    }

    private void doCalculate(String exp){
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for(int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
            if(c>='1'&&c<='9'){
                numbers.push((double)c-'0');
                updateProductAndDivde(numbers,operators);
            } else{
                if(c==')'){
                    while(operators.peek()!='('){
                        updatePlusAndMinus(numbers,operators);
                    }
                    operators.pop();
                    updateProductAndDivde(numbers,operators);
                } else{
                    operators.push(c);
                }
            }
        }
        while(!operators.isEmpty()){
            updatePlusAndMinus(numbers,operators);
        }
        
        if(Math.abs(numbers.peek()-24.000000)<0.000001){
            is24 = true;
        }
    }

    private void updatePlusAndMinus(Stack<Double> numbers,Stack<Character> operators){
        if(operators.peek()=='+'){
            numbers.push(numbers.pop()+numbers.pop());
        }
        if(operators.peek()=='-'){
            numbers.push(numbers.pop()-numbers.pop());
        }
        operators.pop();
    }

    private void updateProductAndDivde(Stack<Double> numbers,Stack<Character> operators){
        if(!operators.isEmpty() && operators.peek()=='*'){
            numbers.push(numbers.pop()*numbers.pop());
            operators.pop();
        } else if(!operators.isEmpty() &&operators.peek()=='/'){
            double a = numbers.pop();
            double b = numbers.pop();
            numbers.push(b/a);
            operators.pop();
        }
    }
}
