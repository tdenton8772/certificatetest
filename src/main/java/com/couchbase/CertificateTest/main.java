/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.CertificateTest;

/**
 *
 * @author tdenton
 */
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.auth.CertAuthenticator;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class main {

    private static Properties m_props;
    private static File propFile;

    public static void main(String... args) {
        m_props = new Properties();
        propFile = new File("main.properties");

        try {
            LoadProperties(propFile);
        } catch (Exception e) {
            System.out.println(e);
        }

        CouchbaseEnvironment env = DefaultCouchbaseEnvironment
                .builder()
                .sslEnabled(true)
                .sslKeystoreFile(m_props.getProperty("keystore"))
                .sslKeystorePassword(m_props.getProperty("keystore_password"))
                .certAuthEnabled(true)
                .build();

        Cluster userCluster = CouchbaseCluster.create(env, m_props.getProperty("cluster"));
        userCluster.authenticate(CertAuthenticator.INSTANCE);
        Bucket travelSample = userCluster.openBucket(m_props.getProperty("bucket"));

        JsonDocument returnedAirline10doc = travelSample.get(m_props.getProperty("docid"));
        System.out.println("Found: " + returnedAirline10doc);
    }

    public static void LoadProperties(File f) throws IOException {
        FileInputStream propStream = null;
        propStream = new FileInputStream(f);
        m_props.load(propStream);
        propStream.close();
    }
}
