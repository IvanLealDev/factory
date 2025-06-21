package com.harry.factorydim;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {

    // Identificadores para cada bioma
    public static final Identifier METAL_FORGE = new Identifier(FactoryDim.MOD_ID, "metal_forge");
    public static final Identifier COPPER_FOUNDRY = new Identifier(FactoryDim.MOD_ID, "copper_foundry");
    public static final Identifier SMOKE_CHIMNEY = new Identifier(FactoryDim.MOD_ID, "smoke_chimney");
    public static final Identifier GEAR_VALLEY = new Identifier(FactoryDim.MOD_ID, "gear_valley");

    // Este método es crucial. Aunque los biomas se definen en JSON,
    // debemos registrar sus identificadores para que el juego los conozca.
    // La forma moderna es no registrar un objeto Biome vacío, sino simplemente
    // asegurarse de que los JSON están en el lugar correcto. El juego los cargará.
    // Una simple llamada a una clase como esta asegura que los Identifiers se inicialicen.
    public static void registerBiomes() {
        // En 1.20.1, la presencia del JSON en la carpeta de datos del mod es suficiente
        // para que se cargue si el namespace (mod id) es válido.
        // No es estrictamente necesario un Registry.register() aquí si los archivos JSON
        // están correctamente ubicados, ya que el cargador de datos lo maneja.
        // Esta clase sirve para mantener los Identificadores organizados.
        FactoryDim.LOGGER.info("Registering Biomes for " + FactoryDim.MOD_ID);
    }
}