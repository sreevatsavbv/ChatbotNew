package com.java.chatbot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.chatbot.model.ChatRequest;

@RestController
@RequestMapping("/chat")
public class ChatBotController {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";


@PostMapping(value="/chatResponse", produces= {"application/json"})
	public static String getBotResponse(@RequestBody ChatRequest chatRequest) {

		return getChatResponse(chatRequest);
	}

private static String getChatResponse(ChatRequest chatRequest) {
	try {
		String convId =chatRequest.getConversationId();
		List<String> convIdList = new ArrayList<String>();
		convIdList.add("qwertyu123");
		String step= null;
		String textLine = "";
		if(convIdList.contains(convId)) {
			textLine="step1";
			
		}
		String resourcesPath = getResourcesPath();
		System.out.println(resourcesPath);
		MagicBooleans.trace_mode = TRACE_MODE;
		Bot bot = new Bot("super", resourcesPath);
		Chat chatSession = new Chat(bot);
		bot.brain.nodeStats();
		

		while (true) {
			System.out.print("Human : ");
			//textLine = chatRequest.getQuestion();
			if ((textLine == null) || (textLine.length() < 1))
				textLine = MagicStrings.null_input;
			if (textLine.equals("q")) {
				System.exit(0);
			} else if (textLine.equals("wq")) {
				bot.writeQuit();
				System.exit(0);
			} else {
				String request = textLine;
			//	if (MagicBooleans.trace_mode)
					System.out.println(
							"STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
									+ ":TOPIC=" + chatSession.predicates.get("topic"));
				String response = chatSession.multisentenceRespond(request);
				while (response.contains("&lt;"))
					response = response.replace("&lt;", "<");
				while (response.contains("&gt;"))
					response = response.replace("&gt;", ">");
				System.out.println("Robot : " + response);
				return response;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
	
	private String redirectRequest(ChatRequest request) {
		switch(request.getIntent()) {
		case "interest":getInterestData();
				break;
				
		case "common": getDBSGreetData();
		
		}
		return null;
	}

	private void getDBSGreetData() {
		// TODO Auto-generated method stub
		
	}

	private void getInterestData() {
		// TODO Auto-generated method stub
		
	}
}
