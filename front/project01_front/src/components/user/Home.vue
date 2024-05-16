<template>
  <div class="container">
    <header>
      <h1 class="title">欢迎来到主页</h1>
      <div class="user-info">
        <p>用户编号：{{ userId }}</p>
        <p>用户名：{{ username }}</p>
      </div>
    </header>

    <div class="carousel">
      <h2 style="text-align: center;">
        以下是今日内容
      </h2>
      <el-carousel trigger="click" height="400px">
        <el-carousel-item v-for="item in imgList" :key="item.id">
          <a href="https://www.eastmoney.com/"><img :src="item.idView" class="carousel-image" alt=""></a>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div class="bigbox">
      <div class="container_box">
        <div class="box">
          <h3>特色服务</h3>
          <div class="serv">
            <div class="service"><a href="http://quote.eastmoney.com/center/"
                style="font-weight: 700; color: black;">行情</a></div>
            <div class="service"><a href="http://quote.eastmoney.com/center/hszs.html">沪深京</a></div>
            <div class="service"><a href="http://quote.eastmoney.com/center/ggsc.html">港股</a></div>
            <div class="service"><a href="http://quote.eastmoney.com/center/mgsc.html">美股</a></div>

          </div>
          <br>
          <div class="serv">
            <div class="service"><a href="http://data.eastmoney.com/center/"
                style="font-weight: 700; color: black;">数据</a></div>
            <div class="service"><a href="http://data.eastmoney.com/zjlx/">资金</a></div>
            <div class="service"><a href="http://data.eastmoney.com/bbsj/">财报</a></div>
            <div class="service"><a href="http://data.eastmoney.com/report/">研报</a></div>
          </div>
          <br>
          <div class="serv">
            <div class="service"><a href="http://fund.eastmoney.com/?spm=100015.lw.4"
                style="font-weight: 700; color: black;">基金</a></div>
            <div class="service"><a href="http://fund.eastmoney.com/data?spm=100015.lw.4">数据</a></div>
            <div class="service"><a href="http://fund.eastmoney.com/favor?spm=100015.lw.4">工具</a></div>
            <div class="service"><a href="https://kuaixun.eastmoney.com/jj.html">资讯</a></div>
          </div>
          <div class="serv">
            <div class="service"><a href="https://jywg.18.cn/Trade/Buy" style="font-weight: 700; color: black;">交易</a>
            </div>
            <div class="service"><a href="https://jywg.18.cn/Trade/Buy">沪深京交易</a></div>
            <div class="service"><a href="https://trade.1234567.com.cn/login?spm=100015.lw.4">基金交易</a></div>
            <div class="service"><a href="http://caifuhao.eastmoney.com/">财富号</a></div>
          </div>
        </div>
        <div class="box">
          <h3>今日要闻</h3>
          <a href="https://finance.eastmoney.com/a/202404233055680844.html"><strong>·</strong>小米:SU7锁单量超7万台 今年目标超10万</a>
          <a href="https://finance.eastmoney.com/a/202404233055745822.html"><strong>·</strong>港股重磅利好瑞银将中国香港股票评级上调至超配</a>
          <a href="https://finance.eastmoney.com/a/202404233055217446.html"><strong>·</strong>涨价潮来了 最高涨幅接近70%
            海运淡季不淡(附股)</a>
          <a href="https://finance.eastmoney.com/a/202404233054731117.html"><strong>·</strong>国内外铝价持续走高
            上市公司积极布局再生铝业务</a>
          <a href="https://finance.eastmoney.com/a/202404223054699364.html"><strong>·</strong>国防军工板块逆市走强
            这些股一季度净利同比增长</a>
          <a href="https://finance.eastmoney.com/a/202404233054745659.html"><strong>·</strong>113家汽车零部件公司披露年报
            八成净利同比增长</a>
          <a href="https://finance.eastmoney.com/a/202404233055229237.html"><strong>·</strong>一季度落实水利建设投资增逾九成
            景气度有望上行</a>
          <a href="https://finance.eastmoney.com/a/202404233055229345.html"><strong>·</strong>华为智能汽车方案新品发布会将召开
            相关企业受益</a>
        </div>

        <div class="box">
          <h3>社区</h3>
          <a href="https://gubatopic.eastmoney.com/topic_v3.html?htid=7666"><strong>·</strong>国防军工底部接近，如何布局？</a>
          <a href="https://caifuhao.eastmoney.com/news/20240423090640646314200"><strong>·</strong>王健林的“白衣骑士” 继续疯狂扫货？</a>
          <a
            href="https://caifuhao.eastmoney.com/news/20240423083145437818340"><strong>·</strong>华为、特斯拉前后夹击小米SU7,雷军该如何应对?</a>
          <a href="https://caifuhao.eastmoney.com/news/20240423093538555714600"><strong>·</strong>业绩“双增” 营收大涨91.83%
            三只松鼠触底反弹？</a>
          <a href="https://gubatopic.eastmoney.com/topic_v3.html?htid=8721"><strong>·</strong>偏苯三酸酐（TMA）要火？</a>
        </div>
        <div class="box">
          <h3>观点</h3>
          <a href="https://finance.eastmoney.com/a/202404233055226024.html"><strong>·</strong>中金公司：美股回调有助于降息交易再度开启</a>
          <a
            href="https://finance.eastmoney.com/a/202404233055234088.html"><strong>·</strong>招商证券:预计2024年全年A股盈利有望逐季改善</a>
          <a href="https://finance.eastmoney.com/a/202404233055225233.html"><strong>·</strong>中信建投:美股正式进入避险模式
            油价剑指100美元</a>
          <a
            href="https://finance.eastmoney.com/a/202404233055232364.html"><strong>·</strong>中金:外部因素及供给出清带来上游资源品行业机遇</a>
          <a href="https://finance.eastmoney.com/a/202404233055267056.html"><strong>·</strong>中信证券:央行管理工具充足
            有能力维持汇率稳定</a>
        </div>
      </div>
      <div class="longbox">
        <h3>指数动态</h3>
        <div class="shares">
          <a href="https://quote.eastmoney.com/unify/r/1.000001">
            <img
              src="//webquotepic.eastmoney.com/GetPic.aspx?nid=1.000001&amp;imageType=RTOPH&amp;token=e1fc716525030b5517f9968fe65eb25d&amp;timespan=23568&amp;wbp2u=|0|0|0|web"
              alt="">
          </a>
          <br>
          <a href="https://quote.eastmoney.com/unify/r/1.000001">上证指数</a>
        </div>

        <div class="shares">
          <a href="https://quote.eastmoney.com/unify/r/0.399001">
            <img
              src="//webquotepic.eastmoney.com/GetPic.aspx?nid=0.399001&amp;imageType=RTOPH&amp;token=e1fc716525030b5517f9968fe65eb25d&amp;timespan=87321&amp;wbp2u=|0|0|0|web"
              alt="">
          </a>
          <br>
          <a href="https://quote.eastmoney.com/unify/r/0.399001">深证成指</a>
        </div>

        <div class="shares">
          <a href="https://quote.eastmoney.com/unify/r/0.399006">
            <img
              src="//webquotepic.eastmoney.com/GetPic.aspx?nid=0.399006&amp;imageType=RTOPH&amp;token=e1fc716525030b5517f9968fe65eb25d&amp;timespan=254344&amp;wbp2u=|0|0|0|web"
              alt="">
          </a>
          <br>
          <a href="https://quote.eastmoney.com/unify/r/0.399006">创业板指</a>
        </div>

        <div class="shares">
          <a href="https://quote.eastmoney.com/unify/r/1.000300">
            <img
              src="//webquotepic.eastmoney.com/GetPic.aspx?nid=1.000300&amp;imageType=RTOPH&amp;token=e1fc716525030b5517f9968fe65eb25d&amp;timespan=52327&amp;wbp2u=|0|0|0|web"
              alt=""> </a>
          <br>
          <a href="https://quote.eastmoney.com/unify/r/1.000300">沪深300</a>
        </div>

      </div>
    </div>

    <div class="content">
      <h3>问答交流</h3>
      <br>
      <hr><br>
      <div class="box">
        <h3>股吧</h3>
        <div class="info">
          <a href="https://finance.eastmoney.com/a/202404233055945484.html">
            <img src="@/assets/discuss/pic01.png" alt="图片已损坏">
            <div class="text3">周期股牛市或将继续，哪些基金经理值得关注</div>
            <div class="text4">随着黄金、煤炭石油、有色金属等价格的不断上涨，市场又开始看元素周期表选股了。</div>
          </a>
        </div>
        <div class="info">
          <a href="https://finance.eastmoney.com/a/202404233055945484.html">
            <img src="@/assets/discuss/pic02.png" alt="图片已损坏">
            <div class="text3">梭哈资源股？260只绩优基金重仓股揭秘</div>
            <div class="text4">随着公募基金2024年一季报披露完毕，绩优基金的制胜法宝也浮出水面。 </div>
          </a>
        </div>
        <div class="info">
          <a href="https://finance.eastmoney.com/a/202404233055945484.html">
            <img src="@/assets/discuss/pic03.png" alt="图片已损坏">
            <div class="text3">48天涨近70% 上半年A股最强板块出现了？今天还在涨</div>
            <div class="text4">4月23日，市场全天震荡分化，沪指调整，创业板指小幅反弹。截至收盘，沪指跌0.74%，深成指跌0.61%，创业板指涨0.15%。</div>
          </a>
        </div>
      </div>
      <div class="box">
        <h3>个人博客</h3>
        <div class="info">
          <a href="https://blog.eastmoney.com/">
            <img src="@/assets/discuss/pic04.png" alt="图片已损坏">
            <div class="text3">大盘跌破3000点，下跌空间重新打开，需谨慎！</div>
            <div class="text4">作者:江山红紫黄</div>
          </a>
        </div>
        <div class="info">
          <a href="https://blog.eastmoney.com/">
            <img src="@/assets/discuss/pic05.png" alt="图片已损坏">
            <div class="text3">罗辑财道：市场回落，找准多单机会！</div>
            <div class="text4">作者:罗辑财道 </div>
          </a>
        </div>
        <div class="info">
          <a href="https://blog.eastmoney.com/">
            <img src="@/assets/discuss/pic06.png" alt="图片已损坏">
            <div class="text3">不要盲目看空，黄金回踩做多</div>
            <div class="text4">作者:愚者898</div>
          </a>
        </div>
      </div>
      <div class="box">
        <h3>斗股</h3>
        <div class="info">
          <a href="https://dougu.eastmoney.com/a/1416407621.html">
            <img src="@/assets/discuss/pic07.png" alt="图片已损坏">
            <div class="text3">贾子枫看好的股票</div>
            <div class="text4">神枪手贾子枫<br>收益率：75%</div>
          </a>
        </div>
        <div class="info">
          <a href="https://dougu.eastmoney.com/a/1411861131.html">
            <img src="@/assets/discuss/pic08.png" alt="图片已损坏">
            <div class="text3">小盘股炒作</div>
            <div class="text4">逆风而上了<br>收益率53%</div>
          </a>
        </div>
        <div class="info">
          <a href="https://dougu.eastmoney.com/a/1411883269.html">
            <img src="@/assets/discuss/pic09.png" alt="图片已损坏">
            <div class="text3">见好就收，股市不缺机会。</div>
            <div class="text4">拉着你不让你出来<br>收益率52%</div>
          </a>
        </div>
      </div>
    </div>

    <div class="footer">
      <ul>
        <li><a href="#">关于我们</a><span> |</span></li>
        <li><a href="#">联系我们</a><span> |</span></li>
        <li><a href="#">意见建议</a><span> |</span></li>
        <li><a href="#">帮助中心</a><span> |</span></li>
        <li><a href="#">使用条款</a></li>
      </ul>
      <p>免责声明：本网站部分内容由用户自行上传，如权利人发现存在误传其作品情形，请及时与本站联系。</p>
    </div>

  </div>
