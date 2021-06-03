package consumer;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ConsumerDLQQueue {

    @SuppressWarnings("resourse")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("DLQ");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message){
                System.out.println(message);
            }

        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
