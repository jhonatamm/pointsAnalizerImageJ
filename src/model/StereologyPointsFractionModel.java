package model;

import java.awt.Color;

import ij.IJ;
import ij.ImagePlus;

import ij.gui.PointRoi;

import ij.io.DirectoryChooser;
import ij.measure.ResultsTable;
import ij.plugin.frame.Recorder;
import ij.plugin.frame.RoiManager;

public class StereologyPointsFractionModel {
	private int numberOfPoints = 0;
	private int spaceOfPoints = 0, xl = 0, yl = 0;
	boolean byNumber = false, analyzed = false, overlay = false;
	protected GeneratePoints generatePoints;
	private Color color = Color.red;
	// private String stringColor = "red";
	private ImagePlus imp;
	int phaseColor = 0;
	private String typePoint;
	private String size, colorname;

	public void setByNumber(boolean byNumber) {
		this.byNumber = byNumber;
	}

	public void setPhaseColor(int phaseColor) {
		this.phaseColor = phaseColor;
	}

	public String getTypePoint() {
		return typePoint;
	}

	public void setTypePoint(String typePoint) {
		this.typePoint = typePoint;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setColor(String color) {
		// IJ.showMessage("chego aqui");
		this.colorname = color;
		// this.color = new Color(0,0,0);
		if (colorname.equals("Blue")) {
			this.color = Color.blue;
			// IJ.showMessage("chego aqui esta no blue"+color+this.color);
		}
		if (colorname == "Red") {
			this.color = Color.red;
		}
		if (colorname.equals("Green")) {
			this.color = Color.green;
		}
		if (colorname.equals("Yellow")) {
			this.color = Color.yellow;
		}
		if (colorname.equals("Magenta")) {
			this.color = Color.magenta;
		}
		if (colorname.equals("Black")) {
			this.color = Color.black;
		}
		if (colorname.equals("Cyan")) {
			this.color = Color.cyan;
		}
		if (colorname.equals("brown")) {
			this.color = new Color(165, 42, 42);
		}
		if (colorname.equals("gold")) {
			this.color = new Color(255, 215, 0);
		}
		if (colorname.equals("Orange")) {
			this.color = Color.orange;
		}
		if (colorname.equals("navy")) {
			this.color = new Color(0, 0, 128);
		}
		if (colorname.equals("purple")) {
			this.color = new Color(128, 0, 128);
		}
		if (colorname.equals("Yellow")) {
			// IJ.showMessage("nao era pra entra aqui");
			this.color = Color.yellow;
		}
	}

	public StereologyPointsFractionModel() {
		generatePoints = new GeneratePoints();

	}

	public void setNumberOfPoint(int number) {
		this.overlay = false;
		this.numberOfPoints = number;
	}

	public void setSpaceOfPoint(int number) {
		this.overlay = false;
		this.spaceOfPoints = number;
	}

	///////////////////////////////////////////////////////////////////////////////////// end
	///////////////////////////////////////////////////////////////////////////////////// sets//////////////////////////////////
	public boolean managerImagem() {
		if ((IJ.getImage().isThreshold() == true)) {
			if (imp == null) {
				imp = IJ.getImage();
			}

			if ((imp != IJ.getImage()) == true) {
				if (this.analyzed == true) {
					if (IJ.showMessageWithCancel("Attention", " You Want to work with new image!?") == true) {
						saveResults();
						removeOverlay();
						// imp.killRoi();
						// imp.flush();
						// imp.close();
						imp = IJ.getImage();
						this.analyzed = false;
						this.generatePoints = new GeneratePoints();
						return true;

					} else {

						return false;

					}
				} else {
					removeOverlay();
					imp = IJ.getImage();
					IJ.showMessage("esta aqui que louco");
					return true;
				}
			} else {
				return true;
			}
		} else {
			IJ.beep();
			IJ.showMessage("The images Must be thresholded using " + "image>Adjust>Threshold.");
			return false;
		}

	}

	public void onCloseSave() {
		if (this.analyzed == true) {
			saveResults();
		} else {
			///////
		}
	}

	/*
	 * public void drawPoints() { if (manager() == true) {
	 * 
	 * generatePoints.generateImageBySpace(this.spaceOfPoints, this.color);
	 * byNumber = false; }
	 * 
	 * }
	 */

	/// IO process //////////////////////////////

	public boolean saveResults() {

		try {
			if (IJ.showMessageWithCancel("Attention", "Click ok to Save?") == true) {
				String directoy;
				DirectoryChooser dc = new DirectoryChooser("Chose directory to Save: save as");
				directoy = dc.getDirectory();
				IJ.saveAs(imp, "PNG", directoy + imp.getShortTitle() + ".png");
				this.generatePoints.getSummary().saveAs(directoy + imp.getShortTitle() + "- SummarPoints.xls");
				this.generatePoints.getResults().saveAs(directoy + imp.getShortTitle() + "- ResultsPoints.xls");
			}
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			// IJ.showMessage(e.toString());
			return false;
		}
	}

	public void removeOverlay() {
		IJ.run("Remove Overlay", "");
		if (this.analyzed == true) {
			generatePoints.deleteOverlayandRm();
		}
	}

	/*
	 * public void drawPointsBold() { generatePoints.setRoiBoldPoints(); if
	 * (byNumber == true) { if (analyzed == true){ generateXYPointsByNumber();
	 * generatePoints.analyzeByNumberPoints(xl, yl, this.color,
	 * this.phaseColor); } else{ generateXYPointsByNumber(); } } else {
	 * if(analyzed == true){
	 * generatePoints.generateImageBySpace(this.spaceOfPoints, this.color);
	 * generatePoints.analyzeImageBySpacePoints(this.spaceOfPoints, this.color,
	 * this.phaseColor);
	 * 
	 * } else{
	 * 
	 * generatePoints.generateImageBySpace(this.spaceOfPoints, this.color); } }
	 * }
	 * 
	 * public void drawPointsNormal() { generatePoints.setRoiNormalPoint(); if
	 * (byNumber == true) { generateXYPointsByNumber(); } else {
	 * generatePoints.generateImageBySpace(this.spaceOfPoints, this.color); } }
	 */
	public void generateXYPointsByNumber() {
		int x = 0;

		int y = 0;
		for (x = this.numberOfPoints; x > 1; x--) {
			for (y = 1; y < this.numberOfPoints; y++) {
				if ((x * y == this.numberOfPoints) && (x >= y)) {
					this.xl = x;
					this.yl = y;
					break;
				}

			}

		}

		// IJ.showMessage("Xlinha " + xl + " Y Linha" + yl);
		// generatePoints.generateImageByPoints(xl, yl, this.color);
		// generatePoints.analyzeByNumberPoints(xl, yl);
		byNumber = true;

	}

	public void invertPhase() {
		if (managerImagem() == true) {
			if (phaseColor == 0) {
				this.phaseColor = 255;
			} else {
				this.phaseColor = 0;
			}
			generatePoints.setAnalyzedby(false);
			if (byNumber == true) {
				IJ.run("Hide Overlay", "");
				generatePoints.analyzeByNumberPoints(xl, yl, this.color, this.phaseColor, this.typePoint, this.size);

				showPointsOverLay();

			} else {
				IJ.run("Hide Overlay", "");
				generatePoints.analyzeImageBySpacePoints(this.spaceOfPoints, this.color, this.phaseColor,
						this.typePoint, this.size);
				showPointsOverLay();
			}
		}

	}

	public void analizePoints() {
		if (managerImagem() == true) {
			if (generatePoints.isAnalyzedby() == false) {
				int typeOfAnal = 0;
				if (byNumber == true) {
					generatePoints.analyzeByNumberPoints(xl, yl, this.color, this.phaseColor, this.typePoint,
							this.size);
					hidePointsOverlay();
					showPointsOverLay();
					typeOfAnal = 1;

				} else {
					generatePoints.analyzeImageBySpacePoints(this.spaceOfPoints, this.color, this.phaseColor,
							this.typePoint, this.size);
					hidePointsOverlay();
					showPointsOverLay();
					// IJ.showMessage("esta aqio" +this.spaceOfPoints
					// +this.color+this.phaseColor+this.typePoint+this.size+"
					// pronto");
					typeOfAnal = 2;
				}
				this.analyzed = true;
				if (Recorder.record) {
					String command = "call('Test_Points_Analyzer.runMacro','" + this.colorname + "," + this.phaseColor
							+ "," + this.typePoint + "," + this.size + "," + this.spaceOfPoints + "," + this.xl + ","
							+ this.yl + "," + this.byNumber + "," + this.numberOfPoints + "');";
					Recorder.recordString(command);
				}
			}
			else{
				IJ.showMessage("Image has already been analyzed,please change an attribute");
			}

		}

	}

	public void setXl(int xl) {
		this.xl = xl;
	}

	public void setYl(int yl) {
		this.yl = yl;
	}

	public void hidePointsOverlay() {
		if (this.imp == IJ.getImage()) {

			if (this.overlay == false) {
				generatePoints.saveOverlay();
				this.overlay = true;
			}
			IJ.run("Hide Overlay", "");
			imp = IJ.getImage();
		}
	}

	public void showPointsOverLay() {
		if (imp == IJ.getImage()) {
			IJ.run("Show Overlay", "");
		}
	}

	public void showAnalizedPoints() {
		if (this.imp == IJ.getImage()) {

			generatePoints.showAnalyzedPoints();
		}
	}

	public void hideAnalizedPoints() {
		if (this.imp == IJ.getImage()) {
			generatePoints.hideAnalyzedPoints();
		}
	}

	public void drawPointsBySpace(int space, String color, String TypePoint, String Size) {
		if (managerImagem() == true) {
			setSpaceOfPoint(space);
			setColor(color);
			setSize(Size);
			setTypePoint(TypePoint);
			IJ.run("Remove Overlay", "");
			generatePoints.setAnalyzedby(false);
			// IJ.showMessage("que doidera"+this.color);
			generatePoints.generateImageBySpace(space, this.color, this.typePoint, this.size);
			this.byNumber = false;
		}

	}

	public void drawPointsByNumber(int number, String color, String TypePoint, String Size) {
		if (managerImagem() == true) {
			setNumberOfPoint(number);
			generateXYPointsByNumber();
			setColor(color);
			setSize(Size);
			setTypePoint(TypePoint);
			generatePoints.setAnalyzedby(false);
			IJ.run("Remove Overlay", "");
			// IJ.showMessage("que doidera"+this.color);
			generatePoints.generateImageByPoints(this.xl, this.yl, this.color, this.typePoint, this.size);
		}

	}

}

/// ------- class work--------//

class GeneratePoints {
	private RoiManager roimanager, rm;

