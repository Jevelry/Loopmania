package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Armour extends Protection {
    
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        super.setType("armour");
    }
    
    public Armour(int level) {
        super(level, 400*(1+(level-1)*15/100));
        super.setType("armour");
    }


    @Override
    public int protect(int damage) {
        //Will implement the percentage reduction
        return 0;
    }
}
