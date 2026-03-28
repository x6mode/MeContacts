package me.com.meContacts.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Utils {
    public static Object getPlayerInAnyOnline(String playerName) {
        Player onlinePlayer = Bukkit.getPlayer(playerName);
        if (onlinePlayer != null && onlinePlayer.isOnline()) {
            return onlinePlayer;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);

        if (!offlinePlayer.hasPlayedBefore()) {
            return null;
        }

        return offlinePlayer;
    }
}
