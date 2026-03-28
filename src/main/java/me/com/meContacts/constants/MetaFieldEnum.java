package me.com.meContacts.constants;

import me.com.meContacts.MeContacts;
import org.bukkit.NamespacedKey;

public class MetaFieldEnum {
    public static final NamespacedKey IRL_NAME = createKey("IRL_NAME");
    public static final NamespacedKey TELEGRAM = createKey("TELEGRAM");

    private static NamespacedKey createKey(String keyName) {
        return MeContacts.generateKey(keyName);
    }
}
