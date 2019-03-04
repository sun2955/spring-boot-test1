package com.sun.demo.base.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @date 2018/03
 * @author szy
 * 
 */

public class ZipUtils {
	private static final int BUFFER_SIZE = 4096;

	private static void extractFile(ZipInputStream in, File outdir, String name)
			throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		try (BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(new File(outdir, name)));) {
			int count = -1;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
		} finally {
			// out.close();
		}
	}

	private static void mkdirs(File outdir, String path) {
		File d = new File(outdir, path);
		if (!d.exists()) {
			d.mkdirs();
		}
	}

	private static String dirpart(String name) {
		int s = name.lastIndexOf(File.separatorChar);
		return s == -1 ? null : name.substring(0, s);
	}

	/**
	 * 解压zip文件
	 * 
	 * @param zipfile
	 * @param outdir
	 * @throws Exception
	 */
	public static void extract(File zipfile, File outdir) throws Exception {
		ZipInputStream zin = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(zipfile);
			zin = new ZipInputStream(fileInputStream);
			ZipEntry entry;
			String name, dir;
			while ((entry = zin.getNextEntry()) != null) {
				name = entry.getName();
				if (entry.isDirectory()) {
					mkdirs(outdir, name);
					continue;
				}
				dir = dirpart(name);
				if (dir != null) {
					mkdirs(outdir, dir);
				}
				extractFile(zin, outdir, name);
			}

		} finally {
			if (zin != null) {
				zin.close();
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	public static void extractEx(File zipfile, File outdir) throws IOException {
		try (FileInputStream fis = new FileInputStream(zipfile);
				ZipInputStream zin = new ZipInputStream(fis);) {
			ZipEntry entry;
			String name, dir;
			while ((entry = zin.getNextEntry()) != null) {
				name = entry.getName();
				if (entry.isDirectory()) {
					mkdirs(outdir, name);
					continue;
				}
				dir = dirpart(name);
				if (dir != null) {
					mkdirs(outdir, dir);
				}

				dir = dirpartEx(name);
				if (dir != null) {
					mkdirs(outdir, dir);
				}

				extractFile(zin, outdir, name);
			}
		} catch (IOException e) {
			throw e;
		}

	}

	public static void extract(String zipfile, String outdir) throws Exception {
		extract(new File(zipfile), new File(outdir));
	}

	/**
	 * 解压zip文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static File zipFile(String filePath) throws IOException {
		File target = null;
		File source = new File(filePath);
		if (source.exists()) {
			String zipName = source.getName() + ".zip";
			target = new File(source.getParent(), zipName);
			if (target.exists()) {
				target.delete();
			}
			FileOutputStream fos = null;
			ZipOutputStream zos = null;
			fos = new FileOutputStream(target);
			zos = new ZipOutputStream(new BufferedOutputStream(fos));
			addEntry("", source, zos);
			zos.close();
			fos.close();
		}
		return target;
	}

	/**
	 * 
	 * @param base
	 * @param source
	 * @param zos
	 * @throws IOException
	 */
	public static void addEntry(String base, File source, ZipOutputStream zos)
			throws IOException {
		if (source != null) {
			String entry = base + source.getName();
			if (source.isDirectory()) {
				if (null != source.listFiles()) {
					for (File file : source.listFiles()) {
						addEntry(entry + "/", file, zos);
					}
				}
			} else {
				byte[] buffer = new byte[BUFFER_SIZE];
				try (FileInputStream fis = new FileInputStream(source);
						BufferedInputStream bis = new BufferedInputStream(fis,
								buffer.length);) {

					int read = 0;
					if (zos != null) {
						zos.putNextEntry(new ZipEntry(entry));
						while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
							zos.write(buffer, 0, read);
						}
						zos.closeEntry();
					} else {
						System.out.println("ZipOutputStream is null");
					}
				} finally {
				}

			}
		} else {
			System.out.println("File is null");
		}
	}

	private static String dirpartEx(String name) {
		int s = name.lastIndexOf("/");
		return s == -1 ? null : name.substring(0, s);
	}
}