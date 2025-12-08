package Example.Collection.List;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        // 添加元素
        fruits.add("苹果");
        fruits.add("香蕉");
        fruits.add(1, "橙子"); // 在索引1处插入元素（原有元素后移）
        System.out.println("List元素：" + fruits); // [苹果, 橙子, 香蕉]

        // 索引访问
        String first = fruits.get(0); // 获取索引0的元素
        System.out.println("第一个元素：" + first); // 苹果

        // 修改元素
        fruits.set(2, "葡萄"); // 替换索引2的元素
        System.out.println("修改后：" + fruits); // [苹果, 橙子, 葡萄]

        // 截取子列表（注意：子列表是原列表的视图，修改会影响原列表）
        List<String> subList = fruits.subList(0, 2); // 从索引0到2（不包含2）
        System.out.println("子列表：" + subList); // [苹果, 橙子]

        // 遍历方式1：for循环（利用索引）
        System.out.println("for循环遍历：");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }

        // 遍历方式2：增强for循环
        System.out.println("增强for循环遍历：");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
