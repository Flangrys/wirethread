package com.wirethread.server;

import com.wirethread.core.block.BlockManager;
import com.wirethread.core.gameplay.advancement.AdvancementManager;
import com.wirethread.core.gameplay.bossbar.BossBarManager;
import com.wirethread.core.gameplay.recipe.RecipeManager;
import com.wirethread.core.gameplay.team.TeamManager;
import com.wirethread.core.world.WorldManager;
import com.wirethread.network.connection.ConnectionManager;
import com.wirethread.server.benchmark.BenchmarkManager;
import com.wirethread.server.command.CommandManager;
import com.wirethread.server.event.GlobalEventManager;
import com.wirethread.server.exception.ExceptionManager;
import com.wirethread.server.scheduler.SchedulerManager;

/**
 * Provide access to all the process managers of this server.
 */
public record ServerProcess(
        ConnectionManager connectionManager,
        WorldManager worldManager,
        BlockManager blockManager,
        CommandManager commandManager,
        RecipeManager recipeManager,
        TeamManager teamManager,
        GlobalEventManager globalEventManager,
        SchedulerManager schedulerManager,
        BenchmarkManager benchmarkManager,
        AdvancementManager advancementManager,
        BossBarManager bossBarManager,
        ExceptionManager exceptionManager
) {
}
