package fr.zayzx.zayzlibs.utils;

import net.minecraft.nbt.*;

import java.util.ArrayList;
import java.util.List;

public class NBTAction {

    // ========== CRÉATION ==========

    /**
     * Crée un nouveau NBTTagCompound
     */
    public static NBTTagCompound createCompound() {
        return new NBTTagCompound();
    }

    /**
     * Crée un nouveau NBTTagList
     */
    public static NBTTagList createList() {
        return new NBTTagList();
    }

    // ========== GET/SET STRING ==========

    /**
     * Définit une valeur String
     */
    public static void setString(NBTTagCompound nbt, String key, String value) {
        if (nbt == null || key == null || value == null) return;
        nbt.setString(key, value);
    }

    /**
     * Obtient une valeur String
     */
    public static String getString(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return "";
        return nbt.getString(key);
    }

    /**
     * Obtient une valeur String avec valeur par défaut
     */
    public static String getString(NBTTagCompound nbt, String key, String defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getString(key);
    }

    // ========== GET/SET INTEGER ==========

    /**
     * Définit une valeur Integer
     */
    public static void setInt(NBTTagCompound nbt, String key, int value) {
        if (nbt == null || key == null) return;
        nbt.setInteger(key, value);
    }

    /**
     * Obtient une valeur Integer
     */
    public static int getInt(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0;
        return nbt.getInteger(key);
    }

    /**
     * Obtient une valeur Integer avec valeur par défaut
     */
    public static int getInt(NBTTagCompound nbt, String key, int defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getInteger(key);
    }

    // ========== GET/SET FLOAT ==========

    /**
     * Définit une valeur Float
     */
    public static void setFloat(NBTTagCompound nbt, String key, float value) {
        if (nbt == null || key == null) return;
        nbt.setFloat(key, value);
    }

    /**
     * Obtient une valeur Float
     */
    public static float getFloat(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0.0F;
        return nbt.getFloat(key);
    }

    /**
     * Obtient une valeur Float avec valeur par défaut
     */
    public static float getFloat(NBTTagCompound nbt, String key, float defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getFloat(key);
    }

    // ========== GET/SET DOUBLE ==========

    /**
     * Définit une valeur Double
     */
    public static void setDouble(NBTTagCompound nbt, String key, double value) {
        if (nbt == null || key == null) return;
        nbt.setDouble(key, value);
    }

    /**
     * Obtient une valeur Double
     */
    public static double getDouble(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0.0;
        return nbt.getDouble(key);
    }

    /**
     * Obtient une valeur Double avec valeur par défaut
     */
    public static double getDouble(NBTTagCompound nbt, String key, double defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getDouble(key);
    }

    // ========== GET/SET BOOLEAN ==========

    /**
     * Définit une valeur Boolean
     */
    public static void setBoolean(NBTTagCompound nbt, String key, boolean value) {
        if (nbt == null || key == null) return;
        nbt.setBoolean(key, value);
    }

    /**
     * Obtient une valeur Boolean
     */
    public static boolean getBoolean(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return false;
        return nbt.getBoolean(key);
    }

    /**
     * Obtient une valeur Boolean avec valeur par défaut
     */
    public static boolean getBoolean(NBTTagCompound nbt, String key, boolean defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getBoolean(key);
    }

    // ========== GET/SET LONG ==========

    /**
     * Définit une valeur Long
     */
    public static void setLong(NBTTagCompound nbt, String key, long value) {
        if (nbt == null || key == null) return;
        nbt.setLong(key, value);
    }

    /**
     * Obtient une valeur Long
     */
    public static long getLong(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0L;
        return nbt.getLong(key);
    }

