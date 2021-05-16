package com.codetron.listviewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var header: String? = null
    private var footer: String? = null
    private val body: ArrayList<Model> = arrayListOf()

    fun setData(header: String?, body: List<Model>, footer: String?) {
        this.header = header

        this.body.clear()
        this.body.addAll(body)

        this.footer = footer

        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
    ) {
        private val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)

        fun bind(title: String?) {
            txtTitle.text = title
        }
    }

    inner class BodyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_body, parent, false)
    ) {

        private val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        private val cbxName = itemView.findViewById<CheckBox>(R.id.cbx_name)

        fun bind(model: Model) {
            txtName.text = model.name

            // Supaya checkbox listener selalu membaca listener baru.
            cbxName.setOnCheckedChangeListener(null)

            cbxName.isChecked = model.isChecked
            cbxName.setOnCheckedChangeListener { _, isChecked ->
                model.isChecked = isChecked
            }
        }
    }

    inner class FooterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
    ) {
        private val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)

        fun bind(title: String?) {
            txtTitle.text = title
        }
    }

    /**
     * Karena body.size sudah di tambah 2 sehingga
     * data 0 => header
     * data ke 0-(n - 1) => body
     * data ke n + 1 => footer
     */
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.item_header
            body.size + 1 -> R.layout.item_footer
            else -> R.layout.item_body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_header -> HeaderViewHolder(parent)
            R.layout.item_footer -> FooterViewHolder(parent)
            else -> BodyViewHolder(parent)
        }
    }

    /**
     * Karena position 0 sudah diambil oleh header,
     * maka untuk mendapatkan nilai body dari 0,
     * harus di kurangi 1
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(this.header)
            is FooterViewHolder -> holder.bind(this.footer)
            is BodyViewHolder -> holder.bind(body[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return body.size + 2
    }
}