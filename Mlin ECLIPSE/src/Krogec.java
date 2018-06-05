import java.awt.Color;
import java.awt.Graphics;

public class Krogec {
	int x;
	int y;
	int polmer;
	Color barva;
	
	public Krogec(int x, int y, Color barva){
		this.x = x;
		this.y = y;
		this.polmer = 10;
		this.barva = barva;
	}
	
	public void narisi(Graphics g){
		g.setColor(barva);
		g.fillOval(x-polmer,y-polmer,2*polmer,2*polmer);
		g.setColor(Color.BLACK);
		g.drawOval(x-polmer,y-polmer,2*polmer,2*polmer);
	}

}
