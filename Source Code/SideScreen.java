import java.awt.*;

public class SideScreen
{
        private DrawableString highScoreLabel, currentScoreLabel, livesLabel, runesLabel, killsLabel, expLabel, grazeLabel,
                highScoreCount, currentScoreCount, killsCount, expCount, grazeCount;
        private int hScore, cScore, kills, exp, graze = 0;
		private int health = 3;
        private Font font;
        private final double linerLabel = Runner.RES_WIDTH - 200;
		private final double linerCount = Runner.RES_WIDTH - 150;
		private final double linerHeartRow1 = 150;
		private final double linerHeartRow2 = 200;
		private final double linerRuneRow1 = 300;
		private final double linerRuneRow2 = 350;
		private SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeSheet.png", 64, 64);
		PlayerSettings playerSettings;
		
		boolean fall = false;
		
        public SideScreen()
        {
			playerSettings = PlayerSettings.getInstance();
			
            font = FontLoader.getInstance().getFont("Press Start 2P", Font.PLAIN, 10);
            highScoreLabel = new DrawableString(new Vector(linerLabel + 25, 22), "High Score:", font, Color.WHITE);
			
            currentScoreLabel = new DrawableString(new Vector(linerLabel + 37, 72), "Current Score:", font, Color.WHITE);
			
            livesLabel = new DrawableString(new Vector(linerLabel, 122), "Lives:", font, Color.WHITE);
            runesLabel = new DrawableString(new Vector(linerLabel, 262), "Runes:", font, Color.WHITE);
			
            killsLabel = new DrawableString(new Vector(linerLabel, 462), "Kills:", font, Color.WHITE);
			
            expLabel = new DrawableString(new Vector(linerLabel + 25, 512), "Experience:", font, Color.WHITE);
			
            grazeLabel = new DrawableString(new Vector(linerLabel, 562), "Graze:", font, Color.WHITE);
        }
        
        public void render(RenderWindow rw)
        {
            rw.draw(highScoreLabel);
            rw.draw(highScoreCount);
            rw.draw(currentScoreLabel);
            rw.draw(currentScoreCount);
            rw.draw(livesLabel);
            rw.draw(runesLabel);
            rw.draw(killsLabel);
            rw.draw(killsCount);
            rw.draw(expLabel);
            rw.draw(expCount);
            rw.draw(grazeLabel);         
            rw.draw(grazeCount);  
			heartRender(rw);
			runeRender(rw);
        }
		
		public void update(double delta)
		{
			playerSettings.scoreIncrement(1);
			this.cScore = playerSettings.getScore();
			this.hScore = playerSettings.getHighScore();
			this.kills = playerSettings.getKills();
			this.graze = playerSettings.getGraze();
			this.health = playerSettings.getHearts();

			currentScoreCount = new DrawableString(new Vector(linerCount, 102), Integer.toString(cScore), font, Color.WHITE);
			highScoreCount = new DrawableString(new Vector(linerCount, 52), Integer.toString(hScore), font, Color.WHITE);
			killsCount = new DrawableString(new Vector(linerCount, 492), Integer.toString(kills), font, Color.WHITE);
			expCount = new DrawableString(new Vector(linerCount, 542), Integer.toString(exp), font, Color.WHITE);
			grazeCount = new DrawableString(new Vector(linerCount, 592), Integer.toString(graze), font, Color.WHITE);
		}
		
		public void heartRender(RenderWindow rw)
		{
			for(int i = 1; i < 9; i++)
			{
				int adjust = 0;
				Vector pos;
				if(i <= 4)
				{
					adjust = 32 * i;
					pos = new Vector(linerLabel - 32 + (adjust), linerHeartRow1);
				}					
				else 
				{
					adjust = 32 * (i - 4);
					pos = new Vector(linerLabel - 32 + (adjust), linerHeartRow2);
				}
				Vector dim = new Vector(32, 32);
				if(i <= health) 
				{
					rw.draw(new DrawableImage(pos, dim, ss.get(0, 1)));
				}
				else
				{
					rw.draw(new DrawableImage(pos, dim, ss.get(0, 0)));
				}
			}
		}
		
		public void runeRender(RenderWindow rw)
		{
				//draw the runes based on the list
				for(int i = 1; i < playerSettings.getRuneNum(); i++)
				{
					int adjust = 0;
					Vector pos;
					if(i <= 5)
					{
						adjust = 32 * i;
						pos = new Vector(linerLabel - 32 + (adjust), linerRuneRow1);
					}					
					else 
					{
						adjust = 32 * (i - 5);
						pos = new Vector(linerLabel - 32 + (adjust), linerRuneRow2);
					}
					Vector dim = new Vector(32, 32);
					
					switch (playerSettings.getRune(i - 1)) {
						case PlayerSettings.PIERCE_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 2)));
						break;
						case PlayerSettings.HOMING_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 3)));
						break;
						case PlayerSettings.SPREAD_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 4)));
						break;
						case PlayerSettings.EXPLOSION_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 5)));
						break;
						case PlayerSettings.SNIPE_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 6)));
						break;
						case PlayerSettings.SPLIT_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 7)));
						break;
						case PlayerSettings.BURST_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 8)));
						break;
						case PlayerSettings.SENTINEL_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 9)));
						break;
						case PlayerSettings.ANTI_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 10)));
						break;
						case PlayerSettings.SUMMON_RUNE : rw.draw (new DrawableImage (pos, dim, ss.get (0, 11)));
						break;
						default : rw.draw(new DrawableImage(pos, dim, ss.get(1, 0)));
					}
				}
		}
}