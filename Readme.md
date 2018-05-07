# View List Demo
based on [JAVA遇见HTML——JSP篇](https://www.imooc.com/learn/166)
## 商品浏览记录的实现
- 实现 DBHelper 类
- 创建实体类
- 创建业务逻辑类 (DAO)
- 所有商品信息显示
- 商品详细信息显示
- 使用 Cookie 实现保存商品浏览记录
## How to build
Configure you data source in `src/main/resources/application.yaml`.

You should have the below table named items 

| id   | name           | city | price | number | picture |
| ---- | -------------- | ---- | ----- | ------ | ------- |
| 1    | 沃特篮球鞋     | 佛山 | 180   | 500    | 001.jpg |
| 2    | 安踏运动鞋     | 福州 | 120   | 800    | 002.jpg |
| 3    | 耐克运动鞋     | 广州 | 500   | 1000   | 003.jpg |
| 4    | 阿迪达斯T血衫  | 上海 | 388   | 600    | 004.jpg |
| 5    | 李宁文化衫     | 广州 | 180   | 900    | 005.jpg |
| 6    | 小米3          | 北京 | 1999  | 3000   | 006.jpg |
| 7    | 小米2S         | 北京 | 1299  | 1000   | 007.jpg |
| 8    | thinkpad笔记本 | 北京 | 6999  | 500    | 008.jpg |
| 9    | dell笔记本     | 北京 | 3999  | 500    | 009.jpg |
| 10   | ipad5          | 北京 | 5999  | 500    | 010.jpg |

## Copyright
&copy;2018 by @Faithful-Mind

&copy;2014 by milanlover