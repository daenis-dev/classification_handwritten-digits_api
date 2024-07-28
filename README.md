# Classification API

---

### Overview

- Web service that integrates the Classification UI with the Classification API



### API

- **URL:** https://localhost:8080/v1/classify-image-as-digit

- **Method:** POST

- **URL Request Parameters:**
  - image: binary large object (blob)
    - Image of digit to be processed in binary format 
  
- **Response Status:** 200 OK

- **Example Response Value:**

  ```json
  {
      "message": "Digit value for image: [8]"
  }
  ```



### Environment Configuration

- Download and configure Java and Maven

- Configure TLS

  - Create a *certs* directory under the project root

    ```
    >> cd classification-api && mkdir certs && cd certs
    ```

  - Create a keystore under the *certs* directory

    ```
    >> keytool -genkeypair -alias classification-api -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore classification-api.p12 -validity 3650 -storepass changeit
    ```

    - Enter *localhost* when prompted for the first and last name

  - Validate the keystore

    ```
    >> keytool -list -v -keystore classification-api.p12
    ```

  - Register the certificate locally

    - Generate a certificate file for the key

      ```
      >> keytool -export -keystore classification-api.p12 -alias classification-api -file classification-api.crt
      ```

    - Register the certificate locally (as admin)

      ```
      >> keytool -importcert -file classification-api.crt -alias app-name -keystore "C:\Program Files\Java\jdk18.0.2\lib\security\cacerts"
      ```

      - Make sure that the correct Java version and location are referenced
      - Default password is *changeit*

- Build the application with Maven from the project root directory, */classification-api*

  ```
  >> mvn clean install
  ```



### Run the Application

- Run the following command from the project root directory, */classification-api*

  ```
  >> java -Dkeystore-path="classpath:certs/classification-api.p12"
  -Dkeystore-password="changeit"
  -Dkeystore-type="pkcs12"
  -Dkeystore-alias="classification-api"
  -Dimage-upload-file-path="/Users/username/classification-api/src/main/resources/images" -jar target/classification-api-1.0-SNAPSHOT.jar
  ```

  