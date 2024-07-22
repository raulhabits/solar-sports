package org.raul.solarsports.model

enum class ProductSize {
    SMALL{
        override fun value() = 10
    },
    MEDIUM {
        override fun value() = 30
    },
    LARGE{
        override fun value() = 100
    };

    abstract fun value(): Int
}