package com.commandrunner.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class NoProfilesEnabledCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean matches = true;

		if (context.getEnvironment() != null) {
			MultiValueMap<String, Object> attrs = metadata
					.getAllAnnotationAttributes(NoProfilesEnabled.class.getName());
			if (attrs != null) {
				for (Object value : attrs.get("value")) {
					String[] requiredProfiles = (String[]) value;

					for (String profile : requiredProfiles) {
						if (context.getEnvironment().acceptsProfiles(profile)) {
							matches = false;
						}
					}

				}
			}
		}
		return matches;
	}
}
