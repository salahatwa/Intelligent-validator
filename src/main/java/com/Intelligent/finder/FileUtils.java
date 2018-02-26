package com.Intelligent.finder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileUtils {

	//	private static final Logger LOG = Logger.getLogger(FileUtils.class);

	private FileUtils() {

	}

	/**
	 * Gets the file name without the extension
	 * 
	 * @param fileName
	 *            name of the file including extension
	 * @return
	 */
	public static String removeExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		String modifiedName = fileName;
		int index = fileName.lastIndexOf(".");
		if (index > -1) {
			modifiedName = fileName.substring(0, index);
		}
		return modifiedName;
	}

	/**
	 * Determines the path which represents the class path base that the
	 * specified class was read from For now, does not support classes read from
	 * a JAR.
	 * 
	 * This is implemented by find the URL for the class, and then splitting off
	 * the part of the URL path which is not part of the fully qualified Java
	 * class name
	 * 
	 * @param clazz
	 *            the Class to examine
	 * @return a path which represents the root of the class path
	 */
	public static String determineClassPathBase(Class<?> clazz) {
		String classPathBase = getClassFilePath(clazz);
		// in order to compare it with the className, we need to replace
		// directory separators with java
		// package separators
		classPathBase = classPathBase.replace(File.separatorChar, '.');
		// find the portion of the string which contains the fully qualified
		// className
		int index = classPathBase.indexOf(clazz.getName());
		classPathBase = classPathBase.substring(0, index - 1);

		classPathBase = classPathBase.replace('.', File.separatorChar);
		if (StringUtil.stringContains(classPathBase, "%")) {
			try {
				classPathBase = URLDecoder.decode(classPathBase, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return classPathBase;
	}

	/**
	 * Gets the path to a class file
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassFilePath(Class<?> clazz) {
		String className = clazz.getName();
		String resourceName = "/" + className.replace('.', '/') + ".class";
		// get a URL so we can turn it into a file
		URL classURL = clazz.getResource(resourceName);
		// get the file, so we can get a full path reference
		File classFile = new File(classURL.getFile());
		// now we have the full file path
		String classPathBase = classFile.getAbsolutePath();
		return classPathBase;
	}

	/**
	 * Determines whether specified path exists
	 * 
	 * @param path
	 * @return true if exists
	 */
	public static boolean exists(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * Reads the package name of a Class
	 * 
	 * @param object
	 * @return
	 */
	public static String getPackageName(Class<?> clazz) {
		String packageName = StringUtil.EMPTY_STRING;
		String className = clazz.getName();
		int index = className.lastIndexOf(".");
		if (index > -1) {
			packageName = className.substring(0, index);
		}
		return packageName;
	}

	/**
	 * Reads the package name, and then returns it as a relative path.
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getPackageNameAsRelativePath(Class<?> clazz) {
		return getPackageName(clazz).replace(".", "/");
	}

	/**
	 * Gets the path of file, minus the file name. If no path portion, returns
	 * empty string;
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getPath(String fullPath) {
		// String path = StringUtil.EMPTY_STRING;
		// if (fullPath.indexOf(File.separator) > -1) {
		// path = fullPath.substring(0, fullPath.lastIndexOf(File.separator));
		// }
		File file = new File(fullPath);
		String path = file.getParent();
		if (path == null)
			path = StringUtil.EMPTY_STRING;
		return path;
	}

	/**
	 * Renames a file
	 * 
	 * @param currentPath
	 *            currentPath/name of file
	 * @param newPath
	 *            new Path/name of file
	 * @return true if file was renamed, otherwise false
	 */
	public static boolean renameFile(String currentPath, String newPath) {
		boolean succeeded = false;
		try {
			File currentFile = new File(currentPath);
			if (currentFile.exists()) {
				File newFile = new File(newPath);
				succeeded = currentFile.renameTo(newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return succeeded;
	}

	public static void appendLine(String filePath, String text) throws Exception {
		File file = new File(filePath);
		FileWriter writer = new FileWriter(file, true);
		String lineSeparator = System.getProperty("line.separator");
		if (!text.endsWith(lineSeparator)) {
			text += lineSeparator;
		}
		writer.write(text);
		writer.close();
	}

	/**
	 * Concatenates path and file to form filePath valid for current O/S.
	 * Null arguments are accepted with throwing Exception.
	 * 
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static String concatenatePath(String filePath, String fileName) {
		if (fileName == null) {
			fileName = StringUtil.EMPTY_STRING;
		}
		return new File(filePath, fileName).getAbsolutePath();
	}

	public static void deleteFile(String filePath) {
		if (exists(filePath)) {
			new File(filePath).delete();			
		}		
	}

	public static int getLineCount(String fileLocation) {
		int lines = 0;
		if (!exists(fileLocation)) {
			//			LOG.warn("getLineCount(): file does not exist! path=" + fileLocation);
			return lines;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileLocation));
			while (reader.readLine() != null) {
				lines++;
			}		
		} catch (Exception e) {
			//			LOG.error("getLineCount(): exception reading file! path=" + fileLocation + ", ex=" + e, e);
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
		return lines;
	}

	/**
	 * Returns the name only portion of the file path
	 * @param fullPath
	 * @return
	 */
	public static String getFileName(String fullPath) {
		File file = new File(fullPath);
		return file.getName();
	}

}
