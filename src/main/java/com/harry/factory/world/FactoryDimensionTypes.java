package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class FactoryDimensionTypes {
    public static final RegistryKey<DimensionType> FACTORY_OVERWORLD_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(FactoryMod.MOD_ID, "factory_overworld"));

    public static void bootstrap(Registerable<DimensionType> context) {
        context.register(FACTORY_OVERWORLD_TYPE_KEY, new DimensionType(
                OptionalLong.of(18000), // fixedTime: sí, a 18000 (atardecer/noche)
                true,  // hasSkylight: sí, para que la luz funcione
                true,  // hasCeiling: ¡SÍ! Esto es lo que hace que tu techo de cristal funcione
                true,  // ultrawarm: sí, para que no llueva
                false, // natural: no, no tiene ciclo día/noche normal
                1.0,   // coordinateScale: escala 1:1 con el Overworld
                false, // bedWorks: no, las camas no deberían funcionar
                true,  // respawnAnchorWorks: sí, por si acaso
                0,     // minY: altura mínima
                256,   // height: altura máxima del mundo
                256,   // logicalHeight: altura lógica
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn: qué bloques arden infinitamente
                DimensionTypes.THE_END_ID, // effectsLocation: cielo tipo "The End"
                0.1f,  // ambientLight: muy poca luz ambiental, para un look oscuro
                new DimensionType.MonsterSettings(false, // no genera monstruos de forma especial
                        false, // no tiene un budget de spawneo especial
                        UniformIntProvider.create(0, 7), // rango de luz para spawnear
                        0) // block light limit
        ));
    }
}