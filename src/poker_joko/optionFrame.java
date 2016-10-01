package poker_joko;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;

public class optionFrame extends JFrame implements ActionListener, ChangeListener{

	private JPanel contentPane;
	private Player player;
	private JButton btnFold;
	private JButton btnCall;
	private JButton btnBet;
	private JSlider slider;
	int toCall;
	private JLabel lbl_raise;
	private JPanel panel;
	private JLabel lbl_c1;
	private JLabel lbl_c2;

	public optionFrame(Player player_, int playerIndex, int maxCash, int toCall_, int card1, int card2) {
		toCall = toCall_;
		player = player_;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][]", "[][][][grow]"));
		
		String title = "Player: " + Integer.toString(playerIndex);
		this.setTitle(title);
		
		btnFold = new JButton("Fold");
		contentPane.add(btnFold, "cell 0 0");
		btnFold.addActionListener(this);
		
		btnCall = new JButton("Call");
		contentPane.add(btnCall, "cell 0 1");
		btnCall.addActionListener(this);
		
		JLabel label_1 = new JLabel("");
		label_1.setText(Integer.toString(toCall));
		contentPane.add(label_1, "cell 1 1");
		
		btnBet = new JButton("Bet");
		contentPane.add(btnBet, "cell 0 2");
		btnBet.addActionListener(this);
		
		slider = new JSlider();
		slider.setMinimum(toCall + 1);
		slider.setMaximum(maxCash);
		slider.addChangeListener(this);
		contentPane.add(slider, "cell 1 2");
		
		JLabel label = new JLabel("");
		contentPane.add(label, "flowx,cell 2 2");
		
		lbl_raise = new JLabel();
		lbl_raise.setText(Integer.toString(slider.getValue()));
		contentPane.add(lbl_raise, "cell 2 2");
		
		panel = new JPanel();
		contentPane.add(panel, "cell 0 3 2 1,grow");
		panel.setLayout(new MigLayout("", "[][]", "[]"));
		
		lbl_c1 = new JLabel("");
		panel.add(lbl_c1, "cell 0 0");
		
		lbl_c2 = new JLabel("");
		panel.add(lbl_c2, "cell 1 0");
		
		Card2Icon c2i = new Card2Icon();
		lbl_c1.setIcon(c2i.getIcon(card1));
		lbl_c2.setIcon(c2i.getIcon(card2));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if( src == btnBet ){
			synchronized(this){
				player.bet(slider.getValue());
				notify();
				this.dispose();
			}			
		} else if ( src == btnCall ){
			synchronized(this){
				player.bet( toCall );
				notify();
				this.dispose();
			}
		} else if ( src == btnFold ){
			synchronized(this){
				player.fold();
				notify();
				this.dispose();
			}			
		}
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		lbl_raise.setText(Integer.toString(slider.getValue()));		
	}

}
