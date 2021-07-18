package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Staff extends Weapon{
    private int tranceChance;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        // super(x, y, level, 700*(1+(level-1)*15/100), 18);
        super(x, y, level, 700*(1+(level-1)*15/100), 18*(1+(level-1)/10));
        tranceChance = 30 + level * 3;
        super.setType("staff");
    }    

    public Staff(int level) {
        super(level, 700*(1+(level-1)*15/100), 18*(1+(level-1)/10));
        tranceChance = 30 + level * 3;
        super.setType("staff");
    }
    


    public boolean castSpell(Enemy enemy, BattleRunner b) {
        int randNum = LoopManiaWorld.getRandNum();

        if (randNum <  tranceChance) {
            enemy.takeDamage(super.getDamage());
            b.convertEnemyToAlly(enemy);
            return true;
        }
        return false;
    }
} 
