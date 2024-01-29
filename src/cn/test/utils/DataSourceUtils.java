package cn.test.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;


public class  DataSourceUtils {
	private static Properties prop = new Properties();
	private static DruidDataSource dataSource = new DruidDataSource();
	static {

		// 直接传properties文件名称，不带properties后缀
		ResourceBundle resourceBundle = ResourceBundle.getBundle("druid");
		String driver = resourceBundle.getString("jdbc.driverClassName");
		String url = resourceBundle.getString("jdbc.url");
		String username = resourceBundle.getString("jdbc.username");
		String password = resourceBundle.getString("jdbc.password");
		String initialSize = resourceBundle.getString("jdbc.initialSize");
		String maxActive = resourceBundle.getString("jdbc.maxActive");
		String maxWait = resourceBundle.getString("jdbc.maxWait");
// 使用druid连接池获取connection连接

		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(Integer.parseInt(initialSize));
		dataSource.setMaxActive(Integer.parseInt(maxActive));
		dataSource.setMaxWait(Long.parseLong(maxWait));
	}



	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	// 直接可以获取一个连接池
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 获取连接对象
	public static Connection getConnection() throws SQLException {

		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}

	// 开启事务
	public static void startTransaction() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.setAutoCommit(false);
		}
	}

	// 事务回滚
	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}

	// 提交并且 关闭资源及从ThreadLocall中释放
	public static void commitAndRelease() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit(); // 事务提交
			con.close();// 关闭资源
			tl.remove();// 从线程绑定中移除
		}
	}

	// 关闭资源方法
	public static void closeConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

}
