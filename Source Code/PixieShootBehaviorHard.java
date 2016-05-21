import java.awt.Color;

public class PixieShootBehaviorHard extends CombinationShootBehavior {

	private Color color;
	private Entity target;
	private PixieShootBehaviorEasy sbEasy;
	private PixieShootBehaviorMedium sbMedium;
	
	public PixieShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage, null);
		
		this.fireRate = 0.1;
		this.target = target;
		this.color = color;
		
		boolean orientationToRight = Math.random() > 0.5;
		this.sbEasy = new PixieShootBehaviorEasy(this.subject, this.target, this.stage, orientationToRight, this.color);
		this.sbEasy.setBounce(true);
		this.sbEasy.setBounceCap(Math.PI / 2);
		this.sbEasy.setRotation(this.target.position.subtract(this.subject.position).getAngle());
		this.sbEasy.rotate(Math.PI / 4);
		this.add(this.sbEasy);
		
		this.sbMedium = new PixieShootBehaviorMedium(this.subject, this.target, this.stage, this.color);
		this.add(sbMedium);
	}
	
	public PixieShootBehaviorMedium getTop() {
		return this.sbMedium;
	}
	
	public PixieShootBehaviorEasy getBottom() {
		return this.sbEasy;
	}
}
