package rabbitmqs;

import com.google.common.base.Stopwatch;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by kmchu on 16/7/28.
 */
public class RabbitMeterByChannelModel {

    private Properties properties;

    private ExecutorService producerEs;

    private ExecutorService consumeEs;

    private String exchangeName = "try";
    private String rabbitMqUri = "amqp://admin:123456@localhost:5672";

    private ConnectionFactory connectionFactory;

    public RabbitMeterByChannelModel(Properties properties) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        this.properties = properties;
        Integer producerSize = Integer.parseInt(properties.getProperty("producer.pool.size", "10"));
        Integer consumerSize = Integer.parseInt(properties.getProperty("consumer.pool.size","10"));
        producerEs = Executors.newFixedThreadPool(producerSize);
        consumeEs = Executors.newFixedThreadPool(consumerSize);

        if(properties.contains("exchange.name")){
            this.exchangeName = properties.getProperty("exchange.name");
        }
        if(properties.containsKey("rabbitmq.uri")){
            this.rabbitMqUri = properties.getProperty("rabbitmq.uri");
        }
        this.connectionFactory = this.composeConnectionFactory();

    }

    private ConnectionFactory composeConnectionFactory() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(rabbitMqUri);

        factory.setRequestedHeartbeat(5);
        factory.setConnectionTimeout(5);
        return factory;
    }


    public void start() throws IOException, TimeoutException, InterruptedException {
        Integer producerSize = Integer.parseInt(this.properties.getProperty("producer.size", "5"));
        Integer consumerSize = Integer.parseInt(this.properties.getProperty("consumer.size","5"));
        final String exchangeName = this.exchangeName;
        final String routingKey = this.properties.getProperty("routingkey","base.rk");
        final String randomString = randomString(400);
        for(int i=0;i< producerSize;i++){
            System.out.println("producer creating "+i+" connection.");
            final Connection connection = this.connectionFactory.newConnection();
            final int index = i+1;
            producerEs.submit(new Runnable() {
                @Override
                public void run() {
                    Channel channel = null;
                    try {
                        Stopwatch stopwatch = Stopwatch.createStarted();

                        for (int j = 0; j < 1000000; j++) {
                            channel = connection.createChannel(index);
                            channel.basicPublish(exchangeName, routingKey, null, (randomString + j).getBytes());
                            try {
                                channel.close();
                            } catch (TimeoutException e) {
                                e.printStackTrace();
                            }
                            if(j% 10000 == 9999){
                                stopwatch.stop();
                                System.out.println("process 9999 cost " + stopwatch.elapsed(TimeUnit.SECONDS));
                                stopwatch.start();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(connection!=null && connection.isOpen()){
                            try {
                                connection.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });

        }
        producerEs.shutdown();

    }


    public void consume() throws IOException, TimeoutException {
        Integer consumerSize = Integer.parseInt(this.properties.getProperty("consumer.size","5"));
        final String queueName = this.properties.getProperty("queue.name","conf");
        for(int i=0;i< consumerSize;i++){
            System.out.println("consumer creating "+i+" connection.");
            final Connection connection = this.connectionFactory.newConnection();
            final int index = i+1;
            consumeEs.submit(new Runnable() {
                @Override
                public void run() {
                    Channel channel = null;
                    try {
                        Stopwatch stopwatch = Stopwatch.createStarted();
                        channel = connection.createChannel(index);
                        int j= 0;
                        while(true){
                            j++;
                            channel.basicConsume(queueName, true, new Consumer() {
                                @Override
                                public void handleConsumeOk(String consumerTag) {

                                }

                                @Override
                                public void handleCancelOk(String consumerTag) {

                                }

                                @Override
                                public void handleCancel(String consumerTag) throws IOException {

                                }

                                @Override
                                public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

                                }

                                @Override
                                public void handleRecoverOk(String consumerTag) {

                                }

                                @Override
                                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                                }
                            });

                            if(j% 10000 == 9999){
                                stopwatch.stop();
                                System.out.println("consume 9999 cost " + stopwatch.elapsed(TimeUnit.SECONDS));
                                stopwatch.start();
                                j = 0;
                            }
                        }

                } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(connection!=null && connection.isOpen()){
                            try {
                                connection.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });

        }
    }


    private String randomString(int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ) {
            sb.append("hello world");
            i=i+11;
        }
        return sb.toString();
    }

    public void shutdown(){
        producerEs.shutdown();
        consumeEs.shutdown();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {
        Properties properties = new Properties();
        properties.put("producer.size","5");
        properties.put("rabbitmq.uri","amqp://admin:admin@vpcz-wangjinhua-rmq-1.vm.elenet.me:5672");
        RabbitMeterByChannelModel rabbitMeterByChannelModel = new RabbitMeterByChannelModel(properties);
        rabbitMeterByChannelModel.start();
        rabbitMeterByChannelModel.consume();

        rabbitMeterByChannelModel.shutdown();
    }
}
