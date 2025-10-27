package Task5;
public class ElectricMonster extends MonsterNew {
    public ElectricMonster(int hitPoints, int attackPoints) {
        super("Electric", hitPoints, attackPoints, new String[0]);
    }
    @Override
    public boolean dodge() {
        return false;
    }
}