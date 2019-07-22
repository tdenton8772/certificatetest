# Couchbase SSL Test
### How to test if your SSL certificate is valid for connecting to Couchbase
> This comes from:<br />
[Authenticating a Java Client by Certificate](https://docs.couchbase.com/server/5.5/security/security-x509certsintro.html#cert_auth_for_java_client)

### Getting it
> git clone https://github.com/tdenton8772/certificatetest.git

### The Config File
> keystore: This is the path to the keystore file<br />
> keystore_password: This is the password to access the keystore<br />
> cluster: This is the url to the Couchbase cluster to test against<br />
> bucket: This is the name of the bucket to access<br />
> docid: This is a document id that should exist in the bucket<br />

### Making it run
> Must be run from the directory with the properties file<br />
> Properties file must be updated specifically for the environment to be tested<br />
> mvn clean package<br />
> java -jar ./target/certificatetest-1.0-SNAPSHOT.jar<br />Admins-MacBook-Pro:Downloads
