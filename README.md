# mincreafting
***
我的世界合成表大全
============
此app的文字和图片来源于 
[中文Minecraft Wiki](http://minecraft-zh.gamepedia.com/Minecraft_Wiki)以及网易, 网络和网友的共享<br />  

 
 ***
教程添加说明
-------------
教程文件需要.html格式
需要将教程名称和相对路径添加到下面的代码中
app/src/main/java/com/cuisanzhang/mincreafting/TutorialListData.java

教程html文件中的图片,需要真实的网络地址

mincreafting/app/src/main/assets/html/wiki
为WIKI教程部分

mincreafting/app/src/main/assets/html/mc163
为网易教程部分

mincreafting/app/src/main/assets/html/mcbbs
为mcbbs教程部分

mincreafting/app/src/main/assets/html/wangluojiaocheng
为网络教程部分




 
***
合成物品生物添加说明
-------------
mincreafting/app/src/main/assets/jsons/creating
为合成部分

mincreafting/app/src/main/assets/jsons/item_block
为物品部分

mincreafting/app/src/main/assets/jsons/mobs
为生物部分

添加需要添加到对应的json文件中
格式如下
<pre><code>{
            "file_name": "wuping_jidang",
            "name": "鸡蛋",
            "material": "鸡",
            "use": "用于合成食物,鸡每5-10分钟下蛋",
            "detail": "鸡蛋(Egg)是能用于合成食物类物品或用作可投掷实体的物品.\n\n 省略N字........."
}</code></pre>
格式说明
<pre><code>  file_name 为放在mincreafting/app/src/main/res/drawable/ 下面的图片名称, 不带后缀, 自动识别gif和png
  name 即为实际显示名称
  use  即图片下的简单说明
  material  代表原材料, 点击图片时将使用这个数据搜索数据库, 
     这个原材料长度不能比搜索出来的物品名称长, 可以短,  不符合的部分可以用空格隔开
     换行用 \n
     不能有制表符, 双引号
</code></pre>
json最好网上找个在线工具验证没有错误.

***

合成生物物品图片添加说明
-------------
图片必须放到
mincreafting/app/src/main/res/drawable/
目录下, 可以为gif和png.
该应用使用了glide库播放gif图片



***
版本修改说明
-------------
应用显示版本号在这2个地方改

1: mincreafting/app/src/main/res/values/strings.xml   
    //页面显示的版本

2: app/build.gradle         
<pre><code>        versionCode 16     //应用版本
        versionName "9.4.1"//应用内部标识版本
</code></pre>
***
升级版本需要修改数据库版本号, 每个版本增加1
-------------
mincreafting/app/src/main/java/com/cuisanzhang/mincreafting/MyDatabaseHelper.java
<pre><code>      int DB_VERSION = 15;     //数据库版本号
</code></pre>

        

