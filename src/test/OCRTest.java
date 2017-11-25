package test;

import com.webSpider.utils.OCRUtil;

/**
 * ocr验证码识别测试
 * @author pibigstar
 *
 */
public class OCRTest {
	
	public static void main(String[] args) {
		// 网易博客 http://reg.email.163.com/unireg/call.do?cmd=register.verifyCode&v=common/verifycode/vc_en&vt=main_acode&env=55500109560&t=1511350478419
		// CSDN 博客 https://passport.csdn.net/ajax/verifyhandler.ashx
		// OS 开源中国 https://www.oschina.net/action/user/captcha
		OCRUtil.ocr("https://www.oschina.net/action/user/captcha");
	}

}
