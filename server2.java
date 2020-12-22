package cosmos;

import java.io.*;			// �����
import java.net.*;			// ��Ʈ��ũ �������
import java.sql.*;			// �����ͺ��̽� ������
import java.awt.event.*;    // �̺�Ʈ

public class server {
	static String aname;	// Ŭ���̾�Ʈ �ڵ��� ���� ���ڿ�
	static String address;	// �����ڿ� ���ؼ� aname, address �и��ϱ����� ����
	static int    asum;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;			// port, client accept
		Socket socket = null;						
		try {
			serverSocket = new ServerSocket(3306);
			socket = serverSocket.accept();
			System.out.println("server connected");
			// ServerSocket port client accept
			// socket

			//InputStream in = socket.getInputStream();
			BufferedReader ain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream out = new DataOutputStream(socket.getOutputStream());

			
			
			while(true)
			{
				String str = ain.readLine();		// android send data receive
				String arg[] = str.split(" "); // " " �������� �Ľ�
				// for(int i=0; i<ar.length; i++){

				aname = arg[0];		// x
				address = arg[1];	// y
				// android ==> javaserver ����Ÿ�� ���ڿ�����
				
				int su1 = Integer.parseInt(aname);
				int su2 = Integer.parseInt(address);
				int sum = 0;
				sum = su1 + su2;
				System.out.println(arg[0]);
				System.out.println(arg[1]);
				System.out.println(sum);
				asum = sum;
				// }
				
				A A1 = new A();		// ����Ÿ���̽� class
				A1.insert();		// A Ŭ����
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				socket.close();

			} catch (Exception ignored) {

			}
			try {
				serverSocket.close();

			} catch (Exception ignored) {

			}
		}
	}
}

class A { // 
	public void insert() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.println(e1);
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:mysql://127.0.0.1:3306/cosmos1"; // ��� ��ġ�� ip ort ����
		String id = "root";									// mysql id
		String pwd = "1234";								// mysql pwd
		String query = null;								// ������ Ȯ��
		System.out.println("driver connected");

		try {
			conn = DriverManager.getConnection(url, id, pwd);
		} catch (SQLException e1) {
			System.err.println(e1);
		}

		while (true) {
			String aname = server.aname;
			String address = server.address;
			int asum = server.asum;

			query = "insert into bravo values(?,?,?)";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, aname);
				pstmt.setString(2, address);
				pstmt.setInt(3, asum);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				break;
				//System.exit(0);
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}

	}
}
