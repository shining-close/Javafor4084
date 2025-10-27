package Example.Abstract;


abstract class Shape {

    protected String color;

    public Shape(String color) { this.color = color; }

    public abstract double calculateArea();

    public void printInfo() { System.out.println("颜色：" + color); }

}

class Circle extends Shape {
    private double radius;
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    @Override
    public double calculateArea() { return Math.PI * radius * radius; }
}

public class Test {
    public static void main (String[] args) {

        Circle b = new Circle(null, 6);

        b.color = "red";

        b.printInfo();

        System.out.println(b.calculateArea());
    }   
}
