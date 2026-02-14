package fr.zayzx.zayzlibs.utils;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import java.util.Random;

public class MathAction {

    // ========== DISTANCES ==========

    /**
     * Calcule la distance 2D
     */
    public static double distance2D(double x1, double z1, double x2, double z2) {
        double dx = x2 - x1;
        double dz = z2 - z1;
        return Math.sqrt(dx * dx + dz * dz);
    }

    /**
     * Calcule la distance 3D
     */
    public static double distance3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Calcule la distance au carré (plus rapide, évite sqrt)
     */
    public static double distanceSquared(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calcule la distance Manhattan (taxicab)
     */
    public static double distanceManhattan(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) + Math.abs(z2 - z1);
    }

    // ========== VECTEURS ==========

    /**
     * Normalise un vecteur
     */
    public static Vec3 normalize(double x, double y, double z) {
        double length = Math.sqrt(x * x + y * y + z * z);
        if (length == 0) return Vec3.createVectorHelper(0, 0, 0);
        return Vec3.createVectorHelper(x / length, y / length, z / length);
    }

    /**
     * Longueur d'un vecteur
     */
    public static double vectorLength(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Produit scalaire (dot product)
     */
    public static double dotProduct(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    /**
     * Produit vectoriel (cross product)
     */
    public static Vec3 crossProduct(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Vec3.createVectorHelper(
            y1 * z2 - z1 * y2,
            z1 * x2 - x1 * z2,
            x1 * y2 - y1 * x2
        );
    }

    /**
     * Angle entre deux vecteurs (en radians)
     */
    public static double angleBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dot = dotProduct(x1, y1, z1, x2, y2, z2);
        double len1 = vectorLength(x1, y1, z1);
        double len2 = vectorLength(x2, y2, z2);
        return Math.acos(dot / (len1 * len2));
    }

    /**
     * Multiplie un vecteur par un scalaire
     */
    public static Vec3 multiplyVector(double x, double y, double z, double scalar) {
        return Vec3.createVectorHelper(x * scalar, y * scalar, z * scalar);
    }

    /**
     * Additionne deux vecteurs
     */
    public static Vec3 addVectors(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Vec3.createVectorHelper(x1 + x2, y1 + y2, z1 + z2);
    }

    /**
     * Soustrait deux vecteurs
     */
    public static Vec3 subtractVectors(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Vec3.createVectorHelper(x1 - x2, y1 - y2, z1 - z2);
    }

    // ========== ROTATION ==========

    /**
     * Rotation autour de l'axe Y (yaw)
     */
    public static Vec3 rotateAroundY(double x, double z, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return Vec3.createVectorHelper(
            x * cos - z * sin,
            0,
            x * sin + z * cos
        );
    }

    /**
     * Rotation autour de l'axe X (pitch)
     */
    public static Vec3 rotateAroundX(double y, double z, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return Vec3.createVectorHelper(
            0,
            y * cos - z * sin,
            y * sin + z * cos
        );
    }

    /**
     * Rotation autour de l'axe Z (roll)
     */
    public static Vec3 rotateAroundZ(double x, double y, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return Vec3.createVectorHelper(
            x * cos - y * sin,
            x * sin + y * cos,
            0
        );
    }

    /**
     * Rotation d'un point autour d'un autre point (2D)
     */
    public static double[] rotatePoint2D(double x, double z, double centerX, double centerZ, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double dx = x - centerX;
        double dz = z - centerZ;
        return new double[]{
            centerX + dx * cos - dz * sin,
            centerZ + dx * sin + dz * cos
        };
    }

    // ========== CLAMP ET CONTRAINTES ==========

    /**
     * Limite une valeur entre min et max
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Limite une valeur entre min et max (float)
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Limite une valeur entre min et max (double)
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Wraparound d'une valeur (comme un angle)
     */
    public static float wrap(float value, float min, float max) {
        float range = max - min;
        while (value < min) value += range;
        while (value >= max) value -= range;
        return value;
    }

    /**
     * Wraparound d'un angle entre -180 et 180
     */
    public static float wrapAngle(float angle) {
        return MathHelper.wrapAngleTo180_float(angle);
    }

    // ========== INTERPOLATION ==========

    /**
     * Interpolation linéaire (lerp)
     */
    public static double lerp(double start, double end, double t) {
        return start + (end - start) * t;
    }

    /**
     * Interpolation linéaire (float)
     */
    public static float lerp(float start, float end, float t) {
        return start + (end - start) * t;
    }

    /**
     * Interpolation lisse (smoothstep)
     */
    public static double smoothstep(double start, double end, double t) {
        t = clamp(t, 0.0, 1.0);
        t = t * t * (3.0 - 2.0 * t);
        return lerp(start, end, t);
    }

    /**
     * Interpolation très lisse (smootherstep)
     */
    public static double smootherstep(double start, double end, double t) {
        t = clamp(t, 0.0, 1.0);
        t = t * t * t * (t * (t * 6.0 - 15.0) + 10.0);
        return lerp(start, end, t);
    }

    /**
     * Interpolation cosinus
     */
    public static double cosineInterpolation(double start, double end, double t) {
        double mu2 = (1.0 - Math.cos(t * Math.PI)) / 2.0;
        return start * (1.0 - mu2) + end * mu2;
    }

    // ========== RANDOM ==========

    /**
     * Random entre min et max (int)
     */
    public static int randomInt(Random random, int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    /**
     * Random entre min et max (float)
     */
    public static float randomFloat(Random random, float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Random entre min et max (double)
     */
    public static double randomDouble(Random random, double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    /**
     * Random boolean avec probabilité
     */
    public static boolean randomChance(Random random, float chance) {
        return random.nextFloat() < chance;
    }

    /**
     * Random point dans un cercle
     */
    public static double[] randomPointInCircle(Random random, double radius) {
        double angle = random.nextDouble() * 2 * Math.PI;
        double r = Math.sqrt(random.nextDouble()) * radius;
        return new double[]{r * Math.cos(angle), r * Math.sin(angle)};
    }

    /**
     * Random point dans une sphère
     */
    public static double[] randomPointInSphere(Random random, double radius) {
        double theta = random.nextDouble() * 2 * Math.PI;
        double phi = Math.acos(2 * random.nextDouble() - 1);
        double r = Math.cbrt(random.nextDouble()) * radius;
        
        return new double[]{
            r * Math.sin(phi) * Math.cos(theta),
            r * Math.sin(phi) * Math.sin(theta),
            r * Math.cos(phi)
        };
    }

    // ========== POURCENTAGES ==========

    /**
     * Calcule un pourcentage
     */
    public static double percentage(double value, double total) {
        if (total == 0) return 0;
        return (value / total) * 100.0;
    }

    /**
     * Calcule une valeur depuis un pourcentage
     */
    public static double fromPercentage(double percentage, double total) {
        return (percentage / 100.0) * total;
    }

    /**
     * Augmente une valeur d'un pourcentage
     */
    public static double increaseByPercent(double value, double percent) {
        return value * (1.0 + percent / 100.0);
    }

    /**
     * Diminue une valeur d'un pourcentage
     */
    public static double decreaseByPercent(double value, double percent) {
        return value * (1.0 - percent / 100.0);
    }

    // ========== CONVERSIONS D'ANGLES ==========

    /**
     * Degrés vers radians
     */
    public static double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    /**
     * Radians vers degrés
     */
    public static double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    // ========== ARRONDIS ==========

    /**
     * Arrondit à N décimales
     */
    public static double round(double value, int decimals) {
        double scale = Math.pow(10, decimals);
        return Math.round(value * scale) / scale;
    }

    /**
     * Arrondit au multiple le plus proche
     */
    public static int roundToNearest(int value, int multiple) {
        return Math.round((float) value / multiple) * multiple;
    }

    /**
     * Arrondit vers le bas au multiple
     */
    public static int floorToNearest(int value, int multiple) {
        return (value / multiple) * multiple;
    }

    /**
     * Arrondit vers le haut au multiple
     */
    public static int ceilToNearest(int value, int multiple) {
        return ((value + multiple - 1) / multiple) * multiple;
    }

    // ========== COLLISION ==========

    /**
     * Vérifie si deux cercles se chevauchent
     */
    public static boolean circlesOverlap(double x1, double z1, double r1, double x2, double z2, double r2) {
        double distance = distance2D(x1, z1, x2, z2);
        return distance < (r1 + r2);
    }

    /**
     * Vérifie si deux sphères se chevauchent
     */
    public static boolean spheresOverlap(double x1, double y1, double z1, double r1, double x2, double y2, double z2, double r2) {
        double distance = distance3D(x1, y1, z1, x2, y2, z2);
        return distance < (r1 + r2);
    }

    /**
     * Vérifie si un point est dans un rectangle
     */
    public static boolean pointInRectangle(double px, double pz, double x1, double z1, double x2, double z2) {
        return px >= Math.min(x1, x2) && px <= Math.max(x1, x2) &&
               pz >= Math.min(z1, z2) && pz <= Math.max(z1, z2);
    }

    /**
     * Vérifie si un point est dans un cercle
     */
    public static boolean pointInCircle(double px, double pz, double cx, double cz, double radius) {
        return distance2D(px, pz, cx, cz) <= radius;
    }

    /**
     * Vérifie si un point est dans une sphère
     */
    public static boolean pointInSphere(double px, double py, double pz, double cx, double cy, double cz, double radius) {
        return distance3D(px, py, pz, cx, cy, cz) <= radius;
    }

    // ========== COURBES DE BÉZIER ==========

    /**
     * Point sur une courbe de Bézier quadratique
     */
    public static double[] quadraticBezier(double t, double[] p0, double[] p1, double[] p2) {
        double u = 1 - t;
        double[] result = new double[p0.length];
        
        for (int i = 0; i < p0.length; i++) {
            result[i] = u * u * p0[i] + 2 * u * t * p1[i] + t * t * p2[i];
        }
        
        return result;
    }

    /**
     * Point sur une courbe de Bézier cubique
     */
    public static double[] cubicBezier(double t, double[] p0, double[] p1, double[] p2, double[] p3) {
        double u = 1 - t;
        double[] result = new double[p0.length];
        
        for (int i = 0; i < p0.length; i++) {
            result[i] = u * u * u * p0[i] + 
                       3 * u * u * t * p1[i] + 
                       3 * u * t * t * p2[i] + 
                       t * t * t * p3[i];
        }
        
        return result;
    }

    // ========== UTILITAIRES ==========

    /**
     * Valeur absolue
     */
    public static int abs(int value) {
        return Math.abs(value);
    }

    /**
     * Valeur absolue (float)
     */
    public static float abs(float value) {
        return Math.abs(value);
    }

    /**
     * Valeur absolue (double)
     */
    public static double abs(double value) {
        return Math.abs(value);
    }

    /**
     * Signe d'un nombre (-1, 0, ou 1)
     */
    public static int sign(double value) {
        if (value > 0) return 1;
        if (value < 0) return -1;
        return 0;
    }

    /**
     * Puissance
     */
    public static double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Racine carrée
     */
    public static double sqrt(double value) {
        return Math.sqrt(value);
    }

    /**
     * Minimum de plusieurs valeurs
     */
    public static int min(int... values) {
        int min = values[0];
        for (int value : values) {
            if (value < min) min = value;
        }
        return min;
    }

    /**
     * Maximum de plusieurs valeurs
     */
    public static int max(int... values) {
        int max = values[0];
        for (int value : values) {
            if (value > max) max = value;
        }
        return max;
    }

    /**
     * Moyenne de plusieurs valeurs
     */
    public static double average(double... values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    /**
     * Factorielle
     */
    public static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Vérifie si un nombre est pair
     */
    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    /**
     * Vérifie si un nombre est impair
     */
    public static boolean isOdd(int value) {
        return value % 2 != 0;
    }

    /**
     * Map une valeur d'une plage à une autre
     */
    public static double map(double value, double fromMin, double fromMax, double toMin, double toMax) {
        return (value - fromMin) * (toMax - toMin) / (fromMax - fromMin) + toMin;
    }
}