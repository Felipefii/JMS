package consumer;

import br.com.caelum.modelo.Pedido;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ConsumerTopicStockSelector {

    @SuppressWarnings("resourse")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();


        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("stock");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "assign", "e-book is null OR e-book=false", false);


        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message){
                ObjectMessage objectMessage  = (ObjectMessage) message;
                try{
                    Pedido pedido = (Pedido) objectMessage.getObject();
                    System.out.println(pedido.getCodigo());
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
