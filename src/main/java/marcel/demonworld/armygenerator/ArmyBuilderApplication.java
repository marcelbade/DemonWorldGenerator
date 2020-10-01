package marcel.demonworld.armygenerator;

import marcel.demonworld.armygenerator.frontEnd.InitiateSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

// needs to implement CommandlineRunner so you can run the, ehh, run method to init whatever you wnat to run.
public class ArmyBuilderApplication implements CommandLineRunner {

    @Autowired
    InitiateSwing swing;

    public static void main(String[] args) {

        // TODO [IMPORTANT FOR NOTES!!!] -> instead of the normal main class spring loader code (commented out), use this:
        //  this takes care of the "headless Exception". Solution found here:
        //  https://vvirlan.wordpress.com/2014/12/10/solved-caused-by-java-awt-headlessexception-when-trying-to-create-a-swingawt-frame-from-spring-boot/


        // hand over main class to Spring container
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ArmyBuilderApplication.class);
        //yes, I want a head app (keyboard and mouse input)
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        swing.init();
    }
}
