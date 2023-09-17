package org.beobma.reto

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class RETO : JavaPlugin(), Listener {
    override fun onEnable() {
        // 플러그인 활성화
        logger.info("RETO 플러그인 활성화")

        // 이벤트 불러오기
        server.pluginManager.registerEvents(MENU(), this);
    }

    override fun onDisable() {
        // 플러그인 비활성화
        logger.info("RETO 플러그인 비활성화")
    }
}