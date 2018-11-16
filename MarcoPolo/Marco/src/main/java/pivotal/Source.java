package pivotal;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

	String OUTPUT = "poloStreamInput";

	@Output(OUTPUT)
	MessageChannel output();

}