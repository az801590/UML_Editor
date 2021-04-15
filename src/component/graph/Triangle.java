package component.graph;

public class Triangle {
	// point order: clockwise
	private int point0[] = { -1, -1 };
	private int point1[] = new int[2];
	private int point2[] = new int[2];
	private double degree = 60;
	private int diagonalPoint[] = { -1, -1 };

	Triangle(int point[], int diaPoint[]) {
		this.point0 = point;
		this.diagonalPoint = diaPoint;
	}

	protected int[] getPoint1() {
// A little bit inaccurate.
//		point1[0] = (int) (point0[0] + (Math.acos(Math.toRadians(degree / 2)))
//				* ((diagonalPoint[0] - point0[0]) * Math.cos(Math.toRadians(degree / 2))
//						- (diagonalPoint[1] - point0[1]) * Math.sin(Math.toRadians(degree / 2))));
//		point1[1] = (int) (point0[1] + (Math.acos(Math.toRadians(degree / 2)))
//				* ((diagonalPoint[0] - point0[0]) * Math.sin(Math.toRadians(degree / 2))
//						+ (diagonalPoint[1] - point0[1]) * Math.cos(Math.toRadians(degree / 2))));

		point1[0] = (int) (diagonalPoint[0] + (1 / Math.sqrt(3)) * (point0[1] - diagonalPoint[1]));
		point1[1] = (int) (diagonalPoint[1] + (1 / Math.sqrt(3)) * (diagonalPoint[0] - point0[0]));

		return point1;
	}

	protected int[] getPoint2() {
// A little bit inaccurate.
//		point2[0] = (int) (point0[0] + (Math.acos(Math.toRadians(degree / 2)))
//				* ((diagonalPoint[0] - point0[0]) * Math.cos(Math.toRadians(360 - degree / 2))
//						- (diagonalPoint[1] - point0[1]) * Math.sin(Math.toRadians(360 - degree / 2))));
//		point2[1] = (int) (point0[1] + (Math.acos(Math.toRadians(degree / 2)))
//				* ((diagonalPoint[0] - point0[0]) * Math.sin(Math.toRadians(360 - degree / 2))
//						+ (diagonalPoint[1] - point0[1]) * Math.cos(Math.toRadians(360 - degree / 2))));

		point2[0] = (int) (diagonalPoint[0] - (1 / Math.sqrt(3)) * (point0[1] - diagonalPoint[1]));
		point2[1] = (int) (diagonalPoint[1] - (1 / Math.sqrt(3)) * (diagonalPoint[0] - point0[0]));

		return point2;
	}
}
