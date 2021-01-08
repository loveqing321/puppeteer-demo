const process = require('process');
const puppeteer = require('puppeteer');

console.log("start")

// 解析参数
const command = process.argv[0];
const script = process.argv[1];
const url = process.argv[2];
const file = process.argv[3];

// 打开浏览器
puppeteer.launch().then(async browser => {
    // 打开页面
    const page = await browser.newPage();
    console.log(url)
    console.log(file)
    await page.goto(url);

    // 当出现某个元素时，表明页面加载完成
    await page.waitForFunction(() => !!document.querySelector("#s_tab"), { timeout: 10 });

    await page.screenshot({
        path: file,
        fullPage: true,
        type: 'png'
    })
    browser.close();
})