package Example.Enum.Test3;

enum DaysOfTheWeek {
    SUNDAY("周日", false),
    MONDAY("周一", true),
    // ... 其他枚举项
    FRIDAY("周五", true),
    SATURDAY("周六", false);

    private final String chineseName; // 中文名称属性
    private final boolean isWorkday;  // 是否工作日属性

    // 构造器（枚举构造器默认private）
    DaysOfTheWeek(String chineseName, boolean isWorkday) {
        this.chineseName = chineseName;
        this.isWorkday = isWorkday;
    }

    // 自定义方法
    public String getChineseName() {
        return chineseName;
    }

    public boolean isWorkday() {
        return isWorkday;
    }
}

public class Test {
    // 调用示例
    public static void main(String[] args) {
        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
            System.out.println(day.getChineseName() + " 是否工作日：" + day.isWorkday());
        }
    }
}
