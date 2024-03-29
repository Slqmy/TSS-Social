package net.slqmy.tss_social;

import net.slqmy.tss_core.TSSCorePlugin;
import net.slqmy.tss_ranks.TSSRanksPlugin;
import net.slqmy.tss_social.command.ChatCommand;
import net.slqmy.tss_social.command.MessageCommand;
import net.slqmy.tss_social.command.PartyCommand;
import net.slqmy.tss_social.command.ReplyCommand;
import net.slqmy.tss_social.event.listener.ConnectionListener;
import net.slqmy.tss_social.manager.PartyManager;
import net.slqmy.tss_social.type.ChatType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class TSSSocialPlugin extends JavaPlugin {

  private final PluginManager pluginManager = Bukkit.getPluginManager();

  private final TSSCorePlugin core = (TSSCorePlugin) pluginManager.getPlugin("TSS-Core");
  private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) pluginManager.getPlugin("TSS-Ranks");
  private final HashMap<UUID, UUID> conversations = new HashMap<>();
  private final HashMap<UUID, ChatType> playerChatModes = new HashMap<>();
  private PartyManager partyManager;

  public TSSCorePlugin getCore() {
	return core;
  }

  public TSSRanksPlugin getRanksPlugin() {
	return ranksPlugin;
  }

  public HashMap<UUID, UUID> getConversations() {
	return conversations;
  }

  public HashMap<UUID, ChatType> getPlayerChatModes() {
	return playerChatModes;
  }

  public PartyManager getPartyManager() {
	return partyManager;
  }

  @Override
  public void onEnable() {
	partyManager = new PartyManager();

	new MessageCommand(this);
	new ReplyCommand(this);

	new PartyCommand(this);

	new ChatCommand(this);

	pluginManager.registerEvents(new ConnectionListener(this), this);
  }
}
