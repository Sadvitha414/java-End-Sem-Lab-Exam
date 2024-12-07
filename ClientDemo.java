package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Device.class)
                .addAnnotatedClass(Smartphone.class)
                .addAnnotatedClass(Tablet.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {
            // Create a Device object
            Device device = new Device();
            device.setBrand("Samsung");
            device.setModel("Galaxy A52");
            device.setPrice(400.0);

            // Create Smartphone object
            Smartphone smartphone = new Smartphone();
            smartphone.setBrand("Apple");
            smartphone.setModel("iPhone 13");
            smartphone.setPrice(1000.0);
            smartphone.setOperatingSystem("iOS");
            smartphone.setCameraResolution("12 MP");

            // Create Tablet object
            Tablet tablet = new Tablet();
            tablet.setBrand("Samsung");
            tablet.setModel("Galaxy Tab S7");
            tablet.setPrice(600.0);
            tablet.setScreenSize(11.0);
            tablet.setBatteryLife(15);

            // Start a transaction
            session.beginTransaction();

            // Save the devices (this will insert records into their respective tables)
            session.save(device);
            session.save(smartphone);
            session.save(tablet);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Devices inserted successfully!");
        } finally {
            factory.close();
        }
    }
}
