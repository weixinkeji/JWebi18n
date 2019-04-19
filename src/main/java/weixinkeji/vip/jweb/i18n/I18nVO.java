package weixinkeji.vip.jweb.i18n;

/**
 * 存放国际化配置文件键值对的对象
 * 
 * @author wangchunzi
 *
 */
public class I18nVO {

	public final String key;
	public final String value;

	public I18nVO(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static void toSetRequestAttr(javax.servlet.http.HttpServletRequest req, I18nVO[] vo) {
		for (I18nVO v : vo) {
			req.setAttribute(v.key, v.value);
		}
	}
}
