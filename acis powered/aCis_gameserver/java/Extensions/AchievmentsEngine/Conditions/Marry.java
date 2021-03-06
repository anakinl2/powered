package Extensions.AchievmentsEngine.Conditions;

import Extensions.AchievmentsEngine.Base.Condition;

import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public class Marry extends Condition
{
	public Marry(Object value)
	{
		super(value);
		setName("Married");
	}
	
	@Override
	public boolean meetConditionRequirements(L2PcInstance player)
	{
		if (getValue() == null)
			return false;
		
		if (player.isMarried())
			return true;
		
		return false;
	}
}