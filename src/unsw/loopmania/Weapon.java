package unsw.loopmania;

/**
 * Base class for all weapons
 */
public abstract class Weapon extends StaticEntity {
    private int level;
    private int damage;
    private int price;

    /**
     * 
     * @param x
     * @param y
     * @param level
     * @param price
     * @param damage
     */
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, int price, int damage) {
        super(x,y);
        this.level = level;
        this.price = price;
        this.damage = damage;
    }

    /**
     * Deals damage with this weapon to the given enemy, also deals with addition 
     * effects for special weapons
     * @param enemy that is being attacked
     * @return boolean that is true if the enemy was killed
     */
    public boolean dealDamage(Enemy enemy) {
        return true;
    }
}
