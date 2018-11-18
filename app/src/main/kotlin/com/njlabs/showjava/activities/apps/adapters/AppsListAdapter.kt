/*
 * Show Java - A java/apk decompiler for android
 * Copyright (c) 2018 Niranjan Rajendran
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.njlabs.showjava.activities.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.njlabs.showjava.R
import com.njlabs.showjava.data.PackageInfo
import kotlinx.android.synthetic.main.layout_app_list_item.view.*

class AppsListAdapter(
    private var apps: List<PackageInfo>,
    private val itemClick: (PackageInfo, View) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<AppsListAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val itemClick: (PackageInfo, View) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bindPackageInfo(packageInfo: PackageInfo) {
            with(packageInfo) {
                itemView.itemLabel.text = packageInfo.label
                itemView.itemSecondaryLabel.text = packageInfo.version
                itemView.itemIcon.setImageDrawable(packageInfo.icon)
                itemView.itemCard.cardElevation = 1F
                itemView.itemCard.setOnClickListener { itemClick(this, itemView) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_app_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: AppsListAdapter.ViewHolder, position: Int) {
        holder.bindPackageInfo(apps[position])
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    fun updateList(apps: List<PackageInfo>) {
        this.apps = apps
        notifyDataSetChanged()
    }
}
