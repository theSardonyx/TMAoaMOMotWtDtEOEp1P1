
public class PlayerShootBehavior extends CombinationShootBehavior {
	private BasicBulletShootBehavior sb0;
	private PierceBulletShootBehavior sb1;
	private HomingBulletShootBehavior sb2;
	private SpreadBulletShootBehavior sb3;
	private ExplosionBulletShootBehavior sb4;
	private SnipeBulletShootBehavior sb5;
	private SplitBulletShootBehavior sb6;
	private BurstBulletShootBehavior sb7;
	private SentinelBulletShootBehavior sb8;
	private AntiBulletShootBehavior sb9;
	private SummonBulletShootBehavior sb10;
	private PlayerSettings playerSettings;

	public PlayerShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage, null);
		
		this.sb0 = new BasicBulletShootBehavior(this.subject, this.stage);
		this.sb1 = new PierceBulletShootBehavior(this.subject, this.stage);
		this.sb2 = new HomingBulletShootBehavior(this.subject, this.stage);
		this.sb3 = new SpreadBulletShootBehavior(this.subject, this.stage);
		this.sb4 = new ExplosionBulletShootBehavior(this.subject, this.stage);
		this.sb5 = new SnipeBulletShootBehavior(this.subject, this.stage);
		this.sb6 = new SplitBulletShootBehavior(this.subject, this.stage);
		this.sb7 = new BurstBulletShootBehavior(this.subject, this.stage);
		this.sb8 = new SentinelBulletShootBehavior(this.subject, this.stage);
		this.sb9 = new AntiBulletShootBehavior(this.subject, this.stage);
		this.sb10 = new SummonBulletShootBehavior(this.subject, this.stage);
		
		this.sb0.setLevel(1);
		this.resetRunes();
		
		this.add(sb0);
		this.add(sb1);
		this.add(sb2);
		this.add(sb3);
		this.add(sb4);
		this.add(sb5);
		this.add(sb6);
		this.add(sb7);
		this.add(sb8);
		this.add(sb9);
		this.add(sb10);
		
		this.playerSettings = PlayerSettings.getInstance();
	}

	@Override
	public void shoot(double delta) {
		if(playerSettings.changedRunes()) {
			this.resetRunes();
			for(int i = 0; playerSettings.getRuneNum() -1  > i; i++) {
				if(playerSettings.getRune(i) != -1) {
					sb0.levelUp();
					switch(playerSettings.getRune(i)) {
						case PlayerSettings.PIERCE_RUNE:
							sb1.levelUp();
							break;
						case PlayerSettings.HOMING_RUNE:
							sb2.levelUp();
							break;
						case PlayerSettings.SPREAD_RUNE:
							sb3.levelUp();
							break;
						case PlayerSettings.EXPLOSION_RUNE:
							sb4.levelUp();
							break;
						case PlayerSettings.SNIPE_RUNE:
							sb5.levelUp();
							break;
						case PlayerSettings.SPLIT_RUNE:
							sb6.levelUp();
							break;
						case PlayerSettings.BURST_RUNE:
							sb7.levelUp();
							break;
						case PlayerSettings.SENTINEL_RUNE:
							sb8.levelUp();
							break;
						case PlayerSettings.ANTI_RUNE:
							sb9.levelUp();
							break;
						case PlayerSettings.SUMMON_RUNE:
							sb10.levelUp();
							break;
					}
				}
			}
			this.playerSettings.setChangedRunes(false);
		}
		super.shoot(delta);
	}
	
	public void resetRunes() {
		this.sb1.setLevel(0);
		this.sb2.setLevel(0);
		this.sb3.setLevel(0);
		this.sb4.setLevel(0);
		this.sb5.setLevel(0);
		this.sb6.setLevel(0);
		this.sb7.setLevel(0);
		this.sb8.setLevel(0);
		this.sb9.setLevel(0);
		this.sb10.setLevel(0);
	}
	
	public void setLevel(int level) {
		this.sb0.setLevel(level);
	}
	
	public void levelUp() {
		this.sb0.levelUp();
	}
}
