package com.aleks.aleksiev.codewars.network

import javax.inject.Inject

class HttpRequestHeaders @Inject constructor() : RequestHeaders {

    // region properties
    private val contentHeader = "Content-Type"
    // message id header name
    private val messageIdHeader = "X-RMG-Message-ID"
    private val _headers = mutableMapOf<String, String>()
    override val headers: Map<String, String> = _headers
    // endregion

    init {
        _headers[contentHeader] = "application/json"
    }

    @Synchronized
    override fun setMessageId(messageId: String) {
        _headers[messageIdHeader] = messageId
    }
}