package net.sf.l2j.gameserver.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.datatables.GmListTable;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public final class IllegalPlayerAction implements Runnable
{
	private static Logger _logAudit = Logger.getLogger("audit");
	
	private final String _message;
	private final int _punishment;
	private final L2PcInstance _actor;
	
	public static final int PUNISH_BROADCAST = 1;
	public static final int PUNISH_KICK = 2;
	public static final int PUNISH_KICKBAN = 3;
	public static final int PUNISH_JAIL = 4;
	
	public IllegalPlayerAction(L2PcInstance actor, String message, int punishment)
	{
		_message = message;
		_punishment = punishment;
		_actor = actor;
		
		switch (punishment)
		{
			case PUNISH_KICK:
				_actor.sendMessage("You will be kicked for illegal action, GM informed.");
				break;
			case PUNISH_KICKBAN:
				_actor.setAccessLevel(-100);
				_actor.setAccountAccesslevel(-100);
				_actor.sendMessage("You are banned for illegal action, GM informed.");
				break;
			case PUNISH_JAIL:
				_actor.sendMessage("Illegal action performed!");
				_actor.sendMessage("You will be teleported to GM Consultation Service area and jailed.");
				break;
		}
	}
	
	@Override
	public void run()
	{
		LogRecord record = new LogRecord(Level.INFO, "AUDIT:" + _message);
		record.setLoggerName("audit");
		record.setParameters(new Object[]
		{
			_actor,
			_punishment
		});
		_logAudit.log(record);
		
		GmListTable.broadcastMessageToGMs(_message);
		
		switch (_punishment)
		{
			case PUNISH_BROADCAST:
				return;
			case PUNISH_KICK:
				_actor.logout(false);
				break;
			case PUNISH_KICKBAN:
				_actor.logout();
				break;
			case PUNISH_JAIL:
				_actor.setPunishLevel(L2PcInstance.PunishLevel.JAIL, Config.DEFAULT_PUNISH_PARAM);
				break;
		}
	}
}