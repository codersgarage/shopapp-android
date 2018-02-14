package com.shopify.api.adapter

import com.shopify.buy3.Storefront
import com.client.shop.getaway.entity.Checkout

object CheckoutAdapter {

    fun adapt(adaptee: Storefront.Checkout): Checkout {
        return Checkout(
            adaptee.id.toString(),
            adaptee.webUrl,
            adaptee.requiresShipping,
            adaptee.subtotalPrice,
            adaptee.totalPrice,
            adaptee.totalTax,
            adaptee.currencyCode.name,
            adaptee.shippingAddress?.let { AddressAdapter.adapt(it) },
            adaptee.shippingLine?.let { ShippingRateAdapter.adapt(it) }
        )
    }
}