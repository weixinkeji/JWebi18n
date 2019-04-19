package weixinkeji.vip.jweb.i18n;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class InitLocalI18nConfig {

	I18nConfig getMap() {
		// 国际化配置文件所在的目录
		String configFilepath = PathTool.getMyProjectPath("i18n.properties");
		PropertiesTool obj = new PropertiesTool();
		Map<String, String> config = new HashMap<String, String>();
		File file = new File(configFilepath);
		if (!file.exists()) {// 当没有配置文件时，采用默认的配置
			System.out.println("加载默认配置文件......");
			return new I18nConfig("/i18n", ".js,.css,.html", "true", "loadi18nfile");
		}
		obj.loadPropertiesToMap(config, file);
		System.out.println("加载用户自定配置文件......");
		return new I18nConfig(config.get("filePath"), config.get("notIN"), config.get("openAlink"), config.get("sessionKey"));
	}
}

class I18nConfig {
	public final String filePath;
	public final String notIN;
	public final boolean openAlink;
	public final String sessionKey;

	public I18nConfig(String filePath, String notIN, String openAlink, String sessionKey) {
		this.filePath = null == filePath || filePath.isEmpty() ? "/i18n" : filePath;
		this.notIN = null == notIN || notIN.isEmpty() ? ".js,.css,.html" : notIN;
		this.openAlink = null == openAlink || openAlink.isEmpty() ? true : Boolean.parseBoolean(openAlink);
		this.sessionKey = null == sessionKey || sessionKey.isEmpty() ? "loadi18nfile" : sessionKey;
	}
}
