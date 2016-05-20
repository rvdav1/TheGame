package engine;

public class GameZone {
	private int x;
	private int y;
	private boolean available;
	private boolean ownedBy;
	
	public GameZone(int x, int y, boolean available) {
		super();
		this.x = x;
		this.y = y;
		this.available = available;
	}
	
	public GameZone(int x,int y){
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(boolean ownedBy) {
		this.ownedBy = ownedBy;
	}
}

