package fr.zayzx.zayzlibs.utils;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketAction {

    private static SimpleNetworkWrapper network;
    private static int packetId = 0;


    public static void initNetwork(String channelName) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
    }

    public static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(
            Class<? extends IMessageHandler<REQ, REPLY>> handler,
            Class<REQ> packetClass,
            Side side) {
        if (network == null) {
            throw new IllegalStateException("Network not initialized! Call initNetwork() first.");
        }
        network.registerMessage(handler, packetClass, packetId++, side);
    }


    public static void sendToServer(IMessage packet) {
        if (network == null) return;
        network.sendToServer(packet);
    }


    public static void sendToPlayer(IMessage packet, EntityPlayerMP player) {
        if (network == null || player == null) return;
        network.sendTo(packet, player);
    }


    public static void sendToAll(IMessage packet) {
        if (network == null) return;
        network.sendToAll(packet);
    }


    public static void sendToDimension(IMessage packet, int dimensionId) {
        if (network == null) return;
        network.sendToDimension(packet, dimensionId);
    }


    public static void sendToAllAround(IMessage packet, double x, double y, double z, double range, int dimensionId) {
        if (network == null) return;
        NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(dimensionId, x, y, z, range);
        network.sendToAllAround(packet, point);
    }

    public static void sendToAllAroundPlayer(IMessage packet, EntityPlayer player, double range) {
        if (network == null || player == null) return;
        sendToAllAround(packet, player.posX, player.posY, player.posZ, range, player.dimension);
    }


    public static EntityPlayerMP getPlayerFromContext(MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            return ctx.getServerHandler().playerEntity;
        }
        return null;
    }


    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }


    public static class PacketString implements IMessage {
        public String message;

        public PacketString() {}

        public PacketString(String message) {
            this.message = message;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            int length = buf.readInt();
            message = new String(buf.readBytes(length).array());
        }

        @Override
        public void toBytes(ByteBuf buf) {
            byte[] bytes = message.getBytes();
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
        }
    }


    public static class PacketInteger implements IMessage {
        public int value;

        public PacketInteger() {}

        public PacketInteger(int value) {
            this.value = value;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            value = buf.readInt();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(value);
        }
    }


    public static class PacketFloat implements IMessage {
        public float value;

        public PacketFloat() {}

        public PacketFloat(float value) {
            this.value = value;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            value = buf.readFloat();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeFloat(value);
        }
    }


    public static class PacketDouble implements IMessage {
        public double value;

        public PacketDouble() {}

        public PacketDouble(double value) {
            this.value = value;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            value = buf.readDouble();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeDouble(value);
        }
    }


    public static class PacketBoolean implements IMessage {
        public boolean value;

        public PacketBoolean() {}

        public PacketBoolean(boolean value) {
            this.value = value;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            value = buf.readBoolean();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeBoolean(value);
        }
    }


    public static class PacketCoordinates implements IMessage {
        public double x, y, z;

        public PacketCoordinates() {}

        public PacketCoordinates(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            x = buf.readDouble();
            y = buf.readDouble();
            z = buf.readDouble();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeDouble(x);
            buf.writeDouble(y);
            buf.writeDouble(z);
        }
    }


    public static class PacketMultiData implements IMessage {
        public int intValue;
        public float floatValue;
        public String stringValue;

        public PacketMultiData() {}

        public PacketMultiData(int intValue, float floatValue, String stringValue) {
            this.intValue = intValue;
            this.floatValue = floatValue;
            this.stringValue = stringValue;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            intValue = buf.readInt();
            floatValue = buf.readFloat();
            int length = buf.readInt();
            stringValue = new String(buf.readBytes(length).array());
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(intValue);
            buf.writeFloat(floatValue);
            byte[] bytes = stringValue.getBytes();
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
        }
    }


    public static class PacketEmpty implements IMessage {
        public PacketEmpty() {}

        @Override
        public void fromBytes(ByteBuf buf) {}

        @Override
        public void toBytes(ByteBuf buf) {}
    }

    // ========== EXEMPLE HANDLERS ==========


    public static class PacketStringHandler implements IMessageHandler<PacketString, IMessage> {
        @Override
        public IMessage onMessage(PacketString message, MessageContext ctx) {
            EntityPlayerMP player = getPlayerFromContext(ctx);
            if (player != null) {
                System.out.println("Received message from " + player.getCommandSenderName() + ": " + message.message);
            }
            return null;
        }
    }


    public static class PacketIntegerHandler implements IMessageHandler<PacketInteger, IMessage> {
        @Override
        public IMessage onMessage(PacketInteger message, MessageContext ctx) {
            EntityPlayerMP player = getPlayerFromContext(ctx);
            if (player != null) {
                System.out.println("Received integer from " + player.getCommandSenderName() + ": " + message.value);
            }
            return null;
        }
    }


    public static class PacketCoordinatesHandler implements IMessageHandler<PacketCoordinates, IMessage> {
        @Override
        public IMessage onMessage(PacketCoordinates message, MessageContext ctx) {
            EntityPlayerMP player = getPlayerFromContext(ctx);
            if (player != null) {
                System.out.println("Received coordinates: " + message.x + ", " + message.y + ", " + message.z);
            }
            return null;
        }
    }


    public static class PacketEmptyHandler implements IMessageHandler<PacketEmpty, IMessage> {
        @Override
        public IMessage onMessage(PacketEmpty message, MessageContext ctx) {
            EntityPlayerMP player = getPlayerFromContext(ctx);
            if (player != null) {
                System.out.println("Received empty packet from " + player.getCommandSenderName());
            }
            return null;
        }
    }
}