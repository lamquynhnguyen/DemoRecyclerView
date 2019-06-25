package com.example.demorecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	private val items = ArrayList<Item>()
	private val mAdapter = ItemAdapter(items)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		items.add(Item(R.drawable.img1, getString(R.string.msg_hello)))
		items.add(Item(R.drawable.img2, getString(R.string.msg_hola)))
		items.add(Item(R.drawable.img3, getString(R.string.msg_konnichiwa)))
		items.add(Item(R.drawable.img4, getString(R.string.msg_nihao)))
		items.add(Item(R.drawable.img5, getString(R.string.msg_xinchao)))

		recyclerView.apply {
			adapter = mAdapter
			itemAnimator = DefaultItemAnimator()
			addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL))
		}

		buttonAdd.setOnClickListener {
			mAdapter.addItem(items.size, Item(R.drawable.img2, getString(R.string.msg_xinchao)))
			recyclerView.scrollToPosition(mAdapter.itemCount - 1)
		}

		buttonRemove.setOnClickListener {
			recyclerView.scrollToPosition(0)

		}
		val callback = DragManagerAdapter(this, mAdapter,
				ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
				ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
		)
		val helper = ItemTouchHelper(callback)
		helper.attachToRecyclerView(recyclerView)
	}
}
