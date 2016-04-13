
public class CollideCircle extends CollideShape {

	public CollideCircle(Vector position, double diameter) {
		super(position, new Vector(diameter, diameter));
	}

	@Override
	public double getDistanceFromPointNearestTo(Vector external) {
		return dimension.x/2.0;
	}

}
