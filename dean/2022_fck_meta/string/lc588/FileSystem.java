package string.lc588;

import java.util.*;

public class FileSystem {
    private Node root;

    public FileSystem() {
        root = new Node("/");
    }

    public List<String> ls(String path) {
        String[] nodes = path.substring(1).split("/");
        Node n = root;
        for (String s : nodes) {
            if (!s.isEmpty()) {
                n = n.dir.get(s);
            }
        }
        if (n.type == Type.FILE) {
            return Arrays.asList(n.name);
        }
        List<String> files = new ArrayList<>();
        for (Node d : n.dir.values()) {
            files.add(d.name);
        }
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
        String[] nodes = path.substring(1).split("/");
        Node n = root;
        for (String s : nodes) {
            n = n.dir.computeIfAbsent(s, Node::new);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] nodes = filePath.substring(1).split("/");
        Node n = root;
        for (String s : nodes) {
            n = n.dir.computeIfAbsent(s, Node::new);
        }
        n.type = Type.FILE;
        //注意审题
        n.content = n.content + content;
    }

    public String readContentFromFile(String filePath) {
        String[] nodes = filePath.substring(1).split("/");
        Node n = root;
        for (String s : nodes) {
            n = n.dir.computeIfAbsent(s, Node::new);
        }
        return n.content;
    }

    class Node {
        Map<String, Node> dir;
        String name;
        Type type;
        String content;

        Node(String name) {
            this.name = name;
            this.type = Type.PATH;
            this.dir = new HashMap<>();
            this.content = "";
        }
    }

    enum Type {
        FILE,
        PATH;
    }
}
