package org.skylar11d.minecraftp.tntrun.utilities.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @Author Skylar11D
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ACommand {

    String name();
    String permission() default "";
    boolean requiresPlayer();

}
