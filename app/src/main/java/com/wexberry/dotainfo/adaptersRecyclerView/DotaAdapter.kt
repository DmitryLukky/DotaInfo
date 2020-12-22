package com.wexberry.dotainfo.adaptersRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.R
import com.wexberry.dotainfo.network.dataModels.Heroes

class DotaAdapter(
    private val heroes: List<Heroes>,
    private val layout: Int
) : RecyclerView.Adapter<DotaAdapter.DotaViewHolder>() {

    class DotaViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var nameHero: TextView = v.findViewById(R.id.name_hero)
        internal var attribute: TextView = v.findViewById(R.id.attribute)
        internal var typeAttack: TextView = v.findViewById(R.id.type_attack)
        internal var roles: TextView = v.findViewById(R.id.roles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return DotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DotaViewHolder, position: Int) {
        val current = heroes[position]

        holder.nameHero.text = current.nameHero
        holder.attribute.text = current.primaryAttr
        holder.typeAttack.text = current.attackType
        holder.roles.text = current.roles.toString()
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}