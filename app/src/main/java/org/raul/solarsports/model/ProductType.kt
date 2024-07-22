package org.raul.solarsports.model

enum class ProductType {
    //First parameter for capacity

    TYPE_1{
        override fun capacity() = 50
    },
    TYPE_2 {
        override fun capacity() = 100
    },
    TYPE_3{
        override fun capacity() = 400
    };

    abstract fun capacity(): Int

}