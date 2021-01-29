import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class HttpPostTest
{
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://postman-echo.com/post");
        String postData = "foo1=bar1&foo2=bar2";

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(postData);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null)
                System.out.println(line);
        }
    }
}