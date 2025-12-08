package Example.Exam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


import java.util.*;
import java.util.stream.Collectors;


public class Exam2019 {
    
}


/**
 * 表示游戏角色的能力类型。
 */
enum Power {
    CLONING,
    COMPUTER,
    ENERGY_BLAST,
    FLIGHT,
    INVINCIBILITY,
    MAGIC,
    MAGNETISM,
    SCIENCE,
    SMALL,
    SPEED,
    STRENGTH,
    TELEPATHY,
    TRANSFORMATION,
    WEAPONS,
    WATER
}




/**
 * 表示电子游戏中的可玩角色，包含名字、成本和能力集合，且对象不可变。
 */
class GameCharacter {
    /** 角色的名字 */
    private final String name;
    /** 角色的购买成本 */
    private final int cost;
    /** 角色的能力集合（不可修改） */
    private final Set<Power> powers;

    /**
     * 构造一个不可变的游戏角色对象。
     * @param name 角色的名字
     * @param cost 角色的购买成本
     * @param powers 角色的能力集合（可变参数）
     */
    public GameCharacter(String name, int cost, Power... powers) {
        this.name = name;
        this.cost = cost;
        // 将可变参数转为不可修改的Set，保证不可变性
        Set<Power> powerSet = new HashSet<>();
        Collections.addAll(powerSet, powers);
        this.powers = Collections.unmodifiableSet(powerSet);
    }

    /**
     * 获取角色的名字。
     * @return 角色的名字
     */
    public String getName() {
        return name;
    }

    /**
     * 获取角色的购买成本。
     * @return 角色的购买成本
     */
    public int getCost() {
        return cost;
    }

    /**
     * 获取角色的能力集合（返回不可修改的视图）。
     * @return 角色的能力集合
     */
    public Set<Power> getPowers() {
        return powers;
    }

    /**
     * 判断两个角色是否相等（基于名字、成本和能力集合）。
     * @param o 要比较的对象
     * @return 若相等则返回true，否则返回false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter that = (GameCharacter) o;
        return cost == that.cost &&
                Objects.equals(name, that.name) &&
                Objects.equals(powers, that.powers);
    }

    /**
     * 生成角色的哈希值（基于名字、成本和能力集合）。
     * @return 角色的哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, cost, powers);
    }

    /**
     * 返回角色的字符串表示（包含名字、成本和能力）。
     * @return 角色的描述字符串
     */
    @Override
    public String toString() {
        return String.format("GameCharacter{name='%s', cost=%d, powers=%s}",
                name, cost, powers);
    }
}



class Player {
    // （保留任务3中的字段、构造方法、addCharacter、getOwnedCharacters）

    /**
     * 选择能覆盖所需能力的角色集合。
     * @param neededPowers 关卡所需的能力集合（可变参数）
     * @return 能覆盖所有能力的角色集合；若无法覆盖则返回null
     */
    public Set<GameCharacter> chooseCharacters(Power... neededPowers) {
        // 1. 转换所需能力为Set，方便后续判断
        Set<Power> requiredPowers = new HashSet<>(Arrays.asList(neededPowers));
        if (requiredPowers.isEmpty()) {
            return new HashSet<>(); // 无需求则返回空集合
        }

        // 2. 遍历玩家的角色，尝试选择能覆盖能力的组合
        Set<GameCharacter> selected = new HashSet<>();
        Set<Power> covered = new HashSet<>();

        for (GameCharacter character : ownedCharacters) {
            // 收集当前角色能补充的“未覆盖能力”
            Set<Power> newPowers = character.getPowers().stream()
                    .filter(p -> !covered.contains(p))
                    .collect(Collectors.toSet());
            
            if (!newPowers.isEmpty()) {
                selected.add(character);
                covered.addAll(newPowers);
                
                // 若已覆盖所有所需能力，返回当前选择
                if (covered.containsAll(requiredPowers)) {
                    return selected;
                }
            }
        }

        // 遍历结束后仍未覆盖所有能力，返回null
        return covered.containsAll(requiredPowers) ? selected : null;
    }
}