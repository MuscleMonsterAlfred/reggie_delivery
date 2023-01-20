# reggie_delivery
瑞吉外卖项目\
小废物Zzy专属项目

jdk: 18\
maven: apache-maven-3.8.6



# study note

1.LambdaQueryWrapper和::的使用\
LambdaQueryWrapper是mybatis plus中的一个条件构造器对象，只是是需要使用Lambda 语法使用 Wrapper\
这个功能是mybatis plus赋予的一个功能\
/**\
  *附加条件构造器LambdaQueryWrapper常用方法 ---这几个肯定够用了\
  */\
 wrapper.eq("实体类::查询字段", "条件值"); //相当于where条件\
 wrapper.between("实体类::查询字段", "区间一", "区间二");//相当于范围内使用的between\
 wrapper.like("实体类::查询字段", "模糊查询的字符"); //模糊查询like\
 wrapper.groupBy("实体类::查询字段");  //相当于group by分组\
 wrapper.in("实体类::查询字段", "包括的值,分割"); //相当于in\
 wrapper.orderByAsc("实体类::查询字段"); //升序\
 wrapper.orderByDesc("实体类::查询字段");//降序\
 wrapper.ge("实体类::查询字段", "要比较的值"); //大于等于\
 wrapper.le("实体类::查询字段", "要比较的值"); //小于等于\
但是通常来说，使用到的时候一般使用的是list形式\
比如\
QueryWrapper<BannerItem> wrapper = new QueryWrapper<>();\
wrapper.lambda().eq(BannerItem::getBannerId, id);\
List<BannerItem> bannerItems = bannerItemMapper.selectList(wrapper);\
其目的主要是用来避免在代码中写入类似于数据库字段名这样的硬编码\
小tips：Lambda是Java8中的新特性
 
2.什么是Session？Session什么时候产生？

Session:在计算机中，尤其是在网络应用中，称为“会话控制”。\
Session 对象存储特定用户会话所需的属性及配置信息。\
这样，当用户在应用程序的 Web 页之间跳转时，存储在 Session 对象中的变量将不会丢失，而是在整个用户会话中一直存在下去。\
当用户请求来自应用程序的 Web 页时，如果该用户还没有会话，则 Web 服务器将自动创建一个 Session 对象。\
当会话过期或被放弃后，服务器将终止该会话\
一般来说session的周期是半个小时，如果过多的session会对服务器造成大的压力
