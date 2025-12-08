package Example.Exam.Exam2024;

public class Enclosure {
    // 围栏的属性（私有，仅通过getter访问）
    private final AnimalSize size;
    private final int temperature;
    private final int runningCosts;
    private Animal currentOccupant; // 居住者（初始为null）


    // ========== 任务2a：构造方法（初始化属性，居住者为null） ==========
    public Enclosure(AnimalSize size, int temperature, int runningCosts) {
        this.size = size;
        this.temperature = temperature;
        this.runningCosts = runningCosts;
        this.currentOccupant = null;
    }


    // ========== 任务2a：getter方法（无setter） ==========
    public AnimalSize getSize() {
        return size;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getRunningCosts() {
        return runningCosts;
    }

    public Animal getCurrentOccupant() {
        return currentOccupant;
    }


    // ========== 任务2b：检查围栏与动物的兼容性 ==========
    public boolean checkCompatibility(Animal animal) {
        // 条件1：动物体型 ≤ 围栏体型（需先定义体型的“大小顺序”）
        boolean sizeCompatible = isAnimalSizeAllowed(animal.getSize());
        // 条件2：围栏温度处于动物的舒适区间内
        boolean tempCompatible = (temperature >= animal.getMinTemp()) 
                && (temperature <= animal.getMaxTemp());
        
        return sizeCompatible && tempCompatible;
    }

    // 辅助方法：判断动物体型是否≤围栏体型（定义体型的优先级：SMALL < MEDIUM < LARGE）
    private boolean isAnimalSizeAllowed(AnimalSize animalSize) {
        // 围栏体型的等级（数值越大，等级越高）
        int enclosureLevel = getSizeLevel(this.size);
        // 动物体型的等级
        int animalLevel = getSizeLevel(animalSize);
        return animalLevel <= enclosureLevel;
    }

    // 辅助方法：将AnimalSize转换为数值等级（便于比较）
private int getSizeLevel(AnimalSize size) {
    int level;
    switch (size) {
        case SMALL:
            level = 1;
            break;
        case MEDIUM:
            level = 2;
            break;
        case LARGE:
            level = 3;
            break;
        default:
            // 因为AnimalSize只有这三个枚举值，默认分支实际不会触发，这里避免编译警告
            throw new IllegalArgumentException("不支持的体型：" + size);
    }
    return level;
}


    // ========== 任务2c：添加/移除动物 ==========
    public void addAnimal(Animal animal) {
        // 校验1：围栏是否已有居住者
        if (this.currentOccupant != null) {
            throw new IllegalArgumentException("围栏已有居住者，无法添加新动物");
        }
        // 校验2：动物与围栏是否兼容
        if (!checkCompatibility(animal)) {
            throw new IllegalArgumentException("动物与围栏不兼容，无法添加");
        }
        // 校验通过：设置居住者
        this.currentOccupant = animal;
    }

    public void removeAnimal() {
        // 若围栏为空，则不操作；否则移除居住者
        if (this.currentOccupant != null) {
            this.currentOccupant = null;
        }
    }
}

