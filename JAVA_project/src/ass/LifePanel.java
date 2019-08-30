package ass;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LifePanel extends JPanel
{
	Image[] lifeImg = new Image[3];
	
	public void paint(Graphics g)
	{
		super.paint(g);	
		
		for(int i = 0; i < MatchingGame.missCount; i++) 
		{
			int x = 50;
			
			lifeImg[i] = new ImageIcon("src/Images/smile.png").getImage();
		
			g.drawImage(lifeImg[i], 25 + (x * (i)), 50, this);
			repaint();
		}
		
	}
	
	
}
