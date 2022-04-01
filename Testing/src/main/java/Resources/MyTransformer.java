package Resources;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class MyTransformer implements IAnnotationTransformer {
    public void transform(ITestAnnotation Annotation, Class testsClass, Constructor testconstructor,
	    Method testMethod) {
	Annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
