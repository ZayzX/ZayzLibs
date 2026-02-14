package fr.zayzx.zayzlibs.utils;

import net.minecraft.world.World;

public class ParticleAction {

    // ========== SPAWN PARTICULES ==========

    public static void spawnParticle(World world, String particleName, double x, double y, double z) {
        if (world == null || particleName == null) return;
        world.spawnParticle(particleName, x, y, z, 0, 0, 0);
    }

    public static void spawnParticle(World world, String particleName, double x, double y, double z, double velX, double velY, double velZ) {
        if (world == null || particleName == null) return;
        world.spawnParticle(particleName, x, y, z, velX, velY, velZ);
    }

    public static void spawnParticles(World world, String particleName, double x, double y, double z, int count) {
        if (world == null || particleName == null) return;
        for (int i = 0; i < count; i++) {
            world.spawnParticle(particleName, x, y, z, 0, 0, 0);
        }
    }

    public static void spawnParticlesWithVelocity(World world, String particleName, double x, double y, double z, int count, double velX, double velY, double velZ) {
        if (world == null || particleName == null) return;
        for (int i = 0; i < count; i++) {
            world.spawnParticle(particleName, x, y, z, velX, velY, velZ);
        }
    }

    public static void spawnRandomParticles(World world, String particleName, double x, double y, double z, int count, double spread) {
        if (world == null || particleName == null) return;
        for (int i = 0; i < count; i++) {
            double offsetX = (world.rand.nextDouble() - 0.5) * spread;
            double offsetY = (world.rand.nextDouble() - 0.5) * spread;
            double offsetZ = (world.rand.nextDouble() - 0.5) * spread;
            world.spawnParticle(particleName, x + offsetX, y + offsetY, z + offsetZ, 0, 0, 0);
        }
    }

    // ========== FORMES ==========

    public static void spawnParticleLine(World world, String particleName, double x1, double y1, double z1, double x2, double y2, double z2, double density) {
        if (world == null || particleName == null) return;
        
        double distance = MathAction.distance3D(x1, y1, z1, x2, y2, z2);
        int particles = (int) (distance * density);
        
        for (int i = 0; i <= particles; i++) {
            double t = (double) i / particles;
            double x = MathAction.lerp(x1, x2, t);
            double y = MathAction.lerp(y1, y2, t);
            double z = MathAction.lerp(z1, z2, t);
            world.spawnParticle(particleName, x, y, z, 0, 0, 0);
        }
    }

    public static void spawnParticleCircle(World world, String particleName, double centerX, double centerY, double centerZ, double radius, int particles) {
        if (world == null || particleName == null) return;
        
        for (int i = 0; i < particles; i++) {
            double angle = 2 * Math.PI * i / particles;
            double x = centerX + radius * Math.cos(angle);
            double z = centerZ + radius * Math.sin(angle);
            world.spawnParticle(particleName, x, centerY, z, 0, 0, 0);
        }
    }

    public static void spawnParticleSphere(World world, String particleName, double centerX, double centerY, double centerZ, double radius, int particles) {
        if (world == null || particleName == null) return;
        
        for (int i = 0; i < particles; i++) {
            double[] point = MathAction.randomPointInSphere(world.rand, radius);
            world.spawnParticle(particleName, centerX + point[0], centerY + point[1], centerZ + point[2], 0, 0, 0);
        }
    }

    public static void spawnParticleSpiral(World world, String particleName, double centerX, double baseY, double centerZ, double radius, double height, int rotations, int particlesPerRotation) {
        if (world == null || particleName == null) return;
        
        int totalParticles = rotations * particlesPerRotation;
        
        for (int i = 0; i < totalParticles; i++) {
            double t = (double) i / totalParticles;
            double angle = 2 * Math.PI * rotations * t;
            double x = centerX + radius * Math.cos(angle);
            double y = baseY + height * t;
            double z = centerZ + radius * Math.sin(angle);
            world.spawnParticle(particleName, x, y, z, 0, 0, 0);
        }
    }

    public static void spawnParticleHelix(World world, String particleName, double centerX, double baseY, double centerZ, double radius, double height, int helixes, int particlesPerHelix) {
        if (world == null || particleName == null) return;
        
        for (int h = 0; h < helixes; h++) {
            double angleOffset = 2 * Math.PI * h / helixes;
            
            for (int i = 0; i < particlesPerHelix; i++) {
                double t = (double) i / particlesPerHelix;
                double angle = 2 * Math.PI * 3 * t + angleOffset;
                double x = centerX + radius * Math.cos(angle);
                double y = baseY + height * t;
                double z = centerZ + radius * Math.sin(angle);
                world.spawnParticle(particleName, x, y, z, 0, 0, 0);
            }
        }
    }

