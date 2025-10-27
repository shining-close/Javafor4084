package Example.Inheritance.Test1;

public class TestInheritance {
    public static void main(String[] args) {
        // 创建子类对象
        Student student = new Student("张三", 18, "2023001");

        // 调用继承的属性（通过父类的protected或getter）
        System.out.println("姓名：" + student.name);  // 直接访问protected属性
        System.out.println("年龄：" + student.getAge());  // 通过getter访问父类私有属性

        // 调用继承的方法（已重写）
        student.eat();  // 输出：张三在学校食堂吃饭（执行子类重写的方法）

        // 调用子类新增的方法
        student.study();  // 输出：张三（学号：2023001）在学习
    }
}