	private int[] points;
	private ImagePlus imp = IJ.getImage();
	private int height = imp.getHeight();
	private int width = imp.getWidth();
	private ResultsTable results, summary;
	boolean analyzed = false, analyzedby = false;

	public boolean isAnalyzedby() {
		return analyzedby;
	}

	public void setAnalyzedby(boolean analyzedby) {
		this.analyzedby = analyzedby;
	}

	public ResultsTable getSummary() {
		return results;
	}

	public ResultsTable getResults() {
		return summary;
	}

	public void setRoiBoldPoints() {
		IJ.run(imp, "Line Width...", "line=2");
	}

	public void setRoiNormalPoint() {
		IJ.run(imp, "Line Width...", "line=1");
	}

	public void generateImageBySpace(int space, Color color, String typePoint, String size) {
		PointRoi pointroi;
		int pointsize = 3, roitipe = 0, counter = 1;

		if (roimanager == null) {
			roimanager = new RoiManager();
		} else {
			roimanager.runCommand("Show None");
			roimanager.dispose();
			roimanager.close();
			roimanager = new RoiManager();
		}
		if (size == "Large") {
			pointsize = 4;
		}
		if (typePoint == "Crosshair") {
			roitipe = 1;
		}
		// IJ.showMessage("que doidera"+color);
		for (int h = space; h < height; h = h + space) {
			for (int w = space; w < width; w = w + space) {
				points = imp.getPixel(w, h);

				pointroi = (new PointRoi(w, h));
				pointroi.setSize(pointsize);
				PointRoi.setColor(color);
				pointroi.setPointType(roitipe);

				roimanager.addRoi(pointroi);
				roimanager.select(counter - 1);
				roimanager.runCommand("Rename", "point " + Integer.toString(counter));
				counter++;

			}
		}
		roimanager.runCommand("Show All");
		// analyzeImageByPoints(space);
	}

