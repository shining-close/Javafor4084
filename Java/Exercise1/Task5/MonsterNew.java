package Task5;
/*
Task 5
Based on the Monster class created above, you should refactor it into a set of classes that are able
to represent every type of monster as its own class, instead of using the type field to distinguish
them. The information that is common to all monsters will be retained in the Monster class, which
will be made abstract, while other information that is relevant only to one of the monster types will
be put into the appropriate subclass. The subtypes should be WaterMonster, FireMonster, and
ElectricMonster, with the following properties:
- A FireMonster has type "Fire" and is weak against Water
- A WaterMonster has type "Water" and is weak against Fire and Electric
- An ElectricMonster has type "Electric" and has no weaknesses

dodge()
Add an abstract dodge() method to the parent Monster class – this method should return a boolean
value and will be implemented in the subclasses to implement the modified attack() behaviour
described below. The method should have a protected access modifier.
The required behaviour for dodge() in each subclass is as follows – you should add any necessary
fields to each subclass to implement this behaviour:
- FireMonster: this method should alternatively return true and false – that is, the first time it
is called, it should return true, the next call should return false, and so on
- WaterMonster: this method should return true if the monster's hit points are at least 100,
and false if they are less than 100.
- ElectricMonster: this method should always return false – that is, an electric monster should
never dodge when attacked.

attack()
The final piece of refactoring is to modify the attack() method of Monster to use dodge() as follows:
- First, call dodge() on the monster being attacked.
- If the result is false, the attack behaviour as before is implemented.
- If the result is true, no hit points are removed from the monster being attacked, but 10 hit
points are removed from the monster doing the attacking. The same rules apply here – if the
monster's HP goes below zero, then it should be set to zero.

 */

import java.util.Arrays;


public abstract class MonsterNew {
protected String type;
protected int hitPoints;
protected int attackPoints;
protected String[] weaknesses;

public MonsterNew(String type, int hitPoints, int attackPoints, String[] weaknesses) {
this.type = type;
this.hitPoints = hitPoints;
this.attackPoints = attackPoints;
this.weaknesses = weaknesses;
}
// Getters and setters
public int getHitPoints() {
return hitPoints;
}
public int getAttackPoints() {
return attackPoints;
}
public String getType() {
return this.type;
}

public boolean attack(MonsterNew otherMonster) {
    // A monster cannot attack itself
    if (otherMonster == this) {
        return false;
    }
    
    // A monster cannot attack or be attacked if it is knocked out
    if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
        return false;
    }
    
    if (otherMonster.dodge()) {
        this.removeHitPoints(10);
        return false;
    } else {
        // Check if the other monster is weak against our type
        boolean otherIsWeak = otherMonster.isWeakAgainst(type);
        int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
        otherMonster.removeHitPoints(pointsToRemove);
        return true;
    }
}

public abstract boolean dodge();

public boolean isWeakAgainst(String otherType) {
    for (String weakness : this.weaknesses) {
        if (weakness.equals(otherType)) {
            return true;
        }
    }
    return false;
}


private void removeHitPoints(int points) {
    this.hitPoints -= points;
    if (hitPoints <= 0) {
        // Monster is knocked out
        hitPoints = 0;
    }
}


    @Override
    public String toString() {
        return "Monster [type=" + type + ", hitPoints=" + hitPoints + ",attackPoints=" + attackPoints + ", weaknesses=" + Arrays.toString(weaknesses) + "]";
    }
}


