package org.ez.vk.vk.dao.common.exception.internal;

import java.io.IOException;

public class InternalException extends IOException {
	private final static String INTERNAL_EXCEPTION = "Internal Exception";

	public InternalException() {
		super(INTERNAL_EXCEPTION);
	}
}
