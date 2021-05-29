package consumer;

import javax.jms.*;
import javax.naming.InitialContext;

public class Consumer {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        connection.start();

        //cria context, factory, connection

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(queue);

        Message message = consumer.receive();
        System.out.println("Recebendo msg: " + message);

        session.close();
        connection.close();
        context.close();

    }
}
