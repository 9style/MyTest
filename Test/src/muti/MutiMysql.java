package muti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 自动触发运算存储过程功能类
 * 算法：只有当tb_agent_flag表中存在记录，且所有记录的statuss=2时，并且is_calculated=0时，将触发本次运算
 * ，调用p_main()的存储过程
 * 
 * @author xing.liu
 * 
 */
public class MutiMysql {

	private static String ipAndPort = "10.196.9.96:3306";

	private static String dbName = "sco2";

	private static String username = "root";

	private static String password = "root";

	private static String saveName = "grubby";

	private static String projectName = "SCO2";

//	private static final String[] strArray = {
//			"p_muti_active_thread_over_time",
//			"p_muti_bytes_throughput_over_time", "p_muti_error_over_time",
//			"p_muti_hit_per_second", "p_muti_response_code_per_second",
//			"p_muti_response_latencies_over_time",
//			"p_muti_response_time_distribution",
//			"p_muti_response_time_over_time",
//			"p_muti_response_time_vs_thread",
//			"p_muti_transcation_per_second" };
	
	private static final String[] strArray = {
		"p_muti_response_time_over_time" };

	public static void main(String[] args) throws SQLException {
		String procdureName;
		for (String str : strArray) {
			procdureName = "{" + "call " + str + "('" + projectName + "'" + ","
					+ "'" + saveName + "')" + "}";
			startup(procdureName);
		}
	}

	private static void startup(final String storeProcedure) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				long t1=System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName()+"beginTime:"+t1);
				callCalculate(storeProcedure);
				long t2 = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName()+"endTime:"+t2);
				System.out.println(Thread.currentThread().getName()+" 耗时时间="+(t2-t1)/60000+"分钟");
			}
		});
		t.setName(storeProcedure);
		t.start();
		
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"
					+ ipAndPort
					+ "/"
					+ dbName
					+ "?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true";
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(true);
			return conn;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 调用运算的存储过程
	 */
	private static void callCalculate(String storeProcedure) {
		Connection conn = getConnection();
		try {
			CallableStatement call = (CallableStatement) conn
					.prepareCall(storeProcedure);
			call.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
