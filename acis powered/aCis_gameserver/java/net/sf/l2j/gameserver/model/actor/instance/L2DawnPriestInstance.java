package net.sf.l2j.gameserver.model.actor.instance;

import net.sf.l2j.gameserver.instancemanager.SevenSigns;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class L2DawnPriestInstance extends L2SignsPriestInstance
{
	public L2DawnPriestInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}
	
	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (command.startsWith("Chat"))
			showChatWindow(player);
		else
			super.onBypassFeedback(player, command);
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		
		String filename = SevenSigns.SEVEN_SIGNS_HTML_PATH;
		
		int sealGnosisOwner = SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_GNOSIS);
		int playerCabal = SevenSigns.getInstance().getPlayerCabal(player.getObjectId());
		
		boolean isSealValidationPeriod = SevenSigns.getInstance().isSealValidationPeriod();
		boolean isCompResultsPeriod = SevenSigns.getInstance().isCompResultsPeriod();
		
		int recruitPeriod = SevenSigns.getInstance().getCurrentPeriod();
		int compWinner = SevenSigns.getInstance().getCabalHighestScore();
		
		switch (playerCabal)
		{
			case SevenSigns.CABAL_DAWN:
				if (isCompResultsPeriod)
					filename += "dawn_priest_5.htm";
				else if (recruitPeriod == 0)
					filename += "dawn_priest_6.htm";
				else if (isSealValidationPeriod)
				{
					if (compWinner == SevenSigns.CABAL_DAWN)
					{
						if (compWinner != sealGnosisOwner)
							filename += "dawn_priest_2c.htm";
						else
							filename += "dawn_priest_2a.htm";
					}
					else if (compWinner == SevenSigns.CABAL_NULL)
						filename += "dawn_priest_2d.htm";
					else
						filename += "dawn_priest_2b.htm";
				}
				else
					filename += "dawn_priest_1b.htm";
				break;
			
			case SevenSigns.CABAL_DUSK:
				if (isSealValidationPeriod)
					filename += "dawn_priest_3a.htm";
				else
					filename += "dawn_priest_3b.htm";
				break;
			
			default:
				if (isCompResultsPeriod)
					filename += "dawn_priest_5.htm";
				else if (recruitPeriod == 0)
					filename += "dawn_priest_6.htm";
				else if (isSealValidationPeriod)
				{
					if (compWinner == SevenSigns.CABAL_DAWN)
						filename += "dawn_priest_4.htm";
					else if (compWinner == SevenSigns.CABAL_NULL)
						filename += "dawn_priest_2d.htm";
					else
						filename += "dawn_priest_2b.htm";
				}
				else
					filename += "dawn_priest_1a.htm";
				break;
		}
		
		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(filename);
		html.replace("%objectId%", getObjectId());
		player.sendPacket(html);
	}
}