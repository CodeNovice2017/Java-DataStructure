package OJ.problems;

import java.util.HashMap;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-09
 */
public class MostPointsInLine20210709 {
    public class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o){
            if(this == o){
                return true;
            }
            if(o == null){
                return false;
            }
            Node temp = (Node)o;
            return this.x == temp.x && this.y == temp.y;
        }
        @Override
        public int hashCode(){
            return String.valueOf(x + y).hashCode();
        }
    }

    public class NodeFlag{
        boolean x;
        boolean y;
        boolean y45;
        boolean y135;
        NodeFlag(){

        }
    }

    public int maxPoints(int[][] points) {
        int result = 0;
        // 感觉实际上问题可以转换为任何一个节点出发,上下左右两个方向,斜方向两个方向扩展,能形成的所有直线的最大值
        HashMap<Node,NodeFlag> map = new HashMap<>();
        for(int[] temp : points){
            Node tempNode = new Node(temp[0],temp[1]);
            NodeFlag tempNodeFlag = new NodeFlag();
            map.put(tempNode,tempNodeFlag);
        }
        for(int[] temp : points){
            Node tempNode = new Node(temp[0],temp[1]);
            // x方向
            if(!map.get(tempNode).x){
                map.get(tempNode).x = true;
                int x = tempNode.x;
                int y = tempNode.y;
                int currentLength = 1;
                Node judgeNode = new Node(++x,y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.x = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(++x,y);
                }
                x = tempNode.x;
                y = tempNode.y;
                judgeNode = new Node(--x,y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.x = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(--x,y);
                }
                result = Math.max(result,currentLength);
            }
            // y方向
            if(!map.get(tempNode).y){
                map.get(tempNode).y = true;
                int x = tempNode.x;
                int y = tempNode.y;
                int currentLength = 1;
                Node judgeNode = new Node(x,++y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(x,++y);
                }
                x = tempNode.x;
                y = tempNode.y;
                judgeNode = new Node(x,--y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(x,--y);
                }
                result = Math.max(result,currentLength);
            }
            // y45方向
            if(!map.get(tempNode).y45){
                map.get(tempNode).y45 = true;
                int x = tempNode.x;
                int y = tempNode.y;
                int currentLength = 1;
                Node judgeNode = new Node(++x,++y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y45 = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(++x,++y);
                }
                x = tempNode.x;
                y = tempNode.y;
                judgeNode = new Node(--x,--y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y45 = true;
                    currentLength++;
                    judgeNode = new Node(--x,--y);
                }
                result = Math.max(result,currentLength);
            }
            // y135方向
            if(!map.get(tempNode).y135){
                map.get(tempNode).y135 = true;
                int x = tempNode.x;
                int y = tempNode.y;
                int currentLength = 1;
                Node judgeNode = new Node(--x,++y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y135 = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(--x,++y);
                }
                x = tempNode.x;
                y = tempNode.y;
                judgeNode = new Node(++x,--y);
                while(map.get(judgeNode) != null){
                    NodeFlag tempFlag = map.get(judgeNode);
                    tempFlag.y135 = true;
                    map.put(judgeNode,tempFlag);
                    currentLength++;
                    judgeNode = new Node(++x,--y);
                }
                result = Math.max(result,currentLength);
            }
        }
        return result;
    }
}
