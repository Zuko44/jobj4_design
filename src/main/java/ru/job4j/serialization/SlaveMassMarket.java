package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class SlaveMassMarket {
    public static void main(String[] args) throws Exception {
        Slave slave = new Slave(false, 30, "firewater",
                new Contact(11111, "11-111"), new String[]{"Worker", "Married"});
        JAXBContext context = JAXBContext.newInstance(Slave.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(slave, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Slave result = (Slave) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
