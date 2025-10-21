package Exeicise1.Exercise1.Task5;
public class FireMonster extends MonsterNew {
    private boolean lastDodge = false;
    public FireMonster(int hitPoints, int attackPoints) {
        super("Fire", hitPoints, attackPoints, new String[] { "Water" });
    }
    @Override
    public boolean dodge() {
        return (lastDodge = !lastDodge);
    }
}