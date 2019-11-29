package JDBC;

import java.sql.*;

public class LoginDao {
	public int login(String name, String pwd)//1代表登录成功  0是登陆失败 -1系统异常
	{
		//要导包 直接复制到lib目录下 进行导包
				//核心:就是讲java中的JDBC代码 复制到JSP中的<%...中
				String URL = "jdbc:mysql://localhost:3306/girls";
				String USERNAME ="root";
			 	String pWD = "cwq352406";
				//访问数据库 嵌套java代码 
				//JSP就是在html里面嵌套java代码
				//创建semt连接对象 不创建会报空指针错误
				ResultSet rs = null;
				Statement stmt = null;
				Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					//与数据库建立连接
					 connection = DriverManager.getConnection(URL, USERNAME, pWD);
					
					stmt = connection.createStatement();
					
					String sql = "select count(*) from author where au_name = '"+name+"' and nation = '"+pwd+"' ";
					
					//处理结果集
					rs= stmt.executeQuery(sql);
					int count = -1;
					if(rs.next())
					{
						count = rs.getInt(1);
					}
					return count;
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
					return -1;
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					return -1;
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return -1;
				}
				finally {
					try {
						if(rs!=null)rs.close();
						if(stmt!=null)stmt.close();//对象.方法操作数据库
						if(connection!=null)connection.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
	}
}
