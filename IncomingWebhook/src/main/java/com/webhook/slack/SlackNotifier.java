package com.webhook.slack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SlackNotifier {

	@Autowired
	private RestTemplate restTemplate;

	public enum SlackTarget {
		// TODO webHookUrl 은 자신의 슬랙 IncomingWebHookAPI로 변경하세요.
		CH_INCOMING("https://hooks.slack.com/services/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", "incoming");

		String webHookUrl;
		String channel;
		SlackTarget(String webHookUrl, String channel) {
			this.webHookUrl = webHookUrl;
			this.channel = channel;
			}
		}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class SlackMessageAttachement {
		private String color;
		private String pretext;
		private String title;
		private String title_link;
		private String text;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class SlackMessage {
		private String text;
		private String channel;
		private List<SlackMessageAttachement> attachments;
		void addAttachment(SlackMessageAttachement attachement) {
			if (this.attachments == null) {
				this.attachments = Lists.newArrayList();
			}
			this.attachments.add(attachement);
		}
	}
	public boolean notify(SlackTarget target, SlackMessageAttachement message) {
		log.debug("Notify[target: {}, message: {}]", target, message);

		SlackMessage slackMessage = SlackMessage.builder().channel(target.channel).attachments(Lists.newArrayList(message)).build();
		try {
			restTemplate.postForEntity(target.webHookUrl, slackMessage, String.class);
			return true;
		} catch (Exception e) {
			log.error("Occur Exception: {}", e); return false;
		}
	}
}
