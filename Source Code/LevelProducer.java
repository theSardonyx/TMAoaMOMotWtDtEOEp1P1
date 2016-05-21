import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelProducer {
	
	
	private int[][][] waveCount;
	private MobWaveFactory[] mobWaveFactories;
	private BulletStage stage;
	
	public LevelProducer(BulletStage stage) 
	{
		this.waveCount = new int[10][3][3]; // level, wave category, enemy difficulty
		this.stage = stage;
		
		this.mobWaveFactories = new MobWaveFactory[17];
		mobWaveFactories[0] = new Wave0Factory(this.stage);
		mobWaveFactories[1] = new Wave1Factory(this.stage);
		mobWaveFactories[2] = new Wave2Factory(this.stage);
		mobWaveFactories[3] = new Wave3Factory(this.stage);
		mobWaveFactories[4] = new Wave4Factory(this.stage);
		mobWaveFactories[5] = new Wave5Factory(this.stage);
		mobWaveFactories[6] = new Wave6Factory(this.stage);
		mobWaveFactories[7] = new Wave7Factory(this.stage);
		mobWaveFactories[8] = new Wave8Factory(this.stage);
		mobWaveFactories[9] = new Wave9Factory(this.stage);
		mobWaveFactories[10] = new Wave10Factory(this.stage);
		mobWaveFactories[11] = new Wave11Factory(this.stage);
		mobWaveFactories[12] = new Wave12Factory(this.stage);
		mobWaveFactories[13] = new Wave13Factory(this.stage);
		mobWaveFactories[14] = new Wave14Factory(this.stage);
		mobWaveFactories[15] = new Wave15Factory(this.stage);
		mobWaveFactories[16] = new Wave16Factory(this.stage);
		
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
	
	public MobWave generateLevel(int level)
	{
		ArrayList<MobWave> mobwaves = new ArrayList<MobWave>();
		
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				int number = waveCount[level][i][j];
				for(int k=0; k<number; k++)
				{
					mobwaves.add( generateSubwaves(i+1, j) );
				}
			}
		}
		
		MobWave[] subwaves = new MobWave[ mobwaves.size() ];
		subwaves = mobwaves.toArray(subwaves);
		
		return new QueueMobWave(this.stage, 7.5, subwaves);
	}
	
	private MobWave generateSubwaves(int category, int difficulty) 
	{
		double chance = Math.random();
		
		if(difficulty > 0 && category > 1 && chance < 0.2)
		{
			MobWave partOne = generateSubwaves(category-1, difficulty-1);
			MobWave partTwo = generateSubwaves(1, difficulty-1);
			return new CombinationMobWave(this.stage, 0, new MobWave[] {partOne, partTwo});
		}
		
		int min = -1;
		int max = -1;
		switch(category)
		{
		case 1:
			min = 0;
			max = 4;
			break;
		case 2:
			min = 5;
			max = 9;
			break;
		case 3:
			min = 10;
			max = 16;
			break;
		}

		int range = (max-min)+1;
		int randomValue = (int)(Math.random()*range) + min;
		
		return mobWaveFactories[randomValue].getMobWave(difficulty);
	}
}
