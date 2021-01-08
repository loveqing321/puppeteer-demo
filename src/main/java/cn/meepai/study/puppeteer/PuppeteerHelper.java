package cn.meepai.study.puppeteer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * puppeteer无头浏览器，用于对报表页面进行截图。
 */
public class PuppeteerHelper {

    // 脚本文件
    private String scriptFile;

    // 截图异常提示
    private String errorMsg;

    public PuppeteerHelper(String scriptFile) {
        this.scriptFile = scriptFile;
    }

    /**
     * 打开页面并渲染图片
     *
     * @param url 要打开的地址
     * @param image 输出文件
     * @param args 渲染参数
     */
    public void openAndRender(String url, String image, PuppeteerArgs args) {
        // 构建执行命令
        String cmd = contributeCommand(url, image, args);
        System.out.println("cmd: " + cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            // 启动子进程读取线程，防止堵塞
            this.startProcessReadThread(process);
            // 等待子进程执行完毕
            while (true) {
                try {
                    int code = process.waitFor();
                    System.out.println(code);
                    if (code != 0) {
                        if (errorMsg == null) {
                            errorMsg = "截图失败，errorCode:" + code;
                        }
                    }
                    break;
                } catch (InterruptedException e) {
                    // ignore.
                }
            }
        } catch (IOException e) {
            errorMsg = "截图失败，异常信息:" + e.getMessage();
        }
    }

    /**
     * 构建puppeteer执行命令
     *
     * node test.js http://xxxx local_file puppeteer_args{ viewport_width viewport_height }
     *
     * @param url 请求的页面地址
     * @param file 文件
     * @param args 请求参数
     * @return 命令行
     */
    private String contributeCommand(String url, String file, PuppeteerArgs args) {
        StringBuilder sb = new StringBuilder();
        sb.append("node ");
        sb.append(scriptFile).append(" ");
        sb.append(url).append(" ");
        sb.append(file).append(" ");
        // 截图高度
        sb.append(args.getClipHeight()).append(" ");
        // 缩放比例
        sb.append(args.getZoomFactor()).append(" ");
        // 页面加载超时
        sb.append(args.getOpenTimeout()).append(" ");
        // 图片质量
        sb.append(args.getImageQuality());

        return sb.toString();
    }

    /**
     * 启动子进程读取线程
     *
     * @param process 子进程
     */
    private void startProcessReadThread(Process process) {
        Thread thread = new Thread(new ProcessReadRunnable(process));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 读取进程输入的线程，防止阻塞。
     */
    private class ProcessReadRunnable implements Runnable {

        private final String ERROR_MESSAGE_SUFFIX = "errorMsg:";

        private final Process process;

        public ProcessReadRunnable(Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(ERROR_MESSAGE_SUFFIX)) {
                        PuppeteerHelper.this.errorMsg = line.substring(ERROR_MESSAGE_SUFFIX.length());
                    }
                    System.out.println(line);
                }
            } catch (IOException e) {
                // ignore.
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    // ignore.
                }
            }
        }
    }
}
