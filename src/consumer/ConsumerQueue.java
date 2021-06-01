package consumer;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ConsumerQueue {

    @SuppressWarnings("resourse")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message){
                TextMessage textMessage  = (TextMessage)message;
                try{
                    System.out.println(textMessage.getText());
                } catch(JMSException e){
                    e.printStackTrace();
                }
            }

        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
