import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class MobWaveProducer {
	
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
	
	private Color[][][] colorAssignment;
	private int[][][] waveCount;
	private BulletStage stage;
	
	public MobWaveProducer(BulletStage stage) 
	{
		this.waveCount = new int[10][3][3]; // level, wave category, enemy difficulty
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
		
		boolean finished = false;
		while(!finished)
		{
			try 
			{
				FileReader fr = new FileReader(FileLoader.getInstance().getFile("wave_dist.ini"));
				Scanner reader = new Scanner(fr);
				
				for(int i=0; i<10; i++)
				{
					for(int j=0; j<3; j++)
					{
						for(int k=0; k<3; k++)
						{
							waveCount[i][j][k] = reader.nextInt();
						}
					}
				}
				
				finished = true;
				reader.close();
			} 
			catch (FileNotFoundException e) 
			{
				try 
				{
					PrintWriter writer = new PrintWriter("wave_dist.ini");
					writer.print(	"4 1 0 0 0 0 0 0 0\n" + 
									"3 1 0 2 0 0 0 0 0\n" + 
									"1 2 1 2 1 0 0 0 0\n" + 
									"0 2 1 2 2 0 1 0 0\n" + 
									"0 0 2 2 2 1 1 0 0\n" + 
									"0 0 1 1 3 2 2 1 0\n" + 
									"0 0 0 0 3 3 2 2 1\n" + 
									"0 0 0 0 2 3 1 3 3\n" + 
									"0 0 0 0 1 3 0 3 5\n" + 
									"0 0 0 0 3 3 0 3 7\n");
					writer.flush();
					writer.close();
				} catch (FileNotFoundException e1) {}
			}
		}
	}
	
	public MobWave generateMasterWave() 
	{
		MobWave[] mobwaves = new MobWave[10];
		
		for(int i=0; i<10; i++)
		{
			mobwaves[i] = genMobWaveForLevel( i );
		}
		
		return new QueueMobWave(this.stage, 0, mobwaves);
	}
	
	private MobWave genMobWaveForLevel(int level)
	{
		ArrayList<MobWave> mobwaves = new ArrayList<MobWave>();
		
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				int number = waveCount[level][i][j];
				for(int k=0; k<number; k++)
				{
					mobwaves.add( genMobWave(i+1, j) );
				}
			}
		}
		
		MobWave[] subwaves = new MobWave[ mobwaves.size() ];
		subwaves = mobwaves.toArray(subwaves);
		
		return new QueueMobWave(this.stage, 7.5, subwaves);
	}
	
	private MobWave genMobWave(int category, int difficulty) 
	{
		MobWave generated = null;
		switch(category)
		{
		case 1:
			generated = genMobWaveCat1(difficulty);
			break;
		case 2:
			generated = genMobWaveCat2(difficulty);
			break;
		case 3:
			generated = genMobWaveCat3(difficulty);
			break;
		}
		
		return generated;
	}
	
	private MobWave genMobWaveCat1(int difficulty)
	{
		int min = 0;
		int max = 4;
		int range = (max-min)+1;
		int randomValue = (int)(Math.random()*range) + min;
		
		MobWave generated = null;
		
		switch(randomValue)
		{
		case 0:
			generated = genMobWave0(difficulty);
			break;
		case 1:
			generated = genMobWave1(difficulty);
			break;
		case 2:
			generated = genMobWave2(difficulty);
			break;
		case 3:
			generated = genMobWave3(difficulty);
			break;
		case 4:
			generated = genMobWave4(difficulty);
			break;
		}
		
		return generated;
		
	}
	
	private MobWave genMobWaveCat2(int difficulty)
	{
		double chance = Math.random();
		
		if(chance < 0.3)
		{
			MobWave partOne = genMobWaveCat1(Math.max(0, difficulty-1));
			MobWave partTwo = genMobWaveCat1(Math.max(0, difficulty-1));
			return new QueueMobWave(this.stage, 0, new MobWave[] {partOne, partTwo});
		}
		
		int min = 5;
		int max = 9;
		int range = (max-min)+1;
		int randomValue = (int)(Math.random()*range) + min;

		MobWave generated = null;
		
		switch(randomValue)
		{
		case 5:
			generated = genMobWave5(difficulty);
			break;
		case 6:
			generated = genMobWave6(difficulty);
			break;
		case 7:
			generated = genMobWave7(difficulty);
			break;
		case 8:
			generated = genMobWave8(difficulty);
			break;
		case 9:
			generated = genMobWave9(difficulty);
			break;
		}
		
		return generated;
	}
	
	private MobWave genMobWaveCat3(int difficulty)
	{
		double chance = Math.random();
		
		if(chance < 0.3)
		{
			MobWave partOne = genMobWaveCat1(Math.max(0, difficulty-1));
			MobWave partTwo = genMobWaveCat2(Math.max(0, difficulty-1));
			return new QueueMobWave(this.stage, 0, new MobWave[] {partOne, partTwo});
		}
		
		int min = 10;
		int max = 16;
		int range = (max-min)+1;
		int randomValue = (int)(Math.random()*range) + min;

		MobWave generated = null;
		
		switch(randomValue)
		{
		case 10:
			generated = genMobWave10(difficulty);
			break;
		case 11:
			generated = genMobWave11(difficulty);
			break;
		case 12:
			generated = genMobWave12(difficulty);
			break;
		case 13:
			generated = genMobWave13(difficulty);
			break;
		case 14:
			generated = genMobWave14(difficulty);
			break;
		case 15:
			generated = genMobWave15(difficulty);
			break;
		case 16:
			generated = genMobWave16(difficulty);
			break;
		}
		
		return generated;
	}
	
	private Color getRandomColor(int enemyType, int difficulty)
	{
		int randomChoice = (int)(Math.random()*4);
		return this.colorAssignment[enemyType][difficulty][randomChoice];
	}
	
	private ShootBehavior getShootBehavior(Entity subject, Color color, int enemyType, int difficulty)
	{
		ShootBehavior retVal = null;
		Entity target = this.stage.getRandomAlly();
		
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
	
	public MobWave genMobWave0(int difficulty)
	{
		MobWave[] combo = new MobWave[7];
		
		int[] spawnX = {280, 185, 90, 440, 535, 630};
		int[] targetY = {70, 175, 280, 70, 175, 280};
		int[] speedX = {-200, -150, -100, 200, 150, 100};
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		
		for(int i=0; i<6; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], -100), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
					new TimedGlideMoveBehavior(bat, new Vector(spawnX[i], targetY[i]), 1, 1),
					new AccelerateMoveBehavior(bat, Vector.zero(), Vector.zero(), 4),
					new AccelerateMoveBehavior(bat, new Vector(speedX[i], 0), Vector.zero(), 1.5)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, BAT_ENEMY, difficulty);
			sb.setFireRate(3.5);
			bat.setShootBehavior( sb );
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[6] = new VacantMobWave(this.stage, 6.5);
		
		return new CombinationMobWave(this.stage, 2.40/(difficulty+1), combo);
	}

	public MobWave genMobWave1(int difficulty)
	{
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {620, 100, 620, 100, 620, 100};
		int targetY = 140;
		int[] genVelocityX = {-260, 260, -260, 260, -260, 260};
		boolean[] startAtRight = {true, false, true, false, true, false};
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], -100), this.stage, spiderColor);
			spider.setMoveBehavior(new QueueMoveBehavior(spider, new MoveBehavior[] {
					new TimedGlideMoveBehavior(spider, new Vector(spawnX[i], targetY), 1, 1),
					new WaveMoveBehavior(spider, new Vector(genVelocityX[i], 0), 40, 1, startAtRight[i], 2),
					new AccelerateMoveBehavior(spider, new Vector(0, 200), Vector.zero(), 3)
			}));;
			ShootBehavior sb = getShootBehavior(spider, spiderColor, SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(1 + (Math.random()*0.5));
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.5, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 6);
		
		return new QueueMobWave(this.stage, 0.9/(difficulty+1), queue);
	}

	public MobWave genMobWave2(int difficulty)
	{
		MobWave[] combo = new MobWave[5];
		Color eyeColor = getRandomColor(EYE_ENEMY, difficulty);
		int[] spawnX = {-100, -100, 820, 820};
		int[] spawnY = {100, 200, 100, 200};
		int[] targetX = {200, 100, 520, 620};
		
		for(int i=0; i<4; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, eyeColor);
			eye.setMoveBehavior(new QueueMoveBehavior(eye, new MoveBehavior[] {
					new TimedGlideMoveBehavior(eye, new Vector(targetX[i], spawnY[i]), 1, 1),
					new AccelerateMoveBehavior(eye, Vector.zero(), Vector.zero(), 2),
					new TimedGlideMoveBehavior(eye, new Vector(targetX[i], -100), 2, 2)
			}));
			ShootBehavior sb = getShootBehavior(eye, eyeColor, EYE_ENEMY, difficulty);
			sb.setFireRate( 2.8 );
			eye.setShootBehavior(sb);
			combo[i] = new SingleSpawnMobWave(this.stage, 0, eye);
		}
		combo[4] = new VacantMobWave(this.stage, 5);
		
		return new CombinationMobWave(this.stage, 0.9/(difficulty+1), combo);
	}

	public MobWave genMobWave3(int difficulty)
	{
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {500, 820, 500, 820, 500, 820};
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int[] spawnY = {-100, 300, -100, 300, -100, 300};
		int centerY = -100;
		
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, spiderColor);
			MoveBehavior mb = new EllipseMoveBehavior(spider, new Vector(centerX[i], centerY), 800, 1200, 12, 2.8);
			mb.update();
			spider.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(spider, spiderColor, SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(0.7 + Math.random()*0.8);
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.25, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 3);
		
		return new QueueMobWave(this.stage, 0.9/(difficulty+1), queue);
	}

	public MobWave genMobWave4(int difficulty)
	{
		MobWave[] queue = new MobWave[9];
		Color eyeColor = getRandomColor(EYE_ENEMY, difficulty);
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		
		int[] spawnX = {-100, -100, -100, -100, 820, 820, 820, 820};
		int[] targetX = {820, 820, 820, 820, -100, -100, -100, -100};
		int[] posY = {240, 240, 240, 240, 160, 160, 160, 160};
		
		for(int i=0; i<8; i++)
		{
			Entity p;
			Vector init = new Vector(spawnX[i], posY[i]);
			Vector target = new Vector(targetX[i], posY[i]);
			if(i % 2 == 0)
			{
				p = new BatEnemy(init, this.stage, batColor);
				ShootBehavior sb = getShootBehavior(p, batColor, BAT_ENEMY, difficulty);
				sb.setFireRate(2.5);
				p.setShootBehavior(sb);
			}
			else
			{
				p = new EyeEnemy(init, this.stage, eyeColor);
				ShootBehavior sb = getShootBehavior(p, eyeColor, EYE_ENEMY, difficulty);
				sb.setFireRate(2.5);
				p.setShootBehavior(sb);
			}
			p.setMoveBehavior(new TimedGlideMoveBehavior(p, target, 4, 4));
			queue[i] = new SingleSpawnMobWave(this.stage, 0.5, p);
		}
		queue[8] = new VacantMobWave(this.stage, 4);
		
		return new QueueMobWave(this.stage, 0.9/(difficulty+1), queue);
	}

	public MobWave genMobWave5(int difficulty)
	{
		MobWave[] combo = new MobWave[4];
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		Color witchColor = getRandomColor(WITCH_ENEMY, difficulty);
		
		WitchEnemy witch = new WitchEnemy(new Vector(360, -100), this.stage, witchColor);
		witch.setShootBehavior(getShootBehavior(witch, witchColor, WITCH_ENEMY, difficulty) );
		witch.setMoveBehavior(new AccelerateMoveBehavior(witch, new Vector(0, 100), Vector.zero(), 9));
		combo[0] = new SingleSpawnMobWave(this.stage, 0, witch);
		
		int[] spawnX = {-100, 820};
		int spawnY = 100;
		int[] targetX = {260, 460};
		int targetY = 820;
		for(int i=0; i<2; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY), this.stage, batColor);
			EllipseMoveBehavior emb = new EllipseMoveBehavior(bat, Vector.zero(), 200, 200, 2, 4);
			emb.setAnchor( witch );
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
				new TimedGlideMoveBehavior(bat, new Vector(targetX[i], spawnY), 2, 2),
				emb,
				new TimedGlideMoveBehavior(bat, new Vector(targetX[i], targetY), 3, 3)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, BAT_ENEMY, difficulty);
			if(difficulty==0)
				((BatShootBehaviorEasy) sb).setOrientationToRight(i%2==0);
			bat.setShootBehavior( sb );
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[3] = new VacantMobWave(stage, 9);
		
		return new CombinationMobWave(this.stage, 0.9/(difficulty+1), combo);
	}

	public MobWave genMobWave6(int difficulty)
	{
		MobWave[] combo = new MobWave[7];
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int[] spawnY = {-100, 820, 100, 100, 300, 300};
		int[] stayX = {100, 620, 300, 420, 100, 620};
		int[] stayY = {100, 100, 200, 200, 300, 300};
		int[] exitX = {300, 420, 500, 220, 820, -100};
		int[] exitY = {-100, -100, -100, -100, 400, 400};
		
		for(int i=0; i<6; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
				new TimedGlideMoveBehavior(bat, new Vector(stayX[i], stayY[i]), 1, 6),
				new TimedGlideMoveBehavior(bat, new Vector(exitX[i], exitY[i]), 3, 3)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, BAT_ENEMY, difficulty);
			if(difficulty==0) 
			{
				((BatShootBehaviorEasy) sb).setOrientationToRight(i%2==0);
				((BatShootBehaviorEasy) sb).setHeight(35);
				sb.setFireRate( 0.75 );
			}
			else if(difficulty == 1) {
				((BatShootBehaviorMedium) sb).setNumBullets(3);
				sb.setFireRate( 2 );
			}
			else if(difficulty==2)
			{
				((BatShootBehaviorHard) sb).setHeight(25);
				sb.setFireRate( 1.5 );
			}
			bat.setShootBehavior( sb );
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[6] = new VacantMobWave(this.stage, 13);
		
		return new CombinationMobWave(this.stage, 0.9/(difficulty+1), combo);
	}

	public MobWave genMobWave7(int difficulty)
	{
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int spawnY = 50;
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int centerY = 820;
		
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], spawnY), this.stage, spiderColor);
			EllipseMoveBehavior mb = new EllipseMoveBehavior(spider, new Vector(centerX[i], centerY), 1500, 1500, 12, 2.8);
			mb.update();
			if( i%2!=0 )
				mb.reverse();
			spider.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(spider, spiderColor, SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(0.2*difficulty + Math.random()*0.5*difficulty);
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.25, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 3);
		
		return new QueueMobWave(this.stage, 0.9/(difficulty+1), queue);
	}

	public MobWave genMobWave8(int difficulty)
	{
		MobWave[] combo = new MobWave[5];
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		int[] spawnX = {-100, -100, 820, 820};
		int[] spawnY = {100, 200, 100, 200};
		int[] targetX = {300, 100, 420, 620};
		
		for(int i=0; i<4; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
					new TimedGlideMoveBehavior(bat, new Vector(targetX[i], spawnY[i]), 1, 3),
					new AccelerateMoveBehavior(bat, new Vector(0, 200), Vector.zero(), 4.5)
			}));
			
			ShootBehavior sb = getShootBehavior(bat, batColor, BAT_ENEMY, difficulty);
			sb.setFireRate( 1 );
			if(difficulty == 0){
				((BatShootBehaviorEasy) sb).setOrientationToRight(Math.random()<0.5);
				((BatShootBehaviorEasy) sb).setHeight(35);
			}
			else if(difficulty == 1)
			{
				((BatShootBehaviorMedium) sb).setNumBullets(3);
				sb.setFireRate(2);
			}
			else if(difficulty == 2)
			{
				((BatShootBehaviorHard) sb).setHeight(35);
				((BatShootBehaviorHard) sb).setFireRate(2.5);
			}
			bat.setShootBehavior(sb);
			
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[4] = new VacantMobWave(this.stage, 5);
		
		return new CombinationMobWave(this.stage, 0.9/(difficulty+1), combo);
	}

	public MobWave genMobWave9(int difficulty)
	{
		MobWave[] queue = new MobWave[7];
		Color eyeColor = getRandomColor(EYE_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int spawnY = 50;
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int centerY = 360;
		
		for(int i=0; i<6; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY), this.stage, eyeColor);
			EllipseMoveBehavior mb = new EllipseMoveBehavior(eye, new Vector(centerX[i], centerY), 620, 1500, 15, 7.5);
			mb.update();
			if( i%2!=0 )
				mb.reverse();
			eye.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(eye, eyeColor, EYE_ENEMY, difficulty);
			if(difficulty == 1) {
				((EyeShootBehaviorMedium) sb).setHeight(20);
				((EyeShootBehaviorMedium) sb).setWithStraight(true);
			}
			sb.setFireRate(2.6);
			eye.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 2, eye);
		}
		queue[6] = new VacantMobWave(this.stage, 7.5);
		
		return new QueueMobWave(this.stage, 0.9/(difficulty+1), queue);
	}

	public MobWave genMobWave10(int difficulty)
	{
		MobWave[] combo = new MobWave[6];
		
		Color witchColor = getRandomColor(WITCH_ENEMY, difficulty);
		WitchEnemy witch = new WitchEnemy(new Vector(360, -100), this.stage, witchColor);
		witch.setMoveBehavior(new QueueMoveBehavior(witch, new MoveBehavior[] {
				new TimedGlideMoveBehavior(witch, new Vector(360, 100), 1, 3),
				new AccelerateMoveBehavior(witch, new Vector(0, -150), Vector.zero(), 1)
		}));
		ShootBehavior sb = getShootBehavior(witch, witchColor, WITCH_ENEMY, difficulty);
		witch.setShootBehavior(sb);
		combo[0] = new SingleSpawnMobWave(this.stage, 0, witch);
		
		int[] spawnX = {260, 460};
		int spawnY = -100;
		int targetY = 150;
		int[] exitX = {-100, 820};
		Color batColor = getRandomColor(BAT_ENEMY, difficulty);
		for(int i=0; i<2; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[]{
					new TimedGlideMoveBehavior(bat, new Vector(spawnX[i], targetY), 1, 3),
					new TimedGlideMoveBehavior(bat, new Vector(exitX[i], targetY), 1, 1)
			}));

			ShootBehavior batShoot = getShootBehavior(bat, batColor, BAT_ENEMY, difficulty);
			sb.setFireRate( 0.75 );
			bat.setShootBehavior(batShoot);
			
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		
		spawnX = new int[] {100, 620};
		targetY = 50;
		Color eyeColor = getRandomColor(EYE_ENEMY, difficulty);
		for(int i=0; i<2; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY), this.stage, eyeColor);
			eye.setMoveBehavior(new QueueMoveBehavior(eye, new MoveBehavior[]{
					new TimedGlideMoveBehavior(eye, new Vector(spawnX[i], targetY), 1, 3),
					new TimedGlideMoveBehavior(eye, new Vector(exitX[i], targetY), 1, 1)
			}));
			ShootBehavior eyeShoot = getShootBehavior(eye, eyeColor, EYE_ENEMY, difficulty);
			eyeShoot.setFireRate(1);
			eye.setShootBehavior(eyeShoot);
			
			combo[i+3] = new SingleSpawnMobWave(this.stage, 0, eye);
		}
		combo[5] = new VacantMobWave(this.stage, 7.2);
		
		return new CombinationMobWave(this.stage, 1, combo);
	}

	private MobWave genMobWave11(int difficulty)
	{
		return null;
	}

	private MobWave genMobWave12(int difficulty)
	{
		return null;
	}

	private MobWave genMobWave13(int difficulty)
	{
		return null;
	}

	private MobWave genMobWave14(int difficulty)
	{
		return null;
	}

	private MobWave genMobWave15(int difficulty)
	{
		return null;
	}

	private MobWave genMobWave16(int difficulty)
	{
		return null;
	}
}
