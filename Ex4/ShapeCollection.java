package Exe.Ex4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Exe.Ex4.geo.Rect2D;

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
		_shapes.remove(i);
		return _shapes.get(i);
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
		
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		
		
		//////////////////////////////////////////
	}
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		//////////add your code below ///////////
		
		
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
