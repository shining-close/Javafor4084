package Example.Enum.Test1;

enum DaysOfTheWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
}


public class EnumTest {
    public static void main(String[] args) {
        DaysOfTheWeek day = DaysOfTheWeek.THURSDAY;
        if (day == DaysOfTheWeek.THURSDAY) {
            System.out.println("Yay they can be put together. ");
        }

        for (DaysOfTheWeek myDay : DaysOfTheWeek.values()) {
            System.out.println(myDay);
        }
        
        System.out.println("\n");

        DaysOfTheWeek day2 = DaysOfTheWeek.valueOf("MONDAY");
        System.out.println("The valueof:" + day2);


        DaysOfTheWeek day3 = DaysOfTheWeek.FRIDAY;
        switch (day3) {
        case MONDAY:
        case TUESDAY:
        case WEDNESDAY:
        case THURSDAY:
        case FRIDAY:
            System.out.println("工作日");
            break;
        case SATURDAY:
        case SUNDAY:
            System.out.println("休息日");
            break;
        }
    }




    
}