	public void saveOverlay() {
		if (roimanager != null) {
			roimanager.moveRoisToOverlay(imp);
			roimanager.close();
			if (this.analyzed == true) {
				showAnalyzedPoints();
			}
		}
	}

	public void deleteOverlayandRm() {
		rm.runCommand("Show None");
		rm.dispose();
		rm.close();
		this.rm = null;
		// this.roimanager = null;
	}

	public void generateImageByPoints(int xl, int yl, Color color, String typePoint, String size) {
		PointRoi pointroi;
		int pointsize = 3, roitype = 0, counter = 1;
		if (roimanager == null) {
			roimanager = new RoiManager();
		} else {
			roimanager.runCommand("Show None");
			roimanager.dispose();
			roimanager.close();
			roimanager = new RoiManager();
		}
		if (size == "Large") {
			pointsize = 4;
		}
		if (typePoint == "Crosshair") {
			roitype = 1;
		}

		float spaceW, spaceH;
		spaceW = (float) width / (xl + 1);
		spaceH = (float) height / (yl + 1);
		// IJ.showMessage("espacoW" + spaceW + " espacoH" + spaceH);
		// IJ.showMessage("que doidera"+color);
		for (float h = spaceH; h < height - 1; h = h + spaceH) {
			for (float w = spaceW; w < width - 1; w = w + spaceW) {

				points = imp.getPixel((int) w, (int) h);
				pointroi = (new PointRoi(w, h));
				pointroi.setSize(pointsize);
				PointRoi.setColor(color);
				pointroi.setPointType(roitype);

				roimanager.addRoi(pointroi);
				roimanager.select(counter - 1);
				roimanager.runCommand("Rename", "point " + Integer.toString(counter));
				counter++;

			}
		}
		roimanager.runCommand("Show All");
	}

