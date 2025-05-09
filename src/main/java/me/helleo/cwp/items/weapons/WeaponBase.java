package me.helleo.cwp.items.weapons;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getConfig;

public abstract class WeaponBase {

    protected enum weaponsGeneralDescriptions{
        WhenInMainHand(ConfigLoader.getLang().getString("When_In_Main_Hand")),
        AttackDamage(ConfigLoader.getLang().getString("Attack_Damage")),
        AttackSpeed(ConfigLoader.getLang().getString("Attack_Speed"));

        private final String description_value;

        weaponsGeneralDescriptions(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    public static ShapedRecipe getWeaponRecipe(String weapon, String namekey, ItemStack item, Material material){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), namekey);
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(getWeaponShape(weapon));

        recipe.setIngredient('M', material);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private static String[] getWeaponShape(String type){
        switch (type.toLowerCase()){
            case "katana":
                return getKatanaShape();
            case "cleaver":
                return getCleaverShape();
            case "knife":
                return getKnifeShape();
            case "longsword":
                return getLongswordShape();
            case "rapier":
                return getRapierShape();
            case "saber":
                return getSaberShape();
            case "scythe":
                return getScytheShape();
            case "spear":
                return getSpearShape();
            default:
                return null;
        }
    }

    protected static Double getCustomDamage(String path){return getConfig().getDouble(path+".Damage");}

    protected static Double getCustomSpeed(String path){
        return getConfig().getDouble(path+".Speed");
    }

    protected static Double getCustomMoveSpeed(String path){
        return getConfig().getDouble(path+".MoveSpeed");
    }


    private static String[] getCleaverShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("CleaverCrafting").get(0),
                ConfigLoader.getConfig().getStringList("CleaverCrafting").get(1),
                ConfigLoader.getConfig().getStringList("CleaverCrafting").get(2)
                /*" MM",
                "MM ",
                "S  "*/};
    }

    private static String[] getKatanaShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("KatanaCrafting").get(0),
                ConfigLoader.getConfig().getStringList("KatanaCrafting").get(1),
                ConfigLoader.getConfig().getStringList("KatanaCrafting").get(2),
                /*"  M",
                " M ",
                "S  "*/};
    }

    private static String[] getKnifeShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("KnifeCrafting").get(0),
                ConfigLoader.getConfig().getStringList("KnifeCrafting").get(1),
                ConfigLoader.getConfig().getStringList("KnifeCrafting").get(2),
                /*"   ",
                " M ",
                " S "*/};
    }

    private static String[] getLongswordShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("LongswordCrafting").get(0),
                ConfigLoader.getConfig().getStringList("LongswordCrafting").get(1),
                ConfigLoader.getConfig().getStringList("LongswordCrafting").get(2),
                /*" M ",
                " M ",
                "MSM"*/};
    }

    private static String[] getRapierShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("RapierCrafting").get(0),
                ConfigLoader.getConfig().getStringList("RapierCrafting").get(1),
                ConfigLoader.getConfig().getStringList("RapierCrafting").get(2),
                /*"  M",
                "MM ",
                "SM "*/};
    }

    private static String[] getSaberShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("SaberCrafting").get(0),
                ConfigLoader.getConfig().getStringList("SaberCrafting").get(1),
                ConfigLoader.getConfig().getStringList("SaberCrafting").get(2)
                /*" MM",
                " M ",
                "S  "*/};
    }

    private static String[] getScytheShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("ScytheCrafting").get(0),
                ConfigLoader.getConfig().getStringList("ScytheCrafting").get(1),
                ConfigLoader.getConfig().getStringList("ScytheCrafting").get(2),
                /*"MMM",
                "  S",
                "  S"*/};
    }

    private static String[] getSpearShape(){
        return new String[]{
                ConfigLoader.getConfig().getStringList("SpearCrafting").get(0),
                ConfigLoader.getConfig().getStringList("SpearCrafting").get(1),
                ConfigLoader.getConfig().getStringList("SpearCrafting").get(2),
                /*" MM",
                " SM",
                "S  "*/};
    }

    protected static List<String> setLore(List<String> lore,Double damage, Double speed){
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"+weaponsGeneralDescriptions.WhenInMainHand.getValue()+":"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+damage+" "+weaponsGeneralDescriptions.AttackDamage.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+String.format("%.1f",speed+4).replace(',','.')+" "+weaponsGeneralDescriptions.AttackSpeed.getValue()));
        return lore;
    }
}
