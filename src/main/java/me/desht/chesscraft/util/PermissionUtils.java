package me.desht.chesscraft.util;
import me.desht.chesscraft.ChessCraft;
import me.desht.chesscraft.exceptions.ChessException;
import me.desht.chesscraft.log.ChessCraftLogger;

import org.bukkit.entity.Player;

public class PermissionUtils {

	/**
	 * Check if the player has the specified permission node.
	 * 
	 * @param player	Player to check
	 * @param node		Node to check for
	 * @return	true if the player has the permission node, false otherwise
	 */
	public static boolean isAllowedTo(Player player, String node) {
		boolean allowed = false;
		
		if (player == null || node == null) {
			allowed = true;
		} else {
			if (ChessCraft.permission != null && !ChessCraft.permission.hasSuperPermsCompat()) {
				allowed = ChessCraft.permission.has(player, node);
			} else {
				allowed = player.hasPermission(node);
			}
		}
		String name = player == null ? "CONSOLE" : player.getName();
		ChessCraftLogger.fine("Permission check: player = " + name + ", node = " + node + ", allowed = " + allowed);
		return allowed;
	}

	/**
	 * Throw an exception if the player does not have the specified permission.
	 * 
	 * @param player	Player to check
	 * @param node		Require permission node
	 * @throws SMSException	if the player does not have the node
	 */
	public static void requirePerms(Player player, String node) throws ChessException {
		if (!isAllowedTo(player, node)) {
			throw new ChessException("You are not allowed to do that.");
		}
	}
}