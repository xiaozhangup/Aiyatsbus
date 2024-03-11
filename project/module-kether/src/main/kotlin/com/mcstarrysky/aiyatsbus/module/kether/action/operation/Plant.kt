package com.mcstarrysky.aiyatsbus.module.kether.action.operation

import com.mcstarrysky.aiyatsbus.core.util.PermissionChecker
import com.mcstarrysky.aiyatsbus.core.util.blockLookingAt
import com.mcstarrysky.aiyatsbus.core.util.placeBlock
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.platform.util.takeItem

/**
 * Aiyatsbus
 * com.mcstarrysky.aiyatsbus.module.kether.action.operation.Plant
 *
 * @author mical
 * @since 2024/3/11 22:23
 */
object Plant {

    val seedsMap = linkedMapOf(
        Material.BEETROOT_SEEDS to Material.BEETROOTS,
        Material.MELON_SEEDS to Material.MELON_STEM,
        Material.PUMPKIN_SEEDS to Material.PUMPKIN_STEM,
        Material.TORCHFLOWER_SEEDS to Material.TORCHFLOWER_CROP,
        Material.WHEAT_SEEDS to Material.WHEAT,
        Material.CARROT to Material.CARROTS,
        Material.POTATO to Material.POTATOES
    )

    fun getSeed(player: Player, seeds: String?): Material? {
        if (seeds == "ALL") return player.inventory.contents.find { seedsMap.containsKey(it?.type) }?.type
        try {
            val type = seeds?.let { Material.valueOf(it) } ?: return null
            if (player.inventory.containsAtLeast(ItemStack(type), 1)) return type
        } catch (ignored: Exception) {
        }
        return null
    }

    fun plant(player: Player, sideLength: Int, seeds: String?) {
        if (sideLength <= 1) return

        val block = player.blockLookingAt(6.0) ?: return
        val loc = block.location
        val type = getSeed(player, seeds) ?: return

        val down = -sideLength / 2
        val up = sideLength / 2 - if (sideLength % 2 == 0) 1 else 0

        for (x in down until up + 1) {
            for (z in down until up + 1) {
                val current = loc.clone().add(x.toDouble(), 0.0, z.toDouble())
                if (current.block.type != Material.FARMLAND) continue
                val planted = current.clone().add(0.0, 1.0, 0.0).block
                if (!PermissionChecker.hasBlockPermission(player, planted))
                    continue
                if (planted.type != Material.AIR) continue // 防止左右手打架
                if (player.placeBlock(planted, ItemStack(type, 1))) {
                    planted.type = seedsMap[type]!!
                    val data = planted.blockData as Ageable
                    data.age = 0
                    planted.blockData = data
                    player.inventory.takeItem(1) { it.type == type }
                }
            }
        }
    }
}