</template>

<script setup>
import { useStore } from 'vuex';

const store = useStore();

const imgList = [
  { id: 0, idView: require('@/assets/RotationImg/1.jpg') },
  { id: 1, idView: require('@/assets/RotationImg/2.jpg') },
  { id: 2, idView: require('@/assets/RotationImg/3.jpg') },
];

const userId = store.getters.getId;
const username = store.getters.getUsername;
</script>

<style scoped>
/* 定义容器样式 */
.container {
    max-width: 1200px; /* 最大宽度为 1200px */
    margin: 0 auto; /* 水平居中 */
}

/* 定义头部样式 */
header {
    position: fixed; /* 固定在页面顶部 */
    top: 0;
    left: 0;
    width: 100%; /* 宽度占满整个页面 */
    background-color: #fff; /* 白色背景 */
    padding: 5px 0; /* 上下内边距为 5px，左右为 0 */
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
    z-index: 999; /* 设置层级为最高，保证在其他内容之上 */
}

/* 定义标题样式 */
.title {
    font-size: 18px; /* 字体大小为 18px */
    color: #333; /* 字体颜色为深灰色 */
    margin: 0; /* 去除标题的外边距 */
    text-align: center; /* 文本居中 */
}

/* 定义用户信息样式 */
.user-info {
    font-size: 12px; /* 字体大小为 12px */
    color: #666; /* 字体颜色为浅灰色 */
    text-align: center; /* 文本居中 */
}

