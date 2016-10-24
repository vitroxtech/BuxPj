package squaring.vitrox.buxpj.Network;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import squaring.vitrox.buxpj.Model.SocketMessage;
import squaring.vitrox.buxpj.Support.Config;

import static okhttp3.ws.WebSocket.TEXT;


public class SocketConnection implements WebSocketListener {

    final static OkHttpClient client = createClient();
    private String mSubId;
    private WebSocket mWebSocket;
    private String unsubId;
    private boolean Connected;
    private List<socketConnectionListener> listeners = new ArrayList<socketConnectionListener>();

    public void addListener(socketConnectionListener toAdd) {
        listeners.add(toAdd);
    }

    public SocketConnection() {
        Connected = false;
    }

    public void setMesageparams(String mSubId) {
        this.mSubId = mSubId;
    }

    public void CreateConnection() throws IOException {
        if (!Connected) {
            Request request = new Request.Builder()
                    .url(Config.SOCKET_BASEURL)
                    .addHeader("Authorization", Config.AUTHORIZATION)
                    .get()
                    .addHeader("Accept-Language", Config.LANGUAGE_HEADER)
                    .build();
            WebSocketCall call = WebSocketCall.create(client, request);
            call.enqueue(this);
            Connected = true;
        } else {
            SendSubscriptionMessage();
        }
    }

    public void SendSubscriptionMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"subscribeTo\": [\"trading.product.");
        sb.append(mSubId);
        sb.append("\"],\"unsubscribeFrom\": [\"");
        if (unsubId != null) {
            sb.append("trading.product." + unsubId + "\"]}");
        } else {
            sb.append("\"]}");
        }
        final RequestBody body = RequestBody.create(TEXT, sb.toString());

        new AsyncTask<Void, Void, Boolean>() {
            protected Boolean doInBackground(Void... params) {
                try {
                    doOneMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Boolean result) {
                unsubId = mSubId;
            }

            private void doOneMessage() throws IOException {
                mWebSocket.sendMessage(body);
            }
        }.execute();
    }

    private static OkHttpClient createClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.MILLISECONDS)
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .writeTimeout(0, TimeUnit.MILLISECONDS);
        return clientBuilder.build();
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        mWebSocket = webSocket;
        System.out.println("API CONNECTION ON OPEN");
    }

    @Override
    public void onFailure(IOException e, Response response) {
        System.out.println("API CONNECTION ON FAILURE");
        client.retryOnConnectionFailure();
        Connected = false;
        for (socketConnectionListener hl : listeners) {
            hl.onError(e.getMessage());
        }
    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {
        final String sMs = message.string();
        System.out.println(sMs);
        if (sMs.contains("connect.connected")) {
            SendSubscriptionMessage();
        } else if (sMs.contains(mSubId)) {
            if (sMs.contains("trading.quote")) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                SocketMessage myMessage = mapper.readValue(sMs, SocketMessage.class);
                for (socketConnectionListener hl : listeners)
                    hl.receivedmessage(myMessage);
            }
        }
        message.close();
    }

    @Override
    public void onPong(Buffer payload) {
        System.out.println("API CONNECTION ON PONG");
    }

    @Override
    public void onClose(int code, String reason) {
        System.out.println("API CONNECTION ON CLOSE " + reason);
    }

}