    // ========== EFFETS SPÉCIAUX ==========

    public static void spawnExplosion(World world, double x, double y, double z, int particles) {
        if (world == null) return;
        
        for (int i = 0; i < particles; i++) {
            double velX = (world.rand.nextDouble() - 0.5) * 2;
            double velY = (world.rand.nextDouble() - 0.5) * 2;
            double velZ = (world.rand.nextDouble() - 0.5) * 2;
            world.spawnParticle("flame", x, y, z, velX, velY, velZ);
            world.spawnParticle("smoke", x, y, z, velX * 0.5, velY * 0.5, velZ * 0.5);
        }
    }

    public static void spawnPortalEffect(World world, double x, double y, double z, int particles) {
        spawnRandomParticles(world, "portal", x, y, z, particles, 2.0);
    }

    public static void spawnMagicEffect(World world, double x, double y, double z, int particles) {
        spawnRandomParticles(world, "magicCrit", x, y, z, particles, 1.5);
    }

    public static void spawnEnchantEffect(World world, double x, double y, double z, int particles) {
        spawnRandomParticles(world, "enchantmenttable", x, y, z, particles, 2.0);
    }

    public static void spawnHealEffect(World world, double x, double y, double z, int particles) {
        for (int i = 0; i < particles; i++) {
            double velX = (world.rand.nextDouble() - 0.5) * 0.2;
            double velY = world.rand.nextDouble() * 0.5;
            double velZ = (world.rand.nextDouble() - 0.5) * 0.2;
            world.spawnParticle("happyVillager", x, y, z, velX, velY, velZ);
        }
    }

    public static void spawnLoveEffect(World world, double x, double y, double z, int particles) {
        spawnRandomParticles(world, "heart", x, y, z, particles, 1.0);
    }

    public static void spawnAngryEffect(World world, double x, double y, double z, int particles) {
        spawnRandomParticles(world, "angryVillager", x, y, z, particles, 1.0);
    }

    // ========== TRAILS ==========

    public static void spawnTrail(World world, String particleName, double x, double y, double z, double lastX, double lastY, double lastZ, int density) {
        spawnParticleLine(world, particleName, lastX, lastY, lastZ, x, y, z, density);
    }

    public static void spawnFireTrail(World world, double x, double y, double z, double lastX, double lastY, double lastZ) {
        spawnTrail(world, "flame", x, y, z, lastX, lastY, lastZ, 5);
        spawnTrail(world, "smoke", x, y, z, lastX, lastY, lastZ, 3);
    }

    public static void spawnMagicTrail(World world, double x, double y, double z, double lastX, double lastY, double lastZ) {
        spawnTrail(world, "magicCrit", x, y, z, lastX, lastY, lastZ, 5);
        spawnTrail(world, "portal", x, y, z, lastX, lastY, lastZ, 3);
    }

    // ========== NOMS DE PARTICULES ==========

    public static final String FLAME = "flame";
    public static final String SMOKE = "smoke";
    public static final String LARGE_SMOKE = "largesmoke";
    public static final String CLOUD = "cloud";
    public static final String PORTAL = "portal";
    public static final String ENCHANT = "enchantmenttable";
    public static final String CRIT = "crit";
    public static final String MAGIC_CRIT = "magicCrit";
    public static final String HEART = "heart";
    public static final String ANGRY_VILLAGER = "angryVillager";
    public static final String HAPPY_VILLAGER = "happyVillager";
    public static final String NOTE = "note";
    public static final String SPLASH = "splash";
    public static final String WATER_WAKE = "wake";
    public static final String BUBBLE = "bubble";
    public static final String SUSPEND = "suspend";
    public static final String DEPTH_SUSPEND = "depthsuspend";
    public static final String TOWN_AURA = "townaura";
    public static final String REDDUST = "reddust";
    public static final String SNOWBALL_POOF = "snowballpoof";
    public static final String SNOW_SHOVEL = "snowshovel";
    public static final String SLIME = "slime";
    public static final String LAVA = "lava";
    public static final String FOOTSTEP = "footstep";
    public static final String EXPLODE = "explode";
    public static final String HUGE_EXPLOSION = "hugeexplosion";
    public static final String FIREWORKS_SPARK = "fireworksSpark";
}