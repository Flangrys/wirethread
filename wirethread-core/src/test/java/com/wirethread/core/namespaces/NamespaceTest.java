package com.wirethread.core.namespaces;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NamespaceTest {

    private static Stream<Arguments> validNamespacesStreamProvider() {
        return Stream.of(
                Arguments.of("minecraft:stone"),
                Arguments.of("minecraft:oak_log"),
                Arguments.of("minecraft:diamond_sword"),
                Arguments.of("minecraft:iron_pickaxe"),
                Arguments.of("minecraft:grass_block"),
                Arguments.of("minecraft:cobblestone"),
                Arguments.of("minecraft:water_bucket"),
                Arguments.of("minecraft:ender_pearl"),
                Arguments.of("minecraft:blaze_rod"),
                Arguments.of("minecraft:nether_star"),
                Arguments.of("minecraft:oak_sapling"),
                Arguments.of("minecraft:wheat_seeds"),
                Arguments.of("minecraft:golden_apple"),
                Arguments.of("minecraft:creeper_spawn_egg"),
                Arguments.of("minecraft:piston"),
                Arguments.of("minecraft:redstone"),
                Arguments.of("minecraft:enchanting_table"),
                Arguments.of("minecraft:observer"),
                Arguments.of("minecraft:dragon_egg"),
                Arguments.of("minecraft:totem_of_undying"),
                Arguments.of("minecraft:structure/igloo/top"),
                Arguments.of("minecraft:structure/village/plains/houses"),
                Arguments.of("minecraft:structure/shipwreck/top"),
                Arguments.of("minecraft:structure/desert_pyramid"),
                Arguments.of("minecraft:structure/nether_fortress"),
                Arguments.of("minecraft:structure/end_city/bridge"),
                Arguments.of("minecraft:chests/village/village_weaponsmith"),
                Arguments.of("minecraft:chests/end_city_treasure"),
                Arguments.of("minecraft:chests/abandoned_mineshaft"),
                Arguments.of("minecraft:chests/bastion_hoglin_stable"),
                Arguments.of("minecraft:sounds/ambient/cave"),
                Arguments.of("minecraft:sounds/entity/ender_dragon/growl"),
                Arguments.of("minecraft:sounds/music/nether/nether_wastes"),
                Arguments.of("minecraft:sounds/block/anvil/use"),
                Arguments.of("minecraft:worldgen.biome.plains"),
                Arguments.of("minecraft:worldgen.biome.snowy_tundra"),
                Arguments.of("minecraft:worldgen/configured_feature.lakes.lava"),
                Arguments.of("minecraft:worldgen/configured_feature.ore.diamond"),
                Arguments.of("minecraft:models/block.oak_planks"),
                Arguments.of("minecraft:models/item.diamond_pickaxe"),
                Arguments.of("minecraft:textures/block.stone"),
                Arguments.of("minecraft:textures/entity.creeper"),
                Arguments.of("minecraft:particle/ash"),
                Arguments.of("minecraft:particle/portal"),
                Arguments.of("minecraft:particle/end_rod")
        );
    }

    private static Stream<Arguments> invalidNamespacesStreamProvider() {
        return Stream.of(
//                Arguments.of("minecraft/stone"),
//                Arguments.of("minecraft:oak_log-"),
                Arguments.of("minecraft: diamond_sword"),
                Arguments.of("minecraft:iron_pickaxe/"),
//                Arguments.of("minecraft:grass_block:"),
                Arguments.of(":minecraft:cobblestone"),
                Arguments.of("minecraft:/:water_bucket"),
//                Arguments.of("minecraft:-/ender_pearl"),
                Arguments.of("minecraft:blaze_rod,"),
                Arguments.of("minecraft:nether_star."),
                Arguments.of("minecraft:oak_sapling,/"),
                Arguments.of("minecraft:wheat_seeds:/"),
                Arguments.of("minecraft,:golden_apple"),
                Arguments.of("minecraft.:creeper_spawn_egg"),
                Arguments.of("minecraft._:piston"),
                Arguments.of("minecraft:redstone."),
                Arguments.of("minecraft:enchanting_table.#"),
                Arguments.of("minecraft:observer "),
                Arguments.of("minecraft:dragon egg"),
                Arguments.of("minecraft:totem_of_undying."),
                Arguments.of("minecraft:structure/village/plains/houses;"),
                Arguments.of("minecraft:structure/shipwreck/axis.z/top"),
                Arguments.of("minecraft:structure/shipwreck/axis.z/bottom"),
                Arguments.of("minecraft:structure/desert_pyramid."),
                Arguments.of("minecraft:structure/nether-fortress."),
                Arguments.of("minecraft:structure/end_city/\\bridge"),
                Arguments.of("minecraft:chests/village//village_weaponsmith"),
                Arguments.of("minecraft:,chests/end_city_treasure"),
                Arguments.of("minecraft:.chests/abandoned_mineshaft"),
                Arguments.of("minecraft:_chests/bastion\\hoglin_stable"),
                Arguments.of("minecraft:sounds/ambient/cave/"),
                Arguments.of("minecraft:sounds/entity/ender_dragon/growl\\"),
                Arguments.of("minecraft:sounds/music/nether/nether_waste\\"),
                Arguments.of("minecraft:sounds/blockanvil/use "),
//                Arguments.of("minecraft:worldgen/biome/plains-"),
//                Arguments.of("minecraft:worldgen/biome/snowy_tundra__"),
                Arguments.of("minecraft:worldgen/configured.feature/lakes\\lava"),
                Arguments.of("minecraft:worldgen/configured.feature/ore//diamond"),
                Arguments.of("minecraft:models.block/oak_planks"),
                Arguments.of("minecraft:models.item/netherite"),
                Arguments.of("minecraft:textures/block/stone "),
                Arguments.of("minecraft:textures//entity.creeper"),
                Arguments.of("minecraft:/particle/ash:"),
                Arguments.of("minecraft:particle\\portal"),
                Arguments.of("minecraft:particle.end_rod//")
        );
    }

    @ParameterizedTest
    @MethodSource(value = "validNamespacesStreamProvider")
    public void testNamespaceFromMethod_withMultipleValidArguments_expectingNoErrors(final @NotNull String namespace) {
        Assertions.assertDoesNotThrow(() -> {
            Namespace.from(namespace);
        }, "This function should not have thrown an exception.");
    }

    @ParameterizedTest
    @MethodSource(value = "invalidNamespacesStreamProvider")
    public void testNamespaceFromMethod_withMultipleInvalidArguments_expectingNotAFailure(final @NotNull String namespace) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Namespace.from(namespace);
        }, "This function should have thrown an exception.");
    }
}