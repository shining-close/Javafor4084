package Example.Interface;

interface Flyable {
    void fly(); // 抽象方法（默认public abstract）
    default void display() { System.out.println("我能飞行"); } // 默认方法
}
class Bird implements Flyable {
    @Override
    public void fly() { System.out.println("鸟儿扇动翅膀飞行"); }
}
class Plane implements Flyable {
    @Override
    public void fly() { System.out.println("飞机靠引擎飞行"); }
}

public class Test {    public static void main (String[] args) {

        Bird a = new Bird();
        a.fly();
        a.display();

        Plane b = new Plane();
        b.fly();
        b.display();
    }
}
