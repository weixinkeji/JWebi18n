package weixinkeji.vip.jweb.i18n;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class I18nFilter implements Filter {
	String filterURL[];
	private Map<String, I18nVO[]> gm = null;
	private final static I18nConfig CONFIG;
	static {
		CONFIG = (new InitLocalI18nConfig()).getMap();
	}

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterURL = CONFIG.notIN.trim().replace("，", ",").split(",");
		try {
			gm = new InitLoadI18nVO().load(CONFIG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		javax.servlet.http.HttpServletRequest req = (HttpServletRequest) request;
		String requrl = req.getRequestURI();
		for (String str : filterURL) {
			if (requrl.endsWith(str)) {// 如果在过滤的名单里，则不须要设置国际化参数
				chain.doFilter(request, response);// 继续下一个处理
				return;
			}
		}
		Object param = request.getParameter(CONFIG.sessionKey);
		String country;
		if (CONFIG.openAlink) {
			if (null != param) {// 如果是通过连接强制加参数的方式
				country = param.toString().trim().replace("-", "");
				if (gm.containsKey(country)) {
					req.getSession().setAttribute(CONFIG.sessionKey, country);
				}
			} else if ((param = req.getSession().getAttribute(CONFIG.sessionKey)) != null) { // 检查session有没有用户的选择
				country = param.toString();
			} else {// 采用默认的配置
				country = request.getLocale().getLanguage() + request.getLocale().getCountry();
			}
		} else {
			country = request.getLocale().getLanguage() + request.getLocale().getCountry();
		}
		I18nVO vo[] = gm.get(country);
		if (null != vo) {
			I18nVO.toSetRequestAttr(req, vo);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
