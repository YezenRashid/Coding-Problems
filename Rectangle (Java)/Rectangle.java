// Represents a 2-dimensional rectangular region.
public class Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;

	// Constructs a new Rectangle whose top-left corner is specified by the
	// given coordinates and with the given width and height.
	public Rectangle(int x, int y, int width, int height) {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Cannot have a negative width or height");
		}

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Constructs a new Rectangle whose top-left corner is specified by the 
	// given Point and with the given width and height.
	public Rectangle(Point p, int width, int height) {
		this(p.getX(), p.getY(), width, height);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Returns whether the given Point or coordinates lie inside the bounds of this Rectangle. (Inclusive)
	public boolean contains(int x, int y) {
		int endX = this.x + width;
		int endY = this.y + height;

		if (x < this.x || y < this.y) {
			return false;
		}

		if (x > endX || y > endY) {
			return false;
		}

		return true;
	}

	// Returns whether the given Point or coordinates lie inside the bounds of this Rectangle. (Inclusive)
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}

	public String toString() {
		return "Rectangle[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
	}

	//Returns a new Rectangle that represents the area occupied by the tightest bounding box that contains both this Rectangle and the given other Rectangle. Your method should not modify the current Rectangle or the one that is passed in as a parameter; you should create and return a new rectangle.
	public Rectangle union(Rectangle rect) {
		int resultX = this.x;
		int resultY = this.y;
		int endX = this.x + this.width;
		int endY = this.y + this.height;

		if (rect.getX() < resultX) {
			resultX = rect.getX();
		}

		if (rect.getY() < resultY) {
			resultY = rect.getY();
		}

		if (rect.getX() + rect.getWidth() > endX) {
			endX = rect.getX() + rect.getWidth();
		}

		if (rect.getY() + rect.getHeight() > endY) {
			endY = rect.getY() + rect.getHeight();
		}

		return new Rectangle(resultX, resultY, endX - resultX, endY - resultY);
	}

    // Returns a new Rectangle that represents the largest rectangular region 
    // completely contained within both this rectangle and the given other 
    // rectangle.  If the rectangles do not intersect at all, returns a rectangle
    // whose width and height are both 0.
    public Rectangle intersection(Rectangle rect) {
        int left = Math.max(this.x, rect.x);
        int top = Math.max(this.y, rect.y);
        int right = Math.min(this.x + this.width, rect.x + rect.width);
        int bottom = Math.min(this.y + this.height, rect.y + rect.height);
        int width = Math.max(0, right - left);
        int height = Math.max(0, bottom - top);
        
        return new Rectangle(left, top, width, height);
    }
}