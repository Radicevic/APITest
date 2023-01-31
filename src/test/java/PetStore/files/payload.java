package PetStore.files;

public class payload {

    public static String createPetBody (){
        return "{\n" +
                "  \"id\": 52,\n" +
                "  \"category\": {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Blacki\"\n" +
                "  },\n" +
                "  \"name\": \"Cat\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
    }

    public static String createStoreBody (){
        return "{\n" +
                "  \"id\": 7,\n" +
                "  \"petId\": 51,\n" +
                "  \"quantity\": 3,\n" +
                "  \"shipDate\": \"2023-01-24T12:54:24.709Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";
    }
}
