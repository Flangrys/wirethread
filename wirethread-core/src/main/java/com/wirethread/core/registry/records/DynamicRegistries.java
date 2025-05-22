package com.wirethread.core.registry.records;

import com.wirethread.core.biome.BiomeType;
import com.wirethread.core.registry.repository.DynamicRegistry;
import org.jetbrains.annotations.NotNull;

/**
 * Holds all dynamic registries handled by the server.
 */
public record DynamicRegistries(
//        @NotNull DynamicRegistry<ChatType> chatType,
//        @NotNull DynamicRegistry<DimensionType> dimensionType,
        @NotNull DynamicRegistry<BiomeType> biomeType
//        @NotNull DynamicRegistry<DamageType> damageType,
//        @NotNull DynamicRegistry<TrimMaterialType> trimMaterialType,
//        @NotNull DynamicRegistry<TrimPatternType> trimPatternType,
//        @NotNull DynamicRegistry<BannerPatternType> bannerPatternType,
//        @NotNull DynamicRegistry<WolfVariantType> wolfVariantType,
//        @NotNull DynamicRegistry<EnchantmentType> enchantmentType,
//        @NotNull DynamicRegistry<PaintingVariantType> paintingVariantType,
//        @NotNull DynamicRegistry<MusicDiskType> musicDiskType,
//        @NotNull DynamicRegistry<InstrumentType> instrumentType
) {
}
