package com.coffee.coffeeweb.config.impl;

import cn.dreampie.orm.dialect.Dialect;
import cn.dreampie.orm.dialect.DialectFactory;
import cn.dreampie.orm.provider.DataSourceProvider;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 为 ActiveRecord 模式的 Resty-ORM 添加 Druid 数据源的实现
 * @author xxx
 *
 */
public class DruidDataSourceProvider implements DataSourceProvider {
	private DruidDataSource dataSource;
	private String dialect;
	private String dsName;
	private boolean isShowSql = false;

	/**
	 * 设置dataSource
	 * @param dataSource
	 */
	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 设置dialect
	 * @param dialect
	 */
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	/**
	 * 设置dsName
	 * @param dsName
	 */
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	/**
	 * 设置isShowSql
	 * @param isShowSql
	 */
	public void setShowSql(boolean isShowSql) {
		this.isShowSql = isShowSql;
	}
	
	/**
	 * 获取dataSource
	 * @return dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Dialect getDialect() {
		return DialectFactory.get(dialect);
	}

	@Override
	public String getDsName() {
		return dsName;
	}

	@Override
	public boolean isShowSql() {
		return isShowSql;
	}

	@Override
	public void close() {
		dataSource.close();
	}

}
