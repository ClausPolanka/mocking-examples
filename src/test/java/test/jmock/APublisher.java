package test.jmock;

import jmock.Message;
import jmock.Publisher;
import jmock.Subscriber;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

public class APublisher {

    @Mock
    private Subscriber subscriber;
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void oneSubscriberReceivesAMessage() throws Exception {
        Publisher publisher = new Publisher();
        publisher.add(subscriber);

        final Message message = new Message("message 2");

        context.checking(new Expectations() {{
            oneOf(subscriber).receive(with(allOf(containsString("message"),
                                                 containsString("1"))));
        }});

        publisher.publish(message);
    }

}
