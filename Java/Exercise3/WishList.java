package Java.Exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WishList {
    // 内部用List存储积木套装（保证顺序，支持排序）
    private final List<BrickSet> sets;

    // 构造方法：初始化空的愿望清单
    public WishList() {
        this.sets = new ArrayList<>();
    }

    /**
     * 添加积木套装（无重复）
     * @param set 要添加的积木套装
     * @return 成功添加返回true；已存在返回false
     */
    public boolean addSet(BrickSet set) {
        if (!sets.contains(set)) {
            sets.add(set);
            return true;
        }
        return false;
    }

    /**
     * 移除积木套装
     * @param set 要移除的积木套装
     * @return 成功移除返回true；不存在返回false
     */
    public boolean removeSet(BrickSet set) {
        return sets.remove(set);
    }

    /**
     * 获取按编号升序排序的积木套装集合（返回不可修改视图）
     * @return 排序后的积木套装集合
     */
    public Collection<BrickSet> getSets() {
        // 复制列表并排序（避免修改原列表）
        List<BrickSet> sortedSets = new ArrayList<>(sets);
        Collections.sort(sortedSets);
        // 返回不可修改集合，避免外部篡改
        return Collections.unmodifiableCollection(sortedSets);
    }


    public static void main(String[] args) {
        WishList wishList = new WishList();
        BrickSet set1 = new BrickSet(12345, "太空基地", "Space", 500, 99.99);
        BrickSet set2 = new BrickSet(67890, "忍者堡垒", "Ninjas", 300, 59.99);

        wishList.addSet(set1);
        wishList.addSet(set2);
        wishList.addSet(set1); // 重复添加，返回false

        System.out.println("愿望清单（按编号排序）：");
        for (BrickSet set : wishList.getSets()) {
            System.out.println(set);
        }

        wishList.removeSet(set2);
        System.out.println("移除后清单：");
        for (BrickSet set : wishList.getSets()) {
            System.out.println(set);
        }
    }
}