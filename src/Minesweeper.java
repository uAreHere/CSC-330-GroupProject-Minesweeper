import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Minesweeper extends JFrame implements MouseListener{
	int window_width=560,window_height=640;//Initialize the game window size
	static int mine_crosswise=10,mine_vertical=10;//Initialize minefield to 10*10
	static int mine_num=10;//Initialize number of mine
	static int sign_num=0;//Initialize number of flag
	static double score1=200;//Initialize the basic score for Player1
	static int numofclick=0;
	static int area1=0;
	static int area2=0;
	
	
	static int bet1=0;
	static int bet2=0;
	static double score2=200;//Initialize the basic score for Player2
	static int [][]mine = new int [mine_crosswise][mine_vertical];//Save mine's location
	static int [][]mine1=new int [mine_crosswise][mine_vertical];//show the data
	int [][] sign=new int [mine_crosswise][mine_vertical];//save flag location
	int [][]uncertainty=new int [mine_crosswise][mine_vertical];// save the question mark location
	static BufferedImage image=null;//image path
	static int judge=0;//Determine the game state, in the game:0, win:1; lose:2
	static boolean mine_place=false;//Draw the switch of the mine to determine whether the mine has been placed, to prevent multiple placements
	
	
//	----------------------------------------------------------------
	
	public  void Minesweeper() {
		
		this.setTitle("Minesweeper");//title
		this.setSize(window_width,window_height);//window size
		this.setResizable(false);//change the window size=false
		this.setDefaultCloseOperation(Minesweeper.EXIT_ON_CLOSE);//close window means done the program
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;//get screen width
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;//get screen height
		this.setLocation((width- window_width)/2,(height-window_height)/2);//The default position of the window is centered
		this.setVisible(true);//if show the screen =true
		setBet();
		this.addMouseListener(this);
        	
		
	}
	
	public static void setBet() {
		bet1=Integer.parseInt(JOptionPane.showInputDialog(null,"Player 1: Please enter your bet!"));
		bet2=Integer.parseInt(JOptionPane.showInputDialog(null,"Player 2: Please enter your bet!"));
	}
		
	//-----------------------------------------------------------------------
	//Override the paint method to draw the interface
	public void paint(Graphics g) {		
		super.paint(g);//prevent drawing string overlap
		//prevents screen flickering
		BufferedImage bi =new BufferedImage(window_width,window_height,BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = bi.createGraphics();
		//number of rest mine
		
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("Mine: "+mine_num,20,80);
		
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("SCOREP1: "+score1,window_width -370, 80);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		//JLabel label=new JLabel();
		//label.setText("Area1:"+area1);
		//JFrame frame=new JFrame();
		//frame.add(label);
		//frame.setLayout(null);
	
		g2.drawString("AREA1: "+area1,window_width -370, 100);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("bet1: "+bet1,window_width -370, 60);

		
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("SCOREP2: "+score2,window_width - 200, 80);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("AREA2: "+area2,window_width - 200, 100);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("bet2: "+bet2,window_width - 200, 60);
		
		
		again();
		g2.drawImage(image,(window_width - 50)/4,45,this);
		
		/*
		 Draw minefields, generate minefields cyclically according to the initialized minefield value, 
		 1 to 8 :display the number of surrounding minefields
		 */

		
		for(int i=0;i<mine_crosswise;i++) {
			for(int j=0;j<mine_vertical;j++) {
				if(mine1[i][j]==-1) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\1.png"));
			
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==1) {
					try{
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\01.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==2) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\02.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==3) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\03.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}		
				if(mine1[i][j]==4) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\04.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}	
				if(mine1[i][j]==5) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\05.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==6) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\06.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==7) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\07.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==8) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\sc330\\CSC-330-GroupProject-Minesweeper-main\\img\\08.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(sign[i][j]==1) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\sign.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(uncertainty[i][j]==1) {
					try {
						image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\uncertainty.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
			
			try {
				image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\0.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(image,30+i*50,110+j*50,this);
			
		}

			
	}	
		
		
		/*
		 Show the location of all mines after the game is over
		 */
		if(judge == 1 || judge == 2) {
			for(int i=0; i< mine_crosswise;i++) {
				for(int j=0; j< mine_vertical; j++) {
					if(mine[i][j]==9) {
						try {
							image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\mine.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						g2.drawImage(image,30+i*50,110+j*50,this);
					}
				}
			}
		}
		//Call the method to place mines
		place();
		//Draw all of the above 
		
		g.drawImage(bi,0,0,this);
		
			    	
	}
	
	
	
	
	/*
	 The chain is realized through a recursive algorithm, and the four sides,
	  four corners, and the rest of the middle part are judged. If zero is changed to -1, 
	 it means that it has been opened, otherwise the number will be displayed.
	 */
	
	public static void scan(int x, int y) {
		
		//up
		if(x-1>=0&& x+1<=mine_crosswise-1&&y-1<0) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
		}
		//down
		if(x-1>=0&& x+1<=mine_crosswise-1&& y+1>mine_vertical-1 ) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y+1]=-1;
				scan(x-1,y-1);
			}else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
		}
		//left
		if(y-1>=0&& y+1<=mine_crosswise-1&&x-1<0) {
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
		}
		//right
		if(y-1>=0&& y+1<=mine_vertical-1&&x+1>mine_crosswise-1) {
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
		}
		//left up
		if(x-1<0&& y-1<0) {
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}else {
				mine1[x][y+1]=mine[x][y+1];
			}
			
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			
		}
		//right up
		if(x+1>=mine_crosswise&& y-1<0) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}else {
				mine1[x][y+1]=mine[x][y+1];
			}
			
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			
		}
		//left down
		if(x-1<0&& y+1>=mine_vertical) {
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}else {
				mine1[x][y-1]=mine[x][y-1];
			}
			
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			
		}
		//right down
		if(x+1>=mine_crosswise && y+1 >= mine_vertical) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}else {
				mine1[x][y-1]=mine[x][y-1];
			}
			
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}
			else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
		}
		//middle
		if(x-1>=0 && y-1>=0&&x+1<mine_crosswise&&y+1<mine_vertical) {
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}
			else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}else {
				mine1[x-1][y]=mine[x-1][y];
			}
			
			if(mine[x-1][y+1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
		}
	}
