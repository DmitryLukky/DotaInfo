package com.wexberry.dotainfo.AdaptersRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.R

import com.wexberry.dotainfo.room.HeroesRoom

class RoomDotaAdapter(
    private val heroes: List<HeroesRoom>,
    private val layout: Int
) : RecyclerView.Adapter<RoomDotaAdapter.DotaViewHolder>() {

    class DotaViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        internal var nameHero: TextView = v.findViewById(R.id.name_hero)
        internal var attribute: TextView = v.findViewById(R.id.attribute)
        internal var typeAttack: TextView = v.findViewById(R.id.type_attack)
        internal var roles: TextView = v.findViewById(R.id.roles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDotaAdapter.DotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RoomDotaAdapter.DotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomDotaAdapter.DotaViewHolder, position: Int) {
        val current = heroes[position]

        holder.nameHero.text = current.localizedName
        holder.attribute.text = current.primaryAttr
        holder.typeAttack.text = current.attackType
        holder.roles.text = current.roles
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}