package com.wirethread.core.registry.records;

import com.wirethread.core.biome.BiomeType;
import com.wirethread.core.registry.repository.StaticRegistry;
import org.jetbrains.annotations.NotNull;

/**
 * Holds all static registries handled by the server.
 */
public record StaticRegistries(
//        @NotNull StaticRegistry<ChatType> chatType,
//        @NotNull StaticRegistry<DimensionType> dimensionType,
        @NotNull StaticRegistry<BiomeType> biomeType
//        @NotNull StaticRegistry<DamageType> damageType,
//        @NotNull StaticRegistry<TrimMaterialType> trimMaterialType,
//        @NotNull StaticRegistry<TrimPatternType> trimPatternType,
//        @NotNull StaticRegistry<BannerPatternType> bannerPatternType,
//        @NotNull StaticRegistry<WolfVariantType> wolfVariantType,
//        @NotNull StaticRegistry<EnchantmentType> enchantmentType,
//        @NotNull StaticRegistry<PaintingVariantType> paintingVariantType,
//        @NotNull StaticRegistry<MusicDiskType> musicDiskType,
//        @NotNull StaticRegistry<InstrumentType> instrumentType

) {
}