/*
 place mines,
The position of the random two-dimensional array is assigned a value of 1, 
which means that there is a mine here. If you randomly arrive at an area where there is already a mine, 
jump out of the loop and re-randomize
 */
	public static void place() {
		// TODO Auto-generated method stub
		
		
		if(mine_place==false) {
			Random r = new Random();
			int x,y;
			for(int i=0;i<mine_num;i++) {
				x=r.nextInt(mine_num);
				y=r.nextInt(mine_num);
				if(mine[x][y]==9) {
					i--;
					continue;
				}
				mine1[x][y]=mine[x][y]=9;
				// up
				if(x-1 >=0&&x+1<=mine_crosswise-1&&y-1<0) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
				}
				//down
				if(x-1 >=0&&x+1<=mine_crosswise-1&&y+1>mine_vertical-1) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
				}
				//left
				if(y-1 >=0&&y+1<=mine_crosswise-1&&x-1<0) {
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
				}
				
			//right
				if(y-1 >=0&&y+1<=mine_vertical-1&&x+1>mine_crosswise-1) {
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
				}
				//left up
				if(x-1 <0&&y-1<0) {
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
				}
				//right up
				if(x+1 >=mine_crosswise&&y-1<0) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
				}
				//left down
				if(x-1 <0&&y+1>=mine_vertical) {
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
				}
				//right down
				if(x+1 >=mine_crosswise &&y+1>=mine_vertical) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
				}
				//middle
				if(x-1 >=0 && y-1>=0 && x+1 < mine_crosswise && y+1 < mine_vertical) {
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
				}
			}
			mine_place= true;
		}
		
	}
	/*
	 smile-restart button
	Determine the current state of the game and change the expression on the face
	 */
	public static void again() {
		if(judge==0) {
			try {
				image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\playing.png"));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
				
			}
		else if(judge==1) {
			try {
				image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\win.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}else if(judge ==2) {
			try {
				image=ImageIO.read(new File("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\lose.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		// TODO Auto-generated method stub
	

	@Override//mouse click
	public void mouseClicked(MouseEvent e) {
		//get the potion of mouse click
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
	    
		// judge if the game is start
		if(judge==0) {
			
			//judge if the potion of mouse click is in the minefield
			if(x>30 && x<30+mine_crosswise*50 && y>110 && y<110+mine_vertical*50) {
				x=(x-30)/50;
				y=(y-110)/50;
				//Determine how the mouse is .Button1:left, Button2:middle,Button3:right;
				if(e.getButton()==MouseEvent.BUTTON1) {
					numofclick++;
					//Left button means flip
					//judge the click potion is legitimate
					if(sign[x][y]==0&&uncertainty[x][y]==0) {
						//judge if there is mine
						if(mine[x][y]!=9) {
							//recursive call
							scan(x,y);
							mine1[x][y]=mine[x][y];
							this.repaint();
						}else if(mine[x][y]==9) {
							judge=2;
							this.repaint();
							if(numofclick%2==1) {
								score1-=bet1;
								score2+=bet1;
							JOptionPane.showMessageDialog(this,"person1: BOOM");}
							else {score2-=bet2;
								score1+=bet1;
							JOptionPane.showMessageDialog(this,"person2:BOOM");}
						}
						
						int num=0;//Count how many squares are left not fliped
						for(int i=0;i<mine_crosswise;i++) {
							for(int j=0;j<mine_vertical;j++) {
								if(mine1[i][j]==0) {
									++num;
								}
							}
						}
						if(numofclick%2==1) area1=area1+100-num-area2;
						else area2=area2+100-num-area1;

						
						if(num==0) {
							judge=1;
							this.repaint();
							if(area1>area2) {
								score1+=20;
							JOptionPane.showMessageDialog(this,"Wonderful, Person1: wins;");}
							else {score2+=20;
							JOptionPane.showMessageDialog(this,"Wonderful, Person2: wins;");}
							
						}
					}
				}
				else if(e.getButton()==MouseEvent.BUTTON2) {
					numofclick++;
					//middle means question mark
					if(uncertainty[x][y]==0&&sign[x][y]==0) {
						uncertainty[x][y]=1;
					}
					else if(uncertainty[x][y]==1) {
						uncertainty[x][y]=0;
					}
					this.repaint();
				}else if(e.getButton()==MouseEvent.BUTTON3) {
					numofclick++;
					//right click means flag mark
					if(sign[x][y]==0&&sign_num<mine_num&&uncertainty[x][y]==0) {
						sign[x][y]=1;
						sign_num++;
					}else if (sign[x][y]==1) {
						sign[x][y]=0;
						sign_num--;
					}
					this.repaint();
				}
				
			}

			//restart button
		/*	if(x>=(window_width-50)/2 && y>=45 && x<=(window_width-50)/2 +50 && y<=45 +50) {
				//reset all data
				mine_num=10;//Initialize the number of mines
				//Initialize 2d array
				for(int i=0;i<mine_crosswise;i++) {
					for(int j=0;j<mine_vertical;j++) {
						mine[i][j]=0;
						mine1[i][j]=0;
						sign[i][j]=0;
						uncertainty[i][j]=0;
					}
				}
				mine_place=false;//Draw mine's Switch
				//new data
				again();//new state 
				place();//new potion of mine
				judge=0;//Restore the state of the game
				this.repaint();
			}*/
		}
		else if(judge==1||judge==2) {
			int answer=JOptionPane.showConfirmDialog(null, "Do you want to play again?");
			if(answer==0)
			{
				mine_num=10;//Initialize the number of mines
				//Initialize 2d array
				for(int i=0;i<mine_crosswise;i++) {
					for(int j=0;j<mine_vertical;j++) {
						mine[i][j]=0;
						mine1[i][j]=0;
						sign[i][j]=0;
						uncertainty[i][j]=0;
					}
				}
				mine_place=false;//Draw mine's Switch
				//new data
				again();//new state 
				place();//new potion of mine
				judge=0;//Restore the state of the game
				this.repaint();
			}
			else
				JOptionPane.showMessageDialog(null,"Thank you for playing Minesweeper!","Minesweeper",JOptionPane.PLAIN_MESSAGE);
			
		}
	}

	@Override//mouse down
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse up
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse in
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse out
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

public class Minesweeper extends JFrame implements MouseListener{
	int window_width=560,window_height=640;//Initialize the game window size
	static int mine_crosswise=10,mine_vertical=10;//Initialize minefield to 10*10
	static int mine_num=10;//Initialize number of mine
	static int sign_num=0;//Initialize number of flag
	static double score1=200;//Initialize the basic score for Player1
	static int numofclick=0;
	static int area1=0;
	static int area2=0;
	static int bet1=0;
	static int bet2=0;
	static double score2=200;//Initialize the basic score for Player2
	static int [][]mine = new int [mine_crosswise][mine_vertical];//Save mine's location
	static int [][]mine1=new int [mine_crosswise][mine_vertical];//show the data
	int [][] sign=new int [mine_crosswise][mine_vertical];//save flag location
	int [][]uncertainty=new int [mine_crosswise][mine_vertical];// save the question mark location
	static BufferedImage image=null;//image path
	static int judge=0;//Determine the game state, in the game:0, win:1; lose:2
	static boolean mine_place=false;//Draw the switch of the mine to determine whether the mine has been placed, to prevent multiple placements
	
	
//	----------------------------------------------------------------
	
	public void Minesweeper() {
		this.setTitle("Minesweeper");//title
		this.setSize(window_width,window_height);//window size
		this.setResizable(false);//change the window size=false
		this.setDefaultCloseOperation(Minesweeper.EXIT_ON_CLOSE);//close window means done the program
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;//get screen width
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;//get screen height
		this.setLocation((width- window_width)/2,(height-window_height)/2);//The default position of the window is centered
		this.addMouseListener(this);
		this.setVisible(true);//if show the screen =true
	}
	//-----------------------------------------------------------------------
	//Override the paint method to draw the interface
	public void paint(Graphics g) {		
		//prevents screen flickering
		BufferedImage bi =new BufferedImage(window_width,window_height,BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = bi.createGraphics();
		//number of rest mine
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("Mine: "+mine_num,20,80);
		
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("SCOREP1: "+score1,window_width -370, 80);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("AREA1: "+area1,window_width -370, 100);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("bet1: "+bet1,window_width -370, 60);

		
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("SCOREP2: "+score2,window_width - 200, 80);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("AREA2: "+area2,window_width - 200, 100);
		g2.setColor(Color.red);
		g2.setFont(new Font("Meiryo",8,20));
		g2.drawString("bet2: "+bet2,window_width - 200, 60);
		
		
		again();
		g2.drawImage(image,(window_width - 50)/4,45,this);
		/*
		 Draw minefields, generate minefields cyclically according to the initialized minefield value, 
		 1 to 8 :display the number of surrounding minefields
		 */

		
		for(int i=0;i<mine_crosswise;i++) {
			for(int j=0;j<mine_vertical;j++) {
				if(mine1[i][j]==-1) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\1.png"));
			
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==1) {
					try{
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\01.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==2) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\02.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==3) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\03.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}		
				if(mine1[i][j]==4) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\04.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}	
				if(mine1[i][j]==5) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\05.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==6) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\06.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==7) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(mine1[i][j]==8) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\08.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(sign[i][j]==1) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\sign.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
				if(uncertainty[i][j]==1) {
					try {
						image=ImageIO.read(new File("D:\\college\\finalproject\\img\\uncertainty.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(image,30+i*50,110+j*50,this);
					continue;
				}
			
			try {
				image=ImageIO.read(new File("D:\\college\\finalproject\\img\\0.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(image,30+i*50,110+j*50,this);
			
		}
	}	
		/*
		 Show the location of all mines after the game is over
		 */
		if(judge == 1 || judge == 2) {
			for(int i=0; i< mine_crosswise;i++) {
				for(int j=0; j< mine_vertical; j++) {
					if(mine[i][j]==9) {
						try {
							image=ImageIO.read(new File("D:\\college\\finalproject\\img\\mine.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						g2.drawImage(image,30+i*50,110+j*50,this);
					}
				}
			}
		}
		//Call the method to place mines
		place();
		//Draw all of the above 
		g.drawImage(bi,0,0,this);
	}
	/*
	 The chain is realized through a recursive algorithm, and the four sides,
	  four corners, and the rest of the middle part are judged. If zero is changed to -1, 
	 it means that it has been opened, otherwise the number will be displayed.
	 */
	
	public static void scan(int x, int y) {
		//up
		if(x-1>=0&& x+1<=mine_crosswise-1&&y-1<0) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
		}
		//down
		if(x-1>=0&& x+1<=mine_crosswise-1&& y+1>mine_vertical-1 ) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y+1]=-1;
				scan(x-1,y-1);
			}else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
		}
		//left
		if(y-1>=0&& y+1<=mine_crosswise-1&&x-1<0) {
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
		}
		//right
		if(y-1>=0&& y+1<=mine_vertical-1&&x+1>mine_crosswise-1) {
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
		}
		//left up
		if(x-1<0&& y-1<0) {
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}else {
				mine1[x][y+1]=mine[x][y+1];
			}
			
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
			
		}
		//right up
		if(x+1>=mine_crosswise&& y-1<0) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}else {
				mine1[x][y+1]=mine[x][y+1];
			}
			
			if(mine[x-1][y+1]==0) {
				mine1[x-1][y+1]=mine[x-1][y+1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x-1][y+1]=mine[x-1][y+1];
			}
			
		}
		//left down
		if(x-1<0&& y+1>=mine_vertical) {
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}else {
				mine1[x][y-1]=mine[x][y-1];
			}
			
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			
		}
		//right down
		if(x+1>=mine_crosswise && y+1 >= mine_vertical) {
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}
			else {
				mine1[x-1][y]=mine[x-1][y];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}else {
				mine1[x][y-1]=mine[x][y-1];
			}
			
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}
			else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			
		}
		//middle
		if(x-1>=0 && y-1>=0&&x+1<mine_crosswise&&y+1<mine_vertical) {
			if(mine[x-1][y-1]==0) {
				mine1[x-1][y-1]=mine[x-1][y-1]=-1;
				scan(x-1,y-1);
			}
			else {
				mine1[x-1][y-1]=mine[x-1][y-1];
			}
			if(mine[x-1][y]==0) {
				mine1[x-1][y]=mine[x-1][y]=-1;
				scan(x-1,y);
			}else {
				mine1[x-1][y]=mine[x-1][y];
			}
			
			if(mine[x-1][y+1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x-1,y+1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x][y-1]==0) {
				mine1[x][y-1]=mine[x][y-1]=-1;
				scan(x,y-1);
			}
			else {
				mine1[x][y-1]=mine[x][y-1];
			}
			if(mine[x][y+1]==0) {
				mine1[x][y+1]=mine[x][y+1]=-1;
				scan(x,y+1);
			}
			else {
				mine1[x][y+1]=mine[x][y+1];
			}
			if(mine[x+1][y-1]==0) {
				mine1[x+1][y-1]=mine[x+1][y-1]=-1;
				scan(x+1,y-1);
			}
			else {
				mine1[x+1][y-1]=mine[x+1][y-1];
			}
			if(mine[x+1][y]==0) {
				mine1[x+1][y]=mine[x+1][y]=-1;
				scan(x+1,y);
			}
			else {
				mine1[x+1][y]=mine[x+1][y];
			}
			if(mine[x+1][y+1]==0) {
				mine1[x+1][y+1]=mine[x+1][y+1]=-1;
				scan(x+1,y+1);
			}
			else {
				mine1[x+1][y+1]=mine[x+1][y+1];
			}
		}
	}
/*
 place mines,
The position of the random two-dimensional array is assigned a value of 1, 
which means that there is a mine here. If you randomly arrive at an area where there is already a mine, 
jump out of the loop and re-randomize
 */
	public static void place() {
		// TODO Auto-generated method stub
		if(mine_place==false) {
			Random r = new Random();
			int x,y;
			for(int i=0;i<mine_num;i++) {
				x=r.nextInt(mine_num);
				y=r.nextInt(mine_num);
				if(mine[x][y]==9) {
					i--;
					continue;
				}
				mine1[x][y]=mine[x][y]=9;
				// up
				if(x-1 >=0&&x+1<=mine_crosswise-1&&y-1<0) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
				}
				//down
				if(x-1 >=0&&x+1<=mine_crosswise-1&&y+1>mine_vertical-1) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
				}
				//left
				if(y-1 >=0&&y+1<=mine_crosswise-1&&x-1<0) {
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
				}
				
			//right
				if(y-1 >=0&&y+1<=mine_vertical-1&&x+1>mine_crosswise-1) {
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
				}
				//left up
				if(x-1 <0&&y-1<0) {
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
				}
				//right up
				if(x+1 >=mine_crosswise&&y-1<0) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
				}
				//left down
				if(x-1 <0&&y+1>=mine_vertical) {
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
				}
				//right down
				if(x+1 >=mine_crosswise &&y+1>=mine_vertical) {
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
				}
				//middle
				if(x-1 >=0 && y-1>=0 && x+1 < mine_crosswise && y+1 < mine_vertical) {
					if(mine[x-1][y-1]!=9) {
						mine[x-1][y-1]++;
					}
					if(mine[x-1][y]!=9) {
						mine[x-1][y]++;
					}
					if(mine[x-1][y+1]!=9) {
						mine[x-1][y+1]++;
					}
					if(mine[x][y-1]!=9) {
						mine[x][y-1]++;
					}
					if(mine[x][y+1]!=9) {
						mine[x][y+1]++;
					}
					if(mine[x+1][y-1]!=9) {
						mine[x+1][y-1]++;
					}
					
					if(mine[x+1][y]!=9) {
						mine[x+1][y]++;
					}
					if(mine[x+1][y+1]!=9) {
						mine[x+1][y+1]++;
					}
				}
			}
			mine_place= true;
		}
		
	}
	/*
	 smile-restart button
	Determine the current state of the game and change the expression on the face
	 */
	public static void again() {
		if(judge==0) {
			try {
				image=ImageIO.read(new File("D:\\college\\finalproject\\img\\playing.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
				
			}
		else if(judge==1) {
			try {
				image=ImageIO.read(new File("D:\\college\\finalproject\\img\\win.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}else if(judge ==2) {
			try {
				image=ImageIO.read(new File("D:\\college\\finalproject\\img\\lose.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
		// TODO Auto-generated method stub
		
		

	@Override//mouse click
	public void mouseClicked(MouseEvent e) {
		//get the potion of mouse click
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		
		// judge if the game is start
		if(judge==0) {
			//judge if the potion of mouse click is in the minefield
			if(x>30 && x<30+mine_crosswise*50 && y>110 && y<110+mine_vertical*50) {
				x=(x-30)/50;
				y=(y-110)/50;
				//Determine how the mouse is .Button1:left, Button2:middle,Button3:right;
				if(e.getButton()==MouseEvent.BUTTON1) {
					numofclick++;
					//Left button means flip
					//judge the click potion is legitimate
					if(sign[x][y]==0&&uncertainty[x][y]==0) {
						//judge if there is mine
						if(mine[x][y]!=9) {
							//recursive call
							scan(x,y);
							mine1[x][y]=mine[x][y];
							this.repaint();
						}else if(mine[x][y]==9) {
							judge=2;
							this.repaint();
							if(numofclick%2==1) {
								score1-=bet1;
								score2+=bet1;
							JOptionPane.showMessageDialog(this,"person1: BOOM");}
							else {score2-=bet2;
								score1+=bet1;
							JOptionPane.showMessageDialog(this,"person2:BOOM");}
						}
						
						int num=0;//Count how many squares are left not fliped
						for(int i=0;i<mine_crosswise;i++) {
							for(int j=0;j<mine_vertical;j++) {
								if(mine1[i][j]==0) {
									++num;
								}
							}
						}
						if(numofclick%2==1) area1=area1+100-num-area2;
						else area2=area2+100-num-area1;

						
						if(num==0) {
							judge=1;
							this.repaint();
							if(area1>area2) {
								score1+=20;
							JOptionPane.showMessageDialog(this,"Wonderful, Person1: wins;");}
							else {score2+=20;
							JOptionPane.showMessageDialog(this,"Wonderful, Person2: wins;");}
							
						}
					}
				}
				else if(e.getButton()==MouseEvent.BUTTON2) {
					numofclick++;
					//middle means question mark
					if(uncertainty[x][y]==0&&sign[x][y]==0) {
						uncertainty[x][y]=1;
					}
					else if(uncertainty[x][y]==1) {
						uncertainty[x][y]=0;
					}
					this.repaint();
				}else if(e.getButton()==MouseEvent.BUTTON3) {
					numofclick++;
					//right click means flag mark
					if(sign[x][y]==0&&sign_num<mine_num&&uncertainty[x][y]==0) {
						sign[x][y]=1;
						sign_num++;
					}else if (sign[x][y]==1) {
						sign[x][y]=0;
						sign_num--;
					}
					this.repaint();
				}
				
			}

			//restart button
			if(x>=(window_width-50)/2 && y>=45 && x<=(window_width-50)/2 +50 && y<=45 +50) {
				//reset all data
				mine_num=10;//Initialize the number of mines
				//Initialize 2d array
				for(int i=0;i<mine_crosswise;i++) {
					for(int j=0;j<mine_vertical;j++) {
						mine[i][j]=0;
						mine1[i][j]=0;
						sign[i][j]=0;
						uncertainty[i][j]=0;
					}
				}
				mine_place=false;//Draw mine's Switch
				//new data
				again();//new state 
				place();//new potion of mine
				judge=0;//Restore the state of the game
				this.repaint();
			}
		}
	}

	@Override//mouse down
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse up
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse in
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//mouse out
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
