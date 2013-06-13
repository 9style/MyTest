package muti;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket socket = new Socket("localhost", 9999);
		OutputStream out = socket.getOutputStream();
		byte[] byts = "abcd".getBytes();
		System.out.println(byts.length);
		out.write(byts);
		out.flush();
		Scanner sc = new Scanner(System.in);
		if (sc.next().equals("1")) {
			out.close();
		}
	}
}
