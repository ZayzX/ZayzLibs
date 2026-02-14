package fr.zayzx.zayzlibs.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldAction {

    // ========== BLOCKS ==========

    public static Block getBlock(World world, int x, int y, int z) {
        if (world == null) return null;
        return world.getBlock(x, y, z);
    }

    public static void setBlock(World world, int x, int y, int z, Block block) {
        if (world == null || block == null) return;
        world.setBlock(x, y, z, block);
    }

    public static void setBlock(World world, int x, int y, int z, Block block, int metadata) {
        if (world == null || block == null) return;
        world.setBlock(x, y, z, block, metadata, 3);
    }

    public static void setBlockWithUpdate(World world, int x, int y, int z, Block block, int metadata, int flag) {
        if (world == null || block == null) return;
        world.setBlock(x, y, z, block, metadata, flag);
    }

    public static int getBlockMetadata(World world, int x, int y, int z) {
        if (world == null) return 0;
        return world.getBlockMetadata(x, y, z);
    }

    public static void setBlockMetadata(World world, int x, int y, int z, int metadata) {
        if (world == null) return;
        world.setBlockMetadataWithNotify(x, y, z, metadata, 3);
    }

    public static boolean isAirBlock(World world, int x, int y, int z) {
        if (world == null) return false;
        return world.isAirBlock(x, y, z);
    }

    public static void removeBlock(World world, int x, int y, int z) {
        if (world == null) return;
        world.setBlockToAir(x, y, z);
    }

    public static void breakBlock(World world, int x, int y, int z, boolean dropItems) {
        if (world == null) return;
        if (dropItems) {
            Block block = world.getBlock(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            block.dropBlockAsItem(world, x, y, z, meta, 0);
        }
        world.setBlockToAir(x, y, z);
    }

    public static TileEntity getTileEntity(World world, int x, int y, int z) {
        if (world == null) return null;
        return world.getTileEntity(x, y, z);
    }

    public static void removeTileEntity(World world, int x, int y, int z) {
        if (world == null) return;
        world.removeTileEntity(x, y, z);
    }

    public static boolean isBlockSolid(World world, int x, int y, int z) {
        if (world == null) return false;
        Block block = world.getBlock(x, y, z);
        return block != null && block.isOpaqueCube();
    }

    public static int getLightLevel(World world, int x, int y, int z) {
        if (world == null) return 0;
        return world.getBlockLightValue(x, y, z);
    }

    // ========== EXPLOSIONS ==========

    public static void createExplosion(World world, double x, double y, double z, float power) {
        if (world == null) return;
        world.createExplosion(null, x, y, z, power, false);
    }

    public static void createExplosion(World world, Entity entity, double x, double y, double z, float power, boolean fire) {
        if (world == null) return;
        world.createExplosion(entity, x, y, z, power, fire);
    }

    public static void createExplosionWithDamage(World world, double x, double y, double z, float power, boolean fire, boolean destroyBlocks) {
        if (world == null) return;
        world.newExplosion(null, x, y, z, power, fire, destroyBlocks);
    }

    // ========== WEATHER ==========

    public static boolean isRaining(World world) {
        if (world == null) return false;
        return world.isRaining();
    }

    public static void setRaining(World world, boolean raining) {
        if (world == null) return;
        WorldInfo info = world.getWorldInfo();
        if (info != null) {
            info.setRaining(raining);
        }
    }

    public static boolean isThundering(World world) {
        if (world == null) return false;
        return world.isThundering();
    }

    public static void setThundering(World world, boolean thundering) {
        if (world == null) return;
        WorldInfo info = world.getWorldInfo();
        if (info != null) {
            info.setThundering(thundering);
        }
    }

    public static int getRainTime(World world) {
        if (world == null) return 0;
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getRainTime() : 0;
    }

    public static void setRainTime(World world, int time) {
        if (world == null) return;
        WorldInfo info = world.getWorldInfo();
        if (info != null) {
            info.setRainTime(time);
        }
    }

    public static int getThunderTime(World world) {
        if (world == null) return 0;
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getThunderTime() : 0;
    }

    public static void setThunderTime(World world, int time) {
        if (world == null) return;
        WorldInfo info = world.getWorldInfo();
        if (info != null) {
            info.setThunderTime(time);
        }
    }

    public static void clearWeather(World world) {
        if (world == null) return;
        setRaining(world, false);
        setThundering(world, false);
        setRainTime(world, 0);
        setThunderTime(world, 0);
    }

    // ========== TIME ==========

    public static long getWorldTime(World world) {
        if (world == null) return 0;
        return world.getWorldTime();
    }

    public static void setWorldTime(World world, long time) {
        if (world == null) return;
        world.setWorldTime(time);
    }

    public static long getTotalWorldTime(World world) {
        if (world == null) return 0;
        return world.getTotalWorldTime();
    }

    public static void addWorldTime(World world, long time) {
        if (world == null) return;
        world.setWorldTime(world.getWorldTime() + time);
    }

    public static void setDay(World world) {
        if (world == null) return;
        world.setWorldTime(1000);
    }

    public static void setNight(World world) {
        if (world == null) return;
        world.setWorldTime(13000);
    }

    public static void setNoon(World world) {
        if (world == null) return;
        world.setWorldTime(6000);
    }

    public static void setMidnight(World world) {
        if (world == null) return;
        world.setWorldTime(18000);
    }

    public static boolean isDaytime(World world) {
        if (world == null) return false;
        long time = world.getWorldTime() % 24000;
        return time < 12000;
    }

    public static boolean isNighttime(World world) {
        if (world == null) return false;
        return !isDaytime(world);
    }

    // ========== ENTITY ==========

    public static void spawnEntity(World world, Entity entity) {
        if (world == null || entity == null) return;
        world.spawnEntityInWorld(entity);
    }

    public static void removeEntity(World world, Entity entity) {
        if (world == null || entity == null) return;
        world.removeEntity(entity);
    }

    public static List<Entity> getEntitiesInRange(World world, double x, double y, double z, double range) {
        if (world == null) return new ArrayList<Entity>();
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        return world.getEntitiesWithinAABB(Entity.class, aabb);
    }

    public static List<EntityPlayer> getPlayersInRange(World world, double x, double y, double z, double range) {
        if (world == null) return new ArrayList<EntityPlayer>();
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        return world.getEntitiesWithinAABB(EntityPlayer.class, aabb);
    }

    public static List<EntityLiving> getLivingEntitiesInRange(World world, double x, double y, double z, double range) {
        if (world == null) return new ArrayList<EntityLiving>();
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
        return world.getEntitiesWithinAABB(EntityLiving.class, aabb);
    }

    public static void killAllEntities(World world, Class<? extends Entity> entityClass) {
        if (world == null || entityClass == null) return;
        List<Entity> entities = new ArrayList<Entity>();
        for (Object obj : world.loadedEntityList) {
            if (entityClass.isInstance(obj)) {
                entities.add((Entity) obj);
            }
        }
        for (Entity entity : entities) {
            entity.setDead();
        }
    }

    public static void killAllMobs(World world) {
        if (world == null) return;
        killAllEntities(world, EntityLiving.class);
    }

    public static int getEntityCount(World world, Class<? extends Entity> entityClass) {
        if (world == null || entityClass == null) return 0;
        int count = 0;
        for (Object obj : world.loadedEntityList) {
            if (entityClass.isInstance(obj)) {
                count++;
            }
        }
        return count;
    }

    // ========== LIGHTNING ==========

    public static void strikeLightning(World world, double x, double y, double z) {
        if (world == null) return;
        EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);
        world.spawnEntityInWorld(lightning);
    }

    public static void strikeLightningWithoutFire(World world, double x, double y, double z) {
        if (world == null) return;
        EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);
        world.addWeatherEffect(lightning);
    }

    // ========== ITEM ON THE FLOOR ==========

    public static void dropItem(World world, double x, double y, double z, ItemStack stack) {
        if (world == null || stack == null) return;
        EntityItem entityItem = new EntityItem(world, x, y, z, stack);
        world.spawnEntityInWorld(entityItem);
    }

    public static void dropItemWithMotion(World world, double x, double y, double z, ItemStack stack, double motionX, double motionY, double motionZ) {
        if (world == null || stack == null) return;
        EntityItem entityItem = new EntityItem(world, x, y, z, stack);
        entityItem.motionX = motionX;
        entityItem.motionY = motionY;
        entityItem.motionZ = motionZ;
        world.spawnEntityInWorld(entityItem);
    }

    public static void clearAllItems(World world) {
        if (world == null) return;
        killAllEntities(world, EntityItem.class);
    }

    // ========== DIFFICULTY ==========

    public static EnumDifficulty getDifficulty(World world) {
        if (world == null) return EnumDifficulty.NORMAL;
        return world.difficultySetting;
    }

    public static void setDifficulty(World world, EnumDifficulty difficulty) {
        if (world == null || difficulty == null) return;
        world.difficultySetting = difficulty;
    }

    public static void setPeaceful(World world) {
        setDifficulty(world, EnumDifficulty.PEACEFUL);
    }

    public static void setEasy(World world) {
        setDifficulty(world, EnumDifficulty.EASY);
    }

    public static void setNormal(World world) {
        setDifficulty(world, EnumDifficulty.NORMAL);
    }

    public static void setHard(World world) {
        setDifficulty(world, EnumDifficulty.HARD);
    }

    // ========== CHUNKS ==========

    public static Chunk getChunk(World world, int chunkX, int chunkZ) {
        if (world == null) return null;
        return world.getChunkFromChunkCoords(chunkX, chunkZ);
    }

    public static boolean isChunkLoaded(World world, int chunkX, int chunkZ) {
        if (world == null) return false;
        return world.getChunkProvider().chunkExists(chunkX, chunkZ);
    }

    public static void loadChunk(World world, int chunkX, int chunkZ) {
        if (world == null) return;
        world.getChunkFromChunkCoords(chunkX, chunkZ);
    }

    public static void unloadChunk(World world, int chunkX, int chunkZ) {
        if (world == null) return;
        Chunk chunk = world.getChunkProvider().provideChunk(chunkX, chunkZ);
        if (chunk != null) {
            world.getChunkProvider().unloadQueuedChunks();
        }
    }

    // ========== BIOME ==========

    public static BiomeGenBase getBiome(World world, int x, int z) {
        if (world == null) return null;
        return world.getBiomeGenForCoords(x, z);
    }

    public static String getBiomeName(World world, int x, int z) {
        if (world == null) return "Unknown";
        BiomeGenBase biome = getBiome(world, x, z);
        return biome != null ? biome.biomeName : "Unknown";
    }

    public static float getBiomeTemperature(World world, int x, int z) {
        if (world == null) return 0.5f;
        BiomeGenBase biome = getBiome(world, x, z);
        return biome != null ? biome.temperature : 0.5f;
    }

    public static float getBiomeRainfall(World world, int x, int z) {
        if (world == null) return 0.5f;
        BiomeGenBase biome = getBiome(world, x, z);
        return biome != null ? biome.rainfall : 0.5f;
    }

    // ========== SPAWN ==========

    public static ChunkCoordinates getSpawnPoint(World world) {
        if (world == null) return null;
        WorldInfo info = world.getWorldInfo();
        if (info == null) return null;
        return new ChunkCoordinates(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());
    }

    public static void setSpawnPoint(World world, int x, int y, int z) {
        if (world == null) return;
        WorldInfo info = world.getWorldInfo();
        if (info != null) {
            info.setSpawnPosition(x, y, z);
        }
    }

    public static int getSpawnX(World world) {
        if (world == null) return 0;
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getSpawnX() : 0;
    }

    public static int getSpawnY(World world) {
        if (world == null) return 0;
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getSpawnY() : 0;
    }

    public static int getSpawnZ(World world) {
        if (world == null) return 0;
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getSpawnZ() : 0;
    }

    // ========== WORLD ==========

    public static boolean isRemote(World world) {
        if (world == null) return false;
        return world.isRemote;
    }

    public static String getWorldName(World world) {
        if (world == null) return "Unknown";
        WorldInfo info = world.getWorldInfo();
        return info != null ? info.getWorldName() : "Unknown";
    }

    public static int getDimensionId(World world) {
        if (world == null) return 0;
        return world.provider.dimensionId;
    }

    public static long getSeed(World world) {
        if (world == null) return 0;
        return world.getSeed();
    }

    public static Random getRandom(World world) {
        if (world == null) return new Random();
        return world.rand;
    }

    public static boolean canBlockSeeSky(World world, int x, int y, int z) {
        if (world == null) return false;
        return world.canBlockSeeTheSky(x, y, z);
    }

    public static int getTopSolidOrLiquidBlock(World world, int x, int z) {
        if (world == null) return 0;
        return world.getTopSolidOrLiquidBlock(x, z);
    }

    public static int getHeightValue(World world, int x, int z) {
        if (world == null) return 0;
        return world.getHeightValue(x, z);
    }

    // ========== GAMERULE ==========

    public static boolean getGameRule(World world, String rule) {
        if (world == null) return false;
        return world.getGameRules().getGameRuleBooleanValue(rule);
    }

    public static void setGameRule(World world, String rule, String value) {
        if (world == null) return;
        world.getGameRules().setOrCreateGameRule(rule, value);
    }

    public static void enableDaylightCycle(World world) {
        setGameRule(world, "doDaylightCycle", "true");
    }

    public static void disableDaylightCycle(World world) {
        setGameRule(world, "doDaylightCycle", "false");
    }

    public static void enableMobSpawning(World world) {
        setGameRule(world, "doMobSpawning", "true");
    }

    public static void disableMobSpawning(World world) {
        setGameRule(world, "doMobSpawning", "false");
    }

    public static void enableFireSpread(World world) {
        setGameRule(world, "doFireTick", "true");
    }

    public static void disableFireSpread(World world) {
        setGameRule(world, "doFireTick", "false");
    }

    public static void enableMobGriefing(World world) {
        setGameRule(world, "mobGriefing", "true");
    }

    public static void disableMobGriefing(World world) {
        setGameRule(world, "mobGriefing", "false");
    }

    public static void keepInventory(World world, boolean keep) {
        setGameRule(world, "keepInventory", keep ? "true" : "false");
    }

    // ========== UTILITIES ==========

    public static void playSound(World world, double x, double y, double z, String sound, float volume, float pitch) {
        if (world == null) return;
        world.playSoundEffect(x, y, z, sound, volume, pitch);
    }

    public static void spawnParticle(World world, String particle, double x, double y, double z, double velX, double velY, double velZ) {
        if (world == null) return;
        world.spawnParticle(particle, x, y, z, velX, velY, velZ);
    }

    public static void updateBlock(World world, int x, int y, int z) {
        if (world == null) return;
        world.markBlockForUpdate(x, y, z);
    }

    public static void updateBlockRange(World world, int x, int y, int z, int range) {
        if (world == null) return;
        world.markBlockRangeForRenderUpdate(x - range, y - range, z - range, x + range, y + range, z + range);
    }

    public static EntityPlayer getNearestPlayer(World world, double x, double y, double z, double range) {
        if (world == null) return null;
        return world.getClosestPlayer(x, y, z, range);
    }

    public static void fillArea(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block) {
        if (world == null || block == null) return;
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        int maxZ = Math.max(z1, z2);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.setBlock(x, y, z, block);
                }
            }
        }
    }

    public static void replaceArea(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block oldBlock, Block newBlock) {
        if (world == null || oldBlock == null || newBlock == null) return;
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        int maxZ = Math.max(z1, z2);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (world.getBlock(x, y, z) == oldBlock) {
                        world.setBlock(x, y, z, newBlock);
                    }
                }
            }
        }
    }

    public static void clearArea(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        fillArea(world, x1, y1, z1, x2, y2, z2, Blocks.air);
    }
}