import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player1 implements Player{
	ImageIcon play1=new ImageIcon("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\playing.png");//create image play1
	public void speak() {  //function speak(), show the sequence of the move in the game
		JOptionPane.showMessageDialog(null,"Hello, this is Player 1. I am in the first move","Minesweeper", JOptionPane.PLAIN_MESSAGE,play1);
	  
	}
	
}
