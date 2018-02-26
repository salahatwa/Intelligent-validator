package com.fetech.test.smart.validate;

import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.Intelligent.core.Validator;
import com.Intelligent.finder.FileUtils;
import com.Intelligent.finder.FileWalker;
import com.Intelligent.finder.JavaClassFileFilter;
import com.Intelligent.finder.JavaClassFileWalker;
import com.Intelligent.finder.JavaClassFinder;

import junit.framework.TestCase;

public class JavaClassFinderTest extends TestCase {

	private String originalClassPath;
	private String originalCustomClassPath;
	private JavaClassFinder classFinder;

	@Override
	protected void setUp() throws Exception {
		classFinder = new JavaClassFinder();
		originalClassPath = System.getProperty("java.class.path");
		originalCustomClassPath = System.getProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY);
		if (originalCustomClassPath != null) {
			System.out.println("custom classpath was already set to=" + originalCustomClassPath);
		}
		System.out.println("original classpath=" + originalClassPath);
	}

	@Override
	protected void tearDown() throws Exception {
		System.setProperty("java.class.path", originalClassPath);
		if (originalCustomClassPath != null) {
			System.setProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY, originalCustomClassPath);
			System.out.println("custom classpath sysproperty reset to=" + originalCustomClassPath);
		} else {
			System.out.println(
					"original custom classpath sysproperty was blank, clearing any custom classpath value value set in tests");
			System.clearProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY);
		}
		// System.getProperties().remove(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY);
	}

	public void testFindExpectedJavaClassFilesForOneFile() throws Throwable {
		List<Class<? extends Validator>> classes = classFinder.findAllMatchingTypes(Validator.class);

		for (Class<? extends Validator> class1 : classes) {

			Type[] genericInterfaces = class1.getGenericInterfaces();

			for (Type type : genericInterfaces) {
				if (type instanceof ParameterizedType) {
					Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();
					
					Class thisClass = (Class) genericTypes[0];
//					for (Type genericType : genericTypes) {
						System.err.println(class1.getName()+" Generic type: " + thisClass);
//					}
				}
				// System.err.println(type.getTypeName());
			}

		}

		assertEquals("found the class", 3, classes.size());

	}

	public void testFindSubclassesClasses() throws Exception {
		Class<FileWalker> toMatch = FileWalker.class;
		String classPathRoot = FileUtils.determineClassPathBase(toMatch);
		System.setProperty("custom.class.path", classPathRoot);
		List<Class<? extends FileWalker>> classes = classFinder.findAllMatchingTypes(toMatch);
		assertEquals("found the subclasses of " + toMatch, 2, classes.size());
		assertTrue(classes.contains(JavaClassFileWalker.class));
		assertTrue(classes.contains(FileWalker.class));

		assertTrue(FileFilter.class.isAssignableFrom(JavaClassFileFilter.class));
	}

	public void testAbilityToUseCustomClassPathProperty() throws Exception {
		Class<FileWalker> toMatch = FileWalker.class;
		String classPathRoot = FileUtils.determineClassPathBase(toMatch);
		System.setProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY, classPathRoot);
		List<Class<? extends FileWalker>> classes = classFinder.findAllMatchingTypes(toMatch);
		assertEquals("found the subclasses of " + toMatch, 2, classes.size());
		assertTrue(classes.contains(JavaClassFileWalker.class));
		assertTrue(classes.contains(FileWalker.class));

		assertTrue(FileFilter.class.isAssignableFrom(JavaClassFileFilter.class));
	}

}
