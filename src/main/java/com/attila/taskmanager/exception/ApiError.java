package com.attila.taskmanager.exception;

public record ApiError(String errorCode, String error, String details) {
}
