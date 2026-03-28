package me.com.meContacts;

import me.com.meContacts.commands.MainCommand;
import me.com.meContacts.listeners.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class MeContacts extends JavaPlugin {
    private static MeContacts instance;

    @Override
    public void onEnable() {
        instance = this;

        MainCommand command = new MainCommand();
        getCommand("info").setExecutor(command);
        getCommand("info").setTabCompleter(command);

        Bukkit
                .getServer()
                .getPluginManager()
                .registerEvents(new PlayerInteractEvent(), this);

        getLogger().info("Плагин успешно запущен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин успешно остановлен!");
    }

    public static NamespacedKey generateKey(String keyName) {
        return new NamespacedKey(instance, keyName);
    }
}
