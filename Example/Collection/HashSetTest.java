package Example.Collection;

import java.util.HashSet;
import java.util.Collection;

public class HashSetTest {
    public static void main(String[] args) {
    
        // 以 HashSet 为例（Set 子接口）
        Collection<Integer> setCollection = new HashSet<>();
        setCollection.add(1);
        setCollection.add(2);
        setCollection.add(1); // Set 不允许重复元素，此操作无效
        System.out.println("Set集合元素：" + setCollection); // [1, 2]

        // 6. 批量添加（addAll()）
        Collection<Integer> anotherSet = new HashSet<>();
        anotherSet.add(3);
        anotherSet.add(4);
        setCollection.addAll(anotherSet); // 将 anotherSet 的元素添加到当前集合
        System.out.println("批量添加后：" + setCollection); // [1, 2, 3, 4]

        // 7. 批量移除（removeAll()）
        setCollection.removeAll(anotherSet); // 移除当前集合中与 anotherSet 共有的元素
        System.out.println("批量移除后：" + setCollection); // [1, 2]

        // 8. 保留交集（retainAll()）
        Collection<Integer> retainSet = new HashSet<>();
        retainSet.add(1);
        retainSet.add(5);
        setCollection.retainAll(retainSet); // 只保留与 retainSet 共有的元素
        System.out.println("保留交集后：" + setCollection); // [1]

        // 9. 清空集合（clear()）
        setCollection.clear();
        System.out.println("清空后是否为空：" + setCollection.isEmpty()); // true
    }
    
}
