package fr.zayzx.zayzlibs.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemAction {

    // ========== CRÉATION D'ITEMS ==========


    public static ItemStack createItem(Item item, int count) {
        if (item == null) return null;
        return new ItemStack(item, count);
    }


    public static ItemStack createItem(Item item, int count, int metadata) {
        if (item == null) return null;
        return new ItemStack(item, count, metadata);
    }


    public static ItemStack cloneItem(ItemStack stack) {
        if (stack == null) return null;
        return stack.copy();
    }

    // ========== ENCHANTEMENTS ==========


    public static void addEnchantment(ItemStack stack, Enchantment enchantment, int level) {
        if (stack == null || enchantment == null) return;
        stack.addEnchantment(enchantment, level);
    }


    public static void removeEnchantment(ItemStack stack, Enchantment enchantment) {
        if (stack == null || enchantment == null) return;
        Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        enchantments.remove(enchantment.effectId);
        EnchantmentHelper.setEnchantments(enchantments, stack);
    }


    public static void removeAllEnchantments(ItemStack stack) {
        if (stack == null) return;
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            nbt.removeTag("ench");
        }
    }


    public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        if (stack == null || enchantment == null) return 0;
        return EnchantmentHelper.getEnchantmentLevel(enchantment.effectId, stack);
    }


    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment) {
        return getEnchantmentLevel(stack, enchantment) > 0;
    }


    public static Map<Integer, Integer> getAllEnchantments(ItemStack stack) {
        if (stack == null) return null;
        return EnchantmentHelper.getEnchantments(stack);
    }


    public static void addRandomEnchantments(ItemStack stack, int count, Random random) {
        if (stack == null || random == null) return;
        for (int i = 0; i < count; i++) {
            Enchantment enchantment = Enchantment.enchantmentsList[random.nextInt(Enchantment.enchantmentsList.length)];
            if (enchantment != null && enchantment.canApply(stack)) {
                int level = 1 + random.nextInt(enchantment.getMaxLevel());
                addEnchantment(stack, enchantment, level);
            }
        }
    }


    public static ItemStack createEnchantedBook(Enchantment enchantment, int level) {
        ItemStack book = new ItemStack(Items.enchanted_book);
        Items.enchanted_book.addEnchantment(book, new net.minecraft.enchantment.EnchantmentData(enchantment, level));
        return book;
    }

    // ========== NOM ET LORE ==========


    public static void setDisplayName(ItemStack stack, String name) {
        if (stack == null || name == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
        display.setString("Name", name);
        stack.getTagCompound().setTag("display", display);
    }


    public static String getDisplayName(ItemStack stack) {
        if (stack == null) return "";
        return stack.getDisplayName();
    }


    public static boolean hasDisplayName(ItemStack stack) {
        if (stack == null) return false;
        return stack.hasDisplayName();
    }


    public static void removeDisplayName(ItemStack stack) {
        if (stack == null || !stack.hasTagCompound()) return;
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.hasKey("display")) {
            NBTTagCompound display = nbt.getCompoundTag("display");
            display.removeTag("Name");
            if (display.hasNoTags()) {
                nbt.removeTag("display");
            }
        }
    }


    public static void setLore(ItemStack stack, List<String> lore) {
        if (stack == null || lore == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
        NBTTagList loreList = new NBTTagList();
        for (String line : lore) {
            loreList.appendTag(new NBTTagString(line));
        }
        display.setTag("Lore", loreList);
        stack.getTagCompound().setTag("display", display);
    }


    public static void addLoreLine(ItemStack stack, String line) {
        if (stack == null || line == null) return;
        List<String> lore = getLore(stack);
        lore.add(line);
        setLore(stack, lore);
    }


    public static List<String> getLore(ItemStack stack) {
        List<String> lore = new ArrayList<String>();
        if (stack == null || !stack.hasTagCompound()) return lore;
        
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.hasKey("display")) {
            NBTTagCompound display = nbt.getCompoundTag("display");
            if (display.hasKey("Lore")) {
                NBTTagList loreList = display.getTagList("Lore", 8);
                for (int i = 0; i < loreList.tagCount(); i++) {
                    lore.add(loreList.getStringTagAt(i));
                }
            }
        }
        return lore;
    }


    public static void removeLore(ItemStack stack) {
        if (stack == null || !stack.hasTagCompound()) return;
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.hasKey("display")) {
            NBTTagCompound display = nbt.getCompoundTag("display");
            display.removeTag("Lore");
        }
    }

    // ========== DURABILITÉ ==========


    public static int getDamage(ItemStack stack) {
        if (stack == null) return 0;
        return stack.getItemDamage();
    }


    public static void setDamage(ItemStack stack, int damage) {
        if (stack == null) return;
        stack.setItemDamage(damage);
    }


    public static int getMaxDamage(ItemStack stack) {
        if (stack == null) return 0;
        return stack.getMaxDamage();
    }


    public static boolean isDamaged(ItemStack stack) {
        if (stack == null) return false;
        return stack.isItemDamaged();
    }


    public static boolean isDamageable(ItemStack stack) {
        if (stack == null) return false;
        return stack.isItemStackDamageable();
    }


    public static void repair(ItemStack stack, int amount) {
        if (stack == null) return;
        int newDamage = Math.max(0, stack.getItemDamage() - amount);
        stack.setItemDamage(newDamage);
    }


    public static void fullRepair(ItemStack stack) {
        if (stack == null) return;
        stack.setItemDamage(0);
    }


    public static void damage(ItemStack stack, int amount) {
        if (stack == null) return;
        int newDamage = Math.min(stack.getMaxDamage(), stack.getItemDamage() + amount);
        stack.setItemDamage(newDamage);
    }


    public static float getDurabilityPercent(ItemStack stack) {
        if (stack == null || !isDamageable(stack)) return 1.0F;
        return 1.0F - ((float) stack.getItemDamage() / (float) stack.getMaxDamage());
    }

    // ========== NBT ==========


    public static NBTTagCompound getNBT(ItemStack stack) {
        if (stack == null) return null;
        return stack.getTagCompound();
    }


    public static void setNBT(ItemStack stack, NBTTagCompound nbt) {
        if (stack == null) return;
        stack.setTagCompound(nbt);
    }


    public static boolean hasNBT(ItemStack stack) {
        if (stack == null) return false;
        return stack.hasTagCompound();
    }


    public static void setNBTString(ItemStack stack, String key, String value) {
        if (stack == null || key == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString(key, value);
    }


    public static String getNBTString(ItemStack stack, String key) {
        if (stack == null || !stack.hasTagCompound() || key == null) return "";
        return stack.getTagCompound().getString(key);
    }


    public static void setNBTInt(ItemStack stack, String key, int value) {
        if (stack == null || key == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setInteger(key, value);
    }


    public static int getNBTInt(ItemStack stack, String key) {
        if (stack == null || !stack.hasTagCompound() || key == null) return 0;
        return stack.getTagCompound().getInteger(key);
    }


    public static void setNBTBoolean(ItemStack stack, String key, boolean value) {
        if (stack == null || key == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setBoolean(key, value);
    }


    public static boolean getNBTBoolean(ItemStack stack, String key) {
        if (stack == null || !stack.hasTagCompound() || key == null) return false;
        return stack.getTagCompound().getBoolean(key);
    }


    public static void removeNBTKey(ItemStack stack, String key) {
        if (stack == null || !stack.hasTagCompound() || key == null) return;
        stack.getTagCompound().removeTag(key);
    }


    public static boolean hasNBTKey(ItemStack stack, String key) {
        if (stack == null || !stack.hasTagCompound() || key == null) return false;
        return stack.getTagCompound().hasKey(key);
    }

    // ========== COMPARAISON ==========


    public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2) {
        if (stack1 == null && stack2 == null) return true;
        if (stack1 == null || stack2 == null) return false;
        return stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage();
    }


    public static boolean areItemsEqualWithNBT(ItemStack stack1, ItemStack stack2) {
        if (!areItemsEqual(stack1, stack2)) return false;
        return ItemStack.areItemStackTagsEqual(stack1, stack2);
    }


    public static boolean canStack(ItemStack stack1, ItemStack stack2) {
        if (stack1 == null || stack2 == null) return false;
        return stack1.isItemEqual(stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }

    // ========== INFORMATIONS ==========


    public static Item getItem(ItemStack stack) {
        if (stack == null) return null;
        return stack.getItem();
    }


    public static int getCount(ItemStack stack) {
        if (stack == null) return 0;
        return stack.stackSize;
    }


    public static void setCount(ItemStack stack, int count) {
        if (stack == null) return;
        stack.stackSize = count;
    }


    public static int getMaxStackSize(ItemStack stack) {
        if (stack == null) return 0;
        return stack.getMaxStackSize();
    }


    public static boolean isStackFull(ItemStack stack) {
        if (stack == null) return false;
        return stack.stackSize >= stack.getMaxStackSize();
    }


    public static int getMetadata(ItemStack stack) {
        if (stack == null) return 0;
        return stack.getItemDamage();
    }


    public static void setMetadata(ItemStack stack, int metadata) {
        if (stack == null) return;
        stack.setItemDamage(metadata);
    }


    public static String getUnlocalizedName(ItemStack stack) {
        if (stack == null) return "";
        return stack.getUnlocalizedName();
    }


    public static boolean isEmpty(ItemStack stack) {
        return stack == null || stack.stackSize <= 0;
    }

    // ========== MANIPULATION ==========


    public static ItemStack splitStack(ItemStack stack, int amount) {
        if (stack == null) return null;
        return stack.splitStack(amount);
    }


    public static void mergeStacks(ItemStack destination, ItemStack source) {
        if (destination == null || source == null) return;
        if (!canStack(destination, source)) return;
        
        int space = destination.getMaxStackSize() - destination.stackSize;
        int toTransfer = Math.min(space, source.stackSize);
        
        destination.stackSize += toTransfer;
        source.stackSize -= toTransfer;
    }


    public static void increment(ItemStack stack, int amount) {
        if (stack == null) return;
        stack.stackSize += amount;
    }


    public static void decrement(ItemStack stack, int amount) {
        if (stack == null) return;
        stack.stackSize -= amount;
        if (stack.stackSize < 0) stack.stackSize = 0;
    }

    // ========== UTILITAIRES ==========


    public static void setUnbreakable(ItemStack stack, boolean unbreakable) {
        if (stack == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setBoolean("Unbreakable", unbreakable);
    }


    public static boolean isUnbreakable(ItemStack stack) {
        if (stack == null || !stack.hasTagCompound()) return false;
        return stack.getTagCompound().getBoolean("Unbreakable");
    }


    public static void setHideFlags(ItemStack stack, int flags) {
        if (stack == null) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setInteger("HideFlags", flags);
    }


    public static int getHideFlags(ItemStack stack) {
        if (stack == null || !stack.hasTagCompound()) return 0;
        return stack.getTagCompound().getInteger("HideFlags");
    }

    public static final int HIDE_ENCHANTMENTS = 1;
    public static final int HIDE_ATTRIBUTES = 2;
    public static final int HIDE_UNBREAKABLE = 4;
    public static final int HIDE_DESTROYS = 8;
    public static final int HIDE_PLACED_ON = 16;
    public static final int HIDE_POTION_EFFECTS = 32;
}