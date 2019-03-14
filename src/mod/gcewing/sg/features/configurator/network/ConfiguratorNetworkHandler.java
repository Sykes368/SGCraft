package gcewing.sg.features.configurator.network;

import gcewing.sg.BaseDataChannel;
import gcewing.sg.SGCraft;
import gcewing.sg.network.SGChannel;
import gcewing.sg.tileentity.SGBaseTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class ConfiguratorNetworkHandler extends SGChannel {

    protected static BaseDataChannel configuratorChannel;

    public ConfiguratorNetworkHandler(String name) {
        super(name);
        configuratorChannel = this;
    }

    @ServerMessageHandler("ConfiguratorInput")
    public void handleConfiguratorInputFromClient(EntityPlayer player, ChannelInput data) {
        BlockPos pos = readCoords(data);
        SGBaseTE te = SGBaseTE.at(player.world, pos);

        int secondsToStayOpen = data.readInt();
        double ringRotationSpeed = data.readDouble();
        double maxEnergyBuffer = data.readDouble();
        double energyPerFuelItem = data.readDouble();
        int gateOpeningsPerFuelItem = data.readInt();
        double distanceFromMultiplier = data.readDouble();
        double interDimensionalMultiplier = data.readDouble();
        boolean oneWayTravel = data.readBoolean();
        boolean hasIrisUpgrade = data.readBoolean();
        boolean hasChevronUpgrade = data.readBoolean();
        int gateType = data.readInt();
        boolean reverseWormholeKills = data.readBoolean();
        boolean allowIncomingConnections = data.readBoolean();
        boolean allowOutgoingConnections = data.readBoolean();
        boolean closeFromEitherEnd = data.readBoolean();
        boolean preserveInventory = data.readBoolean();
        boolean requiresNoPower = data.readBoolean();
        boolean chevronsLockOnDial = data.readBoolean();
        boolean returnToPreviousIrisState = data.readBoolean();
        boolean transientDamage = data.readBoolean();
        boolean transparency = data.readBoolean();

        if (SGCraft.hasPermission(player, "sgcraft.configurator.secondsToStayOpen"))
            te.secondsToStayOpen = secondsToStayOpen;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.ringRotationSpeed"))
            te.ringRotationSpeed = ringRotationSpeed;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.maxEnergyBuffer"))
            te.maxEnergyBuffer = maxEnergyBuffer;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.energyPerFuelItem"))
            te.energyPerFuelItem = energyPerFuelItem;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.gateOpeningsPerFuelItem"))
            te.gateOpeningsPerFuelItem = gateOpeningsPerFuelItem;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.distanceFactorMultiplier"))
            te.distanceFactorMultiplier = distanceFromMultiplier;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.interDimensionalMultiplier"))
            te.interDimensionMultiplier = interDimensionalMultiplier;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.oneWayTravel"))
            te.oneWayTravel = oneWayTravel;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.hasIrisUpgrade"))
            te.hasIrisUpgrade = hasIrisUpgrade;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.hasChevronUpgrade"))
            te.hasChevronUpgrade = hasChevronUpgrade;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.gateType"))
            te.gateType = gateType;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.reverseWormholeKills"))
            te.reverseWormholeKills = reverseWormholeKills;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.allowIncomingConnections"))
            te.allowIncomingConnections = allowIncomingConnections;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.allowOutgoingConnections"))
            te.allowOutgoingConnections = allowOutgoingConnections;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.closeFromEitherEnd"))
            te.closeFromEitherEnd = closeFromEitherEnd;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.preserveInventory"))
            te.preserveInventory = preserveInventory;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.noPowerRequired"))
            te.requiresNoPower = requiresNoPower;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.chevronsLockOnDial"))
            te.chevronsLockOnDial = chevronsLockOnDial;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.returnToPreviousIrisState"))
            te.returnToPreviousIrisState = returnToPreviousIrisState;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.transientDamage"))
            te.transientDamage = transientDamage;
        if (SGCraft.hasPermission(player, "sgcraft.configurator.transparency"))
            te.transparency = transparency;

        player.sendMessage(new TextComponentString("Changes Saved!"));
        te.markForUpdate();
    }

    public static void sendConfiguratorInputToServer(SGBaseTE te, int secondsToStayOpen, double ringRotationSpeed, double maxEnergyBuffer, double energyPerFuelItem, int gateOpeningsPerFuelItem,
        double distanceFactorMultiplier, double interDimensionalMultiplier, boolean oneWayTravel, boolean hasIrisUpgrade, boolean hasChevronUpgrade, int gateType, boolean reverseWormholeKills,
        boolean allowIncomingConnections, boolean allowOutgoingConnections, boolean closeFromEitherEnd, boolean preserveInventory, boolean requiresNoPower, boolean chevronsLockOnDial,
        boolean returnToPreviousIrisState, boolean transientDamage, boolean transparency) {

        ChannelOutput data = configuratorChannel.openServer("ConfiguratorInput");
        writeCoords(data, te);
        data.writeInt(secondsToStayOpen);
        data.writeDouble(ringRotationSpeed);
        data.writeDouble(maxEnergyBuffer);
        data.writeDouble(energyPerFuelItem);
        data.writeInt(gateOpeningsPerFuelItem);
        data.writeDouble(distanceFactorMultiplier);
        data.writeDouble(interDimensionalMultiplier);
        data.writeBoolean(oneWayTravel);
        data.writeBoolean(hasIrisUpgrade);
        data.writeBoolean(hasChevronUpgrade);
        data.writeInt(gateType);
        data.writeBoolean(reverseWormholeKills);
        data.writeBoolean(allowIncomingConnections);
        data.writeBoolean(allowOutgoingConnections);
        data.writeBoolean(closeFromEitherEnd);
        data.writeBoolean(preserveInventory);
        data.writeBoolean(requiresNoPower);
        data.writeBoolean(chevronsLockOnDial);
        data.writeBoolean(returnToPreviousIrisState);
        data.writeBoolean(transientDamage);
        data.writeBoolean(transparency);
        data.close();
    }

    public static void sendGateAddressAccessInputToServer(SGBaseTE te, String address, boolean defaultAllowIncoming, boolean defaultAllowOutgoing, boolean allowIncoming, boolean allowOutgoing) {
        ChannelOutput data = configuratorChannel.openServer("GateAddressAccessInput");
        writeCoords(data, te);
        data.writeUTF(address);
        data.writeBoolean(defaultAllowIncoming);
        data.writeBoolean(defaultAllowOutgoing);
        data.writeBoolean(allowIncoming);
        data.writeBoolean(allowOutgoing);
        data.close();
    }

    @ServerMessageHandler("GateAddressAccessInput")
    public void handleGateAddressAccessInputFromClient(EntityPlayer player, ChannelInput data) {
        BlockPos pos = readCoords(data);
        SGBaseTE te = SGBaseTE.at(player.world, pos);

        String address = data.readUTF();
        boolean defaultAllowIncoming = data.readBoolean();
        boolean defaultAllowOutgoing = data.readBoolean();
        boolean allowIncoming = data.readBoolean();
        boolean allowOutgoing = data.readBoolean();

        if (SGCraft.hasPermission(player, "sgcraft.configurator.gateAddressAccess.default")) {
            te.defaultAllowIncoming = defaultAllowIncoming;
            te.defaultAllowOutgoing = defaultAllowOutgoing;
        }

        if (SGCraft.hasPermission(player, "sgcraft.configurator.gateAddressAccess.custom")) {
            te.setAllowIncomingAddress(address, allowIncoming);
            te.setAllowOutgoingAddress(address, allowOutgoing);
        }
    }
}
