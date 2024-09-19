package me.helleo.cwp.items.weapons;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public abstract class WeaponBase {

    public static ShapedRecipe getWeaponRecipe(String weapon, String namekey, ItemStack item, Material material){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, namekey);
        CombatWeaponryPlus.keys.add(key);
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

    protected static String setName(Material material, String Weapon){
        String material_string;
        switch (material){
            case NETHERITE_INGOT:
                material_string = "Netherite";
                break;
            case DIAMOND:
                material_string = "Diamond";
                break;
            case EMERALD:
                material_string = "Emerald";
                break;
            case GOLD_INGOT:
                material_string = "Golden";
                break;
            case IRON_INGOT:
                material_string = "Iron";
                break;
            case COBBLESTONE:
                material_string = "Stone";
                break;
            case OAK_WOOD:
                material_string = "Wooden";
                break;
            default:
                material_string = "Null";
                break;
        }
        return material_string+" "+Weapon;
    }

    private static String[] getCleaverShape(){
        return new String[]{" MM", "MM ", "S  "};
    }

    private static String[] getKatanaShape(){
        return new String[]{"  M", " M ", "S  "};
    }

    private static String[] getKnifeShape(){
        return new String[]{"   ", " M ", " S "};
    }

    private static String[] getLongswordShape(){
        return new String[]{" M ", " M ", "MSM"};
    }

    private static String[] getRapierShape(){
        return new String[]{"  M", "MM ", "SM "};
    }

    private static String[] getSaberShape(){
        return new String[]{" MM", " M ", "S  "};
    }

    private static String[] getScytheShape(){
        return new String[]{"MMM", "  S", "  S"};
    }

    private static String[] getSpearShape(){
        return new String[]{" MM", " SM", "S  "};
    }

}
