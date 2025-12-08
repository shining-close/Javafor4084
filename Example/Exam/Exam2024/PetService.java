package Example.Exam.Exam2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PetService {
    // 任务3a：管理的围栏集合（初始为空）
    private final List<Enclosure> enclosures;

    public PetService() {
        this.enclosures = new ArrayList<>();
    }


    // ========== 任务3b：添加围栏 & 打印所有围栏 ==========
    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    public void printAllEnclosures() {
        System.out.println("===== 宠物护理服务的围栏列表 =====");
        for (Enclosure enclosure : enclosures) {
            // 打印围栏信息 + 当前居住者信息
            String occupantInfo = (enclosure.getCurrentOccupant() == null) 
                    ? "无居住者" 
                    : enclosure.getCurrentOccupant().toString();
            System.out.printf(
                "围栏：体型=%s，温度=%d，日成本=%d，当前居住者：%s%n",
                enclosure.getSize(), enclosure.getTemperature(),
                enclosure.getRunningCosts(), occupantInfo
            );
        }
    }


    // ========== 任务3c：为动物分配最优围栏 ==========
    public boolean allocateAnimal(Animal animal) {
        // 步骤1：筛选符合条件的围栏（兼容动物 + 无居住者）
        List<Enclosure> eligibleEnclosures = enclosures.stream()
                .filter(enclosure -> enclosure.checkCompatibility(animal)) // 满足动物的体型/温度要求
                .filter(enclosure -> enclosure.getCurrentOccupant() == null) // 无居住者
                .collect(Collectors.toList());

        // 步骤2：若没有符合条件的围栏，返回false
        if (eligibleEnclosures.isEmpty()) {
            return false;
        }

        // 步骤3：找到成本最低的围栏
        Enclosure cheapestEnclosure = eligibleEnclosures.stream()
                .min((e1, e2) -> Integer.compare(e1.getRunningCosts(), e2.getRunningCosts()))
                .orElse(null); // 此处不会为null（因为eligibleEnclosures非空）

        // 步骤4：将动物添加到该围栏
        cheapestEnclosure.addAnimal(animal);
        return true;
    }


    // ========== 任务3d：移除动物 ==========
    public void removeAnimal(Animal animal) {
        // 查找包含该动物的围栏
        Optional<Enclosure> enclosureWithAnimal = enclosures.stream()
                .filter(enclosure -> animal.equals(enclosure.getCurrentOccupant()))
                .findFirst();

        // 若找到则移除；否则抛异常
        if (enclosureWithAnimal.isPresent()) {
            enclosureWithAnimal.get().removeAnimal();
        } else {
            throw new IllegalArgumentException("系统中不存在该动物：" + animal);
        }
    }
}
