package com.java.chatbot;

import com.java.chatbot.model.ChatRequest;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatBotController c = new ChatBotController();
		ChatRequest req = new ChatRequest();
		req.setQuestion("What kind of loans" );
		String res = c.getBotResponse(req);
		System.out.println(res);
	}

}