/* 定义用户信息段落样式 */
.user-info p {
    margin: 0; /* 去除段落的外边距 */
    display: inline; /* 行内显示，紧凑排列 */
}

/* 定义轮播样式 */
.carousel {
    margin-bottom: 50px; /* 底部间距为 50px */
}

/* 定义轮播图片样式 */
.carousel-image {
    width: 100%; /* 图片宽度占满容器 */
    height: 500px; /* 图片高度为 500px */
    object-fit: cover; /* 图片铺满容器，保持比例 */
}

/* 定义长方形容器样式 */
.container_box {
    display: flex; /* 使用弹性布局 */
    flex-wrap: wrap; /* 自动换行 */
    width: 66.66%; /* 宽度为父容器的 2/3 */
}

/* 定义盒子样式 */
.box {
    width: calc(50% - 16px); /* 宽度为父容器的一半减去间距 */
    margin: 8px; /* 盒子之间的间距 */
    padding: 10px; /* 盒子内部的填充 */
    border: 1px solid #ccc; /* 边框样式 */
    box-sizing: border-box; /* 盒子宽度包括边框和填充 */
}

/* 定义盒子中链接样式 */
.box a {
    display: block; /* 显示为块级元素 */
    margin-bottom: 10px; /* 底部间距为 10px */
    text-decoration: none; /* 去除下划线 */
    color: inherit; /* 继承父元素的文本颜色 */
    text-align: center; /* 文本居中 */
}

