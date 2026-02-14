package fr.zayzx.zayzlibs.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SoundAction {

    // ========== JOUER DES SONS ==========

    public static void playSound(World world, double x, double y, double z, String sound, float volume, float pitch) {
        if (world == null || sound == null) return;
        world.playSoundEffect(x, y, z, sound, volume, pitch);
    }

    public static void playSound(World world, double x, double y, double z, String sound) {
        playSound(world, x, y, z, sound, 1.0F, 1.0F);
    }

    public static void playSoundToPlayer(EntityPlayer player, String sound, float volume, float pitch) {
        if (player == null || sound == null) return;
        player.playSound(sound, volume, pitch);
    }

    public static void playSoundToPlayer(EntityPlayer player, String sound) {
        playSoundToPlayer(player, sound, 1.0F, 1.0F);
    }

    public static void playSoundInRadius(World world, double x, double y, double z, double radius, String sound, float volume, float pitch) {
        if (world == null || sound == null) return;
        
        for (Object obj : world.playerEntities) {
            EntityPlayer player = (EntityPlayer) obj;
            double distance = MathAction.distance3D(x, y, z, player.posX, player.posY, player.posZ);
            if (distance <= radius) {
                playSoundToPlayer(player, sound, volume, pitch);
            }
        }
    }

    // ========== SONS DE BLOCS ==========

    public static void playBlockBreakSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.stone", 1.0F, 1.0F);
    }

    public static void playBlockPlaceSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.stone", 1.0F, 0.8F);
    }

    public static void playGlassBreakSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.glass", 1.0F, 1.0F);
    }

    public static void playWoodBreakSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.wood", 1.0F, 1.0F);
    }

    public static void playGravelSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.gravel", 1.0F, 1.0F);
    }

    public static void playSandSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.sand", 1.0F, 1.0F);
    }

    public static void playGrassSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "dig.grass", 1.0F, 1.0F);
    }

    // ========== SONS D'ENTITÉS ==========

    public static void playHurtSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "game.player.hurt", 1.0F, 1.0F);
    }

    public static void playDeathSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "game.player.die", 1.0F, 1.0F);
    }

    public static void playEatSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    }

    public static void playBurpSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    }

    public static void playLevelUpSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.levelup", 0.75F, 1.0F);
    }

    // ========== SONS D'ACTIONS ==========

    public static void playClickSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.click", 0.3F, 0.6F);
    }

    public static void playBowSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.bow", 1.0F, 1.0F);
    }

    public static void playArrowHitSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.bowhit", 1.0F, 1.2F);
    }

    public static void playDoorSound(World world, double x, double y, double z, boolean open) {
        if (open) {
            playSound(world, x, y, z, "random.door_open", 1.0F, 1.0F);
        } else {
            playSound(world, x, y, z, "random.door_close", 1.0F, 1.0F);
        }
    }

    public static void playAnvilSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.anvil_use", 1.0F, 1.0F);
    }

    public static void playChestSound(World world, double x, double y, double z, boolean open) {
        if (open) {
            playSound(world, x, y, z, "random.chestopen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        } else {
            playSound(world, x, y, z, "random.chestclosed", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    // ========== SONS AMBIANTS ==========

    public static void playThunderSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "ambient.weather.thunder", 10000.0F, 0.8F + world.rand.nextFloat() * 0.2F);
    }

    public static void playRainSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "ambient.weather.rain", 0.2F, 1.0F);
    }

    public static void playCaveSound(World world, double x, double y, double z) {
        int cave = world.rand.nextInt(19) + 1;
        playSound(world, x, y, z, "ambient.cave.cave" + cave, 0.7F, 0.8F + world.rand.nextFloat() * 0.2F);
    }

    // ========== EXPLOSIONS ==========

    public static void playExplosionSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.explode", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
    }

    public static void playLargeExplosionSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.explode", 8.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.5F);
    }

    // ========== SONS DE FEU ==========

    public static void playFireSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "fire.fire", 1.0F, 1.0F);
    }

    public static void playIgniteSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
    }

    public static void playFizzSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
    }

    // ========== SONS D'EAU ==========

    public static void playWaterSplashSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "liquid.splash", 0.2F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
    }

    public static void playSwimSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "liquid.swim", 0.2F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
    }

    // ========== SONS D'ANIMAUX ==========

    public static void playCowSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.cow.say", 1.0F, 1.0F);
    }

    public static void playPigSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.pig.say", 1.0F, 1.0F);
    }

    public static void playSheepSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.sheep.say", 1.0F, 1.0F);
    }

    public static void playChickenSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.chicken.say", 1.0F, 1.0F);
    }

    public static void playHorseSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.horse.idle", 1.0F, 1.0F);
    }

    public static void playWolfSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.wolf.bark", 1.0F, 1.0F);
    }

    public static void playCatSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.cat.meow", 1.0F, 1.0F);
    }

    // ========== SONS DE MONSTRES ==========

    public static void playZombieSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.zombie.say", 1.0F, 1.0F);
    }

    public static void playSkeletonSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.skeleton.say", 1.0F, 1.0F);
    }

    public static void playCreeperSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.creeper.say", 1.0F, 1.0F);
    }

    public static void playSpiderSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.spider.say", 1.0F, 1.0F);
    }

    public static void playEndermanSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.endermen.idle", 1.0F, 1.0F);
    }

    public static void playGhastSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.ghast.moan", 1.0F, 1.0F);
    }

    public static void playBlazeSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.blaze.breathe", 1.0F, 1.0F);
    }

    // ========== SONS DE VILLAGEOIS ==========

    public static void playVillagerSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.villager.idle", 1.0F, 1.0F);
    }

    public static void playVillagerYesSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.villager.yes", 1.0F, 1.0F);
    }

    public static void playVillagerNoSound(World world, double x, double y, double z) {
        playSound(world, x, y, z, "mob.villager.no", 1.0F, 1.0F);
    }

    // ========== SONS DE NOTE BLOCK ==========

    public static void playNoteBlockSound(World world, double x, double y, double z, int instrument, int note) {
        String[] instruments = {"harp", "bd", "snare", "hat", "bassattack"};
        if (instrument >= 0 && instrument < instruments.length) {
            float pitch = (float) Math.pow(2.0, (note - 12) / 12.0);
            playSound(world, x, y, z, "note." + instruments[instrument], 3.0F, pitch);
        }
    }

    // ========== UTILITAIRES ==========

    public static float randomPitch(World world) {
        return world.rand.nextFloat() * 0.4F + 0.8F;
    }

    public static float randomPitch(World world, float min, float max) {
        return min + world.rand.nextFloat() * (max - min);
    }
}