
package unsw.loopmania;


public class Zombie extends Enemy {
    
    public Zombie (PathPosition position) {
        super(position, 2, 2, 0, 250, 100);
        super.setType("zombie");
    }

    public Zombie() {
        super(2, 2, 0, 250, 100);
        super.setType("zombie");

    }




    @Override
    public void attack(Hero h, BattleRunner b) {
        if (h instanceof AlliedSoldier) {
            int randomInt = LoopManiaWorld.getRandNum();
            if (randomInt >= 0 && randomInt <= 9) {
                h.setHealth(0);
                b.convertAllyToZombie((AlliedSoldier)h);
            } else {
                h.takeDamage(this.getAttackDamage());
            }

        } else {
            h.takeDamage(this.getAttackDamage());
        }
        
    }
}
