package Example.Exam;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import java.util.Collections;


enum Goods {
    BREAD,
    COAL,
    FISH,
    HELMET,
    IRON,
    PAPER,
    SHIELD,
    SWORD,
    WOOD,
    WOOL
}

class Trade {
    // 三个属性（私有，仅通过get方法访问）
    private final int gems;
    private final int amount;
    private final Goods goods;

    // 公共构造方法：初始化所有属性
    public Trade(int gems, int amount, Goods goods) {
        this.gems = gems;
        this.amount = amount;
        this.goods = goods;
    }

    // 完整的get方法
    public int getGems() {
        return gems;
    }

    public int getAmount() {
        return amount;
    }

    public Goods getGoods() {
        return goods;
    }

    // equals方法：基于三个属性判断相等性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return gems == trade.gems && amount == trade.amount && goods == trade.goods;
    }

    // hashCode方法：与equals逻辑一致，基于三个属性生成哈希值
    @Override
    public int hashCode() {
        return Objects.hash(gems, amount, goods);
    }

    // toString方法：生成类似“1 gem for 3 BREAD”的字符串
    @Override
    public String toString() {
        // 处理gem的单复数（gems=1时用“gem”，否则用“gems”）
        String gemStr = (gems == 1) ? "gem" : "gems";
        return String.format("%d %s for %d %s", gems, gemStr, amount, goods.name());
    }

        // （原有的属性、构造方法、get方法、equals、hashCode、toString不变）

    // 新增的execute方法
    public void execute(Trader trader, Citizen citizen) {
        // 1. 检查当前Trade是否在trader的交易列表中
        List<Trade> traderTrades = trader.getTrades();
        if (!traderTrades.contains(this)) {
            throw new IllegalArgumentException("当前交易不在商人的支持列表中");
        }

        // 2. 调用市民的executeTrade执行交易
        boolean tradeSuccess = citizen.executeTrade(this);

        // 3. 若交易成功，给商人新增随机交易
        if (tradeSuccess) {
            trader.addRandomTrade();
        }
    }
}





class Citizen {
    // 市民的宝石数量
    private int gems;
    // 库存：用Map存储“商品类型→数量”（初始为空）
    private final Map<Goods, Integer> inventory;

    // 构造方法：接收初始宝石数量，初始化库存为空
    public Citizen(int initialGems) {
        this.gems = initialGems;
        this.inventory = new HashMap<>();
    }

    // 1. 返回当前宝石数量
    public int getGems() {
        return gems;
    }

    // 2. 返回指定商品的库存数量（无则返回0）
    public int getAmount(Goods goods) {
        // Map的getOrDefault方法：键存在则返回对应值，否则返回0
        return inventory.getOrDefault(goods, 0);
    }

    // 3. 执行交易：返回是否成功
    public boolean executeTrade(Trade trade) {
        // 步骤1：检查宝石是否足够
        if (this.gems < trade.getGems()) {
            return false; // 宝石不足，交易失败
        }

        // 步骤2：更新宝石数量（扣除交易所需宝石）
        this.gems -= trade.getGems();

        // 步骤3：更新库存（增加交易对应的商品数量）
        Goods tradeGoods = trade.getGoods();
        int currentAmount = getAmount(tradeGoods); // 获取当前库存
        inventory.put(tradeGoods, currentAmount + trade.getAmount()); // 累加后存入Map

        return true; // 交易成功
    }
}




class Trader {
    // 商人的交易列表
    private final List<Trade> trades;
    // 随机数生成器（复用，避免重复创建）
    private final Random random;

    // 构造方法：无参数，初始化时添加一笔随机交易
    public Trader() {
        this.trades = new ArrayList<>();
        this.random = new Random();
        // 初始添加一笔随机交易
        addRandomTrade();
    }

    // 1. 返回当前交易列表
    public List<Trade> getTrades() {
        // 返回列表的“不可修改视图”，避免外部直接修改内部集合（可选，增强封装性）
       return Collections.unmodifiableList(trades);
    }

    // 2. 添加一笔随机交易
    public void addRandomTrade() {
        // 生成随机gems（1-5）：nextInt(5)返回0-4，+1后变为1-5
        int randomGems = random.nextInt(5) + 1;
        // 生成随机amount（1-5）：逻辑同上
        int randomAmount = random.nextInt(5) + 1;
        // 生成随机Goods：从枚举的所有取值中随机选一个
        Goods[] allGoods = Goods.values();
        Goods randomGoods = allGoods[random.nextInt(allGoods.length)];

        // 创建随机交易并加入列表
        Trade newTrade = new Trade(randomGems, randomAmount, randomGoods);
        trades.add(newTrade);
    }
}


