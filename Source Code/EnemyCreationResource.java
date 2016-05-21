import java.awt.Color;


public class EnemyCreationResource {
	
	private Color[][][] colorAssignment;
	private BulletStage stage;
	
	public static final Color RED = new Color(0xFF0000);
	public static final Color ORANGE = new Color(0xFF6600);
	public static final Color YELLOW = new Color(0xFFFF00);
	public static final Color GREEN = new Color(0x00FF00);
	public static final Color BLUE = new Color(0x0000FF);
	public static final Color VIOLET = new Color(0x9900CC);
	public static final Color PINK = new Color(0xFF99FF);
	public static final Color BEIGE = new Color(0xFFCC99);
	public static final Color GOLD = new Color(0xFFA200);
	public static final Color SEA = new Color(0x4BFFA5);
	public static final Color SKY = new Color(0x99E0FF);
	public static final Color INDIGO = new Color(0x4B00CC);
	
	public static final int BAT_ENEMY = 0;
	public static final int SPIDER_ENEMY = 1;
	public static final int EYE_ENEMY = 2;
	public static final int WITCH_ENEMY = 3;
	public static final int PIXIE_ENEMY = 4;
	
	public EnemyCreationResource(BulletStage stage)
	{
		this.stage = stage;
		this.colorAssignment = new Color[5][3][4]; // enemy type, enemy difficulty, colors
		this.colorAssignment[BAT_ENEMY][2][2] = 
				this.colorAssignment[SPIDER_ENEMY][0][2] =
				this.colorAssignment[EYE_ENEMY][2][0] =
				this.colorAssignment[WITCH_ENEMY][1][2] =
				this.colorAssignment[PIXIE_ENEMY][1][0] = RED;

		this.colorAssignment[BAT_ENEMY][2][3] = 
				this.colorAssignment[SPIDER_ENEMY][0][3] =
				this.colorAssignment[EYE_ENEMY][2][1] =
				this.colorAssignment[WITCH_ENEMY][1][3] =
				this.colorAssignment[PIXIE_ENEMY][1][1] = PINK;

		this.colorAssignment[BAT_ENEMY][1][0] = 
				this.colorAssignment[SPIDER_ENEMY][2][0] =
				this.colorAssignment[EYE_ENEMY][2][2] =
				this.colorAssignment[WITCH_ENEMY][0][0] =
				this.colorAssignment[PIXIE_ENEMY][1][2] = ORANGE;

		this.colorAssignment[BAT_ENEMY][1][1] = 
				this.colorAssignment[SPIDER_ENEMY][2][1] =
				this.colorAssignment[EYE_ENEMY][2][3] =
				this.colorAssignment[WITCH_ENEMY][0][1] =
				this.colorAssignment[PIXIE_ENEMY][1][3] = BEIGE;

		this.colorAssignment[BAT_ENEMY][1][2] = 
				this.colorAssignment[SPIDER_ENEMY][2][2] =
				this.colorAssignment[EYE_ENEMY][0][0] =
				this.colorAssignment[WITCH_ENEMY][0][2] =
				this.colorAssignment[PIXIE_ENEMY][2][0] = YELLOW;

		this.colorAssignment[BAT_ENEMY][1][3] = 
				this.colorAssignment[SPIDER_ENEMY][2][3] =
				this.colorAssignment[EYE_ENEMY][0][1] =
				this.colorAssignment[WITCH_ENEMY][0][3] =
				this.colorAssignment[PIXIE_ENEMY][2][1] = GOLD;

		this.colorAssignment[BAT_ENEMY][0][0] = 
				this.colorAssignment[SPIDER_ENEMY][1][0] =
				this.colorAssignment[EYE_ENEMY][0][2] =
				this.colorAssignment[WITCH_ENEMY][2][0] =
				this.colorAssignment[PIXIE_ENEMY][2][2] = GREEN;

		this.colorAssignment[BAT_ENEMY][0][1] = 
				this.colorAssignment[SPIDER_ENEMY][1][1] =
				this.colorAssignment[EYE_ENEMY][0][3] =
				this.colorAssignment[WITCH_ENEMY][2][1] =
				this.colorAssignment[PIXIE_ENEMY][2][3] = SEA;

		this.colorAssignment[BAT_ENEMY][0][2] = 
				this.colorAssignment[SPIDER_ENEMY][1][2] =
				this.colorAssignment[EYE_ENEMY][1][0] =
				this.colorAssignment[WITCH_ENEMY][2][2] =
				this.colorAssignment[PIXIE_ENEMY][0][0] = BLUE;

		this.colorAssignment[BAT_ENEMY][0][3] = 
				this.colorAssignment[SPIDER_ENEMY][1][3] =
				this.colorAssignment[EYE_ENEMY][1][1] =
				this.colorAssignment[WITCH_ENEMY][2][3] =
				this.colorAssignment[PIXIE_ENEMY][0][1] = SKY;

		this.colorAssignment[BAT_ENEMY][2][0] = 
				this.colorAssignment[SPIDER_ENEMY][0][0] =
				this.colorAssignment[EYE_ENEMY][1][2] =
				this.colorAssignment[WITCH_ENEMY][1][0] =
				this.colorAssignment[PIXIE_ENEMY][0][2] = VIOLET;

		this.colorAssignment[BAT_ENEMY][2][1] = 
				this.colorAssignment[SPIDER_ENEMY][0][1] =
				this.colorAssignment[EYE_ENEMY][1][3] =
				this.colorAssignment[WITCH_ENEMY][1][1] =
				this.colorAssignment[PIXIE_ENEMY][0][3] = INDIGO;
	}
	
