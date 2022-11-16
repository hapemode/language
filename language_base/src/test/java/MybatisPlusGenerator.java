import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @Author: hape
 * @Date: 2022/9/29 15:08
 */
public class MybatisPlusGenerator {
    /**
     * 数据库配置
     */
    private static DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://49.235.91.191:3306/japan?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8", "root", "root")
            .dbQuery(new MySqlQuery())// 数据库查询->查字段名称、类型、备注
            .schema("mybatis-plus")// 数据库架构
            .typeConvert(new MySqlTypeConvert())// 数据类型转换器
            .keyWordsHandler(new MySqlKeyWordsHandler())// 数据库关键字处理器
            .build();

    /**
     * 全局配置
     */
    private static GlobalConfig GLOBAL_CONFIG = new GlobalConfig.Builder()
            .fileOverride()// 是否覆盖以生成文件，默认：false
            .outputDir("/com/ppx/user")// 指定输出目录
            .author("hape")// 创建人名称
//            .enableKotlin()// 开启kotlin模式
//            .enableSwagger()// 开启swagger注释
            .dateType(DateType.TIME_PACK)// 时间策略
            .commentDate("yyyy-MM-dd HH:mm:ss")// 注释日期
            .build();

    /**
     * 包配置
     */
    private static PackageConfig PACKAGE_CONFIG = new PackageConfig.Builder()
            .parent("")// 生成地址：outputDir+parent
            .moduleName("")
            .entity("entity")// entity包名
            .service("service")// service报名
            .serviceImpl("service.impl")// service实现类包名
            .mapper("mapper")// mapper包名
            .controller("controller")
            .other("other")// 输出自定义文件时用到的包名
            .pathInfo(Collections.singletonMap(OutputFile.xml, "/com/ppx/user/mapper"))// xml文件输出路径配置信息
            .build();

    /**
     * 模板配置
     */
    private static TemplateConfig TEMPLATE_CONFIG = new TemplateConfig.Builder()
            .disable(TemplateType.ENTITY)
            .entity("/templates/entity.java")
            .service("/templates/service.java")
            .serviceImpl("/templates/serviceImpl.java")
            .mapper("/templates/mapper.java")
            .xml("/templates/mapper.xml")
            .controller("/templates/controller.java")
            .build();

    /**
     * 注入策略
     */
//    private static InjectionConfig INJECTION_CONFIG = new InjectionConfig.Builder()
//            .beforeOutputFile((tableInfo, objectMap) -> {
//                System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
//            })// 输出文件之前消费者
//            .customMap(Collections.singletonMap("test", "baomidou"))//自定义配置 Map 对象
//            .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))//自定义配置模板文件
//            .build();


    /**
     * 策略配置
     */
    private static StrategyConfig STRATEGY_CONFIG = new StrategyConfig.Builder()
            .enableCapitalMode()// 开启大写命名
            .enableSkipView()// 开启跳过视图
            .disableSqlFilter()//禁用 sql 过滤
            .likeTable(new LikeTable("USER"))//模糊表匹配(sql 过滤)	likeTable 与 notLikeTable 只能配置一项
//            .addInclude("t_simple")//增加表匹配(内存过滤)	include 与 exclude 只能配置一项
            .addTablePrefix("t_", "c_")//增加过滤表前缀
//            .addFieldSuffix("_flag")//增加过滤表后缀
            .build();


    @SneakyThrows
    @Test
    public void start() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(STRATEGY_CONFIG)
                .global(GLOBAL_CONFIG)
                .packageInfo(PACKAGE_CONFIG)
                .template(TEMPLATE_CONFIG)
                .execute();
    }
}
