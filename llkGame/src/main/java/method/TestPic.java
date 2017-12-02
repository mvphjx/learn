package method;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import llk.BMP;
import llk.Fangkuai;
import llk.NewBMP;
import llk.model.Point;

public class TestPic {
	public static void main(String [] args){
		NewBMP bmp = new NewBMP("C:\\Users\\Administrator\\Desktop\\mine\\qqllk8657470107051357611.bmp");

		// QQ连连看是 11 * 19 矩阵
		//一个方块大小  大约是  25*31
		int[][] n = new int[11][19];
		Set<Fangkuai> set = new HashSet<Fangkuai>();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 19; j++) {
				// 截取一小块 15 *15 的数据
				int x = 17 + j * 31;
				int y = 187 + i * 35;
				x=x+5;
				y=y+5;
					if(i==10&&j==10){
					  i=10;
					}
					if(i==2&&j==14){
						  i=2;
					}
					if(i==8&&j==11){
						  i=8;
					}
				Fangkuai fk = new Fangkuai(bmp.getData(x, y, 10, 10));
				if (bmp.getColor(x, y) != -13611920) {// 这个值是空白区的颜色值
					if(!set.contains(fk)){
						set.add(fk);
					}
					int type=fk.hashCode();
					if (type != 0) {
						n[i][j] = type;
					}
				}

			}
		}
		
		int X=11;int Y=19;
		Map <String,Set<Point>> mapbug=new HashMap <String,Set<Point>>();
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (n[i][j] != 0) {
					int keyint=n[i][j];
					String key=String.valueOf(keyint);
					Set<Point> setbug=mapbug.get(key);
					if(setbug==null){
						setbug=new HashSet();
					}
					setbug.add(new Point(i,j));
					mapbug.put(key, setbug);
				}

			}
		}
		Iterator ite = mapbug.entrySet().iterator();
		
		while(ite.hasNext()){
			Map.Entry<Object, Object> entry = (Entry<Object, Object>) ite.next();
			String key = (String) entry.getKey();//map中的key
			Set<Point> setbug= (Set<Point>) entry.getValue();//上面key对应的value
			if(setbug.size()%2!=0){
				System.out.println(key);
				for(Point p :setbug){
					System.out.println(p);
				}
				System.out.println();
			}
		}
		System.out.println("num:"+set.size());
		
	}
	public static void main2(String[] args) {  
        OutputStream output = null;  
        try {  
            // read bmp  
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\mine\\qqllk615748696737982375.bmp"));  
            int imageType = img.getType();  
            int w = img.getWidth();  
            int h = img.getHeight();  
            int startX = 0;  
            int startY = 0;  
            int offset = 0;  
            int scansize = w;  
            // rgb的数组  
            int[] rgbArray = new int[offset + (h - startY) * scansize  
                    + (w - startX)];  
            img.getRGB(startX, startY, w, h, rgbArray, offset, scansize);  
  
            int x0 = w / 2;  
            int y0 = h / 2;  
            int rgb = rgbArray[offset + (y0 - startY) * scansize  
                    + (x0 - startX)];  
            Color c = new Color(rgb);  
            
            System.out.println("中间像素点的rgb：" + c);  
            // create and save to bmp  
            File out = new File("C:\\Users\\Administrator\\Desktop\\mine\\2.bmp");  
            if (!out.exists())  
                out.createNewFile();  
            output = new FileOutputStream(out);  
            BufferedImage imgOut = new BufferedImage(w, h, imageType);  
            imgOut.setRGB(startX, startY, w, h, rgbArray, offset, scansize);  
            ImageIO.write(imgOut, "bmp", output);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (output != null)  
                try {  
                    output.close();  
                } catch (IOException e) {  
                }  
        }  
    }  
	

}
