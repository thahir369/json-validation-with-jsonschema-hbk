# json-validation-with-jsonschema-hbk

### This project is to validate incoming json payload with jsonschema

1. To achieve I used [networknt dependency](https://github.com/networknt/json-schema-validator)

````
<dependency>
    <groupId>com.networknt</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>1.0.72</version>
</dependency>
````

2. In addition, I added below jackson dependency to fix serialization issues encountered during
   development

````
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.13.3</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.13.3</version>
</dependency>
````
3. Refer resource/jsonfiles folder for sample data to test this application via postman

4. Test cases are added for happy path and negative scenarios for JsonValidationService