	////////////////////////////////// analyze steps
	////////////////////////////////// ////////////////////////////////////////////////////
	public void analyzeImageBySpacePoints(int space, Color color, int phaseColor, String typePoint, String size) {

		// rm.dispose();
		// rm.close();
		if (this.analyzedby == false) {
			PointRoi pointroi;
			int pointsize = 3, roitype = 0, counter = 1;
			if (size == "Large") {
				pointsize = 4;
			}
			if (typePoint == "Crosshair") {
				roitype = 1;
			}
			roimanager.runCommand("Show None");
			roimanager.dispose();
			roimanager.close();
			if (rm == null) {
				rm = new RoiManager();
			} else {
				rm.runCommand("Show None");
				rm.dispose();
				rm.close();
				rm = new RoiManager();
			}
			this.analyzed = true;
			results = new ResultsTable();
			summary = new ResultsTable();
			Color colors = Color.red;
			if (color == Color.red) {
				colors = Color.green;
			}
			int x = 0, y = 0;
			for (int h = space; h < height; h = h + space) {
				for (int w = space; w < width; w = w + space) {
					points = imp.getPixel(w, h);
					x = x + 1;
					if (points[0] == phaseColor) {
						pointroi = (new PointRoi(w, h));
						pointroi.setSize(pointsize);
						pointroi.setStrokeColor(colors);
						pointroi.setPointType(roitype);

						rm.addRoi(pointroi);
						rm.select(counter - 1);
						rm.runCommand("Rename", "point " + Integer.toString(counter));
						counter++;
						results.incrementCounter();
						results.addValue("  ", "Point ");
						results.addValue("On Interest Phase", "1");
						y = y + 1;

					} else {
						results.incrementCounter();
						results.addValue("  ", "Point ");
						results.addValue("On Interest Phase", "0");
					}
				}
			}
			float result = (float) y / (float) x;
			summary.incrementCounter();
			summary.addValue("Total Points", x);
			summary.addValue("Points On Phase", y);
			summary.addValue("Points Fraction Pp", result);
			rm.runCommand("Show All");
			results.show("Results");
			summary.show("Summary");
			this.analyzedby = true;
		}
		else{
			IJ.showMessage("Image has already been analyzed,please change an attribute");
		}
		// rm = roimanager;

	}

