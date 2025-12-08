package Example.Collection.Set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        // 1. HashSet（无序、去重）
        Set<String> hashSet = new HashSet<>();
        hashSet.add("张三");
        hashSet.add("李四");
        hashSet.add("张三"); // 重复元素，添加失败
        System.out.println("HashSet元素（无序）：" + hashSet); // [张三, 李四]（顺序可能不同）

        // 2. TreeSet（自动排序）
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        System.out.println("TreeSet元素（排序）：" + treeSet); // [1, 2, 3]

        // 遍历：增强for循环（Set无索引，不能用普通for循环）
        System.out.println("遍历TreeSet：");
        for (Integer num : treeSet) {
            System.out.println(num);
        }
    }
}