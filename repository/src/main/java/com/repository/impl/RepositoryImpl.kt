package com.repository.impl

import com.domain.entity.*
import com.repository.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryImpl(private val shopRepository: ShopRepository,
                     private val blogRepository: BlogRepository,
                     private val productRepository: ProductRepository,
                     private val categoryRepository: CategoryRepository,
                     private val cartRepository: CartRepository) : Repository {

    override fun getShop(): Single<Shop> {
        return shopRepository.getShop()
    }

    override fun getCategory(categoryId: String, productPerPage: Int, productPaginationValue: String?,
                             sortBy: SortType?, reverse: Boolean): Single<Category> {
        return categoryRepository.getCategory(categoryId, productPerPage, productPaginationValue,
                sortBy, reverse)
    }

    override fun getCategoryList(perPage: Int, paginationValue: String?, sortBy: SortType?,
                                 reverse: Boolean): Single<List<Category>> {
        return categoryRepository.getCategoryList(perPage, paginationValue, sortBy, reverse)
    }

    override fun getProductList(perPage: Int, paginationValue: Any?, sortBy: SortType?,
                                reverse: Boolean): Single<List<Product>> {
        return productRepository.getProductList(perPage, paginationValue, sortBy, reverse)
    }

    override fun searchProductListByQuery(searchQuery: String, perPage: Int,
                                          paginationValue: String?): Single<List<Product>> {
        return productRepository.searchProductListByQuery(searchQuery, perPage, paginationValue)
    }

    override fun getProduct(productId: String): Single<Product> {
        return productRepository.getProduct(productId)
    }

    override fun getArticleList(perPage: Int, paginationValue: Any?, sortBy: SortType?,
                                reverse: Boolean): Single<List<Article>> {
        return blogRepository.getArticleList(perPage, paginationValue, sortBy, reverse)
    }

    override fun getCartProductList(): Observable<List<CartProduct>> {
        return cartRepository.getCartProductList()
    }

    override fun addCartProduct(cartProduct: CartProduct): Single<CartProduct> {
        return cartRepository.addCartProduct(cartProduct)
    }

    override fun deleteProductFromCart(productVariantId: String): Completable? {
        return cartRepository.deleteProductFromCart(productVariantId)
    }

    override fun changeCartProductQuantity(productVariantId: String, newQuantity: Int): Single<CartProduct>? {
        return cartRepository.changeCartProductQuantity(productVariantId, newQuantity)
    }
}