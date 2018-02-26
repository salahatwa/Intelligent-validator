package com.Intelligent.finder;

import java.io.File;

/**
 * Plug in for the FileWalker class, to execute an action whenever a file is found
 * @author Sam
 *
 */
public interface FileFindHandler {

	/**
	 * Execute an action when file is found
	 * @param file the File which was found
	 */
	abstract public void handleFile(File file);
	
	/**
	 * Should be called by the client when no more files will be fed to the FileFindHandler
	 */
	abstract public void onComplete();
	
}
