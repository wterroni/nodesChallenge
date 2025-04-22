package com.example.nodeschallenge.ui.xml

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nodeschallenge.R
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.utils.toBTC
import com.example.nodeschallenge.utils.toFormattedDate

class NodeAdapter(
    private var nodes: List<LightningNode>
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
        holder.capacityTextView.text = "${context.getString(R.string.item_node_capacity)} ${node.capacity.toBTC()} BTC"
        holder.firstSeenTextView.text = "${context.getString(R.string.item_node_first_seen)} ${node.firstSeen.toFormattedDate()}"
        holder.lastUpdatedTextView.text = "${context.getString(R.string.item_node_last_updated)} ${node.updatedAt.toFormattedDate()}"
        holder.locationTextView.text = getLocation(node)
    }

    fun updateData(newNodes: List<LightningNode>) {
        nodes = newNodes

        notifyDataSetChanged()
    }

    private fun getLocation(node: LightningNode): String {
        val city = node.city?.ptBR ?: node.city?.en ?: ""
        val country = node.country?.ptBR ?: node.country?.en ?: ""
        return "$city, $country"
    }

    override fun getItemCount(): Int = nodes.size

}