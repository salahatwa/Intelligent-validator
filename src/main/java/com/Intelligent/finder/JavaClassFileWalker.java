package com.Intelligent.finder;


public class JavaClassFileWalker extends FileWalker {
	
	public static final JavaClassFileFilter JAVA_CLASS_FILE_FILTER = new JavaClassFileFilter();
	public static final MatchAllFileFilter ALL_FILES_FILTER = new MatchAllFileFilter();

	public JavaClassFileWalker() {
		super(JAVA_CLASS_FILE_FILTER);
	}


	public JavaClassFileWalker(FileFindHandler handler) {
		super(JAVA_CLASS_FILE_FILTER, handler);
	}


}
