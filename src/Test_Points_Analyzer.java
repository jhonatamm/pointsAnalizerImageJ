
import javax.swing.SwingUtilities;

import controller.StereologyFractionPointController;
import ij.IJ;
import ij.Prefs;
import ij.plugin.PlugIn;

import model.StereologyPointsFractionModel;

public class Test_Points_Analyzer implements PlugIn {

	
	protected StereologyFractionPointController stereologyFractionPointController;
	public void startStereology() {
		
		
		IJ.getImage();
		Prefs.blackBackground = true;
		Prefs.enhancedLineTool = true;
		IJ.setTool("line");
		this.stereologyFractionPointController = new StereologyFractionPointController();
		
	}
	public static void runMacro(String paramsArgs){
		
	
		StereologyPointsFractionModel stereologyPointsModel = new StereologyPointsFractionModel();
		String arrayParams [] = new String[9];
		arrayParams = paramsArgs.split(",");
		stereologyPointsModel.setColor(arrayParams[0]);
		stereologyPointsModel.setPhaseColor(Integer.parseInt(arrayParams[1]));
		stereologyPointsModel.setTypePoint(arrayParams[2]);
		stereologyPointsModel.setSize(arrayParams[3]);
		stereologyPointsModel.setByNumber(Boolean.valueOf(arrayParams[7]));
		stereologyPointsModel.setSpaceOfPoint(Integer.parseInt(arrayParams[4]));
		stereologyPointsModel.setXl(Integer.parseInt(arrayParams[5]));
		stereologyPointsModel.setYl(Integer.parseInt(arrayParams[6]));
		if(Boolean.valueOf(arrayParams[7])== false){
			stereologyPointsModel.drawPointsBySpace(Integer.parseInt(arrayParams[4]), arrayParams[0], arrayParams[2],arrayParams[3]);
		}
		else{
			stereologyPointsModel.drawPointsByNumber(Integer.parseInt(arrayParams[8]), arrayParams[0], arrayParams[2],arrayParams[3]);
		}
		stereologyPointsModel.analizePoints();
	}
		


	@Override
	public void run(String arg0) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable(){ 
	          public void run(){
	        	  startStereology();
	          }
	      });

	}

}
