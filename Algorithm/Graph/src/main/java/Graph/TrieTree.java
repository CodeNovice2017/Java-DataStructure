package Graph;

import java.util.HashMap;

public class TrieTree {
    
    public static class TrieNode{
        // 曾经经过了这个节点多少次,就是添加的时候,有多少字符串经过了我
        // 其实就是之前的前缀是多少个字符串共享的
        public int pass;
        // 有多少个字符串以当前节点结尾
        public int end;
        // 边的集合
        public TrieNode nexts[];

        // 另一种边的集合表达方式
        // public HashMap<Integer,TrieNode> hashMap;
        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }
    public static class Trie{
        private TrieNode root;
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] arr = word.toCharArray();
            TrieNode tempNode = root;
            // 本身头结点也应该加1的,但似乎不影响计算
            // tempNode.pass++;
            for (int i = 0; i < arr.length; i++) {
                if(tempNode.nexts[arr[i] - 'a'] == null){
                    tempNode.nexts[arr[i] - 'a'] = new TrieNode();
                }
                tempNode = tempNode.nexts[arr[i] - 'a'];
                tempNode.pass++;
            }
            // 最后该字符串尾节点end++
            tempNode.end++;
        }
        public void delete(String word){
            if (search(word) != 0) {
                TrieNode tempNode = root;
                // 本身头结点也应该减1的,但似乎不影响计算
                // tempNode.pass--;
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    // 关键判断,如果当前节点的某个字符的下一个边指向的节点的pass减1后为0
                    // 那么就代表该节点之前只经过了一次,那么把该节点直接设置为null即可删除
                    // 而删除节点之前的字符串有可能是之前多个字符串共享的前缀字符串,这些节点的pass都是大于1的,删除某字符串时把pass减1即可
                    if(--tempNode.nexts[arr[i] - 'a'].pass == 0){
                        tempNode.nexts[arr[i] - 'a'] = null;
                        return;
                    }
                    tempNode = tempNode.nexts[arr[i] - 'a'];
                }
                // 如果到了尾部还没有return
                tempNode.end--;
            }
        }
        // 查word这个字符串加过几次
        public int search(String word){
            if (word == null) {
                return 0;
            }
            char[] arr = word.toCharArray();
            TrieNode tempNode = root;
            for (int i = 0; i < arr.length; i++) {
                if (tempNode.nexts[arr[i] - 'a'] == null) {
                    return 0;
                }
                tempNode = tempNode.nexts[arr[i] - 'a'];
            }
            return tempNode.end;
        }
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] arr = pre.toCharArray();
            TrieNode tempNode = root;
            for (int i = 0; i < arr.length; i++) {
                if(tempNode.nexts[arr[i] - 'a'] != null){
                    tempNode = tempNode.nexts[arr[i] - 'a'];
                }else{
                    return 0;
                }
            }
            return tempNode.pass;
        }
    }
}
