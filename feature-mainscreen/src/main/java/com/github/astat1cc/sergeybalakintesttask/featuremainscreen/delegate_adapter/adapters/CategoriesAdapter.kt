package com.github.astat1cc.sergeybalakintesttask.featuremainscreen.delegate_adapter.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.R
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.databinding.DelegateItemCategoriesBinding
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.delegate_adapter.DelegateAdapter
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.delegate_adapter.adapters.CategoryItemTag.*
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.delegate_adapter.items.CategoriesItem

class CategoriesAdapter(
    private val categoryItemClickListener: (CategoryItemTag) -> Unit
) :
    DelegateAdapter<CategoriesItem, CategoriesAdapter.CategoriesViewHolder>(CategoriesItem::class.java) {

    private var categoryImageViewItems: List<ImageView>? = null

    private lateinit var selectedItemTag: CategoryItemTag

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DelegateItemCategoriesBinding.inflate(inflater, parent, false)
        setCategoryImageViewItemList(binding)
        categoryImageViewItems?.setOnClickListeners()
        return CategoriesViewHolder(binding)
    }

    private fun setCategoryImageViewItemList(binding: DelegateItemCategoriesBinding) {
        with(binding) {
            categoryImageViewItems = listOf(
                itemPhoneImageView.apply { tag = PHONE },
                itemComputerImageView.apply { tag = COMPUTER },
                itemHealthImageView.apply { tag = HEALTH },
                itemBooksImageView.apply { tag = BOOKS },
                itemUnknownImageView.apply { tag = UNKNOWN }
            )
        }
    }

    override fun bindViewHolder(model: CategoriesItem, viewHolder: CategoriesViewHolder) {
        selectItemByTag(selectedItemTag)
    }

    private fun List<ImageView>.setOnClickListeners() {
        forEach { itemImageView ->
            itemImageView.setOnClickListener { itemClicked ->
                categoryItemClickListener(itemClicked.tag as CategoryItemTag)
            }
        }
    }

    fun setSelectedItem(newSelectedItem: CategoryItemTag) {
        selectedItemTag = newSelectedItem
        selectItemByTag(selectedItemTag)
    }

    private fun selectItemByTag(newSelectedItem: CategoryItemTag) {
        categoryImageViewItems?.find { it.tag == newSelectedItem }?.let { newSelectedItemFound ->
            selectItem(newSelectedItemFound)
        }
    }

    private fun selectItem(it: ImageView) {
        clearAllSelectedItems()
        setSelectedBackground(it)
    }

    private fun setUnselectedBackground(it: ImageView) {
        it.setBackgroundResource(R.drawable.round_shape_white)
        ImageViewCompat.setImageTintList(
            it,
            ColorStateList.valueOf(Color.GRAY)
        )
    }

    private fun setSelectedBackground(it: ImageView) {
        it.setBackgroundResource(R.drawable.round_shape_orange)
        ImageViewCompat.setImageTintList(
            it,
            ColorStateList.valueOf(Color.WHITE)
        )
    }

    private fun clearAllSelectedItems() {
        categoryImageViewItems?.forEach {
            setUnselectedBackground(it)
        }
    }

    inner class CategoriesViewHolder(
        val binding: DelegateItemCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root)
}


enum class CategoryItemTag {
    BOOKS, PHONE, COMPUTER, HEALTH, UNKNOWN
}