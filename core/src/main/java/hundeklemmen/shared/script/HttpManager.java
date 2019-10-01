package hundeklemmen.shared.script;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class HttpManager {
    public String get(String s){
        try {
            URL url = new URL(s);
            Scanner a = new Scanner(url.openStream());
            String str = "";
            boolean first = true;
            while(a.hasNext()){
                if(first) str = a.next();
                else str += " " + a.next();
                first = false;
            }
            return str;
        } catch(IOException ex) {
            return null;
        }
    }

    public String post(String url, JSONObject obj) {
        try {
            String payload = obj.toString();
            StringEntity entity = new StringEntity(payload,
                    ContentType.APPLICATION_JSON);

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public String post(String url, String obj) {
        try {
            String payload = obj.toString();
            StringEntity entity = new StringEntity(payload,
                    ContentType.DEFAULT_TEXT);

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public String post(String url, Map<Object,Object> objectMap){
        JSONObject obj = toJson(objectMap);
        return this.post(url, obj);
    }



    public String postWithHeaders(String url, Map<Object,Object> headersObject, Map<Object,Object> objectMap){
        JSONObject Headers_obj = toJson(headersObject);
        JSONObject Body_obj = toJson(objectMap);

        String payload = Body_obj.toString();
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("User-Agent", "Mozilla/5.0");
            for(int i = 0; i<Headers_obj.names().length(); i++){
               post.setHeader(Headers_obj.names().getString(i), (String) Headers_obj.get(Headers_obj.names().getString(i)));
           }

            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getWithHeaders(String url, Map<Object,Object> headersObject){
        JSONObject Headers_obj = toJson(headersObject);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            // add request header
            request.setHeader("User-Agent", "Mozilla/5.0");
            for(int i = 0; i<Headers_obj.names().length(); i++){
                request.setHeader(Headers_obj.names().getString(i), (String) Headers_obj.get(Headers_obj.names().getString(i)));
            }

            HttpResponse response = client.execute(request);

            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public static JSONObject toJson(Map<Object, Object> objectMap){
        JSONObject obj = new JSONObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj;
    }
}
