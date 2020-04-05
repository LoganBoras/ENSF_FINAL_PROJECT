package ensf409preproject;

import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainGUI extends JFrame{
	
	private JButton b1,b2,b3,b4;
	private ObjectInputStream input;
	private BinSearchTree theTree;
	private PrintWriter pr;

	public MainGUI() {
		//super(s); dont need
		
		b1= new JButton("Insert");
		b2= new JButton("Find");
		b3= new JButton("Browse");
		b4= new JButton("Create Tree From File");		
		
		setTitle("Main Window");
		setSize(500, 500);

		JPanel panel1 = new JPanel();
		// panel1=(JPanel) getContentPane();
		panel1.add(b1);
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b4);

		JPanel panel2 = new JPanel();
		// panel2= (JPanel) getContentPane();
		JTextArea textArea = new JTextArea(500, 42);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.add(scrollPane);

		setLayout(new BorderLayout());
		add("North", new JLabel("An Application to Maintain Student Records"));
		add("South", getContentPane().add(panel1));
		add("Center", getContentPane().add(panel2));
		// add("East",new Scrollbar( Scrollbar.VERTICAL) );

		setVisible(true);
		
		input = new ObjectInputStream(new FileInputStream("input.txt"));
		
		try{
			while(true) {
				theTree.insert(input.readLine()); 
		}
			
			
		}catch(EOFException e) {
            System.out.println("End of file.");
		}


		b3.addActionListener((ActionEvent e)->{
			textArea.setText(theTree.print_tree(theTree.root, pr)); 
		});
		
		

		
		
		
		
				b2.addActionListener((ActionEvent e) -> {
			//String key = "";
			JFrame inputBox = new JFrame("Please enter your search key");
			inputBox.setSize(300, 200);
			JTextField userInput = new JTextField(50);
			inputBox.add(userInput);
			userInput.setVisible(true);
			userInput.addActionListener((ActionEvent a) -> {
				System.out.println(theTree.find(theTree.root,userInput.getText()));
			});
			inputBox.setVisible(true);
		});

		
		b4.addActionListener((ActionEvent e) -> {
			JDialog dialog = new JDialog();
			JButton bOk = new JButton("OK");
			JButton bCancel = new JButton("Cancel");
			dialog.add(bOk);
			dialog.add(bCancel);
			setLayout(new BorderLayout());
			add("North", new JLabel("Enter the file name:"));
			JTextArea text = new JTextArea(100, 42);
			dialog.setVisible(true);
		});

	}

	





public static void main(String[] args) {
	// TODO Auto-generated method stub
	MainGUI main = new MainGUI();
}

}



