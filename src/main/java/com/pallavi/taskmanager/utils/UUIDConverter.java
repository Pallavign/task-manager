package com.pallavi.taskmanager.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pallavi.taskmanager.exception.InvalidUUIDFormatException;

import lombok.extern.slf4j.Slf4j;

/**
 * utility classes generally contain static methods or static variables & do not
 * need spring’s dependency injection or lifecycle management.
 * 
 * using <strong> @Component </strong> on a utility class is unnecessary because
 * it does not need to be instantiated or managed by spring.
 */
@Slf4j
public final class UUIDConverter {

	private static final Logger logger = LoggerFactory.getLogger(UUIDConverter.class);

	/**
	 * converts a string representation of a string uuid to a <strong> @UUID
	 * </strong> object.
	 * 
	 * @param id - the string representation of the <strong> @UUID </strong>
	 * @return the <strong> @UUID </strong> object corresponding to the string
	 * @throws <strong> @InvalidUUIDFormatException </strong> if the input string is
	 *                  null or not in the valid <strong> @UUID </strong> format
	 */
	public static final UUID stringToUUIDConverter(String id) {
		logger.debug("Starting Conversion of String to UUID: {} ", id);
		/**
		 * check if the input string is null or does not match the @UUID format
		 */
		if (id == null
				|| !id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
			logger.error("Invalid UUID Format: {} ", id);
			throw new InvalidUUIDFormatException("Invalid UUID Format: " + id);
		}
		/**
		 * convert the valid string to a @UUID object
		 */
		UUID uuid = UUID.fromString(id);
		logger.debug("Successfully Converted string to UUID: {} ", uuid);
		return uuid;
	}
}
