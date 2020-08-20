package com.webhook.slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webhook.slack.SlackNotifier.SlackMessageAttachement;
import com.webhook.slack.SlackNotifier.SlackTarget;

@RestController
public class SlackSendController {
	@Autowired
	private SlackNotifier slackNotifier;

	@RequestMapping(value = "/slack", method = RequestMethod.POST)
	public ResponseEntity<Boolean> send(@RequestBody SlackMessageAttachement message) {
		return ResponseEntity.ok(slackNotifier.notify(SlackTarget.CH_INCOMING, message));
	}
}
