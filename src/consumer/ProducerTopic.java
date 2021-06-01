package consumer;

import javax.jms.*;
import javax.naming.InitialContext;

public class ProducerTopic {

    @SuppressWarnings("resourse")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        connection.setClientID("stock");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topic = (Destination) context.lookup("loja");
        MessageProducer producer = session.createProducer(topic);


        Message message = session.createTextMessage("<pessoa><id>"+ 122 +"</id></pessoa>");
        producer.send(message);


        session.close();
        connection.close();
        context.close();

    }
}
