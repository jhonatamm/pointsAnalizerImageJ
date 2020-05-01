package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ij.IJ;
import model.StereologyPointsFractionModel;
import view.StereologyPointsFractionView;



public class StereologyFractionPointController {

	public StereologyFractionPointController() {

		SingletonFractionPoints.getViewInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SingletonFractionPoints.getViewInstance().setVisible(true);
		SingletonFractionPoints.getViewInstance().getrdbspace().addActionListener(new RdbSelectionSpace());
		SingletonFractionPoints.getViewInstance().getrdbtPoints().addActionListener(new RdbSelectionNumber());
		// SingletonFractionPoints.getViewInstance().gettxtNumber().addFocusListener(new
		// NumberPointsListerner());
		// SingletonFractionPoints.getViewInstance().gettxtSpace().addFocusListener(new
		// SapcePointsListerner());
//		SingletonFractionPoints.getViewInstance().getButtonShow().addActionListener(new ShowPoints());
		SingletonFractionPoints.getViewInstance().getButtonAnalyze().addActionListener(new AnalyzePoints());
		// SingletonFractionPoints.getViewInstance().getCheckBoldBox().addActionListener(new
		// DrawBold());
		SingletonFractionPoints.getViewInstance().getCbxColor().addActionListener(new ChooseColorPoints());
		SingletonFractionPoints.getViewInstance().getButtonHide().addActionListener(new HidePoints());
		SingletonFractionPoints.getViewInstance().addWindowListener(new WindowsOut());
		SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().addActionListener(new HideAnalizedPoints());
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().addActionListener(new InvertPhaseColor());
		SingletonFractionPoints.getViewInstance().getSpnSpacePoints().addChangeListener(new drawSpacePoints());
		SingletonFractionPoints.getViewInstance().getcbxSize().addActionListener(new ChooseColorPoints());
		SingletonFractionPoints.getViewInstance().getCbxTypePoint().addActionListener(new ChooseColorPoints());
		SingletonFractionPoints.getViewInstance().getspnNumberPoints().addChangeListener(new drawNumberPoints());
		SingletonFractionPoints.getViewInstance().getButtonSave().addActionListener( new SaveResults());

	}

}

// intance of class //

class SingletonFractionPoints {
	private static StereologyPointsFractionView estereologyPointsViewinstance;
	private static StereologyPointsFractionModel estereologyPointsModelInstance;

	public static synchronized StereologyPointsFractionView getViewInstance() {
		if (estereologyPointsViewinstance == null) {
			estereologyPointsViewinstance = new StereologyPointsFractionView();
		}
		return estereologyPointsViewinstance;
	}

	public static synchronized StereologyPointsFractionModel getModelInstance() {
		if (estereologyPointsModelInstance == null) {
			estereologyPointsModelInstance = new StereologyPointsFractionModel();
		}
		return estereologyPointsModelInstance;
	}

	public static void destructAll() {
		estereologyPointsViewinstance.dispose();
		estereologyPointsViewinstance = null;
		estereologyPointsModelInstance = null;

	}

	public static void drawPointsBySpace() {

		SingletonFractionPoints.getModelInstance().drawPointsBySpace(
				Integer.parseInt(SingletonFractionPoints.getViewInstance().getSpnSpacePoints().getValue().toString()),
				SingletonFractionPoints.getViewInstance().getCbxColor().getSelectedItem().toString(),
				SingletonFractionPoints.getViewInstance().getCbxTypePoint().getSelectedItem().toString(),
				SingletonFractionPoints.getViewInstance().getcbxSize().getSelectedItem().toString());
		SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonHide().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().setEnabled(false);

	}

	public static void drawPointsByNumber() {
		SingletonFractionPoints.getModelInstance().drawPointsByNumber(
				Integer.parseInt(SingletonFractionPoints.getViewInstance().getspnNumberPoints().getValue().toString()),
				SingletonFractionPoints.getViewInstance().getCbxColor().getSelectedItem().toString(),
				SingletonFractionPoints.getViewInstance().getCbxTypePoint().getSelectedItem().toString(),
				SingletonFractionPoints.getViewInstance().getcbxSize().getSelectedItem().toString());
		SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonHide().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().setEnabled(false);

	}
}
// windows event class//

class WindowsOut implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		
		
		
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		SingletonFractionPoints.destructAll();
		
		SingletonFractionPoints.getModelInstance().onCloseSave();

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}

// change listener //
class drawSpacePoints implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
		SingletonFractionPoints.drawPointsBySpace();

	}

}

class drawNumberPoints implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
		SingletonFractionPoints.drawPointsByNumber();

	}

}

// action listener class //
class SaveResults implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SingletonFractionPoints.getModelInstance().saveResults();
		
	}
	
}

class InvertPhaseColor implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SingletonFractionPoints.getModelInstance().invertPhase();
		//SingletonFractionPoints.getModelInstance().analizePoints();

	}

}

class HideAnalizedPoints implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().getText() == "Hide") {
			SingletonFractionPoints.getModelInstance().hideAnalizedPoints();
			SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().setText("Show");
		} else {
			SingletonFractionPoints.getModelInstance().showAnalizedPoints();
			SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().setText("Hide");

		}
	}

}

class HidePoints implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (SingletonFractionPoints.getViewInstance().getButtonHide().getText() == "Hide") {
			SingletonFractionPoints.getModelInstance().hidePointsOverlay();
			SingletonFractionPoints.getViewInstance().getButtonHide().setText("Show");
		} else {
			SingletonFractionPoints.getModelInstance().showPointsOverLay();
			SingletonFractionPoints.getViewInstance().getButtonHide().setText("Hide");

		}
	}

}

