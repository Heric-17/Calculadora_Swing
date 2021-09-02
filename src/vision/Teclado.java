package vision;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{
	
	private final Color CorTeclado = new Color(250, 244, 249);
	private final Color CorIgual = new Color(134, 163, 214);
	private final Color CorOpe = new Color(237, 222, 239);

	public Teclado() {
		GridLayout layout = new GridLayout(5, 4);

		setLayout(layout);

		addbotao("CE", CorOpe, Font.PLAIN);
		addbotao("C", CorOpe, Font.PLAIN);
		addbotao("<--", CorOpe, Font.PLAIN);
		addbotao("/", CorOpe, Font.PLAIN);

		addbotao("7", CorTeclado, Font.BOLD);
		addbotao("8", CorTeclado, Font.BOLD);
		addbotao("9", CorTeclado, Font.BOLD);
		addbotao("X", CorOpe, Font.PLAIN);

		addbotao("4", CorTeclado, Font.BOLD);
		addbotao("5", CorTeclado, Font.BOLD);
		addbotao("6", CorTeclado, Font.BOLD);
		addbotao("-", CorOpe, Font.PLAIN);
		
		addbotao("1", CorTeclado, Font.BOLD);
		addbotao("2", CorTeclado, Font.BOLD);
		addbotao("3", CorTeclado, Font.BOLD);
		addbotao("+", CorOpe, Font.PLAIN);
		
		addbotao("+/-", CorTeclado, Font.BOLD);
		addbotao("0", CorTeclado, Font.BOLD);
		addbotao(",", CorTeclado, Font.BOLD);
		addbotao("=", CorIgual, Font.PLAIN);
		
	}

	private void addbotao(String texto, Color color, int font) {

		Botao botao = new Botao(texto, color, font);
		add(botao);
		botao.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			Memoria.getInstancia().processarComando(botao.getText());
		}
	}
}
