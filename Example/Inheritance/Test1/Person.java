package Example.Inheritance.Test1;

// 父类：Person（含人类类共有的属性和方法）
public class Person {
    // 属性
    protected String name;  // 受保护的属性，子类可访问
    private int age;        // 私有有的属性，子类不可直接访问

    // 构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 方法
    public void eat() {
        System.out.println(name + "在吃饭");
    }

    // getter/setter（供子类子类私有属性age）
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}