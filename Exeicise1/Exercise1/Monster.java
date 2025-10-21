import java.util.Scanner;

public class Monster {

    private String type;
    private int hit;
    private int attack;
    private String[] weaknesses;

    public Monster(String type, int hit, int attack, String[] weaknesses) {
        this.type = type;
        this. hit = hit;
        this.attack = attack;
        this.weaknesses = weaknesses;
    }

    public String getType() {
        return type;
    }
    public void setType (String type) {
        this.type = type;
    }

    public int getHit() {
        return hit;
    }
    public void setHit (int hit) {
        this.hit = hit;
    }

    public int getAttack() {
        return attack;
    }
    public void setAttack (int attack) {
        this.attack = attack;
    }

    public String[] getWeaknesses() {
        return weaknesses;
    }
    public void setWeaknesses (String[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    public boolean isWeakAgainst(String otherType) {
        for (i=0; i<this.weaknesses.length(); i++) {
            if (otherType.equals(this.weaknesses[i])) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void removeHitPoints (int pointsToRemove) {
        hit_points = this.getHit();
        hit = hit_points - pointsToRemove;
        this.setHit(hit);
    }

    public boolean attack (Monster otherMonster) {
        if (otherMonster == this.type) {
            return false;
        } else if (otherMonster.getHit() == 0 || this.getHit() == 0) {
            return false;
        } else if (otherMonster.isWeakAgainst() == true) {
            this.removeHitPoints(otherMonster.getAttack())
        } else if (otherMonster.isWeakAgainst() == false) {
             this.removeHitPoints(otherMonster.getAttack() + 20)
        }

    }

    public boolean dodge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dodge'");
    }


 


}