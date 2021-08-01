package micronuat.cassandra.sample;

import io.micronaut.runtime.Micronaut;


/**
 * This is main class for bootstraping micronuat application
 */
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
