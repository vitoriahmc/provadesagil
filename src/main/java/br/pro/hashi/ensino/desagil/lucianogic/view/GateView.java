package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox[] outBox;

	private Switch[] switches;
	private Gate gate;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		image = loadImage(gate.toString());

		int inSize = gate.getInSize();
		
		int outSize = gate.getOutSize();

		inBoxes = new JCheckBox[inSize];
		
		outBox = new JCheckBox[outSize];

		switches = new Switch[inSize];

		for(int i = 0; i < inSize; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}
		
		for(int i = 0; i < outSize; i++) {
		
			outBox[i] = new JCheckBox();

			outBox[i].setEnabled(false);
			
		}


		if(inSize == 1) {
			add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < inSize; i++) {
				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}
		
		if(outSize == 1) {

			add(outBox[0], 184, 60, 20, 20);
			outBox[0].setSelected(gate.read());
		}
		else {
			for(int i = 0; i < outSize; i++) {
				add(outBox[i], 184, (i+1)*53, 20, 20);		
				outBox[i].setSelected(gate.read());
			}			
		}


	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		int outSize = gate.getOutSize();
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());
		
		if(outSize == 1) {
			outBox[0].setSelected(gate.read());
		}
		else {
			for(int j = 0; j < outSize; j++) {	
				outBox[j].setSelected(gate.read(j));
			}			
		}
		
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
}
