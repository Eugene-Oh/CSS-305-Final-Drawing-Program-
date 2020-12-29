/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package view;

import java.awt.BasicStroke;

/**
 * This class will set up the entirety of the PowerPaint GUI.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.EllipseTool;
import tools.EraserTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.Tool;

public class PowerPaintGUI {
	
	 /** Color of UW's Purple */
	private final Color UW_PURPLE = new Color(51, 0, 111);
	
	 /** Color of UW's Gold. */
	private final Color UW_GOLD = new Color(232, 211, 162);
	
	 /** Frame for the overall GUI. */
	private final JFrame myFrame;
	
	/** Icon used for the frame's image icon. */
	private final ImageIcon myImage;
	
	/** The panel that the user will draw on. */
	private final DrawingPanel myDrawingPanel;
	
	/** The menu for the GUI. */
	private final JMenuBar myMenuBar;
	
	/** The toolbar for the GUI. */
	private final JToolBar myToolBar;
	    
	/** Tool actions for the differnet painting tools. */
	private List<ToolAction> myToolActions;   
	
	/** The about menu item for the GUI. */
	private JMenuItem myAboutItem;
	
	/** The thickness slider menu for the GUI. */
	private JMenuItem myThicknessItem;
	
	/** The primary color menu item for the GUI. */
	private JMenuItem myPrimaryColorItem;
	
	/** The secondary color menu item for the GUI. */
	private JMenuItem mySecondColorItem;
	
	/** The clear menu item to clear the drawings on the panel. */
	private JMenuItem myClearItem;

	/**
	 * Initializes the GUI, menubar, and toolbar.
	 */
	public PowerPaintGUI() {
		myFrame = new JFrame();
		myFrame.setTitle("Power Paint");
		myImage = new ImageIcon("images/paintbrushicon.png");
		myFrame.setIconImage(myImage.getImage());
		
		// Initializes the different paint tools.
		myToolActions = new ArrayList<ToolAction>();
		setupActions();
		myDrawingPanel = new DrawingPanel();
		myMenuBar = createMenuBar();
		myToolBar = createToolBar();
	}
	
	/**
	 * Starts the entire GUI by creating a frame and adding the menubar and toolbar and by
	 * setting tool defaults.
	 */
	public void start() {
		myFrame.add(myDrawingPanel);
		myFrame.add(myMenuBar, BorderLayout.NORTH);
		myFrame.add(myToolBar, BorderLayout.SOUTH);
		myDrawingPanel.setCurrentTool(new LineTool());	
		myDrawingPanel.setCurrentColor(UW_PURPLE);
		myDrawingPanel.setCurrentColor2(UW_GOLD);
		myDrawingPanel.setCurrentThickness(new BasicStroke(10));
		myDrawingPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
		myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
	}
	
	/**
	 * Sets up all of the actions for each tool.
	 */
	private void setupActions() {
		myToolActions.add(new ToolAction("Pencil", new ImageIcon("images/pencil.gif"), 
				          new PencilTool(), 'P'));
     	myToolActions.add(new ToolAction("Line", new ImageIcon("images/line.gif"), 
     					  new LineTool(), 'L'));
     	myToolActions.add(new ToolAction("Rectangle", new ImageIcon("images/rectangle.gif"),
     				      new RectangleTool() ,'R'));
		myToolActions.add(new ToolAction("Ellipse", new ImageIcon("images/ellipse.gif"),
					      new EllipseTool(), 'E'));
		myToolActions.add(new ToolAction("Eraser", new ImageIcon("images/eraser.gif"),
						  new EraserTool(), 'A'));
	}
	
	/**
	 * Creates the individual menu options for the menu bar.
	 * 
	 * @return The completed JMenuBar.
	 */
	private JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(createOptionsMenu(), BorderLayout.EAST);
		menuBar.add(createToolsMenu(), BorderLayout.EAST);
		menuBar.add(createHelpMenu(), BorderLayout.EAST);		
		return menuBar;
	}
	
	/**
	 * Creates the options menu.
	 * 
	 * @return The completed options menu.
	 */
	private JMenu createOptionsMenu() {
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic('O');		
		createThicknessSliderMenuItem();
		createColorMenuItems();
		createClearMenuItem();
		optionsMenu.add(myThicknessItem);
		optionsMenu.addSeparator();
		optionsMenu.add(myPrimaryColorItem);
		optionsMenu.add(mySecondColorItem);
		optionsMenu.addSeparator();
		optionsMenu.add(myClearItem);
		return optionsMenu;
	}
	
	/**
	 * @return A completed thickness slider menu.
	 */
	private void createThicknessSliderMenuItem() {
        myThicknessItem = new JMenu("Thickness");
        myThicknessItem.setMnemonic('T');
        JSlider thickSlider = new JSlider(JSlider.HORIZONTAL, 20, 10);
        thickSlider.setMajorTickSpacing(5);
        thickSlider.setMinorTickSpacing(1);
        thickSlider.setPaintTicks(true);
        thickSlider.setPaintLabels(true);
        thickSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                int value = thickSlider.getValue();
                myDrawingPanel.setCurrentThickness(new BasicStroke(value));
            }
        });
        myThicknessItem.add(thickSlider);
	}
	
	/**
	 * @return A completed color chooser menu with listeners.
	 */
	private void createColorMenuItems() {
		myPrimaryColorItem = new JMenuItem("Primary Color...", KeyEvent.VK_P);
		myPrimaryColorItem.setIcon(colorImage(UW_PURPLE));
		mySecondColorItem = new JMenuItem("Secondary Color...", KeyEvent.VK_S);
		mySecondColorItem.setIcon(colorImage(UW_GOLD));
		
	    myPrimaryColorItem.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			/**
			 * Creates a mouse listener for the primary color menu item.
			 */
			public void mousePressed(MouseEvent event) {;
				myDrawingPanel.setCurrentColor(JColorChooser.showDialog(null, "Choose a color",
											   myDrawingPanel.getCurrentColor()));
				myPrimaryColorItem.setIcon(colorImage(myDrawingPanel.getCurrentColor()));
	    	}
	    });
	    
	    mySecondColorItem.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			/**
			 * Creates a mouse listener for the secondary color menu item.
			 */
			public void mousePressed(MouseEvent event) {
				myDrawingPanel.setCurrentColor2(JColorChooser.showDialog(null, "Choose a color", myDrawingPanel.getCurrentColor2()));
				mySecondColorItem.setIcon(colorImage(myDrawingPanel.getCurrentColor2()));
	    	}
	    });
	}
	
	/**
	 * @return A color image icon with an outline.
	 */
	private ImageIcon colorImage(Color theColor) {
		BufferedImage imageColor = new BufferedImage(15, 15, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = imageColor.createGraphics();
		graphics.setPaint(theColor);
		graphics.fillRect(0, 0, imageColor.getWidth(), imageColor.getHeight());
		graphics.setPaint(Color.BLACK);
		graphics.setStroke(new BasicStroke(2));
		graphics.drawRect(0, 0, imageColor.getWidth(), imageColor.getHeight());
		ImageIcon imageIcon = new ImageIcon(imageColor);
		return imageIcon;
	}
	
	/**
	 * @return A completed clear menu item that removes all drawings from the GUI.
	 */
	private void createClearMenuItem() {
		myClearItem = new JMenuItem("Clear", KeyEvent.VK_C);
		myClearItem.setEnabled(true);
		/**
		 * Creates a mouse listener for the clear menu item.
		 */
	    myClearItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (myClearItem.isEnabled()) {
					myDrawingPanel.clearPanel();
				}	
	    	}
	    });
	}
	
	/**
	 * Creates the tools menu.
	 * 
	 * @return The completed tools menu.
	 */
	private JMenu createToolsMenu() {
		final JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic('T');
		final ButtonGroup group2 = new ButtonGroup();
		for (final ToolAction tool : myToolActions) {
			final JRadioButtonMenuItem button = new JRadioButtonMenuItem(tool);
			group2.add(button);
			toolsMenu.add(button);
		}
		return toolsMenu;
	}
	
	/**
	 * Creates the help menu.
	 * 
	 * @return The completed help menu.
	 */
	private JMenu createHelpMenu() {
		final JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
	    myAboutItem = new JMenuItem("About...", KeyEvent.VK_A);
	    myAboutItem.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			/**
			 * Creates a mouse listener for the help menu item.
			 */
			public void mousePressed(MouseEvent event) {
	    		final JOptionPane aboutPane = new JOptionPane();
	    	    Image tempImage = myImage.getImage();
	    		Image rescaledImage = tempImage.getScaledInstance(60,60, java.awt.
	    														  Image.SCALE_SMOOTH);
	    		ImageIcon rescaledIcon = new ImageIcon(rescaledImage);
	    		aboutPane.showMessageDialog(new JFrame(), "Eugene Oh\n" + "Autumn 2020\n"
	    								    + "TCSS 305 Assignment 4", "About", 
	    								    JOptionPane.INFORMATION_MESSAGE, rescaledIcon);
	    	}
	    });	    
	    helpMenu.add(myAboutItem);
	    return helpMenu;
	}
	
	/**
	 * Creates the toolbar and adds the necessary tools.
	 * 
	 * @return The completed JToolBar.
	 */
	private JToolBar createToolBar() {
		final JToolBar bar = new JToolBar();
		final ButtonGroup group = new ButtonGroup();
		for (final ToolAction tool : myToolActions) {
			final JToggleButton button = new JToggleButton(tool);
			group.add(button);
			bar.add(button);
			if (tool.toString().equals("Line")) {
				button.setSelected(true);
			}
		}
		return bar;
	}
	
	/**
	 * Creates the associated actions with the different paint tools.
	 */ 
	public class ToolAction extends AbstractAction {
		
		/** The current tool to add the action to. */
		private final Tool myTool;
		
		/** The name of the tool. */
		private final String myToolName;
		
		/** The key to set the mnemonic to. */
		private final char myKey;
		
		/** The icon used to represent the tool. */
		final ImageIcon myIcon;
		
		/**
		 * Creates the tool actions with required charactersitics.
		 * 
		 * @param theName String representation of tool name.
		 * @param theIcon Icon for the tool.
		 * @param theTool The tool to be used to create the aciton.
		 */
		ToolAction(final String theName, final Icon theIcon, final Tool theTool, final char theKey) {
			super(theName);
			myToolName = theName;
			myKey = theKey;
			myIcon = (ImageIcon) theIcon;
			final Image largeImage = myIcon.getImage().getScaledInstance
					(15, -1, java.awt.Image.SCALE_SMOOTH);
			final ImageIcon largeIcon = new ImageIcon(largeImage);
			putValue(Action.LARGE_ICON_KEY, largeIcon);
			putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar(myKey));
			putValue(Action.SELECTED_KEY, true);
			myTool = theTool;
		}
		
		/**
		 * Sets the current tool to the one chosen from the user.
		 */
	    public void actionPerformed(final ActionEvent theEvent) {
	        myDrawingPanel.setCurrentTool(myTool);
	    }
	    
		/**
		 * @return the name of the toolAction.
		 */
	    public String toString() {
	    	return myToolName;
	    }
	}
}
