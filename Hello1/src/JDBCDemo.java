
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


//操作数据库
public class JDBCDemo {
    //mydb是我自己建的数据库
    //user=root 我自己数据库的用户名
    //password=wizardfu 我自己的数据库的密码
	//author 
	private static final String URL = "jdbc:mysql://localhost:3306/girls";
	private static final String USERNAME ="root";
	private static final String pWD = "cwq352406";
	
	//驱动问题 驱动程序版本必须一致 不一致会产生问题
	//重点 驱动版本必须一致
	public static void uqdate() 
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//与数据库建立连接
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			//stmt = connection.createStatement();
			
			 
			//可进行增删改的操作
			//增加表格操作
			//String sql = "insert into author values(1,'21','cen6')";
			 //预编译问题
			 //String sql = "insert into author values(?,?,?)";
			//删除操作
			//String  sql = "delete from author";
			
			//ALTER TABLE 表名 CHANGE 旧字段名 新字段名 新数据类型;
			//修改表格内容操作 占位符 一个一个换
			//pstmt = connection.prepareStatement(sql);//预编译问题
			//先进行预编译 问题
			//进行预编译问题
			//设置内容
//			pstmt.setInt(1, 36);
//			pstmt.setString(2, "chen");
//			pstmt.setString(3, "wei");
			Scanner input = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String name = input.nextLine();
			System.out.println("请输入密码:");
			String pwd = input.nextLine();
			String sql = "select count(*) from author where au_name = ? and nation = ? ";
			pstmt = connection.prepareStatement(sql);//预编译问题
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			//处理结果集
			rs= pstmt.executeQuery();
			int count = -1;
			if(rs.next())
			{
				count = rs.getInt(1);
			}
			//登录成功
			if(count > 0)
			{
				System.out.println("登录成功!");
			}
			//登陆失败
			else 
			{
				System.out.println("登陆失败!");
			}
//			if(count > 0)
//			{
//				System.out.println("操作成功");
//			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null)stmt.close();//对象.方法操作数据库
				if(connection!=null)connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void query() {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			//抛出异常 ClassNotFoundException 其余方法全部抛出SQLException异常
			Class.forName("com.mysql.jdbc.Driver");
			
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			stmt = connection.createStatement();
			
			//可进行增删改的操作
			//增加表格操作
			//String sql = "select id,nation from author";
			
			//
			//字符串的拼接
			//String name = "3";
//			String sql = "select * from author where nation like '%"+name+"%'";
			//要进行预编译问题
			String sql = "select * from author where nation like ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,"%3%");
			//删除操作
			//String  sql = "delete from author";
			
			//ALTER TABLE 表名 CHANGE 旧字段名 新字段名 新数据类型;
			//修改表格内容操作
			
			//查询 返回结果集
			//查询数据库操作
			//
			rs  = pstmt.executeQuery();
//			if(count > 0)
//			{
//				System.out.println("操作成功");
//			}
			while(rs.next())
			{
				int sno = rs.getInt("id");
				String sname = rs.getString("nation");
				
				//此方法不推荐因为无法知道字段名 字段从1开始
//				int sno = rs.getInt(1);//下标从一开始
//				String sname = rs.getString(3);
				System.out.println(sno+"----"+sname);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Exception 基类异常
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//打开顺序与关闭顺序相反
			try {
				//先排除空指针异常
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();//对象.方法操作数据库
				if(connection!=null)connection.close();
			 } catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
   public static void main(String[] args) {
	   uqdate();
	   //query();
}
}
