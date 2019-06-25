package com.example.demorecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.Collections.swap


class ItemAdapter(private val itemList: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
	override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
		val view = LayoutInflater.from(p0.context).inflate(R.layout.item_list, p0, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
		val item = itemList[p1]
		p0.bindView(item)
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val imageItem = itemView.findViewById<ImageView>(R.id.imageItem)
		val textItem = itemView.findViewById<TextView>(R.id.textItem)

		fun bindView(item: Item) {
			imageItem.setImageResource(item.imageId)
			textItem.text = item.text
		}
	}

	fun addItem(position: Int, item: Item) {
		itemList.add(item)
		notifyItemInserted(position)
	}

	fun onMove(oldPosition: Int, newPosition: Int) {
		if (oldPosition < newPosition) {
			for (i in oldPosition until newPosition) {
				swap(itemList, i, i + 1)
			}
		} else {
			for (i in oldPosition downTo newPosition + 1) {
				swap(itemList, i, i - 1)
			}
		}
		notifyItemMoved(oldPosition, newPosition)
	}

	fun onSwipe(position: Int) {
		itemList.removeAt(position)
		notifyItemRemoved(position)
	}
}
