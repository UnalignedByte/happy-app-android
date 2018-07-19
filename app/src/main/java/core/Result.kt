package com.unalignedbyte.happyapp.core

sealed class Result<T> {
    class Success<T>(override val value: T) : Result<T>()
    class Failure<T>() : Result<T>() {
        override val value: T? = null
    }

    abstract val value: T?

    override fun equals(other: Any?): Boolean {
        if(other is Result<*>) {
            if(this is Result.Success && other is Result.Success<*>) {
                return this.value == other.value
            } else {
                return this is Result.Failure && other is Result.Failure<*>
            }
        }
        return false
    }
}