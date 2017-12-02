package llk;
/*
 * 解析bmp
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * @author 
 * 
 */
public class NewBMP {

	private int width;
	private int height;
	private int[] data;

	public NewBMP() {

	}

	public NewBMP(String src) {
		this.read(src);
	}
	public NewBMP(BufferedImage buffImage) {
		this.read(buffImage);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static int b2i(byte[] b, int s) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int temp = b[s + i] & 0xff;
			ret += temp << (8 * i);
		}
		return ret;
	}

	/** * 读取图片文件 * @param src 文件路径 */
	public void read(String src) {
		OutputStream output = null;  
        try {  
            // read bmp  
            BufferedImage img = ImageIO.read(new File(src));  
            int imageType = img.getType();  
            int w = img.getWidth();  
            int h = img.getHeight(); 
            height=h;
            width=w;
            int startX = 0;  
            int startY = 0;  
            int offset = 0;  
            int scansize = w;  
            // rgb的数组  
            data= new int[offset + (h - startY) * scansize  
                    + (w - startX)];  
            img.getRGB(startX, startY, w, h, data, offset, scansize);  
  
              
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
	/** * 读取图片文件 * @param src 用流  缓存  不产生文件 */
	public void read(BufferedImage buffImage) {
		OutputStream output = null;  
        try {  
            // read bmp  
            BufferedImage img = buffImage;  
            int imageType = img.getType();  
            int w = img.getWidth();  
            int h = img.getHeight(); 
            height=h;
            width=w;
            int startX = 0;  
            int startY = 0;  
            int offset = 0;  
            int scansize = w;  
            // rgb的数组  
            data= new int[offset + (h - startY) * scansize  
                    + (w - startX)];  
            img.getRGB(startX, startY, w, h, data, offset, scansize);  
  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (output != null)  
                try {  
                    output.close();  
                } catch (IOException e) {  
                }  
        }  
	}

	/*
	 * 获取颜色  这里可以配置一个阀值  来判断相似  TODO
	 * 自己操纵图片的像素实现图片变灰。图片变灰的通用算法：取出某个像素的r、g、b值，然后重新计算r、g、b值，
	 * 计算公式为r=r*0.3+g*0.59+b*0.11，g=r,b=g，最后将该rgb值重新写回像素。
	 */
	public int getColor(int x, int y) {
        int rgb = data[0 + (y - 0) * width  
                + (x - 0)];  
        Color c = new Color(rgb); 
        c.getRed();
        c.getRed();
        c.getGreen();
        return rgb;
	}

	public void setColor(int x, int y, int v) {
		int lineW = 0;
		switch ((width * 3) % 4) {
		case 0:
			lineW = width * 3;
			break;
		case 1:
			lineW = width * 3 + 3;
			break;
		case 2:
			lineW = width * 3 + 2;
			break;
		case 3:
			lineW = width * 3 + 1;
		}
		int i = 54 + (height - y - 1) * lineW + 3 * x;
		data[i + 2] = (byte) ((v >> 16) & 0xff);
		data[i + 1] = (byte) ((v >> 8) & 0xff);
		data[i] = (byte) (v & 0xff);
	}
	

	// 取矩形颜色数据
	public byte[] getData(int x, int y, int w, int h) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(w * h);
		try {
			for (int i = x; i < x + w; i++) {
				for (int j = y; j < y + h; j++) {
					bos.write(getColor(i, j));
					bos.flush();
				}
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}
