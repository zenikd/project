package org.ez.vk.exception.internal;

import java.io.IOException;

public class InternalException extends IOException {
	private final static String INTERNAL_EXCEPTION = "Internal Exception";

	public InternalException() {
		super(INTERNAL_EXCEPTION);
	}
}
