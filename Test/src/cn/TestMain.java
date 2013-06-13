package cn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class TestMain {
	public static void main(String[] args) {
		File userDir = new File(System.getProperty("user.dir"));// $NON-NLS-1$
		String tmpDir = userDir.getAbsoluteFile().getAbsolutePath();
		System.out.println("=====" + tmpDir);
		System.out.println(tests());
		
		long aa =1369807436061l;
		System.out.println(Math.floor(aa/1000));
		System.out.println(aa);
		try {
			InputStream in = new FileInputStream("D:\\KEY.txt");
			System.out.println(in.available());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int tests() {

		int result = new Random().nextInt(100);
		System.out.println("result=" + result);
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		map.put(0, 20);// 20%
		map.put(1, 35);// 15%
		map.put(2, 50);// 15%
		map.put(3, 100);// 50%
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (result < entry.getValue()) {
				return entry.getKey();
			}
		}

		return -1;
	}
}
