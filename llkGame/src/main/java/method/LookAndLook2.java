package method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import llk.model.NewPoint;
import llk.model.Point;



/**从游戏的角度，判断两个值相同的点，能不能连接起来
 *  如果ab相连  则存在一条线，经过空白且两个端点与ab的连线也经过空白
 *  只要找出这两个点就可以，如果找不到，自然不成立
 */
public class LookAndLook2 implements LookBasicIn{
	private static int[][] v;
	private static Point pp;
	private static int Y=6;
	private static int X=4;
	private static Long count=0L;
	private static int value=0;
	public LookAndLook2(){
		
	}
	public LookAndLook2(int[][] v){
		this.v=v;
		X=v.length;
		Y=v[0].length;
	}
	public  static void main(String [] args){
		 int[][] a = {  { 6, 1, 2, 1, 0, 6}, 
		 				{ 0, 4, 5, 0, 0, 0 },
		 				{ 0, 0, 0, 0, 0, 3 },
		 				{ 3, 2, 5, 0, 0, 4 } };
		int[][] b = { 
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 1, 2, 0, 2, 0, 1 } };
		int [][]bug={{619727040, 290682456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -229447080, 1458774640}, 
				{-81538424, 0, 0, 0, 0, -1146650072, 0, 0, 0, 0, 0, 0, 0, -1982799128, 0, 0, 0, 0, 1502758400}, 
				{0, 0, 0, 0, -1810327624, 63929824, -1300634360, 0, 0, 0, 0, 0, -1685422216, -1537207736, -1434996256, 0, 0, 0, 0}, 
				{0, 0, 0, 1829329952, -1434996256, -229447080, 1859189656, -1982799128, 0, 0, 0, 63929824, -1537207736, 1458774640, 1829329952, -2022859776, 0, 0, 0},
				{0, 0, -1685422216, 63929824, 619727040, -1434996256, 1502758400, 1502758400, 619727040, 0, 1458774640, -1092770648, 1182565728, 619727040, 2061588104, 859826056, -1300634360, 0, 0},
				{0, -81538424, -64969952, 1859189656, 1458774640, 1182565728, 1935668320, 1182565728, 1018163896, -1982799128, -1092770648, -1092770648, -1300634360, -2022859776, 1829329952, 2061588104, -1146650072, 859826056, 0}, 
				{0, 0, 290682456, 2061588104, -229447080, -1982799128, 1859189656, -64969952, -2022859776, 0, -2019347616, 1182565728, 1935668320, 63929824, 1502758400, -2019347616, 1829329952, 0, 0},
				{0, 0, 0, 1859189656, -1146650072, -1092770648, 859826056, -81538424, 0, 0, 0, -2019347616, -1537207736, -1537207736, 859826056, -81538424, 0, 0, 0},
				{0, 0, 0, 0, -1300634360, 290682456, -1434996256, 0, 0, 0, 0, 0, 1935668320, -229447080, -1146650072, 0, 0, 0, 0}, {1018163896, 0, 0, 0, 0, -2022859776, 0, 0, 0, 0, 0, 0, 0, -2019347616, 0, 0, 0, 0, 2061588104}, 
				{-1685422216, 290682456, 0, 0, 0, 0, 0, 0, 0, -1810327624, 0, 0, 0, 0, 0, 0, 0, 1935668320, -1685422216}};

	
	    LookAndLook2 look2=new LookAndLook2(bug);
	    while(true){
			 List<Point> list=look2.search();
			 if(list==null)
				 break;
			
		 }

	}
	/*
	 * 获取可以连线的俩点
	 */
	public List<Point> search(){
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (v[i][j] != 0) {
					Point p1 = new Point(i,j);//第一个点
					for (int ii = 0; ii < X; ii++) {
						for (int jj = 0; jj < Y; jj++) {
							if (v[ii][jj] != 0) {
								Point p2 = new Point(ii,jj);//第二个点
								if(eqValue(p1,p2)&&isRight(p1,p2)){//两个点值相同并且能够联通
									//LookApi.isRight(v, p1, p2);//调试语句
									list.add(p1);
									list.add(p2);
									v[ p1.getX()][p1.getY()]=0;
									v[ p2.getX()][p2.getY()]=0;
									return list;
								}
									
							}
						}
					}
				}

			}
		}
		return null;
	}
	/*
	 * 判断两个点的value是否相等
	 */
	private boolean eqValue(Point p1,Point p2){
		if(!p1.equals(p2)){
			if(v[p1.getX()][p1.getY()]!=0&&v[p2.getX()][p2.getY()]!=0&&v[p1.getX()][p1.getY()]==v[p2.getX()][p2.getY()])
				return true;
		}
		return false;
		
	}
	/*
	 * 判断两个点是否能够连线
	 */
	public boolean isRight(Point p1,Point p2){
		Point p3=null;
		Point p4=null;
		/*
		 * 循环查找在一条直线上的  俩点  p3 p4（可以为一个点）
		 * 分为x相同，y相同
		 * Ps  这里也可以查找所有点的组合，但是效率略低
		 */
		for(int x=0;x<X;x++){
			for(int y1=0;y1<Y;y1++){
				for(int y2=0;y2<=y1;y2++){
				p3=new Point(x,y1);
				p4=new Point(x,y2);
				if(isRight(p1,p2,p3,p4))
					return true;
				}
			}
		}
		for(int y=0;y<Y;y++){
			for(int x1=0;x1<X;x1++){
				for(int x2=0;x2<=x1;x2++){
				p3=new Point(x1,y);
				p4=new Point(x2,y);
				if(isRight(p1,p2,p3,p4))
					return true;
				}
			}
		}
		
		
		return false;
		
	}
	/*
	 * 转化为判断这四个点组成的线，是否联通
	 * 1  p3 p4为空点或者为值为给定值value
	 * 2     判断  p3 p4是否联通
	 * 3     判断(isLine(p1,p3)&&isLine(p2,p4))||(isLine(p2,p3)&&isLine(p1,p4))
	 */
	private  boolean isRight(Point p1,Point p2,Point p3,Point p4){
		value=v[p1.getX()][p1.getY()];
		if(v[p3.getX()][p3.getY()]!=0&&v[p3.getX()][p3.getY()]!=value){
			return false;
		}
		if(v[p4.getX()][p4.getY()]!=0&&v[p4.getX()][p4.getY()]!=value){
			return false;
		}
		if(isLine(p3,p4)){
			if((isLine(p1,p3)&&isLine(p2,p4))||(isLine(p2,p3)&&isLine(p1,p4))){
				return true;
			}
		}
		return false;
		
	}
	/*
	 *判断两个点是否联通
	 *1   判断如果两个点相同   》成立
	 *2 x坐标或者y坐标相同》中间点值为0或者没有点   》成立
	 */
	private boolean isLine(Point p1,Point p2){
		int i=0;int j=0;
		if(p1.getX()==p2.getX()){
			if(p1.getY()!=p2.getY()){
				i=p1.getY();
				j=p2.getY();
				if(i>j){
					i=i+j;
					j=i-j;
					i=i-j;
				}
				for(int y=i+1;y<j;y++){
					count++;
					if(v[p1.getX()][y]!=0)
						return false;
				}
			}
		}else if(p1.getY()==p2.getY()){
			if(p1.getX()!=p2.getX()){
				i=p1.getX();
				j=p2.getX();
				if(i>j){
					i=i+j;
					j=i-j;
					i=i-j;
				}
				for(int x=i+1;x<j;x++){
					count++;
					if(v[x][p1.getY()]!=0)
						return false;
				}
			}
		}else{
			return  false;
		}
		return true;
		
	}
	/*
	 * 此方法用来  打印  当前数组  以及   寻找出来的点    用来调试bug
	 */
	private void getView(Point p1,Point p2){
	    for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if(v[i][j]<10){
					System.out.print("0"+v[i][j]+",");
				}else{
					System.out.print(v[i][j]+",");
				}
			}
			System.out.println();
	    }
	    System.out.println(p1.getX()+","+p1.getY()+"  and "+p2.getX()+","+p2.getY());
	}
	

	
	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
