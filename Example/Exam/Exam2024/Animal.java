package Example.Exam.Exam2024;


import java.util.Objects;

public class Animal {
    // 动物的属性
    private String name;
    private AnimalSize size;
    private int minTemp; // 舒适温度下界
    private int maxTemp; // 舒适温度上界


    // ========== 任务1a：构造方法（初始化所有属性） ==========
    public Animal(String name, AnimalSize size, int minTemp, int maxTemp) {
        // 调用校验方法（任务1c的逻辑）
        validateName(name);
        validateTemperatureRange(minTemp, maxTemp);
        
        this.name = name;
        this.size = size;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }


    // ========== 任务1b：getter和setter方法 ==========
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name); // 修改时也校验
        this.name = name;
    }

    public AnimalSize getSize() {
        return size;
    }

    public void setSize(AnimalSize size) {
        this.size = size;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        validateTemperatureRange(minTemp, this.maxTemp); // 联动校验上下界
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        validateTemperatureRange(this.minTemp, maxTemp); // 联动校验上下界
        this.maxTemp = maxTemp;
    }


    // ========== 任务1b：重写toString() ==========
    @Override
    public String toString() {
        return String.format("Animal{name='%s', size=%s, 舒适温度=[%d, %d]}",
                name, size, minTemp, maxTemp);
    }


    // ========== 任务1b：重写equals()和hashCode() ==========
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        // 按要求：名字和体型都相同则视为相等
        return Objects.equals(name, animal.name) && size == animal.size;
    }

    @Override
    public int hashCode() {
        // 与equals逻辑一致，基于名字和体型生成哈希值
        return Objects.hash(name, size);
    }


    // ========== 任务1c：合法性校验方法（私有工具方法） ==========
    // 校验名字：长度≥3
    private void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("动物名字长度至少为3个字符");
        }
    }

    // 校验温度区间：0≤min≤max≤50
    private void validateTemperatureRange(int min, int max) {
        if (min < 0 || max > 50 || min > max) {
            throw new IllegalArgumentException("舒适温度区间需满足：0≤下界≤上界≤50");
        }
    }
}
