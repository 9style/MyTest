package proc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class Test1 {

	public static void main(String[] args) {
		executeProcedure();
	}

	public static void executeProcedure() {
		try {
			CallableStatement callableStatement = (CallableStatement) getConnection()
					.prepareCall("{call p_AaTest(?)}");
			// 设置输出参数
			callableStatement.registerOutParameter(1, Types.INTEGER);
			// 执行存储过程
			ResultSet resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"
					+ "10.196.11.37:3306"
					+ "/"
					+ "dan2"
					+ "?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true";
			Connection conn = (Connection) DriverManager.getConnection(url,
					"root", "root");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
