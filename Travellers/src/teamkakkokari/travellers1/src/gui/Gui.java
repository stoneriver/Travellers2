package teamkakkokari.travellers1.src.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import teamkakkokari.travellers.src.resource.ResourceManager;

public abstract class Gui extends JPanel {
	
	public final ResourceManager manager;
	public final TravellersFrame invoker;
	
	public Gui(ResourceManager manager, TravellersFrame invoker) {
		super(true);
		this.manager = manager;
		this.invoker = invoker;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}
