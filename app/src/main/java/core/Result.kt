package com.unalignedbyte.happyapp.core

class Result<T>  {
    enum class State {
        Success, Failure
    }

    val state: State
    var value: T?
        get() {
            if(state == State.Success) {
                return field
            } else {
                return null
            }
        }

    private constructor(state: Result.State, value: T?) {
        this.state = state
        this.value = value
    }

    override fun equals(other: Any?): Boolean {
        if(other is Result<*>) {
            if(this.state == State.Success && other.state == State.Success) {
                return this.value == other.value
            } else {
                return this.state == State.Failure && other.state == State.Failure
            }
        }

        return false
    }

    companion object {
        fun <T>success(value: T): Result<T> {
            return Result(State.Success, value)
        }

        fun <T>failure(): Result<T> {
            return Result(State.Failure, null)
        }
    }
}