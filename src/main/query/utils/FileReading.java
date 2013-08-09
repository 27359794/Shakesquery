package query.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReading {
	public static String readTextFile(File filePath) throws IOException {
        StringBuilder str = new StringBuilder();
        Scanner sc = new Scanner(filePath);
        while (sc.hasNext()) {
        	str.append(sc.nextLine());
        }
        sc.close();
        return str.toString();
	}
}
