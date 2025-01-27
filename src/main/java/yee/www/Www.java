package yee.www;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Www extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("BlockPluginsCommand has been enabled!");
        // 註冊事件
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("BlockPluginsCommand has been disabled!");
    }

    // 攔截 /pl 和 /plugins 指令
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().toLowerCase(); // 確保是小寫
        Player player = event.getPlayer();

        // 如果玩家輸入 /pl 或 /plugins
        if (command.equals("/pl") || command.equals("/plugins")) {
            // 阻止執行指令
            event.setCancelled(true);

            // 給予負面效果
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 180, 0)); // 失明 3 分鐘
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20 * 180, 0));  // 黑暗 3 分鐘
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 180, 0)); // 噁心 3 分鐘
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 780, 2));      // 緩速 13 分鐘

            // 發送警告訊息給玩家
            player.sendMessage(ChatColor.RED + "偷看！戳瞎你！");
        }
    }
}
