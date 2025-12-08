package Java.Exercise3;

import java.util.Objects;

public class BrickSet implements Comparable<BrickSet>{
    // 积木套装的属性
    private final int number;       // 编号（不可修改）
    private final String name;      // 名称（不可修改）
    private final String theme;     // 主题（不可修改）
    private final int pieceCount;   // 零件数量（不可修改）
    private double retailPrice;     // 零售价（可修改）

    /**
     * 构造方法：初始化积木套装的所有属性
     * @param number 编号
     * @param name 名称
     * @param theme 主题
     * @param pieceCount 零件数量
     * @param retailPrice 零售价
     */
    public BrickSet(int number, String name, String theme, int pieceCount, double retailPrice) {
        this.number = number;
        this.name = name;
        this.theme = theme;
        this.pieceCount = pieceCount;
        this.retailPrice = retailPrice;
    }

    // ========== Get方法（所有属性） ==========
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    // ========== Set方法（仅零售价） ==========
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 计算单个零件的价格（零售价 ÷ 零件数量）
     * @return 单个零件的价格；若零件数量为0则返回0
     */
    public double getPricePerPiece() {
        if (pieceCount == 0) {
            return 0.0;
        }
        return retailPrice / pieceCount;
    }

    // ========== 重写对象通用方法 ==========
    /**
     * 基于编号判断两个积木套装是否相等（编号唯一标识套装）
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrickSet brickSet = (BrickSet) o;
        return number == brickSet.number;
    }

    /**
     * 基于编号生成哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * 返回积木套装的字符串描述（包含所有属性）
     */
    @Override
    public String toString() {
        return String.format(
            "BrickSet{编号=%d, 名称='%s', 主题='%s', 零件数量=%d, 零售价=%.2f}",
            number, name, theme, pieceCount, retailPrice
        );
    }

        @Override
    public int compareTo(BrickSet other) {
        return Integer.compare(this.number, other.number);
    }


    public static void main(String[] args) {
        BrickSet set = new BrickSet(12345, "太空基地", "Space", 500, 99.99);
        System.out.println(set); // 打印套装信息
        System.out.println("单件价格：" + set.getPricePerPiece()); // 输出单件价格
        set.setRetailPrice(89.99); // 修改零售价
        System.out.println("修改后单件价格：" + set.getPricePerPiece());
    }
}