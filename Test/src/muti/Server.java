package muti;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		Socket socket = serverSocket.accept();
		InputStream in = socket.getInputStream();
		byte[] header = new byte[2];
		System.out.println("begin:"+in.available());
		
		while(in.read(header,0,2)!=-1){
			System.out.println("result:"+new String(header));
		}
		System.out.println("======");
	}
}
