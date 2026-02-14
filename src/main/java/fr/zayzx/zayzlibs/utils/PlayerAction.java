package fr.zayzx.zayzlibs.utils;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerAction {

    // ========== INVENTORY ==========
    
    public static void addItem(EntityPlayer player, Item item, int count) {
        if (player == null || item == null) return;
        ItemStack stack = new ItemStack(item, count);
        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropPlayerItemWithRandomChoice(stack, false);
        }
    }

    public static void removeItem(EntityPlayer player, Item item, int count) {
        if (player == null || item == null) return;
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                if (stack.stackSize > count) {
                    stack.stackSize -= count;
                    break;
                } else {
                    count -= stack.stackSize;
                    player.inventory.setInventorySlotContents(i, null);
                    if (count <= 0) break;
                }
            }
        }
    }

    public static void clearInventory(EntityPlayer player) {
        if (player == null) return;
        player.inventory.clearInventory(null, -1);
    }

    public static boolean hasItem(EntityPlayer player, Item item, int count) {
        if (player == null || item == null) return false;
        int totalCount = 0;
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                totalCount += stack.stackSize;
            }
        }
        return totalCount >= count;
    }

    public static int countItem(EntityPlayer player, Item item) {
        if (player == null || item == null) return 0;
        int count = 0;
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                count += stack.stackSize;
            }
        }
        return count;
    }

    // ========== HEALTH ==========

    public static float getHealth(EntityPlayer player) {
        if (player == null) return 0;
        return player.getHealth();
    }

    public static void setHealth(EntityPlayer player, float health) {
        if (player == null) return;
        player.setHealth(health);
    }

    public static float getMaxHealth(EntityPlayer player) {
        if (player == null) return 0;
        return player.getMaxHealth();
    }

    public static void addHealth(EntityPlayer player, float amount) {
        if (player == null) return;
        player.heal(amount);
    }

    public static void removeHealth(EntityPlayer player, float amount) {
        if (player == null) return;
        player.attackEntityFrom(DamageSource.generic, amount);
    }

    public static void setMaxHealth(EntityPlayer player, float maxHealth) {
        if (player == null) return;
        player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
    }

    public static void fullHeal(EntityPlayer player) {
        if (player == null) return;
        player.setHealth(player.getMaxHealth());
    }

    public static boolean isFullHealth(EntityPlayer player) {
        if (player == null) return false;
        return player.getHealth() >= player.getMaxHealth();
    }

    // ========== FOOD ==========

    public static int getFoodLevel(EntityPlayer player) {
        if (player == null) return 0;
        return player.getFoodStats().getFoodLevel();
    }

    public static void setFoodLevel(EntityPlayer player, int foodLevel) {
        if (player == null) return;
        player.getFoodStats().addStats(foodLevel - player.getFoodStats().getFoodLevel(), 0.0F);
    }

    public static float getSaturation(EntityPlayer player) {
        if (player == null) return 0;
        return player.getFoodStats().getSaturationLevel();
    }

    public static void setSaturation(EntityPlayer player, float saturation) {
        if (player == null) return;
        FoodStats stats = player.getFoodStats();
        player.getFoodStats().addStats(0, saturation);
    }

    public static void addFood(EntityPlayer player, int foodLevel, float saturation) {
        if (player == null) return;
        player.getFoodStats().addStats(foodLevel, saturation);
    }

    public static void fillFood(EntityPlayer player) {
        if (player == null) return;
        player.getFoodStats().addStats(20, 20.0F);
    }

    public static boolean needsFood(EntityPlayer player) {
        if (player == null) return false;
        return player.getFoodStats().needFood();
    }

    // ========== ARMOR ==========

    public static int getArmor(EntityPlayer player) {
        if (player == null) return 0;
        return player.getTotalArmorValue();
    }

    public static void damageArmor(EntityPlayer player, float damage) {
        if (player == null) return;
        player.inventory.damageArmor(damage);
    }

    public static ItemStack getArmorInSlot(EntityPlayer player, int slot) {
        if (player == null) return null;
        return player.inventory.armorInventory[slot];
    }

    public static void setArmorInSlot(EntityPlayer player, int slot, ItemStack stack) {
        if (player == null) return;
        player.inventory.armorInventory[slot] = stack;
    }

    public static void clearArmor(EntityPlayer player) {
        if (player == null) return;
        for (int i = 0; i < player.inventory.armorInventory.length; i++) {
            player.inventory.armorInventory[i] = null;
        }
    }

    // ========== XP ==========

    public static int getExperienceLevel(EntityPlayer player) {
        if (player == null) return 0;
        return player.experienceLevel;
    }

    public static void setExperienceLevel(EntityPlayer player, int level) {
        if (player == null) return;
        player.experienceLevel = level;
    }

    public static int getExperienceTotal(EntityPlayer player) {
        if (player == null) return 0;
        return player.experienceTotal;
    }

    public static void setExperienceTotal(EntityPlayer player, int total) {
        if (player == null) return;
        player.experienceTotal = total;
    }

    public static float getExperience(EntityPlayer player) {
        if (player == null) return 0;
        return player.experience;
    }

    public static void setExperience(EntityPlayer player, float xp) {
        if (player == null) return;
        player.experience = xp;
    }

    public static void addExperience(EntityPlayer player, int amount) {
        if (player == null) return;
        player.addExperience(amount);
    }

    public static void addExperienceLevel(EntityPlayer player, int levels) {
        if (player == null) return;
        player.addExperienceLevel(levels);
    }

    public static void clearExperience(EntityPlayer player) {
        if (player == null) return;
        player.experience = 0.0F;
        player.experienceLevel = 0;
        player.experienceTotal = 0;
    }

    public static int getXpNeededForNextLevel(EntityPlayer player) {
        if (player == null) return 0;
        return player.xpBarCap();
    }

    // ========== POTION EFFECT ==========

    public static void addPotionEffect(EntityPlayer player, Potion potion, int duration, int amplifier) {
        if (player == null || potion == null) return;
        player.addPotionEffect(new PotionEffect(potion.id, duration, amplifier));
    }

    public static void removePotionEffect(EntityPlayer player, Potion potion) {
        if (player == null || potion == null) return;
        player.removePotionEffect(potion.id);
    }

    public static void clearPotionEffects(EntityPlayer player) {
        if (player == null) return;
        player.clearActivePotions();
    }

    public static boolean hasPotionEffect(EntityPlayer player, Potion potion) {
        if (player == null || potion == null) return false;
        return player.isPotionActive(potion);
    }

    public static PotionEffect getPotionEffect(EntityPlayer player, Potion potion) {
        if (player == null || potion == null) return null;
        return player.getActivePotionEffect(potion);
    }

    // ========== MOUVEMENT ==========

    public static void applyVelocity(EntityPlayer player, double x, double y, double z) {
        if (player == null) return;
        player.motionX += x;
        player.motionY += y;
        player.motionZ += z;
        player.fallDistance = 0;
    }

    public static void setVelocity(EntityPlayer player, double x, double y, double z) {
        if (player == null) return;
        player.motionX = x;
        player.motionY = y;
        player.motionZ = z;
    }

    public static void stopMotion(EntityPlayer player) {
        if (player == null) return;
        player.motionX = 0;
        player.motionY = 0;
        player.motionZ = 0;
    }

    public static void teleport(EntityPlayer player, double x, double y, double z) {
        if (player == null) return;
        player.setPositionAndUpdate(x, y, z);
    }

    public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
        if (player == null) return;
        if (player.worldObj.provider.dimensionId != dimension) {
            player.travelToDimension(dimension);
        }
        player.setPositionAndUpdate(x, y, z);
    }

    public static void jump(EntityPlayer player, float strength) {
        if (player == null) return;
        player.motionY = strength;
        player.isAirBorne = true;
    }

    public static void launchForward(EntityPlayer player, double speed) {
        if (player == null) return;
        Vec3 look = player.getLookVec();
        player.motionX += look.xCoord * speed;
        player.motionY += look.yCoord * speed;
        player.motionZ += look.zCoord * speed;
    }

    public static void launchUpward(EntityPlayer player, double strength) {
        if (player == null) return;
        player.motionY += strength;
        player.isAirBorne = true;
    }

    public static void pushAway(EntityPlayer player, EntityPlayer target, double strength) {
        if (player == null || target == null) return;
        double dx = target.posX - player.posX;
        double dz = target.posZ - player.posZ;
        double distance = Math.sqrt(dx * dx + dz * dz);
        if (distance > 0) {
            target.motionX += (dx / distance) * strength;
            target.motionZ += (dz / distance) * strength;
        }
    }

    // ========== POSITION ==========

    public static double getX(EntityPlayer player) {
        if (player == null) return 0;
        return player.posX;
    }

    public static double getY(EntityPlayer player) {
        if (player == null) return 0;
        return player.posY;
    }

    public static double getZ(EntityPlayer player) {
        if (player == null) return 0;
        return player.posZ;
    }

    public static void setPosition(EntityPlayer player, double x, double y, double z) {
        if (player == null) return;
        player.setPosition(x, y, z);
    }

    public static ChunkCoordinates getSpawnPoint(EntityPlayer player) {
        if (player == null) return null;
        return player.getBedLocation(player.dimension);
    }

    public static void setSpawnPoint(EntityPlayer player, int x, int y, int z) {
        if (player == null) return;
        player.setSpawnChunk(new ChunkCoordinates(x, y, z), false, player.dimension);
    }

    // ========== GAMEMODE ==========

    public static void setCreativeMode(EntityPlayer player) {
        if (player == null) return;
        player.capabilities.allowFlying = true;
        player.capabilities.isCreativeMode = true;
        player.capabilities.disableDamage = true;
        player.sendPlayerAbilities();
    }

    public static void setSurvivalMode(EntityPlayer player) {
        if (player == null) return;
        player.capabilities.allowFlying = false;
        player.capabilities.isCreativeMode = false;
        player.capabilities.disableDamage = false;
        player.capabilities.isFlying = false;
        player.sendPlayerAbilities();
    }

    public static void setFlying(EntityPlayer player, boolean flying) {
        if (player == null) return;
        player.capabilities.isFlying = flying;
        player.sendPlayerAbilities();
    }

    public static void setAllowFlying(EntityPlayer player, boolean allow) {
        if (player == null) return;
        player.capabilities.allowFlying = allow;
        player.sendPlayerAbilities();
    }

    public static void setInvulnerable(EntityPlayer player, boolean invulnerable) {
        if (player == null) return;
        player.capabilities.disableDamage = invulnerable;
        player.sendPlayerAbilities();
    }

    public static boolean isCreative(EntityPlayer player) {
        if (player == null) return false;
        return player.capabilities.isCreativeMode;
    }

    public static boolean isFlying(EntityPlayer player) {
        if (player == null) return false;
        return player.capabilities.isFlying;
    }

    // ========== FIRE AND DAMAGE ==========

    public static void setFire(EntityPlayer player, int seconds) {
        if (player == null) return;
        player.setFire(seconds);
    }

    public static void extinguish(EntityPlayer player) {
        if (player == null) return;
        player.extinguish();
    }

    public static boolean isBurning(EntityPlayer player) {
        if (player == null) return false;
        return player.isBurning();
    }

    public static void damage(EntityPlayer player, DamageSource source, float amount) {
        if (player == null) return;
        player.attackEntityFrom(source, amount);
    }

    public static void kill(EntityPlayer player) {
        if (player == null) return;
        player.setHealth(0);
    }

    // ========== DIVERS ==========

    public static World getWorld(EntityPlayer player) {
        if (player == null) return null;
        return player.worldObj;
    }

    public static int getDimension(EntityPlayer player) {
        if (player == null) return 0;
        return player.dimension;
    }

    public static boolean isSneaking(EntityPlayer player) {
        if (player == null) return false;
        return player.isSneaking();
    }

    public static void setSneaking(EntityPlayer player, boolean sneaking) {
        if (player == null) return;
        player.setSneaking(sneaking);
    }

    public static boolean isSprinting(EntityPlayer player) {
        if (player == null) return false;
        return player.isSprinting();
    }

    public static void setSprinting(EntityPlayer player, boolean sprinting) {
        if (player == null) return;
        player.setSprinting(sprinting);
    }

    public static float getWalkSpeed(EntityPlayer player) {
        if (player == null) return 0;
        return player.capabilities.getWalkSpeed();
    }

    public static void setWalkSpeed(EntityPlayer player, float speed) {
        if (player == null) return;
        player.capabilities.setPlayerWalkSpeed(speed);
        player.sendPlayerAbilities();
    }

    public static float getFlySpeed(EntityPlayer player) {
        if (player == null) return 0;
        return player.capabilities.getFlySpeed();
    }

    public static void setFlySpeed(EntityPlayer player, float speed) {
        if (player == null) return;
        player.capabilities.setFlySpeed(speed);
        player.sendPlayerAbilities();
    }

    public static void respawn(EntityPlayer player) {
        if (player == null) return;
        player.setHealth(player.getMaxHealth());
        player.clearActivePotions();
        player.extinguish();
    }
}