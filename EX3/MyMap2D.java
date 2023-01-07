package Exe.EX3;
/*
 * ID 1: 208762971
 * ID 2: 207859919
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		// TODO Auto-generated method stub
		int minX = Math.min(p1.ix(), p2.ix());
		int minY = Math.min(p1.iy(), p2.iy());
		int maxX = Math.max(p1.ix(), p2.ix());
		int maxY = Math.max(p1.iy(), p2.iy());
		if (Math.abs(p2.ix()-p1.ix())>=Math.abs(p2.iy()-p1.iy())) {
			double[] line = getLine(p1, p2);
			for(int i = minX; i<=maxX; i++) {
				int j = (int)Math.round(i*line[0]+line[1]);
				if (inRange(i, j)) this.setPixel(i, j, v);
			}
		} else {
			if (p2.ix()==p1.ix()) {
				for (int i = minY; i<=maxY; i++) {
					if (inRange(p2.ix(), i)) this.setPixel(p2.ix(), i, v);
				}
			} else {
				double[] line = getLine(p1, p2);
				for(int i = minY; i<=maxY; i++) {
					int j = (int)Math.round((i-line[1])/line[0]);
					if (inRange(j, i)) this.setPixel(j, i, v);
				}
			}
		}

	}
	
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		// TODO Auto-generated method stub
		int minX = Math.min(p1.ix(), p2.ix());
		int minY = Math.min(p1.iy(), p2.iy());
		int maxX = Math.max(p1.ix(), p2.ix());
		int maxY = Math.max(p1.iy(), p2.iy());
		for (int i = minX; i<=maxX; i++) {
			for (int j = minY; j<=maxY; j++) {
				if (inRange(i, j)) this.setPixel(i, j, col);
			}
		}
	}

	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		// TODO Auto-generated method stub
		int minX = (int) Math.round((p.x()-rad));
		int minY = (int) Math.round((p.y()-rad));
		int maxX = (int) Math.round((p.x()+rad));
		int maxY = (int) Math.round((p.y()+rad));
		for (int i = minX; i<=maxX;i++) {
			for (int j = minY; j<=maxY; j++) {
				double dx = i - p.x();
				double dy = j - p.y();
				double dis = Math.sqrt((dx*dx+dy*dy));
				if (inRange(i, j) && dis<=rad) {
					this.setPixel(i, j, col);
				}
			}
		}
		
	}

	@Override
	public int fill(Point2D p, int new_v) {
		// TODO Auto-generated method stub
		return fill(p.ix(), p.iy(), new_v);
		//		
	}
	/*
	 * The general idea of this algorithm is to work from the
	 * starting point like wave. For that we created a queue of points
	 * in a method of "first in first out". We also created a boolean
	 * map and used it to not step on the same point several times and thus
	 * making sure the queue will be empty at some point in time and the
	 * loop will stop. At last for
	 * 
	 * Basic Structure:
	 * 1. Get the first point in the queue and promote the rest inside the queue
	 * 2. If the point is out of range or was already visited or the color
	 *    is not identical to the starting color move on to the next point in
	 *    the queue
	 * 3. Change the cell sign to visited at the boolean map
	 * 4. Paint the point
	 * 5. Increment the number of cells painted by one
	 * 6. Add the cell's neighbours to the queue
	 * 7. Return steps 1-6 until the queue is empty
	 *  
	 */
	@Override
	public int fill(int x, int y, int new_v) {
		// TODO Auto-generated method stub
		int cellsPainted=0;
		int old_v = getPixel(x, y);
		boolean[][] visited = new boolean[getWidth()][getHeight()];
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {x,y});
		while(!que.isEmpty()) {
			int[] cu = que.poll();
			int cuX = cu[0];
			int cuY = cu[1];

			if (inRange(cuX,cuY)==false || visited[cuX][cuY]==true || getPixel(cuX, cuY)!=old_v) {
				continue;
			}
			visited[cuX][cuY]=true;
			this.setPixel(cuX, cuY, new_v);
			cellsPainted++;
			que.add(new int[] {cuX+1, cuY});
			que.add(new int[] {cuX-1, cuY});
			que.add(new int[] {cuX, cuY+1});
			que.add(new int[] {cuX, cuY-1});
		}
		return cellsPainted;
	}
	/*
	 * The main idea of this code is similar to the the fill function. The difference
	 * is that now we want to keep track of the points we are adding to the queue in relation
	 * to the starting point. So instead of points inside the queue we are putting an array
	 * of points inside the queue. The array represents the path we paved up to the current point.
	 * 
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		if (getPixel(p1)!=getPixel(p2)) return null; // If the points are not in the same color there's no path
		if (p1.ix()==p2.ix() && p1.iy()==p2.iy()) { // If the points are identical the path contains only one point
			return new Point2D[] {p1};
		}
		int color = getPixel(p1); // The color criteria for stepping forward in the matrix
		boolean[][] visited = new boolean[getWidth()][getHeight()]; // Creating a matrix for keeping track of already visited cells
		Queue<Point2D[]> path = new LinkedList<Point2D[]>();
		path.add(new Point2D[] {p1});
		while (!path.isEmpty()) {
			Point2D[] lastPath = path.poll();
			Point2D lastPoint2D = lastPath[lastPath.length-1];
			if (visited[lastPoint2D.ix()][lastPoint2D.iy()]) continue;
			if (getPixel(lastPoint2D)!=color) continue;
			visited[lastPoint2D.ix()][lastPoint2D.iy()] = true;
			if (lastPoint2D.ix()==p2.ix()&&lastPoint2D.iy()==p2.iy()) return lastPath; // If we reached the end point end the function
			Point2D[] neighbours = new Point2D[4]; // This block of code creates our neighbours.
			neighbours[0] = new Point2D(lastPoint2D.ix()+1, lastPoint2D.iy());
			neighbours[1] = new Point2D(lastPoint2D.ix()-1, lastPoint2D.iy());
			neighbours[2] = new Point2D(lastPoint2D.ix(), lastPoint2D.iy()+1);
			neighbours[3] = new Point2D(lastPoint2D.ix(), lastPoint2D.iy()-1);
			for (int i=0;i<neighbours.length;i++) { // add the neighbour to the path only if it is in the range
				// and has the same color and hasn't been visited yet
				if (inRange(neighbours[i])){
					Point2D[] pathNew = new Point2D[lastPath.length+1];
					for (int j = 0;j<lastPath.length;j++) {
						pathNew[j] = lastPath[j];
					}
					pathNew[pathNew.length-1] = neighbours[i];
					path.add(pathNew);
				}
			}

		}
		return null;
	}
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		Point2D[] path = shortestPath(p1, p2);
		if (path!=null) return path.length-1;
		return -1;
	}
	/**
	 * Basic structure:
	 * 1. Create matrix with the same dimensions as the old one (aka old generation)
	 * 2. Iterate through it
	 * 3. Check how many live (MEANING with color not white) cells neighbours it has at the old generation
	 * 4. Decide whether to paint it or not according to the rules of GOL
	 * 5. Move on to the next cell
	 * 6. Finally, swap between the old generation matrix and the new one
	 * 
	 * Game of Life's Rules: (taken from wikipedia)
	 * 1. Any live cell with two or three live neighbours survives.
	 * 2. Any dead cell with three live neighbours becomes a live cell.
	 * 3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
	 */
	@Override
	public void nextGenGol() {
		int[][] mapNextGen = new int[getWidth()][getHeight()];
		for(int x = 0;x<mapNextGen.length;x++) {
			for(int y = 0;y<mapNextGen[0].length;y++) {
				mapNextGen[x][y] = WHITE;
			}
		}
		for (int x=0;x<mapNextGen.length;x++) {
			for (int y=0;y<mapNextGen[0].length;y++) {
				int liveNe = numLiveNeigh(x, y);
				if (this.getPixel(x, y)!=WHITE) {
					if (liveNe==2||liveNe==3) mapNextGen[x][y]=BLACK;
				} else {
					if (liveNe==3) mapNextGen[x][y]=BLACK;
				}
			}
		}
		this._map=mapNextGen;
		
	}
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}
	////////Below are the functions I added//////////
	// This function counts how many live (meaning not white) neighbours cells
	// the input cell has
	private int numLiveNeigh(int x, int y) {
		int liveNeigh = 0;
		int[][] neighbours = new int[8][2];
		neighbours[0] = new int[] {x+1, y};
		neighbours[1] = new int[] {x-1, y};
		neighbours[2] = new int[] {x, y+1};
		neighbours[3] = new int[] {x, y-1};
		neighbours[4] = new int[] {x+1, y+1};
		neighbours[5] = new int[] {x+1, y-1};
		neighbours[6] = new int[] {x-1, y+1};
		neighbours[7] = new int[] {x-1, y-1};
		for (int i=0;i<neighbours.length;i++) {
			if (!inRange(neighbours[i][0], neighbours[i][1])) continue; 
			if (this.getPixel(neighbours[i][0], neighbours[i][1])!=WHITE) liveNeigh++;
		}
		return liveNeigh;
	}
	/*
	 * This function gets 2 points and returns an array which
	 * represents their common linear line.
	 * Y = aX+b
	 * The first variable in the array represents 'a'
	 * The second variable in the array represents 'b'
	 */
	private double[] getLine (Point2D p1, Point2D p2) {
		double[] line = new double[2];
		int dy = p2.iy() - p1.iy();
		int dx = p2.ix() - p1.ix();
		double curve = (double) dy / dx;
		double b = p1.iy() - curve*p1.ix();
		line[0] = curve;
		line[1] = b;
		return line;
	}
	private boolean inRange(Point2D p) {
		return (p.ix()>=0 && p.ix()<getWidth() && p.iy()>=0 && p.iy()<getHeight());
	}
	private boolean inRange(int x, int y) {
		return (x>=0 && x<getWidth() && y>=0 && y<getHeight());	
	}
}
