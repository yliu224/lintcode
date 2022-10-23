package tree.lc1628;

import java.util.Stack;

class TreeBuilder {

    Node buildTree(String[] postfix) {
        Stack<Node> stack = new Stack<>();
        for (String s : postfix) {
            Node n = new ExpressionNode(s);
            if (n.isOperator) {
                n.right = stack.pop();
                n.left = stack.pop();
            }
            stack.push(n);
        }
        return stack.pop();
    }

    abstract class Node {
        public abstract int evaluate();

        boolean isOperator;
        String op;
        int value;
        Node left;
        Node right;
        // define your fields here
    }


    class ExpressionNode extends Node {

        public ExpressionNode(String s) {
            try {
                value = Integer.parseInt(s);
                isOperator = false;
            } catch (Exception e) {
                isOperator = true;
                op = s;
            }
        }

        @Override
        public int evaluate() {
            if (this.left == null && this.right == null) {
                return value;
            }
            int leftV = left.evaluate();
            int rightV = right.evaluate();
            switch (op) {
                case "+" -> {
                    return leftV + rightV;
                }
                case "-" -> {
                    return leftV - rightV;
                }
                case "*" -> {
                    return leftV * rightV;
                }
                case "/" -> {
                    return leftV / rightV;
                }
            }
            throw new RuntimeException("Unkonwn operator" + op);
        }
    }
}
