package com.example.data.search

sealed class AppError : RuntimeException {
    private constructor()
    private constructor(message: String?) : super(message)
    private constructor(message: String?, cause: Throwable?) : super(message, cause)
    private constructor(cause: Throwable?) : super(cause)

    sealed class ApiException(cause: Throwable?) : AppError(cause) {
        public class NetworkException(cause: Throwable?) : ApiException(cause)
        public class ServerException(cause: Throwable?) : ApiException(cause)
        public class TimeoutException(cause: Throwable?) : ApiException(cause)
        public class SessionNotFountException(cause: Throwable?) : AppError(cause)
        public class UnknownException(cause: Throwable?) : AppError(cause)
    }

    class UnknownException(cause: Throwable?) : AppError(cause)
}
