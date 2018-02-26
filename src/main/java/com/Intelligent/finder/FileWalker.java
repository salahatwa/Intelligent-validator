package com.Intelligent.finder;

import java.io.File;
import java.io.FileFilter;

public class FileWalker {

	private FileFilter matchFilter;

	private FileFindHandler handler;

	private String baseDir;

	private int matchingFiles;

	private int allFiles;

	public FileWalker() {
		this(new MatchAllFileFilter(), new FileFindHandlerAdapter());
	}

	public FileWalker(FileFilter matchFilter) {
		this(matchFilter, new FileFindHandlerAdapter());
	}

	public FileWalker(FileFindHandler handler) {
		this(new MatchAllFileFilter(), handler);
	}

	public FileWalker(FileFilter matchFilter, FileFindHandler handler) {
		this.matchFilter = matchFilter;
		this.handler = handler;
	}

	public FileFilter getMatchFilter() {
		return matchFilter;
	}

	public void setMatchFilter(FileFilter matchFilter) {
		this.matchFilter = matchFilter;
	}

	public FileFindHandler getHandler() {
		return handler;
	}

	public void setHandler(FileFindHandler handler) {
		this.handler = handler;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public void walk() {
		try {
			File rootDir = new File(baseDir);
			walk(rootDir);
			handler.onComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Preorder traversal of tree
	 * 
	 * @param rootDir
	 */
	protected void walk(File currentDir) {
		File[] files = currentDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				walk(file);
			} else {

				if (matchFilter.accept(file)) {
					matchingFiles++;
					handler.handleFile(file);
				}
				allFiles++;
			}
		}

	}

	public int getMatchingFileCount() {
		return matchingFiles;
	}

	public int getAllFilesCount() {
		return allFiles;
	}

}
