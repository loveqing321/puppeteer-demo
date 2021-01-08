package cn.meepai.study.puppeteer;

import java.net.URL;

/**
 * @author lzx
 * @date 2021/1/8 2:42 PM
 */
public class Test {

    public static void main(String[] args) {
        URL url = Test.class.getClassLoader().getResource("capture.js");
        if (url == null) {
            System.err.println("没有找到截图脚本文件！");
            return;
        }
        String script = url.getFile();
        PuppeteerHelper puppeteer = new PuppeteerHelper(script);

        String address = "http://www.baidu.com";
        String file = "/Users/liuzhenxing/temp/t.png";
        PuppeteerArgs pas = new PuppeteerArgs();
        puppeteer.openAndRender(address, file, pas);
    }
}
