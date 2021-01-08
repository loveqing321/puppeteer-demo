const puppeteer = require('puppeteer');

// 打开浏览器
puppeteer.launch().then(async browser => {
    // 打开页面
    const page = await browser.newPage();
    page.on("console", msg => console.log(msg.text));

    await page.exposeFunction('convertStr', text => {
        return "converted: " + text;
    })

    // await page.goto("http://www.baidu.com");

    const res = await page.evaluate(async () => {
        const str = "my text";
        const converted = await window.convertStr(str);
        return converted;
    })
    console.log(res);

    await browser.close();
})