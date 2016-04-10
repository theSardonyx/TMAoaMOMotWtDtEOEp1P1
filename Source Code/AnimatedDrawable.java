
public abstract class AnimatedDrawable extends Drawable {

	public AnimatedDrawable(Vector position, Vector dimension) {
		super(position, dimension);
	}
	public abstract void update(double delta);

}
