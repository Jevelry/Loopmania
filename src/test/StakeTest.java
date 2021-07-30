package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Stake;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;


public class StakeTest {
    private BattleRunner b = new BattleRunner();
    @Test
    public void StakeLevelStoredTest() {
        Character c = new Character();
        Item stake = new Stake(3);

        assertEquals(1, c.getHighestLevel(stake));
        c.equip(stake);
        assertEquals(3, c.getHighestLevel(stake));
    }

    @Test
    public void StakeDamage() {
        Character c = new Character();
        Item stake = new Stake(1);

        c.equip(stake);
        Enemy slug = new Slug();
        assertEquals(slug.getHealth(), 50);
        c.attack(slug, b);
        assertEquals(slug.getHealth(), 30);
        Enemy vampire = new Vampire();
        assertEquals(150, vampire.getHealth());
        c.attack(vampire, b);
        assertEquals(95, vampire.getHealth());
    }

    @Test
    public void compareDamages() {
        Weapon stake = new Stake(1);
        Double damage = stake.getDamage();
        Double initialDamage = damage;
        for (int i = 2; i <= 10; i++) {
            Weapon nextStake= new Stake(i);
            assertEquals(nextStake.getDamage(), initialDamage * Math.pow(1.1, i - 1));
            damage = nextStake.getDamage();
        }
    }
}
