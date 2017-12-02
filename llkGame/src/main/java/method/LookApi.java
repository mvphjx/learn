package method;

import java.util.HashMap;

import llk.model.Point;

/**
 * 工具方法
 * 
 * @author Administrator
 * 
 */

public class LookApi {
	/**
	 * 判断两个点是否能相连
	 * 
	 * @return
	 */
	public static boolean isRight(int[][] v, Point p1, Point p2) {
		LookAndLook2 look2 = new LookAndLook2(v);
		boolean bool = look2.isRight(p1, p2);
		if (!bool) {
			System.out
					.println("--------------------wrong!!!------------------------");
		}
		getView(v, p1, p2);
		return bool;
	}

	/*
	 * 此方法用来 打印 当前数组 以及 寻找出来的点 用来调试bug
	 */
	private static void getView(int[][] v, Point p1, Point p2) {
		int X = v.length;
		int Y = v[0].length;
		for (int j = 0; j < Y; j++) {
			if (j < 10) {
				System.out.print("0" + j + ",");
			} else {
				System.out.print(j + ",");
			}
		}
		System.out.println();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		int value = 1;//为了方便查看   把原来的value值  替换成00,01,02,03...等数字
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (v[i][j] != 0) {
					if (map.get(v[i][j]) == null) {
						String s = String.valueOf(value);
						if (value < 10) {
							s = "0" + s;
						}
						map.put(v[i][j], s);
						value++;
					}
					System.out.print(map.get(v[i][j]) + ",");
				} else {
					System.out.print("00,");
				}

			}
			System.out.println();
		}
		System.out.println(p1.getX() + "," + p1.getY() + "  and " + p2.getX()
				+ "," + p2.getY());
	}

}
