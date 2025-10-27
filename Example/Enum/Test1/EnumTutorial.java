package Example.Enum.Test1;


public class EnumTutorial {
    public static void main(String[] args) {
        DaysOfTheWeek day = DaysOfTheWeek.THURSDAY;
        if (day == DaysOfTheWeek.THURSDAY) {
            System.out.println("Yay it's almost ");
        }

        for (DaysOfTheWeek myDay : DaysOfTheWeek.values()) {
            System.out.println(myDay);
        }
    }


}
