package com.evergrande.springboot.common;

import com.baomidou.kisso.common.util.EnvUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * 
 * 自动生成映射工具类
 * 
 * @author hubin
 *
 */
public class AutoGeneratorHelper {

	/**
	 * <p>
	 * 测试 run 执行
	 * </p>
	 * <p>
	 * 更多使用查看 http://mp.baomidou.com
	 * </p>
	 */
	public static void main(String[] args) {

		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		if (EnvUtil.isLinux()) {
			gc.setOutputDir("/opt/springboot/");
		} else {
			gc.setOutputDir("E:\\generator");
		}
		gc.setFileOverride(true);
		gc.setActiveRecord(false);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("CuiCan");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert());
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("springboot");
		dsc.setPassword("springboot");
		dsc.setUrl("jdbc:mysql://116.62.134.242:3306/springboot?useUnicode=true&characterEncoding=utf8");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix(new String[] { "cl_", "pr_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		// 字段名生成策略
//		strategy.setFieldNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperServiceImplClass("BaseServiceImpl");
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName("springboot");
		pc.setParent("com.baomidou");// 自定义包路径
		pc.setController("controller");// 这里是控制器包名，默认 web
		mpg.setPackageInfo(pc);
		// 执行生成
		mpg.execute();
	}

}
