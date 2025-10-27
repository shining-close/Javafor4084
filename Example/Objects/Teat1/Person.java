package Example.Objects.Teat1;

// 定义Person类（模板）
public class Person {
    // 属性（成员变量）
    private String name;  // 姓名（私有，通过getter/setter访问）
    private int age;      // 年龄

    // 构造方法（用于创建对象时初始化属性）
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 无参构造方法（可选，方便灵活创建对象）
    public Person() {}

    // 行为（方法）
    public void sayHello() {
        System.out.println("大家好，我是" + name + "，今年" + age + "岁");
    }

    // getter/setter方法（用于访问私有属性）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}