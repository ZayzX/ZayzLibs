package fr.zayzx.zayzlibs.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryAction {

    // ========== RECHERCHE D'ITEMS ==========

    /**
     * Trouve le premier slot contenant un item
     */
    public static int findItem(IInventory inventory, Item item) {
        if (inventory == null || item == null) return -1;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Trouve le premier slot contenant un ItemStack similaire
     */
    public static int findItemStack(IInventory inventory, ItemStack targetStack) {
        if (inventory == null || targetStack == null) return -1;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && ItemAction.areItemsEqual(stack, targetStack)) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Trouve tous les slots contenant un item
     */
    public static List<Integer> findAllItems(IInventory inventory, Item item) {
        List<Integer> slots = new ArrayList<Integer>();
        if (inventory == null || item == null) return slots;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                slots.add(i);
            }
        }
        
        return slots;
    }

    /**
     * Compte le nombre d'items d'un type
     */
    public static int countItem(IInventory inventory, Item item) {
        if (inventory == null || item == null) return 0;
        
        int count = 0;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                count += stack.stackSize;
            }
        }
        
        return count;
    }

    /**
     * Vérifie si l'inventaire contient un item
     */
    public static boolean hasItem(IInventory inventory, Item item, int count) {
        return countItem(inventory, item) >= count;
    }

    // ========== SLOTS VIDES ==========

    /**
     * Trouve le premier slot vide
     */
    public static int findFirstEmptySlot(IInventory inventory) {
        if (inventory == null) return -1;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i) == null) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Compte le nombre de slots vides
     */
    public static int countEmptySlots(IInventory inventory) {
        if (inventory == null) return 0;
        
        int count = 0;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i) == null) {
                count++;
            }
        }
        
        return count;
    }

    /**
     * Vérifie si l'inventaire est plein
     */
    public static boolean isFull(IInventory inventory) {
        return findFirstEmptySlot(inventory) == -1;
    }

    /**
     * Vérifie si l'inventaire est vide
     */
    public static boolean isEmpty(IInventory inventory) {
        if (inventory == null) return true;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i) != null) {
                return false;
            }
        }
        
        return true;
    }

    // ========== MANIPULATION D'ITEMS ==========

    /**
     * Ajoute un item à l'inventaire
     */
    public static boolean addItem(IInventory inventory, ItemStack stack) {
        if (inventory == null || stack == null) return false;
        
        // Essaie d'abord de stack avec des items existants
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack != null && ItemAction.canStack(slotStack, stack)) {
                int space = slotStack.getMaxStackSize() - slotStack.stackSize;
                if (space > 0) {
                    int toAdd = Math.min(space, stack.stackSize);
                    slotStack.stackSize += toAdd;
                    stack.stackSize -= toAdd;
                    
                    if (stack.stackSize <= 0) {
                        return true;
                    }
                }
            }
        }
        
        // Puis trouve un slot vide
        int emptySlot = findFirstEmptySlot(inventory);
        if (emptySlot != -1) {
            inventory.setInventorySlotContents(emptySlot, stack.copy());
            stack.stackSize = 0;
            return true;
        }
        
        return false;
    }

    /**
     * Retire un item de l'inventaire
     */
    public static boolean removeItem(IInventory inventory, Item item, int count) {
        if (inventory == null || item == null) return false;
        
        int remaining = count;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                if (stack.stackSize > remaining) {
                    stack.stackSize -= remaining;
                    return true;
                } else {
                    remaining -= stack.stackSize;
                    inventory.setInventorySlotContents(i, null);
                    
                    if (remaining <= 0) {
                        return true;
                    }
                }
            }
        }
        
        return remaining <= 0;
    }

    /**
     * Échange deux slots
     */
    public static void swapSlots(IInventory inventory, int slot1, int slot2) {
        if (inventory == null) return;
        
        ItemStack temp = inventory.getStackInSlot(slot1);
        inventory.setInventorySlotContents(slot1, inventory.getStackInSlot(slot2));
        inventory.setInventorySlotContents(slot2, temp);
    }

    /**
     * Déplace un item d'un slot à un autre
     */
    public static void moveItem(IInventory inventory, int fromSlot, int toSlot) {
        if (inventory == null) return;
        
        ItemStack fromStack = inventory.getStackInSlot(fromSlot);
        ItemStack toStack = inventory.getStackInSlot(toSlot);
        
        if (fromStack == null) return;
        
        if (toStack == null) {
            inventory.setInventorySlotContents(toSlot, fromStack);
            inventory.setInventorySlotContents(fromSlot, null);
        } else if (ItemAction.canStack(fromStack, toStack)) {
            int space = toStack.getMaxStackSize() - toStack.stackSize;
            int toMove = Math.min(space, fromStack.stackSize);
            
            toStack.stackSize += toMove;
            fromStack.stackSize -= toMove;
            
            if (fromStack.stackSize <= 0) {
                inventory.setInventorySlotContents(fromSlot, null);
            }
        }
    }

    /**
     * Vide l'inventaire
     */
    public static void clearInventory(IInventory inventory) {
        if (inventory == null) return;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            inventory.setInventorySlotContents(i, null);
        }
    }

    // ========== STACKING ==========

    /**
     * Auto-stack tous les items similaires
     */
    public static void autoStack(IInventory inventory) {
        if (inventory == null) return;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack1 = inventory.getStackInSlot(i);
            if (stack1 == null || stack1.stackSize >= stack1.getMaxStackSize()) continue;
            
            for (int j = i + 1; j < inventory.getSizeInventory(); j++) {
                ItemStack stack2 = inventory.getStackInSlot(j);
                if (stack2 == null) continue;
                
                if (ItemAction.canStack(stack1, stack2)) {
                    int space = stack1.getMaxStackSize() - stack1.stackSize;
                    int toMove = Math.min(space, stack2.stackSize);
                    
                    stack1.stackSize += toMove;
                    stack2.stackSize -= toMove;
                    
                    if (stack2.stackSize <= 0) {
                        inventory.setInventorySlotContents(j, null);
                    }
                    
                    if (stack1.stackSize >= stack1.getMaxStackSize()) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Compacte l'inventaire (retire les trous)
     */
    public static void compact(IInventory inventory) {
        if (inventory == null) return;
        
        List<ItemStack> items = new ArrayList<ItemStack>();
        
        // Collecte tous les items
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        // Vide l'inventaire
        clearInventory(inventory);
        
        // Remet les items
        for (int i = 0; i < items.size() && i < inventory.getSizeInventory(); i++) {
            inventory.setInventorySlotContents(i, items.get(i));
        }
    }

    // ========== TRI ==========

    /**
     * Trie l'inventaire par ID d'item
     */
    public static void sortByItemId(IInventory inventory) {
        if (inventory == null) return;
        
        List<ItemStack> items = new ArrayList<ItemStack>();
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        Collections.sort(items, new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack s1, ItemStack s2) {
                return Item.getIdFromItem(s1.getItem()) - Item.getIdFromItem(s2.getItem());
            }
        });
        
        clearInventory(inventory);
        
        for (int i = 0; i < items.size() && i < inventory.getSizeInventory(); i++) {
            inventory.setInventorySlotContents(i, items.get(i));
        }
    }

    /**
     * Trie l'inventaire par nom d'item
     */
    public static void sortByName(IInventory inventory) {
        if (inventory == null) return;
        
        List<ItemStack> items = new ArrayList<ItemStack>();
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        Collections.sort(items, new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack s1, ItemStack s2) {
                return s1.getDisplayName().compareTo(s2.getDisplayName());
            }
        });
        
        clearInventory(inventory);
        
        for (int i = 0; i < items.size() && i < inventory.getSizeInventory(); i++) {
            inventory.setInventorySlotContents(i, items.get(i));
        }
    }

    /**
     * Trie l'inventaire par quantité
     */
    public static void sortByCount(IInventory inventory, boolean ascending) {
        if (inventory == null) return;
        
        List<ItemStack> items = new ArrayList<ItemStack>();
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        final int direction = ascending ? 1 : -1;
        
        Collections.sort(items, new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack s1, ItemStack s2) {
                return direction * (s1.stackSize - s2.stackSize);
            }
        });
        
        clearInventory(inventory);
        
        for (int i = 0; i < items.size() && i < inventory.getSizeInventory(); i++) {
            inventory.setInventorySlotContents(i, items.get(i));
        }
    }

    // ========== TRANSFERT ==========

    /**
     * Transfère tous les items d'un inventaire à un autre
     */
    public static void transferAll(IInventory from, IInventory to) {
        if (from == null || to == null) return;
        
        for (int i = 0; i < from.getSizeInventory(); i++) {
            ItemStack stack = from.getStackInSlot(i);
            if (stack != null) {
                if (addItem(to, stack.copy())) {
                    from.setInventorySlotContents(i, null);
                }
            }
        }
    }

    /**
     * Transfère un certain nombre d'items d'un type
     */
    public static int transferItem(IInventory from, IInventory to, Item item, int count) {
        if (from == null || to == null || item == null) return 0;
        
        int transferred = 0;
        
        for (int i = 0; i < from.getSizeInventory() && transferred < count; i++) {
            ItemStack stack = from.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                int toTransfer = Math.min(stack.stackSize, count - transferred);
                ItemStack transferStack = stack.copy();
                transferStack.stackSize = toTransfer;
                
                if (addItem(to, transferStack)) {
                    stack.stackSize -= toTransfer;
                    if (stack.stackSize <= 0) {
                        from.setInventorySlotContents(i, null);
                    }
                    transferred += toTransfer;
                }
            }
        }
        
        return transferred;
    }

    // ========== JOUEUR ==========

    /**
     * Trouve un item dans l'inventaire du joueur
     */
    public static int findItemInPlayerInventory(EntityPlayer player, Item item) {
        if (player == null) return -1;
        return findItem(player.inventory, item);
    }

    /**
     * Obtient les items de la hotbar
     */
    public static List<ItemStack> getHotbarItems(EntityPlayer player) {
        List<ItemStack> items = new ArrayList<ItemStack>();
        if (player == null) return items;
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        return items;
    }

    /**
     * Obtient l'item en main
     */
    public static ItemStack getHeldItem(EntityPlayer player) {
        if (player == null) return null;
        return player.getHeldItem();
    }

    /**
     * Définit l'item en main
     */
    public static void setHeldItem(EntityPlayer player, ItemStack stack) {
        if (player == null) return;
        player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
    }

    /**
     * Obtient le slot de la hotbar sélectionné
     */
    public static int getSelectedHotbarSlot(EntityPlayer player) {
        if (player == null) return 0;
        return player.inventory.currentItem;
    }

    /**
     * Définit le slot de la hotbar sélectionné
     */
    public static void setSelectedHotbarSlot(EntityPlayer player, int slot) {
        if (player == null) return;
        player.inventory.currentItem = MathAction.clamp(slot, 0, 8);
    }

    // ========== SAUVEGARDE/CHARGEMENT ==========

    /**
     * Sauvegarde l'inventaire en NBT
     */
    public static NBTTagList saveInventoryToNBT(IInventory inventory) {
        if (inventory == null) return null;
        
        NBTTagList list = new NBTTagList();
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                list.appendTag(tag);
            }
        }
        
        return list;
    }

    /**
     * Charge l'inventaire depuis NBT
     */
    public static void loadInventoryFromNBT(IInventory inventory, NBTTagList list) {
        if (inventory == null || list == null) return;
        
        clearInventory(inventory);
        
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            int slot = tag.getByte("Slot") & 255;
            
            if (slot >= 0 && slot < inventory.getSizeInventory()) {
                ItemStack stack = ItemStack.loadItemStackFromNBT(tag);
                inventory.setInventorySlotContents(slot, stack);
            }
        }
    }

    // ========== INFORMATIONS ==========


    public static int getTotalItemCount(IInventory inventory) {
        if (inventory == null) return 0;
        
        int count = 0;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                count += stack.stackSize;
            }
        }
        
        return count;
    }


    public static int getUniqueItemCount(IInventory inventory) {
        if (inventory == null) return 0;
        
        List<Item> uniqueItems = new ArrayList<Item>();
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && !uniqueItems.contains(stack.getItem())) {
                uniqueItems.add(stack.getItem());
            }
        }
        
        return uniqueItems.size();
    }

    /**
     * Obtient tous les items de l'inventaire
     */
    public static List<ItemStack> getAllItems(IInventory inventory) {
        List<ItemStack> items = new ArrayList<ItemStack>();
        if (inventory == null) return items;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                items.add(stack);
            }
        }
        
        return items;
    }
}