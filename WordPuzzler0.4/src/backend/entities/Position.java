package backend.entities;

public class Position {

private int x;
private int y;

public Position(int y, int x) {
	this.y = y;
	this.x = x;
	
}

/**
 * setter for the X attribute
 * @param x
 */
public void setX(int x) {
	this.x = x;
}
/**
 * setter for the Y attribute
 * @param y
 */
public void setY(int y) {
	this.y = y;
}
/**
 * getter for the X attribute
 * @return X
 */
public Integer getX() {
	return x;
}
/**
 * getter for the Y attribute
 * @return Y
 */
public Integer getY() {
	return y;
}

/**
 * changes the x and y value
 * @param y the new y value
 * @param x the new x value
 */
public void realocate (int y, int x) {
	setX(x);
	setY(y);
}
/**
 * overload to realocate to a new position
 * @param pos the new position
 */
public void realocate (Position pos) {
	setX(pos.getX());
	setY(pos.getY());
}

}
