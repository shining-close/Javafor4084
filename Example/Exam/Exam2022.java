package Example.Exam;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;


import java.util.HashSet;
import java.util.Set;

class Node {
    // 节点的标签（整数）
    private final int label;
    // 邻居节点列表（邻接表）
    private final List<Node> neighbours;

    // 构造方法：初始化标签，邻接表为空
    public Node(int label) {
        this.label = label;
        this.neighbours = new ArrayList<>();
    }

    // 添加邻居节点到邻接表
    public void addNeighbour(Node node) {
        this.neighbours.add(node);
    }

    // 获取邻接表中的所有邻居节点
    public List<Node> getNeighbours() {
        // 返回不可修改列表，避免外部直接修改内部邻接表
        return Collections.unmodifiableList(neighbours);
    }

    // 获取节点标签
    public int getLabel() {
        return label;
    }

    // 重写toString：仅返回标签字符串
    @Override
    public String toString() {
        return String.valueOf(label);
    }

    // 重写equals：基于标签判断相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label == node.label;
    }

    // 重写hashCode：基于标签生成哈希值
    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

class GraphParser {
    // 静态方法：解析字符串并返回Node数组
    public static Node[] parseGraph(String spec) {
        // 1. 按换行分割字符串，得到每行内容
        String[] lines = spec.split("\n");
        
        // 2. 解析节点数量和边数量
        int nodeCount = Integer.valueOf(lines[0]);
        int edgeCount = Integer.valueOf(lines[1]);
        
        // 3. 创建节点数组（nodes[0]对应ID=1的节点，依此类推）
        Node[] nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            int label = i + 1; // 节点ID从1开始
            nodes[i] = new Node(label);
        }
        
        // 4. 解析每条边，构建邻接表
        for (int i = 0; i < edgeCount; i++) {
            // 取边的行（从第3行开始，索引为2）
            String edgeLine = lines[2 + i];
            // 按空格分割得到起点和终点的ID
            String[] edgeParts = edgeLine.split(" ");
            int startLabel = Integer.valueOf(edgeParts[0]);
            int endLabel = Integer.valueOf(edgeParts[1]);
            
            // 找到起点对应的Node对象（ID-1对应数组索引）
            Node startNode = nodes[startLabel - 1];
            // 找到终点对应的Node对象
            Node endNode = nodes[endLabel - 1];
            // 将终点添加到起点的邻接表中
            startNode.addNeighbour(endNode);
        }
        
        return nodes;
    }
}


class Edge {
    // 边的起点和终点
    private final Node start;
    private final Node end;

    // 构造方法：初始化起点和终点
    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    // 重写equals：基于起点和终点的标签判断边是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return start.equals(edge.start) && end.equals(edge.end);
    }

    // 重写hashCode：基于起点和终点的标签生成哈希值
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    // 重写toString：生成“(起点标签, 终点标签)”格式的字符串
    @Override
    public String toString() {
        return String.format("(%d, %d)", start.getLabel(), end.getLabel());
    }
}




class GraphExplorer {
    // 静态方法：遍历Node数组，收集所有边
    public static Set<Edge> listEdges(Node[] nodes) {
        Set<Edge> edges = new HashSet<>();
        // 遍历每个节点
        for (Node startNode : nodes) {
            // 遍历当前节点的所有邻居（即从当前节点出发的边）
            for (Node endNode : startNode.getNeighbours()) {
                // 创建边对象并加入集合（Set自动去重）
                edges.add(new Edge(startNode, endNode));
            }
        }
        return edges;
    }
}