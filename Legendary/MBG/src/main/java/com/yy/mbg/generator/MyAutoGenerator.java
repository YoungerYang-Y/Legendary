package com.yy.mbg.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Mybatis-Plus 代码自动生成
 *
 * @author yangyang
 * */
@Slf4j
public class MyAutoGenerator {

    /**
     * 输出code的相对项目路径
     */
    private static String url = "/MBG/src/main/java";
    /**
     * 父类包名
     */
    private static String parentPackage = "com.yy.mbg";
    /**
     * 数据库设置
     */
    private static String DBUrl = "jdbc:mysql://localhost:3306/legendary?characterEncoding=UTF-8&serverTimezone=UTC";
    private static String DBDriverName = "com.mysql.cj.jdbc.Driver";
    private static String DBUsername = "root";
    private static String DBPassword = "root";
    private static String DBName = "legendary";

    public static void main(String[] args) {

        String[] tables = getTables();
        AutoGenerator generator = new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = getGlobalConfig();
        generator.setGlobalConfig(gc);

        // 2、设置数据源
        DataSourceConfig ds = getDataSourceConfig();
        generator.setDataSource(ds);

        // 3、设置包
        PackageConfig packageConfig = getPackageConfig();
        generator.setPackageInfo(packageConfig);

        // 4、设置策略
        StrategyConfig strategyConfig = getStrategyConfig(tables);
        generator.setStrategy(strategyConfig);

        // 5、设置不生成Controller
        TemplateConfig templateConfig = new TemplateConfig();
        // 控制不生成 controller
        templateConfig.setController("");
        generator.setTemplate(templateConfig);

        generator.execute();
    }

    /**
     * 全局配置
     */
    public static GlobalConfig getGlobalConfig(){

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + url);
        /**
         * 开发人员
         */
        gc.setAuthor("YangYang");
        /**
         * 是否打开输出目录
         */
        gc.setOpen(false);
        /**
         * 是否覆盖已有文件
         */
        gc.setFileOverride(false);
        /**
         * 是否在xml中添加二级缓存配置
         */
        gc.setEnableCache(true);
        /**
         * 开启 BaseResultMap
         */
        gc.setBaseResultMap(true);
        /**
         * 开启 baseColumnList
         */
        gc.setBaseColumnList(true);
        /**
         * 各层文件名称方式，例如： %sAction 生成 UserAction
         * %s 为占位符
         */
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        /**
         * 指定生成的主键的ID类型
         */
        gc.setIdType(IdType.AUTO);
        /**
         * 时间类型对应策略
         */
        gc.setDateType(DateType.ONLY_DATE);
        /**
         * 开启 swagger2 模式
         */
        gc.setSwagger2(true);

        return gc;
    }

    /**
     * 数据源配置
     */
    public static DataSourceConfig getDataSourceConfig(){

        DataSourceConfig ds = new DataSourceConfig();
        // MySQL
        ds.setUrl(DBUrl);
        ds.setDriverName(DBDriverName);
        ds.setUsername(DBUsername);
        ds.setPassword(DBPassword);
        ds.setDbType(DbType.MYSQL);
        return ds;
    }

    /**
     * 包配置
     */
    public static PackageConfig getPackageConfig(){

        PackageConfig pc = new PackageConfig();
        /**
         * 父包模块名
         */
        pc.setModuleName("domain");
        /**
         * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
         */
        pc.setParent(parentPackage);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        return pc;
    }

    /**
     * 策略设置
     */
    public static StrategyConfig getStrategyConfig(String[] tables){

        StrategyConfig sc = new StrategyConfig();

        /**
         * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
         * 当{@link #enableSqlFilter}为true时，正则表达式无效.
         */
        sc.setInclude(tables);
        /**
         * 数据库表映射到实体的命名策略
         */
        sc.setNaming(NamingStrategy.underline_to_camel);
        /**
         * 数据库表字段映射到实体的命名策略
         * <p>未指定按照 naming 执行</p>
         */
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        /**
         * 【实体】是否为lombok模型（默认 false）<br>
         * <a href="https://projectlombok.org/">document</a>
         */
        sc.setEntityLombokModel(true);
        /**
         * 逻辑删除属性名称
         */
        sc.setLogicDeleteFieldName("deleted");
        // 自动填充
        TableFill gmt_create = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modified);
        /**
         * 表填充字段
         */
        sc.setTableFillList(tableFills);
        /**
         * 乐观锁属性名称
         */
        sc.setVersionFieldName("version");
        /**
         * 生成 <code>@RestController</code> 控制器
         * <pre>
         *      <code>@Controller</code> -> <code>@RestController</code>
         * </pre>
         */
        sc.setRestControllerStyle(true);
        /**
         * 驼峰转连字符
         * <pre>
         *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
         * </pre>
         */
        sc.setControllerMappingHyphenStyle(true);

        return sc;
    }

    public static String[] getTables(){
        List<String> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select table_name from information_schema.tables where table_schema='"+ DBName +"';";
        try {
            //加载驱动程序
            Class.forName(DBDriverName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //连接MySQL数据库时，要写上连接的用户名和密码
            con = DriverManager.getConnection(DBUrl, DBUsername, DBPassword);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            //关闭结果集
            rs.close();
            //关闭操作
            stmt.close();
            //关闭数据库
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String[] tables = list.toArray(new String[0]);

        return tables;
    }
}
