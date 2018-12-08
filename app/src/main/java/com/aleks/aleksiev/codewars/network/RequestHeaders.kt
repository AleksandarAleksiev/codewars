package com.aleks.aleksiev.codewars.network

interface RequestHeaders {

    val headers: Map<String, String>
    fun setMessageId(messageId: String)
}