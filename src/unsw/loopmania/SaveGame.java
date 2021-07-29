package unsw.loopmania;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

public class SaveGame {
    private LoopManiaWorld world;
    private JSONObject save;

    public SaveGame(LoopManiaWorld world) {
        this.world = world;
        save = new JSONObject();
    }

    public void SaveWorld(String name) {
        saveCharacter();
        saveNonSpecifiedEntities();
        saveEnemies();
        saveCycleBuildings();
        saveMoveBuildings();
        save.put("seed", world.getSeed());
        save.put("healthPotionsBought", world.getHealthPotionsBought());
        save.put("strengthPotionsBought", world.getStrengthPotionsBought());
        save.put("gameMode", world.getSelectedGamemode());

        JSONObject json = world.getJSON();
        json.put("saveWorld", save);

        try {
            FileWriter file = new FileWriter(String.format("backup/%s.json", name));
            file.write(json.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCharacter() {
        Character character = world.getCharacter();
        JSONObject c = new JSONObject();
        c.put("experience", character.getXP().get());
        c.put("gold", character.getGold());
        c.put("cycles", character.getCycles().get());
        c.put("health", character.getHealth());
        c.put("bossKills", character.getBossKills());
        c.put("strengthpotionbuff", character.getStrengthPotionBuff());
        c.put("canTakeDamage", character.canTakeDamage());
        addEquippedWeapon(c);
        addEquippedArmour(c);
        addEquippedHelmet(c);
        addEquippedShield(c);
        addCharacterStats(c, character);
        c.put("aliveSoldiers", character.getAlliedSoldierCount());
        addUnequippedInventory(c, character.getInventory());
        save.put("character", c);
    }

    private void saveNonSpecifiedEntities() {
        JSONArray nonSpecifiedEntities = new JSONArray();
        List<Entity> nonSpecifiedEntitiyList = world.getNonSpecifiedEntities();
        if (nonSpecifiedEntities.isEmpty()) {
            return;
        }
        for (Entity e : world.getNonSpecifiedEntities()) {
            JSONObject entity = new JSONObject();
            entity.put("type", e.getType());
            entity.put("x", e.getX());
            entity.put("y", e.getY());
            nonSpecifiedEntities.put(entity);
        }
        save.put("nonSpecifiedEntities", nonSpecifiedEntities);
    }

    private void saveEnemies() {
        List<Enemy> list = world.getEnemies();
        JSONArray enemies = new JSONArray();
        for (Enemy enemy : list) {
            JSONObject enemyObject = new JSONObject();
            enemyObject.put("type", enemy.getType());
            enemyObject.put("index", enemy.getIndexOfPosition());
            enemies.put(enemyObject);
        }
        save.put("enemies", enemies);
    }

    private void saveCycleBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnCycle> list = world.getCycleBuildings();
        for (BuildingOnCycle building : list) {
            JSONObject buildingObject = new JSONObject();
            buildingObject.put("type", ((StaticEntity)building).getType());
            buildingObject.put("X", ((StaticEntity)building).getX());
            buildingObject.put("Y", ((StaticEntity)building).getY());
            buildings.put(buildingObject);
        }
        save.put("cycleBuildings", buildings);
    }

    private void saveMoveBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnMove> list = world.getMoveBuildings();
        for (BuildingOnMove building : list) {
            JSONObject buildingObject = new JSONObject();
            buildingObject.put("type", ((StaticEntity)building).getType());
            buildingObject.put("X", ((StaticEntity)building).getX());
            buildingObject.put("Y", ((StaticEntity)building).getY());
            buildings.put(buildingObject);
        }
        save.put("moveBuildings", buildings);
    }

    private void addEquippedWeapon(JSONObject c) {
        JSONObject equippedWeapon = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(0);
        equippedWeapon.put("type", item.getType());
        equippedWeapon.put("level", ((Weapon)item).getLevel());
        equippedWeapon.put("x", item.getX());
        equippedWeapon.put("y", item.getY());
        if (item instanceof ConfusedRareItem) {
            equippedWeapon.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
        }
        c.put("equippedWeapon", equippedWeapon);
    }

    private void addEquippedHelmet(JSONObject c) {
        JSONObject equippedHelmet = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(1);
        if (item != null) {
            equippedHelmet.put("type", ((StaticEntity)item).getType());
            equippedHelmet.put("level", ((Protection)item).getLevel());
            equippedHelmet.put("x", ((StaticEntity)item).getX());
            equippedHelmet.put("y", ((StaticEntity)item).getY());
        }
        c.put("equippedHelmet", equippedHelmet);
    }

    private void addEquippedShield(JSONObject c) {
        JSONObject equippedShield = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(2);
        if (item != null) {
            equippedShield.put("type", ((StaticEntity)item).getType());
            equippedShield.put("level", ((Protection)item).getLevel());
            equippedShield.put("x", ((StaticEntity)item).getX());
            equippedShield.put("y", ((StaticEntity)item).getY());
            if (item instanceof ConfusedRareItem) {
                equippedShield.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
            }
        }
        c.put("equippedShield", equippedShield);
    }
    
    private void addEquippedArmour(JSONObject c) {
        JSONObject equippedArmour = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(3);
        if (item != null) {
            equippedArmour.put("type", ((StaticEntity)item).getType());
            equippedArmour.put("level", ((Protection)item).getLevel());
            equippedArmour.put("x", ((StaticEntity)item).getX());
            equippedArmour.put("y", ((StaticEntity)item).getY());
        }
        c.put("equippedArmour", equippedArmour);
    }

    private void addCharacterStats(JSONObject c, Character character) {
        CharacterStats cs = character.getStats();
        JSONObject stats = new JSONObject();
        stats.put("sword", cs.getHighestLevel("sword"));
        stats.put("stake", cs.getHighestLevel("stake"));
        stats.put("staff", cs.getHighestLevel("staff"));
        stats.put("shield", cs.getHighestLevel("shield"));
        stats.put("armour", cs.getHighestLevel("armour"));
        stats.put("helmet", cs.getHighestLevel("helmet"));
        c.put("stats", stats);
    }

    private void addUnequippedInventory(JSONObject character, Inventory inventory) {
        JSONArray items = new JSONArray();
        for (Item item : inventory.getunequippedInventoryItems()) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("type", item.getType());
            if (item instanceof DoggieCoin) {
                itemObject.put("strategy", ((DoggieCoin)item).getStrategy());
            }
            else if (item instanceof ConfusedRareItem) {
                itemObject.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
            }
            else if (item instanceof Protection) {
                itemObject.put("level", ((Protection)item).getLevel());
            }
            else if (item instanceof Weapon) {
                itemObject.put("level", ((Weapon)item).getLevel());
            }
            items.put(itemObject);
        }

        JSONArray cards = new JSONArray();
        for (Card c : inventory.getCards()) {
            JSONObject card = new JSONObject();
            card.put("type", ((StaticEntity)c).getType());
            cards.put(card);
        }

        character.put("unequippedItems", items);
        character.put("cards", cards);
    }
}
