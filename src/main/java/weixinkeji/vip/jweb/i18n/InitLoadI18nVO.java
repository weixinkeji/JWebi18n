package weixinkeji.vip.jweb.i18n;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

public class InitLoadI18nVO {

	public Map<String, I18nVO[]> load(I18nConfig config) throws ServletException, IOException {
		Map<String, I18nVO[]> reuturnMap = new HashMap<String, I18nVO[]>();
		// 国际化配置文件所在的目录
		String gmFilepath = PathTool.getMyProjectPath(config.filePath);
		File f = new File(gmFilepath);
		if (f.isFile() || !f.exists()) {// 如果是文件，或不存在。国际化配置失败
			// this.isFilterURL=false;
			throw new ServletException("配置文件出错，没找到存放国际化文件的目录！异常路径：" + f.getAbsolutePath());
		}
		Map<String, String> tmpMap = new HashMap<String, String>();
		I18nVO[] gmvos;
		PropertiesTool loadPF = new PropertiesTool();
		// 加载所有国际化的属性文件
		for (File fobj : f.listFiles()) {
			loadPF.loadPropertiesToMap(tmpMap, fobj);
			gmvos = mapToGMVO(tmpMap);// 将国际化的文件里的键值对，变成对象（一个对象 一个键值对）数组的方式。
			if (null != gmvos) {
				reuturnMap.put(
						// 放入文件名-国际化的键值对. 注意，把-线移除。因为Servlet技术里，使用时，我们传入的key，是没有-符号的
						fobj.getName().substring(0, fobj.getName().lastIndexOf(".")).replace("-", "")// 配置文件名
						, gmvos// 配置文件里的键值对
				);
				tmpMap.clear();// 清空临时集合，方便下轮循环使用。
			}
		}
		return reuturnMap;
	}

	private I18nVO[] mapToGMVO(Map<String, String> gmap) {
		if (null == gmap || gmap.isEmpty()) {
			return null;
		}
		I18nVO[] gm = new I18nVO[gmap.size()];
		int i = 0;
		for (Map.Entry<String, String> kv : gmap.entrySet()) {
			gm[i++] = new I18nVO(kv.getKey(), kv.getValue());
		}
		return gm;
	}
}
