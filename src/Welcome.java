import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	
	public  class Welcome extends JFrame implements MouseListener {
	    Minesweeper ms; //Minesweeper game object
	    Player1 mineworker1;
	    Player2 mineworker2;
		JButton start;
	    ImageIcon player1Image,player2Image,vsImage;//image variable for player1,player2 and vs
		JPanel top,left,right,middle,bottom;
		JLabel toplabel1,toplabel2,leftlabel,rightlabel,middlelabel;
		JFrame firstPage;
		public Welcome(){
			//create top,left,right,middle,bottom panel
			 top=new JPanel();
			 left=new JPanel();
			 right=new JPanel();
			 middle=new JPanel();
			 bottom=new JPanel();
			 
			 //create Player1 and Player2 object
			 mineworker1=new Player1();
			 mineworker2=new Player2();
			
			 //set background color for top,bottom,left,right,middle panel
		    top.setBackground(Color.lightGray);
		    bottom.setBackground(Color.lightGray);
		    left.setBackground(Color.white);
		    right.setBackground(Color.white);
		    middle.setBackground(Color.white);
		    
		    //set size for each panel:top,bottom,left,middle,right
		    top.setPreferredSize(new Dimension(640,100));
		    bottom.setPreferredSize(new Dimension(640,50));
		    left.setPreferredSize(new Dimension(250,250));
		    middle.setPreferredSize(new Dimension(140,250));
		    right.setPreferredSize(new Dimension(250,250));
		    	    
		    toplabel1=new JLabel("Welcome to Minesweeper !"); //create label toplabel1
		    toplabel1.setFont(new Font("Verdana",1,30));//set text font for toplabe1
		   
		    //set text in the center of  toplabel1
		    toplabel1.setHorizontalTextPosition(JLabel.CENTER);
		    toplabel1.setVerticalTextPosition(JLabel.CENTER);
		    
		    toplabel2=new JLabel("Please choose player !");//create toplabel2
		    toplabel2.setFont(new Font("Verdana",1,20));//set text font for toplabel2
		    toplabel2.setForeground(Color.BLUE);//set text color for toplabel2
            
		    //set text in the center of toplabel2
		    toplabel2.setHorizontalTextPosition(JLabel.CENTER);
		    toplabel2.setVerticalTextPosition(JLabel.CENTER);
		   
		    //add toplabe1 and toplabe2 in panel top
		    top.add(toplabel1);
		    top.add(toplabel2);
		    
		    
		    //create player1 Image
		    player1Image=new ImageIcon("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\player1.jpg");
		    leftlabel=new JLabel(player1Image,JLabel.CENTER); //create leftlabel and put player1 image into leftlael
		    
		    leftlabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {  //when clicking player1 Image,mineworker1 will speak
					// TODO Auto-generated method stub
					mineworker1.speak();//calling speck() function
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
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
		    	
		    });
		    left.add(leftlabel);//add leftlabel to left panel
		    
		   //create vsImage
		    vsImage=new ImageIcon("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\vs.png");
		  
		  //put vsImage in the center of middlelabel
		    middlelabel=new JLabel(vsImage,JLabel.CENTER);
		    middle.add(middlelabel);
		    
		    //create player2Image
		    player2Image=new ImageIcon("C:\\Users\\sharon\\Desktop\\csc330\\CSC-330-GroupProject-Minesweeper-main\\img\\player2.jpg");
		    rightlabel=new JLabel(player2Image,JLabel.CENTER);//put player2Image in the rightlabel
		    
		    rightlabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {  //when cliking on player2Image,mineworker2 will speck
					// TODO Auto-generated method stub
					mineworker2.speak();//calling speck() function
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
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
		    	
		    });

		    right.add(rightlabel); //add rightlabel to panel right
		    
		    //create start button
		    start=new JButton();
		    start.setSize(120,80);
		    start.setText("START");
		    start.setFont(new Font("Comic Sans",Font.BOLD,20));//set text font
		    start.setForeground(Color.BLUE);//set text color
		    start.setBackground(Color.YELLOW);
		    start.setBorder(BorderFactory.createRaisedBevelBorder());		    
		    start.setFocusable(false);//make the dotted board invisable
		    start.addMouseListener(this);//add mouse listener to start button
		    
		    bottom.add(start); //add start button to panel buttom
		    
		    
		    //create new JFrame firstPage
		    firstPage=new JFrame(); 
			firstPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when close window, exit program
			firstPage.setResizable(false);//fix window siae
			firstPage.setSize(640, 400);
			firstPage.setTitle("Minesweeper");
			firstPage.setLayout(new BorderLayout());//set BorderLayout type
		    firstPage.add(top,BorderLayout.NORTH); //put panel top in north direction
		    firstPage.add(bottom,BorderLayout.SOUTH);//put panel bottom in south direction
		    firstPage.add(left,BorderLayout.WEST); //put panel left in west direction
		    firstPage.add(middle,BorderLayout.CENTER);//put panel middle in center direction
		    firstPage.add(right,BorderLayout.EAST);//put panel right in east direction
		    firstPage.pack();//size frame and work with setsize
		    firstPage.setLocationRelativeTo(null);//place firstPage in the center of the screen
			firstPage.setVisible(true);	//make firstPage visible
			
		}
		public void mouseClicked(MouseEvent e) {
			 firstPage.dispose();//close current window
		     ms=new Minesweeper();//calling constructor Minesweeper():start game Minesweeper
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
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
		
	}


