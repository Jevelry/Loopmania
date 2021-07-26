package unsw.loopmania;

public class SurvivalShopStrategy implements ShopStrategy{
    private boolean available;
    private Character character;

    public SurvivalShopStrategy(Character character) {
        available = true;
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem instanceof HealthPotion) {
            available = false;
        }
    }

    @Override
    public void restock() {
        available = true;
        
    }

    @Override
    public Boolean getAvailable(Item item) {
        if (item instanceof HealthPotion) {
            return available && character.getGold() >= item.getPrice();
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }
}
