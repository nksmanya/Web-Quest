import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener {
	String[] questions = { 
		    "What does HTML stand for?",
		    "Which HTML tag is used to define an internal style sheet?",
		    "Which property is used in CSS to change the background color?",
		    "How can you make a list that lists items with bullets?",
		    "Which is the correct syntax to create a function in JavaScript?",
		    "What does URL stand for?",
		    "Which of the following is a front-end development framework?",
		    "What does HTTP stand for?",
		    "Which tag is used to create a hyperlink in HTML?",
		    "What is the purpose of the <meta> tag in HTML?"
		};

		String[][] options = { 
		    {"Hyperlinks and Text Markup Language", "Hypertext Markup Language", "Home Tool Markup Language", "Hypertext Machine Language"},
		    {"<style>", "<css>", "<script>", "<link>"},
		    {"color", "background", "background-color", "bg-color"},
		    {"<ul>", "<ol>", "<list>", "<bullet>"},
		    {"function myFunction()", "function:myFunction()", "function = myFunction()", "myFunction():function"},
		    {"Universal Resource Locator", "Uniform Resource Locator", "Unified Resource Link", "Unique Resource Link"},
		    {"Node.js", "Angular", "Express.js", "Django"},
		    {"Hypertext Transfer Protocol", "Hypertext Transition Protocol", "Hyperlink Transmission Protocol", "Hyperlink Transfer Protocol"},
		    {"<link>", "<href>", "<a>", "<hyperlink>"},
		    {"To link CSS files", "To specify metadata about the webpage", "To create interactive forms", "To embed images"}
		};
		char[] answers = { 
			    'B', // Correct answer for "What does HTML stand for?"
			    'A', // Correct answer for "Which HTML tag is used to define an internal style sheet?"
			    'C', // Correct answer for "Which property is used in CSS to change the background color?"
			    'A', // Correct answer for "How can you make a list that lists items with bullets?"
			    'A', // Correct answer for "Which is the correct syntax to create a function in JavaScript?"
			    'B', // Correct answer for "What does URL stand for?"
			    'B', // Correct answer for "Which of the following is a front-end development framework?"
			    'A', // Correct answer for "What does HTTP stand for?"
			    'C', // Correct answer for "Which tag is used to create a hyperlink in HTML?"
			    'B'  // Correct answer for "What is the purpose of the <meta> tag in HTML?"
			};
		char guess;
		char answer;
		int index;
		int correct_guesses =0;
		int total_questions = questions.length;
		int result;
		int seconds=10;
		
		JFrame frame = new JFrame();
		JTextField textfield = new JTextField();
		JTextArea textarea = new JTextArea();
		
		JButton buttonA = new JButton(); // option buttons
		JButton buttonB = new JButton();
		JButton buttonC = new JButton();
		JButton buttonD = new JButton();
		
		JLabel answer_labelA = new JLabel(); //answer labes
		JLabel answer_labelB = new JLabel();
		JLabel answer_labelC = new JLabel();
		JLabel answer_labelD = new JLabel();
		
		JLabel time_label = new JLabel(); //time label
		JLabel seconds_left = new JLabel(); // time left label
		
		JTextField number_right = new JTextField();
		JTextField percentage = new JTextField();
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds--;
				seconds_left.setText(String.valueOf(seconds));
				if(seconds<=0) {
					displayAnswer();
				}
			}
			
		});
		

	public Quiz() {
		//frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.getContentPane().setBackground(new Color(0,0,0));
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Web Quest");
		
		//text field 
		textfield.setBounds(0,0,650,50);
		textfield.setBackground(new Color(102,0,51));
		textfield.setForeground(new Color(255,255,255));
		textfield.setFont(new Font("Ink Free",Font.BOLD,30)); //font name, font style, font size
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		textfield.setVisible(true);
		
		
		textarea.setBounds(0,50,650,50);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(102,0,51));
		textarea.setForeground(new Color(255,255,255));
		textarea.setFont(new Font("MV Boli",Font.BOLD,19)); //font name, font style, font size
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		//button A
		buttonA.setBounds(0,100,100,100);
		buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");
		//button B
		buttonB.setBounds(0,200,100,100);
		buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");
		//button C
		buttonC.setBounds(0,300,100,100);
		buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");
		//button D 
		buttonD.setBounds(0,400,100,100);
		buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");
		//answer_labels A
		answer_labelA.setBounds(125,100,500,100);
		answer_labelA.setBackground(new Color(0,51,102));
		answer_labelA.setForeground(new Color(25,255,0));
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 19));
		//answer_labels B
		answer_labelB.setBounds(125,200,500,100);
		answer_labelB.setBackground(new Color(0,51,102));
		answer_labelB.setForeground(new Color(25,255,0));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 19));
		//answer_labels C
		answer_labelC.setBounds(125,300,500,100);
		answer_labelC.setBackground(new Color(0,51,102));
		answer_labelC.setForeground(new Color(25,255,0));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 19));
		//answer_labels D 
		answer_labelD.setBounds(125,400,500,100);
		answer_labelD.setBackground(new Color(0,51,102));
		answer_labelD.setForeground(new Color(25,255,0));
		answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 19));
		
		seconds_left.setBounds(535,510,100,100);
		seconds_left.setBackground(new Color(0,0,0));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		//adding the word "timer" to the seconds_left
		time_label.setBounds(535,475,100,25);
		time_label.setBackground(new Color(0,0,0));
		time_label.setForeground(new Color(255,255,255));
		time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("Timer :)");
		
		//results
		number_right.setBounds(255,225,200,100);
		number_right.setBackground(new Color(0,0,0));
		number_right.setForeground(new Color(255,255,255));
		number_right.setFont(new Font("Ink Free",Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		percentage.setBounds(255,325,200,100);
		percentage.setBackground(new Color(0,0,0));
		percentage.setForeground(new Color(255,255,255));
		percentage.setFont(new Font("Ink Free",Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		
		
		//adding button to the frame
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		//adding answer_labels 
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		//adding text field and area 
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		//adding timer
		frame.add(seconds_left);
		//adding the text timer
		frame.add(time_label);
		
		nextQuestion();
	}
	public void nextQuestion() {
		if(index>=total_questions) {
			results();
		}
		else {
			textfield.setText("Question "+(index+1));
			textarea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			timer.start();

		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(e.getSource()==buttonA) {
			answer = 'A';
			if(answer ==answers[index]) {
				correct_guesses++;
			}
		}
		if(e.getSource()==buttonB) {
			answer = 'B';
			if(answer ==answers[index]) {
				correct_guesses++;
			}
		}
		if(e.getSource()==buttonC) {
			answer = 'C';
			if(answer ==answers[index]) {
				correct_guesses++;
			}
		}
		if(e.getSource()==buttonD) {
			answer = 'D';
			if(answer ==answers[index]) {
				correct_guesses++;
			}
		}
		displayAnswer();
		
	}
	public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(answers[index] != 'A') 
			answer_labelA.setForeground(new Color(255,0,0));

		if(answers[index] != 'B') 
			answer_labelB.setForeground(new Color(255,0,0));

		if(answers[index] != 'C') 
			answer_labelC.setForeground(new Color(255,0,0));

		if(answers[index] != 'D') 
			answer_labelD.setForeground(new Color(255,0,0));
		
		Timer pause = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(25,255,0));
				answer_labelB.setForeground(new Color(25,255,0));
				answer_labelC.setForeground(new Color(25,255,0));
				answer_labelD.setForeground(new Color(25,255,0));
				
				answer = ' ';
				seconds = 10;
				seconds_left.setText(String.valueOf(seconds));
				
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
			
		});
		pause.setRepeats(false);

		pause.start();
		
	}
	public void results() {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		result =(int)((correct_guesses/(double)total_questions) *100);
		
		textfield.setText("RESULTS!");
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		
		percentage.setText(result+"%");
		
		frame.add(percentage);
		frame.add(number_right);
	}

}
