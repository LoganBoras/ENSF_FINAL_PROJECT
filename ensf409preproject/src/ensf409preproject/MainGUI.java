package ensf409preproject;

import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI extends JFrame {

	private JButton b1, b2, b3, b4;
	private ObjectInputStream input;
	private BinSearchTree theTree;
	private PrintWriter pr;
	private BufferedReader scan;
	private String fileName;

	public MainGUI() {
		setFileName("");

		// super(s); dont need

		b1 = new JButton("Insert");
		b2 = new JButton("Find");
		b3 = new JButton("Browse");
		b4 = new JButton("Create Tree From File");

		// super(s);

		b1 = new JButton("Insert");
		b2 = new JButton("Find");
		b3 = new JButton("Browse");
		b4 = new JButton("Create Tree From File");

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
		add("East", new Scrollbar(Scrollbar.VERTICAL));

		setVisible(true);

		b2.addActionListener((ActionEvent e) -> {
			// String key = "";
			JFrame inputBox = new JFrame("Please enter your search key");
			inputBox.setSize(300, 50);
			JTextField userInput = new JTextField(50);
			inputBox.add(userInput);
			userInput.setVisible(true);
			userInput.addActionListener((ActionEvent a) -> {
				System.out.println(theTree.find(theTree.root, userInput.getText()));
			});
			inputBox.setVisible(true);
		});

		b3.addActionListener((ActionEvent e) -> {
			StringWriter buffer = new StringWriter();
			PrintWriter writer = new PrintWriter(buffer);
			try {
				theTree.print_tree(theTree.root, writer);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			String contents = buffer.toString();
			textArea.setText(contents);
		});

		b4.addActionListener((ActionEvent e) -> {
			JFrame f = new JFrame("Input");
			JDialog dialog = new JDialog(f, "Input");
			JLabel label = new JLabel("Please enter the file name (and hit enter then ok): ");
			JButton bOk = new JButton("OK");
			JButton bCancel = new JButton("Cancel");
			JPanel inputPanel = new JPanel();
			JTextField userInput = new JTextField(20);

			inputPanel.add(label);
			inputPanel.add(userInput);
			userInput.setVisible(true);
			inputPanel.add(bCancel);
			inputPanel.add(bOk);
			dialog.add(inputPanel);
			dialog.setSize(200, 200);

			userInput.addActionListener((ActionEvent event) -> {
				String line = userInput.getText();
				System.out.println(line);
				setFileName(line); // error with getText() here
			});

			bOk.addActionListener((ActionEvent event) -> {
				createBinaryTree();
				dialog.dispose();
			});
			bCancel.addActionListener((ActionEvent event) -> {
				dialog.dispose();
			});

			dialog.setVisible(true);
		});
	}

	private void createBinaryTree() {
		theTree = new BinSearchTree();
		try {
			scan = new BufferedReader(new FileReader(getFileName()));
			String line;
			String[] words;
			while (true) {
				line = scan.readLine();
				if (line == null)
					break;

				words = line.split("\\s+");
				theTree.insert(words[1], words[2], words[3], words[4]);
			}
		} catch (EOFException e) {
			System.out.println("End of file.");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MainGUI main = new MainGUI();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
