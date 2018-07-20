import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MouseInfo;
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
	 boolean vzame;
	 LinkedList <Set<int[]>>  seznamPravilnih;
	 Krogec zacetnoPolje;
	 int [][][] pravilni;
	 
	 

	public MlinPlatno() {
//		this.setBackground(Color.white);
		koordinate = new int [][] {{0,0,0},{0,3,0},{0,6,0},{1,1,0},{1,3,0},{1,5,0},{2,2,0},{2,3,0},{2,4,0},{3,0,0},{3,1,0},{3,2,0},
                 {3,4,0},{3,5,0},{3,6,0},{4,2,0},{4,3,0},{4,4,0},{5,1,0},{5,3,0},{5,5,0},{6,0,0},{6,3,0},{6,6,0}};
        seznamPravilnih = new LinkedList<Set<int[]>> ();
        pravilni = new int [][][] {{{0,0},{0,3},{0,6}},{{1,1},{1,3},{1,5}}, {{2,2},{2,3},{2,4}}, {{3,0},{3,1},{3,2}}, {{3,4},{3,5},{3,6}},
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
		vzame = false;
		zacetnoPolje = null;
		
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
		 if (brisiM > 6 || brisiR > 6) {
			 g.drawString("Igre je konec!!!!", 100, 50);
		 }
		 else {
			 g.drawString("Na potezi: ", 100, 50);
			 g.setColor(kdoJeNaPotezi == 1 ? Color.RED : Color.BLUE);
			 g.fillOval(160, 35, 20, 20);
			 g.setColor(Color.BLACK);
			 g.drawOval(160, 35, 20, 20);
			 if (vzame) {
				 g.drawString("Zbriši: ", 200, 50);
				 g.setColor(kdoJeNaPotezi == 1 ? Color.BLUE : Color.RED);
				 g.fillOval(240, 35, 20, 20);
				 g.setColor(Color.BLACK);
				 g.drawOval(240, 35, 20, 20);
			 }
		 }
		
		 
		
		 
		 
		 
	}

	public void klik1(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Krogec[] seznam = (kdoJeNaPotezi == 1) ? seznamRdeci : seznamModri;
		for(int i = 0; i< 9; i++){
			Krogec krogec = seznam[i];
			if (krogec == null) continue;
			double r = Math.sqrt(Math.pow(x-krogec.x,2) + Math.pow(y - krogec.y, 2));
			if(((r < 20) && (kdoJeNaPotezi == 1) && (krogec.x < 75)) || ((r < 10) && (kdoJeNaPotezi == 2) && (krogec.x > 425))){
				premik = krogec;
				premikX = x;
				premikY = y;
				zacetniX = krogec.x;
				zacetniY = krogec.y;
				break;
			}
		}
	}
	
	public boolean vsebuje(Set <int[]> mnozica, int[] element) {
		for (int[] e: mnozica) {
			if (e[0] == element[0] && e[1] == element[1]) return true;
		}
		return false;
	}
	
	public boolean vsebujeVse(Set <int[]> mnozica1, Set <int[]> mnozica2) {
		for (int[] e: mnozica2) {
			if (!vsebuje(mnozica1, e)) return false;
		}
		return true;
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
							notri.add(new int[] {koordinate[k][1], koordinate[k][0]});
						}		
					}
					for (Set <int[]> trojka: seznamPravilnih) {
						if (!vsebuje(trojka, new int[] {(krogec.x - 110)/50, (krogec.y - 110)/50})) continue;
						if (vsebujeVse(notri, trojka)) {
							vzame = true;
							return; // spremeni igralca pri vzeti
						}
					}
					kdoJeNaPotezi = 3 - kdoJeNaPotezi;
					return;
				}
			
			}
			premik.x = zacetniX; // premakne nazaj na zaèetek, èe je polje zasedeno
			premik.y = zacetniY;
			premik = null;
			repaint();
		}
	
	}
	
	public void klik2(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Krogec[] seznam = (kdoJeNaPotezi == 1) ? seznamRdeci : seznamModri;
		for(int i = 0; i< 9; i++){
			Krogec krogec = seznam[i];
			if (krogec == null) continue;
			double r = Math.sqrt(Math.pow(x-krogec.x,2) + Math.pow(y - krogec.y, 2));
			if(r < 20){
				zacetnoPolje = null;
				for (int j = 0; j < 24; j++){
					if (krogec.x == seznamBeli[j].x && krogec.y == seznamBeli[j].y) {
						zacetnoPolje = seznamBeli[j];
						break;
					}
				}
				premik = krogec;
				premikX = x;
				premikY = y;
				zacetniX = krogec.x;
				zacetniY = krogec.y;
				break;
			}
		}
	}
	
	
	public boolean sosednji(Krogec k1, Krogec k2){
		for (int [][] trojka: pravilni){
			int p1x = 110 + 50*trojka[0][0];
			int p2x = 110 + 50*trojka[1][0];
			int p3x = 110 + 50*trojka[2][0];
			int p1y = 110 + 50*trojka[0][1];
			int p2y = 110 + 50*trojka[1][1];
			int p3y = 110 + 50*trojka[2][1];
			if (k1.x == p1x && k1.y == p1y && k2.x == p2x && k2.y == p2y) return true;
			if (k1.x == p2x && k1.y == p2y && k2.x == p1x && k2.y == p1y) return true;
			if (k1.x == p2x && k1.y == p2y && k2.x == p3x && k2.y == p3y) return true;
			if (k1.x == p3x && k1.y == p3y && k2.x == p2x && k2.y == p2y) return true;
		}
		return false;
	}
	
	public void spusti2(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(premik != null){
			for (int i = 0; i < 24; i++){
				Krogec krogec = seznamBeli[i];
				double raz = Math.sqrt(Math.pow(x - krogec.x , 2) + Math.pow(y - krogec.y, 2));
				if((raz < 20) && (koordinate[i][2] == 0) && sosednji(krogec, zacetnoPolje)){
					premik.x = krogec.x;
					premik.y = krogec.y;
					premik = null;
					koordinate[i][2] = kdoJeNaPotezi;
					for (int j = 0; j < 24; j ++) {
						if (zacetnoPolje.x == seznamBeli[j].x && zacetnoPolje.y == seznamBeli[j].y) {
							koordinate[j][2] = 0;
							break;
						}
					}
					repaint();
					if (kdoJeNaPotezi == 1)  {r = r + 1;}
					else {m = m+ 1;}
					Set<int[]> notri = new HashSet<int[]> (); 
					for (int k = 0; k < 24; k++){
						if (koordinate[k][2] == kdoJeNaPotezi) {
							notri.add(new int[] {koordinate[k][1], koordinate[k][0]});
						}		
					}
					for (Set <int[]> trojka: seznamPravilnih) {
						if (!vsebuje(trojka, new int[] {(krogec.x - 110)/50, (krogec.y - 110)/50})) continue;
						if (vsebujeVse(notri, trojka))  {
							vzame = true;
							return; // spremeni igralca pri vzeti
						}
					}
					kdoJeNaPotezi = 3 - kdoJeNaPotezi;
					return;
				}
			
			}
			premik.x = zacetniX; // premakne nazaj na zaèetek, èe je polje zasedeno
			premik.y = zacetniY;
			premik = null;
			repaint();
		}
	
	}
	
	public void spusti3(MouseEvent e){
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
					for (int j = 0; j < 24; j ++) {
						if (zacetnoPolje.x == seznamBeli[j].x && zacetnoPolje.y == seznamBeli[j].y) {
							koordinate[j][2] = 0;
							break;
						}
					}
					repaint();
					if (kdoJeNaPotezi == 1)  {r = r + 1;}
					else {m = m+ 1;}
					Set<int[]> notri = new HashSet<int[]> (); 
					for (int k = 0; k < 24; k++){
						if (koordinate[k][2] == kdoJeNaPotezi) {
							notri.add(new int[] {koordinate[k][1], koordinate[k][0]});
						}		
					}
					for (Set <int[]> trojka: seznamPravilnih) {
						if (!vsebuje(trojka, new int[] {(krogec.x - 110)/50, (krogec.y - 110)/50})) continue;
						if (vsebujeVse(notri, trojka))  {
							vzame = true;
							return; // spremeni igralca pri vzeti
						}
					}
					kdoJeNaPotezi = 3 - kdoJeNaPotezi;
					return;
				}
			
			}
			premik.x = zacetniX; // premakne nazaj na zaèetek, èe je polje zasedeno
			premik.y = zacetniY;
			premik = null;
			repaint();
		}
	
	}
	
	public void brisi(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Krogec[] seznam = (kdoJeNaPotezi == 1) ? seznamModri : seznamRdeci;
		for(int i = 0; i< 9; i++){
			Krogec krogec = seznam[i];
			if (krogec == null) continue;
			double r = Math.sqrt(Math.pow(x-krogec.x,2) + Math.pow(y - krogec.y, 2));
			if(r < 20 && krogec.x > 75 && krogec.x < 425) {
				for (int j = 0; j < 24; j++){
					if (krogec.x == seznamBeli[j].x && krogec.y == seznamBeli[j].y) {
						koordinate[j][2] = 0;
						break;
					}
				}
				seznam[i] = null;
				vzame = false;
				repaint();
				kdoJeNaPotezi = 3 - kdoJeNaPotezi;
				if (kdoJeNaPotezi == 1) {
					brisiR += 1;
				}
				else {
					brisiM += 1;
				}
				break;
			}
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
		if (brisiM > 6 || brisiR > 6) return;
		if (e.getButton() == MouseEvent.BUTTON3 && vzame) brisi(e);
		else if (e.getButton() == MouseEvent.BUTTON1) {
			if (m < 9) klik1(e);
			else klik2(e);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (brisiM > 6 || brisiR > 6) return;
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (m < 9) spusti1(e);
			else if (kdoJeNaPotezi == 1 && brisiR < 6 || kdoJeNaPotezi == 2 && brisiM < 6) spusti2(e);
			else spusti3(e);
		}
	}

	
	
}


