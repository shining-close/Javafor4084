package Example.Objects.Teat1;

public class TestPerson {
    public static void main(String[] args) {
        // 1. 创建对象（有参构造）
        Person person1 = new Person("张三", 20);
        
        // 2. 创建对象（无参构造 + setter赋值）
        Person person2 = new Person();
        person2.setName("李四");
        person2.setAge(25);
        
        // 3. 使用对象：调用方法
        person1.sayHello();  // 大家好，我是张三，今年20岁
        person2.sayHello();  // 大家好，我是李四，今年25岁
        
        // 4. 使用对象：访问和修改属性
        System.out.println(person1.getName() + "的年龄：" + person1.getAge());  // 张三的年龄：20
        person1.setAge(21);
        System.out.println(person1.getName() + "的新年龄：" + person1.getAge());  // 张三的新年龄：21
    }
}
