package net.sf.l2j.gameserver.network.serverpackets;

import net.sf.l2j.gameserver.model.L2Party;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public final class PartySmallWindowAdd extends L2GameServerPacket
{
	private final L2PcInstance _member;
	private final int _leaderId;
	private final int _distribution;
	
	public PartySmallWindowAdd(L2PcInstance member, L2Party party)
	{
		_member = member;
		_leaderId = party.getPartyLeaderOID();
		_distribution = party.getLootDistribution();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x4f);
		writeD(_leaderId); // c3
		writeD(_distribution); // c3
		writeD(_member.getObjectId());
		writeS(_member.getName());
		writeD((int) _member.getCurrentCp()); // c4
		writeD(_member.getMaxCp()); // c4
		writeD((int) _member.getCurrentHp());
		writeD(_member.getMaxHp());
		writeD((int) _member.getCurrentMp());
		writeD(_member.getMaxMp());
		writeD(_member.getLevel());
		writeD(_member.getClassId().getId());
		writeD(0);// writeD(0x01); ??
		writeD(0);
	}
}