	public Color getRandomColor(int enemyType, int difficulty)
	{
		int randomChoice = (int)(Math.random()*4);
		return this.colorAssignment[enemyType][difficulty][randomChoice];
	}
	
	public ShootBehavior getShootBehavior(Entity subject, Color color, int enemyType, int difficulty)
	{
		ShootBehavior retVal = null;
		Entity target = this.stage.getRandomType(Entity.ALLY_TYPE);
		
		if(difficulty == 0)
		{
			switch(enemyType)
			{
			case BAT_ENEMY:
				retVal = new BatShootBehaviorEasy(subject, this.stage, color);
				break;
			case SPIDER_ENEMY:
				retVal = null;
				break;
			case EYE_ENEMY:
				retVal = new EyeShootBehaviorEasy(subject, target, this.stage, color);
				break;
			case WITCH_ENEMY:
				retVal = new WitchShootBehaviorEasy(subject, target, this.stage, color);
				break;
			case PIXIE_ENEMY:
				retVal = new PixieShootBehaviorEasy(subject, target, this.stage, (Math.random()<0.5), color);
				break;
			}
		}
		else if(difficulty == 1)
		{
			switch(enemyType)
			{
			case BAT_ENEMY:
				retVal = new BatShootBehaviorMedium(subject, target, this.stage, color);
				break;
			case SPIDER_ENEMY:
				retVal = new SpiderShootBehaviorMedium(subject, this.stage, color);
				break;
			case EYE_ENEMY:
				retVal = new EyeShootBehaviorMedium(subject, target, this.stage, color);
				break;
			case WITCH_ENEMY:
				retVal = new WitchShootBehaviorMedium(subject, target, this.stage, color);
				break;
			case PIXIE_ENEMY:
				retVal = new PixieShootBehaviorMedium(subject, target, this.stage, color);
				break;
			}
		}
		else if(difficulty == 2)
		{
			switch(enemyType)
			{
			case BAT_ENEMY:
				retVal = new BatShootBehaviorHard(subject, this.stage, color);
				break;
			case SPIDER_ENEMY:
				retVal = new SpiderShootBehaviorHard(subject, this.stage, color);
				break;
			case EYE_ENEMY:
				retVal = new EyeShootBehaviorHard(subject, target, this.stage, color);
				break;
			case WITCH_ENEMY:
				retVal = new WitchShootBehaviorHard(subject, target, this.stage, color);
				break;
			case PIXIE_ENEMY:
				retVal = new PixieShootBehaviorHard(subject, target, this.stage, color);
				break;
			}
		}
		
		return retVal;
	}
}
