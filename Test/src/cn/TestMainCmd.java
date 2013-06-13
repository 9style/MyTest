package cn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestMainCmd {
	static Process p = null;

	public static void main(String[] args) throws IOException,
			InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {

				String cmd = "cmd.exe /c start \"abcd\" C:\\Users\\xing.liu\\Desktop\\Script\\Auto_Calculate\\autoScript.bat";
				try {
					p = Runtime.getRuntime().exec(cmd);

					Scanner s = new Scanner(System.in);
					int i = s.nextInt();
					if (i == 5) {
						System.out.println("over");
						System.out.println(p);
						p.destroy();
						p.exitValue();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}
	
//	
//    public static void runbat(String batName) {  
//        try {  
//        	System.out.println(batName);
//            Process ps = Runtime.getRuntime().exec("C:\\Users\\xing.liu\\Desktop\\Script\\Auto_Calculate\\autoScript.bat");  
////            InputStream in = ps.getInputStream();  
////            int c;  
////            while ((c = in.read()) != -1) {  
////                System.out.print(c);// 如果你不需要看输出，这行可以注销掉  
////            }  
////            in.close();  
//            ps.waitFor();  
//          Scanner s = new Scanner(System.in);
//			int i = s.nextInt();
//			if (i == 5) {
//				System.out.println("over");
//				System.out.println(ps);
//				ps.destroy();
//				System.exit(0);
//			}
//  
//        } catch (IOException ioe) {  
//            ioe.printStackTrace();  
//        } catch (Exception e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        System.out.println("child thread done");  
//    }  
//  
//    public static void main(String[] args) {  
//        String batName = "C:\\Users\\xing.liu\\Desktop\\Script\\Auto_Calculate\\autoScript.bat";  
//        runbat(batName);  
//        System.out.println("main thread");  
//    }  

}
