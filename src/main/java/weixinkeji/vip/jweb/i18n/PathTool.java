package weixinkeji.vip.jweb.i18n;

import java.io.UnsupportedEncodingException;

/**
 * 项目路径 处理工具
 * @author 汪春滋
 *
 */
public class PathTool {
	
	// 定义一个常量，用来存放我们的项目路径
	private static final String PROJECT_PATH;
	static {
		// 获取项目路径
		String path="";
		try {
			path = java.net.URLDecoder.decode(PathTool.class.getClassLoader().getResource("").getFile(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (path.endsWith("/")||path.endsWith("\\")) {// 如果项目路径以/结束
			path = path.substring(0, path.length() - 1);// 去掉结尾的字符/
		}
		PROJECT_PATH = path;
	}

	/**
	 * 获取项目中，用户指定的路径
	 * 
	 * @param yourPath null "" "/aa/a" "aa/aa"
	 * @return String
	 */
	public static String getMyProjectPath(String yourPath) {
		
		if (null == yourPath || yourPath.isEmpty()) {// 如果没有传入其他路径
			return PROJECT_PATH;// 直接 返回 项目根目录路径
		}
		try {
			yourPath= java.net.URLDecoder.decode(yourPath,"UTF8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("转码出错");
		}
		if (!yourPath.startsWith("/")) {// 如果传入路径不以/开头
			yourPath = "/" + yourPath;// 改成以/开头。因为我们的 PROJECT_PATH ，不以/结尾
		}
		return PROJECT_PATH + yourPath;
		
	}
}
