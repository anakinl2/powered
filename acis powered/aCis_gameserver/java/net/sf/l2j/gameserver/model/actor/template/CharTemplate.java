package net.sf.l2j.gameserver.model.actor.template;

import net.sf.l2j.gameserver.templates.StatsSet;

public class CharTemplate
{
	private final int _baseSTR;
	private final int _baseCON;
	private final int _baseDEX;
	private final int _baseINT;
	private final int _baseWIT;
	private final int _baseMEN;
	
	private final double _baseCpMax;
	private final double _baseHpMax;
	private final double _baseMpMax;
	
	private final double _baseHpReg;
	private final double _baseMpReg;
	
	private final double _basePAtk;
	private final double _baseMAtk;
	private final double _basePDef;
	private final double _baseMDef;
	
	private final int _basePAtkSpd;
	
	private final int _baseAtkRange;
	
	private final int _baseCritRate;
	
	private final int _baseWalkSpd;
	private final int _baseRunSpd;
	
	protected final double _collisionRadius;
	protected final double _collisionHeight;
	
	public CharTemplate(StatsSet set)
	{
		_baseSTR = set.getInteger("str", 40);
		_baseCON = set.getInteger("con", 21);
		_baseDEX = set.getInteger("dex", 30);
		_baseINT = set.getInteger("int", 20);
		_baseWIT = set.getInteger("wit", 43);
		_baseMEN = set.getInteger("men", 20);
		
		_baseCpMax = set.getDouble("cp", 0);
		_baseHpMax = set.getDouble("hp", 0);
		_baseMpMax = set.getDouble("mp", 0);
		
		_baseHpReg = set.getDouble("hpRegen", 1.5d);
		_baseMpReg = set.getDouble("mpRegen", 0.9d);
		
		_basePAtk = set.getDouble("pAtk");
		_baseMAtk = set.getDouble("mAtk");
		_basePDef = set.getDouble("pDef");
		_baseMDef = set.getDouble("mDef");
		
		_basePAtkSpd = set.getInteger("atkSpd", 300);
		
		_baseAtkRange = set.getInteger("attackRange", 40);
		
		_baseCritRate = set.getInteger("crit", 4);
		
		_baseWalkSpd = set.getInteger("walkSpd", 0);
		_baseRunSpd = set.getInteger("runSpd", 1);
		
		_collisionRadius = set.getDouble("radius");
		_collisionHeight = set.getDouble("height");
	}
	
	public int getBaseSTR()
	{
		return _baseSTR;
	}
	
	public int getBaseCON()
	{
		return _baseCON;
	}
	
	public int getBaseDEX()
	{
		return _baseDEX;
	}
	
	public int getBaseINT()
	{
		return _baseINT;
	}
	
	public int getBaseWIT()
	{
		return _baseWIT;
	}
	
	public int getBaseMEN()
	{
		return _baseMEN;
	}
	
	public double getBaseCpMax(int level)
	{
		return _baseCpMax;
	}
	
	public double getBaseHpMax(int level)
	{
		return _baseHpMax;
	}
	
	public double getBaseMpMax(int level)
	{
		return _baseMpMax;
	}
	
	public double getBaseHpReg()
	{
		return _baseHpReg;
	}
	
	public double getBaseMpReg()
	{
		return _baseMpReg;
	}
	
	public double getBasePAtk()
	{
		return _basePAtk;
	}
	
	public double getBaseMAtk()
	{
		return _baseMAtk;
	}
	
	public double getBasePDef()
	{
		return _basePDef;
	}
	
	public double getBaseMDef()
	{
		return _baseMDef;
	}
	
	public int getBasePAtkSpd()
	{
		return _basePAtkSpd;
	}
	
	public int getBaseAtkRange()
	{
		return _baseAtkRange;
	}
	
	public int getBaseCritRate()
	{
		return _baseCritRate;
	}
	
	public int getBaseWalkSpd()
	{
		return _baseWalkSpd;
	}
	
	public int getBaseRunSpd()
	{
		return _baseRunSpd;
	}
	
	public int getCollisionRadius()
	{
		return getCollisionRadius(false);
	}
	
	/**
	 * @param sex : True - female, False - male.
	 * @return : radius depending on sex.
	 */
	public int getCollisionRadius(boolean sex)
	{
		return (int) _collisionRadius;
	}
	
	public int getCollisionHeight()
	{
		return getCollisionHeight(false);
	}
	
	/**
	 * @param sex : True - female, False - male.
	 * @return : height depending on sex.
	 */
	protected int getCollisionHeight(boolean sex)
	{
		return (int) _collisionHeight;
	}
}
