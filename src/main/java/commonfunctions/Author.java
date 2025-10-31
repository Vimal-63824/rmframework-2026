package commonfunctions;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks October 31, 2025
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Custom annotation to specify the author of a test method.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Author {
	String value();
}