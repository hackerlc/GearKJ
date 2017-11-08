package org.joker.gear.ui.travelnote.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import gear.yc.com.gearlibrary.ui.adapter.GearRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_travel_notes.view.*
import org.joker.gear.R
import org.joker.gear.base.glide.AGlide
import org.joker.gear.pojo.TravelNoteBook

/**
 * 游记的adapter类
 * 说明一下kotlinx.android.synthetic.main.item_travel_notes.view.*的使用方式
 * 这里主要是使用了*.view.*的方式绑定了控件，并且把所有的控件调用扩展给了View
 * 具体的扩展请看(https://kotlinlang.org/docs/reference/extensions.html)
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
class TravelNotesAdapter(c : Context, d:MutableList<TravelNoteBook.Books>) :
        GearRecyclerViewAdapter<TravelNoteBook.Books, TravelNotesAdapter.Holder>(c,d) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        var view = View.inflate(mContext, R.layout.item_travel_notes, null)
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder?, position: Int) {
        var data = mData[position]
        if (holder != null) {
            AGlide.with(mContext)
                    .load(data.coverImageDefault)
                    .into(holder.itemView.sdvBooksImg)
            holder.itemView.tv_title.text = data.name
        }

        super.onBindViewHolder(holder, position)
    }

    /**
     * adapter holder
     */
    class Holder(v : View) : RecyclerView.ViewHolder(v)
}