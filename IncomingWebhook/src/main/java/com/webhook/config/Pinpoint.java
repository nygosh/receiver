package com.webhook.config;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pinpoint {
	private String pinpointUrl;
	private String batchEnv;
	private Integer sequenceCount;
	private String applicationId;
	private String checkerName;
	private Integer threshold;
	private String userGroupId;
	private String notes;
	private Map checkerValue;
}
