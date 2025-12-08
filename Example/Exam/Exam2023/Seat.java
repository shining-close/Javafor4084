package Example.Exam.Exam2023;

import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

import java.util.List;
import java.util.ArrayList;

public class Seat {

    // TODO complete this class according to the specification

        // 核心属性
    private final char row;
    private final int seatNumber;
    private final SeatType seatType;
    private boolean availability; // 仅该属性有setter
    
    public Seat(char row, int seatNumber, SeatType seatType) {
        // TODO complete this method

                // 2. 校验属性合法性
        validateRow(row);
        validateSeatNumber(seatNumber);

        this.row = row;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.availability = true; // 初始可用
    }

        // ========== 2. 合法性校验方法（私有工具方法） ==========
    // 校验排：必须是大写字母A-Z
    private void validateRow(char row) {
        if (!(row >= 'A' && row <= 'Z')) {
            throw new IllegalArgumentException("排必须是大写字母A到Z，当前值：" + row);
        }
    }

    // 校验座位号：必须是正整数
    private void validateSeatNumber(int seatNumber) {
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("座位号必须是正整数，当前值：" + seatNumber);
        }
    }


    // ========== 3. getter方法（所有属性） + availability的setter ==========
    public char getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

}




class Venue {
    // 内部存储：键为“排（char）”，值为“该排的座位Map（座位号→Seat对象）”
    private final Map<Character, Map<Integer, Seat>> venueSeats;

    // ========== 1. 构造方法：解析配置字符串生成Seat对象 ==========
    public Venue(String config) {
        venueSeats = new HashMap<>();
        String[] lines = config.split("\n"); // 按换行分割字符串

        int rowCount = Integer.parseInt(lines[0]); // 第一行是排数
        for (int i = 0; i < rowCount; i++) {
            char row = (char) ('A' + i); // 排从A开始（A对应i=0，B对应i=1...）
            String[] seatTypes = lines[i + 1].split(" "); // 第i+1行是当前排的座位类型

            // 为当前排创建座位Map
            Map<Integer, Seat> rowSeats = new HashMap<>();
            for (int seatIdx = 0; seatIdx < seatTypes.length; seatIdx++) {
                int seatNum = seatIdx + 1; // 座位号从1开始
                SeatType type = SeatType.valueOf(seatTypes[seatIdx].toUpperCase()); // 解析座位类型
                rowSeats.put(seatNum, new Seat(row, seatNum, type));
            }

            venueSeats.put(row, rowSeats);
        }
    }

    // ========== 2. 根据排和座位号获取Seat对象 ==========
    public Seat getSeat(char row, int seatNum) {
        // 校验排是否存在
        if (!venueSeats.containsKey(row)) {
            throw new IllegalArgumentException("无效的排：" + row);
        }
        Map<Integer, Seat> rowSeats = venueSeats.get(row);

        // 校验座位号是否存在
        if (!rowSeats.containsKey(seatNum)) {
            throw new IllegalArgumentException("排" + row + "中无效的座位号：" + seatNum);
        }

        return rowSeats.get(seatNum);
    }

    // ========== 3. 格式化打印所有座位详情 ==========
    public void printDetails() {
        System.out.println("===== 场馆座位详情 =====");
        // 按排的字母顺序遍历（A→B→...）
        for (char row = 'A'; row <= 'Z'; row++) {
            if (!venueSeats.containsKey(row)) {
                break; // 无更多排则终止
            }
            Map<Integer, Seat> rowSeats = venueSeats.get(row);
            System.out.printf("排 %c：%n", row);

            // 按座位号顺序遍历
            for (int seatNum : rowSeats.keySet().stream().sorted().collect(Collectors.toList())) {
                Seat seat = rowSeats.get(seatNum);
                String status = seat.isAvailability() ? "可用" : "已预订";
                System.out.printf("  座位号 %d | 类型 %s | 状态 %s%n",
                        seatNum, seat.getSeatType(), status);
            }
        }
    }
}




class Event {
    // 活动的核心属性
    private final Venue venue; // 举办场馆
    private final Map<SeatType, Integer> priceStructure; // 票型→价格的映射


    // ========== 1. 构造方法：初始化场馆和价格结构 ==========
    public Event(Venue venue, Map<SeatType, Integer> priceStructure) {
        this.venue = venue;
        this.priceStructure = new HashMap<>(priceStructure); // 深拷贝避免外部修改
    }


    // ========== 2. 预订座位（寻找相邻可用座位） ==========
    public int reserveSeats(int numSeats, SeatType seatType) {
        // 遍历场馆的每一排，寻找符合条件的相邻座位
        for (char row = 'A'; row <= 'Z'; row++) {
            try {
                // 获取当前排的所有座位（按座位号排序）
                List<Seat> rowSeats = getSortedSeatsInRow(row);
                // 寻找当前排中“指定类型、可用、连续numSeats个”的座位
                List<Seat> availableAdjacentSeats = findAdjacentSeats(rowSeats, numSeats, seatType);
                
                if (!availableAdjacentSeats.isEmpty()) {
                    // 找到：标记为已预订，并计算总价
                    int pricePerSeat = priceStructure.get(seatType);
                    int totalPrice = pricePerSeat * numSeats;
                    for (Seat seat : availableAdjacentSeats) {
                        seat.setAvailability(false);
                    }
                    return totalPrice;
                }
            } catch (IllegalArgumentException e) {
                // 当前排不存在，终止遍历
                break;
            }
        }
        // 所有排都未找到符合条件的座位
        return -1;
    }

    // 辅助方法：获取指定排的座位（按座位号升序排序）
private List<Seat> getSortedSeatsInRow(char row) {
    List<Seat> rowSeats = new ArrayList<>();
    int seatNum = 1;
    // 循环遍历当前排的所有座位（直到座位号无效）
    while (true) {
        try {
            // 尝试获取当前排、当前座位号的座位
            Seat seat = venue.getSeat(row, seatNum);
            rowSeats.add(seat);
            seatNum++; // 座位号+1，继续尝试下一个
        } catch (IllegalArgumentException e) {
            // 座位号无效，说明当前排的座位已遍历完，终止循环
            break;
        }
    }
    // 按座位号升序排序后返回
    return rowSeats.stream()
            .sorted((s1, s2) -> Integer.compare(s1.getSeatNumber(), s2.getSeatNumber()))
            .collect(Collectors.toList());
}

    // 辅助方法：在排的座位列表中，寻找“指定类型、可用、连续num个”的座位
    private List<Seat> findAdjacentSeats(List<Seat> rowSeats, int numSeats, SeatType seatType) {
        List<Seat> candidate = new ArrayList<>();
        for (Seat seat : rowSeats) {
            // 检查当前座位是否符合“类型匹配 + 可用”
            if (seat.getSeatType() == seatType && seat.isAvailability()) {
                candidate.add(seat);
                // 若候选列表达到指定数量，返回
                if (candidate.size() == numSeats) {
                    return candidate;
                }
            } else {
                // 中断连续，重置候选列表
                candidate.clear();
            }
        }
        // 未找到足够连续座位
        return new ArrayList<>();
    }


    // ========== 3. 归还座位 ==========
    public void returnSeat(char row, int seatNum) {
        // 1. 校验座位是否存在
        Seat seat;
        try {
            seat = venue.getSeat(row, seatNum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("无效的座位：排" + row + " 座位号" + seatNum);
        }

        // 2. 校验座位是否已预订
        if (seat.isAvailability()) {
            throw new IllegalArgumentException("排" + row + " 座位号" + seatNum + "已处于可用状态");
        }

        // 3. 恢复为可用
        seat.setAvailability(true);
    }
}