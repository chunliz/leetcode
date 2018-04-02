class FileSystem {
    class TreeNode {
        String name = "";
        String tag = ""; // dir or file or empty
        List<TreeNode> children = null;
        StringBuilder content = new StringBuilder();
        
        public void addChild(TreeNode node) {
            if (children == null) children = new ArrayList<>();
            children.add(node);
        }
    }

    TreeNode root;
    public FileSystem() {
        root = new TreeNode();
    }
    
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        TreeNode node = cd(path);
        if (node.tag.equals("file")) {
            res.add(node.name);
            return res;
        }
        if (node.children == null) return res;
        for (TreeNode tn : node.children) {
            res.add(tn.name);
        }
        //System.out.println(res);
        Collections.sort(res);
        return res;
    }
    
    public void mkdir(String path) {
        path = path.substring(1);
        if (path.length() == 0) return;
        String[] strs = path.split("/"); 
        TreeNode node = root;
        for (String str : strs) {
            //System.out.println(str);
            boolean exist = false;
            if (node.children != null) {
                for (TreeNode tn : node.children) {
                    if (tn.name.equals(str) && tn.tag.equals("dir")) {
                        node = tn;
                        exist = true;
                        break;
                    }
                }
            } 
            
            if (exist == true) continue;

            TreeNode nNode = new TreeNode();
            nNode.name = str;
            nNode.tag = "dir";
            node.addChild(nNode);
            node = nNode;
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        int index = filePath.lastIndexOf("/");
        String dir = filePath.substring(0, index);
        String file = filePath.substring(index+1);
        
        TreeNode cur = null;
        if (index == 0) {
            cur = root;
        } else {
            mkdir(dir);
            cur = cd(dir);
        }
        addFile(cur, file, content);
    }
    
    private TreeNode cd(String dir) {
        dir = dir.substring(1);
        if (dir.length() == 0) return root;
        String[] strs = dir.split("/");
        TreeNode node = root;
        for (String str : strs) {
            for (TreeNode tn : node.children) {
                if (tn.name.equals(str)) { //  && tn.tag.equals("dir")
                    node = tn;
                    break;
                }
            } 
        } 
        return node;
    }
    
    private void addFile(TreeNode cur, String file, String content) {
        TreeNode node = cur;
        boolean exist = false;
        if (node.children != null) {
            for (TreeNode tn : node.children) {
                if (tn.name.equals(file) && tn.tag.equals("file")) {
                    exist = true;
                    node = tn;
                    break;
                }
            }
        }
        if (exist == false) {
            TreeNode nNode = new TreeNode();
            nNode.name = file;
            nNode.tag = "file";
            node.addChild(nNode);
            node = nNode;
        }
        
        node.content.append(content);
    }
    
    public String readContentFromFile(String filePath) {
        int index = filePath.lastIndexOf("/");
        String dir = filePath.substring(0, index);
        String file = filePath.substring(index+1);
        TreeNode node = index == 0? root : cd(dir);
        for (TreeNode tn : node.children) {
            if (tn.name.equals(file) && tn.tag.equals("file")) {
                return tn.content.toString();
            }
        }        
        return "";
    }
}