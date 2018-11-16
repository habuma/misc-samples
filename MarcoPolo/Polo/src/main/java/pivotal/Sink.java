package pivotal;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {

	String INPUT = "poloStreamInput";

	@Input(INPUT)
	SubscribableChannel input();

}