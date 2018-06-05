import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MlinPlatno extends JPanel implements MouseListener,MouseMotionListener{
	
	 Krogec [] seznamRdeci;
	 Krogec [] seznamModri;
	 Krogec [] seznamBeli;
	 int kdoJeNaPotezi;
	 Krogec premik;
	 int premikX, premikY;
	 int zacetniX, zacetniY;
	 int [][] koordinate;
	 int r;
	 int m;
	 int brisiM;
	 int brisiR;
	 int vzame;
	 LinkedList <Set<int[]>>  seznamPravilnih;
	 
	 

	public MlinPlatno() {
//		this.setBackground(Color.white);
		koordinate = new int [][] {{0,0,0},{0,3,0},{0,6,0},{1,1,0},{1,3,0},{1,5,0},{2,2,0},{2,3,0},{2,4,0},{3,0,0},{3,1,0},{3,2,0},
                 {3,4,0},{3,5,0},{3,6,0},{4,2,0},{4,3,0},{4,4,0},{5,1,0},{5,3,0},{5,5,0},{6,0,0},{6,3,0},{6,6,0}};
        seznamPravilnih = new LinkedList<Set<int[]>> ();
        int[][][] pravilni = {{{0,0},{0,3},{0,6}},{{1,1},{1,3},{1,5}}, {{2,2},{2,3},{2,4}}, {{3,0},{3,1},{3,2}}, {{3,4},{3,5},{3,6}},
                {{4,2},{4,3},{4,4}},{{5,1},{5,3},{5,5}}, {{6,0},{6,3},{6,6}}, {{0,0},{3,0},{6,0}}, {{1,1},{3,1},{5,1}},
                {{2,2},{3,2},{4,2}},{{0,3},{1,3},{2,3}}, {{4,3},{5,3},{6,3}}, {{2,4},{3,4},{4,4}}, {{1,5},{3,5},{5,5}},
                {{0,6},{3,6},{6,6}},{{0,0},{1,1},{2,2}}, {{6,0},{5,1},{4,2}}, {{2,4},{1,5},{0,6}}, {{4,4},{5,5},{6,6}}};
        for (int i = 0; i < pravilni.length; i++) {
	        Set<int[]> trojka = new HashSet<int[]>();
	        trojka.add(new int[] {pravilni[i][0][0], pravilni[i][0][1]});
	        trojka.add(new int[] {pravilni[i][1][0], pravilni[i][1][1]});
	        trojka.add(new int[] {pravilni[i][2][0], pravilni[i][2][1]});
	        seznamPravilnih.add(trojka);
        }
		seznamBeli = new Krogec[24];
		seznamRdeci = new Krogec[9];
		seznamModri = new Krogec[9];
		for (int i = 0; i<9; i++){
			seznamRdeci[i] = new Krogec(60,140+30*i,Color.RED);
			seznamModri[i] = new Krogec(460,140+30*i,Color.BLUE);
		}
		for(int i = 0; i<24; i++){
			int y = 110 + 50*koordinate[i][0];
			int x = 110 + 50*koordinate[i][1];
			seznamBeli[i] = new Krogec(x,y,Color.WHITE);
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		kdoJeNaPotezi = 1;
		premik = null;
		r = 0;
		m = 0;
		brisiM = 0;
		brisiR = 0;
		vzame = 0;
		
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(525, 525);
    }
	
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.setColor(Color.BLACK);
		 int polmer = 10;
		 g.drawLine(100 + polmer, 100+ polmer, 400 + polmer, 400+ polmer);
		 g.drawLine(250 + polmer, 100+ polmer, 250 + polmer, 400+ polmer);
		 g.drawLine(100 + polmer, 250+ polmer, 400 + polmer, 250+ polmer);
		 g.drawLine(400 + polmer, 100+ polmer, 100 + polmer, 400+ polmer);
		 g.drawRect(100 + polmer, 100 + polmer, 300, 300);
		 g.drawRect(150 + polmer, 150 + polmer, 200, 200);
		 g.setColor(Color.WHITE);
		 g.fillRect(200 + polmer, 200 + polmer, 100, 100);
		 g.setColor(Color.BLACK);
		 g.drawRect(200 + polmer, 200 + polmer, 100, 100);
		 
		 
		 for(int i = 0; i<24; i++){
			 if(seznamBeli[i] != null) seznamBeli[i].narisi(g);
		 }
		 
		 for (int i = 0; i<9; i++){
			 if(seznamRdeci[i] != null) seznamRdeci[i].narisi(g);
			 if(seznamModri[i] != null) seznamModri[i].narisi(g);
		 }
		 
		
		 
		
		 
		 
		 
	}

	public void klik1(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Krogec[] seznam = (kdoJeNaPotezi == 1) ? seznamRdeci : seznamModri;
		for(int i = 0; i< 9; i++){
			Krogec krogec = seznam[i];
			double r = Math.sqrt(Math.pow(x-krogec.x,2) + Math.pow(y - krogec.y, 2));
			if(((r < 10) && (kdoJeNaPotezi == 1) && (krogec.x < 75)) || ((r < 10) && (kdoJeNaPotezi == 2) && (krogec.x > 425))){
				premik = krogec;
				premikX = x;
				premikY = y;
				zacetniX = krogec.x;
				zacetniY = krogec.y;
				break;
			}
		}
	}
	
	public void spusti1(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(premik != null){
			for (int i = 0; i < 24; i++){
				Krogec krogec = seznamBeli[i];
				double raz = Math.sqrt(Math.pow(x - krogec.x , 2) + Math.pow(y - krogec.y, 2));
				if((raz < 20) && (koordinate[i][2] == 0)){
					premik.x = krogec.x;
					premik.y = krogec.y;
					premik = null;
					koordinate[i][2] = kdoJeNaPotezi;
					repaint();
					if (kdoJeNaPotezi == 1)  {r = r + 1;}
					else {m = m+ 1;}
					Set<int[]> notri = new HashSet<int[]> (); 
					for (int k = 0; k < 24; k++){
						if (koordinate[k][2] == kdoJeNaPotezi) {
							notri.add(new int[] {koordinate[k][0], koordinate[k][1]});
						}		
					}
					for (Set <int[]> trojka: seznamPravilnih) {
						if (notri.containsAll(trojka)) {
							vzame = kdoJeNaPotezi;
							return; // spremeni igralca pri vzeti
						}
					}
					kdoJeNaPotezi = 3 - kdoJeNaPotezi;
					return;
				}
			
			}
			premik.x = zacetniX;
			premik.y = zacetniY;
			premik = null;
			repaint();
		}
	
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(premik != null){
			premik.x += x - premikX;
			premik.y += y - premikY;
		}
		premikX = x;
		premikY = y;
		repaint();
	}
	
	
	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		klik1(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		spusti1(e);
	}

	
	
}


/* 
self.seznamRdeci = []
self.seznamModri = []
for i in range(1, 10):
self.seznamRdeci.append(self.canvas.create_oval(50, 100 + 30*i, 50 + 2*self.polmer, 100 + 30*i + 2*self.polmer, fill='red'))
self.seznamModri.append(self.canvas.create_oval(450, 100 + 30*i, 450 + 2*self.polmer, 100 + 30*i + 2*self.polmer, fill='blue'))

def klik1(self, event):
        seznam = self.canvas.find_overlapping(event.x, event.y, event.x+1, event.y+1)
        if len(seznam)==0:
            return
        for krog in seznam:
            if self.kdoJeNaPotezi == 1: 
                if krog in self.seznamRdeci and event.x < 75:
                    self.x = event.x
                    self.y = event.y
                    self.krog = krog
            elif self.kdoJeNaPotezi == 2: 
                if krog in self.seznamModri and event.x > 425:
                    self.x = event.x
                    self.y = event.y
					self.krog = krog
*/