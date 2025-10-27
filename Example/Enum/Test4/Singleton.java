package Example.Enum.Test4;

public enum Singleton {
    INSTANCE; // 唯一实例

    public void doSomething() {
        System.out.println("单例方法执行");
    }
}
