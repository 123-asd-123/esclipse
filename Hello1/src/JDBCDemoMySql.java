
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import java.sql.Connection;

//�������ݿ�
public class JDBCDemoMySql {
    //mydb�����Լ��������ݿ�
    //user=root ���Լ����ݿ���û���
    //password=wizardfu ���Լ������ݿ������
	//author 
	
	private static final String URL = "jdbc:mysql://localhost:3306/girls";
	private static final String USERNAME ="root";
	private static final String pWD = "cwq352406";
	
	//�������� ��������汾����һ�� ��һ�»��������
	public static void uqdate() 
	{
		ResultSet rs = null;
		//�������ݿ�Ķ���
		Statement stmt = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			 //��ò������ݿ�Ķ���
			stmt = connection.createStatement();
			
			//String sql = "insert into author values(1,'wei','qiang')";
			
			//�������ݿ� ��¼
			//�����ݿ��в�ѯ����
			Scanner input = new Scanner(System.in);
			
			//abc ' or 1=1 -- ��ס--����һ��Ҫ�пո�
			//stmt����sqlע��ķ���
			System.out.println("�������û���:");
			String name = input.nextLine();
			System.out.println("����������:");
			String pwd = input.nextLine();
			String sql = "select count(*) from author where au_name = '"+name+"' and nation = '"+pwd+"' ";
			
			
			//�洢���� �� �洢����
			 //������ʽ:
			 //�洢����:(�޷���ֵreturn ��out��������)
			 //{call �洢������(�����б�)}
			 //�洢����:�з���ֵreturn 
			 //{? = call �洢������(�����б�)}
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
			//��¼�ɹ�
			if(count > 0)
			{
				System.out.println("��¼�ɹ�!");
			}
			//��½ʧ��
			else 
			{
				System.out.println("��½ʧ��!");
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
				if(stmt!=null)stmt.close();//����.�����������ݿ�
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
