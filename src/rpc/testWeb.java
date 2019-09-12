package rpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class testWeb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String body[]= new String[6];
			body[0]="id";
			body[1]="familyname";
			body[2]="true";
			body[3]="false";
			body[4]="texts";
			body[5]="u";
            JSONObject jsonbody= new JSONObject();
            jsonbody.put("appid",body[0]);
            jsonbody.put("fontFamilyName",body[1]);
            jsonbody.put("bold",body[2]);
            jsonbody.put("italic",body[3]);
            jsonbody.put("textTyped",body[4]);
            jsonbody.put("url",body[5]);
            URL url = new URL("https://eulerity-hackathon.appspot.com/makeText");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setDoOutput(true);
            OutputStream os = httpConn.getOutputStream();
            os.write(jsonbody.toString().getBytes());
            os.flush();
            os.close();
            int responseCode = httpConn.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + httpConn.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        httpConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in .readLine()) != null) {

                    response.append(inputLine);

                }
                in.close();
                System.out.println(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
