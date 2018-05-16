package com.jinhui.common.utils.okcoin;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


/**
 * 测试类
 */

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

	public WebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
	}

	public WebSocketClient(URI serverURI) {
		super(serverURI);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		send("{'event':'addChannel','channel':'ok_sub_futureusd_btc_ticker_this_week'}");
		System.out.println("new connection opened");
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("closed with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(String message) {
		System.out.println("received message: " + message);
	}

	@Override
	public void onMessage(ByteBuffer message) {
		System.out.println("received ByteBuffer");
	}

	@Override
	public void onError(Exception ex) {
		System.err.println("an error occurred:" + ex);
	}

	public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException {
		org.java_websocket.client.WebSocketClient client = new WebSocketClient(new URI("wss://real.okcoin.cn:10440/websocket/okcoinapi"));

		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance( "TLS" );
		 sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates

		SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

		client.setSocket( factory.createSocket() );

		client.connect();
	}
}