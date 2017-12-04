package com.shopify.api.call

import com.domain.network.ApiCallback
import com.domain.entity.Error
import com.shopify.api.adapter.ErrorAdapter
import com.shopify.buy3.GraphCall
import com.shopify.buy3.GraphError
import com.shopify.buy3.GraphResponse
import com.shopify.buy3.Storefront

abstract class MutationCallWrapper<out T>(private val callback: ApiCallback<T>) : GraphCall.Callback<Storefront.Mutation> {

    internal abstract fun adapt(data: Storefront.Mutation?): T?

    override fun onResponse(response: GraphResponse<Storefront.Mutation>) {
        val result = adapt(response.data())
        if (result != null) {
            callback.onResult(result)
        } else {
            callback.onFailure(Error.Content())
        }
    }

    override fun onFailure(graphError: GraphError) {
        callback.onFailure(ErrorAdapter.adapt(graphError))
    }
}