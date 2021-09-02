package vision;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton {
	
	private final Color CorBack = new Color(223, 201, 230);

	public Botao(String texto, Color color, int font) {
		setText(texto);
		setFont(new Font("Courier", font, 22));
		setOpaque(true);
		setBackground(color);
		setForeground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(CorBack));
	}
}
