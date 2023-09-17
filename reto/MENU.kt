@file:Suppress("DEPRECATION")

package org.beobma.reto

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*


class MENU : Listener {

    @EventHandler
    fun onSwapHandItems(event : PlayerSwapHandItemsEvent) {
        val player = event.player

        // 플레이어가 시프트 키와 양손들기 키를 눌렀는지 확인
        if (player.isSneaking && player.itemInHand.type == Material.AIR) {
            event.isCancelled = true

            openMenuInventory(player)
            player.addScoreboardTag("Open_Menu")

        }
    }

    @EventHandler
    fun onEnhanceButtonClick(event: InventoryClickEvent) {
        val clickedItem = event.currentItem
        val player = event.whoClicked as? Player

        if(player?.scoreboardTags?.contains("Open_Menu") == true){

            when (clickedItem?.itemMeta?.displayName) {
                "${ChatColor.BOLD}마을 이동" -> {
                    player.sendMessage("마을 구현 예정")
                    player.closeInventory()
                }
                "${ChatColor.BOLD}로비 이동" -> {
                    player.sendMessage("로비 구현 예정")
                    player.closeInventory()
                }
                "${ChatColor.BOLD}야생 이동" -> {
                    player.sendMessage("야생 구현 예정")
                    player.closeInventory()
                }
                "${ChatColor.BOLD}상점" -> {
                    player.sendMessage("상점 구현 예정")
                    player.closeInventory()
                }
                "${ChatColor.BOLD}사설 상점" -> {
                    player.sendMessage("사설 상점 구현 예정")
                    player.closeInventory()
                }
                "${ChatColor.BOLD}PVP 끄기/켜기" -> {
                    player.sendMessage("시스템 구현 예정")
                    player.closeInventory()
                }
            }

            event.isCancelled = true
        }
    }









    private fun openMenuInventory(player: Player) {
        val menu = Bukkit.createInventory(player, 54, "${ChatColor.BOLD}메뉴")

        // 1 : 내 정보
        val myinformation = ItemStack(Material.PLAYER_HEAD, 1, 3.toShort())
        val skull = myinformation.itemMeta as SkullMeta
        skull.setDisplayName(player.name)
        skull.owner = player.name
        myinformation.itemMeta = skull
        menu.setItem(4, myinformation)

        // 2 : 마을 이동
        val movevillageStack = ItemStack(Material.OAK_PLANKS)
        val movevillage = movevillageStack.itemMeta
        movevillage.setDisplayName("${ChatColor.BOLD}마을 이동")
        movevillageStack.itemMeta = movevillage
        menu.setItem(21, movevillageStack)

        val movevillagelevel: MutableList<String> = ArrayList()
        movevillagelevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 마을로 이동합니다.")
        menu.getItem(21)!!.lore = movevillagelevel

        //3 : 로비 이동
        val movespawnStack = ItemStack(Material.WHITE_WOOL)
        val movespawn = movespawnStack.itemMeta
        movespawn.setDisplayName("${ChatColor.BOLD}로비 이동")
        movespawnStack.itemMeta = movespawn
        menu.setItem(22, movespawnStack)

        val movespawnlevel: MutableList<String> = ArrayList()
        movespawnlevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 로비으로 이동합니다.")
        menu.getItem(22)!!.lore = movespawnlevel

        //4 : 야생 이동
        val survivalspawnStack = ItemStack(Material.GRASS_BLOCK)
        val survivalspawn = survivalspawnStack.itemMeta
        survivalspawn.setDisplayName("${ChatColor.BOLD}야생 이동")
        survivalspawnStack.itemMeta = survivalspawn
        menu.setItem(23, survivalspawnStack)

        val survivalspawnlevel: MutableList<String> = ArrayList()
        survivalspawnlevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 야생으로 이동합니다.")
        menu.getItem(23)!!.lore = survivalspawnlevel

        //5 : 상점
        val shopStack = ItemStack(Material.RED_WOOL)
        val shop = shopStack.itemMeta
        shop.setDisplayName("${ChatColor.BOLD}상점")
        shopStack.itemMeta = shop
        menu.setItem(30, shopStack)

        val shoplevel: MutableList<String> = ArrayList()
        shoplevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 상점으로 이동합니다.")
        menu.getItem(30)!!.lore = shoplevel

        //6 : 사설 상점
        val usershopStack = ItemStack(Material.BLUE_WOOL)
        val usershop = usershopStack.itemMeta
        usershop.setDisplayName("${ChatColor.BOLD}사설 상점")
        usershopStack.itemMeta = usershop
        menu.setItem(32, usershopStack)

        val usershoplevel: MutableList<String> = ArrayList()
        usershoplevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 사설 상점으로 이동합니다.")
        menu.getItem(32)!!.lore = usershoplevel

        //7 : PVP 끄기/켜기
        val pvponandoffStack = ItemStack(Material.DIAMOND_SWORD)
        val pvponandoff = pvponandoffStack.itemMeta
        pvponandoff.setDisplayName("${ChatColor.BOLD}PVP 끄기/켜기")
        pvponandoffStack.itemMeta = pvponandoff
        menu.setItem(31, pvponandoffStack)

        val pvponandofflevel: MutableList<String> = ArrayList()
        pvponandofflevel.add("${ChatColor.BOLD}${ChatColor.WHITE}클릭하면 PVP를 끄거나 킵니다.")
        menu.getItem(31)!!.lore = pvponandofflevel





        player.openInventory(menu)
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as? Player
        if (player?.scoreboardTags?.contains("Open_Menu")  == true){
            player.removeScoreboardTag("Open_Menu")
        }
    }
}