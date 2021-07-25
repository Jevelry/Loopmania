package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Staff extends Weapon{
    public static final double PRICE = 700.0;
    public static final double DAMAGE = 18.0;
    public static final int BASETRANCECHANCE = 30;

    private int tranceChance;
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, PRICE, DAMAGE);
        tranceChance = BASETRANCECHANCE + level * 3;
        super.setType("staff");
    }    

    public Staff(int level) {
        super(level, PRICE, DAMAGE);
        tranceChance = BASETRANCECHANCE + level * 3;
        super.setType("staff");
    }
    

    /** 
     * Attacks Enemy and potentially cast a spell on them
     * @param enemy
     * @param b
     * @return true if enemy tranced, false otherwise
     */
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
