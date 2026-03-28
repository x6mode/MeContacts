package me.com.meContacts.managers;

import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentContainerManager {

    public static <P, C> void setMeta(PersistentDataContainer container, NamespacedKey key, PersistentDataType<P, C> type, C value) {
        container
            .set(key, type, value);
    }

    public static boolean isHasMeta(PersistentDataContainerView container, NamespacedKey key) {
        return container
                .has(key);
    }

    public static <P, C> C getMeta(PersistentDataContainerView container, NamespacedKey key, PersistentDataType<P, C> type) {
        return container
                .get(key, type);
    }
}