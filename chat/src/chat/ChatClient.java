package chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatClient extends JFrame {

	JTextField textToSend;
	Socket socket;
	PrintWriter w;
	String nome;
	
	public ChatClient(String nome) {
		super("Chat : " + nome);
		this.nome = nome;
		
		Font fonte = new Font("Serif", Font.PLAIN, 26);
		textToSend = new JTextField();
		textToSend.setFont(fonte);
		JButton button = new JButton("Enviar");
		button.setFont(fonte);
		button.addActionListener(new EnviarListener());
		
		Container envio = new JPanel();
		envio.setLayout(new BorderLayout());
		envio.add(BorderLayout.CENTER, textToSend);
		envio.add(BorderLayout.EAST, button);
		getContentPane().add(BorderLayout.SOUTH, envio);
		
		configurarRede();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 90);
		setVisible(true);
	}

	private class EnviarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			w.println(nome +": "+textToSend.getText());
			w.flush();
			textToSend.setText("");
			
			textToSend.requestFocus();
		}
		
	}
	
	private void configurarRede() {
		try {
			socket = new Socket("127.0.0.1", 5002);
			w = new PrintWriter(socket.getOutputStream());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void main(String[] args) {
		new ChatClient("Felipe");
		new ChatClient("Spock");

	}


}
