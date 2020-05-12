package com.picpay.desafio.android.data.entities


data class ResultRest<T>(var data: T? = null, val throwable: Throwable? = null, val code: Int? = null) {

    fun isSuccessful() = throwable == null

    fun containsError() = throwable != null

    fun errorMessage() = throwable?.message ?: ""

    fun isCacheSuccessful() = !isSuccessful() && data != null

    companion object {

        /**
         * Cria um resultado de erro a partir de uma mensagem.
         */
        fun <T> error(msg: String?, code: Int? = null) =
            ResultRest<T>(
                throwable = Throwable(msg),
                code = code
            )

        /**
         * Cria uma mensagem de erro a partir de uma exceção.
         */
        fun <T> error(throwable: Throwable, code: Int? = null) =
            ResultRest<T>(
                throwable = throwable,
                code = code
            )

        /**
         * Cria um resultado de sucesso.
         */
        fun <T> success(data: T, code: Int? = null) =
            ResultRest(
                data = data,
                code = code
            )

        fun <T> cacheSuccess(data: T, throwable: Throwable) =
            ResultRest(
                data = data,
                throwable = throwable
            )

    }
}