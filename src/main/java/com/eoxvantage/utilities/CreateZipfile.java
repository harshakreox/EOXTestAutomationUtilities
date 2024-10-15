package com.eoxvantage.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipfile {

	static CurrentDateandTime date = new CurrentDateandTime();

	static final int BUFFER = 1024;

	// Folder to be Zipped
	static Path folderPath = Paths.get(System.getProperty("user.dir")+ "./Reports/" + date.date().toString());
	List<File> fileList = new ArrayList<File>();

	/**
	 * @throws IOException
	 */
	public static void callZip() throws IOException {

		CreateZipfile zf = new CreateZipfile();
		// get list of files
		List<File> fileList = zf.getFileList(new File(folderPath.toString()));
		// go through the list of files and zip them
		zf.zipFiles(fileList);
	}

	/**
	 * @param fileList
	 * @throws IOException
	 */
	private void zipFiles(List<File> fileList) throws IOException {

		try {
			FileOutputStream fos = new FileOutputStream(folderPath + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {

				if (file.isDirectory()) {

					ZipEntry ze = new ZipEntry(getFileName(file.toString()) + "/");
					try {
						zos.putNextEntry(ze);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						zos.closeEntry();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				} else {

					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis, BUFFER);

					ZipEntry ze = new ZipEntry(getFileName(file.toString()));
					try {
						zos.putNextEntry(ze);
						byte data[] = new byte[BUFFER];
						int count;
						while ((count = bis.read(data, 0, BUFFER)) != -1) {
							zos.write(data, 0, count);
						}
						bis.close();
						zos.closeEntry();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			zos.close();

		} catch (IOException ioExp) {
			System.out.println("Error while zipping " + ioExp.getMessage());
			ioExp.printStackTrace();
		}
	}

	// the method returns a list of files
	/**
	 * @param source
	 * @return
	 */
	private List<File> getFileList(File source) {
		if (source.isFile()) {
			fileList.add(source);
		} else if (source.isDirectory()) {
			String[] subList = source.list();
			// this condition checks for empty directory
			if (subList.length == 0) {
				fileList.add(new File(source.getAbsolutePath()));
			}
			for (String child : subList) {
				getFileList(new File(source, child));
			}
		}
		return fileList;
	}

	/**
	 * @param filePath
	 * @return
	 */
	private String getFileName(String filePath) {
		String name = filePath.substring((((CharSequence) folderPath.toString()).length()) + 1, filePath.length());
		// System.out.println(" name " + name);
		return name;
	}

}
