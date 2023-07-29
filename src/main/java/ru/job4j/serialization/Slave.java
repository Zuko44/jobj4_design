package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "slave")
@XmlAccessorType(XmlAccessType.FIELD)
public class Slave {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    private String sobriquet;
    private Contact contact;
    private String[] owners;

    public Slave() {
    }

    public Slave(boolean sex, int age, String sobriquet, Contact contact, String[] owners) {
        this.sex = sex;
        this.age = age;
        this.sobriquet = sobriquet;
        this.contact = contact;
        this.owners = owners;
    }

    public static void main(String[] args) throws JAXBException {

        Slave slave = new Slave(false, 30, "firewater",
                new Contact(11111, "11-111"), new String[]{"Worker", "Married"});

        JAXBContext context = JAXBContext.newInstance(Slave.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(slave, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getSobriquet() {
        return sobriquet;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getOwners() {
        return owners;
    }

    @Override
    public String toString() {
        return "Slave{"
                + "sex=" + sex
                + ", age=" + age
                + ", sobriquet='" + sobriquet + '\''
                + ", contact=" + contact
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}
