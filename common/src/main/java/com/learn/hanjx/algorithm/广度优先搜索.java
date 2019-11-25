package com.learn.hanjx.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
以转角数为基准的广度优先搜索法
　　这种算法参考《编程之美》。
　　这种算法的动机：若能将所有与图片A经过不多于2个转角的路径相连的图片找出来，加入一个集合S中。那么判断B与A能否相连只需判断B是否存在于集合S中即可。采用广度优先搜索算法可以方便的实现这一构想。算法的思路如下：
　　(1)定义空集S与T，将A加入集合S
　　(2)找出所有与A能直接相连的点，将其加入集合S
　　(3)找出与集合S中的点能直接相连的点，加入集合T，然后将T中所有元素加入到集合S中，清空集合T
　　(4)找出与集合S中的点能直接相连的点，加入集合T，然后将T中所有元素加入到集合S中
　　(5)若B在集合S中，则A，B可以相连。否则A,B不能相连
 应用场景连连看：
 PS  上述没有考虑连连看碰壁的情况，多找出了许多路径
附加条件   集合中的点必须是   与A的值相同的点，或者是空点，值为0

 */
public class 广度优先搜索 {
	public static void main(String[] args) {
		 int[][] a = {  { 6, 1, 2, 1, 0, 6 }, 
	 					{ 0, 4, 5, 0, 0, 0 },
	 					{ 0, 0, 0, 0, 0, 0 },
	 					{ 0, 2, 5, 0, 0, 4 } };
		 int[][] b ={{-1537207736, 0, -1300634360, 0, 1935668320, 0, 1182565728, 0, -1146650072, 0, -1537207736, 0, -1434996256, 0, -81538424, 0, -64969952, 0, -1434996256}, {0, -1414082248, 0, 63929824, 0, -1537207736, 0, -1685422216, 0, -2019347616, 0, -1414082248, 0, 2061588104, 0, 2061588104, 0, -81538424, 0}, {-1685422216, 0, -1300634360, 0, -1146650072, 0, -2019347616, 0, -2019347616, 0, 1458774640, 0, -229447080, 0, -1982799128, 0, -1982799128, 0, -1982799128}, {0, -64969952, 0, 1458774640, 0, 1944978824, 0, 1935668320, 0, 63929824, 0, -81538424, 0, -1300634360, 0, 1859189656, 0, -81538424, 0}, {290682456, 0, 1182565728, 0, -1434996256, 0, -229447080, 0, 1458774640, 0, -2022859776, 0, -2022859776, 0, 1502758400, 0, 1829329952, 0, 1944978824}, {0, -1434996256, 0, 2061588104, 0, 1859189656, 0, 1458774640, 0, 0, 0, -2022859776, 0, 859826056, 0, -2046518792, 0, -229447080, 0}, {290682456, 0, 1944978824, 0, -1146650072, 0, 1018163896, 0, -2046518792, 0, 1502758400, 0, 1018163896, 0, 2061588104, 0, 1502758400, 0, 619727040}, {0, -2046518792, 0, 1944978824, 0, 1018163896, 0, 1859189656, 0, 63929824, 0, 1182565728, 0, -2046518792, 0, 63929824, 0, -1088219424, 0}, {-2019347616, 0, -2022859776, 0, 1859189656, 0, 1829329952, 0, -1088219424, 0, 619727040, 0, 290682456, 0, 619727040, 0, -1685422216, 0, -1300634360}, {0, 1829329952, 0, 859826056, 0, 1182565728, 0, -1982799128, 0, -691737688, 0, 1935668320, 0, 859826056, 0, 859826056, 0, -229447080, 0}, {1502758400, 0, 1935668320, 0, -1537207736, 0, 619727040, 0, 1829329952, 0, -1685422216, 0, -691737688, 0, -1146650072, 0, 290682456, 0, 1018163896}};
		 LLKSearchUtil s = new LLKSearchUtil(a);
		 String result = s.sreach().size()+"";
		 System.out.println(result);
	}
}
class LLKSearchUtil{
	//搜索的数组
	private  int[][] v;
	private  int Y=6;
	private  int X=4;
	private  Long count=0L;
	private  int value=0;
	public LLKSearchUtil(int[][] array){
		this.v=array;
		this.X=v.length;
		this.Y=v[0].length;
	}
	public List<Point> sreach(){
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (v[i][j] != 0) {
					value=v[i][j];
					Point p = new Point(i, j);
					Point match = getPoint(p);
					if (match!=null) {
						v[i][j]=0;
						v[ match.getX()][match.getY()]=0;
						List<Point> list=new ArrayList<Point>();
						list.add(new Point(i,j));
						list.add(new Point(match.getX(),match.getY()));
						return list;
					} else {
						System.out.println("lost:" + i + "," + j + "   "+ count);
					}
				}
			}
		}
		return null;
	}
	/*
	 * 返回匹配点，如果没有返回空
	 */
	private Point getPoint(Point p){
		Set<Point> set = new  HashSet<Point>();
		Set<Point> setclone = new  HashSet<Point>();
		set.add(p);
		getSetByPoint(p,set);
		for(Point point :set){
			setclone.add(point);
		}
		for(Point point :setclone){
			getSetByPoint(point,set);
		}
		for(Point point :set){
			setclone.add(point);
		}
		for(Point point :setclone){
			getSetByPoint(point,set);
		}
		set.remove(p);
		for(Point point :set){
			if(v[point.getX()][point.getY()]==v[p.getX()][p.getY()]){
				Point pp=new Point(point.getX(), point.getY());
				return pp;
			}
		}
		return null;
		
	}
	/*
	 * 一个方法找出所有与  A点   能直接相连的点
	 * 并放入set中
	 *
	 */
	private Set<Point> getSetByPoint(Point p,Set<Point> set){
		int x=p.getX();
		int y=p.getY();
		if(p.getX()==0&&p.getY()==5){
			x=0;
		}
			
		while(true){
			x--;
			if (x < 0 || x >= X || y < 0 || y >= Y) { // 越界
				break;
			}
			if(v[x][y]==0||v[x][y]==value){
				set.add(new Point(x, y));
			}
			if(v[x][y] !=0){//碰壁
				break;
			}
		}
		x=p.getX();
		y=p.getY();
		while(true){
			x++;
			if (x < 0 || x >= X || y < 0 || y >= Y) { // 越界
				break;
			}
			if(v[x][y]==0||v[x][y]==value){
				set.add(new Point(x, y));
			}
			if(v[x][y] !=0){//碰壁
				break;
			}
		}
		x=p.getX();
		y=p.getY();
		while(true){
			y--;
			if (x < 0 || x >= X || y < 0 || y >= Y) { // 越界
				break;
			}
			if(v[x][y]==0||v[x][y]==value){
				set.add(new Point(x, y));
			}
			if(v[x][y] !=0){//碰壁
				break;
			}

		}
		x=p.getX();
		y=p.getY();
		while(true){
			y++;
			if (x < 0 || x >= X || y < 0 || y >= Y) { // 越界
				break;
			}
			if(v[x][y]==0||v[x][y]==value){
				set.add(new Point(x, y));
			}
			if(v[x][y] !=0){//碰壁
				break;
			}
		}
		return set;
	}
}
class Point{
	private int x;
	private int y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
