package com.harry.factory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryMod implements ModInitializer {
    public static final String MOD_ID = "factory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RegistryKey<World> FACTORY_WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "factory_overworld"));

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Factory Dimension...");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("tp_factory")
                    .executes(context -> {
                        ServerCommandSource source = context.getSource();
                        ServerWorld serverWorld = source.getServer().getWorld(FACTORY_WORLD_KEY);

                        if (serverWorld == null) {
                            source.sendError(Text.literal("La dimensión Factory no se ha podido encontrar o cargar."));
                            return 0;
                        }

                        source.getPlayer().teleport(serverWorld, 0.5, 4.0, 0.5, 0, 0);
                        source.sendFeedback(() -> Text.literal("Bienvenido a la Factory!"), true);
                        return 1;
                    }));
        });
    }
}