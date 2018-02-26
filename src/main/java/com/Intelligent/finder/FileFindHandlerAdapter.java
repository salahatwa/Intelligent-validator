/**
 * 
 */
package com.Intelligent.finder;

import java.io.File;

/**
 * Empty implementation of FileFindHandler so that subclasses can extend this
 * and only override the methods they care about
 * @author Sam
 *
 */
public class FileFindHandlerAdapter implements FileFindHandler {
	public void handleFile(File file) {
		// do nothing
	}

	public void onComplete() {
		
	}
}