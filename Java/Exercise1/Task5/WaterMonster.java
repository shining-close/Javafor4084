package Task5;

public class WaterMonster extends MonsterNew {
    public WaterMonster(int hitPoints, int attackPoints) {
        super("Water", hitPoints, attackPoints, new String[] { "Fire", "Electric" } );
        }
        
        @Override
        public boolean dodge() {
            return (hitPoints >= 100);
        }
}