package com.example.core

class EventHandler<Event>(val filter: (Event) -> Boolean, val onEvent: (Event) -> Unit)