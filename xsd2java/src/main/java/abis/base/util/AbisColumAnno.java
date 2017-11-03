package abis.base.util;

// Notes: per RCP client requirement, move this annotation to base.util package and copy it to AbisBase library.
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface AbisColumAnno
{
    public String name() default "";

    public int length() default 0;

    public int precision() default 0;

    public boolean visible() default true;
}
