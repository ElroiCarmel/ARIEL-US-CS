package Exe.Ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	public ShapeCollection(ArrayList<GUI_Shapeable> shapes) {
		_shapes = shapes;
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		//////////add your code below ///////////
		GUI_Shapeable ans = _shapes.get(i).copy();
		_shapes.remove(i);
		return ans;
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below ///////////
		_shapes.add(i, s);
		
		//////////////////////////////////////////
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
			Ex4.incrementTagCounter();
		}
	}
	@Override
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
		ArrayList<GUI_Shapeable> copyShapes = new ArrayList<GUI_Shapeable>();
		for (GUI_Shapeable s : _shapes) {
			GUI_Shapeable sCopy = s.copy();
			copyShapes.add(sCopy);
		}
		return new ShapeCollection(copyShapes);
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		Collections.sort(_shapes, comp);
		
		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {
		//////////add your code below ///////////
		_shapes.clear();
		Ex4.resetTagCounter();
		
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		try {
			FileWriter fileWrite = new FileWriter(file);
			for(int i = 0; i<_shapes.size(); i++) {
				fileWrite.write((_shapes.get(i).toString()+ "\n")); 
			}	
			fileWrite.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		_shapes.clear();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				_shapes.add(new GUIShape(line));
			}

			// Close the file
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//////////////////////////////////////////
	}
	/*
	 * 1. create 2 lists of all minimum and maximum x's
	 * 2. create 2 list of all minimum and maximum y's 
	 * 3. find the minimum and maximum of each list
	 * 4. create 4 points
	 * 5. assign the points the values according to max and min
	 * 6. create a rect2d with the right points
	 * 7. return it
	 */
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		if (_shapes.isEmpty()) return null;
		//////////add your code below ///////////
		List<Double> allMinX = new ArrayList<Double>();
		List<Double> allMaxX = new ArrayList<Double>();
		List<Double> allMinY = new ArrayList<Double>();
		List<Double> allMaxY = new ArrayList<Double>();
		for (GUI_Shapeable g : _shapes) {
			GeoShapeable gs = g.getShape();
			if (gs instanceof Circle2D) {
				Circle2D c = (Circle2D) gs;
				allMinX.add(c.getMinX());
				allMinY.add(c.getMinY());
				allMaxX.add(c.getMaxX());
				allMaxY.add(c.getMaxY());	
			}
			if (gs instanceof Segment2D) {
				Segment2D s = (Segment2D) gs;
				allMinX.add(s.getMinX());
				allMinY.add(s.getMinY());
				allMaxX.add(s.getMaxX());
				allMaxY.add(s.getMaxY());	
			}
			if (gs instanceof Rect2D) {
				Rect2D r = (Rect2D) gs;
				allMinX.add(r.getMinX());
				allMinY.add(r.getMinY());
				allMaxX.add(r.getMaxX());
				allMaxY.add(r.getMaxY());	
			}
			if (gs instanceof Triangle2D) {
				Triangle2D t = (Triangle2D) gs;
				allMinX.add(t.getMinX());
				allMinY.add(t.getMinY());
				allMaxX.add(t.getMaxX());
				allMaxY.add(t.getMaxY());	
			}
			if (gs instanceof Polygon2D) {
				Polygon2D p = (Polygon2D) gs;
				allMinX.add(p.getMinX());
				allMinY.add(p.getMinY());
				allMaxX.add(p.getMaxX());
				allMaxY.add(p.getMaxY());	
			}	
		}
		// Find min x
		double minX = allMinX.get(0);
		for (int i=0; i<allMinX.size(); i++) {
			if (allMinX.get(i)<minX) minX = allMinX.get(i);
		}
		//Find max x
		double maxX = allMaxX.get(0);
		for (int i=0; i<allMaxX.size(); i++) {
			if (allMaxX.get(i)>maxX) maxX = allMaxX.get(i);
		}
		// Find min y
		double minY = allMinY.get(0);
		for (int i=0; i<allMinY.size(); i++) {
			if (allMinY.get(i)<minY) minY = allMinY.get(i);
		}
		// Find max y
		double maxY = allMaxY.get(0);
		for (int i=0; i<allMaxY.size(); i++) {
			if (allMaxY.get(i)>maxY) maxY = allMaxY.get(i);
		}
		Point2D p1 = new Point2D(minX, minY);
		Point2D p2 = new Point2D(maxX, minY);
		Point2D p3 = new Point2D(maxX, maxY);
		Point2D p4 = new Point2D(minX, maxY);
		ans = new Rect2D(p1, p2, p3, p4);
		
		//////////////////////////////////////////
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
