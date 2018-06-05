import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MlinOkno extends JFrame{
	private MlinPlatno platno;
	    
	public MlinOkno() {                           
	    platno = new MlinPlatno();   
	    add(platno);
	    }
}
