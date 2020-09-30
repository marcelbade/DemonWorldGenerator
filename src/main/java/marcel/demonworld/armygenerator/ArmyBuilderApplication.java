package marcel.demonworld.armygenerator;

import marcel.demonworld.armygenerator.frontEnd.AppWindow;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ArmyBuilderApplication {


    static AppWindow appWindow = new AppWindow();

    public static void main(String[] args) {
        //   SpringApplication.run(ArmyBuilderApplication.class, args);

        // TODO [IMPORTANT FOR NOTES!!!] -> instead of the normal main class spring loader code (commented out), use this:
        //  this takes care of the "headless Exception". Solution found here:
        //  https://vvirlan.wordpress.com/2014/12/10/solved-caused-by-java-awt-headlessexception-when-trying-to-create-a-swingawt-frame-from-spring-boot/
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ArmyBuilderApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        // start GUI
        AppWindow.createMainWindow();
    }


}
