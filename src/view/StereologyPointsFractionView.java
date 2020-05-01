package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import ij.util.Java2;

import javax.swing.JLabel;

import java.awt.Checkbox;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class StereologyPointsFractionView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup group;
	private JRadioButton rdbspace;
	private JRadioButton rdbtPoints;
	private JLabel lblSpace;
	private JLabel lblNumberOfPoints;
	private JButton btnHelp;
	private JButton btnHide;
	private JButton btnHelpIntercptos ;
	private JButton btnHideIntercepts;
	private JButton btnAnalyze;
	private JButton btnSave;
	private JButton btnInvertPhase;
	private JComboBox<String> CbxColor;
	private JSpinner spnSpacePoints;
	private JComboBox<String> cbxPointype;
	private JComboBox<String> cbxSize;
	private JSpinner spnNumberPoints;
	
	public StereologyPointsFractionView() {
		setResizable(false);
		Java2.setSystemLookAndFeel();
		setTitle("Test Points Analyzer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 49, 446, 257);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Generate Method:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 133, 14);
		panel.add(lblNewLabel);
		
		this.rdbtPoints = new JRadioButton("By Number Of Points");
		rdbtPoints.setSelected(true);
		rdbtPoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtPoints.setBounds(10, 32, 168, 23);
		panel.add(rdbtPoints);
		
		this.lblSpace = new JLabel("Space Between Points:");
		lblSpace.setEnabled(false);
		lblSpace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpace.setBounds(172, 58, 177, 23);
		panel.add(lblSpace);
		
		this.lblNumberOfPoints = new JLabel("Number Of Points:");
		lblNumberOfPoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfPoints.setBounds(198, 32, 177, 23);
		panel.add(lblNumberOfPoints);
		
		//this.btnHelp = new JButton("Help");
		//btnHelp.setEnabled(false);
		//btnHelp.setBounds(232, 204, 86, 39);
		//panel.add(btnHelp);
		
		this.btnHide = new JButton("Hide");
		btnHide.setBounds(339, 204, 86, 39);
		panel.add(btnHide);
		
		JLabel lblGeneratePoints = new JLabel("Generate Grid of Points");
		lblGeneratePoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneratePoints.setBounds(150, 24, 185, 14);
		contentPane.add(lblGeneratePoints);
		
		JLabel lblGenerateIntercept = new JLabel("Analyze Points");
		lblGenerateIntercept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenerateIntercept.setBounds(174, 317, 134, 14);
		contentPane.add(lblGenerateIntercept);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 342, 446, 111);
		contentPane.add(panel_1);
		
		//this.btnHelpIntercptos = new JButton("Help");
		//btnHelpIntercptos.setEnabled(false);
		//btnHelpIntercptos.setBounds(229, 11, 86, 39);
		//panel_1.add(btnHelpIntercptos);
		
		this.btnHideIntercepts = new JButton("Hide");
		btnHideIntercepts.setEnabled(false);
		btnHideIntercepts.setBounds(338, 11, 86, 39);
		panel_1.add(btnHideIntercepts);
		
		this.btnAnalyze = new JButton("Analyze");
		btnAnalyze.setEnabled(false);
		btnAnalyze.setBounds(151, 61, 132, 39);
		panel_1.add(btnAnalyze);
		
		this.btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setBounds(292, 61, 132, 39);
		panel_1.add(btnSave);
		
		this.btnInvertPhase = new JButton("Invert Phase");
		btnInvertPhase.setEnabled(false);
		btnInvertPhase.setBounds(10, 61, 131, 39);
		panel_1.add(btnInvertPhase);
		this.group = new ButtonGroup();
		group.add(rdbtPoints);
		
		JLabel lblPointType = new JLabel("Point type:");
		lblPointType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPointType.setBounds(10, 136, 177, 23);
		panel.add(lblPointType);
		
		this.cbxPointype = new JComboBox<String>();
		cbxPointype.setModel(new DefaultComboBoxModel<String>(new String[] {"Hybrid", "Crosshair"}));
		cbxPointype.setForeground(Color.BLACK);
		cbxPointype.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxPointype.setBackground(Color.WHITE);
		cbxPointype.setBounds(339, 137, 86, 23);
		panel.add(cbxPointype);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSize.setBounds(10, 169, 177, 23);
		panel.add(lblSize);
		
		this.cbxSize = new JComboBox<String>();
		cbxSize.setModel(new DefaultComboBoxModel<String>(new String[] {"Medium", "Large"}));
		cbxSize.setForeground(Color.BLACK);
		cbxSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxSize.setBackground(Color.WHITE);
		cbxSize.setBounds(339, 170, 86, 23);
		panel.add(cbxSize);
		
		this.spnSpacePoints = new JSpinner();
		spnSpacePoints.setEnabled(false);
		spnSpacePoints.setModel(new SpinnerNumberModel(new Integer(100), new Integer(2), null, new Integer(1)));
		spnSpacePoints.setBounds(339, 61, 86, 20);
		panel.add(spnSpacePoints);
		
		this.spnNumberPoints = new JSpinner();
		spnNumberPoints.setModel(new SpinnerNumberModel(new Integer(20), new Integer(2), null, new Integer(1)));
		spnNumberPoints.setBounds(339, 35, 86, 20);
		panel.add(spnNumberPoints);
		
		this.rdbspace = new JRadioButton("By Space");
		rdbspace.setBounds(10, 58, 98, 23);
		panel.add(rdbspace);
		rdbspace.setSelected(true);
		rdbspace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		group.add(rdbspace);
		
		JLabel lblColorOfPoints = new JLabel("Color:");
		lblColorOfPoints.setBounds(10, 100, 159, 25);
		panel.add(lblColorOfPoints);
		lblColorOfPoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		this.CbxColor = new JComboBox<String>();
		CbxColor.setBounds(339, 102, 86, 23);
		panel.add(CbxColor);
		CbxColor.setModel(new DefaultComboBoxModel<String>(new String[] {"Red", "Green", "Blue", "Magenta", "Cyan", "Yellow", "Orange", "Black"}));
		CbxColor.setForeground(Color.BLACK);
		CbxColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		CbxColor.setBackground(Color.WHITE);
	}
	public JRadioButton getrdbtPoints(){
		return this.rdbtPoints;
	}
	public JRadioButton getrdbspace(){
		return this.rdbspace;
	}

	public JLabel getlblSpace(){
		return this.lblSpace;
	}
	public JLabel getlblNumber(){
		return this.lblNumberOfPoints;
	}

	public JButton getButtonHide(){
		return this.btnHide;
		
	}
	public JButton getButtonHelp(){
		return this.btnHelp;
	}

	public JButton getButtonSave(){
		return this.btnSave;
	}
	public JButton getButtonAnalyze(){
		return this.btnAnalyze;
	}
	public JButton getButtonHideIntercpts(){
		return this.btnHideIntercepts;
	}
	/*public JButton getButtonHelpIntercpts(){
		return this.btnHelpIntercptos;
	}*/
	
	public JComboBox<String> getCbxColor(){
		return this.CbxColor;
	}
	public JButton getbtnInvetPhase(){
		return this.btnInvertPhase;
	}
	
	public JSpinner getSpnSpacePoints(){
		return this.spnSpacePoints;
	}
	
	public JComboBox<String> getcbxSize(){
		return this.cbxSize;
	}
	public JComboBox<String> getCbxTypePoint(){
		return this.cbxPointype;
	}
	public JSpinner getspnNumberPoints(){
		return this.spnNumberPoints;
	}
}
