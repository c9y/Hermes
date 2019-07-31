package cc.zeala.hermes.utils.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    String name();

    String[] aliases() default "";

    String[] usage() default "&cContact an administrator for help on this command.";

    String permission() default "";

    String noPermissionMessage() default "&cNo permission.";

}
