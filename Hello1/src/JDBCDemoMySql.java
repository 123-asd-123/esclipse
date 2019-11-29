
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import java.sql.Connection;

//操作数据库
public class JDBCDemoMySql {
    //mydb是我自己建的数据库
    //user=root 我自己数据库的用户名
    //password=wizardfu 我自己的数据库的密码
	//author 
	
	private static final String URL = "jdbc:mysql://localhost:3306/girls";
	private static final String USERNAME ="root";
	private static final String pWD = "cwq352406";
	
	//驱动问题 驱动程序版本必须一致 不一致会产生问题
	public static void uqdate() 
	{
		ResultSet rs = null;
		//操作数据库的对象
		Statement stmt = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			 //获得操作数据库的对象
			stmt = connection.createStatement();
			
			//String sql = "insert into author values(1,'wei','qiang')";
			
			//连接数据库 登录
			//在数据库中查询数据
			Scanner input = new Scanner(System.in);
			
			//abc ' or 1=1 -- 记住--后面一定要有空格
			//stmt存在sql注入的风险
			System.out.println("请输入用户名:");
			String name = input.nextLine();
			System.out.println("请输入密码:");
			String pwd = input.nextLine();
			String sql = "select count(*) from author where au_name = '"+name+"' and nation = '"+pwd+"' ";
			
			
			//存储过程 和 存储函数
			 //参数格式:
			 //存储过程:(无返回值return 用out参数代替)
			 //{call 存储过程名(参数列表)}
			 //存储函数:有返回值return 
			 //{? = call 存储函数名(参数列表)}
			 //connection.prepareCall(sql);
//			create or replace procedure addtwo(num1 in number,num2 in number,result out number)
//			as
//			begin 
//				result:=num1+num2;
//			end;
			
			rs = stmt.executeQuery(sql);
			//int count  = stmt.executeUpdate(sql);
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
				if(rs!=null) rs.close();
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
}
}
