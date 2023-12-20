package hust.soict.hedspi.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.swing.GUI_Component.AddBook;
import hust.soict.hedspi.swing.GUI_Component.AddCD;
import hust.soict.hedspi.swing.GUI_Component.AddDVD;

public class StoreManagerScreen extends JFrame {
	private Store store;
	public StoreManagerScreen(Store store) {
		this.store = store;
		// TODO Auto-generated constructor stub
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setTitle("Store");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");
		
		menu.add(new JMenuItem("View store"));
		
		JMenu smUpdateStore = new JMenu("Update Store");
		
		JMenuItem addBookBtn = new JMenuItem("Add Bookku");
		JMenuItem addCDBtn = new JMenuItem("Add CD");
		JMenuItem addDiscBtn = new JMenuItem("Add DVD");
		
		addBookBtn.addActionListener(e -> {
			AddBook addBook = new AddBook(store, this);
			addBook.setVisible(true);
		});
		
		addCDBtn.addActionListener(e -> {
			AddCD addCD = new AddCD(store, this);
			addCD.setVisible(true);
		});
		addDiscBtn.addActionListener(e -> {
			AddDVD addDVD = new AddDVD(store, this);
			addDVD.setVisible(true);
		});
		
		smUpdateStore.add(addBookBtn);
		smUpdateStore.add(addCDBtn);
		smUpdateStore.add(addDiscBtn);
		menu.add(smUpdateStore);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
	}
	
	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		int size = mediaInStore.size();
		for (int i = 0; i < Math.min(size, 9); i++) {
		    MediaStore cell = new MediaStore(mediaInStore.get(i));
		    center.add(cell);
		}
		
		return center;
	}
	public static void main(String[] args) {
        // Assuming you have a Store object to pass to the constructor
        Store store = new Store();
        SwingUtilities.invokeLater(() -> new StoreManagerScreen(store));
    }
}
