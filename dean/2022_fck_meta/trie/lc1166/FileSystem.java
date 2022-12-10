package trie.lc1166;

import java.util.*;

public class FileSystem {
    class FS {
        Map<String, FS> subFolder;
        int value;

        FS(int value) {
            this.value = value;
            this.subFolder = new HashMap<>();
        }
    }

    private FS root;

    public FileSystem() {
        this.root = new FS(0);
    }

    private String[] parsePath(String path) {
        String[] paths = path.substring(1).split("/");
        //System.out.println(Arrays.toString(paths));
        return paths;
    }

    public boolean createPath(String path, int value) {
        String[] paths = parsePath(path);
        FS node = root;
        for (int i = 0; i < paths.length - 1; i++) {
            if (!node.subFolder.containsKey(paths[i])) {
                return false;
            }
            node = node.subFolder.get(paths[i]);
        }
        if (node.subFolder.containsKey(paths[paths.length - 1])) {
            return false;
        }
        node.subFolder.put(paths[paths.length - 1], new FS(value));
        return true;
    }

    public int get(String path) {
        String[] paths = parsePath(path);
        FS node = root;
        for (int i = 0; i < paths.length; i++) {
            if (!node.subFolder.containsKey(paths[i])) {
                return -1;
            }
            node = node.subFolder.get(paths[i]);
        }
        return node.value;
    }
}
