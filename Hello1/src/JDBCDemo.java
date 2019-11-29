
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


//�������ݿ�
public class JDBCDemo {
    //mydb�����Լ��������ݿ�
    //user=root ���Լ����ݿ���û���
    //password=wizardfu ���Լ������ݿ������
	//author 
	private static final String URL = "jdbc:mysql://localhost:3306/girls";
	private static final String USERNAME ="root";
	private static final String pWD = "cwq352406";
	
	//�������� ��������汾����һ�� ��һ�»��������
	//�ص� �����汾����һ��
	public static void uqdate() 
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//�����ݿ⽨������
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			//stmt = connection.createStatement();
			
			 
			//�ɽ�����ɾ�ĵĲ���
			//���ӱ�����
			//String sql = "insert into author values(1,'21','cen6')";
			 //Ԥ��������
			 //String sql = "insert into author values(?,?,?)";
			//ɾ������
			//String  sql = "delete from author";
			
			//ALTER TABLE ���� CHANGE ���ֶ��� ���ֶ��� ����������;
			//�޸ı�����ݲ��� ռλ�� һ��һ����
			//pstmt = connection.prepareStatement(sql);//Ԥ��������
			//�Ƚ���Ԥ���� ����
			//����Ԥ��������
			//��������
//			pstmt.setInt(1, 36);
//			pstmt.setString(2, "chen");
//			pstmt.setString(3, "wei");
			Scanner input = new Scanner(System.in);
			System.out.println("�������û���:");
			String name = input.nextLine();
			System.out.println("����������:");
			String pwd = input.nextLine();
			String sql = "select count(*) from author where au_name = ? and nation = ? ";
			pstmt = connection.prepareStatement(sql);//Ԥ��������
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			//��������
			rs= pstmt.executeQuery();
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
//			if(count > 0)
//			{
//				System.out.println("�����ɹ�");
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
				if(stmt!=null)stmt.close();//����.�����������ݿ�
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
			//�׳��쳣 ClassNotFoundException ���෽��ȫ���׳�SQLException�쳣
			Class.forName("com.mysql.jdbc.Driver");
			
			 connection = DriverManager.getConnection(URL, USERNAME, pWD);
			
			stmt = connection.createStatement();
			
			//�ɽ�����ɾ�ĵĲ���
			//���ӱ�����
			//String sql = "select id,nation from author";
			
			//
			//�ַ�����ƴ��
			//String name = "3";
//			String sql = "select * from author where nation like '%"+name+"%'";
			//Ҫ����Ԥ��������
			String sql = "select * from author where nation like ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,"%3%");
			//ɾ������
			//String  sql = "delete from author";
			
			//ALTER TABLE ���� CHANGE ���ֶ��� ���ֶ��� ����������;
			//�޸ı�����ݲ���
			
			//��ѯ ���ؽ����
			//��ѯ���ݿ����
			//
			rs  = pstmt.executeQuery();
//			if(count > 0)
//			{
//				System.out.println("�����ɹ�");
//			}
			while(rs.next())
			{
				int sno = rs.getInt("id");
				String sname = rs.getString("nation");
				
				//�˷������Ƽ���Ϊ�޷�֪���ֶ��� �ֶδ�1��ʼ
//				int sno = rs.getInt(1);//�±��һ��ʼ
//				String sname = rs.getString(3);
				System.out.println(sno+"----"+sname);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Exception �����쳣
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//��˳����ر�˳���෴
			try {
				//���ų���ָ���쳣
				if(rs!=null)rs.close();
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
	   //query();
}
}
