package rabbitmqs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by kmchu on 16/7/27.
 */
public class RabbitmqMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        System.out.println("Begin to test rabbitmq component.");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:123456@localhost:5672");
//        factory.setAutomaticRecoveryEnabled(false);
//        factory.setExceptionHandler(new DefaultExceptionHandler());
//        factory.setRequestedHeartbeat(5);
//        factory.setConnectionTimeout(5);
//        factory.setVirtualHost("/");
//        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel(2);
            channel.basicPublish("try","base.rk",null,"hello word".getBytes());

            System.out.println("After basic publish");
            channel.close();
            System.out.println("Following is to close connection");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("End to test rabbitmq component.");
    }
}