/* 鼠标悬停时链接样式 */
.box a:hover {
    color: purple; /* 链接文本颜色变为紫色 */
    text-decoration: underline; /* 显示下划线 */
}

/* 最后一个链接样式 */
.box a:last-child {
    margin-bottom: 0; /* 最后一个链接不需要底部间距 */
}

/* 标题样式 */
h3 {
    text-align: center; /* 文本居中 */
}

/* 服务样式 */
.service {
    float: left; /* 左浮动 */
    margin-right: 20px; /* 右边距为 20px */
    margin-top: 30px; /* 顶部间距为 30px */
}

/* 服务链接样式 */
.service a {
    color: gray; /* 链接颜色为灰色 */
}

/* 内联块级元素样式 */
.serv {
    display: inline-block; /* 显示为内联块级元素 */
}

/* 长方形盒子样式 */
.longbox {
    text-align: center; /* 文本居中 */
    display: inline-block; /* 显示为内联块级元素 */
    width: calc(36% - 50px); /* 宽度为父容器的 36% 减去 50px */
    margin: 8px; /* 盒子之间的间距 */
    padding: 10px; /* 盒子内部的填充 */
    border: 1px solid #ccc; /* 边框样式 */
    box-sizing: border-box; /* 盒子宽度包括边框和填充 */
}

