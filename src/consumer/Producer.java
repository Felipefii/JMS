package consumer;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class Producer {

    @SuppressWarnings("resourse")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("financeiro");
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 1000; i++){
            Message message = session.createTextMessage("<pessoa><id>"+ i +"</id></pessoa>");
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();

    }
}
