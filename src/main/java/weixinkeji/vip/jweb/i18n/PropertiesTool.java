package weixinkeji.vip.jweb.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesTool {

	/**
	 * 加载属性文件的 key,value 到用户的Map实例中
	 * 
	 * @param map
	 *            Map实例 要求必须有key。 value的值由此方法填充(根据属性文件对应的key-value)
	 * @param propertiesFilePath
	 *            属性文件的路径
	 */
	void loadPropertiesToMapByMyKey(Map<String, String> map, File file) {
		Properties pt = new Properties();// 实例一个专门处理属性文件的 对象
		InputStream is = null;
		try {
			// 定义一个输入流。用处：加载我们的配置文件
			is = new FileInputStream(file);
			// 把操作输入流的对象，传给 pt的load方法。
			// 它会自动把文件属性文件 加载到内存，并解析成key-value 存放到Map实例中
			pt.load(is);
			String key;// 定义一个字符，用来临时存放key
			for (Map.Entry<String, String> kv : map.entrySet()) {// 循环用户传的map对象
				key = kv.getKey();// 获取用户的key
				// 根据用户的key,到【属性对象pt】中查找这个key相关的value
				// 把属性对象查找的结果，放到用户的map中
//				map.put(key, pt.getProperty(key));
				map.put(toUtf8(kv.getKey().toString()), toUtf8( pt.getProperty(key)));
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在 ！");
		} catch (IOException e) {
			System.out.println("输入流异常！！！");
		} finally {
			if (null != is) {
				try {
					is.close();// 关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void loadPropertiesToMap(Map<String, String> map, File file) {
		Properties pt = new Properties();// 实例一个专门处理属性文件的 对象
		InputStream is = null;
		try {
			// 定义一个输入流。用处：加载我们的配置文件
			is = new FileInputStream(file);
			// 把操作输入流的对象，传给 pt的load方法。
			// 它会自动把文件属性文件 加载到内存，并解析成key-value 存放到Map实例中
			pt.load(is);
			// 把属性值，转移到我们自己的Map集合中
			for (Entry<Object, Object> kv : pt.entrySet()) {
				map.put(toUtf8(kv.getKey().toString()), toUtf8(kv.getValue().toString()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在 ！");
		} catch (IOException e) {
			System.out.println("输入流异常！！！");
		} finally {
			if (null != is) {
				try {
					is.close();// 关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	Map<String, String> loadPropertiesToMap(File file) {
		Properties pt = new Properties();// 实例一个专门处理属性文件的 对象
		InputStream is = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			// 定义一个输入流。用处：加载我们的配置文件
			is = new FileInputStream(file);
			// 把操作输入流的对象，传给 pt的load方法。
			// 它会自动把文件属性文件 加载到内存，并解析成key-value 存放到Map实例中
			pt.load(is);
			// 把属性值，转移到我们自己的Map集合中
			for (Entry<Object, Object> kv : pt.entrySet()) {
				map.put(toUtf8(kv.getKey().toString()), toUtf8(kv.getValue().toString()));
			}
			return map;
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在 ！");
		} catch (IOException e) {
			System.out.println("输入流异常！！！");
		} finally {
			if (null != is) {
				try {
					is.close();// 关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private String toUtf8(String str) {
		try {
			if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				return new String(str.getBytes("ISO-8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
