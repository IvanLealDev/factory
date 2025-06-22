package com.harry.factory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryMod implements ModInitializer {
    public static final String MOD_ID = "factory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // 1. Clave para la DIMENSIÓN
    public static final RegistryKey<World> FACTORY_WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "factory_overworld"));

    // 2. Clave para el TIPO DE DIMENSIÓN
    public static final RegistryKey<DimensionType> FACTORY_DIMENSION_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, new Identifier(MOD_ID, "factory_overworld"));

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Factory Dimension...");

        // 3. Registrar un comando para teletransportarse fácilmente
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("tp_factory")
                    .executes(context -> {
                        ServerCommandSource source = context.getSource();
                        ServerWorld serverWorld = source.getServer().getWorld(FACTORY_WORLD_KEY);

                        if (serverWorld == null) {
                            source.sendError(Text.literal("La dimensión Factory no se ha podido encontrar o cargar."));
                            return 0;
                        }

                        // Teletransporta al jugador a una altura segura sobre el suelo (Capa 3 está en Y=3, así que Y=4 es seguro)
                        source.getPlayer().teleport(serverWorld, 0.5, 4.0, 0.5, 0, 0);
                        source.sendFeedback(() -> Text.literal("Bienvenido a la Factory!"), true);
                        return 1;
                    }));
        });
    }
}
