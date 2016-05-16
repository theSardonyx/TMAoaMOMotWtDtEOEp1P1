import java.awt.Color;
import java.util.ArrayList;

public class PixieShootBehaviorHard extends CombinationShootBehavior {

	private Color color;
	private Entity target;
	
	public PixieShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage, null);
		
		this.fireRate = 0.1;
		this.target = target;
		this.color = color;
		
		boolean orientationToRight = Math.random() > 0.5;
		PixieShootBehaviorEasy sbEasy = new PixieShootBehaviorEasy(this.subject, this.target, this.stage, orientationToRight, color);
		sbEasy.setBounce(true);
		sbEasy.setBounceCap(Math.PI / 2);
		sbEasy.setRotation(this.target.position.subtract(this.subject.position).getAngle());
		sbEasy.rotate(Math.PI / 4);
		this.add(sbEasy);
		
		PixieShootBehaviorMedium sbMedium = new PixieShootBehaviorMedium(this.subject, this.target, this.stage, color);
		this.add(sbMedium);
	}
}
