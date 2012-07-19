package mybeans.mydb.compare;

import java.io.*;

public class readFile {

	public static byte[] fileRead(String filename) throws IOException {

		File file = new File(filename);

		if (filename == null || filename == "") {
			throw new NullPointerException("Invalid file url!!!");
		}

		long fileLen = file.length();
		byte[] bytes = new byte[(char) fileLen];

		BufferedInputStream inStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = inStream.read(bytes);
		if (r != fileLen)
			throw new IOException("Read file failed!!!");
		inStream.close();

		return bytes;
	}

}