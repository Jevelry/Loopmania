package unsw.loopmania.GoalCalculator;

import unsw.loopmania.Heroes.Character;

public class CompositeBoss implements Composite{
    private static final int MAXBOSSKILLS = 2;
    private Character character;
    public CompositeBoss(Character character) {
        this.character = character;
    }
    @Override
    public boolean getValue() {
        return character.getBossKills().get() == MAXBOSSKILLS;
    }

    @Override
    public int getMax(String type) {
        return MAXBOSSKILLS;
    }
    
}
