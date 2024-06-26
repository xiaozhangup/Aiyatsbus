package com.mcstarrysky.aiyatsbus.core.data.trigger

import org.bukkit.inventory.EquipmentSlot

enum class TriggerSlots(val slots: Set<EquipmentSlot?>) {

    ARMORS(
        setOf(
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
        )
    ),
    HANDS(setOf(EquipmentSlot.HAND, EquipmentSlot.OFF_HAND)),
    MAIN_HAND(setOf(EquipmentSlot.HAND)),
    OFF_HAND(setOf(EquipmentSlot.OFF_HAND)),
    HELMET(setOf(EquipmentSlot.HEAD)),
    CHESTPLATE(setOf(EquipmentSlot.CHEST)),
    LEGGINGS(setOf(EquipmentSlot.LEGS)),
    BOOTS(setOf(EquipmentSlot.FEET)),
    ALL(
        setOf(
            EquipmentSlot.HAND,
            EquipmentSlot.OFF_HAND,
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
        )
    )
}