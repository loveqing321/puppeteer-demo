##puppeteer 截图

###1. 安装node
####1.1 windows安装node
访问https://nodejs.org/en/download/下载windows安装包msi，双击安装后检查PATH环境变量是否配置了node.js

####1.2 linux安装node
下载：wget https://nodejs.org/dist/v10.9.0/node-v10.9.0-linux-x64.tar.xz
安装：tar xf  node-v10.9.0-linux-x64.tar.xz 
设置环境变量：
vi ~/.bash_profile
增加PATH=$PATH:.../node-v10.9.0-linux-x64/bin
source ~/.bash_profile

####1.3 Mac安装node
brew install node

###2.使用淘宝镜像
npm install -g cnpm --registry=https://registry.npm.taobao.org

###3.创建node项目环境
进入 ${project}/src/test/resources 目录
执行 cnpm install 

###4.执行Test
