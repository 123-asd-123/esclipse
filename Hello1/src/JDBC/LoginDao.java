package JDBC;

import java.sql.*;

public class LoginDao {
	public int login(String name, String pwd)//1�����¼�ɹ�  0�ǵ�½ʧ�� -1ϵͳ�쳣
	{
		//Ҫ���� ֱ�Ӹ��Ƶ�libĿ¼�� ���е���
				//����:���ǽ�java�е�JDBC���� ���Ƶ�JSP�е�<%...��
				String URL = "jdbc:mysql://localhost:3306/girls";
				String USERNAME ="root";
			 	String pWD = "cwq352406";
				//�������ݿ� Ƕ��java���� 
				//JSP������html����Ƕ��java����
				//����semt���Ӷ��� �������ᱨ��ָ�����
				ResultSet rs = null;
				Statement stmt = null;
				Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					//�����ݿ⽨������
					 connection = DriverManager.getConnection(URL, USERNAME, pWD);
					
					stmt = connection.createStatement();
					
					String sql = "select count(*) from author where au_name = '"+name+"' and nation = '"+pwd+"' ";
					
					//��������
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
						if(stmt!=null)stmt.close();//����.�����������ݿ�
						if(connection!=null)connection.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
	}
}
