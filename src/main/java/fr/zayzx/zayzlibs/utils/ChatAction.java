package fr.zayzx.zayzlibs.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.util.List;

public class ChatAction {

    // ========== ENVOI DE MESSAGES ==========

    public static void sendMessage(EntityPlayer player, String message) {
        if (player == null || message == null) return;
        player.addChatMessage(new ChatComponentText(message));
    }

    public static void sendColoredMessage(EntityPlayer player, String message, EnumChatFormatting color) {
        if (player == null || message == null) return;
        ChatComponentText component = new ChatComponentText(message);
        component.getChatStyle().setColor(color);
        player.addChatMessage(component);
    }

    public static void sendFormattedMessage(EntityPlayer player, String message) {
        if (player == null || message == null) return;
        player.addChatMessage(new ChatComponentText(translateColorCodes(message)));
    }

    public static void sendMessageToAll(String message) {
        if (MinecraftServer.getServer() == null) return;
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(message));
    }

    public static void sendMessageToOps(String message) {
        if (MinecraftServer.getServer() == null) return;
        for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            EntityPlayerMP player = (EntityPlayerMP) obj;
            if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile())) {
                sendMessage(player, message);
            }
        }
    }

    // ========== COULEURS ==========

    public static String translateColorCodes(String text) {
        if (text == null) return "";
        return text.replace('&', '\u00a7');
    }

    public static String stripColorCodes(String text) {
        if (text == null) return "";
        return text.replaceAll("(?i)\u00a7[0-9A-FK-OR]", "");
    }

    public static String color(String text, EnumChatFormatting color) {
        return color + text + EnumChatFormatting.RESET;
    }

    public static String red(String text) {
        return color(text, EnumChatFormatting.RED);
    }

    public static String green(String text) {
        return color(text, EnumChatFormatting.GREEN);
    }

    public static String blue(String text) {
        return color(text, EnumChatFormatting.BLUE);
    }

    public static String yellow(String text) {
        return color(text, EnumChatFormatting.YELLOW);
    }

    public static String gold(String text) {
        return color(text, EnumChatFormatting.GOLD);
    }

    public static String aqua(String text) {
        return color(text, EnumChatFormatting.AQUA);
    }

    public static String gray(String text) {
        return color(text, EnumChatFormatting.GRAY);
    }

    public static String darkGray(String text) {
        return color(text, EnumChatFormatting.DARK_GRAY);
    }

    public static String white(String text) {
        return color(text, EnumChatFormatting.WHITE);
    }

    public static String black(String text) {
        return color(text, EnumChatFormatting.BLACK);
    }

    // ========== FORMATAGE ==========

    public static String bold(String text) {
        return EnumChatFormatting.BOLD + text + EnumChatFormatting.RESET;
    }

    public static String italic(String text) {
        return EnumChatFormatting.ITALIC + text + EnumChatFormatting.RESET;
    }

    public static String underline(String text) {
        return EnumChatFormatting.UNDERLINE + text + EnumChatFormatting.RESET;
    }

    public static String strikethrough(String text) {
        return EnumChatFormatting.STRIKETHROUGH + text + EnumChatFormatting.RESET;
    }

    public static String obfuscated(String text) {
        return EnumChatFormatting.OBFUSCATED + text + EnumChatFormatting.RESET;
    }

    // ========== COMPOSANTS AVANCÉS ==========

    public static IChatComponent createClickableMessage(String text, String command) {
        ChatComponentText component = new ChatComponentText(text);
        ChatStyle style = new ChatStyle();
        style.setChatClickEvent(new net.minecraft.event.ClickEvent(
            net.minecraft.event.ClickEvent.Action.RUN_COMMAND, command));
        component.setChatStyle(style);
        return component;
    }

    public static IChatComponent createHoverMessage(String text, String hoverText) {
        ChatComponentText component = new ChatComponentText(text);
        ChatStyle style = new ChatStyle();
        style.setChatHoverEvent(new net.minecraft.event.HoverEvent(
            net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText(hoverText)));
        component.setChatStyle(style);
        return component;
    }

    public static IChatComponent createClickableHoverMessage(String text, String command, String hoverText) {
        ChatComponentText component = new ChatComponentText(text);
        ChatStyle style = new ChatStyle();
        style.setChatClickEvent(new net.minecraft.event.ClickEvent(
            net.minecraft.event.ClickEvent.Action.RUN_COMMAND, command));
        style.setChatHoverEvent(new net.minecraft.event.HoverEvent(
            net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText(hoverText)));
        component.setChatStyle(style);
        return component;
    }

    public static void sendClickableMessage(EntityPlayer player, String text, String command) {
        if (player == null) return;
        player.addChatMessage(createClickableMessage(text, command));
    }

    public static void sendHoverMessage(EntityPlayer player, String text, String hoverText) {
        if (player == null) return;
        player.addChatMessage(createHoverMessage(text, hoverText));
    }

    // ========== MESSAGES PRÉFORMATÉS ==========

    public static void sendSuccess(EntityPlayer player, String message) {
        sendFormattedMessage(player, "&a[SUCCESS] &f" + message);
    }

    public static void sendError(EntityPlayer player, String message) {
        sendFormattedMessage(player, "&c[ERROR] &f" + message);
    }

    public static void sendWarning(EntityPlayer player, String message) {
        sendFormattedMessage(player, "&e[WARNING] &f" + message);
    }

    public static void sendInfo(EntityPlayer player, String message) {
        sendFormattedMessage(player, "&b[INFO] &f" + message);
    }

    public static void sendDebug(EntityPlayer player, String message) {
        sendFormattedMessage(player, "&7[DEBUG] &f" + message);
    }

    // ========== BROADCAST ==========

    public static void broadcast(String message) {
        sendMessageToAll(message);
    }

    public static void broadcastFormatted(String message) {
        broadcast(translateColorCodes(message));
    }

    public static void broadcastSuccess(String message) {
        broadcastFormatted("&a[SUCCESS] &f" + message);
    }

    public static void broadcastError(String message) {
        broadcastFormatted("&c[ERROR] &f" + message);
    }

    public static void broadcastWarning(String message) {
        broadcastFormatted("&e[WARNING] &f" + message);
    }

    // ========== LIGNES ET SÉPARATEURS ==========

    public static void sendLine(EntityPlayer player) {
        sendMessage(player, "----------------------------------------");
    }

    public static void sendColoredLine(EntityPlayer player, EnumChatFormatting color) {
        sendColoredMessage(player, "----------------------------------------", color);
    }

    public static void sendCenteredMessage(EntityPlayer player, String message) {
        int spaces = (40 - stripColorCodes(message).length()) / 2;
        String padding = "";
        for (int i = 0; i < spaces; i++) padding += " ";
        sendFormattedMessage(player, padding + message);
    }

    // ========== MESSAGES MULTIPLES ==========

    public static void sendMessages(EntityPlayer player, String... messages) {
        if (player == null || messages == null) return;
        for (String message : messages) {
            sendMessage(player, message);
        }
    }

    public static void sendFormattedMessages(EntityPlayer player, String... messages) {
        if (player == null || messages == null) return;
        for (String message : messages) {
            sendFormattedMessage(player, message);
        }
    }

    public static void sendMessageList(EntityPlayer player, List<String> messages) {
        if (player == null || messages == null) return;
        for (String message : messages) {
            sendMessage(player, message);
        }
    }

    // ========== TITRES ET EN-TÊTES ==========

    public static void sendTitle(EntityPlayer player, String title) {
        sendLine(player);
        sendCenteredMessage(player, "&6&l" + title);
        sendLine(player);
    }

    public static void sendHeader(EntityPlayer player, String header) {
        sendFormattedMessage(player, "&8&m          &r &6" + header + " &8&m          ");
    }

    public static void sendFooter(EntityPlayer player) {
        sendFormattedMessage(player, "&8&m                                        ");
    }

    // ========== FORMATAGE AVANCÉ ==========

    public static String center(String text, int width) {
        int spaces = (width - stripColorCodes(text).length()) / 2;
        String padding = "";
        for (int i = 0; i < spaces; i++) padding += " ";
        return padding + text;
    }

    public static String repeat(String text, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(text);
        }
        return sb.toString();
    }

    public static String pad(String text, int width, char padChar) {
        int padding = width - stripColorCodes(text).length();
        if (padding <= 0) return text;
        return text + repeat(String.valueOf(padChar), padding);
    }

    // ========== CONSOLE ==========

    public static void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void logWarning(String message) {
        System.out.println("[WARNING] " + message);
    }

    public static void logError(String message) {
        System.err.println("[ERROR] " + message);
    }

    public static void logDebug(String message) {
        System.out.println("[DEBUG] " + message);
    }
}