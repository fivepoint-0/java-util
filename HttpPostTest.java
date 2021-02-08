import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Util {
    public static String getHTTPParameterString(HashMap<String, String> hm) {

        StringBuilder parameterString = new StringBuilder();

        for (Map.Entry<String, String> mapElement : hm.entrySet()) {
            parameterString.append("&").append(mapElement.getKey()).append("=").append(mapElement.getValue());
        }

        return parameterString.toString().substring(1);
    }
}

class HttpPostTest
{
    public static void main(String[] args) throws Exception {
        // Got the base URL for the API.
        URL url = new URL("https://postman-echo.com/post");

        // Create a HashMap to handle URL POST parameters.
        HashMap<String, String> myParameterHashMap = new HashMap<String, String>();

        myParameterHashMap.put("key1","value1");
        myParameterHashMap.put("key2","value2");
        myParameterHashMap.put("key3","value3");

        String myParameterString = Util.getHTTPParameterString(myParameterHashMap);

        System.out.println("My Parameter String for the POST request: " + myParameterString);

        // Handle connection and set header properties.
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(myParameterString.length()));

        // Try and get output from connection by using output stream.
        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(myParameterString);
        }

        // Get the string from the DataOutStream (dos) by buffering the bytes into an
        // InputStreamReader. The BufferedReader allows us to complete this.
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                conn.getInputStream()))) {
            String line;
            // While the output is not null, print it to the screen.
            while ((line = bf.readLine()) != null)
                System.out.println(line);
        }





    }
}