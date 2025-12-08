package Example.Collection;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayListTest {
    public static void main(String[] args) {
        // 以 ArrayList 为例（List 子接口）
        Collection<String> listCollection = new ArrayList<>();
        // 1. 添加元素（add()）
        listCollection.add("Java");
        listCollection.add("Python");
        listCollection.add("Java"); // List 允许重复元素
        System.out.println("List集合元素：" + listCollection); // [Java, Python, Java]

        // 2. 判断元素是否存在（contains()）
        boolean hasJava = listCollection.contains("Java");
        System.out.println("是否包含 Java：" + hasJava); // true

        // 3. 移除元素（remove()）
        boolean removed = listCollection.remove("Python");
        System.out.println("移除 Python 成功？" + removed); // true
        System.out.println("移除后元素：" + listCollection); // [Java, Java]

        // 4. 获取元素个数（size()）
        System.out.println("元素个数：" + listCollection.size()); // 2

        // 5. 判断是否为空（isEmpty()）
        System.out.println("是否为空：" + listCollection.isEmpty()); // false
    }
}
