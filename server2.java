package cosmos;

import java.io.*;			// 입출력
import java.net.*;			// 네트워크 소켓통신
import java.sql.*;			// 데이터베이스 쿼리문
import java.awt.event.*;    // 이벤트

public class server {
	static String aname;	// 클라이언트 핸드폰 값을 문자열
	static String address;	// 구분자에 의해서 aname, address 분리하기위한 변수
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
				String arg[] = str.split(" "); // " " 기준으로 파싱
				// for(int i=0; i<ar.length; i++){

				aname = arg[0];		// x
				address = arg[1];	// y
				// android ==> javaserver 데이타는 문자열··
				
				int su1 = Integer.parseInt(aname);
				int su2 = Integer.parseInt(address);
				int sum = 0;
				sum = su1 + su2;
				System.out.println(arg[0]);
				System.out.println(arg[1]);
				System.out.println(sum);
				asum = sum;
				// }
				
				A A1 = new A();		// 데이타베이스 class
				A1.insert();		// A 클래스
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

		String url = "jdbc:mysql://127.0.0.1:3306/cosmos1"; // 디비 설치된 ip ort 디비명
		String id = "root";									// mysql id
		String pwd = "1234";								// mysql pwd
		String query = null;								// 쿼리값 확인
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