    /**
     * Obtient une valeur Long avec valeur par défaut
     */
    public static long getLong(NBTTagCompound nbt, String key, long defaultValue) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return defaultValue;
        return nbt.getLong(key);
    }

    // ========== GET/SET BYTE ==========

    /**
     * Définit une valeur Byte
     */
    public static void setByte(NBTTagCompound nbt, String key, byte value) {
        if (nbt == null || key == null) return;
        nbt.setByte(key, value);
    }

    /**
     * Obtient une valeur Byte
     */
    public static byte getByte(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0;
        return nbt.getByte(key);
    }

    // ========== GET/SET SHORT ==========

    /**
     * Définit une valeur Short
     */
    public static void setShort(NBTTagCompound nbt, String key, short value) {
        if (nbt == null || key == null) return;
        nbt.setShort(key, value);
    }

    /**
     * Obtient une valeur Short
     */
    public static short getShort(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return 0;
        return nbt.getShort(key);
    }

    // ========== GET/SET COMPOUND ==========

    /**
     * Définit un NBTTagCompound
     */
    public static void setCompound(NBTTagCompound nbt, String key, NBTTagCompound value) {
        if (nbt == null || key == null || value == null) return;
        nbt.setTag(key, value);
    }

    /**
     * Obtient un NBTTagCompound
     */
    public static NBTTagCompound getCompound(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return new NBTTagCompound();
        return nbt.getCompoundTag(key);
    }

    // ========== GET/SET LIST ==========

    /**
     * Définit un NBTTagList
     */
    public static void setList(NBTTagCompound nbt, String key, NBTTagList list) {
        if (nbt == null || key == null || list == null) return;
        nbt.setTag(key, list);
    }

    /**
     * Obtient un NBTTagList
     */
    public static NBTTagList getList(NBTTagCompound nbt, String key, int type) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return new NBTTagList();
        return nbt.getTagList(key, type);
    }

    // ========== ARRAYS ==========

    /**
     * Définit un int array
     */
    public static void setIntArray(NBTTagCompound nbt, String key, int[] array) {
        if (nbt == null || key == null || array == null) return;
        nbt.setIntArray(key, array);
    }

    /**
     * Obtient un int array
     */
    public static int[] getIntArray(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return new int[0];
        return nbt.getIntArray(key);
    }

    /**
     * Définit un byte array
     */
    public static void setByteArray(NBTTagCompound nbt, String key, byte[] array) {
        if (nbt == null || key == null || array == null) return;
        nbt.setByteArray(key, array);
    }

    /**
     * Obtient un byte array
     */
    public static byte[] getByteArray(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null || !nbt.hasKey(key)) return new byte[0];
        return nbt.getByteArray(key);
    }

    // ========== VÉRIFICATIONS ==========

    /**
     * Vérifie si une clé existe
     */
    public static boolean hasKey(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null) return false;
        return nbt.hasKey(key);
    }


    /**
     * Vérifie si le NBT est vide
     */
    public static boolean isEmpty(NBTTagCompound nbt) {
        if (nbt == null) return true;
        return nbt.hasNoTags();
    }

    // ========== SUPPRESSION ==========

    /**
     * Supprime une clé
     */
    public static void removeKey(NBTTagCompound nbt, String key) {
        if (nbt == null || key == null) return;
        nbt.removeTag(key);
    }

    /**
     * Vide complètement un NBT
     */
    public static void clear(NBTTagCompound nbt) {
        if (nbt == null) return;
        for (Object key : nbt.func_150296_c().toArray()) {
            nbt.removeTag((String) key);
        }
    }

    // ========== COPIE ET FUSION ==========

    /**
     * Copie un NBTTagCompound
     */
    public static NBTTagCompound copy(NBTTagCompound nbt) {
        if (nbt == null) return new NBTTagCompound();
        return (NBTTagCompound) nbt.copy();
    }

    /**
     * Fusionne deux NBT (le second écrase les valeurs du premier)
     */
    public static NBTTagCompound merge(NBTTagCompound nbt1, NBTTagCompound nbt2) {
        if (nbt1 == null && nbt2 == null) return new NBTTagCompound();
        if (nbt1 == null) return copy(nbt2);
        if (nbt2 == null) return copy(nbt1);
        
        NBTTagCompound result = copy(nbt1);
        
        for (Object keyObj : nbt2.func_150296_c()) {
            String key = (String) keyObj;
            result.setTag(key, nbt2.getTag(key).copy());
        }
        
        return result;
    }

    /**
     * Copie toutes les clés d'un NBT à un autre
     */
    public static void copyTo(NBTTagCompound from, NBTTagCompound to) {
        if (from == null || to == null) return;
        
        for (Object keyObj : from.func_150296_c()) {
            String key = (String) keyObj;
            to.setTag(key, from.getTag(key).copy());
        }
    }

    // ========== COMPARAISON ==========

    /**
     * Compare deux NBT (égalité stricte)
     */
    public static boolean equals(NBTTagCompound nbt1, NBTTagCompound nbt2) {
        if (nbt1 == null && nbt2 == null) return true;
        if (nbt1 == null || nbt2 == null) return false;
        return nbt1.equals(nbt2);
    }

    /**
     * Vérifie si un NBT contient toutes les clés d'un autre
     */
    public static boolean contains(NBTTagCompound nbt, NBTTagCompound subset) {
        if (nbt == null || subset == null) return false;
        
        for (Object keyObj : subset.func_150296_c()) {
            String key = (String) keyObj;
            if (!nbt.hasKey(key)) return false;
            
            NBTBase tag1 = nbt.getTag(key);
            NBTBase tag2 = subset.getTag(key);
            
            if (!tag1.equals(tag2)) return false;
        }
        
        return true;
    }

    // ========== LISTES ==========

    /**
     * Ajoute un String à une liste
     */
    public static void addStringToList(NBTTagList list, String value) {
        if (list == null || value == null) return;
        list.appendTag(new NBTTagString(value));
    }

    /**
     * Ajoute un Compound à une liste
     */
    public static void addCompoundToList(NBTTagList list, NBTTagCompound compound) {
        if (list == null || compound == null) return;
        list.appendTag(compound);
    }

    /**
     * Obtient un String d'une liste
     */
    public static String getStringFromList(NBTTagList list, int index) {
        if (list == null || index < 0 || index >= list.tagCount()) return "";
        return list.getStringTagAt(index);
    }

    /**
     * Obtient un Compound d'une liste
     */
    public static NBTTagCompound getCompoundFromList(NBTTagList list, int index) {
        if (list == null || index < 0 || index >= list.tagCount()) return new NBTTagCompound();
        return list.getCompoundTagAt(index);
    }

    /**
     * Obtient la taille d'une liste
     */
    public static int getListSize(NBTTagList list) {
        if (list == null) return 0;
        return list.tagCount();
    }

    /**
     * Convertit une liste de Strings en List Java
     */
    public static List<String> stringListToJavaList(NBTTagList list) {
        List<String> result = new ArrayList<String>();
        if (list == null) return result;
        
        for (int i = 0; i < list.tagCount(); i++) {
            result.add(list.getStringTagAt(i));
        }
        
        return result;
    }

    /**
     * Convertit une List Java en NBTTagList de Strings
     */
    public static NBTTagList javaListToStringList(List<String> list) {
        NBTTagList nbtList = new NBTTagList();
        if (list == null) return nbtList;
        
        for (String s : list) {
            nbtList.appendTag(new NBTTagString(s));
        }
        
        return nbtList;
    }

    // ========== INFORMATIONS ==========

    /**
     * Obtient toutes les clés
     */
    public static List<String> getKeys(NBTTagCompound nbt) {
        List<String> keys = new ArrayList<String>();
        if (nbt == null) return keys;
        
        for (Object keyObj : nbt.func_150296_c()) {
            keys.add((String) keyObj);
        }
        
        return keys;
    }

    /**
     * Obtient le nombre de clés
     */
    public static int getKeyCount(NBTTagCompound nbt) {
        if (nbt == null) return 0;
        return nbt.func_150296_c().size();
    }

    /**
     * Affiche le NBT (debug)
     */
    public static String toString(NBTTagCompound nbt) {
        if (nbt == null) return "null";
        return nbt.toString();
    }

    /**
     * Affiche le NBT de manière formatée (debug)
     */
    public static void printNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            System.out.println("NBT is null");
            return;
        }
        
        System.out.println("NBT Content:");
        printNBTRecursive(nbt, 0);
    }

    private static void printNBTRecursive(NBTTagCompound nbt, int indent) {
        String indentStr = "";
        for (int i = 0; i < indent; i++) indentStr += "  ";
        
        for (Object keyObj : nbt.func_150296_c()) {
            String key = (String) keyObj;
            NBTBase tag = nbt.getTag(key);
            
            if (tag instanceof NBTTagCompound) {
                System.out.println(indentStr + key + ": {");
                printNBTRecursive((NBTTagCompound) tag, indent + 1);
                System.out.println(indentStr + "}");
            } else if (tag instanceof NBTTagList) {
                System.out.println(indentStr + key + ": [...]");
            } else {
                System.out.println(indentStr + key + ": " + tag.toString());
            }
        }
    }

    // ========== TYPES NBT ==========

    public static final int TAG_END = 0;
    public static final int TAG_BYTE = 1;
    public static final int TAG_SHORT = 2;
    public static final int TAG_INT = 3;
    public static final int TAG_LONG = 4;
    public static final int TAG_FLOAT = 5;
    public static final int TAG_DOUBLE = 6;
    public static final int TAG_BYTE_ARRAY = 7;
    public static final int TAG_STRING = 8;
    public static final int TAG_LIST = 9;
    public static final int TAG_COMPOUND = 10;
    public static final int TAG_INT_ARRAY = 11;
}