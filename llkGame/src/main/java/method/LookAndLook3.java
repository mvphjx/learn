package method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import llk.model.NewPoint;
import llk.model.Point;



/**
 以转角数为基准的广度优先搜索法
　　这种算法参考《编程之美》。
　　这种算法的动机：若能将所有与图片A经过不多于2个转角的路径相连的图片找出来，加入一个集合S中。
    那么判断B与A能否相连只需判断B是否存在于集合S中即可。采用广度优先搜索算法可以方便的实现这一构想。算法的思路如下：
　　(1)定义空集S与T，将A加入集合S
　　(2)找出所有与A能直接相连的点，将其加入集合S
　　(3)找出与集合S中的点能直接相连的点，加入集合T，然后将T中所有元素加入到集合S中，清空集合T
　　(4)找出与集合S中的点能直接相连的点，加入集合T，然后将T中所有元素加入到集合S中
　　(5)若B在集合S中，则A，B可以相连。否则A,B不能相连

PS  上述没有考虑连连看碰壁的情况，多找出了许多路径
附加条件   集合中的点必须是   与A的值相同的点，或者是空点，值为0
 */
public class LookAndLook3 implements LookBasicIn{
	private  int[][] v;
	private  Point pp;
	private  int Y=6;
	private  int X=4;
	private  Long count=0L;
	private  int value=0;
	public LookAndLook3(int[][] v){
		this.v=v;
		X=v.length;
		Y=v[0].length;
	}
	public  static void main(String [] args){
		 int[][] a = {  { 6, 1, 2, 1, 0, 6}, 
		 				{ 0, 4, 5, 0, 0, 0 },
		 				{ 0, 0, 0, 0, 0, 0 },
		 				{ 0, 2, 5, 0, 0, 4 } };
		int[][] b ={{-1537207736, 0, -1300634360, 0, 1935668320, 0, 1182565728, 0, -1146650072, 0, -1537207736, 0, -1434996256, 0, -81538424, 0, -64969952, 0, -1434996256}, {0, -1414082248, 0, 63929824, 0, -1537207736, 0, -1685422216, 0, -2019347616, 0, -1414082248, 0, 2061588104, 0, 2061588104, 0, -81538424, 0}, {-1685422216, 0, -1300634360, 0, -1146650072, 0, -2019347616, 0, -2019347616, 0, 1458774640, 0, -229447080, 0, -1982799128, 0, -1982799128, 0, -1982799128}, {0, -64969952, 0, 1458774640, 0, 1944978824, 0, 1935668320, 0, 63929824, 0, -81538424, 0, -1300634360, 0, 1859189656, 0, -81538424, 0}, {290682456, 0, 1182565728, 0, -1434996256, 0, -229447080, 0, 1458774640, 0, -2022859776, 0, -2022859776, 0, 1502758400, 0, 1829329952, 0, 1944978824}, {0, -1434996256, 0, 2061588104, 0, 1859189656, 0, 1458774640, 0, 0, 0, -2022859776, 0, 859826056, 0, -2046518792, 0, -229447080, 0}, {290682456, 0, 1944978824, 0, -1146650072, 0, 1018163896, 0, -2046518792, 0, 1502758400, 0, 1018163896, 0, 2061588104, 0, 1502758400, 0, 619727040}, {0, -2046518792, 0, 1944978824, 0, 1018163896, 0, 1859189656, 0, 63929824, 0, 1182565728, 0, -2046518792, 0, 63929824, 0, -1088219424, 0}, {-2019347616, 0, -2022859776, 0, 1859189656, 0, 1829329952, 0, -1088219424, 0, 619727040, 0, 290682456, 0, 619727040, 0, -1685422216, 0, -1300634360}, {0, 1829329952, 0, 859826056, 0, 1182565728, 0, -1982799128, 0, -691737688, 0, 1935668320, 0, 859826056, 0, 859826056, 0, -229447080, 0}, {1502758400, 0, 1935668320, 0, -1537207736, 0, 619727040, 0, 1829329952, 0, -1685422216, 0, -691737688, 0, -1146650072, 0, 290682456, 0, 1018163896}};
		
	
	    LookAndLook3 look3=new LookAndLook3(b);
	    while(true){
		 List<Point> list=look3.search();
		 if(list==null)
			 break;
	    }

	}
	public List<Point> search(){
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (v[i][j] != 0) {
					value=v[i][j];
					Point p = new Point(i, j);
					if (getPoint(p)) {
						LookApi.isRight(v, new Point(i,j),new Point(pp.getX(),pp.getY()));
						v[i][j]=0;
						v[ pp.getX()][pp.getY()]=0;
						List<Point> list=new ArrayList<Point>();
						list.add(new Point(i,j));
						list.add(new Point(pp.getX(),pp.getY()));
						pp = null;//清空全局变量，以便下次找到放入
						return list;
					} else {
						//System.out.println("lost:" + i + "," + j + "   "+ count);
					}

				}

			}
		}
		return null;
	}
	/*
	 * 判断一个点查询是否  有匹配点
	 */
	private boolean getPoint(Point p){
		Set<Point> set = new HashSet<>();
		Set<Point> setclone = new HashSet<>();
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
				pp=new Point(point.getX(), point.getY());
				return true;
			}
		}
		return false;
		
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

	
	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
