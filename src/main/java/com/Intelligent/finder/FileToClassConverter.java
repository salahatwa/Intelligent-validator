package com.Intelligent.finder;

import java.io.File;

/**
 * Convert a File object to a Class
 * 
 * @author Sam
 * 
 */
public class FileToClassConverter {

	private String classPathRoot;

	public FileToClassConverter(String classPathRoot) {
		setClassPathRoot(classPathRoot);
	}

	/**
	 * @param classPathRoot
	 */
	public void setClassPathRoot(String classPathRoot) {
		if (classPathRoot == null) {
			throw new RuntimeException("Class path root must not be null");
		}
		this.classPathRoot = classPathRoot;
	}

	public Class convertToClass(File classFile) {
		Class classInstance = null;
		if (classFile.getAbsolutePath().startsWith(classPathRoot) && classFile.getAbsolutePath().endsWith(".class")) {
			classInstance = getClassFromName(classFile.getAbsolutePath());
		}
		return classInstance;
	}

	private Class getClassFromName(String fileName) {
		try {
			String className = removeClassPathBase(fileName);
			className = FileUtils.removeExtension(className);
			return Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	private String removeClassPathBase(String fileName) {
		String classPart = fileName.substring(classPathRoot.length() + 1);
		String className = classPart.replace(File.separatorChar, '.');
		return className;
	}



}
