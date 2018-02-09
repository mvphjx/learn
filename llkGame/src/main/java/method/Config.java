package method;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 配置文件
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Config extends Properties {
	private static final  String configPath= "src\\main\\resources\\config.properties";
	private static Config config;

	private Config()
	{
		InputStream iStream;
		try {
			iStream = new FileInputStream(configPath);
			this.load(iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 得到唯一的配置对象
	 * @return
	 */
	public static Config createConfig() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}


}