	public void analyzeByNumberPoints(int xl, int yl, Color color, int phaseColor, String typePoint, String size) {
		if (this.analyzedby == false) {
			PointRoi pointroi;
			int pointsize = 3, roitype = 0, counter = 1, x = 0, y = 0;
			if (size == "Large") {
				pointsize = 4;
			}
			if (typePoint == "Crosshair") {
				roitype = 1;
			}
			results = new ResultsTable();
			summary = new ResultsTable();

			roimanager.runCommand("Show None");
			roimanager.dispose();

			roimanager.close();
			if (rm == null) {
				rm = new RoiManager();
			} else {
				rm.runCommand("Show None");
				rm.dispose();
				rm.close();
				rm = new RoiManager();
			}

			float spaceW, spaceH;
			spaceW = (float) width / (xl + 1);
			spaceH = (float) height / (yl + 1);
			Color colors = Color.red;
			if (color == Color.red) {
				colors = Color.green;
			}
			// IJ.showMessage("espacoW" + spaceW + " espacoH" + spaceH);
			for (float h = spaceH; h < height - 1; h = h + spaceH) {
				for (float w = spaceW; w < width - 1; w = w + spaceW) {
					x = x + 1;
					points = imp.getPixel((int) w, (int) h);
					if (points[0] == phaseColor) {
						pointroi = (new PointRoi(w, h));
						pointroi.setSize(pointsize);
						pointroi.setStrokeColor(colors);
						pointroi.setPointType(roitype);

						rm.addRoi(pointroi);
						rm.select(counter - 1);
						rm.runCommand("Rename", "point " + Integer.toString(counter));
						counter++;
						results.incrementCounter();
						results.addValue("  ", "Point ");
						results.addValue("On Interest Phase", "1");
						y = y + 1;

					} else {
						results.incrementCounter();
						results.addValue("  ", "Point ");
						results.addValue("On Interest Phase", "0");
					}
				}
			}
			float result = (float) y / (float) x;
			summary.incrementCounter();
			summary.addValue("Total Points", x);
			summary.addValue("Points On Phase", y);
			summary.addValue("Points Fraction Pp", result);
			rm.runCommand("Show All");
			results.show("Results");
			summary.show("Summary");
			this.analyzed = true;
			this.analyzedby = true;
		}
		else{
			IJ.showMessage("Image has already been analyzed,please change an attribute");
		}
		// rm = roimanager;
	}

	public void showAnalyzedPoints() {
		rm.runCommand("Show All");
		this.analyzed = true;

	}

	public void hideAnalyzedPoints() {
		rm.runCommand("Show None");
		this.analyzed = false;

	}

}
