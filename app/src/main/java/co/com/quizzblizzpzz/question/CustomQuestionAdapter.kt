package co.com.quizzblizzpzz.question

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.quizzblizzpzz.R

class CustomQuestionAdapter(private val list: ArrayList<String>)
        : RecyclerView.Adapter<CustomQuestionAdapter.ViewHolder>() {

    private var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): String {
        return list?.get(position)
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnClickListener {

        private val item = ItemView.findViewById(R.id.viewItem) as LinearLayout

        init {
            if (clickListener != null){
                item.setOnClickListener(this)
            }
        }

        val textView: TextView = itemView.findViewById(R.id.textCard)

        override fun onClick(p0: View?) {
            if (p0 != null) {
                clickListener?.onItemClick(p0,adapterPosition)
            }
        }

    }

    interface ClickListener {
        fun onItemClick(v: View,position: Int)
    }

}