class RdbSelectionSpace implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		SingletonFractionPoints.getViewInstance().getSpnSpacePoints().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getlblSpace().setEnabled(true);
		
		SingletonFractionPoints.getViewInstance().getspnNumberPoints().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getlblNumber().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(false);
		

	}

}

class RdbSelectionNumber implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		SingletonFractionPoints.getViewInstance().getSpnSpacePoints().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getlblSpace().setEnabled(false);
		
		SingletonFractionPoints.getViewInstance().getspnNumberPoints().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getlblNumber().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().setEnabled(false);
		SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(false);

	}

}

class ChooseColorPoints implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SingletonFractionPoints.getModelInstance()
				.setColor(SingletonFractionPoints.getViewInstance().getCbxColor().getSelectedItem().toString());
		if (SingletonFractionPoints.getViewInstance().getrdbspace().isSelected() == true) {
			if (Integer.parseInt(
					SingletonFractionPoints.getViewInstance().getSpnSpacePoints().getValue().toString()) > 0) {
				SingletonFractionPoints.drawPointsBySpace();
				
			}
		}
		else{
			if(Integer.parseInt(SingletonFractionPoints.getViewInstance().getspnNumberPoints().getValue().toString()) > 0){
				SingletonFractionPoints.drawPointsByNumber();
				
			}
			
			
		}
	}

}

/*
 * class NumberPointsListerner implements FocusListener{
 * 
 * @Override public void focusGained(FocusEvent e) {
 * 
 * 
 * }
 * 
 * @Override public void focusLost(FocusEvent e) {
 * SingletonFractionPoints.getViewInstance().gettxtNumber().getText();
 * IJ.showMessage(SingletonFractionPoints.getViewInstance().gettxtNumber().
 * getText());
 * 
 * }
 * 
 * } class SapcePointsListerner implements FocusListener{
 * 
 * @Override public void focusGained(FocusEvent e) {
 * 
 * 
 * }
 * 
 * @Override public void focusLost(FocusEvent e) {
 * 
 * IJ.showMessage(SingletonFractionPoints.getViewInstance().gettxtSpace().
 * getText());
 * SingletonFractionPoints.getModelInstance().setSpaceOfPoint(Integer.parseInt(
 * SingletonFractionPoints.getViewInstance().gettxtSpace().getText()));
 * SingletonFractionPoints.getModelInstance().drawPoints();
 * 
 * }
 * 
 * 
 * }
 */

/*class ShowPoints implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (SingletonFractionPoints.getViewInstance().gettxtNumber().isEnabled() == false) {
			SingletonFractionPoints.getModelInstance().setSpaceOfPoint(
					Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtSpace().getText()));
			SingletonFractionPoints.getModelInstance().removeOverlay();
			// SingletonFractionPoints.getModelInstance().drawPoints();
			SingletonFractionPoints.getViewInstance().getButtonHelp().setEnabled(true);
			SingletonFractionPoints.getViewInstance().getButtonHide().setEnabled(true);
			SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(true);
		} else {
			SingletonFractionPoints.getModelInstance().setNumberOfPoint(
					Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtNumber().getText()));
			SingletonFractionPoints.getModelInstance().removeOverlay();
			SingletonFractionPoints.getModelInstance().generateXYPointsByNumber();
			SingletonFractionPoints.getViewInstance().getButtonHelp().setEnabled(true);
			SingletonFractionPoints.getViewInstance().getButtonHide().setEnabled(true);
			SingletonFractionPoints.getViewInstance().getButtonAnalyze().setEnabled(true);

		}
		// TODO Auto-generated method stub

	}
}
*/
class AnalyzePoints implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SingletonFractionPoints.getModelInstance().analizePoints();
		SingletonFractionPoints.getViewInstance().getButtonSave().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getButtonHideIntercpts().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getbtnInvetPhase().setEnabled(true);
		SingletonFractionPoints.getViewInstance().getButtonHide().setEnabled(true);
		// TODO Auto-generated method stub

	}

}

// class DrawBold implements ActionListener {
//
// @Override
// public void actionPerformed(ActionEvent arg0) {
// if ((SingletonFractionPoints.getViewInstance().getCheckBoldBox().isSelected()
// == true)
// &&
// ((SingletonFractionPoints.getViewInstance().gettxtNumber().getText().isEmpty()
// &&
// SingletonFractionPoints.getViewInstance().gettxtSpace().getText().isEmpty())
// == false)) {
// if (SingletonFractionPoints.getViewInstance().getrdbtPoints().isSelected() ==
// true) {
// SingletonFractionPoints.getModelInstance().setNumberOfPoint(
// Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtNumber().getText()));
// } else {
// SingletonFractionPoints.getModelInstance().setSpaceOfPoint(
// Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtSpace().getText()));
// }
// SingletonFractionPoints.getModelInstance().drawPointsBold();
// } else if
// ((SingletonFractionPoints.getViewInstance().gettxtNumber().getText().isEmpty()
// &&
// SingletonFractionPoints.getViewInstance().gettxtSpace().getText().isEmpty())
// == false) {
// if (SingletonFractionPoints.getViewInstance().getrdbtPoints().isSelected() ==
// true) {
// SingletonFractionPoints.getModelInstance().setNumberOfPoint(
// Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtNumber().getText()));
// } else {
// SingletonFractionPoints.getModelInstance().setSpaceOfPoint(
// Integer.parseInt(SingletonFractionPoints.getViewInstance().gettxtSpace().getText()));
// }
//
// SingletonFractionPoints.getModelInstance().drawPointsNormal();
// }
//
// }
//
// }