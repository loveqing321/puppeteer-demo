package cn.meepai.study.puppeteer;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class PuppeteerArgs implements Serializable {

    // 截图高度，-1表示自适应
    private int clipHeight = -1;

    // 缩放比例，默认是1,一般选择1或2
    private int zoomFactor = 1;

    // 页面加载超时
    private long openTimeout = TimeUnit.MINUTES.toMillis(2);

    // 图片质量，默认100
    private int imageQuality = 100;

    public int getClipHeight() {
        return clipHeight;
    }

    public void setClipHeight(int clipHeight) {
        this.clipHeight = clipHeight;
    }

    public int getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(int zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public long getOpenTimeout() {
        return openTimeout;
    }

    public void setOpenTimeout(long openTimeout) {
        this.openTimeout = openTimeout;
    }

    public int getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(int imageQuality) {
        this.imageQuality = imageQuality;
    }
}
