package OJ.problems;

import java.util.HashMap;
import java.util.List;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-18
 */
public class UnionFind20210818 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int equationsSize = equations.size();
        // 传入一个2 * equationsSize构造参数,是因为如果equations中所有的String变量名都不同,自然最多需要保存的id就是2 * equationsSize
        UnionFind unionFind = new UnionFind(2 * equationsSize);

        // 通过一个HashMap维护equations中String和一个唯一id的绑定关系,这样我们的并查集就可以通过数组的方式去实现,代码更简洁
        // 同时,为了防止Hash表自动扩容,我们可以传入一个2 * equationsSize容量
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        HashMap<String,Integer> hashMap = new HashMap<>(2 * equationsSize);
        // 定义一个id自增,表示每一个equations中的变量的唯一标识
        int id = 0;
        for(int i = 0;i < equationsSize;i++){
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            if(!hashMap.containsKey(var1)){
                hashMap.put(var1,id++);
            }
            if(!hashMap.containsKey(var2)){
                hashMap.put(var2,id++);
            }
            // 执行合并的操作,并且合并的是否传入对应的权值
            unionFind.union(hashMap.get(var1),hashMap.get(var2),values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind{

        private int[] parent;
        /**
         * 节点指向父节点的权值
         */
        private double[] weight;

        public UnionFind(int n){
            parent = new int[n];
            weight = new double[n];
            // 最开始初始化的时候,相当于每个节点都指向其自己本身,并且本身即为当前集合的代表节点
            for(int i = 0;i < n;i++){
                parent[i] = i;
                // 自己除自己商为0
                weight[i] = 1.0;
            }
        }
        public void union(int x, int y,double value){
            // 查找两个变量对应根节点的Id
            int rootX = find(x);
            int rootY = find(y);
            // 如果这两个变量的对应的根节点(代表节点)的id相等,那么就无须合并
            if(rootX == rootY){
                return;
            }else{
                // 默认规则:让x的根节点的父节点指向y节点的父节点
                parent[rootX] = rootY;
                weight[rootX] = (weight[y] * value) / weight[x];
            }
        }

        /**
         * 路径压缩
         * @param id
         * @return
         */
        public int find(int id){
            if(parent[id] == id){
                return id;
            }else{
                // 原本id节点的父节点的id
                int originId = parent[id];
                parent[id] = find(parent[id]);
                // 并查集最大的特点:路径压缩,边查边压缩,我之前保存了我原本id节点的父节点的id,originId,那么经过上面递归之后,originId已经连接在根节点并且维护好权值了,那么我本身id节点的权值维护就只需要
                weight[id] *= weight[originId];
                return parent[id];
            }
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            // 不需要担心在计算时,
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0;
            }
        }
    }
}
