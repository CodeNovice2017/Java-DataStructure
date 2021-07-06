package OJ.problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-05
 */
public class SerializeTree20210705 {

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serializeResult = new StringBuilder();
        if(root == null){
            return serializeResult.toString();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp != null){
                serializeResult.append(temp.val).append(',');
                queue.offer(temp.left);
                queue.offer(temp.right);
            }else{
                serializeResult.append("null,");
            }
        }
        return serializeResult.deleteCharAt(serializeResult.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        String[] array = data.split(",");
        int arrayIndex = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(arrayIndex < array.length){
                if(!"null".equals(array[arrayIndex])){
                    TreeNode leftNode = new TreeNode(Integer.parseInt(array[arrayIndex]));
                    temp.left = leftNode;
                    queue.offer(leftNode);
                }
                arrayIndex++;
                if(!"null".equals(array[arrayIndex])){
                    TreeNode rightNode = new TreeNode(Integer.parseInt(array[arrayIndex]));
                    temp.right = rightNode;
                    queue.offer(rightNode);
                }
                arrayIndex++;
            }
        }
        return root;
    }
}
