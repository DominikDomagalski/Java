import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		new Controller().startApplication();	

	}

}
class Controller {

	private Model model;
	private List<ChildWindow> window;
	
	public Controller() {
		model = new Model(this);
		window = new ArrayList<>();
	}
	public void openWindow() {
		ChildWindow cw = new ChildWindow(this);
		window.add(cw);
		cw.show(model.getData());
	}
	public void startApplication() {
		new StartWindow(this);
	}
	public void add(String message) {
		model.add(message);
		for (ChildWindow w : window) {
			w.show(model.getData());
	        w.setVisible(true);

		}
	}
	
}
class Model {
	private final Controller ctrl;
	private List<String> list;
	
	public Model(Controller ctrl) {
		this.ctrl = ctrl;
		this.list = new ArrayList<>();
		
	}
	public void add(String message) {
		this.list.add(message);
	}
	public List<String> getData(){
		return list;
}
}
class StartWindow extends JFrame {
	
	private final Controller ctrl;	
	
	public StartWindow(Controller ctrl){
		
		this.ctrl = ctrl;
		setTitle("Start Window");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JLabel jLabel1 = new JLabel("Hello in my GUI application");
		this.getContentPane().add(jLabel1);
		
		JButton jButton = new JButton("Child Window");
		jButton.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent e) {
           
       		ctrl.openWindow();
           }

    });
		
		this.getContentPane().add(jButton);
		this.setLayout(new FlowLayout());
		this.pack();
       setSize(1024, 768);        
       setVisible(true);
	}
}
class ChildWindow extends JFrame {
	
	private final Controller ctrl;
	private Container window;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	private JPanel jFrame;
	private JTextField  jTextField;
	private JButton jButton;

		public ChildWindow(Controller ctrl) {
		super();
		this.ctrl = ctrl;
	
		init();
		showChildWindow();
	}
	private void init() {

		this.window = this.getContentPane();
       this.jPanel = new JPanel();
       this.jScrollPane = new JScrollPane(jPanel);
       this.jFrame = new JPanel();
       this.jTextField = new JTextField();
       this.jButton = new JButton("OK");

       setTitle("Child Window");
       this.setSize(1024, 768);	
     
		window.setLayout(new FlowLayout());

       jScrollPane.setPreferredSize(new Dimension(950, 550));
       jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

       jTextField.setPreferredSize(new Dimension(900, 20));
       jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTextField.getText()!=null) {
					ctrl.add(jTextField.getText());
				}
				 jTextField.setText("");
			}
		});

       jFrame.add(jTextField);
       jFrame.add(jButton);
       window.add(jScrollPane);
       window.add(jFrame);
	}
	private void showChildWindow() {
       setVisible(true);
	}
	public void show(List<String> list) {
		jPanel.removeAll();
       for (String text : list) {
           JLabel jLabel = new JLabel(text);
           jPanel.add(jLabel);
       }		
	}		
}
