package com.example.nodeschallenge.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nodeschallenge.R
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.utils.getText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NodeAdapter(
    private var nodes: List<LightningNode>,
    private var selectedLanguage: Language
) : RecyclerView.Adapter<NodeAdapter.NodeViewHolder>() {

    class NodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val aliasTextView: TextView = itemView.findViewById(R.id.alias_text_view)
        val publicKeyTextView: TextView = itemView.findViewById(R.id.public_key_text_view)
        val channelsTextView: TextView = itemView.findViewById(R.id.channels_text_view)
        val capacityTextView: TextView = itemView.findViewById(R.id.capacity_text_view)
        val firstSeenTextView: TextView = itemView.findViewById(R.id.first_seen_text_view)
        val lastUpdatedTextView: TextView = itemView.findViewById(R.id.last_updated_text_view)
        val locationTextView: TextView = itemView.findViewById(R.id.location_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_node, parent, false)
        return NodeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        val node = nodes[position]
        val context = holder.itemView.context

        holder.aliasTextView.text = node.alias
        holder.publicKeyTextView.text = node.publicKey
        holder.channelsTextView.text = "${context.getString(R.string.item_node_channels)} ${node.channels}"
        holder.capacityTextView.text = "${context.getString(R.string.item_node_capacity)} ${formatSatsToBTC(node.capacity)} BTC"
        holder.firstSeenTextView.text = "${context.getString(R.string.item_node_first_seen)} ${formatUnixTime(node.firstSeen)}"
        holder.lastUpdatedTextView.text = "${context.getString(R.string.item_node_last_updated)} ${formatUnixTime(node.updatedAt)}"
        holder.locationTextView.text = getLocation(node)
    }

    fun updateData(newNodes: List<LightningNode>) {
        val diffCallback = NodeDiffCallback(
            oldList = nodes,
            newList = newNodes,
            oldLang = selectedLanguage,
            newLang = selectedLanguage
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        nodes = newNodes
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateLanguage(newLanguage: Language) {
        val diffCallback = NodeDiffCallback(nodes, nodes, selectedLanguage, newLanguage)
        selectedLanguage = newLanguage
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
    }

    private fun getLocation(node: LightningNode): String {
        val langKey = selectedLanguage.key
        val city = node.city?.getText(langKey)
        val country = node.country.getText(langKey)
        return "$city, $country"
    }

    override fun getItemCount(): Int = nodes.size

    private fun formatSatsToBTC(sats: Long): String {
        return String.format("%.8f", sats / 100_000_000.0)
    }

    private fun formatUnixTime(unixTime: Long): String {
        val date = Date(unixTime * 1000)
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(date)
    }

    class NodeDiffCallback(
        private val oldList: List<LightningNode>,
        private val newList: List<LightningNode>,
        private val oldLang: Language,
        private val newLang: Language
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].publicKey == newList[newItemPosition].publicKey
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldNode = oldList[oldItemPosition]
            val newNode = newList[newItemPosition]

            if (oldLang != newLang) return false

            return oldNode == newNode
        }
    }
}