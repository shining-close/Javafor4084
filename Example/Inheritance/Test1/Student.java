package Example.Inheritance.Test1;

// 子类：Student（继承Person）
public class Student extends Person {
    // 子类新增属性
    private String studentId;  // 学号

    // 子类构造方法（必须先通过super()调用父类构造方法）
    public Student(String name, int age, String studentId) {
        super(name, age);  // 调用父类的有参构造方法，初始化name和age
        this.studentId = studentId;
    }

    // 子类新增方法
    public void study() {
        System.out.println(name + "（学号：" + studentId + "）在学习");
    }

    // 重写父类的方法（重写实现）
    @Override  // 注解解：标识这是重写的方法，编译器会校验
    public void eat() {
        System.out.println(name + "在学校食堂吃饭");  // 子类特有的实现
    }
}
