package backend.entities;

public class Position {

private int x;
private int y;

public Position(int y, int x) {
	this.y = y;
	this.x = x;
	
}


public void setX(int x) {
	this.x = x;
}
public void setY(int y) {
	this.y = y;
}
public Integer getX() {
	return x;
}

public Integer getY() {
	return y;
}

public void realocate (int y, int x) {
	setX(x);
	setY(y);
}
public void realocate (Position pos) {
	setX(pos.getX());
	setY(pos.getY());
}

}
