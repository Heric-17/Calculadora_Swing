package vision;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Memoria;
import model.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{
	
	private JLabel label;
	private final Color corBack = new Color(220, 201, 230);
	
	public Display() {
		
		Memoria.getInstancia().adicionarObservador(this);
		
		setBackground(corBack);
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Helvetica", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 50));
		add(label);
	}
	
	@Override
	public void valorAlterado(String novoValor) {	
		label.setText(novoValor);
	}
	
	
}
