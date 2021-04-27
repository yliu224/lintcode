package dfs.lc262_m;

import java.util.ArrayList;
import java.util.List;

public class MyTreeNode {
    int val;
    List<MyTreeNode> children;
    MyTreeNode parent;
    /**
     * @param val: the val of the node
     * @return: a MyTreeNode Object
     */
    MyTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
        this.parent=null;
    }
    
    /**
     * @param root: the root treenode
     * @return: get the traverse of the treenode
     */
    public ArrayList<Integer> traverse(MyTreeNode root) {
        ArrayList<Integer> traverseList = new ArrayList<>();
        traverseList.add(root.val);
        
        for(MyTreeNode child:root.children){
            traverseList.addAll(traverse(child));
        }
        return traverseList;
    }

    /**
     * @param root: the node where added
     * @param value: the added node's value
     * @return: add a node, return the node
     */
    public MyTreeNode addNode(MyTreeNode root, int value) {
        MyTreeNode newChild = new MyTreeNode(value);
        newChild.parent = root;
        root.children.add(newChild);
        return newChild;
    }

    /**
     * @param root: the deleted node
     * @return: nothing
     */
    //注意这个Delete逻辑
    //若果是root那么是特殊case
    //要思考child加进去后的顺序变化
    public void deleteNode(MyTreeNode root) {
        if(root.parent==null){
            for(MyTreeNode child:root.children){
                child.parent=null;
            }
            return;
        }
        int index = root.parent.children.indexOf(root);
        
        for(int i = root.children.size()-1;i>=0;i--){
            MyTreeNode child = root.children.get(i);
            child.parent = root.parent;
            root.parent.children.add(index,child);
        }
        root.parent.children.remove(index+root.children.size());
    }
}