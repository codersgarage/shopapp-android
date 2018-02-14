package com.client.shop.ui.item

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.View
import com.client.shop.R
import com.client.shop.getaway.entity.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryItem(context: Context) : ConstraintLayout(context) {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        View.inflate(context, R.layout.item_category, this)
        setBackgroundResource(R.color.white)
    }

    fun setCategory(category: Category) {
        image.setImageURI(category.image?.src)
        titleText.text = category.title
    }
}