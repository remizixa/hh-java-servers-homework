package resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/counter")
public class CounterApplication extends Application{
        @Override
        public Set<Class<?>> getClasses() {
            Set<Class<?>> classes = new HashSet<Class<?>>();
            classes.add(CounterResource.class);
            return classes;
        }
}
