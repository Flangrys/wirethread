package com.wirethread.core.registry.resources;

import org.jetbrains.annotations.NotNull;

public enum Resources {
    BLOCKS("blocks.json"),
    ITEMS("items.json"),
    ENTITIES("entities.json"),
    FEATURE_FLAGS("feature_flags.json"),
    SOUNDS("sounds.json"),
    STATISTICS("custom_statistics.json"),
    POTION_EFFECTS("potion_effects.json"),
    POTION_TYPES("potions.json"),
    PARTICLES("particles.json"),
    DAMAGE_TYPES("damage_types.json"),
    TRIM_MATERIALS("trim_materials.json"),
    TRIM_PATTERNS("trim_patterns.json"),
    BLOCK_TAGS("tags/block.json"),
    ENTITY_TYPE_TAGS("tags/entity_type.json"),
    FLUID_TAGS("tags/fluid.json"),
    GAMEPLAY_TAGS("tags/game_event.json"),
    GAME_EVENTS("game_events.json"),
    ITEM_TAGS("tags/item.json"),
    ENCHANTMENT_TAGS("tags/enchantment.json"),
    BIOMES_TAGS("tags/biomes.json"),
    DIMENSION_TYPES("dimension_types.json"),
    BIOMES("biomes.json"),
    ATTRIBUTES("attributes.json"),
    BANNER_PATTERNS("banner_patterns.json"),
    WOLF_VARIANTS("wolf_variants.json"),
    CHAT_TYPES("chat_types.json"),
    ENCHANTMENTS("enchantments.snbt"),
    PAINTING_VARIANTS("painting_variants.json"),
    JUKEBOX_SONGS("jukebox_songs.json"),
    VILLAGER_PROFESSIONS("villager_professions.json"),
    INSTRUMENTS("instruments.json"),
    INSTRUMENT_TAGS("tags/instrument.json"),
    BLOCK_SOUND_TYPES("block_sound_types.json");

    private final @NotNull String path;

    Resources(@NotNull String path) {
        this.path = path;
    }

    public final @NotNull String getPath() {
        return this.path;
    }
}
