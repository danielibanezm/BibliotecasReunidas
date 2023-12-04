package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Multas_Ventana extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Multas_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		//		--	VOLVER AL MENÚ --
		JButton btnMen = new JButton("MENÚ");
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
			}
		});
		//----------------------------------------------------
		
		btnMen.setToolTipText("");
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(50, 26, 79, 37);
		add(btnMen);

	}

}
