package fr.zayzx.zayzlibs.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityAction {

    // ========== SPAWN ET SUPPRESSION ==========


    public static Entity spawnEntity(World world, String entityName, double x, double y, double z) {
        if (world == null || entityName == null) return null;
        Entity entity = EntityList.createEntityByName(entityName, world);
        if (entity != null) {
            entity.setPosition(x, y, z);
            world.spawnEntityInWorld(entity);
        }
        return entity;
    }


    public static Entity spawnEntityWithRotation(World world, String entityName, double x, double y, double z, float yaw, float pitch) {
        Entity entity = spawnEntity(world, entityName, x, y, z);
        if (entity != null) {
            entity.rotationYaw = yaw;
            entity.rotationPitch = pitch;
        }
        return entity;
    }


    public static void removeEntity(Entity entity) {
        if (entity == null) return;
        entity.setDead();
    }


    public static void killEntity(EntityLivingBase entity) {
        if (entity == null) return;
        entity.setHealth(0);
    }


    public static void removeAllEntitiesOfType(World world, Class<? extends Entity> entityClass) {
        if (world == null || entityClass == null) return;
        List<Entity> toRemove = new ArrayList<Entity>();
        for (Object obj : world.loadedEntityList) {
            if (entityClass.isInstance(obj)) {
                toRemove.add((Entity) obj);
            }
        }
        for (Entity entity : toRemove) {
            entity.setDead();
        }
    }

    // ========== RECHERCHE D'ENTITÉS ==========


    public static Entity getNearestEntity(World world, double x, double y, double z, double range, Class<? extends Entity> entityClass) {
        if (world == null) return null;
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        List<Entity> entities = world.getEntitiesWithinAABB(entityClass, aabb);
        
        Entity nearest = null;
        double minDistance = Double.MAX_VALUE;
        
        for (Entity entity : entities) {
            double distance = entity.getDistance(x, y, z);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = entity;
            }
        }
        
        return nearest;
    }


    public static List<Entity> getEntitiesInRange(World world, double x, double y, double z, double range) {
        if (world == null) return new ArrayList<Entity>();
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        return world.getEntitiesWithinAABB(Entity.class, aabb);
    }


    public static <T extends Entity> List<T> getEntitiesOfTypeInRange(World world, double x, double y, double z, double range, Class<T> entityClass) {
        if (world == null || entityClass == null) return new ArrayList<T>();
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        return world.getEntitiesWithinAABB(entityClass, aabb);
    }


    public static EntityPlayer getNearestPlayer(Entity entity, double range) {
        if (entity == null || entity.worldObj == null) return null;
        return entity.worldObj.getClosestPlayer(entity.posX, entity.posY, entity.posZ, range);
    }


    public static int countEntitiesOfType(World world, Class<? extends Entity> entityClass) {
        if (world == null || entityClass == null) return 0;
        int count = 0;
        for (Object obj : world.loadedEntityList) {
            if (entityClass.isInstance(obj)) {
                count++;
            }
        }
        return count;
    }

    // ========== TÉLÉPORTATION ==========


    public static void teleport(Entity entity, double x, double y, double z) {
        if (entity == null) return;
        if (entity instanceof EntityPlayer) {
            ((EntityPlayer) entity).setPositionAndUpdate(x, y, z);
        } else {
            entity.setPosition(x, y, z);
        }
    }


    public static void teleportWithRotation(Entity entity, double x, double y, double z, float yaw, float pitch) {
        teleport(entity, x, y, z);
        if (entity != null) {
            entity.rotationYaw = yaw;
            entity.rotationPitch = pitch;
        }
    }


    public static void teleportToEntity(Entity entity, Entity target) {
        if (entity == null || target == null) return;
        teleport(entity, target.posX, target.posY, target.posZ);
    }


    public static void randomTeleport(Entity entity, double range) {
        if (entity == null) return;
        Random rand = entity.worldObj.rand;
        double x = entity.posX + (rand.nextDouble() - 0.5) * 2 * range;
        double y = entity.posY + (rand.nextDouble() - 0.5) * 2 * range;
        double z = entity.posZ + (rand.nextDouble() - 0.5) * 2 * range;
        teleport(entity, x, y, z);
    }


    public static void safeTeleport(Entity entity, double x, double z) {
        if (entity == null || entity.worldObj == null) return;
        int y = entity.worldObj.getTopSolidOrLiquidBlock((int) x, (int) z);
        teleport(entity, x, y, z);
    }

    // ========== MOUVEMENT ET VÉLOCITÉ ==========


    public static void applyVelocity(Entity entity, double x, double y, double z) {
        if (entity == null) return;
        entity.motionX += x;
        entity.motionY += y;
        entity.motionZ += z;
        entity.velocityChanged = true;
    }


    public static void setVelocity(Entity entity, double x, double y, double z) {
        if (entity == null) return;
        entity.motionX = x;
        entity.motionY = y;
        entity.motionZ = z;
        entity.velocityChanged = true;
    }


    public static void stopMotion(Entity entity) {
        setVelocity(entity, 0, 0, 0);
    }


    public static void jump(EntityLivingBase entity, float strength) {
        if (entity == null) return;
        entity.motionY = strength;
        entity.isAirBorne = true;
    }


    public static void launchInLookDirection(Entity entity, double speed) {
        if (entity == null) return;
        Vec3 look = entity.getLookVec();
        entity.motionX = look.xCoord * speed;
        entity.motionY = look.yCoord * speed;
        entity.motionZ = look.zCoord * speed;
        entity.velocityChanged = true;
    }


    public static void launchUpward(Entity entity, double strength) {
        if (entity == null) return;
        entity.motionY = strength;
        entity.velocityChanged = true;
    }


    public static void pushAwayFrom(Entity entity, double x, double y, double z, double strength) {
        if (entity == null) return;
        double dx = entity.posX - x;
        double dy = entity.posY - y;
        double dz = entity.posZ - z;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        
        if (distance > 0) {
            entity.motionX += (dx / distance) * strength;
            entity.motionY += (dy / distance) * strength;
            entity.motionZ += (dz / distance) * strength;
            entity.velocityChanged = true;
        }
    }


    public static void pullTowards(Entity entity, double x, double y, double z, double strength) {
        pushAwayFrom(entity, x, y, z, -strength);
    }


    public static void orbit(Entity entity, double centerX, double centerZ, double radius, double speed) {
        if (entity == null) return;
        double angle = Math.atan2(entity.posZ - centerZ, entity.posX - centerX);
        angle += speed;
        
        double newX = centerX + Math.cos(angle) * radius;
        double newZ = centerZ + Math.sin(angle) * radius;
        
        entity.setPosition(newX, entity.posY, newZ);
    }

    // ========== ROTATION ET REGARD ==========


    public static void lookAt(Entity entity, double x, double y, double z) {
        if (entity == null) return;
        double dx = x - entity.posX;
        double dy = y - entity.posY - entity.getEyeHeight();
        double dz = z - entity.posZ;
        
        double distance = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) (-(Math.atan2(dy, distance) * 180.0D / Math.PI));
        
        entity.rotationYaw = yaw;
        entity.rotationPitch = pitch;
    }


    public static void lookAtEntity(Entity entity, Entity target) {
        if (entity == null || target == null) return;
        lookAt(entity, target.posX, target.posY + target.getEyeHeight(), target.posZ);
    }


    public static void rotate(Entity entity, float yaw, float pitch) {
        if (entity == null) return;
        entity.rotationYaw += yaw;
        entity.rotationPitch += pitch;
    }


    public static void setRotation(Entity entity, float yaw, float pitch) {
        if (entity == null) return;
        entity.rotationYaw = yaw;
        entity.rotationPitch = pitch;
    }

    // ========== VIE ET DÉGÂTS ==========


    public static float getHealth(EntityLivingBase entity) {
        if (entity == null) return 0;
        return entity.getHealth();
    }


    public static void setHealth(EntityLivingBase entity, float health) {
        if (entity == null) return;
        entity.setHealth(health);
    }


    public static float getMaxHealth(EntityLivingBase entity) {
        if (entity == null) return 0;
        return entity.getMaxHealth();
    }


    public static void heal(EntityLivingBase entity, float amount) {
        if (entity == null) return;
        entity.heal(amount);
    }


    public static void fullHeal(EntityLivingBase entity) {
        if (entity == null) return;
        entity.setHealth(entity.getMaxHealth());
    }


    public static void damage(EntityLivingBase entity, float amount) {
        if (entity == null) return;
        entity.attackEntityFrom(DamageSource.generic, amount);
    }


    public static void damageWithSource(EntityLivingBase entity, DamageSource source, float amount) {
        if (entity == null) return;
        entity.attackEntityFrom(source, amount);
    }


    public static void instantKill(EntityLivingBase entity) {
        if (entity == null) return;
        entity.setHealth(0);
    }


    public static boolean isAlive(EntityLivingBase entity) {
        if (entity == null) return false;
        return entity.isEntityAlive();
    }


    public static boolean isDead(Entity entity) {
        if (entity == null) return true;
        return entity.isDead;
    }

    // ========== EFFETS DE POTION ==========


    public static void addPotionEffect(EntityLivingBase entity, Potion potion, int duration, int amplifier) {
        if (entity == null || potion == null) return;
        entity.addPotionEffect(new PotionEffect(potion.id, duration, amplifier));
    }


    public static void removePotionEffect(EntityLivingBase entity, Potion potion) {
        if (entity == null || potion == null) return;
        entity.removePotionEffect(potion.id);
    }


    public static void clearPotionEffects(EntityLivingBase entity) {
        if (entity == null) return;
        entity.clearActivePotions();
    }


    public static boolean hasPotionEffect(EntityLivingBase entity, Potion potion) {
        if (entity == null || potion == null) return false;
        return entity.isPotionActive(potion);
    }


    public static PotionEffect getPotionEffect(EntityLivingBase entity, Potion potion) {
        if (entity == null || potion == null) return null;
        return entity.getActivePotionEffect(potion);
    }


    public static void applyMultiplePotionEffects(EntityLivingBase entity, Potion[] potions, int duration, int amplifier) {
        if (entity == null || potions == null) return;
        for (Potion potion : potions) {
            if (potion != null) {
                addPotionEffect(entity, potion, duration, amplifier);
            }
        }
    }

    // ========== FEU ==========


    public static void setFire(Entity entity, int seconds) {
        if (entity == null) return;
        entity.setFire(seconds);
    }


    public static void extinguish(Entity entity) {
        if (entity == null) return;
        entity.extinguish();
    }


    public static boolean isBurning(Entity entity) {
        if (entity == null) return false;
        return entity.isBurning();
    }


    public static boolean isImmuneToFire(Entity entity) {
        if (entity == null) return false;
        return entity.isImmuneToFire();
    }

    // ========== INVULNÉRABILITÉ ==========


    public static void setInvulnerable(Entity entity, boolean invulnerable) {
        if (entity == null) return;
        NBTTagCompound nbt = new NBTTagCompound();
        entity.writeToNBT(nbt);
        nbt.setBoolean("Invulnerable", invulnerable);
        entity.readFromNBT(nbt);
    }


    public static boolean isInvulnerable(Entity entity) {
        if (entity == null) return false;
        NBTTagCompound nbt = new NBTTagCompound();
        entity.writeToNBT(nbt);
        return nbt.getBoolean("Invulnerable");
    }

    // ========== MONTAGE ==========


    public static void mountEntity(Entity rider, Entity mount) {
        if (rider == null || mount == null) return;
        rider.mountEntity(mount);
    }


    public static void dismount(Entity entity) {
        if (entity == null) return;
        entity.mountEntity(null);
    }


    public static boolean isRiding(Entity entity) {
        if (entity == null) return false;
        return entity.ridingEntity != null;
    }


    public static Entity getRidingEntity(Entity entity) {
        if (entity == null) return null;
        return entity.ridingEntity;
    }

    // ========== INVISIBILITÉ ET RENDU ==========


    public static void setInvisible(Entity entity, boolean invisible) {
        if (entity == null) return;
        entity.setInvisible(invisible);
    }


    public static boolean isInvisible(Entity entity) {
        if (entity == null) return false;
        return entity.isInvisible();
    }


    public static void setSneaking(EntityLivingBase entity, boolean sneaking) {
        if (entity == null) return;
        entity.setSneaking(sneaking);
    }


    public static boolean isSneaking(Entity entity) {
        if (entity == null) return false;
        return entity.isSneaking();
    }


    public static void setSprinting(EntityLivingBase entity, boolean sprinting) {
        if (entity == null) return;
        entity.setSprinting(sprinting);
    }


    public static boolean isSprinting(EntityLivingBase entity) {
        if (entity == null) return false;
        return entity.isSprinting();
    }

    // ========== TAILLE ET ÉCHELLE ==========

    public static void setSize(Entity entity, float width, float height) {
        if (entity == null) return;
        try {
            java.lang.reflect.Method method = Entity.class.getDeclaredMethod("setSize", float.class, float.class);
            method.setAccessible(true);
            method.invoke(entity, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static float getWidth(Entity entity) {
        if (entity == null) return 0;
        return entity.width;
    }


    public static float getHeight(Entity entity) {
        if (entity == null) return 0;
        return entity.height;
    }


    public static void setWidth(Entity entity, float width) {
        if (entity == null) return;
        entity.width = width;
    }


    public static void setHeight(Entity entity, float height) {
        if (entity == null) return;
        entity.height = height;
    }

    // ========== NOMMAGE ==========

    public static String getEntityName(Entity entity) {
        if (entity == null) return "";
        return entity.getCommandSenderName();
    }

    // ========== AI ET COMPORTEMENT ==========


    public static void clearAI(EntityLiving entity) {
        if (entity == null) return;
        entity.tasks.taskEntries.clear();
        entity.targetTasks.taskEntries.clear();
    }


    public static void setAttackTarget(EntityLiving entity, EntityLivingBase target) {
        if (entity == null) return;
        entity.setAttackTarget(target);
    }


    public static EntityLivingBase getAttackTarget(EntityLiving entity) {
        if (entity == null) return null;
        return entity.getAttackTarget();
    }


    public static void flee(EntityLiving entity, Entity from, double speed) {
        if (entity == null || from == null) return;
        double dx = entity.posX - from.posX;
        double dz = entity.posZ - from.posZ;
        double distance = Math.sqrt(dx * dx + dz * dz);
        
        if (distance > 0) {
            entity.motionX = (dx / distance) * speed;
            entity.motionZ = (dz / distance) * speed;
        }
    }

    // ========== DROPS D'ITEMS ==========


    public static EntityItem dropItem(Entity entity, ItemStack stack) {
        if (entity == null || stack == null) return null;
        return entity.entityDropItem(stack, 0.0F);
    }


    public static EntityItem dropItemWithOffset(Entity entity, ItemStack stack, float offsetY) {
        if (entity == null || stack == null) return null;
        return entity.entityDropItem(stack, offsetY);
    }

    // ========== INFORMATIONS ==========


    public static int getDimension(Entity entity) {
        if (entity == null) return 0;
        return entity.dimension;
    }


    public static World getWorld(Entity entity) {
        if (entity == null) return null;
        return entity.worldObj;
    }


    public static double getX(Entity entity) {
        if (entity == null) return 0;
        return entity.posX;
    }


    public static double getY(Entity entity) {
        if (entity == null) return 0;
        return entity.posY;
    }


    public static double getZ(Entity entity) {
        if (entity == null) return 0;
        return entity.posZ;
    }


    public static double getMotionX(Entity entity) {
        if (entity == null) return 0;
        return entity.motionX;
    }


    public static double getMotionY(Entity entity) {
        if (entity == null) return 0;
        return entity.motionY;
    }


    public static double getMotionZ(Entity entity) {
        if (entity == null) return 0;
        return entity.motionZ;
    }


    public static double getDistance(Entity entity1, Entity entity2) {
        if (entity1 == null || entity2 == null) return 0;
        return entity1.getDistanceToEntity(entity2);
    }


    public static double getDistanceToPoint(Entity entity, double x, double y, double z) {
        if (entity == null) return 0;
        return entity.getDistance(x, y, z);
    }


    public static boolean isInWater(Entity entity) {
        if (entity == null) return false;
        return entity.isInWater();
    }


    public static boolean isInLava(Entity entity) {
        if (entity == null) return false;
        return entity.handleLavaMovement();
    }


    public static boolean isOnGround(Entity entity) {
        if (entity == null) return false;
        return entity.onGround;
    }


    public static boolean canSee(EntityLiving entity, Entity target) {
        if (entity == null || target == null) return false;
        return entity.getEntitySenses().canSee(target);
    }

    // ========== CLONAGE ==========


    public static Entity cloneEntity(Entity entity) {
        if (entity == null || entity.worldObj == null) return null;
        
        NBTTagCompound nbt = new NBTTagCompound();
        entity.writeToNBT(nbt);
        
        Entity clone = EntityList.createEntityFromNBT(nbt, entity.worldObj);
        if (clone != null) {
            clone.setPosition(entity.posX, entity.posY, entity.posZ);
        }
        
        return clone;
    }

    // ========== TYPES D'ENTITÉS ==========


    public static boolean isPlayer(Entity entity) {
        return entity instanceof EntityPlayer;
    }


    public static boolean isMonster(Entity entity) {
        return entity instanceof EntityMob;
    }


    public static boolean isAnimal(Entity entity) {
        return entity instanceof EntityAnimal;
    }

    
    public static boolean isBoss(Entity entity) {
        return entity instanceof EntityDragon || entity instanceof EntityWither;
    }


    public static boolean isItem(Entity entity) {
        return entity instanceof EntityItem;
    }

    // ========== EFFETS DE ZONE ==========


    public static void applyPotionEffectInRadius(World world, double x, double y, double z, double radius, Potion potion, int duration, int amplifier) {
        List<EntityLivingBase> entities = getEntitiesOfTypeInRange(world, x, y, z, radius, EntityLivingBase.class);
        for (EntityLivingBase entity : entities) {
            addPotionEffect(entity, potion, duration, amplifier);
        }
    }


    public static void healInRadius(World world, double x, double y, double z, double radius, float amount) {
        List<EntityLivingBase> entities = getEntitiesOfTypeInRange(world, x, y, z, radius, EntityLivingBase.class);
        for (EntityLivingBase entity : entities) {
            heal(entity, amount);
        }
    }


    public static void damageInRadius(World world, double x, double y, double z, double radius, float amount) {
        List<EntityLivingBase> entities = getEntitiesOfTypeInRange(world, x, y, z, radius, EntityLivingBase.class);
        for (EntityLivingBase entity : entities) {
            damage(entity, amount);
        }
    }


    public static void pushAllAwayFrom(World world, double x, double y, double z, double radius, double strength) {
        List<Entity> entities = getEntitiesInRange(world, x, y, z, radius);
        for (Entity entity : entities) {
            pushAwayFrom(entity, x, y, z, strength);
        }
    }


    public static void pullAllTowards(World world, double x, double y, double z, double radius, double strength) {
        pushAllAwayFrom(world, x, y, z, radius, -strength);
    }


    public static void freezeInRadius(World world, double x, double y, double z, double radius) {
        List<Entity> entities = getEntitiesInRange(world, x, y, z, radius);
        for (Entity entity : entities) {
            stopMotion(entity);
        }
    }
}