/* 长方形盒子中图片样式 */
.longbox img {
    display: inline-block; /* 显示为内联块级元素 */
    vertical-align: middle; /* 垂直居中对齐 */
}

/* 长方形盒子中链接样式 */
.longbox a {
    color: gray; /* 链接颜色为灰色 */
}

/* 鼠标悬停时链接样式 */
.longbox a:hover {
    color: purple; /* 链接文本颜色变为紫色 */
    text-decoration: underline; /* 显示下划线 */
}

/* 大盒子样式 */
.bigbox {
    width: 100%; /* 宽度占满父容器 */
    display: flex; /* 使用弹性布局 */
}

/* 分享样式 */
.shares {
    margin-top: 20px; /* 顶部间距为 20px */
}

/* 底部样式 */
.footer {
    margin: 0 auto; /* 居中显示 */
    overflow: hidden; /* 溢出隐藏 */
    width: 100%; /* 宽度占满整个页面 */
    height: 120px; /* 高度为 120px */
    background-color: gainsboro; /* 背景颜色为浅灰色 */
}

/* 底部段落样式 */
.footer p {
    color: rgba(0, 0, 0, 0.864); /* 文本颜色为深灰色 */
    text-align: center; /* 文本居中 */
    margin: 10px; /* 外边距为 10px */
}

/* 底部无序列表样式 */
.footer ul {
    text-align: center; /* 文本居中 */
}

/* 底部列表项样式 */
.footer ul li {
    display: inline-block; /* 显示为内联块级元素 */
}

/* 底部链接样式 */
.footer ul li a {
    margin: 0 auto; /* 居中显示 */
    text-decoration: none; /* 去除下划线 */
    color: rgba(0, 0, 0, 0.864); /* 文本颜色为深灰色 */
}

/* 鼠标悬停时链接样式 */
.footer ul li a:hover {
    color: skyblue; /* 链接文本颜色变为天蓝色 */
}

/* 底部额外样式 */
.footer span {
    color: black; /* 文本颜色为黑色 */
}

/* 内容样式 */
.content .box {
    float: left; /* 左浮动 */
    color: rgba(0, 0, 0, 0.71); /* 文本颜色为深灰色 */
    width: calc(33% - 66px); /* 宽度为父容器的 33% 减去 66px */
    height: 400px; /* 高度为 400px */
    background-color: transparent; /* 背景透明 */
    margin-right: 60px; /* 右边距为 60px */
    border: 1px solid gray; /* 边框样式 */
}

/* 内容样式中标题样式 */
.content .box h3 {
    margin: 20px; /* 外边距为 20px */
}

/* 内容样式中链接样式 */
.content .box a {
    margin-top: 20px; /* 顶部间距为 20px */
}

/* 内容样式中信息样式 */
.content .box .info a {
    color: rgba(0, 0, 0, 0.714); /* 文本颜色为深灰色 */
    text-decoration: none; /* 去除下划线 */
}

/* 鼠标悬停时链接样式 */
.content .box .info a:hover {
    color: skyblue; /* 链接文本颜色变为天蓝色 */
}

/* 内容样式中信息图片样式 */
.content .box .info a img {
    float: left; /* 左浮动 */
}

/* 内容样式中文本样式 */
.content .box .text3,
.content .box .text4 {
    padding-left: 60px; /* 左填充为 60px */
}

/* 内容样式中第四个文本样式 */
.content .box .text4 {
    height: 40px; /* 高度为 40px */
    margin-top: 5px; /* 顶部间距为 5px */
    font-size: 9px; /* 字体大小为 9px */
    color: rgba(128, 128, 128, 0.69); /* 文本颜色为灰色 */
}

/* 内容样式 */
.content {
    overflow: hidden; /* 溢出隐藏 */
    margin-bottom: 50px; /* 底部间距为 50px */
}
</style>
